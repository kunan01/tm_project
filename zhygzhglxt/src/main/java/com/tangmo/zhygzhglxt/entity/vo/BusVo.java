package com.tangmo.zhygzhglxt.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by chengge on 2018/12/8.
 */
public class BusVo implements Serializable {

    private static final long serialVersionUID = 123567785L;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String ObjectCode;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String VehicleNum;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String GPSTime;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String RcvTime;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String Lon;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String Lat;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String Speed;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String Direct;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String Mileage;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String StatusDes;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String OilNum;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String IsOnline;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String IsAlarm;


    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String Status;

    /**
     * 登录标志
     */
    @ApiModelProperty(value = "登录标志")
    private String loninKey;

    /**
     * 距离
     */
    @ApiModelProperty(value = "距离")
    private String distance;

    /**
     * 到站时间
     */
    @ApiModelProperty(value = "距离")
    private Double siteTime;

    public Double getSiteTime() {
        return siteTime;
    }

    public void setSiteTime(Double siteTime) {
        this.siteTime = siteTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLoninKey() {
        return loninKey;
    }

    public void setLoninKey(String loninKey) {
        this.loninKey = loninKey;
    }

    public String getObjectCode() {
        return ObjectCode;
    }

    public void setObjectCode(String objectCode) {
        ObjectCode = objectCode;
    }

    public String getVehicleNum() {
        return VehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        VehicleNum = vehicleNum;
    }

    public String getGPSTime() {
        return GPSTime;
    }

    public void setGPSTime(String GPSTime) {
        this.GPSTime = GPSTime;
    }

    public String getRcvTime() {
        return RcvTime;
    }

    public void setRcvTime(String rcvTime) {
        RcvTime = rcvTime;
    }

    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
        Lon = lon;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getSpeed() {
        return Speed;
    }

    public void setSpeed(String speed) {
        Speed = speed;
    }

    public String getDirect() {
        return Direct;
    }

    public void setDirect(String direct) {
        Direct = direct;
    }

    public String getMileage() {
        return Mileage;
    }

    public void setMileage(String mileage) {
        Mileage = mileage;
    }

    public String getStatusDes() {
        return StatusDes;
    }

    public void setStatusDes(String statusDes) {
        StatusDes = statusDes;
    }

    public String getOilNum() {
        return OilNum;
    }

    public void setOilNum(String oilNum) {
        OilNum = oilNum;
    }

    public String getIsOnline() {
        return IsOnline;
    }

    public void setIsOnline(String isOnline) {
        IsOnline = isOnline;
    }

    public String getIsAlarm() {
        return IsAlarm;
    }

    public void setIsAlarm(String isAlarm) {
        IsAlarm = isAlarm;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
