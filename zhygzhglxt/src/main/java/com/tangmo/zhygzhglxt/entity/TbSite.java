package com.tangmo.zhygzhglxt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbSite {

    @ApiModelProperty(value = "站点的主键")
    private String siteId;

    @ApiModelProperty(value = "站点的唯一标识")
    private String siteCode;

    @ApiModelProperty(value = "站点的名称")
    private String siteName;

    @ApiModelProperty(value = "站点的经度")
    private String siteLo;

    @ApiModelProperty(value = "站点的纬度")
    private String siteLa;

    @JsonIgnore
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @JsonIgnore
    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    private String flag;


    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId == null ? null : siteId.trim();
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSiteLo() {
        return siteLo;
    }

    public void setSiteLo(String siteLo) {
        this.siteLo = siteLo == null ? null : siteLo.trim();
    }

    public String getSiteLa() {
        return siteLa;
    }

    public void setSiteLa(String siteLa) {
        this.siteLa = siteLa == null ? null : siteLa.trim();
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

    @Override
    public String toString() {
        return "TbSite{" +
                "siteId='" + siteId + '\'' +
                ", siteCode='" + siteCode + '\'' +
                ", siteName='" + siteName + '\'' +
                ", siteLo='" + siteLo + '\'' +
                ", siteLa='" + siteLa + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", flag='" + flag + '\'' +
                '}';
    }
}