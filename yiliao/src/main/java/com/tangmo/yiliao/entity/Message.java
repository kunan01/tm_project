package com.tangmo.yiliao.entity;

import lombok.Data;

@Data
public class Message {
    private String miId;
    private String title;
    private String miContent;
    private Byte   miCategory;
    private String commId;
    private Byte   state;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;
    private String userId;
    private Integer count;
}
