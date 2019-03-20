package com.tangmo.zhjy.app.modules.bean;

import java.sql.Date;

public class ShopCart {
    private Integer scId;
    private Integer cId;
    private Integer userId;
    private Integer scCount;
    private String cdSize;
    private Date createTime;

    private Commodity commodity;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScCount() {
        return scCount;
    }

    public void setScCount(Integer scCount) {
        this.scCount = scCount;
    }

    public String getCdSize() {
        return cdSize;
    }

    public void setCdSize(String cdSize) {
        this.cdSize = cdSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
