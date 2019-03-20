package com.tangmo.yiliao.entity;

import lombok.Data;

import java.util.List;

@Data
public class Permission {

    private String permissionsId;
    private String permissionsName;
    private String moduleId;
    private Byte state;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;
    private List<Module> moduleList;
}
