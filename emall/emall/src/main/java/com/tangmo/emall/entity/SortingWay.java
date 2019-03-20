package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SortingWay implements Serializable {

    private static final long serialVersionUID = -3547162651819129435L;

    //排序方式id
    private Integer sortingId;

    //排序标题
    private String title;

    //排序规则定义
    private String sortingRules;

    //创建时间
    private String createdTime;
}
