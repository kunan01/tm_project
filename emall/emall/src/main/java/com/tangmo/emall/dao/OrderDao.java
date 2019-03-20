package com.tangmo.emall.dao;

import com.tangmo.emall.entity.Order;
import com.tangmo.emall.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    //添加订单
    int addOrder(Order order);

    //添加订单明细
    int addOrderDetail(OrderDetail orderDetail);

    //通过明细id查询订单明细信息
    OrderDetail getOrderDetailById(Integer detailId);

    //通过订单id查询订单详情信息
    Order getOrderById(Integer orderId);

    //取消订单
    int cancelOrder(Integer orderId);

    //删除订单
    int deleteOrder(Integer orderId);

    //确认收货
    int ConfirmOrder(Integer orderId);

    //更新支付单号
    int updPaypalNo(@Param("orderId") Integer orderId,@Param("payNo") String payNo);

    //通过支付单号更新订单信息
    int updateOrderInformation(String payNo);

    //查询订单列表
    List<OrderDetail> getOrderListByUserId(@Param("userId")Integer userId,@Param("state") Integer state);

    //查询订单详情（包含订单/商品/规格/店铺）
    OrderDetail getOrderDetailAllById(Integer detailId);

    //查询过期订单
    List<OrderDetail> getBackOrder();

    //更新订单状态为失效
    int updateFailure(Integer orderId);
}
