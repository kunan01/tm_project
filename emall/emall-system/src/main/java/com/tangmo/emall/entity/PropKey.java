package com.tangmo.emall.entity;

import lombok.Data;

import java.util.List;

@Data
public class PropKey {

    //规格属性key主键
    private Integer keyId;

    //属性名称
    private String keyName;

    //店铺id
    private Integer shopId;

    //3级分类id
    private Integer cateGoryId;

    //创建时间
    private String createdTime;

    private List<PropValue> valueList;

    private Integer shopUserId;

    private Integer[] KeyIdList;

}
