package com.tangmo.yiliao.entity;

import lombok.Data;

import java.util.List;

@Data
public class Module {
    private String moduleId;
    private String moduleName;
    private Integer moduleLevel;
    private String fatherId;
    private String path;
    private Byte state;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;
    private List<Module> ModuleList;
}
