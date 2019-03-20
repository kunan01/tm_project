package com.tangmo.zhjy.app.modules.bean;

import java.util.Date;

public class AppSlideBean {

	private Integer id;
	/**
	 * 轮播图标题
	 */
	private String title;
	/**
	 * 轮播图访问路径
	 */
	private String url;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 是否显示
	 */
	private Integer isShow;
	/**
	 * 排列字段
	 */
	private Integer rank;
	/**
	 * 是否冻结
	 */
	private Integer freeze;
	
	/**
	 * 办事、百科外键
	 */
	private Integer informationId;
	
	
	
	public Integer getInformationId() {
		return informationId;
	}

	public void setInformationId(Integer informationId) {
		this.informationId = informationId;
	}

	public AppSlideBean() {
	}
	
	/**
	 * 
	* <p>Title:增加构造器 </p>   
	* <p>Description: </p>   
	* @param title
	* @param url
	* @param isShow
	* @param rank
	* @param freeze
	 */
	public AppSlideBean(String title,String url,Integer rank,Integer freeze,Integer informationId){
		this.title=title;
		this.url=url;
		this.rank=rank;
		this.freeze=freeze;
		this.informationId=informationId;
	}
	/**
	 * 
	* <p>Title: 修改构造器</p>   
	* <p>Description: </p>   
	* @param title
	* @param url
	* @param isShow
	* @param rank
	* @param freeze
	 */
	public AppSlideBean(Integer id,String title,String url,Integer isShow,Integer rank,Integer freeze,Integer informationId){
		this.id=id;
		this.title=title;
		this.url=url;
		this.isShow=isShow;
		this.rank=rank;
		this.freeze=freeze;
		this.informationId=informationId;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getFreeze() {
		return freeze;
	}

	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}

	@Override
	public String toString() {
		return "AppSlideBean [id=" + id + ", title=" + title + ", url=" + url + ", createTime=" + createTime
				+ ", isShow=" + isShow + ", rank=" + rank + ", freeze=" + freeze + ", informationId=" + informationId
				+ "]";
	}
}