/**     
 * @Title: AppSlideServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.app.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月16日 下午5:45:07   
 * @version V1.0     
 */   
package com.tangmo.zhjy.app.modules.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.AppSlideBean;
import com.tangmo.zhjy.app.modules.dao.AppSlideBeanMapper;
import com.tangmo.zhjy.app.modules.dto.AppSlideBeanDto;
import com.tangmo.zhjy.app.modules.service.AppSlideService;

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
 * @Date : 2018年1月16日 下午5:45:07
 */
@Service
@Transactional
public class AppSlideServiceImpl implements AppSlideService {

	@Autowired
	private AppSlideBeanMapper appSlideBeanMapper;

	Logger logger = Logger.getLogger(AppSlideServiceImpl.class);

	/* (非 Javadoc)   
	 * <p>Title: addSlide</p>   
	 * <p>Description: </p>   
	 * @param appSlideBeanDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppSlideService#addSlide(com.tangmo.zhjy.modules.app.dto.AppSlideBeanDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result addSlide(AppSlideBeanDto appSlideBeanDto){
		try {
			AppSlideBean appSlideBean = new AppSlideBean(appSlideBeanDto.getTitle(),appSlideBeanDto.getUrl(),appSlideBeanDto.getRank(),appSlideBeanDto.getFreeze(),appSlideBeanDto.getInformationId());
			appSlideBeanMapper.insert(appSlideBean);
		} catch (Exception e) {
			logger.info("添加轮播图接口异常:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		return new Result(ResultCode.SUCCESS);

	}

	/* (非 Javadoc)   
	 * <p>Title: modify</p>   
	 * <p>Description: </p>   
	 * @param appSlideBeanDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppSlideService#modify(com.tangmo.zhjy.modules.app.dto.AppSlideBeanDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modify(AppSlideBeanDto appSlideBeanDto){
		try {
			AppSlideBean appSlideBean = new AppSlideBean(appSlideBeanDto.getId(),appSlideBeanDto.getTitle(),appSlideBeanDto.getUrl(),appSlideBeanDto.getIsShow(),appSlideBeanDto.getRank(),appSlideBeanDto.getFreeze(),appSlideBeanDto.getInformationId());
			appSlideBeanMapper.updateByPrimaryKeySelective(appSlideBean);
		} catch (Exception e) {
			logger.info("修改轮播图接口异常:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);

		}
		return new Result(ResultCode.SUCCESS);
	}

	/* (非 Javadoc)   
	 * <p>Title: findByPage</p>   
	 * <p>Description: </p>   
	 * @param roleName
	 * @param pageNo
	 * @param pageSize
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppSlideService#findByPage(java.lang.String, java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByPage(String roleName, Integer pageNo, Integer pageSize){

		try {
			if(roleName!=null && !StringUtil.isEmpty(roleName)){
				roleName="%"+roleName+"%";
			}else{
				roleName="%%";
			}
			PageHelper.startPage(1, 5);
			return new Result(ResultCode.SUCCESS,new PageInfo(appSlideBeanMapper.findByPage(roleName)));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("轮播图分页查询异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/**
	 * 根据编号查询轮播图是否存在
	 */
	public AppSlideBean findById(int id){
		try {
			return appSlideBeanMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			return null;
		}
	}
}
