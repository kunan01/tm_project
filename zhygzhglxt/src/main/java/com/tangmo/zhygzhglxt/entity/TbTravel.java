package com.tangmo.zhygzhglxt.entity;

import java.util.Date;

public class TbTravel {

    /**
     *
     */
    private String travelId;

    /**
     *
     */
    private String travelCode;

    /**
     *
     */
    private String busCode;

    /**
     *
     */
    private String busNumber;

    /**
     *
     */
    private String busLo;

    /**
     *
     */
    private String busLa;

    /**
     *
     */
    private String travelState;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private String flag;


    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId == null ? null : travelId.trim();
    }

    public String getTravelCode() {
        return travelCode;
    }

    public void setTravelCode(String travelCode) {
        this.travelCode = travelCode == null ? null : travelCode.trim();
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode == null ? null : busCode.trim();
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber == null ? null : busNumber.trim();
    }

    public String getBusLo() {
        return busLo;
    }

    public void setBusLo(String busLo) {
        this.busLo = busLo == null ? null : busLo.trim();
    }

    public String getBusLa() {
        return busLa;
    }

    public void setBusLa(String busLa) {
        this.busLa = busLa == null ? null : busLa.trim();
    }

    public String getTravelState() {
        return travelState;
    }

    public void setTravelState(String travelState) {
        this.travelState = travelState == null ? null : travelState.trim();
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