package com.tangmo.zhjy.app.modules.bean;


import java.sql.Date;

public class Message {
    private Integer miId;
    private String  title;
    private String  miContent;
    private Byte    miCategory;
    private Byte    state;
    private Date    createTime;
    private Date    updateTime;
    private Integer userId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMiContent() {
        return miContent;
    }

    public void setMiContent(String miContent) {
        this.miContent = miContent;
    }

    public Byte getMiCategory() {
        return miCategory;
    }

    public void setMiCategory(Byte miCategory) {
        this.miCategory = miCategory;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getMiId() {
        return miId;
    }

    public void setMiId(Integer miId) {
        this.miId = miId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "miId='" + miId + '\'' +
                ", title='" + title + '\'' +
                ", miContent='" + miContent + '\'' +
                ", miCategory=" + miCategory +
                ", state=" + state +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
