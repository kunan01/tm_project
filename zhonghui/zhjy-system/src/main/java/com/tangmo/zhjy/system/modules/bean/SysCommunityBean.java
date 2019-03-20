package com.tangmo.zhjy.system.modules.bean;

import java.util.List;

public class SysCommunityBean {
	private Integer id;

	private String logo;

	private String name;

	private String intro;

	private String longitude;

	private String latitude;

	private String address;

	private String phone;

	private String province;

	private String city;

	private String district;

	private Integer isShow=0;
	
	private Integer isAttention2;

	private Integer sysUserId;//超级管理员id

	private boolean isAttention=false; //用户是否关注该社区

	public Integer getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}

	private List<SysCommunityInformBean> SysCommunityInforms ;

	public List<SysCommunityInformBean> getSysCommunityInforms() {
		return SysCommunityInforms;
	}
	

	public Integer getIsAttention2() {
		return isAttention2;
	}


	public void setIsAttention2(Integer isAttention2) {
		this.isAttention2 = isAttention2;
	}


	public void setSysCommunityInforms(List<SysCommunityInformBean> SysCommunityInforms) {
		this.SysCommunityInforms = SysCommunityInforms;

	}

	public boolean isAttention() {
		return isAttention;
	}

	public void setAttention(boolean isAttention) {
		this.isAttention = isAttention;
	}

	public SysCommunityBean(Integer id,
			String logo,
			String name,
			String intro,
			String longitude,
			String latitude,
			String address,
			String phone,
			String province,
			String city,
			String district,
			Integer isShow){
		this.id=id;
		this.logo=logo;
		this.name=name;
		this.intro=intro;
		this.longitude=longitude;
		this.latitude=latitude;
		this.address=address;
		this.phone=phone;
		this.province=province;
		this.city=city;
		this.district=district;
		this.isShow=isShow;
	}

	public SysCommunityBean(
			String logo,
			String name,
			String intro,
			String longitude,
			String latitude,
			String address,
			String phone,
			String province,
			String city,
			String district){
		this.logo=logo;
		this.name=name;
		this.intro=intro;
		this.longitude=longitude;
		this.latitude=latitude;
		this.address=address;
		this.phone=phone;
		this.province=province;
		this.city=city;
		this.district=district;
	}

	public SysCommunityBean() {
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