package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.dto.SysCommunityDto;

public interface SysCommunityService {

	/**
	 * 1.添加社区
	 */
	@SuppressWarnings("rawtypes")
	Result saveCommunity(SysCommunityDto appCommunityDto);

	/**
	 * 2.修改并且删除社区信息
	 */
	@SuppressWarnings("rawtypes")
	Result modify(SysCommunityDto appCommunityDto);

	/**
	 * 2.彻底删除社区信息
	 */
	@SuppressWarnings("rawtypes")
	Result delById(Integer id);

	/**
	 * 3.后台分页查询+模糊查询
	 */
	@SuppressWarnings("rawtypes")
	Result findByPage(String title, Integer pageNo, Integer pageSize,Integer userId);

	/**
	 * 5.关注社区
	 */
	@SuppressWarnings("rawtypes")
	Result addAttention(Integer userId, Integer communityId);

	/**
	 * 6.退出社区
	 */
	@SuppressWarnings("rawtypes")
	Result removeAttention(Integer userId, Integer communityId);
	@SuppressWarnings("rawtypes")
	Result findById(Integer id);
	@SuppressWarnings("rawtypes")
	Result findAttention(Integer userId);

	/**
	 * 4.查询所有社区
	 */
	@SuppressWarnings("rawtypes")
	Result queryAll(Integer userId);



}