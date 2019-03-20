package com.tangmo.zhygzhglxt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chengge on 2019/1/28.
 */
public class TbOrderRoute implements Serializable {

    private static final long serialVersionUID = 544446654266753546L;

    @JsonIgnore
    @ApiModelProperty(value = "订单路线id")
    private String orderRouteId;

    @JsonIgnore
    @ApiModelProperty(value = "司机订单的唯一标识")
    private String driverOrderCode;

    @ApiModelProperty(value = "纬度")
    private String la;

    @ApiModelProperty(value = "经度")
    private String lo;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @JsonIgnore
    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    private String flag;

    @ApiModelProperty(value = "时间戳")
    private Integer tm;

    public Integer getTm() {
        return tm;
    }

    public void setTm(Integer tm) {
        this.tm = tm;
    }

    public String getOrderRouteId() {
        return orderRouteId;
    }

    public void setOrderRouteId(String orderRouteId) {
        this.orderRouteId = orderRouteId == null ? null : orderRouteId.trim();
    }

    public String getDriverOrderCode() {
        return driverOrderCode;
    }

    public void setDriverOrderCode(String driverOrderCode) {
        this.driverOrderCode = driverOrderCode == null ? null : driverOrderCode.trim();
    }

    public String getLa() {
        return la;
    }

    public void setLa(String la) {
        this.la = la == null ? null : la.trim();
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo == null ? null : lo.trim();
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
