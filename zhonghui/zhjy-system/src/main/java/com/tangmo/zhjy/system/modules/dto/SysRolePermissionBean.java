/**     
 * @Title: SysRolePermissionDto.java   
 * @Package com.tangmo.zhjy.modules.sys.dto   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月12日 上午11:56:15   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
 * @Description : TODO(角色关联菜单校验类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月12日 上午11:56:15
 */
public class SysRolePermissionBean implements Serializable{
	
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/   
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键",dataType="Integer",hidden=true)
	@Null
	private Integer id;
	@NotNull
	@ApiModelProperty(value="菜单ID",dataType="Integer",required=true,example="菜单编号不能小于1")
	@Min(value=1,message="菜单ID传递有误")
	private Integer sysPermissionId;
	@NotNull
	@ApiModelProperty(value="操作记录编号",dataType="Integer[]")
	private List<Integer> operations;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getSysPermissionId() {
		return sysPermissionId;
	}

	public void setSysPermissionId(Integer sysPermissionId) {
		this.sysPermissionId = sysPermissionId;
	}
	
	

	public List<Integer> getOperations() {
		return operations;
	}

	public void setOperations(List<Integer> operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		return "SysRolePermissionBean [id=" + id + ", sysPermissionId=" + sysPermissionId + ", operations=" + operations
				+ "]";
	}

	
	
	
}
