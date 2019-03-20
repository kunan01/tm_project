package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.dto.AppNavigationDto;

public interface AppNavigationService {

	/**
	 * 1.增加导航栏菜单
	 */
	@SuppressWarnings("rawtypes")
	Result save(AppNavigationDto appNavigationDto);

	/**
	 * 2.修改导航栏菜单 && 概念删除导航栏菜单
	 */
	@SuppressWarnings("rawtypes")
	Result modify(AppNavigationDto appNavigationDto);

	/**
	 * 3.分页查询展示
	 */
	@SuppressWarnings("rawtypes")
	Result findByPage(String name, Integer pageNo, Integer pageSize);

	/**
	 * 4.根据ID查找
	 */
	@SuppressWarnings("rawtypes")
	Result findById(Integer id);

	/**
	 * 5.查询所有展示菜单
	 */
	@SuppressWarnings("rawtypes")
	Result appShow(Integer pageNo, Integer pageSize);

}