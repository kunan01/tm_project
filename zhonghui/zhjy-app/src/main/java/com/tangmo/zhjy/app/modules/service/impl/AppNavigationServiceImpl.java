/**     
 * @Title: AppNavigationServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.app.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月17日 下午1:20:21   
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
import com.tangmo.zhjy.app.modules.bean.AppNavigationBean;
import com.tangmo.zhjy.app.modules.dao.AppNavigationBeanMapper;
import com.tangmo.zhjy.app.modules.dto.AppNavigationDto;
import com.tangmo.zhjy.app.modules.service.AppNavigationService;

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
 * @Description : TODO()
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月17日 下午1:20:21
 */
@Service
@Transactional
public class AppNavigationServiceImpl implements AppNavigationService {

	@Autowired
	private AppNavigationBeanMapper appNavigationBeanMapper;

	private Logger logger = Logger.getLogger(AppNavigationServiceImpl.class);

	/* (非 Javadoc)   
	* <p>Title: save</p>   
	* <p>Description: </p>   
	* @param appNavigationDto
	* @return   
	* @see com.tangmo.zhjy.modules.app.service.impl.AppNavigationService#save(com.tangmo.zhjy.modules.app.dto.AppNavigationDto)   
	*/  
	@SuppressWarnings("rawtypes")
	@Override
	public Result save(AppNavigationDto appNavigationDto){
		try {
			AppNavigationBean appNavigationBean= new AppNavigationBean(appNavigationDto.getName(),
					appNavigationDto.getIcon(),
					appNavigationDto.getUrl(),
					appNavigationDto.getFreeze(),
					appNavigationDto.getRank());
			appNavigationBeanMapper.insert(appNavigationBean);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("增加APP导航菜单异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	* <p>Title: modify</p>   
	* <p>Description: </p>   
	* @param appNavigationDto
	* @return   
	* @see com.tangmo.zhjy.modules.app.service.impl.AppNavigationService#modify(com.tangmo.zhjy.modules.app.dto.AppNavigationDto)   
	*/  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modify(AppNavigationDto appNavigationDto){

		try {
			AppNavigationBean appNavigationBean= new AppNavigationBean(appNavigationDto.getId(),
																		appNavigationDto.getName(),
																		appNavigationDto.getIcon(),
																		appNavigationDto.getUrl(),
																		appNavigationDto.getFreeze(),
																		appNavigationDto.getRank(),
																		appNavigationDto.getIsShow());
			appNavigationBeanMapper.updateByPrimaryKeySelective(appNavigationBean);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("修改APP导航菜单异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}

	}

	/* (非 Javadoc)   
	* <p>Title: findByPage</p>   
	* <p>Description: </p>   
	* @param name
	* @param pageNo
	* @param pageSize
	* @return   
	* @see com.tangmo.zhjy.modules.app.service.impl.AppNavigationService#findByPage(java.lang.String, java.lang.Integer, java.lang.Integer)   
	*/  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByPage(String name,Integer pageNo,Integer pageSize){
		try {
			if(name!=null && !StringUtil.isEmpty(name)){
				name="%"+name+"%";
			}else{
				name="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appNavigationBeanMapper.findByPage(name)));
		} catch (Exception e) {
			logger.info("查询导航菜单分页异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	* <p>Title: findById</p>   
	* <p>Description: </p>   
	* @param id
	* @return   
	* @see com.tangmo.zhjy.modules.app.service.impl.AppNavigationService#findById(java.lang.Integer)   
	*/  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findById(Integer id){
		try {
			return new Result(ResultCode.SUCCESS,appNavigationBeanMapper.selectByPrimaryKey(id));
		} catch (Exception e) {
			logger.info("根据APP导航菜单编号查询异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	* <p>Title: appShow</p>   
	* <p>Description: </p>   
	* @param pageNo
	* @param pageSize
	* @return   
	* @see com.tangmo.zhjy.modules.app.service.impl.AppNavigationService#appShow(java.lang.Integer, java.lang.Integer)   
	*/  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result appShow(Integer pageNo,Integer pageSize){
		try {
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appNavigationBeanMapper.appShow()));
		} catch (Exception e) {
			logger.info("查询APP首页显示菜单异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}
}
