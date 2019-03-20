package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 车主订单类目
 */
public class TbDriverOrder {

    /**
     * 司机订单的主键.
     */
    @ApiModelProperty(value = "司机订单的主键")
    private String driverOrderId;

    /**
     * 司机订单的唯一标识
     */
    @ApiModelProperty(value = "司机订单的唯一标识")
    private String driverOrderCode;

    /**
     * 司机的唯一标识code
     */
    @ApiModelProperty(value = "司机的唯一标识code")
    private String driverId;

    /**
     * 乘客订单的唯一标识
     */
    @ApiModelProperty(value = "乘客订单的唯一标识")
    private String passengerOrderCode;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 意见
     */
    @ApiModelProperty(value = "意见")
    private String opinion;

    /**
     * 好评度.
     */
    @ApiModelProperty(value = "好评度")
    private String rating;

    /**
     * 司机订单的状态
     */
    @ApiModelProperty(value = "司机订单的状态")
    private String driverOrderState;

    /**
     * 是否删除（0正常 1删除）
     */
    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    private String flag;

    //车主类型
    @ApiModelProperty(value = "车主类型")
    private String tbParmId;

    //开始地
    @ApiModelProperty(value = "开始地")
    private String startAddress;

    //结束地
    @ApiModelProperty(value = "结束地")
    private String endAddress;

    //货物名称
    @ApiModelProperty(value = "货物名称")
    private String goodsName;

    //结货物详情
    @ApiModelProperty(value = "结货物详情")
    private String goodsDetail;

    //预约时间
    @ApiModelProperty(value = "预约时间")
    private String appointmentTime;

    /**
     * 评价状态（0未评价 1已评价）
     */
    @ApiModelProperty(value = "评价状态（0未评价 1已评价）")
    private String ratingState;

    /**
     * 评价时间
     */
    @ApiModelProperty(value = "评价时间")
    private Date ratingTime;

    public Date getRatingTime() {
        return ratingTime;
    }

    public void setRatingTime(Date ratingTime) {
        this.ratingTime = ratingTime;
    }

    public String getRatingState() {
        return ratingState;
    }

    public void setRatingState(String ratingState) {
        this.ratingState = ratingState;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public String getTbParmId() {
        return tbParmId;
    }

    public void setTbParmId(String tbParmId) {
        this.tbParmId = tbParmId;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getDriverOrderId() {
        return driverOrderId;
    }


    public void setDriverOrderId(String driverOrderId) {
        this.driverOrderId = driverOrderId == null ? null : driverOrderId.trim();
    }

    public String getDriverOrderCode() {
        return driverOrderCode;
    }


    public void setDriverOrderCode(String driverOrderCode) {
        this.driverOrderCode = driverOrderCode == null ? null : driverOrderCode.trim();
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId == null ? null : driverId.trim();
    }

    public String getPassengerOrderCode() {
        return passengerOrderCode;
    }

    public void setPassengerOrderCode(String passengerOrderCode) {
        this.passengerOrderCode = passengerOrderCode == null ? null : passengerOrderCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

    public String getDriverOrderState() {
        return driverOrderState;
    }

    public void setDriverOrderState(String driverOrderState) {
        this.driverOrderState = driverOrderState == null ? null : driverOrderState.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}