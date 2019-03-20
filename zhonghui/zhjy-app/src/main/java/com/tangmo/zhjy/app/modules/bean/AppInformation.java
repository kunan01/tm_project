package com.tangmo.zhjy.app.modules.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;


public class AppInformation {
	private Integer id;

	private String title;

	private Date publishData;

	private Integer freeze=0;

	private Integer isShow=0;

	private Integer sysUserid;

	private Integer appUserid;

	public Integer getAppUserid() {
		return appUserid;
	}

	public void setAppUserid(Integer appUserid) {
		this.appUserid = appUserid;
	}

	private String content;

	private Integer isChecked;

	private Integer classifyId;

	private boolean isAttention=false; //是否关注

	private Date createTime;

	private String u_userName;

	private String classTwoName;

	private Integer visitCount;

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    private List<String> imgList;


    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public String getClassTwoName() {
		return classTwoName;
	}

	public void setClassTwoName(String classTwoName) {
		this.classTwoName = classTwoName;
	}

	public String getU_userName() {
		return u_userName;
	}

	public void setU_userName(String u_userName) {
		this.u_userName = u_userName;
	}

	private List<AppInformationImages> appInformationImages;

	public AppInformation() {
	}

	public AppInformation(Integer twoClassifyId,String title,Integer freeze,Integer sysUserid,String content) {
		this.title=title;
		this.freeze=freeze;
		this.sysUserid=sysUserid;
		this.content=content;
		this.twoClassifyId=twoClassifyId;
	}

	public AppInformation(Integer twoClassifyId,String title,Integer freeze,Integer appUserid,String content,Integer type) {
		this.title=title;
		this.freeze=freeze;
		this.appUserid=appUserid;
		this.content=content;
		this.twoClassifyId=twoClassifyId;
	}

	public AppInformation(Integer id,String title,Integer freeze,Integer sysUserid,String content,Integer isShow,Integer isChecked) {
		this.id=id;
		this.title=title;
		this.freeze=freeze;
		this.sysUserid=sysUserid;
		this.content=content;
		this.isShow=isShow;
		this.isChecked=isChecked;
	}


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

	public List<AppInformationImages> getAppInformationImages() {
		return appInformationImages;
	}

	public void setAppInformationImages(List<AppInformationImages> appInformationImages) {
		this.appInformationImages = appInformationImages;
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

    private String twoClassifyName;

    public String getTwoClassifyName() {
        return twoClassifyName;
    }

    public void setTwoClassifyName(String twoClassifyName) {
        this.twoClassifyName = twoClassifyName;
    }

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
				+ ", isShow=" + isShow + ", sysUserid=" + sysUserid + ", content=" + content + ", isChecked=" + isChecked + ", classifyId=" + classifyId + ", createTime=" + createTime
				+ ", appInformationImages=" + appInformationImages + ", twoClassifyId=" + twoClassifyId + "]";
	}


}