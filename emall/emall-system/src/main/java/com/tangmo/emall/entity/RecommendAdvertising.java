package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RecommendAdvertising implements Serializable {


    private static final long serialVersionUID = 8174241888522319293L;
    //热门广告表主键
    private Integer raId;

    //广告图
    private String advertisingImage;

    //创建时间
    private String createdTime;

    //
    private String descript;

    private Byte location;

    private String title;

    private Integer productId;

    private Integer[] raIdList;

    private Product product;
}
