package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TrendAdvertising implements Serializable {

    //趋势广告主键
    private Integer taId;

    //趋势标题
    private String title;

    //广告图
    private String advertisingImage;

    //描述语
    private String descript;

    //页面样式 12345
    private Byte location;

    //创建时间
    private String createdTime;

    private String[] images;

    public void setAdvertisingImage(String advertisingImage) {
        if(advertisingImage != null){
            this.advertisingImage = advertisingImage;
            this.images = advertisingImage.split(",");
        }

    }

    private List<Product> products;
}
