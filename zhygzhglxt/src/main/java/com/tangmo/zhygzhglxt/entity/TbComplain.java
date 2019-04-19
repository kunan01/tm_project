package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbComplain {

    /**
     * 投诉的主键
     */
    @ApiModelProperty(value = "投诉的主键")
    private String complainId;

    /**
     * 投诉的唯一标识
     */
    @ApiModelProperty(value = "投诉的唯一标识")
    private String complainCode;

    /**
     * 投诉的类型
     */
    @ApiModelProperty(value = "投诉的类型")
    private String complainType;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String carNum;

    /**
     * 车辆颜色
     */
    @ApiModelProperty(value = "车辆颜色")
    private String carColor;

    /**
     * 司机姓名
     */
    @ApiModelProperty(value = "司机姓名")
    private String driverName;

    /**
     * 线路描述.
     */
    @ApiModelProperty(value = "线路描述")
    private String routeDesc;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    private String departureTime;

    /**
     * 详细描述
     */
    @ApiModelProperty(value = "详细描述")
    private String detailDesc;

    /**
     * 投诉的状态
     */
    @ApiModelProperty(value = "投诉的状态")
    private String complainState;

    /**
     * 反馈描述.
     */
    @ApiModelProperty(value = "反馈描述")
    private String backDesc;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 是否删除（0正常 1删除）
     */
    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    private String flag;

    /**
     * 用户的唯一标识
     */
    @ApiModelProperty(value = "用户的唯一标识")
    private String userCode;

    /**
     * 投诉类型
     */
    @ApiModelProperty(value = "投诉类型")
    private String type;

    /**
     * 车辆颜色
     */
    @ApiModelProperty(value = "车辆颜色")
    private String color;

    @ApiModelProperty(value = "投诉图片")
    private String imgList;// 投诉图片（多个）

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId == null ? null : complainId.trim();
    }

    public String getComplainCode() {
        return complainCode;
    }

    public void setComplainCode(String complainCode) {
        this.complainCode = complainCode == null ? null : complainCode.trim();
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType == null ? null : complainType.trim();
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum == null ? null : carNum.trim();
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor == null ? null : carColor.trim();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc == null ? null : routeDesc.trim();
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    public String getComplainState() {
        return complainState;
    }

    public void setComplainState(String complainState) {
        this.complainState = complainState == null ? null : complainState.trim();
    }

    public String getBackDesc() {
        return backDesc;
    }

    public void setBackDesc(String backDesc) {
        this.backDesc = backDesc == null ? null : backDesc.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
        this.flag = flag == null ? null : flag.trim();
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }
}