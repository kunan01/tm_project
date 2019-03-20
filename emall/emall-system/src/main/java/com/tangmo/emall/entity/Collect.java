package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Collect implements Serializable {

    //收藏表主键
    private Integer collectId;

    //用户id
    private Integer userId;

    //商品id
    private Integer productId;

    //创建时间
    private String createdTime;

    //是否失效 0为失效 1已失效
    private Byte dataFailure;

    //商品对象
    private Product product;
}
