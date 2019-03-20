package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SortingWay  implements Serializable {

    //排序方式id
    private Integer sortingId;

    //排序标题
    private String title;

    //排序规则sql定义
    private String sortingRules;

    //创建时间
    private String createdTime;

    //排序类别
    private Byte sortingType;

    private Integer[] sortingIdList;
}
