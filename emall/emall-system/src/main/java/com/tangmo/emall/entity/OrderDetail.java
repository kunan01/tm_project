package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetail implements Serializable {

    //订单明细表 主键
    private Integer detailId;

    //订单id
    private Integer orderId;

    //商品id
    private Integer productId;

    //店铺id
    private Integer shopId;

    //规格id
    private Integer specId;

    //商品名
    private String productName;

    //购买数量
    private Integer productCount;

    //购买单价
    private Double productPrice;

    //修改时间
    private String updatedTime;

    //优惠券id
    private Integer disId;

    //配送方式id
    private Integer ExpressId;

    private Integer userId;

    private Integer addressId;

    //订单信息
    private Order order;

    //商品信息
    private Product product;

    //规格信息
    private ProductSpec productSpec;

    //店铺信息


}
