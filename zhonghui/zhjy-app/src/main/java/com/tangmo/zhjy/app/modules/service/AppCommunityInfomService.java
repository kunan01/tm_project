package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.dto.AppCommunityInformDto;

public interface AppCommunityInfomService {

	/**
	 * 1.增加通知
	 */
	@SuppressWarnings("rawtypes")
	Result saveCommunityInform(AppCommunityInformDto appCommunityInformDto);

	/**
	 * 2.修改通知
	 */
	@SuppressWarnings("rawtypes")
	Result modifyCommunityInform(AppCommunityInformDto appCommunityInformDto);

	/**
	 * 3.分页查询通知 
	 */
	@SuppressWarnings("rawtypes")
	Result findByPage(String title,Integer userId, Integer pageNo, Integer pageSize);

	Result findCommunityInfomByUserId(Integer type,Integer userId, Integer pageNo, Integer pageSize);
	
	/**
	 * 
	* @Title: findByCommunityId   
	* @Description: TODO(4.根据社区ID查询所有通知)   
	* @param @param communityId
	* @param @return    设定文件   
	* @return List<AppCommunityInformBean>    返回类型   
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	Result findByCommunityId(Integer communityId);


	Result findCommunityInfomById(Integer id);

	Result getCommunityInfomById(Integer id,Integer userId);

	Result getUnreadCount(Integer userId);


}