package com.tangmo.zhjy.system.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.SysCommunityInformAppUserBean;
import com.tangmo.zhjy.system.modules.bean.SysCommunityInformBean;
import com.tangmo.zhjy.system.modules.bean.SysCommunityUserBean;
import com.tangmo.zhjy.system.modules.dao.SysCommunityInformAppUserBeanMapper;
import com.tangmo.zhjy.system.modules.dao.SysCommunityInformBeanMapper;
import com.tangmo.zhjy.system.modules.dao.SysCommunityUserBeanMapper;
import com.tangmo.zhjy.system.modules.dto.SysCommunityInformDto;
import com.tangmo.zhjy.system.modules.service.SysCommunityInfomService;
import com.tangmo.zhjy.system.utils.PageInfo;
import com.tangmo.zhjy.system.utils.StringUtil;

@Service
@Transactional
public class SysCommunityInfomServiceImpl implements SysCommunityInfomService {

	@Autowired
	private SysCommunityInformBeanMapper appCommunityInformBeanMapper;

	@Autowired
	private SysCommunityInformAppUserBeanMapper appCommunityInformAppUserBeanMapper;

	@Autowired
	private SysCommunityUserBeanMapper appCommunityUserBeanMapper;

	Logger logger = Logger.getLogger(SysCommunityInfomServiceImpl.class);
	/* (非 Javadoc)   
	 * <p>Title: saveCommunityInform</p>   
	 * <p>Description: </p>   
	 * @param appCommunityInformDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#saveCommunityInform(com.tangmo.zhjy.modules.app.dto.AppCommunityInformDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result saveCommunityInform(SysCommunityInformDto appCommunityInformDto){
		try {
			SysCommunityInformBean appCommunityInformBean = new SysCommunityInformBean(appCommunityInformDto.getTitle(),
					appCommunityInformDto.getCover(),
					appCommunityInformDto.getIsChecked(),
					appCommunityInformDto.getCommunityId(),
					appCommunityInformDto.getContent());
			appCommunityInformBeanMapper.insert(appCommunityInformBean);

			//查询所有关注的用户
			List<SysCommunityUserBean> list=appCommunityUserBeanMapper.findById(appCommunityInformDto.getCommunityId());
			List<SysCommunityInformAppUserBean>  commInfoApps = new ArrayList<SysCommunityInformAppUserBean>();
			if(list.size()>0){
				for (SysCommunityUserBean appCommunityUserBean : list) {
					SysCommunityInformAppUserBean appCommunityInformAppUserBean = new SysCommunityInformAppUserBean();
					appCommunityInformAppUserBean.setAppCommunityInform(appCommunityInformBean.getId());
					appCommunityInformAppUserBean.setAppUserId(appCommunityUserBean.getUserId());
					commInfoApps.add(appCommunityInformAppUserBean);
				}
				appCommunityInformAppUserBeanMapper.insert(commInfoApps);
			}
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("添加社区消息异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}
	/* (非 Javadoc)   
	 * <p>Title: modifyCommunityInform</p>   
	 * <p>Description: </p>   
	 * @param appCommunityInformDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#modifyCommunityInform(com.tangmo.zhjy.modules.app.dto.AppCommunityInformDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modifyCommunityInform(SysCommunityInformDto appCommunityInformDto){

		try {
			if(appCommunityInformBeanMapper.selectByPrimaryKey(appCommunityInformDto.getId())!=null){

				SysCommunityInformBean appCommunityInformBean = new SysCommunityInformBean(appCommunityInformDto.getId(),
						appCommunityInformDto.getTitle(),
						appCommunityInformDto.getCover(),
						appCommunityInformDto.getIsChecked(),
						appCommunityInformDto.getIsShow(),
						appCommunityInformDto.getCommunityId(),
						appCommunityInformDto.getContent());
				appCommunityInformBeanMapper.updateByPrimaryKeySelective(appCommunityInformBean);
				return new Result(ResultCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.info("修改社区消息异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
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
	public Result findByPage(String title,Integer pageNo,Integer pageSize,Integer sysUserId){
		try {
			if(title!=null && !StringUtil.isEmpty(title)){
				title="%"+title+"%";
			}else{
				title="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			//List<SysCommunityInformBean> appCommunityInformBeans=appCommunityInformBeanMapper.findByPage(title);
			List<SysCommunityInformBean> appCommunityInformBeans=appCommunityInformBeanMapper.findByPageSysUserId(title,sysUserId);
			//查询每一个通知的总人数和总阅读数
			for (SysCommunityInformBean appCommunityInformBean : appCommunityInformBeans) {
				SysCommunityInformAppUserBean communityInformAppUserBean=appCommunityInformAppUserBeanMapper.queryTotalAndRedTotal(appCommunityInformBean.getId());
				appCommunityInformBean.setTotal(communityInformAppUserBean.getTotal());
				appCommunityInformBean.setReadTotal(communityInformAppUserBean.getRedTotal());
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appCommunityInformBeans));
		} catch (Exception e) {
			logger.info("分页查询社区通知接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public Result delCommunityInfom(Integer id){
		try {
			//根据社区通知id先删除社区用户通知已读未读表
			appCommunityInformAppUserBeanMapper.deleteByCommunityInform(id);
			appCommunityInformBeanMapper.deleteByPrimaryKey(id);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("彻底删除社区通知出现异常："+e);
			e.printStackTrace();
			return new Result(ResultCode.WEAK_NET_WORK);
		}

	}




}
