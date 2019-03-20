package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbRegularBus {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String regularBusId;

    /**
     * 班车的唯一标识
     */
    @ApiModelProperty(value = "班车的唯一标识")
    private String regularBusCode;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String regularBusNumber;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String regularBusModel;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String regularBusDesc;

    /**
     * 人数
     */
    @ApiModelProperty(value = "人数")
    private Integer peopleNumber;

    /**
     * 班车路线
     */
    @ApiModelProperty(value = "班车路线")
    private String regularBusRoute;

    /**
     * 司机姓名
     */
    @ApiModelProperty(value = "司机姓名")
    private String driverName;

    /**
     * 司机电话
     */
    @ApiModelProperty(value = "司机电话")
    private String driverPhone;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String state;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String regularBusType;

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
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private String flag;

    public String getRegularBusId() {
        return regularBusId;
    }

    public void setRegularBusId(String regularBusId) {
        this.regularBusId = regularBusId == null ? null : regularBusId.trim();
    }

    public String getRegularBusCode() {
        return regularBusCode;
    }

    public void setRegularBusCode(String regularBusCode) {
        this.regularBusCode = regularBusCode == null ? null : regularBusCode.trim();
    }

    public String getRegularBusNumber() {
        return regularBusNumber;
    }

    public void setRegularBusNumber(String regularBusNumber) {
        this.regularBusNumber = regularBusNumber == null ? null : regularBusNumber.trim();
    }

    public String getRegularBusModel() {
        return regularBusModel;
    }

    public void setRegularBusModel(String regularBusModel) {
        this.regularBusModel = regularBusModel == null ? null : regularBusModel.trim();
    }

    public String getRegularBusDesc() {
        return regularBusDesc;
    }

    public void setRegularBusDesc(String regularBusDesc) {
        this.regularBusDesc = regularBusDesc == null ? null : regularBusDesc.trim();
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public String getRegularBusRoute() {
        return regularBusRoute;
    }

    public void setRegularBusRoute(String regularBusRoute) {
        this.regularBusRoute = regularBusRoute == null ? null : regularBusRoute.trim();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone == null ? null : driverPhone.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRegularBusType() {
        return regularBusType;
    }

    public void setRegularBusType(String regularBusType) {
        this.regularBusType = regularBusType == null ? null : regularBusType.trim();
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
}