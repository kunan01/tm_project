/**     
* @Title: SysRolePermissionDto2.java   
* @Package com.tangmo.zhjy.modules.sys.dto   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月12日 下午3:48:43   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.modules.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
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
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月12日 下午3:48:43
 */
public class SysRolePermissionDto {
	
	@Min(value=1)
	@NotNull
	private Integer roleId;
	
	@Valid
	@NotNull
	private List<SysRolePermissionBean> permissions;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<SysRolePermissionBean> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SysRolePermissionBean> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "SysRolePermissionDto [roleId=" + roleId + ", permissions=" + permissions + "]";
	}
	
	

}
