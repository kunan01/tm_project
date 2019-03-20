/**     
* @Title: AppClassifyServiceImpl.java   
* @Package com.tangmo.zhjy.modules.app.service.impl   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月18日 上午2:25:08   
* @version V1.0     
*/   
package com.tangmo.zhjy.app.modules.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.dao.AppClassifyMapper;
import com.tangmo.zhjy.app.modules.service.AppClassifyService;

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
 * @Date : 2018年1月18日 上午2:25:08
 */
@Service
@Transactional
public class AppClassifyServiceImpl implements AppClassifyService {
	
	@Autowired
	private AppClassifyMapper appClassifyMapper;
	
	private Logger logger = Logger.getLogger(AppClassifyServiceImpl.class);
	
	/* (非 Javadoc)   
	* <p>Title: queryAll</p>   
	* <p>Description: </p>   
	* @return   
	* @see com.tangmo.zhjy.modules.app.service.impl.AppClassifyService#queryAll()   
	*/  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result queryAll(){
		try {
			return new Result(ResultCode.SUCCESS,appClassifyMapper.queryAll());
		} catch (Exception e) {
			logger.info("查询所有一级分类接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK,appClassifyMapper.queryAll());
		}
	}

}
