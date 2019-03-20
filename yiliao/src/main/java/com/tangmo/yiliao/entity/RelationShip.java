package com.tangmo.yiliao.entity;

public class RelationShip {

    private String userId;
    private String onUser;
    private String belowUser;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOnUser() {
        return onUser;
    }

    public void setOnUser(String onUser) {
        this.onUser = onUser;
    }

    public String getBelowUser() {
        return belowUser;
    }

    public void setBelowUser(String belowUser) {
        this.belowUser = belowUser;
    }
}
