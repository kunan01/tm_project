package com.tangmo.zhjy.system.modules.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class SysCommunitySysuserDto {

	private Integer id;

	@ApiModelProperty(value="管理员Id",required=true)
	@NotNull
	private Integer sysUserId;

	@ApiModelProperty(value="多个社区编号ID",required=true)
	@NotNull
	private List<Integer> communityId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}

	public List<Integer> getCommunityId() {
		return communityId;
	}

	public void setCommunityId(List<Integer> communityId) {
		this.communityId = communityId;
	}

	

	
}