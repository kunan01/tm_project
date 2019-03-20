/**     
 * @Title: SysRoleController.java   
 * @Package com.tangmo.zhjy.modules.sys.controller   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月10日 下午3:44:54   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.controller;

import javax.validation.Valid;

import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.exception.SystemUserNotExistException;
import com.tangmo.zhjy.system.modules.bean.SysRoleBean;
import com.tangmo.zhjy.system.modules.dto.SysRoleDto;
import com.tangmo.zhjy.system.modules.dto.SysRolePermissionDto;
import com.tangmo.zhjy.system.modules.dto.SysRoleUserDto;
import com.tangmo.zhjy.system.modules.service.SysRoleService;
import com.tangmo.zhjy.system.utils.ValidationUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
 * @Description : TODO(角色管理控制层)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月10日 下午3:44:54
 */
@RestController
@RequestMapping(value="/system/role")
@Api(value="角色管理模块")
public class SysRoleController extends BaseController {

	@Autowired
	SysRoleService sysRoleServiceImpl;

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="添加角色",notes="")
	@ApiImplicitParam(name="sysRoleDto",dataType="SysRoleDto",required=true)
	@PostMapping(value="/saveSysRole")
	public Result saveSysRole(@Valid @RequestBody SysRoleDto sysRoleDto,BindingResult error){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("添加角色");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		ValidationUtil.verifyDispose(error);
		//根据角色名称查询
		SysRoleBean selectRoleBean=sysRoleServiceImpl.selectByName(sysRoleDto.getName());
		//判断是否存在
		if(selectRoleBean==null){
			return sysRoleServiceImpl.saveSysRole(new SysRoleBean(sysRoleDto.getName(),sysRoleDto.getFreeze()));
		}else{
			throw new SystemUserNotExistException(ResultCode.SYSTEM_ADMIN_ROLE_ERROR);
		}
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="修改角色",notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="角色编号",dataType="Long",required=true,paramType="path"),
		@ApiImplicitParam(name="sysRoleDto",value="角色Dto",dataType="SysRoleDto",required=true)
	})
	@PutMapping(value="/updateSysRole/{id:\\d+}")
	public Result updateSysRole(@PathVariable Long id,@Valid @RequestBody SysRoleDto sysRoleDto,BindingResult error){
		ValidationUtil.verifyDispose(error);
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("修改角色");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);

		SysRoleBean selectSysRoleBean = (SysRoleBean) sysRoleServiceImpl.findById(id.intValue()).getData();
		if(selectSysRoleBean!=null){
			//根据名称查询角色
			if(sysRoleServiceImpl.selectByName(sysRoleDto.getName())!=null){
				throw new SystemUserNotExistException(ResultCode.SYSTEM_ADMIN_ROLE_ERROR);
			}else{
				selectSysRoleBean.setFreeze(sysRoleDto.getFreeze());
				selectSysRoleBean.setName(sysRoleDto.getName());
				selectSysRoleBean.setIsShow(sysRoleDto.getIsShow());
				//修改角色
				return  sysRoleServiceImpl.updateSysRole(selectSysRoleBean);
			}
		}else{
			throw new SystemUserNotExistException(ResultCode.PAPAMETE_ERROR);
		}
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="删除角色",notes="概念性删除")
	@ApiImplicitParam(name="id",value="角色编号ID",dataType="Long",required=true,paramType="path")
	@DeleteMapping(value="/deleteSysRole/{id:\\d+}")
	public Result deleteSysRole(@PathVariable Long id){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("删除角色");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		SysRoleBean selectRoleBean = (SysRoleBean) sysRoleServiceImpl.findById(id.intValue()).getData();
		if(selectRoleBean==null){
			throw new SystemUserNotExistException(ResultCode.PAPAMETE_ERROR);
		}else{
			selectRoleBean.setIsShow(1);
			return sysRoleServiceImpl.deleteSysRole(selectRoleBean);
		}
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="彻底删除角色",notes="彻底删除")
	@ApiImplicitParam(name="id",value="角色编号ID",dataType="Long",required=true,paramType="path")
	@DeleteMapping(value="/delSysRole/{id:\\d+}")
	public Result delSysRole(@PathVariable Long id){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("彻底删除角色");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
			return sysRoleServiceImpl.delSysRole(id.intValue());
	}


	@SuppressWarnings("rawtypes")
	@ApiOperation(value="关联角色",notes="一个角色关联多个管理员")
	@ApiImplicitParams({
		@ApiImplicitParam(name="sysRoleUserDto",value="角色关联表Dto",dataType="SysRoleUserDto",required=true)
	})
	@PostMapping(value="/relevanceAdmin")
	public Result relevanceAdmin(@Valid @RequestBody SysRoleUserDto sysRoleUserDto,BindingResult error){
		ValidationUtil.verifyDispose(error);
	    return sysRoleServiceImpl.relevanceAdmin(sysRoleUserDto.getRoleId(), sysRoleUserDto.getUsers());
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页查询角色",notes="如果要查询所有角色不用传参数即可")
	@ApiImplicitParams({
		@ApiImplicitParam(name="roleName",value="模糊查询参数",dataType="string",required=false,paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query")
	})
	@GetMapping(value="/findByRolePage")
	public Result findByPage(String roleName,
			Integer pageNo,
			Integer pageSize){
		return sysRoleServiceImpl.findByPage(roleName, pageNo, pageSize);
	}
	
	@SuppressWarnings("rawtypes")	
	@ApiOperation(value="分配权限",notes="给角色分配菜单权限和操作权限,注：permissions是一个数组对象")
	@ApiImplicitParam(name="permissions",value="分配菜单及操作权限数组",dataType="SysRolePermissionDto",required=true)
	@PostMapping("/allotAccredit")
	public Result allotAccredit(@Valid @RequestBody SysRolePermissionDto permissions,BindingResult error){
		ValidationUtil.verifyDispose(error);
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("分配权限");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return sysRoleServiceImpl.allotAccredit(permissions.getRoleId(), permissions.getPermissions());
	}

}
