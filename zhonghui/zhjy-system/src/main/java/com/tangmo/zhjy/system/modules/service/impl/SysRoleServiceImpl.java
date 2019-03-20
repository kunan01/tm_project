/**     
 * @Title: SysRoleServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.sys.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月10日 下午3:26:14   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tangmo.zhjy.system.modules.dao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.exception.SystemUserNotExistException;
import com.tangmo.zhjy.system.modules.bean.SysPermission;
import com.tangmo.zhjy.system.modules.bean.SysRelatierolOperation;
import com.tangmo.zhjy.system.modules.bean.SysRoleBean;
import com.tangmo.zhjy.system.modules.bean.SysRolePermission;
import com.tangmo.zhjy.system.modules.dto.SysRolePermissionBean;
import com.tangmo.zhjy.system.modules.service.SysRoleService;

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
 * @Description : TODO(后台管理角色业务逻辑层)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月10日 下午3:26:14
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	SysRoleBeanMapper sysRoleBeanMapper;

	@Autowired
	SysCommunitySysuserBeanMapper sysCommunitySysuserBeanMapper;

	@Autowired
	SysRoleUserBeanMapper sysRoleUserBeanMapper;

	@Autowired
	SysRolePermissionMapper sysRolePermissionMapper;

	@Autowired
	SysRelatierolOperationMapper sysRelatierolOperationMapper;
	
	@Autowired
	SysUserBeanMapper sysUserBeanMapper;

	@Autowired
	SysOperationMapper sysOperationMapper;
	
	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Autowired
	private AppBrowsingHistoryBeanMapper appBrowsingHistoryBeanMapper;

	@Autowired
	private AppCollectMapper appCollectMapper;

	@Autowired
	private AppInformationPageViewMapper appInformationPageViewMapper;

	@Autowired
	private SysInformationImagesMapper sysInformationImagesMapper;

	@Autowired
	private SysSlideBeanMapper sysSlideBeanMapper;



	Logger logger = Logger.getLogger(SysRoleServiceImpl.class);

	/* (非 Javadoc)   
	 * <p>Title: saveSysRole</p>   
	 * <p>Description: </p>   
	 * @param sysRoleBean
	 * @return   
	 * @see com.tangmo.zhjy.modules.sys.service.impl.SysRoleService#saveSysRole(com.tangmo.zhjy.modules.sys.bean.SysRoleBean)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result saveSysRole(SysRoleBean sysRoleBean){
		sysRoleBean.setName("ROLE_"+sysRoleBean.getName());
		sysRoleBeanMapper.insertSelective(sysRoleBean);
		return new Result(ResultCode.SUCCESS);
	}
	/* (非 Javadoc)   
	 * <p>Title: updateSysRole</p>   
	 * <p>Description: </p>   
	 * @param sysRoleBean
	 * @return   
	 * @see com.tangmo.zhjy.modules.sys.service.impl.SysRoleService#updateSysRole(com.tangmo.zhjy.modules.sys.bean.SysRoleBean)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result updateSysRole(SysRoleBean sysRoleBean){
		try {
			sysRoleBeanMapper.updateByPrimaryKeySelective(sysRoleBean);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new SystemUserNotExistException(ResultCode.WEAK_NET_WORK);
		}
		return new Result(ResultCode.SUCCESS);
	}

	/* (非 Javadoc)   
	 * <p>Title: findById</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.tangmo.zhjy.modules.sys.service.impl.SysRoleService#findById(int)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result findById(int id){
		SysRoleBean sysRoleBean = sysRoleBeanMapper.selectByPrimaryKey(id);
		return new Result<SysRoleBean>(ResultCode.SUCCESS,sysRoleBean);
	}


	/**
	 * 
	 * @Title: selectByName   
	 * @Description: TODO(根据角色名称查询)   
	 * @param @param name
	 * @param @return    设定文件   
	 * @return SysRoleBean    返回类型   
	 * @throws
	 */
	public SysRoleBean selectByName(String name){
		return sysRoleBeanMapper.selectByName(name);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result relevanceAdmin(int roleId, List<Integer> userId) {
		try {
			if(sysRoleBeanMapper.selectByPrimaryKey(roleId)!=null){
				if(sysUserBeanMapper.findUserId(userId).size()==userId.size()){
					return	new Result(ResultCode.SUCCESS,sysRoleUserBeanMapper.relevanceAdmin(roleId, userId));
				}
			}
			return new Result(ResultCode.PAPAMETE_ERROR);
		} catch (Exception e) {
			logger.info("关联角色接口：异常信息"+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByPage(String roleName, Integer pageNo, Integer pageSize) {
		try {
			if(roleName!=null && !"".equals(roleName)){
				roleName="%"+roleName+"%";
			}else{
				roleName="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			//查询角色集合
			List<SysRoleBean> sysRoles=sysRoleBeanMapper.findByPage(roleName);
			//2.查询关联菜单信息
			List<SysPermission> sysPermissions = sysPermissionMapper.findByPermissionAndOperation(sysRoles);
			//循环遍历角色
			for (SysRoleBean sysRoleBean : sysRoles) {
				
				List<SysRolePermission> isBePermission = sysRolePermissionMapper.selectByRoleId(sysRoleBean.getId());
				List<SysPermission> sysPermissions2 = new ArrayList<SysPermission>();
				//判断该角色是否拥有菜单
				if(isBePermission.size()!=0){
					//7.循环遍历拥有菜单集合
					for (SysPermission sysPermission : sysPermissions) {
						if(sysPermission.getRoleId()==sysRoleBean.getId()){
							//根据角色和菜单中间表记录编号查找拥有操作记录ID
							List<SysRelatierolOperation> sysRelatierolOperations=sysRelatierolOperationMapper.findByRelatierolId(sysPermission.getSysRolePermissionId());
							List<Integer> operationIds = new ArrayList<Integer>();
							for (SysRelatierolOperation SysRelatierolOperation : sysRelatierolOperations) {
								operationIds.add(SysRelatierolOperation.getId());
							}
							if(operationIds.size()!=0){
								sysPermission.setSysOperations(sysOperationMapper.batchSelect(operationIds));
							}
							sysPermissions2.add(sysPermission);
						}
						
					}
					
				}
				sysRoleBean.setSysPermissions(sysPermissions2);
			}
			
			return new Result(ResultCode.SUCCESS,new PageInfo(sysRoles));
		} catch (Exception e) {
			logger.info("角色分页接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Result allotAccredit(Integer roleId, List<SysRolePermissionBean> permissions) {
//		try {
			System.out.println(sysRoleBeanMapper.selectByPrimaryKey(roleId));
			if(sysRoleBeanMapper.selectByPrimaryKey(roleId)!=null){
				List<SysRolePermission> sysRolePermissions = new ArrayList<SysRolePermission>();
				//循环遍历Dto
				for (SysRolePermissionBean sysRolePermissionBean : permissions) {
					SysRolePermission sysRolePermission = new SysRolePermission();
					sysRolePermission.setSysRoleId(roleId);
					sysRolePermission.setSysPermissionId(sysRolePermissionBean.getSysPermissionId());
					sysRolePermission.setOpermissions(sysRolePermissionBean.getOperations());
					sysRolePermissions.add(sysRolePermission);
				}
				//删除当前角色关联关系
				sysRolePermissionMapper.deleteByPrimaryKey(roleId);
				//给当前角色关联菜单
				sysRolePermissionMapper.allotAccredit(sysRolePermissions);
				//循环遍历给菜单关联操作
				for (SysRolePermission sysRolePermission : sysRolePermissions) {
					if(sysRolePermission.getOpermissions()!=null && sysRolePermission.getOpermissions().size()>0){
						//查询操作编号数据是否合法
						if(sysOperationMapper.findById(sysRolePermission.getOpermissions()).size()==sysRolePermission.getOpermissions().size()){
							SysRelatierolOperation sysRelatierolOperation = new SysRelatierolOperation();
							sysRelatierolOperation.setSysRelatierolId(sysRolePermission.getId());
							sysRelatierolOperation.setSysOperationIds(sysRolePermission.getOpermissions());
							//添加操作权限
							sysRelatierolOperationMapper.batchAdd(sysRelatierolOperation);
						}else{
							return new Result(ResultCode.PAPAMETE_ERROR);
						}
					}
				}
				return new Result(ResultCode.SUCCESS);
			}else{
				return new Result(ResultCode.PAPAMETE_ERROR);
			}
	/*	} catch (Exception e) {
			logger.info("角色分配权限接口异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}*/
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Result deleteSysRole(SysRoleBean sysRoleBean) {
		try {
			sysRoleBeanMapper.updateByPrimaryKeySelective(sysRoleBean);
		} catch (Exception e) {
			logger.info("删除角色接口异常:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new SystemUserNotExistException(ResultCode.WEAK_NET_WORK);
		}
		return new Result<SysRoleBean>(ResultCode.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public Result delSysRole(Integer id) {
		try {
			//根据sysUserId删除管理员社区分配表的信息
			sysCommunitySysuserBeanMapper.deleteBySysUserId(id);
			//根据sysUserId删除管理员发布的信息app_information：
					//1：根据文章id删除文章封面图
			sysInformationImagesMapper.deleteByInfomationId(id);
					//2:根据文章id删除文章的访问量表
			appInformationPageViewMapper.deleteByInfomationId(id);
					//3：关联的轮播图
			sysSlideBeanMapper.deleteByInfomationId(id);
					//4:收藏的
			appCollectMapper.deleteByInfomationId(id);
					//5：浏览记录
			appBrowsingHistoryBeanMapper.deleteByInfomationId(id);
			//根据sysUserId删除管理员角色分配表
			sysRoleUserBeanMapper.deleteBySysUserId(id);
			//根据sysUserId删除角色表
			sysRoleBeanMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.info("彻底删除角色接口异常:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new SystemUserNotExistException(ResultCode.WEAK_NET_WORK);
		}
		return new Result<SysRoleBean>(ResultCode.SUCCESS);
	}

}
