package com.tangmo.zhjy.system.modules.service.impl;


import com.tangmo.zhjy.system.modules.bean.*;
import com.tangmo.zhjy.system.modules.dao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.dto.SysCommunityDto;
import com.tangmo.zhjy.system.modules.service.SysCommunityService;
import com.tangmo.zhjy.system.utils.PageInfo;
import com.tangmo.zhjy.system.utils.StringUtil;

import java.util.List;

@Service
@Transactional
public class SysCommunityServiceImple implements SysCommunityService {

	@Autowired
	private SysCommunityBeanMapper appCommunityBeanMapper;//社区表

	@Autowired
	private SysCommunityInformAppUserBeanMapper sysCommunityInformAppUserBeanMapper;//社区通知未、已读表

	@Autowired
	private SysCommunityInformBeanMapper sysCommunityInformBeanMapper;  //社区通知表

	@Autowired
	private SysCommunitySysuserBeanMapper sysCommunitySysuserBeanMapper;//社区用户分配表

	@Autowired 
	private SysCommunityUserBeanMapper  appCommunityUserBeanMapper;//社区用户关注表

	@Autowired
	private SysUserBeanMapper appUserBeanMapper;//用户表

	@Autowired
	private SysRoleUserBeanMapper sysRoleUserBeanMapper;//管理员角色







	Logger logger = Logger.getLogger(SysCommunityServiceImple.class);

	/* (非 Javadoc)   
	 * <p>Title: saveCommunity</p>   
	 * <p>Description: </p>   
	 * @param appCommunityDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#saveCommunity(com.tangmo.zhjy.modules.app.dto.AppCommunityDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result saveCommunity(SysCommunityDto appCommunityDto){
		try {
			SysCommunityBean  appCommunityBean= new SysCommunityBean(appCommunityDto.getLogo()
					,appCommunityDto.getName()
					, appCommunityDto.getIntro(),
					appCommunityDto.getLongitude(),
					appCommunityDto.getLatitude(),
					appCommunityDto.getAddress(),
					appCommunityDto.getPhone(),
					appCommunityDto.getProvince(),
					appCommunityDto.getCity(),
					appCommunityDto.getDistrict());
			Integer a=appCommunityBeanMapper.insert(appCommunityBean);
			if(a>0){
				SysCommunitySysuserBean communitySysuserBean=new SysCommunitySysuserBean();
				communitySysuserBean.setCommunityId(appCommunityBean.getId());
				communitySysuserBean.setSysUserId(appCommunityDto.getSysUserId());
				sysCommunitySysuserBeanMapper.insertSelective(communitySysuserBean);
			}
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("添加社区接口异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	 * <p>Title: modify</p>   
	 * <p>Description: </p>   
	 * @param appCommunityDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#modify(com.tangmo.zhjy.modules.app.dto.AppCommunityDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modify(SysCommunityDto appCommunityDto){
		Integer id = appCommunityDto.getId();
		try {
			if(id!=null){
				if(appCommunityBeanMapper.selectByPrimaryKey(id)!=null){
					SysCommunityBean  appCommunityBean= new SysCommunityBean(id,appCommunityDto.getLogo()
							,appCommunityDto.getName()
							, appCommunityDto.getIntro(),
							appCommunityDto.getLongitude(),
							appCommunityDto.getLatitude(),
							appCommunityDto.getAddress(),
							appCommunityDto.getPhone(),
							appCommunityDto.getProvince(),
							appCommunityDto.getCity(),
							appCommunityDto.getDistrict(),
							appCommunityDto.getIsShow());
					appCommunityBeanMapper.updateByPrimaryKeySelective(appCommunityBean);
					return new Result(ResultCode.SUCCESS);
				}
			}
		} catch (Exception e) {
			logger.info("修改社区接口异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}


	/**
	 * 彻底删除社区信息
	 * @param id
	 * @return
	 */

	//根据社区id关联删除有关该社区的所有表
	@Override
	@Transactional
	public Result delById(Integer id) {
		try{
			//根据社区id查找社区通知表的所有通知id,根据社区通知表id删除所有社区用户通知已读未读表
			sysCommunityInformAppUserBeanMapper.deleteByCommunityInformId(id);
			//根据社区id查找所有的社区通知，根据社区通知id删除所有的社区通知表
			sysCommunityInformBeanMapper.deleteByCommunityId(id);
			//根据社区id删除所有有关联的管理员社区分配表
			sysCommunitySysuserBeanMapper.deleteByCommunityId(id);
			//根据社区id删除所有该社区被关注的用户
			appCommunityUserBeanMapper.deleteByCommunityId(id);
			//根据社区id删除该社区
			appCommunityBeanMapper.deleteByPrimaryKey(id);


			return new Result(ResultCode.SUCCESS);
		}catch(Exception e){
			logger.info("删除社区接口异常："+e);
			return new Result(ResultCode.PAPAMETE_ERROR);
		}
	}

	/* (非 Javadoc)
	 * <p>Title: findByPage</p>   
	 * <p>Description: </p>   
	 * @param title
	 * @param pageNo
	 * @param pageSize
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#findByPage(java.lang.String, java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByPage(String title,Integer pageNo,Integer pageSize,Integer userId){
		try {
			if(title!=null && !StringUtil.isEmpty(title)){
				title="%"+title+"%";
			}else{
				title="%%";
			}

			SysRoleUserBean sysRoleUserBean=sysRoleUserBeanMapper.selectBySysUserId(userId);
			if(sysRoleUserBean!=null){
				if(sysRoleUserBean.getSysRoleId()==1){ //代表是超级管理员
					userId=null;
				}
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appCommunityBeanMapper.findBySysPageBySysUserId(title,userId)));
			//return new Result(ResultCode.SUCCESS,new PageInfo(appCommunityBeanMapper.findBySysPage(title)));
		} catch (Exception e) {
			logger.info("分页查询社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	 * <p>Title: addAttention</p>   
	 * <p>Description: </p>   
	 * @param userId
	 * @param communityId
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#addAttention(java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result addAttention(Integer userId,Integer communityId){
		try {
			SysCommunityUserBean appCommunityUserBean =	appCommunityUserBeanMapper.findBycommunityIdAndUserId(userId, communityId);
			if(appCommunityUserBean==null){
				SysUserBean appUserBean=appUserBeanMapper.selectByPrimaryKey(userId);
				SysCommunityBean appCommunityBean = appCommunityBeanMapper.selectByPrimaryKey(communityId);
				if(appUserBean!=null && appCommunityBean!=null){
					appCommunityUserBean = new SysCommunityUserBean(userId,communityId);
					appCommunityUserBeanMapper.insert(appCommunityUserBean);
					return new Result(ResultCode.SUCCESS);
				}
			}else{
				return new Result(10000,"已经关注该社区",null);
			}
		} catch (Exception e) {
			logger.info("关注社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}

		return new Result(ResultCode.PAPAMETE_ERROR);
	}

	/* (非 Javadoc)   
	 * <p>Title: removeAttention</p>   
	 * <p>Description: </p>   
	 * @param userId
	 * @param communityId
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#removeAttention(java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result removeAttention(Integer userId,Integer communityId){
		try {
			SysCommunityUserBean appCommunityUserBean =	appCommunityUserBeanMapper.findBycommunityIdAndUserId(userId, communityId);
			if(appCommunityUserBean!=null){
				appCommunityUserBeanMapper.deleteByPrimaryKey(userId, communityId);
				return new Result(ResultCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.info("取消关注社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}

		return new Result(ResultCode.PAPAMETE_ERROR);
	}

	/**
	 * 
	 * @Title: findById   
	 * @Description: TODO(根据编号查找)   
	 * @param @param id
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Result findById(Integer id){
		try {
			SysCommunityBean appCommunityBean =	appCommunityBeanMapper.selectByPrimaryKey(id);
			if(appCommunityBean!=null){
				return new Result(ResultCode.SUCCESS,appCommunityBean);
			}
		} catch (Exception e) {
			logger.info("根据社区编号查找接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Result findAttention(Integer userId){
		try {
			return new Result(ResultCode.SUCCESS,appCommunityBeanMapper.findAttention(userId));
		} catch (Exception e) {
			logger.info("根据用户编号查找加入社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)
	 * <p>Title: queryAll</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#queryAll()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result queryAll(Integer userId){
		try {
			List<SysCommunityBean> appCommunityBeans=appCommunityBeanMapper.findBySysPage("%%");//查到所有的社区
			List<SysCommunityBean> attentionCommunitys=appCommunityBeanMapper.queryAll(userId);//查询到该管理员所管理的社区
			for (SysCommunityBean appCommunityBean : appCommunityBeans) {//循环所有社区
				for (SysCommunityBean sysCommunityBean2 : attentionCommunitys) {//循环用户所拥有的社区
					if(sysCommunityBean2.getId()==appCommunityBean.getId() && sysCommunityBean2.getIsAttention2()!=null){
						appCommunityBean.setAttention(true);
					}
				}
			}
			return new Result(ResultCode.SUCCESS,appCommunityBeans);
		} catch (Exception e) {
			logger.info("查询所有社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

}
