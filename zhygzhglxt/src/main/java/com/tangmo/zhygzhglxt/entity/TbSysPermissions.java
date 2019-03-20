package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class TbSysPermissions {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 唯一标识
     */
    @ApiModelProperty(value = "唯一标识")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 模块
     */
    @ApiModelProperty(value = "模块")
    private String module;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建用户的id
     */
    @ApiModelProperty(value = "创建用户的id")
    private String createUserid;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 修改用户的id
     */
    @ApiModelProperty(value = "修改用户的id")
    private String updateUserid;

    /**
     * 是否删除（0正常 1删除）
     */
    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    private String flag;

    /**
     * 多个模块对象
     */
    @ApiModelProperty(value = "多个模块对象")
    private List<TbSysModule> modules;

    public List<TbSysModule> getModules() {
        return modules;
    }

    public void setModules(List<TbSysModule> modules) {
        this.modules = modules;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
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