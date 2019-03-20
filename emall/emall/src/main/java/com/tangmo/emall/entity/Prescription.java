package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Prescription implements Serializable {

    //用户处方信息id
    private Integer prescriptionId;

    //处方名
    private String prescriptionName;

    //镜片度数
    private String rightSphere;

    //散光度数
    private String rightCylinder;

    //散光轴度
    private String rightAxis;

    //需要增加的度数
    private String rightAdd;

    //远用瞳距
    private String rightPd;

    //镜片度数
    private String leftSphere;

    //散光度数
    private String leftCylinder;

    //散光轴度
    private String leftAxis;

    //需要增加的度数
    private String leftAdd;

    //远用瞳距
    private String leftPd;

    //近用瞳距
    private String nearPd;

    //用户id
    private Integer userId;

    //附加信息
    private String comment;

    //创建时间
    private String createdTime;

    //是否删除 0否 1是
    private Byte dataFlag;
















}
