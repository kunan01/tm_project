package com.tangmo.zhjy.system.modules.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class SysCommunityInformDto {
    private Integer id;
    @ApiModelProperty(value="标题")
    private String title;
    @ApiModelProperty(value="封面图片")
    private String cover;
    @ApiModelProperty(value="是否审核通过：0：未通过 1：待审核 2：通过 3：驳回")

    private Integer isChecked;

    @ApiModelProperty(value="是否删除：0正常，1删除",hidden=true)
    private Integer isShow;

    @ApiModelProperty(value="发布社区编号")
    private Integer communityId;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}