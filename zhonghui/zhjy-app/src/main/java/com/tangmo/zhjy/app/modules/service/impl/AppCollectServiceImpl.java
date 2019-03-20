/**     
 * @Title: AppCollectServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.app.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年2月8日 下午2:15:41   
 * @version V1.0     
 */   
package com.tangmo.zhjy.app.modules.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.AppCollect;
import com.tangmo.zhjy.app.modules.dao.AppCollectMapper;
import com.tangmo.zhjy.app.modules.dto.AppCollectDto;
import com.tangmo.zhjy.app.modules.service.AppCollectService;

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
 * @Date : 2018年2月8日 下午2:15:41
 */
@Service
@Transactional
public class AppCollectServiceImpl implements AppCollectService {

	@Autowired
	private AppCollectMapper appCollectMapper;

	private Logger logger = Logger.getLogger(AppCollectServiceImpl.class);

	/* (非 Javadoc)   
	 * <p>Title: save</p>   
	 * <p>Description: </p>   
	 * @param collectDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCollectService#save(com.tangmo.zhjy.modules.app.dto.AppCollectDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result save(AppCollectDto collectDto){
		try {
			AppCollect appCollectBean = new AppCollect(collectDto.getInformationid(), collectDto.getUserId());
			appCollectMapper.insert(appCollectBean);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("收藏方法异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}

	}

	/* (非 Javadoc)   
	 * <p>Title: delete</p>   
	 * <p>Description: </p>   
	 * @param collectDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCollectService#delete(com.tangmo.zhjy.modules.app.dto.AppCollectDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result delete(AppCollectDto collectDto){
		try {
			appCollectMapper.deleteByPrimaryKey(collectDto.getInformationid(), collectDto.getUserId());
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("删除收藏方法异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Result selectById(AppCollectDto collectDto) {
		try {
			AppCollect appCollectBean = new AppCollect(collectDto.getInformationid(), collectDto.getUserId());
			List<AppCollect> collects=appCollectMapper.selectByPrimaryKey(appCollectBean);
			if(appCollectBean!=null && collects.size()>0){
				return new Result(ResultCode.SUCCESS,true);
			}
			return new Result(ResultCode.SUCCESS,false);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询用户是否收藏方法异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}
}
