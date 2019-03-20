package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Recommend implements Serializable {

    //热门推荐商品表主键
    private Integer trId;

    //商品id
    private Integer productId;

    //广告id
    private Integer raId;

    //创建时间
    private String createdTime;

    private Product product;

    private Integer[] trIdList;

    private Integer[] productIdList;
}
