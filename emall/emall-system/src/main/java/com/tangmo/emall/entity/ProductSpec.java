package com.tangmo.emall.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductSpec implements Serializable {

    //商品规格表主键
    private Integer specId;

    //商品id
    private Integer productId;

    //商品规格json
    private String productSpecs;

    //商品规格json
    private JSONObject productSpecsJson;

    //库存
    private Integer stock;

    //价格
    private Double price;

    //预留字段
    private String status;

    //创建时间
    private String createdTime;

    //修改时间
    private String updatedTime;

    //是否删除 0否 1是
    private Byte dataFlag;

    //优惠后的价格
    private Double preferentialPrice;

    private List<ProductImage> productImages;

    private String shopUserId;



}
