package com.tangmo.zhjy.system.modules.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class SysRoleDto{
	
	@ApiModelProperty(value="角色编号",dataType="Integer")
    private Integer id;
    @ApiModelProperty(value="角色名称",dataType="Integer",required=true)
    private String name;
    @Max(value=1,message="freeze参数传递有误")
    @Min(value=0,message="freeze参数传递有误")
    @ApiModelProperty(dataType="Integer",notes="是否冻结角色",example="0：正常 1：冻结")
    private Integer freeze=0;
    @Max(value=1,message="isShow参数传递有误")
    @Min(value=0,message="isShow参数传递有误")
    @ApiModelProperty(dataType="Integer",notes="是否显示数据",example="0:正常 1：隐藏")
    private Integer isShow=0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public Integer getIsShow() {
        return isShow;
    }
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}