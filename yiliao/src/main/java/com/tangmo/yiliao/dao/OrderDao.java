package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.PayOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hanjialin
 * @date 2019-1-3
 * @description
 */
@Mapper
public interface OrderDao {

    //添加订单
    int addOrder(PayOrder payOrder);

    //通过id查询订单信息
    PayOrder getOrderById(String goId);

    //通过支付号查询订单信息
    List<PayOrder> getOrderByPayNo(String payNo);

    //根据订单id增加支付单号
    int updPayNo(@Param("goId") String goId, @Param("payNo") String payNo);

    //根据支付号修改订单状态
    int updateOrderDone(@Param("payNo") String payNo,@Param("type") Byte type);
}
