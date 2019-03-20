package com.tangmo.zhjy.system.modules.service;

import java.util.List;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.SysRoleBean;
import com.tangmo.zhjy.system.modules.dto.SysRolePermissionBean;

public interface SysRoleService {

	/**
	 * 
	 * @Title: saveSysRole   
	 * @Description: TODO(添加角色)   
	 * @param @param sysRoleBean
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	Result saveSysRole(SysRoleBean sysRoleBean);

	/**
	 * 
	 * @Title: updateSysRole   
	 * @Description: TODO(修改角色信息)   
	 * @param @param sysRoleBean
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	Result updateSysRole(SysRoleBean sysRoleBean);

	/**
	 * 
	 * @Title: findById   
	 * @Description: TODO(根据角色ID查询)   
	 * @param @param id
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	Result findById(int id);

	/**
	 * 
	* @Title: delSysRole
	* @Description: TODO(彻底删除角色)
	* @param @param id
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	Result delSysRole(Integer id);

	/**
	 *
	 * @Title: deleteSysRole
	 * @Description: TODO(概念删除角色)
	 * @param @param id
	 * @param @return    设定文件
	 * @return Result    返回类型
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	Result deleteSysRole(SysRoleBean sysRoleBean);
	/**
	 * 
	* @Title: selectByName   
	* @Description: TODO(根据角色名称查询)   
	* @param @param name
	* @param @return    设定文件   
	* @return SysRoleBean    返回类型   
	* @throws
	 */
	SysRoleBean selectByName(String name);
	
	@SuppressWarnings("rawtypes")
	Result relevanceAdmin(int roleId,List<Integer> userId);
	
	/**
	 * 
	* @Title: findByPage   
	* @Description: TODO(分页查询)   
	* @param @param roleName
	* @param @return    设定文件   
	* @return Page<SysRoleBean>    返回类型   
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	Result findByPage(String roleName,Integer pageNo,Integer pageSize);
	
	/**
	 * 
	* @Title: allotAccredit   
	* @Description: TODO(分配权限+更新权限)   
	* @param @param roleId
	* @param @param permissions
	* @param @return    设定文件   
	* @return Result    返回类型   
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	Result allotAccredit(Integer roleId,List<SysRolePermissionBean> permissions);


}