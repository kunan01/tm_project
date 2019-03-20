package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class TbFeedBack implements Serializable {

    private static final long serialVersionUID = 66L;

    @ApiModelProperty(value = "反馈id")
    private Integer fbId;

    @ApiModelProperty(value = "反馈内容")
    private String fbContent;

    @ApiModelProperty(value = "反馈人电话")
    private String fbPhone;

    @ApiModelProperty(value = "反馈人姓名")
    private String userName;

    @ApiModelProperty(value = "提交反馈时间")
    private Date fbCreateTime;

    @ApiModelProperty(value = "提交反馈时间")
    private Date fbUpdateTime;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "反馈状态")
    private Byte fbState;


    public Date getFbUpdateTime() {
        return fbUpdateTime;
    }

    public void setFbUpdateTime(Date fbUpdateTime) {
        this.fbUpdateTime = fbUpdateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getFbId() {
        return fbId;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
    }

    public String getFbContent() {
        return fbContent;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent == null ? null : fbContent.trim();
    }

    public String getFbPhone() {
        return fbPhone;
    }

    public void setFbPhone(String fbPhone) {
        this.fbPhone = fbPhone == null ? null : fbPhone.trim();
    }

    public Date getFbCreateTime() {
        return fbCreateTime;
    }

    public void setFbCreateTime(Date fbCreateTime) {
        this.fbCreateTime = fbCreateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Byte getFbState() {
        return fbState;
    }

    public void setFbState(Byte fbState) {
        this.fbState = fbState;
    }
}