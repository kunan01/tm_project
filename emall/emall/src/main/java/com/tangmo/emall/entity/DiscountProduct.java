package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DiscountProduct implements Serializable {

    //活动商品表主键
    private Integer dtId;

    //商品id
    private Integer targetId;

    //活动id
    private Integer disId;

}
