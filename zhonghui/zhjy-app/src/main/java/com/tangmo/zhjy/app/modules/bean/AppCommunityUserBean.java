package com.tangmo.zhjy.app.modules.bean;

public class AppCommunityUserBean {
    private Integer id;

    private Integer communityId;

    private Integer userId;
    
    public AppCommunityUserBean() {
		// TODO Auto-generated constructor stub
	}
    
    public AppCommunityUserBean(Integer userId,Integer communityId) {
    	this.communityId=communityId;
    	this.userId=userId;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}