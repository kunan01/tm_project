package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PrescriptionKey implements Serializable {

    //处方参数key表
    private Integer pkId;

    //key说明
    private String pkName;

    //创建时间
    private String createdTime;

    //状态 0处方使用信息 1处方度数信息
    private Byte state;

    private List<PrescriptionValue> valueList;
}
