package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {

    //订单表主键
    private Integer orderId;

    //订单编号
    private String orderSn;

    //用户id
    private Integer userId;

    //收货人
    private String consignee;

    //地址行1
    private String addressLine1;

    //地址行2
    private String addressLine2;

    //城市
    private String city;

    //省/州/地区
    private String province;

    //邮政编码
    private String zipCode;

    //国家
    private String country;

    //收货人手机
    private String userPhone;

    //支付类型
    private Byte payType;

    //订单金额
    private Double orderMoney;

    //优惠金额
    private Double discountMoney;

    //运费
    private Double expressMoney;

    //实付金额
    private Double payMoney;

    //快递名称
    private String expressName;

    //快递单号
    private String expressNo;

    //挤出时间
    private String deliverTime;

    //支付时间
    private String payTime;

    //支付单号
    private String payNo;

    //收获时间
    private String receiveTime;

    //订单状态 -4失效 -3交易关闭 -2:退款中 -1:退款完成 0:未支付 1:已支付 2:派送中 3:已收货 4:已删除
    private Byte orderStatus;

    //创建时间
    private String createdTime;

    //修改时间
    private String updatedTime;
}
