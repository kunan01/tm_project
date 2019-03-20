package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DiscountUser implements Serializable {

    //用户优惠码主键
    private Integer duId;

    //用户id
    private Integer userId;

    //优惠券id
    private Integer disId;

    //是否已使用 0未使用 1已使用 2预使用
    private Byte isUse;

    //创建时间
    private String createdTime;

    //兑换码
    private String conversionCode;







}
