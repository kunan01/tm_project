package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.dto.SysNavigationDto;

public interface SysNavigationService {

	/**
	 * 1.增加导航栏菜单
	 */
	@SuppressWarnings("rawtypes")
	Result save(SysNavigationDto appNavigationDto);

	/**
	 * 2.修改导航栏菜单 && 概念删除导航栏菜单
	 */
	@SuppressWarnings("rawtypes")
	Result modify(SysNavigationDto appNavigationDto);

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
	 * 5.根据ID彻底删除导航菜单
	 */
	@SuppressWarnings("rawtypes")
	Result delNavigation(Integer id);


}