package com.tangmo.zhjy.app.modules.bean;

public class AppInformationPageView {
    private Integer id;

    private Integer appInfoId;

    private Integer appUserid;
    
    public AppInformationPageView() {
		// TODO Auto-generated constructor stub
	}
    
    public AppInformationPageView(Integer appInfoId,Integer appUserid) {
    	this.appInfoId=appInfoId;
    	this.appUserid=appUserid;
  	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(Integer appInfoId) {
        this.appInfoId = appInfoId;
    }

    public Integer getAppUserid() {
        return appUserid;
    }

    public void setAppUserid(Integer appUserid) {
        this.appUserid = appUserid;
    }
}