package com.tangmo.emall.service;

import com.tangmo.emall.entity.OrderDetail;
import com.tangmo.emall.utils.Result;

public interface OrderService {

    //生成订单
    Result addOrder(OrderDetail orderDetail);

    //取消订单
    Result cancelOrder(Integer detailId);

    //删除订单
    Result deleteOrder(Integer detailId);

    //查询订单列表
    Result queryOrderList(Integer userId,Integer state,Integer pageNo,Integer pageSize);

    //查询订单详情
    Result getOrderDetailById(Integer detailId);

    //确认收货
    Result ConfirmOrder(Integer detailId);

    //获取支付页面信息
    Result getPayInformation(String detailId);

}
