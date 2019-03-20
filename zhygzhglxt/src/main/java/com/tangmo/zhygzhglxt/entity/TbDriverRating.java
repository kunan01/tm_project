package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbDriverRating {

    /**
     * 司机好评度的主键.
     */
    @ApiModelProperty(value = "司机好评度的主键")
    private String driverRatingId;

    /**
     * 司机好评度的唯一标识
     */
    @ApiModelProperty(value = "司机好评度的唯一标识")
    private String driverRatingCode;

    /**
     * 用户的唯一标识
     */
    @ApiModelProperty(value = "用户的唯一标识")
    private String userCode;

    /**
     * 好评度
     */
    @ApiModelProperty(value = "好评度")
    private Integer rating;

    /**
     * 创建时间.
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private String flag;


    public String getDriverRatingId() {
        return driverRatingId;
    }

    public void setDriverRatingId(String driverRatingId) {
        this.driverRatingId = driverRatingId == null ? null : driverRatingId.trim();
    }

    public String getDriverRatingCode() {
        return driverRatingCode;
    }

    public void setDriverRatingCode(String driverRatingCode) {
        this.driverRatingCode = driverRatingCode == null ? null : driverRatingCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}