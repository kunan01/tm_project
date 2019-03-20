package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ParamType implements Serializable {

    //参数表id
    private Integer typeId;

    //参数名称
    private String typeName;

    //店铺id
    private Integer shopId;

    //创建时间
    private String createdTime;

    //状态 0正在审核 1通过 2未通过
    private Byte state;

    //是否删除 0未删除 1已删除
    private Byte dataFlag;

    private List<ParamValue> paramValues;

    private Integer[] typeIdList;

}
