package com.tangmo.zhjy.system.modules.bean;

import java.util.Date;
import java.util.List;

public class SysInformation {

    private Integer id;

    private String title;

    private Date publishData;

    private Integer freeze=0;

    private Integer isShow=0;

    private Integer sysUserid;

	private Integer appUserid;

    private String content;
    
    private SysUserBean sysUser;
    
    private Integer isChecked;
    
    private Integer classifyId;
    
    private boolean isAttention=false; //是否关注
    
    private Date createTime;

    private String uname;

	private String classTwoName;

	public String getClassTwoName() {
		return classTwoName;
	}

	public void setClassTwoName(String classTwoName) {
		this.classTwoName = classTwoName;
	}

	public String getUname() {
		return uname;
	}

	public Integer getAppUserid() {
		return appUserid;
	}

	public void setAppUserid(Integer appUserid) {
		this.appUserid = appUserid;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	private List<SysInformationImages> sysInformationImages;
    
    public Date getCreateTime() {
		return createTime;
	}
    
    

	public boolean isAttention() {
		return isAttention;
	}



	public void setAttention(boolean isAttention) {
		this.isAttention = isAttention;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<SysInformationImages> getSysInformationImages() {
		return sysInformationImages;
	}

	public void setSysInformationImages(List<SysInformationImages> sysInformationImages) {
		this.sysInformationImages = sysInformationImages;
	}

	public Integer getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	/**
     * 二级菜单外键
     */
    private Integer twoClassifyId;
    
    public Integer getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}

	public Integer getTwoClassifyId() {
		return twoClassifyId;
	}

	public void setTwoClassifyId(Integer twoClassifyId) {
		this.twoClassifyId = twoClassifyId;
	}

	public SysUserBean getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserBean sysUser) {
		this.sysUser = sysUser;
	}

	public SysInformation() {
		// TODO Auto-generated constructor stub
	}
    
    public SysInformation(Integer twoClassifyId,String title,Integer freeze,Integer sysUserid,String content) {
    	this.title=title;
    	this.freeze=freeze;
    	this.sysUserid=sysUserid;
    	this.content=content;
    	this.twoClassifyId=twoClassifyId;
	}
    public SysInformation(Integer id,String title,Integer freeze,Integer sysUserid,String content,Integer isShow,Integer isChecked) {
    	this.id=id;
    	this.title=title;
    	this.freeze=freeze;
    	this.sysUserid=sysUserid;
    	this.content=content;
    	this.isShow=isShow;
    	this.isChecked=isChecked;
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

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getSysUserid() {
        return sysUserid;
    }

    public void setSysUserid(Integer sysUserid) {
        this.sysUserid = sysUserid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	@Override
	public String toString() {
		return "AppInformation [id=" + id + ", title=" + title + ", publishData=" + publishData + ", freeze=" + freeze
				+ ", isShow=" + isShow + ", sysUserid=" + sysUserid + ", content=" + content + ", sysUser=" + sysUser
				+ ", isChecked=" + isChecked + ", classifyId=" + classifyId + ", createTime=" + createTime
				+ ", SysInformationImages=" + sysInformationImages + ", twoClassifyId=" + twoClassifyId + "]";
	}
    
    
}