/**     
* @Title: SysRoleUserDto.java   
* @Package com.tangmo.zhjy.modules.sys.dto   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月11日 下午5:03:01   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.modules.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

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
 * @Date : 2018年1月11日 下午5:03:01
 */
public class SysRoleUserDto {
	
	/**
	 * 角色编号
	 */
	@NotNull(message="角色编号不能为空！")
	@ApiModelProperty(value="角色编号",required=true,dataType="Integer")
	private Integer roleId;
	
	/**
	 * 管理员集合
	 */
	@NotNull(message="请填写关联管理员编号")
	@ApiModelProperty(value="多个管理员ID",required=true,dataType="Integer[]")
	private List<Integer> users;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getUsers() {
		return users;
	}

	public void setUsers(List<Integer> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "SysRoleUserDto [roleId=" + roleId + ", users=" + users + "]";
	}
	
	
	
	

}
