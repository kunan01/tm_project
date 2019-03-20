package com.tangmo.zhygzhglxt.service.Impl;

import com.tangmo.zhygzhglxt.dao.TbDriverOrderMapper;
import com.tangmo.zhygzhglxt.dao.TbListenOrderMapper;
import com.tangmo.zhygzhglxt.dao.TbParmMapper;
import com.tangmo.zhygzhglxt.dao.TbPassengerOrderMapper;
import com.tangmo.zhygzhglxt.entity.TbDriverOrder;
import com.tangmo.zhygzhglxt.entity.TbListenOrder;
import com.tangmo.zhygzhglxt.entity.TbPassengerOrder;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbListenOrderService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by chengge on 2019/3/12.
 */
@Service
public class TbListenOrderServiceImpl implements TbListenOrderService {

    @Autowired
    protected TbListenOrderMapper tbListenOrderMapper;

    @Autowired
    protected TbDriverOrderMapper tbDriverOrderMapper;

    @Autowired
    protected TbPassengerOrderMapper tbPassengerOrderMapper;

    @Override
    public Result addListenOrder(String passengerOrderCode) {

        if (passengerOrderCode == null || "".equals(passengerOrderCode)) {
            return new Result(ResultCode.FAIL, "乘客订单的唯一标识不能为空！");
        }
        //根据乘客订单查询司机接的订单是否存在
        TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selByPassengerOrderCode(passengerOrderCode);

        if (tbDriverOrder == null) {
            return new Result(ResultCode.FAIL, "该订单未有司机接单！");
        }
        //查询之前是否有乘客发起的订单，并查看状态
        TbListenOrder tbListenOrder = tbListenOrderMapper.selByPassengerOrderCode(passengerOrderCode);

        if (tbListenOrder != null) {
            if ("2".equals(tbListenOrder.getState())) { //查看该监听订单是否是司机拒绝的订单
                tbListenOrderMapper.updateByByPassengerOrderCode(passengerOrderCode, "0");//改变监听订单的状态，并且改变订单的发起时间
            }
//            if("1".equals(tbListenOrder.getState())){
//                return new Result(ResultCode.SUCCESS,"该乘客订单司机已同意完成！");
//            }
            return new Result(ResultCode.SUCCESS);
        }

        TbListenOrder listenOrder = new TbListenOrder();
        listenOrder.setId(EncryptUtil.get32Uuid());
        listenOrder.setPassengerOrderCode(passengerOrderCode);
        listenOrder.setDriverOrderCode(tbDriverOrder.getDriverOrderCode());
        listenOrder.setState("0");
        tbListenOrderMapper.insertSelective(listenOrder);

        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result passengerListenOrder(String passengerOrderCode) {

        if (passengerOrderCode == null || "".equals(passengerOrderCode)) {
            return new Result(ResultCode.FAIL, "乘客订单的唯一标识不能为空！");
        }

        TbListenOrder tbListenOrder = tbListenOrderMapper.selByPassengerOrderCode(passengerOrderCode);

        if (tbListenOrder == null) {
            return new Result(ResultCode.FAIL, "乘客端未发起提交完成订单！");
        }

        if ("2".equals(tbListenOrder.getState())) {   //代表司机对该订单请求完成拒绝
            return new Result(ResultCode.LISTEN_ORDER1);//10013
        }

        if ("1".equals(tbListenOrder.getState())) {   //代表司机对该订单请求完成同意
            return new Result(ResultCode.LISTEN_ORDER2);//10014
        }

        return new Result(ResultCode.SUCCESS);//代表状态为0时，司机端没有做任何反应
    }

    @Override
    public Result driverListenOrder(String driverOrderCode) {

        if (driverOrderCode == null || "".equals(driverOrderCode)) {
            return new Result(ResultCode.FAIL, "司机订单的唯一标识不能为空！");
        }
        TbListenOrder tbListenOrder = tbListenOrderMapper.selByDriverOrderCode(driverOrderCode);

        if (tbListenOrder == null) {
            return new Result(ResultCode.FAIL, "乘客端未发起提交完成订单！");
        }

        if ("0".equals(tbListenOrder.getState())) {
            return new Result(ResultCode.LISTEN_ORDER3);//10015 乘客端发起提交完成订单
        }

        return new Result(ResultCode.SUCCESS);
    }

    @Transactional
    @Override
    public Result updateListenOrder(String driverOrderCode, String state) {

        if (driverOrderCode == null || "".equals(driverOrderCode)) {
            return new Result(ResultCode.FAIL, "司机订单的唯一标识不能为空！");
        }
        if (state == null || "".equals(state)) {
            return new Result(ResultCode.FAIL, "是否同意的状态不能为空！");
        }

        TbListenOrder tbListenOrder = tbListenOrderMapper.selByDriverOrderCode(driverOrderCode);

        if (tbListenOrder == null) {
            return new Result(ResultCode.FAIL, "该乘客发起的监听完成订单不存在！");
        }

        if ("1".equals(state)) { //代表同意完成乘客端提交的完成订单
            //同意执行三步 1.改变监听订单的状态为1 2.改变乘客订单的状态为完成 3.改变司机订单的单子为完成
            int a = tbListenOrderMapper.updateByDriverOrderCode(driverOrderCode, state);//完成监听订单
            if (a > 0) {
                tbDriverOrderMapper.updateDriverOrderState(state, driverOrderCode);//完成司机订单
                tbPassengerOrderMapper.updateStatePayByPassengerOrderCode(tbListenOrder.getPassengerOrderCode(), "2", "0");//完成乘客订单。并且支付默认为0显现支付
            }
            return new Result(ResultCode.SUCCESS, "已完成订单！");
        }

        if ("2".equals(state)) { //代表拒绝完成乘客端提交的完成订单
            tbListenOrderMapper.updateByDriverOrderCode(driverOrderCode, state);
            return new Result(ResultCode.SUCCESS, "已拒绝完成订单");
        }
        return new Result(ResultCode.FAIL);
    }

    @Transactional
    @Override
    public Result listenAllOrder() {

        List<TbListenOrder> tbListenOrderList = tbListenOrderMapper.listenAllOrder();//查询所有乘客端发起的监听订单，并且司机端未作出回应的订单
        System.out.println("===开始查询异常订单了===！");
        if (tbListenOrderList != null) {
            if (tbListenOrderList.size() > 0) {
                for (TbListenOrder tbListenOrder : tbListenOrderList) {
                    Long createTime = tbListenOrder.getCreateTime().getTime();
                    //判断创建监听订单五分钟之后和当前时间作比较
                    Long nowDate = new Date().getTime();
                    if ((createTime + 60000) < nowDate) {//代表订单过了五分钟还未处理，自动默认为线下完成该订单
                        int a = tbListenOrderMapper.updateByDriverOrderCode(tbListenOrder.getDriverOrderCode(), "1");//完成监听订单
                        if (a > 0) {
                            tbDriverOrderMapper.updateDriverOrderState("1", tbListenOrder.getDriverOrderCode());//完成司机订单
                            tbPassengerOrderMapper.updateStatePayByPassengerOrderCode(tbListenOrder.getPassengerOrderCode(), "2", "0");//完成乘客订单。并且支付默认为0显现支付
                        }
                    }
                }
            }
        }
        return null;
    }
}
