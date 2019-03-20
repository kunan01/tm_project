package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class TbSysRole {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 唯一标识code
     */
    @ApiModelProperty(value = "唯一标识code")
    private String code;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 权限
     */
    @ApiModelProperty(value = "权限")
    private String permissions;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建用户id
     */
    @ApiModelProperty(value = "创建用户id")
    private String createUserid;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 修改用户id
     */
    @ApiModelProperty(value = "修改用户id")
    private String updateUserid;

    /**
     * 是否删除（0正常 1删除）
     */
    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    private String flag;

    /**
     * 所拥有的模块
     */
    @ApiModelProperty(value = "所拥有的模块")
    private List<TbSysModule> moduleList;

    /**
     * 所拥有的权限名称
     */
    @ApiModelProperty(value = "所拥有的权限名称")
    private List<TbSysPermissions> permissionsList;


    public List<TbSysPermissions> getPermissionsList() {
        return permissionsList;
    }

    public void setPermissionsList(List<TbSysPermissions> permissionsList) {
        this.permissionsList = permissionsList;
    }

    public List<TbSysModule> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<TbSysModule> moduleList) {
        this.moduleList = moduleList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions == null ? null : permissions.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid == null ? null : createUserid.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid == null ? null : updateUserid.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}