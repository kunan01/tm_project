package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Discount implements Serializable {

    //优惠活动主键
    private Integer discountId;

    //活动标题
    private String title;

    //宣传语
    private String description;

    //宣传图片
    private String adImage;

    //开始时间
    private String startTime;

    //结束时间
    private String endTime;

    //活动类型 0满减 1打折 2免邮 3买送
    private Byte eventType;

    //活动规则
    private String eventRule;

    //创建时间
    private String createdTime;

    //活动对象 0全场 1商品
    private Byte objectType;

    //剩余数量
    private Integer objectCount;

    //是否限制数量 0不限 1限制
    private Byte isCount;


}
