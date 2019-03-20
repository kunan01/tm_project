package com.tangmo.yiliao.entity;

import lombok.Data;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 医生信息
 */
@Data
public class DoctorDetails {

    private String ddId;
    private String userId;
    private String ddHospital;
    private String ddPosition;
    private String ddService;
    private String ddExperience;
    private String ddField;
    private String ddFieldUU;
    private String ddCertificate;
    private String ddCertificateName;
    private String ddPhone;
    private Integer integral;
    private Byte ddState;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;
    private String userImgUrl;
    private String name;

}
