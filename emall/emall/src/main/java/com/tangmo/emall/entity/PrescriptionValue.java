package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PrescriptionValue implements Serializable {

    //处方参数value主键
    private Integer pvId;

    //参数key id
    private Integer pkId;

    //标题/度数的取值范围（范围用_表示 逗号后面表示差值）
    private String pvDetailed;

    //介绍/插入的其他选择值
    private String pvIntroduce;

    //价格
    private Double pvPrice;

    //创建时间
    private String createdTime;

    private List<String> stringList;
}
