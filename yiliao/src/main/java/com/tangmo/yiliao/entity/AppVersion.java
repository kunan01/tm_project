package com.tangmo.yiliao.entity;

import lombok.Data;

@Data
public class AppVersion {
    private String vId;
    private String vNumber;
    private String vContent;
    private Integer vType;
    private String onlineTime;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;

    public String getvNumber() {
        return vNumber;
    }

    public void setvNumber(String vNumber) {
        this.vNumber = vNumber;
    }

    public String getvContent() {
        return vContent;
    }

    public void setvContent(String vContent) {
        this.vContent = vContent;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
}
