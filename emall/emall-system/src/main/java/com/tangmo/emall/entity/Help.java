package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Help implements Serializable {

    //帮助信息主键id
    private Integer helpId;

    //类目
    private String helpTitle;

    //说明
    private String helpInstructions;

    //级别
    private Integer level;

    //父级id
    private Integer parentId;

    //创建时间
    private String createdTime;


    private List<Help> helpList;

    private Integer[] helpIdList;
}
