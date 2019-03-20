package com.tangmo.zhjy.app.modules.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.AppBrowsingHistoryBean;
import com.tangmo.zhjy.app.modules.bean.AppInformationPageView;
import com.tangmo.zhjy.app.modules.dao.AppBrowsingHistoryBeanMapper;
import com.tangmo.zhjy.app.modules.dao.AppInformationPageViewMapper;
import com.tangmo.zhjy.app.modules.dto.AppInformationPageViewDto;
import com.tangmo.zhjy.app.modules.service.AppInformationPageViewService;

@Service
@Transactional
public class AppInformationPageViewServiceImpl implements AppInformationPageViewService {
	
	@Autowired
	private AppInformationPageViewMapper appInformationPageViewMapper;
	
	private Logger logger = Logger.getLogger(AppInformationPageViewServiceImpl.class);
	
	@Autowired
	private AppBrowsingHistoryBeanMapper appBrowsingHistoryBeanMapper;
	/* (非 Javadoc)   
	* <p>Title: save</p>   
	* <p>Description: </p>   
	* @param appInformationPageViewDto
	* @return   
	* @see com.tangmo.zhjy.modules.app.service.impl.AppInformationPageViewService#save(com.tangmo.zhjy.modules.app.dto.AppInformationPageViewDto)   
	*/  
	@SuppressWarnings("rawtypes")
	@Override
	public Result save(AppInformationPageViewDto appInformationPageViewDto){
		try {
			int appInfoId= appInformationPageViewDto.getAppInfoId();
			int userId = appInformationPageViewDto.getAppUserid();
			AppInformationPageView appInformationPageView = new AppInformationPageView(appInfoId,userId);
			AppBrowsingHistoryBean appBrowsingHistoryBean = new AppBrowsingHistoryBean(userId, appInfoId);
			appInformationPageViewMapper.insert(appInformationPageView);
			appBrowsingHistoryBeanMapper.insert(appBrowsingHistoryBean);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("添加访问文章次数接口异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}
}
