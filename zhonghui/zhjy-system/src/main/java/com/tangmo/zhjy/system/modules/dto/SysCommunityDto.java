package com.tangmo.zhjy.system.modules.dto;

import io.swagger.annotations.ApiModelProperty;

public class SysCommunityDto {

	private Integer id;

	@ApiModelProperty(value="社区LOGO")
	private String logo;

	@ApiModelProperty(value="社区名称")
	private String name;

	@ApiModelProperty(value="社区介绍")
	private String intro;

	@ApiModelProperty(value="经度")
	private String longitude;

	@ApiModelProperty(value="纬度")
	private String latitude;

	@ApiModelProperty(value="详细地址")
	private String address;

	@ApiModelProperty(value="手机号码")
	private String phone;

	@ApiModelProperty(value="省")
	private String province;

	@ApiModelProperty(value="市")
	private String city;

	@ApiModelProperty(value="区")
	private String district;

	@ApiModelProperty(value="是否删除:0正常1删除",hidden=true)

	private Integer isShow;

	private Integer sysUserId;//超级管理员id

	public Integer getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo == null ? null : logo.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude == null ? null : longitude.trim();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude == null ? null : latitude.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district == null ? null : district.trim();
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
}