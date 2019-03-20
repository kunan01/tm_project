package com.tangmo.zhygzhglxt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class TbRoute {

    /**
     * 路线的主键
     */
    @ApiModelProperty(value = "路线的主键")
    private String routeId;

    /**
     * 路线的唯一标识code
     */
    @ApiModelProperty(value = "路线的唯一标识code")
    private String routeCode;

    /**
     * 路线的名称
     */
    @ApiModelProperty(value = "路线的名称")
    private String routeName;

    /**
     * 路线的开始地
     */
    @ApiModelProperty(value = "路线的开始地")
    private String routeNameStart;

    /**
     * 路线的结束地
     */
    @ApiModelProperty(value = "路线的结束地")
    private String routeNameEnd;

    /**
     * 路线的状态
     */
    @ApiModelProperty(value = "路线的状态")
    private String routeState;

    /**
     * 站点的唯一标识
     */
    @ApiModelProperty(value = "站点的唯一标识")
    private String siteCodes;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonIgnore
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonIgnore
    private Date updateTime;

    /**
     * 是否删除（0正常 1删除）
     */
    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    @JsonIgnore
    private String flag;

    /**
     * 站点集合
     */
    @ApiModelProperty(value = "站点集合")
    private List<TbSite> tbSiteList;


    public List<TbSite> getTbSiteList() {
        return tbSiteList;
    }

    public void setTbSiteList(List<TbSite> tbSiteList) {
        this.tbSiteList = tbSiteList;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId == null ? null : routeId.trim();
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode == null ? null : routeCode.trim();
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public String getRouteState() {
        return routeState;
    }

    public void setRouteState(String routeState) {
        this.routeState = routeState == null ? null : routeState.trim();
    }

    public String getSiteCodes() {
        return siteCodes;
    }

    public void setSiteCodes(String siteCodes) {
        this.siteCodes = siteCodes == null ? null : siteCodes.trim();
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

    public String getRouteNameStart() {
        return routeNameStart;
    }

    public void setRouteNameStart(String routeNameStart) {
        this.routeNameStart = routeNameStart;
    }

    public String getRouteNameEnd() {
        return routeNameEnd;
    }

    public void setRouteNameEnd(String routeNameEnd) {
        this.routeNameEnd = routeNameEnd;
    }

    @Override
    public String toString() {
        return "TbRoute{" +
                "routeId='" + routeId + '\'' +
                ", routeCode='" + routeCode + '\'' +
                ", routeName='" + routeName + '\'' +
                ", routeState='" + routeState + '\'' +
                ", siteCodes='" + siteCodes + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", flag='" + flag + '\'' +
                ", tbSiteList=" + tbSiteList +
                '}';
    }
}