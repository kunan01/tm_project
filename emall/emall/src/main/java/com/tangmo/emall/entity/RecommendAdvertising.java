package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RecommendAdvertising implements Serializable {

    //热门广告表主键
    private Integer raId;

    //广告图
    private String advertisingImage;

    //创建时间
    private String createdTime;
}
