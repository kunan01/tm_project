package com.tangmo.zhjy.app.modules.bean;

public class AppCommunityInformAppUserBean {
    private Integer id;

    private Integer appUserId;

    private Integer appCommunityInform;

    private Integer isRead=0;
    
    //社区消息通知总人数
    private Integer total;
    //消息已读人数
    private Integer redTotal;

    public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRedTotal() {
		return redTotal;
	}

	public void setRedTotal(Integer redTotal) {
		this.redTotal = redTotal;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public Integer getAppCommunityInform() {
        return appCommunityInform;
    }

    public void setAppCommunityInform(Integer appCommunityInform) {
        this.appCommunityInform = appCommunityInform;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}