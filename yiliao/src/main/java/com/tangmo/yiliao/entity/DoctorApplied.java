package com.tangmo.yiliao.entity;


import lombok.Data;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 申请医生信息
 */
@Data
public class DoctorApplied {
    private String daId;
    private String userId;
    private String name;
    private String phone;
    private String daHospital;
    private String dtId;
    private String daPosition;
    private String daDisease;
    private String daAchievement;
    private String daCertificate;
    private String daCertificateName;
    private Byte   daState;
    private String daWhy;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;
    private Byte   daType;
}
