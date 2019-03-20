package com.tangmo.yiliao.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private String roleId;
    private String roleName;
    private String permissionsId;
    private Byte   state;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;

    private List<Permission> permissionList;
}
