package com.tangmo.zhygzhglxt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * 班车类目
 */
@Data
@ToString
public class TbShuttleBus {

    @ApiModelProperty(value = "班车表主键")
    private String shuttleBusId;//班车表主键

    @ApiModelProperty(value = "班车表唯一标识")
    private String shuttleBusCode;//班车表唯一标识

    @ApiModelProperty(value = "起始地址")
    private String startAddress;//起始地址

    @ApiModelProperty(value = "结束地址")
    private String endAddress;//结束地址

    @ApiModelProperty(value = "发车时间")
    private String interval;//发车时间

    @ApiModelProperty(value = "大概多远")
    private BigDecimal km;//大概多远

    @ApiModelProperty(value = "总路程时间")
    private String totalTime;//总路程时间

    @ApiModelProperty(value = "班车状态（0未发 1已发 2到达）")
    private String state;//班车状态（0未发 1已发 2到达）

    @ApiModelProperty(value = "创建时间")
    private String createTime;//创建时间

    @ApiModelProperty(value = "修改时间")
    private String updateTime;//修改时间

    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    @JsonIgnore
    private String flag;//是否删除（0正常 1删除）

    @ApiModelProperty(value = "车的类型")
    private String parmId;//车的类型

    @ApiModelProperty(value = "途径站点(1站，2站，3站)")
    private String address;//途径站点(1站，2站，3站)

    @ApiModelProperty(value = "区域id")
    private String areaId;//区域id

    @ApiModelProperty(value = "票价")
    private BigDecimal fare;//票价

    @ApiModelProperty(value = "到点时间")
    private String intervalTime;//到点时间

    @ApiModelProperty(value = "该线路的所有车牌号")
    private List<String> busNumbers;//该线路的所有车牌号

    /**
     * 开始经度
     */
    @ApiModelProperty(value = "开始经度")
    private String startLongitude;

    /**
     * 开始纬度
     */
    @ApiModelProperty(value = "开始纬度")
    private String startLatitude;

    /**
     * 结束经度
     */
    @ApiModelProperty(value = "结束经度")
    private String endLongitude;

    /**
     * 结束纬度
     */
    @ApiModelProperty(value = "结束纬度")
    private String endLatitude;


    //途径站数组
    @ApiModelProperty(value = "途径站数组")
    private String[] names;

    public List<String> getBusNumbers() {
        return busNumbers;
    }

    public void setBusNumbers(List<String> busNumbers) {
        this.busNumbers = busNumbers;
    }

    public String getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(String startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(String startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(String endLongitude) {
        this.endLongitude = endLongitude;
    }

    public String getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(String endLatitude) {
        this.endLatitude = endLatitude;
    }

    public String getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(String intervalTime) {
        this.intervalTime = intervalTime;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    public String getShuttleBusId() {
        return shuttleBusId;
    }

    public void setShuttleBusId(String shuttleBusId) {
        this.shuttleBusId = shuttleBusId;
    }

    public String getShuttleBusCode() {
        return shuttleBusCode;
    }

    public void setShuttleBusCode(String shuttleBusCode) {
        this.shuttleBusCode = shuttleBusCode;
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

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public BigDecimal getKm() {
        return km;
    }

    public void setKm(BigDecimal km) {
        this.km = km;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        this.flag = flag;
    }

    public String getParmId() {
        return parmId;
    }

    public void setParmId(String parmId) {
        this.parmId = parmId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}
