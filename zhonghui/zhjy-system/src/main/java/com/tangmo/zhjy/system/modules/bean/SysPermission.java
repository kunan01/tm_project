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
 * @Description : TODO(后台管理系统菜单实体类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月13日 下午8:58:23
 */
public class SysPermission {
	private Integer id;
	//菜单名称
	private String name;
	//描述
	private String descritpion;
	//访问路径
	private String url;
	//父菜单ID
	private Integer pid;
	//当前菜单所拥有的增删改操作实体类
	private List<SysOperation> SysOperations;
	//二级菜单
	private List<SysPermission> permissionTos;
	//角色ID  
	private Integer roleId;
	//角色和菜单中间表记录ID
	private Integer sysRolePermissionId;
	
	

	public List<SysPermission> getPermissionTos() {
		return permissionTos;
	}

	public void setPermissionTos(List<SysPermission> permissionTos) {
		this.permissionTos = permissionTos;
	}

	public Integer getSysRolePermissionId() {
		return sysRolePermissionId;
	}

	public void setSysRolePermissionId(Integer sysRolePermissionId) {
		this.sysRolePermissionId = sysRolePermissionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	SysPermission(){

	}

	SysPermission(String name,String descritpion,String url,Integer pid){
		this.name=name;
		this.descritpion=descritpion;
		this.url=url;
		this.pid=pid;
	}


	public List<SysOperation> getSysOperations() {
		return SysOperations;
	}

	public void setSysOperations(List<SysOperation> sysOperations) {
		SysOperations = sysOperations;
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

	public String getDescritpion() {
		return descritpion;
	}

	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion == null ? null : descritpion.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "SysPermission [id=" + id + ", name=" + name + ", descritpion=" + descritpion + ", url=" + url + ", pid="
				+ pid + ", SysOperations=" + SysOperations + ", roleId=" + roleId + "]";
	}


}