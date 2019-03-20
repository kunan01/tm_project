package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.*;
import com.tangmo.zhygzhglxt.entity.*;
import com.tangmo.zhygzhglxt.entity.dto.OrderDto;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbDriverOrderService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车主 ServiceImpl
 */
@Service
public class TbDriverOrderServiceImpl implements TbDriverOrderService {

    @Autowired
    TbDriverOrderMapper tbDriverOrderMapper;//车主订单

    @Autowired
    TbPassengerOrderMapper tbPassengerOrderMapper;//用户订单

    @Autowired
    TbSysUserMapper tbSysUserMapper;//用户信息

    @Autowired
    TbDriverVerifyMapper tbDriverVerifyMapper;//用户司机信息

    @Autowired
    TbRegularBusMapper tbRegularBusMapper;//班车车辆信息表

    @Autowired
    TbOrderRouteMapper tbOrderRouteMapper;//订单路线


    @Override
    public Result addDriverOrder(TbDriverOrder tbDriverOrder) {

        if (tbDriverOrder.getDriverId() == null || "".equals(tbDriverOrder.getDriverId())) {
            return new Result(ResultCode.FAIL, "司机的唯一标识不能为空！");
        }

        if (tbDriverOrder.getPassengerOrderCode() == null || "".equals(tbDriverOrder.getPassengerOrderCode())) {
            return new Result(ResultCode.FAIL, "乘客订单的唯一标识不能为空！");
        }

        TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selOrderDetailByOrderCode(tbDriverOrder.getPassengerOrderCode());
        if (tbPassengerOrder == null) {
            return new Result(ResultCode.FAIL, "该乘客订单不存在！");
        }
        if (!("1017".equals(tbPassengerOrder.getTbParmId()) || "1018".equals(tbPassengerOrder.getTbParmId()))) {//说明不是预约班车和同乡客运的单子，所以不可以多接
            List<TbDriverOrder> driverOrders = tbDriverOrderMapper.selectByDriverCodeAndState(tbDriverOrder.getDriverId());
            if (driverOrders.size() > 0) {
                return new Result(ResultCode.ORDER_ERROR, "存在订单未完成，无法接单！");
            }
        }
        if ("0".equals(tbPassengerOrder.getOrderState())) {
            String driverOrderCode = EncryptUtil.get32Uuid();
            String DriverOrderId = EncryptUtil.get32Uuid();
            tbDriverOrder.setDriverOrderCode(driverOrderCode);
            tbDriverOrder.setDriverOrderId(DriverOrderId);
            tbDriverOrder.setTbParmId(tbPassengerOrder.getTbParmId());
            int a = tbDriverOrderMapper.insertSelective(tbDriverOrder);
            if (a > 0) {
                //改变乘客订单的状态为已接单的状态
                tbPassengerOrderMapper.updateStateByPassengerOrderCode(tbDriverOrder.getPassengerOrderCode(), "1");
            }
            return new Result(ResultCode.SUCCESS, driverOrderCode);
        }
        return new Result(ResultCode.FAIL, "该乘客订单已被接单！");
    }


    @Override
    public Result updateOrderPayWay(OrderDto orderDto) {

        if (orderDto == null) {
            return new Result(ResultCode.FAIL, "该订单实体不能为空！");
        }

        if (orderDto.getDriverOrderCode() == null || "".equals(orderDto.getDriverOrderCode())) {
            return new Result(ResultCode.FAIL, "司机订单的唯一标识不能为空！");
        }

        if (orderDto.getPrice() == null || orderDto.getPrice().compareTo(new BigDecimal(0)) < 1 || "".equals(orderDto.getPrice())) {
            return new Result(ResultCode.FAIL, "发起金额不能为空,或格式错误！");
        }

        TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selectByCode(orderDto.getDriverOrderCode());

        if (tbDriverOrder != null) {
            tbPassengerOrderMapper.updatePayWayPriceByPassengerOrderCode(tbDriverOrder.getPassengerOrderCode(), orderDto.getPrice());
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }

    @Override
    public Result selStateByDriverOrderCode(String driverOrderCode) {

        if (driverOrderCode == null || "".equals(driverOrderCode)) {
            return new Result(ResultCode.FAIL, "车主订单的唯一标识不能为空！");
        }

        TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selectByCode(driverOrderCode);

        Map map = new HashMap();

        if (tbDriverOrder != null) {
            if ("1".equals(tbDriverOrder.getDriverOrderState())) {  //代表订单已经完成
                map.put("state", "1");
            } else {
                map.put("state", "0");
            }
        }

        return new Result(ResultCode.SUCCESS, map);
    }

    @Override
    public Result updateDriverOrderState(String state, String code) {

        if (state == null || "".equals(state)) {
            return new Result(ResultCode.FAIL, "状态不能为空！");
        }

        if (code == null || "".equals(code)) {
            return new Result(ResultCode.FAIL, "车主订单的唯一标识不能为空！");
        }

        int a = tbDriverOrderMapper.updateDriverOrderState(state, code);

        if (a > 0) {

            TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selectByCode(code);

            if ("1".equals(state)) { //代表订单完成
                //改变乘客订单的信息
                //tbPassengerOrderMapper.updateStateByPassengerOrderCode(tbDriverOrder.getPassengerOrderCode(),"2");
                tbPassengerOrderMapper.updateStatePayByPassengerOrderCode(tbDriverOrder.getPassengerOrderCode(), "2", "0");

                if ("1018".equals(tbDriverOrder.getTbParmId())) {
                    TbOrderRoute tbOrderRoute = tbOrderRouteMapper.selectByDriverOrderCodeLast(code);
                    if (tbOrderRoute != null) {
                        tbPassengerOrderMapper.updateLastLaLoByPassengerOrderCode(tbDriverOrder.getPassengerOrderCode(), tbOrderRoute.getLa(), tbOrderRoute.getLo());
                    }
                }

            } else if ("2".equals(state)) {//代表订单取消
                //改变乘客订单的信息

            }
        }
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 车主查询乘客所有单子（预约单 实时单）
     *
     * @param endAbout   0预约单 1实时单
     * @param orderState 0未接单 1已接单 2订单完成 3订单已取消
     * @param tbParmId   网约车1006，货车
     * @return
     */
    @Override
    public Result jtQuery(String endAbout, String orderState, String tbParmId, String userCode) {

        if ("".equals(endAbout)) {
            endAbout = null;
        }
        if ("".equals(orderState)) {
            orderState = null;
        }
        if ("".equals(tbParmId)) {
            tbParmId = null;
        }
        if ("1017".equals(tbParmId)) {
            if (userCode == null || "".equals(userCode)) {
                return new Result(ResultCode.FAIL, "用户的唯一标识不能为空！");
            }
            //根据用户id查询车主信息的车牌号
            TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByUserCode(userCode);
            if (tbDriverVerify == null) {
                return new Result(ResultCode.FAIL, "车主信息不存在");
            }
            //根据车牌号查询关联的班车路线
            //tbRegularBusMapper
            //根据路线查询乘客叫的单子
            List<TbPassengerOrder> passengerOrders = tbDriverOrderMapper.selPassengerOrderByBusRoute(endAbout, orderState, tbParmId, tbDriverVerify.getCarNumber());
            return new Result(ResultCode.SUCCESS, passengerOrders);
        } else {
            List<TbPassengerOrder> jtQuery = tbDriverOrderMapper.jtQuery(endAbout, orderState, tbParmId);
            return new Result(ResultCode.SUCCESS, jtQuery);
        }
    }


    /**
     * 车主查询单子详情
     *
     * @param driverOrderCode 司机订单唯一标识
     * @return
     */
    @Override
    public Result jtQueryOrderCode(String driverOrderCode) {

        //司机根据订单唯一code查看司机订单详情
        TbDriverOrder tbDriverOrderList = tbDriverOrderMapper.jtQueryOrderCode(driverOrderCode);

        //根据司机订单中的用户订单唯一code查用户订单详情
        TbPassengerOrder tbPassengerOrderList = tbPassengerOrderMapper.jtQueryByListByCode(tbDriverOrderList.getPassengerOrderCode());

        //根据用户订单中的用户唯一标识查询用户详细信息
        TbSysUser tbSysUserList = tbSysUserMapper.selectByCode(tbPassengerOrderList.getUserCode());
        Map map = new HashMap();
        if ("1".equals(tbDriverOrderList.getDriverOrderState()) && "2".equals(tbPassengerOrderList.getOrderState()) && "1018".equals(tbPassengerOrderList.getTbParmId())) {
            List<TbOrderRoute> list = tbOrderRouteMapper.selectByDriverOrderCode(tbDriverOrderList.getDriverOrderCode());
            map.put("routeDetail", list);                        //路线明细的经纬度
        }

        map.put("DriverOrderList", tbDriverOrderList);
        map.put("PassengerOrderList", tbPassengerOrderList);
        map.put("TbSysUserList", tbSysUserList);

        return new Result(ResultCode.SUCCESS, map);
    }

    /**
     * 车主查询单子详情
     *
     * @param driverOrderCode 司机订单唯一标识
     * @return
     */
    @Override
    public Result selectByDriverOrderCode(String driverOrderCode) {

        //司机根据订单唯一code查看司机订单详情
        TbDriverOrder tbDriverOrderList = tbDriverOrderMapper.jtQueryOrderCode(driverOrderCode);

        if (tbDriverOrderList == null) {
            return new Result(ResultCode.FAIL, "司机订单不存在！");
        }

        //根据司机订单中的用户订单唯一code查用户订单详情
        TbPassengerOrder tbPassengerOrderList = tbPassengerOrderMapper.jtQueryByListByCode(tbDriverOrderList.getPassengerOrderCode());

        if (tbPassengerOrderList == null) {
            return new Result(ResultCode.FAIL, "乘客订单不存在！");
        }
        List<TbOrderRoute> list = new ArrayList<>();

        if ("1".equals(tbDriverOrderList.getDriverOrderState()) && "2".equals(tbPassengerOrderList.getOrderState()) && "1018".equals(tbPassengerOrderList.getTbParmId())) {
            list = tbOrderRouteMapper.selectByDriverOrderCode(tbDriverOrderList.getDriverOrderCode());  //路线明细的经纬度
        }
        Map map = new HashMap();
        map.put("orderState", tbPassengerOrderList.getOrderCode());
        map.put("contact", tbPassengerOrderList.getContact());
        map.put("startAddress", tbPassengerOrderList.getStartAddress());
        map.put("endAddress", tbPassengerOrderList.getEndAddress());
        map.put("parmId", tbPassengerOrderList.getTbParmId());
        map.put("payWay", tbPassengerOrderList.getPayWay());
        map.put("phone", tbPassengerOrderList.getPhone());
        map.put("Price", tbPassengerOrderList.getPrice());
        map.put("startLongitude", tbPassengerOrderList.getStartLongitude());//开始经度
        map.put("startLatitude", tbPassengerOrderList.getStartLatitude());//开始纬度
        map.put("endLongitude", tbPassengerOrderList.getEndLongitude());//结束经度
        map.put("endLatitude", tbPassengerOrderList.getEndLatitude());//结束纬度
        map.put("orderRoute", list);

        return new Result(ResultCode.SUCCESS, map);
    }

    /**
     * 车主查询自己所有单子状态
     *
     * @param driverId         车主id
     * @param driverOrderState 订单状态（0 未完成  1完成  2 取消）
     * @return
     */
    @Override
    public Result jtQyeryById(String driverId, String driverOrderState) {

        if (driverId == null || "".equals(driverId)) {
            return new Result(ResultCode.FAIL, "司机用户的id不能为空！");
        }
        if (driverOrderState == null || "".equals(driverOrderState)) {
            return new Result(ResultCode.FAIL, "订单状态不能为空！");
        }

        List<TbDriverOrder> jtQyeryById = tbDriverOrderMapper.jtQyeryById(driverId, driverOrderState);

        if (jtQyeryById.size() > 0) {
            for (TbDriverOrder tbDriverOrder : jtQyeryById) {

                TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selOrderDetailByOrderCode(tbDriverOrder.getPassengerOrderCode());

                if (tbPassengerOrder == null) {
                    return new Result(ResultCode.FAIL, "该乘客订单有误或不存在！");
                }

                tbDriverOrder.setStartAddress(tbPassengerOrder.getStartAddress());
                tbDriverOrder.setEndAddress(tbPassengerOrder.getEndAddress());
                tbDriverOrder.setAppointmentTime(tbPassengerOrder.getAppointmentTime());

                if ("1016".equals(tbPassengerOrder.getTbParmId())) {
                    tbDriverOrder.setGoodsName(tbPassengerOrder.getGoodsName());
                    tbDriverOrder.setGoodsDetail(tbPassengerOrder.getGoodsDetails());
                }
            }
        }

        return new Result(ResultCode.SUCCESS, jtQyeryById);

    }
}
