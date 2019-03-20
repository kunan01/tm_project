package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbListenOrder {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 乘客订单code
     */
    @ApiModelProperty(value = "乘客订单code")
    private String passengerOrderCode;

    /**
     * 司机订单code
     */
    @ApiModelProperty(value = "司机订单code")
    private String driverOrderCode;

    /**
     * 状态（0乘客发起完成订单 1司机通过完成订单 2司机拒绝完成订单）
     */
    @ApiModelProperty(value = "状态（0乘客发起完成订单 1司机通过完成订单 2司机拒绝完成订单）")
    private String state;

    /**
     * 创建时间（判断时效：司机端的操作）
     */
    @ApiModelProperty(value = "创建时间（判断时效：司机端的操作）")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPassengerOrderCode() {
        return passengerOrderCode;
    }

    public void setPassengerOrderCode(String passengerOrderCode) {
        this.passengerOrderCode = passengerOrderCode == null ? null : passengerOrderCode.trim();
    }

    public String getDriverOrderCode() {
        return driverOrderCode;
    }

    public void setDriverOrderCode(String driverOrderCode) {
        this.driverOrderCode = driverOrderCode == null ? null : driverOrderCode.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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
}