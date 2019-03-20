package com.tangmo.zhjy.app.modules.bean;

public class AppBrowsingHistoryBean {
    private Integer id;

    private Integer userId;

    private Integer appInformid;
    
    public AppBrowsingHistoryBean() {
	}
    
    public AppBrowsingHistoryBean(Integer userId,Integer appInformid) {
    	this.userId=userId;
    	this.appInformid=appInformid;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getappInformid() {
        return appInformid;
    }

    public void setappInformid(Integer appInformid) {
        this.appInformid = appInformid;
    }
}