package com.tangmo.zhygzhglxt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbRouteDetail {

    @JsonIgnore
    @ApiModelProperty(value = "路线明细的主键")
    private String id;

    @ApiModelProperty(value = "路线明细的唯一标识")
    private String routeDetailCode;

    @ApiModelProperty(value = "路线的唯一标识code")
    private String routeCode;

    @ApiModelProperty(value = "经度")
    private String lo;

    @ApiModelProperty(value = "维度")
    private String la;

    @JsonIgnore
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @JsonIgnore
    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    private String flag;

    @JsonIgnore
    @ApiModelProperty(value = "状态（备用列）")
    private String state;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "站点名称")
    private String address;

    @ApiModelProperty(value = "方向")
    private String direct;

    @ApiModelProperty(value = "速度")
    private String speed;

    @ApiModelProperty(value = "定位时间")
    private String gpsTime;

    @ApiModelProperty(value = "间隔")
    private String tm;

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRouteDetailCode() {
        return routeDetailCode;
    }

    public void setRouteDetailCode(String routeDetailCode) {
        this.routeDetailCode = routeDetailCode == null ? null : routeDetailCode.trim();
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode == null ? null : routeCode.trim();
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo == null ? null : lo.trim();
    }

    public String getLa() {
        return la;
    }

    public void setLa(String la) {
        this.la = la == null ? null : la.trim();
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}