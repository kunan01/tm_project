package com.tangmo.yiliao.entity;

import lombok.Data;

@Data
public class SystemTheArticle {

    private String saId;
    private String dtId;
    private String saImgId;
    private String saTitle;
    private String saContent;
    private Integer visitNumber;
    private Byte state;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;
    private String userId;
    private Integer saIntegral;
    private Integer commCount;
    private String userName;

    private String dtName;

}
