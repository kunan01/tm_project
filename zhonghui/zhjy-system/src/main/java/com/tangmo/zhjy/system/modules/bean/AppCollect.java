package com.tangmo.zhjy.system.modules.bean;

public class AppCollect {
    private Integer id;

    private Integer informationid;

    private Integer userId;
    
    public AppCollect() {
	}
    
    public AppCollect(Integer informationid, Integer userId) {
    	this.informationid=informationid;
    	this.userId=userId;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInformationid() {
        return informationid;
    }

    public void setInformationid(Integer informationid) {
        this.informationid = informationid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}