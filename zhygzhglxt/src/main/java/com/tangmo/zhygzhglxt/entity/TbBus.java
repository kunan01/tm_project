package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class TbBus {

    /**
     * 公交车主键
     */
    @ApiModelProperty(value = "公交车主键")
    private String busId;

    /**
     * 公交车的唯一标识
     */
    @ApiModelProperty(value = "公交车的唯一标识")
    private String busCode;

    /**
     * 公交车的名称
     */
    @ApiModelProperty(value = "公交车的名称")
    private String busName;

    /**
     * 公交车的车号.
     */
    @ApiModelProperty(value = "公交车的车号")
    private String busNumber;

    /**
     * 公交车首班时间
     */
    @ApiModelProperty(value = "公交车首班时间")
    private String busStartTime;

    /**
     * 公交车末班时间
     */
    @ApiModelProperty(value = "公交车末班时间")
    private String busEndTime;

    /**
     * 公交车最高价
     */
    @ApiModelProperty(value = "公交车最高价")
    private String busPrice;

    /**
     * 大概距离
     */
    @ApiModelProperty(value = "大概距离")
    private BigDecimal distance;

    /**
     * 公交车关联的路线
     */
    @ApiModelProperty(value = "公交车关联的路线")
    private String routeCodes;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private String updateTime;

    /**
     * 是否删除（0正常 1删除）
     */
    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    private String flag;

    /**
     * 线路对象
     */
    @ApiModelProperty(value = "线路对象")
    private TbRoute tbRoute;

    /**
     * 开始地
     */
    @ApiModelProperty(value = "开始地")
    private String startAddress;

    /**
     * 结束地
     */
    @ApiModelProperty(value = "结束地")
    private String endAddress;

    /**
     * 冬季公交车首班时间
     */
    @ApiModelProperty(value = "冬季公交车首班时间")
    private String winterStartTime;

    /**
     * 冬季公交车末班时间
     */
    @ApiModelProperty(value = "冬季公交车末班时间")
    private String winterEndTime;

    @ApiModelProperty(value = "公交类型 busType_0-城市公交 busType_1-城乡公交 busType_2-专线")
    private String busType;

    @ApiModelProperty(value = "公交类型名称")
    private String parmName;

    public TbBus() {

    }

    public TbBus(String busId, String busCode, String busName, String busNumber, String busStartTime, String busEndTime, String busPrice, BigDecimal distance, String routeCodes, String createTime, String updateTime, String busType, String parmName, TbRoute tbRoute) {
        this.busId = busId;
        this.busCode = busCode;
        this.busName = busName;
        this.busNumber = busNumber;
        this.busStartTime = busStartTime;
        this.busEndTime = busEndTime;
        this.busPrice = busPrice;
        this.distance = distance;
        this.routeCodes = routeCodes;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.busType = busType;
        this.parmName = parmName;
        this.tbRoute = tbRoute;
    }

    public String getWinterStartTime() {
        return winterStartTime;
    }

    public void setWinterStartTime(String winterStartTime) {
        this.winterStartTime = winterStartTime;
    }

    public String getWinterEndTime() {
        return winterEndTime;
    }

    public void setWinterEndTime(String winterEndTime) {
        this.winterEndTime = winterEndTime;
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

    public TbRoute getTbRoute() {
        return tbRoute;
    }

    public void setTbRoute(TbRoute tbRoute) {
        this.tbRoute = tbRoute;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId == null ? null : busId.trim();
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode == null ? null : busCode.trim();
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName == null ? null : busName.trim();
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber == null ? null : busNumber.trim();
    }

    public String getBusStartTime() {
        return busStartTime;
    }

    public void setBusStartTime(String busStartTime) {
        this.busStartTime = busStartTime;
    }

    public String getBusEndTime() {
        return busEndTime;
    }

    public void setBusEndTime(String busEndTime) {
        this.busEndTime = busEndTime;
    }

    public String getBusPrice() {
        return busPrice;
    }

    public void setBusPrice(String busPrice) {
        this.busPrice = busPrice;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public String getRouteCodes() {
        return routeCodes;
    }

    public void setRouteCodes(String routeCodes) {
        this.routeCodes = routeCodes == null ? null : routeCodes.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getParmName() {
        return parmName;
    }

    public void setParmName(String parmName) {
        this.parmName = parmName;
    }

    @Override
    public String toString() {
        return "TbBus{" +
                "busId='" + busId + '\'' +
                ", busCode='" + busCode + '\'' +
                ", busName='" + busName + '\'' +
                ", busNumber='" + busNumber + '\'' +
                ", busStartTime='" + busStartTime + '\'' +
                ", busEndTime='" + busEndTime + '\'' +
                ", busPrice=" + busPrice +
                ", distance=" + distance +
                ", routeCodes='" + routeCodes + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", flag='" + flag + '\'' +
                ", tbRoute=" + tbRoute +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                '}';
    }
}