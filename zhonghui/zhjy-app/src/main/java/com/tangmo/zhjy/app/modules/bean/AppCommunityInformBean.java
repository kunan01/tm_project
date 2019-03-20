package com.tangmo.zhjy.app.modules.bean;

import java.util.Date;

public class AppCommunityInformBean {
    private Integer id;

    private String title;

    private Date publishData;

    private String cover;

    private Integer isChecked;

    private Integer isShow=0;

    private Integer communityId;

    private String content;
    
    private Date createTime;
    
    //社区消息通知总人数
    private Integer total;

    //消息已读人数
    private Integer redTotal;

    //社区名称
    private String communityName;

    private Byte isRead;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public AppCommunityInformBean() {
		// TODO Auto-generated constructor stub
	}
    
    
    public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public Integer getRedTotal() {
		return redTotal;
	}



	public void setRedTotal(Integer redTotal) {
		this.redTotal = redTotal;
	}



	public AppCommunityInformBean(Integer id,String title,String cover,Integer isChecked,Integer isShow,Integer communityId,String content) {
    	this.id=id;
    	this.title=title;
    	this.cover=cover;
    	this.isChecked=isChecked;
    	this.isShow=isShow;
    	this.communityId=communityId;
    	this.content=content;
	}
    public AppCommunityInformBean(String title,String cover,Integer isChecked,Integer communityId,String content) {
    	this.title=title;
    	this.cover=cover;
    	this.isChecked=isChecked;
    	this.communityId=communityId;
    	this.content=content;
	}
    
    

    public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getReadTotal() {
		return redTotal;
	}

	public void setReadTotal(Integer redTotal) {
		this.redTotal = redTotal;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPublishData() {
        return publishData;
    }

    public void setPublishData(Date publishData) {
        this.publishData = publishData;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }
}