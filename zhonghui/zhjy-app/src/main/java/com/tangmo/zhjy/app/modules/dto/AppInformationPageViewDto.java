package com.tangmo.zhjy.app.modules.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class AppInformationPageViewDto {
    private Integer id;
    
    @ApiModelProperty(value="文章记录编号",required=true)
    @NotNull
    private Integer appInfoId;
    @ApiModelProperty(value="访问用户编号",required=true)
    @NotNull
    private Integer appUserid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(Integer appInfoId) {
        this.appInfoId = appInfoId;
    }

    public Integer getAppUserid() {
        return appUserid;
    }

    public void setAppUserid(Integer appUserid) {
        this.appUserid = appUserid;
    }
}