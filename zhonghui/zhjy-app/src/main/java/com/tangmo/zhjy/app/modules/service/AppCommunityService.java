package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.dto.AppCommunityDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface AppCommunityService {

	/**
	 * 1.添加社区
	 */
	@SuppressWarnings("rawtypes")
	Result saveCommunity(AppCommunityDto appCommunityDto);

	/**
	 * 2.修改并且删除社区信息
	 */
	@SuppressWarnings("rawtypes")
	Result modify(AppCommunityDto appCommunityDto);

	/**
	 * 3.后台分页查询+模糊查询
	 */
	@SuppressWarnings("rawtypes")
	Result findByPage(String title, Integer pageNo, Integer pageSize);

	/**
	 * 4.查询所有社区
	 */
	@SuppressWarnings("rawtypes")
	Result queryAll(Integer userId,Integer pageNo,Integer pageSize);

	/**
	 * 5.关注社区
	 */
	@SuppressWarnings("rawtypes")
	Result addAttention(Integer userId, Integer communityId);

	/**
	 * 6.取消关注接口
	 */
	@SuppressWarnings("rawtypes")
	Result removeAttention(Integer userId, Integer communityId);

	/**
	 * 根据社区编号查找
	 * @param id
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	Result findById(Integer id);

	/**
	 * 根据经度纬度查找附近社区
	 * @param longitude
	 * @param latitude
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	Result queryDistance(String longitude,String latitude,Integer pageNo,Integer pageSize,Integer userId,String city);

	Result queryCity(Integer userId);

	/**
	 * 查询关注社区消息未读通知
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Result findByUnreadInform(Integer userId);






	@SuppressWarnings("rawtypes")
	Result findAttention(Integer userId);

}