package com.tangmo.zhjy.system.modules.bean;


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
		this.phone = phone;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getIsRealname() {
		return isRealname;
	}

	public void setIsRealname(String isRealname) {
		this.isRealname = isRealname;
	}

	public String getIsStore() {
		return isStore;
	}

	public void setIsStore(String isStore) {
		this.isStore = isStore;
	}

	public String getFreeze() {
		return freeze;
	}

	public void setFreeze(String freeze) {
		this.freeze = freeze;
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

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
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

	public Byte getIsShop() {
		return isShop;
	}

	public void setIsShop(Byte isShop) {
		this.isShop = isShop;
	}

    @Override
    public String toString() {
        return "AppUserBean{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", nikeName='" + nikeName + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", identityId='" + identityId + '\'' +
                ", isRealname='" + isRealname + '\'' +
                ", isStore='" + isStore + '\'' +
                ", freeze='" + freeze + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", headImage='" + headImage + '\'' +
                ", front='" + front + '\'' +
                ", verso='" + verso + '\'' +
                ", isShop=" + isShop +
                '}';
    }
}