package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductImage implements Serializable {

    //商品图片表主键
    private Integer imageId;

    //商品id
    private Integer productId;

    //规格id
    private Integer specId;

    //图片描述
    private String imageDesc;

    //图片
    private String imageUrl;

    //是否主图 0不是 1是
    private Byte isMaster;

    //图片排序
    private Integer imageOrder;

    //图片状态 0无效 1有效
    private Byte imageStatus;

    //创建时间
    private String createdTime;

    //修改时间
    private String updatedTime;


}
