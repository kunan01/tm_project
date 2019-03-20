package com.tangmo.emall.entity;

import lombok.Data;

@Data
public class PropValue {

    //规格属性value主键
    private Integer valueId;

    //key主键
    private Integer keyId;

    //属性值
    private String propValue;

    //创建时间
    private String createdTime;

    private Integer shopUserId;

    private Integer[] valueIdList;

}
