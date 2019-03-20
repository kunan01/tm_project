package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAddress implements Serializable {

    //地址id
    private Integer addressId;

    //用户id
    private Integer userId;

    //收货人姓名
    private String userName;

    //地址行一：街道地址/邮政信箱
    private String addressLine1;

    //地址行二：公寓套房等
    private String addressLine2;

    //城市
    private String city;

    //州/省
    private String province;

    //邮政编码
    private String zipCode;

    //国家
    private String country;

    //收货人电话
    private String userPhone;

    //交货说明
    private String instructions;

    //送货说明：进入大楼的安全码或电话
    private String security;

    //是否可以在周末交货 0不是 1是
    private Byte isReceivePackage;

    //是否默认地址 0不是 1是
    private Byte isDefault;

    //地址是否有效 0无效 1有效
    private Byte dataStatus;

    //创建时间
    private String createdTime;

}
