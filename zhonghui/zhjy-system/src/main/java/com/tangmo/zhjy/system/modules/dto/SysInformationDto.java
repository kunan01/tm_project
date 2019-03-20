package com.tangmo.zhjy.system.modules.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class SysInformationDto {

	private Integer id;
	@ApiModelProperty(value="标题")
	private String title;
	@ApiModelProperty(value="是否冻结：0正常 1冻结")
	@Max(value=1)
	@Min(value=0)
	private Integer freeze;
	@ApiModelProperty(value="是否删除：0正常 1删除",hidden=true)
	@Max(value=1)
	@Min(value=0)
	private Integer isShow;
	@ApiModelProperty(value="添加的管理员ID")
	@Min(value=1)
	private Integer sysUserid;
	@ApiModelProperty(value="文章内容")
	private String content;
	@ApiModelProperty(value="封面图")
	private String[] urls;
	@ApiModelProperty(value="二级分类编号")
	private Integer twoClassifyId;
	@ApiModelProperty(value="是否审核通过：0：未通过 1：待审核 2：通过 3：驳回",hidden=true)
	@Max(value=3)
	@Min(value=0)
	private Integer isChecked;
	

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

	public String[] getUrls() {
		return urls;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
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

}
