package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopDelivery implements Serializable {

    //店铺发货地址id
    private String daId;

    //店铺id
    private String shopId;

    //发件人姓名
    private String senderName;

    //发件人电话
    private String senderPhone;

    //地址行1
    private String addressLine1;

    //地址行2
    private String addressLine2;

    //城市/区域
    private String city;

    //州/省
    private String province;

    //邮政编码
    private String zipCode;

    //国家
    private String country;

    //创建时间
    private String createdTime;
}
