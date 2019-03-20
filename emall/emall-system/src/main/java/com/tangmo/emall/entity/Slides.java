package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Slides  implements Serializable {

    //轮播图id
    private Integer slidesId;

    //图片
    private String slideImage;

    //排序
    private Integer slideSort;

    //图片跳转链接
    private String imageUrl;

    //是否有效 1有效 2无效
    private Byte isEffect;

    //创建时间
    private String createdTime;

    //目标分类 1商品类别 2商品
    private Byte objectType;

    //目标id
    private Integer objectId;

    private Integer[] slidesIdList;
}
