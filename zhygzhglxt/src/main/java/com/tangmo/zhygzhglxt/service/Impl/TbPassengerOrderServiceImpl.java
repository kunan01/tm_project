package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.*;
import com.tangmo.zhygzhglxt.entity.*;
import com.tangmo.zhygzhglxt.entity.dto.OrderDto;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbPassengerOrderService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.OrderRelated;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chengge on 2018/10/24.
 */
@Service
public class TbPassengerOrderServiceImpl implements TbPassengerOrderService {

    @Autowired
    TbPassengerOrderMapper tbPassengerOrderMapper;

    @Autowired
    TbSysUserMapper tbSysUserMapper;//用户

    @Autowired
    TbParmMapper tbParmMapper;//参数

    @Autowired
    TbDriverOrderMapper tbDriverOrderMapper;//司机订单

    @Autowired
    TbDriverVerifyMapper tbDriverVerifyMapper;//司机信息

    @Autowired
    TbRegularBusMapper tbRegularBusMapper;//班车车辆管理

    @Autowired
    TbOrderRouteMapper tbOrderRouteMapper;//订单路线

    @Autowired
    TbListenOrderMapper tbListenOrderMapper;//监听订单


    @Override
    public Result addPassengerOrder(TbPassengerOrder tbPassengerOrder) {

        if (tbPassengerOrder == null) {
            return new Result(ResultCode.FAIL, "订单不能为空！");
        }
//        System.out.println("============"+tbPassengerOrder.getEndAddress());
//        System.out.println("============"+tbPassengerOrder.getPeopleNumber());
//        System.out.println("============"+tbPassengerOrder.getStartAddress());
//        System.out.println("============"+tbPassengerOrder.getUserCode());
//        System.out.println("============"+tbPassengerOrder.getStartLatitude());
//        System.out.println("============"+tbPassengerOrder.getStartLongitude());
//        System.out.println("============"+tbPassengerOrder.getEndLatitude());
//        System.out.println("============"+tbPassengerOrder.getEndLongitude());
        if (tbPassengerOrder.getEndAddress() == null ||
                tbPassengerOrder.getPeopleNumber() == null ||
                tbPassengerOrder.getStartAddress() == null ||
                tbPassengerOrder.getUserCode() == null) {
            return new Result(ResultCode.FAIL, "订单信息不全！");
        }
        if (tbPassengerOrder.getTbParmId() == null || "".equals(tbPassengerOrder.getTbParmId())) {
            return new Result(ResultCode.FAIL, "呼叫类型不能为空！");
        }

        if (!("1017".equals(tbPassengerOrder.getTbParmId()))) {
            if (tbPassengerOrder.getStartLatitude() == null || "".equals(tbPassengerOrder.getStartLatitude()) ||
                    tbPassengerOrder.getStartLongitude() == null || "".equals(tbPassengerOrder.getStartLongitude()) ||
                    tbPassengerOrder.getEndLatitude() == null || "".equals(tbPassengerOrder.getEndLatitude()) ||
                    tbPassengerOrder.getEndLongitude() == null || "".equals(tbPassengerOrder.getEndLongitude())) {
                return new Result(ResultCode.FAIL, "开始经度纬度和结束经度纬度不全！");
            }
        } else {//说明叫的是班车
            //根据线路的唯一标识查询那些车辆关联的这条路线
            if (tbPassengerOrder.getBusRoute() == null || "".equals(tbPassengerOrder.getBusRoute())) {
                return new Result(ResultCode.FAIL, "呼叫班车的线路不能为空！");
            }
            List<String> tbRegularBuses = tbRegularBusMapper.selectByBusRoute(tbPassengerOrder.getBusRoute());
            if (tbRegularBuses.size() == 0 || tbRegularBuses == null) {
                return new Result(ResultCode.BUS_ERROR1, "该路线暂时未开通司机接单或者车辆！");
            }
//            List<String> busNumbers = new ArrayList();
//            if(tbRegularBuses.size()>0){
//                for(TbRegularBus tbRegularBus:tbRegularBuses){
//                    busNumbers.add(tbRegularBus.getRegularBusNumber());
//                }
//            }
            //根据车辆的车牌号查询有没有司机，如果没有司机表示该线路不能叫车
            List<TbDriverVerify> tbDriverVerifys = tbDriverVerifyMapper.selectByCarNumbers(tbRegularBuses);
            if (tbDriverVerifys.size() == 0 || tbDriverVerifys == null) {
                return new Result(ResultCode.BUS_ERROR2, "该路线暂时未开通司机接单或者车辆！");
            }
        }
        TbSysUser tbSysUser = tbSysUserMapper.selectByCode(tbPassengerOrder.getUserCode());
        if (!("0".equals(tbSysUser.getUserStatus()))) {   //代表是管理员后台叫的老年帮扶的叫车
            List<TbPassengerOrder> orders = tbPassengerOrderMapper.selOrderStateByUserCode(tbPassengerOrder.getUserCode());

            if (orders.size() > 0) {
                return new Result(ResultCode.ORDER_ERROR, "有订单正在进行中！");
            }
        }
        if (tbSysUser != null) {
            if (tbPassengerOrder.getContact() == null || "".equals(tbPassengerOrder.getContact())) {
                tbPassengerOrder.setContact(tbSysUser.getUserName());
            }
            if (tbPassengerOrder.getPhone() == null || "".equals(tbPassengerOrder.getPhone())) {
                tbPassengerOrder.setPhone(tbSysUser.getPhone());
            }
        }
        String orderCode = EncryptUtil.get32Uuid();
        tbPassengerOrder.setOrderCode(orderCode);//设置订单唯一标识
        tbPassengerOrder.setOrderId(EncryptUtil.get32Uuid());//设置订单主键
        tbPassengerOrder.setOrderNumber(OrderRelated.getOrderIdByUUId());//设置订单编号
        if (tbPassengerOrder.getAppointmentTime() == null || "".equals(tbPassengerOrder.getAppointmentTime())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tbPassengerOrder.setAppointmentTime(sdf.format(new Date()));
        }
        tbPassengerOrderMapper.insertSelective(tbPassengerOrder);
        return new Result(ResultCode.SUCCESS, orderCode);
    }

    /*
     * 根据用户的唯一标识code模糊查找所有订单（可分页）
     * */
    @Override
    public Result selPassOrderByUserCode(String name, String state, String userCode, String endAbout, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        if ("".equals(state)) {
            state = null;
        }
        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }

        List<TbPassengerOrder> tbPassengerOrderList = tbPassengerOrderMapper.selPassOrderByUserCode(name, state, userCode, endAbout);
        if (tbPassengerOrderList != null || tbPassengerOrderList.size() > 0) {
            for (TbPassengerOrder tbPassengerOrder : tbPassengerOrderList) {
                tbPassengerOrder.setCarType(tbParmMapper.selectByPrimaryKey(tbPassengerOrder.getTbParmId()).getParmName());
                if ("1".equals(tbPassengerOrder.getOrderState()) || "2".equals(tbPassengerOrder.getOrderState())) { //代表接单和完成的订单，查询出司机的信息
                    //根据乘客的订单的唯一标识查询被接单的司机的单号，然后查询出司机的详细信息
                    TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selByPassengerOrderCode(tbPassengerOrder.getOrderCode());
                    if (tbDriverOrder != null) {
                        TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByUserCode(tbDriverOrder.getDriverId());
                        tbPassengerOrder.setDriverName(tbDriverVerify.getDriverName());
                        tbPassengerOrder.setDriverNumber(tbDriverVerify.getCarNumber());
                        tbPassengerOrder.setDriverPhone(tbDriverVerify.getPhone());
                        tbPassengerOrder.setRatingState(tbDriverOrder.getRatingState());
                        tbPassengerOrder.setRating(tbDriverOrder.getRating());
                    }
                }
            }
        }

        return new Result(ResultCode.SUCCESS, new PageInfo(tbPassengerOrderList));
    }

    /*
     * 根据用户的唯一标识code模糊查找所有订单（可分页）
     * */
    @Override
    public Result selPassOrderByUserCodeAndType(String name, String userCode, String type, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }


        List<TbPassengerOrder> tbPassengerOrderList = tbPassengerOrderMapper.selPassOrderByUserCodeAndType(name, userCode, type);
        List<Map> listMap = new ArrayList();
        for (TbPassengerOrder tbPassengerOrder : tbPassengerOrderList) {
            Map map = new HashMap();
            map.put("passengerOrderCode", tbPassengerOrder.getOrderCode()); //唯一标识;
            map.put("carCode", tbPassengerOrder.getTbParmId());//车辆类型的唯一标识
            map.put("carType", tbParmMapper.selectByPrimaryKey(tbPassengerOrder.getTbParmId()).getParmName());//车辆类型
            map.put("createTime", tbPassengerOrder.getCreatTime());//下单时间
            map.put("startAddress", tbPassengerOrder.getStartAddress());//开始地
            map.put("endAddress", tbPassengerOrder.getEndAddress());//结束地
            map.put("orderState", tbPassengerOrder.getOrderState());//状态

            map.put("contact", tbPassengerOrder.getContact());//联系人
            map.put("phone", tbPassengerOrder.getPhone());//联系电话
            map.put("peopleNumber", tbPassengerOrder.getPeopleNumber());//同行人数
            map.put("remarks", tbPassengerOrder.getRemarks());//备注
            map.put("appointmentTime", tbPassengerOrder.getAppointmentTime());//预约时间
            listMap.add(map);
        }

        return new Result(ResultCode.SUCCESS, new PageInfo(listMap));
    }

    /**
     * 用户修改订单状态
     *
     * @param orderCode  订单唯一标识
     * @param orderState 订单状态（0未接单 1已接单 2订单完成 3订单已取消）
     * @return
     */
    @Override
    public Result jtUpdateById(String orderCode, String orderState) {


        return new Result(ResultCode.SUCCESS, tbPassengerOrderMapper.jtUpdateById(orderCode, orderState));
    }

    @Override
    public Result selOrderStateByUserCode(String userCode) {

        if (userCode == null || "".equals(userCode)) {
            return new Result(ResultCode.FAIL, "用户的唯一标识不能为空！");
        }
        List<TbPassengerOrder> tbPassengerOrders = tbPassengerOrderMapper.selOrderStateByUserCode(userCode);
        Map map = new HashMap();
        map.put("listenOrderState", "0");
        if (tbPassengerOrders.size() > 0) {
            TbPassengerOrder tbPassengerOrder = tbPassengerOrders.get(0);
            if ("0".equals(tbPassengerOrder.getOrderState())) {
                map.put("state", "0");   //代表未接单的单子
            }
            if ("1".equals(tbPassengerOrder.getOrderState())) {
                map.put("state", "1");   //代表已接单的单子
                //根据乘客订单判断之前是否有发起完成订单的请求
                TbListenOrder tbListenOrder = tbListenOrderMapper.selByPassengerOrderCode(tbPassengerOrders.get(0).getOrderCode());
                if (tbListenOrder != null) {
                    map.put("listenOrderState", "1");   //代表之前发起过完成订单
                }
            }
            map.put("startAddress", tbPassengerOrder.getStartAddress());
            map.put("endAddress", tbPassengerOrder.getEndAddress());
            map.put("passengerOrderCode", tbPassengerOrders.get(0).getOrderCode());
            return new Result(ResultCode.SUCCESS, map);
        } else {
            map.put("state", "4");   //代表没有正在在进行的订单
            return new Result(ResultCode.SUCCESS, map);
        }
    }

    /**
     * 乘客端取消订单
     */
    @Override
    public Result cancelByOrderCode(String passengerOrderCode) {

        if (passengerOrderCode == null || "".equals(passengerOrderCode)) {
            return new Result(ResultCode.FAIL, "乘客订单唯一标识不能为空！");
        }
        tbPassengerOrderMapper.updateStateByPassengerOrderCode(passengerOrderCode, "3");
        tbDriverOrderMapper.updateStateByPassengerOrderCode("2", passengerOrderCode);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 乘客评价订单
     */
    @Override
    public Result ratingByOrderCode(String passengerOrderCode, String opinion, String rating) {

        if (passengerOrderCode == null || "".equals(passengerOrderCode)) {
            return new Result(ResultCode.FAIL, "乘客订单唯一标识不能为空！");
        }
        if (rating == null || "".equals(rating)) {
            return new Result(ResultCode.FAIL, "好评度不能为空！");
        }
        TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selOrderDetailByOrderCode(passengerOrderCode);
        if (tbPassengerOrder == null) {
            return new Result(ResultCode.FAIL, "该订单不存在！");
        }
        TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selByPassengerOrderCode(passengerOrderCode);

        if (tbDriverOrder == null) {
            return new Result(ResultCode.FAIL, "该订单未有司机接单！");
        }

        if ("2".equals(tbPassengerOrder.getOrderState()) && "1".equals(tbDriverOrder.getDriverOrderState())) {

            tbDriverOrderMapper.ratingByOrderCode(passengerOrderCode, opinion, rating);
            return new Result(ResultCode.SUCCESS);
        } else {
            return new Result(ResultCode.FAIL, "该订单未完成！");
        }
    }

    /**
     * 根据乘客订单的唯一标识查询订单的时间和当前时间
     */
    @Override
    public Result selTimeByOrderCode(String passengerOrderCode) {

        if (passengerOrderCode == null || "".equals(passengerOrderCode)) {
            return new Result(ResultCode.FAIL, "乘客订单唯一标识不能为空！");
        }

        TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selOrderDetailByOrderCode(passengerOrderCode);
        Map map = new HashMap();
        map.put("createTime", tbPassengerOrder.getCreatTime());

        map.put("nowTime", new Date());
        return new Result(ResultCode.SUCCESS, map);
    }

    /**
     * 乘客端在被接单后实时查询该订单是否完成，是否是发起线上支付
     */
    @Override
    public Result selPayWayByOrderCode(String passengerOrderCode) {

        if (passengerOrderCode == null || "".equals(passengerOrderCode)) {
            return new Result(ResultCode.FAIL, "乘客订单唯一标识不能为空！");
        }
        Map map = new HashMap();
        TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selOrderDetailByOrderCode(passengerOrderCode);
        if (tbPassengerOrder != null) {
            if ("1".equals(tbPassengerOrder.getPayWay()) && "1".equals(tbPassengerOrder.getOrderState())) {  //说明该订单已被司机发起线上支付
                map.put("price", tbPassengerOrder.getPrice());
                map.put("passengerOrderCode", passengerOrderCode);
                map.put("state", "1"); //代表司机已发起支付
            } else {
                if ("2".equals(tbPassengerOrder.getOrderState())) {  //代表司机已点击完成了该订单
                    map.put("state", "2"); //代表此订单已完成
                }
            }
        }
        return new Result(ResultCode.SUCCESS, map);
    }


    @Override
    public Result selOrderDetailByOrderCode(String passengerOrderCode) {

        if (passengerOrderCode == null || "".equals(passengerOrderCode)) {
            return new Result(ResultCode.FAIL, "用户订单的唯一标识不能为空！");
        }

        TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selOrderDetailByOrderCode(passengerOrderCode);

        if (tbPassengerOrder == null) {
            return new Result(ResultCode.FAIL, "该订单不存在！");
        }

        Map map = new HashMap();
        map.put("payWay", tbPassengerOrder.getPayWay());//支付方式
        map.put("startAddress", tbPassengerOrder.getStartAddress());//开始地址
        map.put("endAddress", tbPassengerOrder.getEndAddress());//结束地址
        map.put("startLongitude", tbPassengerOrder.getStartLongitude());//开始经度
        map.put("startLatitude", tbPassengerOrder.getStartLatitude());//开始纬度
        map.put("endLongitude", tbPassengerOrder.getEndLongitude());//结束经度
        map.put("endLatitude", tbPassengerOrder.getEndLatitude());//结束纬度
        map.put("orderState", tbPassengerOrder.getOrderState());//订单的状态
        map.put("parmId", tbPassengerOrder.getTbParmId());//订单的是什么类型的单子（网约，货车，班车）
        map.put("endAbout", tbPassengerOrder.getEndAbout());//订单的是否实时是否预约
        if ("2".equals(tbPassengerOrder.getOrderState()) && "1".equals(tbPassengerOrder.getPayWay())) {
            map.put("price", tbPassengerOrder.getPrice());//价格
        }
        //根据乘客的订单的唯一标识查询被接单的司机的单号，然后查询出司机的详细信息
        TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selByPassengerOrderCode(tbPassengerOrder.getOrderCode());
        if (tbDriverOrder != null) {
            TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByUserCode(tbDriverOrder.getDriverId());
            map.put("driverName", tbDriverVerify.getDriverName());  //师傅姓名
            map.put("carNumber", tbDriverVerify.getCarNumber());    //师傅车牌号
            map.put("phone", tbDriverVerify.getPhone());            //联系电话
            map.put("driverUserCode", tbDriverVerify.getDriverUserid());//司机用户的id
            map.put("Rating", tbDriverOrder.getRating());           //好评度
            map.put("RatingState", tbDriverOrder.getRatingState());//好评状态（0未评价 1已评价）
            map.put("RatingTime", tbDriverOrder.getRatingTime());  //评价时间
            //map.put("price",tbPassengerOrder.getPrice());         //价格
            if ("1".equals(tbDriverOrder.getDriverOrderState()) && "2".equals(tbPassengerOrder.getOrderState()) && "1018".equals(tbPassengerOrder.getTbParmId())) {
                List<TbOrderRoute> list = tbOrderRouteMapper.selectByDriverOrderCode(tbDriverOrder.getDriverOrderCode());
                map.put("routeDetail", list);                        //路线明细的经纬度
            }
        }
        return new Result(ResultCode.SUCCESS, map);
    }

    @Override
    public Result selOrderStateByOrderCode(String passengerOrderCode) {

        if (passengerOrderCode == null || "".equals(passengerOrderCode)) {
            return new Result(ResultCode.FAIL, "用户订单的唯一标识不能为空！");
        }

        TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selOrderDetailByOrderCode(passengerOrderCode);

        if (tbPassengerOrder == null) {
            return new Result(ResultCode.FAIL, "该订单不存在！");
        }
        Map map = new HashMap();
        if ("1".equals(tbPassengerOrder.getOrderState())) { //代表乘客订单已被接单
            //查询接单的司机
            TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selByPassengerOrderCode(passengerOrderCode);
            //根据司机订单中的司机的唯一标识查询司机的信息
            if (tbDriverOrder != null) {
                TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByUserCode(tbDriverOrder.getDriverId());
                map.put("state", "1");
                map.put("passengerOrderInfo", tbPassengerOrder);
                map.put("driverInfo", tbDriverVerify);
            }
        } else {
            map.put("state", "0");
            //map.put("passengerOrderInfo",tbPassengerOrder);
        }
        return new Result(ResultCode.SUCCESS, map);
    }

    @Override
    public Result selOrderByCondition(OrderDto orderDto) {

        if (orderDto == null) {
            return new Result(ResultCode.FAIL, "条件不呢为空!");
        }

        if (orderDto.getPageSize() != null && orderDto.getPageNo() != null) {
            PageHelper.startPage(orderDto.getPageNo(), orderDto.getPageSize());
        }
        if (orderDto.getName() != null && !"".equals(orderDto.getName())) {
            orderDto.setName("%" + orderDto.getName() + "%");
        } else {
            orderDto.setName("%%");
        }

        if ("".equals(orderDto.getEndAbout())) {
            orderDto.setEndAbout(null);
        }
        if ("".equals(orderDto.getOrderState())) {
            orderDto.setOrderState(null);
        }

        List<TbPassengerOrder> tbPassengerOrders = tbPassengerOrderMapper.selOrderByCondition(orderDto);


//        List<TbPassengerOrder> jtQueryByList=tbPassengerOrderMapper.jtQueryByList(name, orderState, endAbout);
//        for(TbPassengerOrder tbPassengerOrder:jtQueryByList){
//            String names=tbSysUserMapper.jtQueryById(tbPassengerOrder.getUserCode()).getUserName();
//            tbPassengerOrder.setUserName(names);
//        }

        return new Result(ResultCode.SUCCESS, new PageInfo(tbPassengerOrders));
    }


    /**
     * 后台根据查询所有订单
     *
     * @param name       模糊(开始地，结束地)
     * @param orderState 订单状态（0未接单 1已接单 2订单完成 3订单已取消）
     * @param endAbout   是否是预约单 （0是预约订单  1实时订单）
     * @param pageSize   每页几条数据
     * @param pageNo     当前页
     * @return
     */
    @Override
    public Result jtQueryByList(String name, String orderState, String endAbout, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }

        if ("".equals(orderState)) {
            orderState = null;
        }
        if ("".equals(endAbout)) {
            endAbout = null;
        }

        List<TbPassengerOrder> jtQueryByList = tbPassengerOrderMapper.jtQueryByList(name, orderState, endAbout);
        if (jtQueryByList != null && jtQueryByList.size() > 0) {
            for (TbPassengerOrder tbPassengerOrder : jtQueryByList) {
                TbSysUser tbSysUser = tbSysUserMapper.selectByCode(tbPassengerOrder.getUserCode());
                if (tbSysUser != null) {
                    tbPassengerOrder.setUserName(tbSysUser.getUserName());
                }
            }
        }
        return new Result(ResultCode.SUCCESS, new PageInfo(jtQueryByList));
    }


    //    /**
//     * 后台根据统计用户的所有订单下单量
//     */
    @Override
    public Result totalUserOrder(Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        //统计所有用户（包括乘客和司机下的单）
        List<TbSysUser> tbSysUsers = tbSysUserMapper.selAllUserByStatus();
        List<Map> mapList = new ArrayList();
        for (TbSysUser tbSysUser : tbSysUsers) {
            Integer totalOrders = tbPassengerOrderMapper.totalUserOrder(tbSysUser.getCode());//求总数量
            Integer totalQxOrders = tbPassengerOrderMapper.totalUserOrderByState(tbSysUser.getCode(), "3");//求取消量
            Integer totalWcOrders = tbPassengerOrderMapper.totalUserOrderByState(tbSysUser.getCode(), "2");//求完成量
            Map map = new HashMap();
            map.put("totalOrders", totalOrders);//总订单数
            map.put("totalQxOrders", totalQxOrders);//取消的订单数
            map.put("totalWcOrders", totalWcOrders);//完成数量
            map.put("userCode", tbSysUser.getCode());
            map.put("userName", tbSysUser.getUserName());
            mapList.add(map);
        }
        //List<Map> listMap=tbPassengerOrderMapper.totalUserOrder();

        return new Result(ResultCode.SUCCESS, new PageInfo(mapList));
    }


    /**
     * 查询指定订单详情
     *
     * @param orderCode 订单唯一标识
     * @return
     */
    @Override
    public Result jtQueryByListByCode(String orderCode) {

        TbPassengerOrder jtQueryByListByCode = tbPassengerOrderMapper.jtQueryByListByCode(orderCode);

        return new Result(ResultCode.SUCCESS, jtQueryByListByCode);
    }

    /**
     * 删除指定订单（假删）
     *
     * @param orderCode 订单唯一标识
     * @return
     */
    @Override
    public Result jtDelete(String orderCode) {

        return new Result(ResultCode.SUCCESS, tbPassengerOrderMapper.jtDelete(orderCode));
    }


    /**
     * 订单统计：后台根据年月查询订单的完成量和取消量
     *
     * @param timeDate 时间条件
     * @return
     */
    @Override
    public Result selOrderByDate(String timeDate) {

        if ("".equals(timeDate)) {
            timeDate = null;
        }
        Map map = tbPassengerOrderMapper.selOrderByDate(timeDate);

        return new Result(ResultCode.SUCCESS, map);
    }

    /**
     * 订单统计：后台根据年月查询订单 实时单完成单量 预约单完成单量 取消的实时单量 取消的预约单单量
     *
     * @param timeDate 时间条件
     * @return
     */
    @Override
    public Result selOrderCountByDate(String timeDate) {

        if ("".equals(timeDate)) {
            timeDate = null;
        }

        Map map = tbPassengerOrderMapper.selOrderCountByDate(timeDate);

        return new Result(ResultCode.SUCCESS, map);
    }

    /**
     * 订单统计：后台根据年月查询订单 这个月内每天的用户的单量的统计和完成单量
     *
     * @param timeDate 时间条件
     * @return
     */
    @Override
    public Result selOrderCountListByDate(String timeDate) {

        if (timeDate == null || "".equals(timeDate)) {
            return new Result(ResultCode.FAIL, "年份月份不能为空");
        }
        int day = tbSysUserMapper.selectByDay(timeDate); //查询该月有多少天

        String startDate = timeDate + "-01";
        String endDate = timeDate + "-" + day;

        List<Map> list = new ArrayList<>();

        List<Map> mapList = tbPassengerOrderMapper.selOrderCountListByDate(startDate, endDate);
        for (int i = 1; i < day + 1; i++) {
            String date = "";
            if (i < 10) {
                date = "0" + i;
            } else {
                date = i + "";
            }
            date = timeDate + "-" + date;
            Map map = new HashMap();
            map.put("timeDate", date);
            map.put("totalNumber", 0);
            map.put("completeNumber", 0);
            for (int j = 0; j < mapList.size(); j++) {
                if (date.equals(mapList.get(j).get("timeDate").toString())) {  //如果相等
                    map.put("totalNumber", mapList.get(j).get("totalNumber").toString());
                    map.put("completeNumber", mapList.get(j).get("completeNumber").toString());
                    mapList.remove(j);
                }
            }
            list.add(map);
        }
        return new Result(ResultCode.SUCCESS, list);
    }

    /**
     * 订单统计：根据开始时间和结束时间查询每日的订单统计量
     *
     * @return
     */
    @Override
    public Result selOrderByStartDateEndDate(String startDate, String endDate, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (startDate == null || "".equals(startDate)) {
            startDate = null;
        }
        if (endDate == null || "".equals(endDate)) {
            endDate = null;
        }

        List<Map> mapList = tbPassengerOrderMapper.selOrderByStartDateEndDate(startDate, endDate);

        return new Result(ResultCode.SUCCESS, new PageInfo(mapList));
    }

    /**
     * 订单统计：根据开始时间和结束时间且订单类型查询每日的通乡客运的订单统计量(列表)
     *
     * @param name       模糊(开始地，结束地)
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param orderState 订单状态（0未接单 1已接单 2订单完成 3订单已取消）
     * @param pageSize   每页几条数据
     * @param pageNo     当前页
     * @return
     */
    @Override
    public Result selOrderByCountry(String name, String startDate, String endDate, String orderState, String carNumber, Integer pageSize, Integer pageNo) {

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if ("".equals(orderState)) {
            orderState = null;
        }
        if ("".equals(startDate)) {
            startDate = null;
        }
        if ("".equals(endDate)) {
            endDate = null;
        }
        if ("".equals(carNumber)) {
            carNumber = null;
        }
        List<TbPassengerOrder> tbPassengerOrders = new ArrayList();

        if (carNumber == null) {
            tbPassengerOrders = tbPassengerOrderMapper.selOrderByCountry(name, startDate, endDate, orderState);
        } else {
            //根据车牌号，查找司机
            List<String> passengerOrderCodes = null;
            TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByCarNumber(carNumber);
            if (tbDriverVerify != null) {
                //根据司机id查询该司机接的所有同乡客运的订单的乘客订单号
                passengerOrderCodes = tbDriverOrderMapper.selPassengerOrderCodeByDriverCode(tbDriverVerify.getDriverUserid());
            }
            if (passengerOrderCodes != null && passengerOrderCodes.size() > 0) {
                tbPassengerOrders = tbPassengerOrderMapper.selOrderByCountry2(name, startDate, endDate, orderState, passengerOrderCodes);
            }
        }

        if (tbPassengerOrders != null && tbPassengerOrders.size() > 0) {
            for (TbPassengerOrder tbPassengerOrder : tbPassengerOrders) {
                TbSysUser tbSysUser = tbSysUserMapper.selectByCode(tbPassengerOrder.getUserCode());
                if (tbSysUser != null) {
                    tbPassengerOrder.setUserName(tbSysUser.getUserName());
                }
            }
        }

        return new Result(ResultCode.SUCCESS, new PageInfo(tbPassengerOrders));
    }


//    public static void main(String[] args){
//        SimpleDateFormat sdf = new SimpleDateFormat("YY-mm-dd hh:MM:ss");
//        String date = sdf.format(new Date());
//        System.out.println("=========="+date);
//        System.out.println("=========="+System.currentTimeMillis());
//    }

}
