package com.tangmo.yiliao.entity;


public class Dialogue {

    private String dlId;
    private String originatorId;
    private String peopleId;
    private Byte dType;
    private String dContent;
    private Byte dState;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;
    private String userImgUrl;
    private String name;
    private Integer fileLength;

    public Integer getFileLength() {
        return fileLength;
    }

    public void setFileLength(Integer fileLength) {
        this.fileLength = fileLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDlId() {
        return dlId;
    }

    public void setDlId(String dlId) {
        this.dlId = dlId;
    }

    public String getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(String originatorId) {
        this.originatorId = originatorId;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getdContent() {
        return dContent;
    }

    public void setdContent(String dContent) {
        this.dContent = dContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Byte getdType() {
        return dType;
    }

    public void setdType(Byte dType) {
        this.dType = dType;
    }

    public Byte getdState() {
        return dState;
    }

    public void setdState(Byte dState) {
        this.dState = dState;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    @Override
    public String toString() {
        return "Dialogue{" +
                "dlId='" + dlId + '\'' +
                ", originatorId='" + originatorId + '\'' +
                ", peopleId='" + peopleId + '\'' +
                ", dType=" + dType +
                ", dContent='" + dContent + '\'' +
                ", dState=" + dState +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                ", userImgUrl='" + userImgUrl + '\'' +
                ", name='" + name + '\'' +
                ", fileLength=" + fileLength +
                '}';
    }
}
