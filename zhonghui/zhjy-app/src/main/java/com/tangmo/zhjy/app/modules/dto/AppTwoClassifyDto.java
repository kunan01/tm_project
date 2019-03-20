package com.tangmo.zhjy.app.modules.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class AppTwoClassifyDto {

	private Integer id;
	@ApiModelProperty(value="二级分类名称")
	private String name;
	@ApiModelProperty(value="图标访问路径")
	private String icon;
	@ApiModelProperty(value="访问路径")
	private String url;
	@ApiModelProperty(value="是否冻结：0：正常 1：冻结")
	@Max(value=1)
	@Min(value=0)
	private Integer freeze;
	@ApiModelProperty(value="是否删除：0：正常 1：删除",hidden=true)
	@Max(value=1)
	@Min(value=0)
	private Integer isShow;
	@ApiModelProperty(value="一级分类Id")
	private Integer appClassifyid;
	
	

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getAppClassifyid() {
		return appClassifyid;
	}

	public void setAppClassifyid(Integer appClassifyid) {
		this.appClassifyid = appClassifyid;
	}
}