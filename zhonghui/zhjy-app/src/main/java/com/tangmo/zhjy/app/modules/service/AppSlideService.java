package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.AppSlideBean;
import com.tangmo.zhjy.app.modules.dto.AppSlideBeanDto;

public interface AppSlideService {

	/**
	 * 1.增加轮播图
	 */
	@SuppressWarnings("rawtypes")
	Result addSlide(AppSlideBeanDto appSlideBeanDto);

	/**
	 * 2.修改排序顺序、修改是否显示、删除轮播图、恢复轮播图
	 */
	@SuppressWarnings("rawtypes")
	Result modify(AppSlideBeanDto appSlideBeanDto);

	/**
	 * 3.分页模糊查询轮播图
	 */
	@SuppressWarnings("rawtypes")
	Result findByPage(String name, Integer pageNo, Integer pageSize);
	
	/**
	 * 4.根据编号查询是否存在
	* @Title: findById   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param id
	* @param @return    设定文件   
	* @return AppSlideBean    返回类型   
	* @throws
	 */
	AppSlideBean findById(int id);
	

}