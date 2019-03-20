package com.tangmo.yiliao.entity;

import lombok.Data;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 医生视频
 */
@Data
public class DoctorVideo {

    private String dvId;
    private String userId;
    private String dvVideo;
    private String dvImgId;
    private String dvTitle;
    private String dvContent;
    private Integer dvIntegral;
    private Integer visitNumber;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;
    private String dtId;
    private Byte category;
    private Integer commCount;
    private Integer sortingId;

    private String userImgId;
    private String name;
    private String ddHospital;
    private String ddPosition;
    private String ddField;



}
