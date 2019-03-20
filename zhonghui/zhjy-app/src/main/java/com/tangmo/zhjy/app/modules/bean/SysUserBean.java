package com.tangmo.zhjy.app.modules.bean;

import java.util.List;


/**
 * @Description : TODO(管理员用户表实体类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月7日 下午10:00:05
 */
public class SysUserBean {
	
	private Integer id;
	/**
	 * 账号
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 0：正常  1：冻结
	 */
	private Integer freeze;
	/**
	 * 0：显示 1：隐藏
	 */
	private Integer isShow;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}