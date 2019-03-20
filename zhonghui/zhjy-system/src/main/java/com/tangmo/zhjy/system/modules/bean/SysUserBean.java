package com.tangmo.zhjy.system.modules.bean;

import java.util.List;



/**
 * 
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 *
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
	 * 所拥有角色名称
	 */
	private String roleName;
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
	
	/**
	 * 拥有的所有角色
	 */
	private List<Integer> roles;
	
	/**
	 * 权限详情
	 */
	private List<SysRoleBean> sysRoleBeans;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 工号
	 */
	private String jobNumber;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<SysRoleBean> getSysRoleBeans() {
		return sysRoleBeans;
	}
	public void setSysRoleBeans(List<SysRoleBean> sysRoleBeans) {
		this.sysRoleBeans = sysRoleBeans;
	}

	/**
	 * 拥有的社区
	 */
	private List<Integer>  communitysIds;
	
	public List<Integer> getCommunitysIds() {
		return communitysIds;
	}
	public void setCommunitysIds(List<Integer> communitysIds) {
		this.communitysIds = communitysIds;
	}
	public List<Integer> getRoles() {
		return roles;
	}
	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	/**
	 * 
	* <p>Title: </p>   
	* <p>Description:添加的构造器 </p>   
	* @param username
	* @param password
	* @param freeze
	 */
	public SysUserBean(String username,String password,Integer freeze) {
		this.username=username;
		this.password=password;
		this.freeze=freeze;
	}
	/**
	 * 
	* <p>Title: </p>   
	* <p>Description:修改的构造器 </p>   
	* @param id
	* @param password
	* @param freeze
	 */
	public SysUserBean(Integer id,String password,Integer freeze,Integer isShow) {
		this.id=id;
		this.password=password;
		this.freeze=freeze;
		this.isShow=isShow;
	}

	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public SysUserBean() {

	}
	
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
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getFreeze() {
		return freeze;
	}

	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}

	@Override
	public String toString() {
		return "SysUserBean [id=" + id + ", username=" + username + ", password=" + password + ", freeze=" + freeze
				+ ", isShow=" + isShow + ", roles=" + roles + "]";
	}


}