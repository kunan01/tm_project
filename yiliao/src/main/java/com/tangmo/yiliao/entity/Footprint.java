package com.tangmo.yiliao.entity;

import lombok.Data;

@Data
public class Footprint {

    private String fpId;
    private String userId;
    private Byte fpType;
    private String typeId;
    private Byte state;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;

}
