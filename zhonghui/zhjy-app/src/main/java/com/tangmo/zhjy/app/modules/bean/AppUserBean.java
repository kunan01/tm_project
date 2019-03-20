package com.tangmo.zhjy.app.modules.bean;

import com.tangmo.zhjy.app.modules.dto.AppUserDto;

public class AppUserBean {
	private Integer id;
	/**
	 * 手机号码 || 账号
	 */
	private String phone;
	/**
	 * 昵称
	 */
	private String nikeName;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 真是姓名
	 */
	private String realName;
	/**
	 * 身份证号码
	 */
	private String identityId;
	/**
	 * 是否实名
	 */
	private String isRealname="0";
	/**
	 * 是否是商家
	 */
	private String isStore="0";
	/**
	 * 是否冻结
	 */
	private String freeze="0";
	/**
	 * 所在省
	 */
	private String province;
	/**
	 * 所在市
	 */
	private String city;

	/**
	 * 头像
	 */
	private String headImage;
	
	/**
	 * 身份证正面
	 */
	private String front;
	
	/**
	 * 身份证反面
	 */
	private String verso;

	/**
	 * 是否是商家
	 */
	private Byte isShop;

	public Byte getIsShop() {
		return isShop;
	}

	public void setIsShop(Byte isShop) {
		this.isShop = isShop;
	}

	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getVerso() {
		return verso;
	}

	public void setVerso(String verso) {
		this.verso = verso;
	}

	public AppUserBean() {
	}

	public AppUserBean(Integer id,
			String phone,
			String nikeName,
			String sex,
			String password,
			String realName,
			String identityId,
			String isRealname,
			String isStore,
			String freeze,
			String province,
			String city,
			String front,
			String verso) {
		this.id=id;
		this.phone=phone;
		this.nikeName=nikeName;
		this.sex=sex;
		this.password=password;
		this.realName=realName;
		this.identityId=identityId;
		this.isRealname=isRealname;
		this.isStore=isStore;
		this.freeze=freeze;
		this.province=province;
		this.city=city;
		this.front=front;
		this.verso=verso;
	}

	public AppUserBean(
			String phone,
			String nikeName,
			String sex,
			String password,
			String province,
			String city) {
		this.phone=phone;
		this.nikeName=nikeName;
		this.sex=sex;
		this.password=password;
		this.province=province;
		this.city=city;
	
	}

	public AppUserBean(AppUserDto appUserDto) {
		this.id=appUserDto.getId();
		this.realName=appUserDto.getRealName();
		this.identityId=appUserDto.getIdentityId();
		this.front=appUserDto.getFront();
		this.verso=appUserDto.getVerso();
	}



	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName == null ? null : nikeName.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId == null ? null : identityId.trim();
	}

	public String getIsRealname() {
		return isRealname;
	}

	public void setIsRealname(String isRealname) {
		this.isRealname = isRealname == null ? null : isRealname.trim();
	}

	public String getIsStore() {
		return isStore;
	}

	public void setIsStore(String isStore) {
		this.isStore = isStore == null ? null : isStore.trim();
	}

	public String getFreeze() {
		return freeze;
	}

	public void setFreeze(String freeze) {
		this.freeze = freeze == null ? null : freeze.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "AppUserBean [id=" + id + ", phone=" + phone + ", nikeName=" + nikeName + ", sex=" + sex + ", password="
				+ password + ", realName=" + realName + ", identityId=" + identityId + ", isRealname=" + isRealname
				+ ", isStore=" + isStore + ", freeze=" + freeze + ", province=" + province + ", city=" + city
				+ ", headImage=" + headImage + ", front=" + front + ", verso=" + verso + "]";
	}

	

}