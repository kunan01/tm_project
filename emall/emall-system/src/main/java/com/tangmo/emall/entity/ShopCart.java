package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopCart implements Serializable {

    //购物车主键
    private Integer cartId;

    //用户id
    private Integer userId;

    //商品id
    private Integer productId;

    //规格id
    private Integer specId;

    //商品数量
    private Integer productNum;

    //创建时间
    private String createdTime;

    //是否失效 0未失效 1已失效
    private Byte dataFailure;

    //商品对象
    private Product product;

    //商品规格对象
    private ProductSpec productSpec;

}
