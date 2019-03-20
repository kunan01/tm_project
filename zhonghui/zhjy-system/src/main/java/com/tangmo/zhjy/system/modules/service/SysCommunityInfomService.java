package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.dto.SysCommunityInformDto;

public interface SysCommunityInfomService {

	/**
	 * 1.增加通知
	 */
	@SuppressWarnings("rawtypes")
	Result saveCommunityInform(SysCommunityInformDto appCommunityInformDto);

	/**
	 * 1.彻底删除通知
	 */
	@SuppressWarnings("rawtypes")
	Result delCommunityInfom(Integer id);

	/**
	 * 2.修改通知
	 */
	@SuppressWarnings("rawtypes")
	Result modifyCommunityInform(SysCommunityInformDto appCommunityInformDto);

	/**
	 * 3.分页查询通知 
	 */
	@SuppressWarnings("rawtypes")
	Result findByPage(String title, Integer pageNo, Integer pageSize,Integer sysUserId);



}