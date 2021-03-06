package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

//商品分类表
@Data
public class CateGory implements Serializable {


    private static final long serialVersionUID = 2550285863870926627L;
    //主键id
    private Integer categoryId;

    //分类名称
    private String categoryName;

    //父级id
    private Integer parentId;

    //当前等级
    private Integer categoryLevel;

    //创建时间
    private String createdTime;

    //系统用户id
    private String sysUserId;

    private List<CateGory> cateGoryList;
}
