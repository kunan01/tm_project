package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Trend implements Serializable {

    //趋势商品表主键
    private Integer ttId;

    //商品id
    private Integer productId;

    //趋势id
    private Integer taId;

    //创建时间
    private String createdTime;

    private Product product;

    private Integer[] productIdList;

    private Integer[] ttIdList;
}
