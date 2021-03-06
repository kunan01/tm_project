package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageUs implements Serializable {

    //反馈表id
    private Integer mId;

    //用户id
    private Integer userId;

    //主题
    private String  mTopic;

    //邮箱地址
    private String  mEmail;

    //反馈内容
    private String  mContent;

    //名
    private String  firstName;

    //行
    private String  lastName;

    //订单号
    private String  orderNumber;

    //反馈状态  0未处理  1已处理
    private Byte state;

    //创建时间
    private String  createdTime;
}
