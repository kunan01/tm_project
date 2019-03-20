package com.tangmo.zhjy.app.modules.dto;

import java.util.Arrays;

public class AppAddOrderDto {

    private String scId;
    private String userId;
    private String uaId;
    private String cdId;
    private String goCount;
    private String expressFee;
    private String[] cdSize;

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUaId() {
        return uaId;
    }

    public void setUaId(String uaId) {
        this.uaId = uaId;
    }

    public String getCdId() {
        return cdId;
    }

    public void setCdId(String cdId) {
        this.cdId = cdId;
    }

    public String getGoCount() {
        return goCount;
    }

    public void setGoCount(String goCount) {
        this.goCount = goCount;
    }

    public String getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(String expressFee) {
        this.expressFee = expressFee;
    }

    public String[] getCdSize() {
        return cdSize;
    }

    public void setCdSize(String[] cdSize) {
        this.cdSize = cdSize;
    }

    @Override
    public String toString() {
        return "AppAddOrderDto{" +
                "scId='" + scId + '\'' +
                ", userId='" + userId + '\'' +
                ", uaId='" + uaId + '\'' +
                ", cdId='" + cdId + '\'' +
                ", goCount='" + goCount + '\'' +
                ", expressFee='" + expressFee + '\'' +
                ", cdSize=" + Arrays.toString(cdSize) +
                '}';
    }
}
