package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.dto.AppInformationDto;

public interface AppInformationService {

	/**
	 * 1.添加文章
	 */
	@SuppressWarnings("rawtypes")
	Result save(AppInformationDto appInformationDto);

	/**
	 * 2.修改+概念删除文章
	 */
	@SuppressWarnings("rawtypes")
	Result modify(AppInformationDto appInformationDto);

	//获取我发布的文章
	Result getInformationById(Integer userId,Integer start,Integer end);

	//删除我发布的文章
    Result delInformationById(Integer id);

    //热点推荐
    Result getHotInformation(Integer pageNo,Integer pageSize);

	/**
	 * 3.根据编号查找
	 */
	@SuppressWarnings("rawtypes")
	Result findById(Integer id);

	/**
	 * 4.后台分页查询
	 */
	@SuppressWarnings("rawtypes")
	Result sysFindPage(String name, Integer pageNo, Integer pageSize,Integer classifyId);

	/**
	 * 5.前台分页查询
	 */
	@SuppressWarnings("rawtypes")
	Result appFindPage(Integer id,String name, Integer pageNo, Integer pageSize);
	
	
	/**
	 * 6.根据社区通知编号查找未读用户信息
	 */
	@SuppressWarnings("rawtypes")
	Result findByCommunityInformId(Integer communityInformId);
	
	/**
	 * 
	* @Title: findClassifyId   
	* @Description: TODO(7.根据一级分类查询所有二级分类文章)   
	* @param @param classifId
	* @param @return    设定文件   
	* @return Result    返回类型   
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	Result findClassifyId(Integer classifId,Integer pageNo,Integer pageSize,String title);


	Result findClassImgById(Integer classifId);

    /**
     *
     * @Title: findTwoClassifyId
     * @Description: TODO(7.根据二级分类id查询所有二级分类文章)
     * @param @param twoclassifId
     * @param @return    设定文件
     * @return Result    返回类型
     * @throws
     */
    @SuppressWarnings("rawtypes")
	Result findTwoClassifyId(Integer twoclassifId,Integer pageNo,Integer pageSize,String title);

    Result findTwoClassifImgById(Integer twoclassifId);
	
	/**
	 * 8.根据用户Id查询浏览记录
	 * 
	 */
	@SuppressWarnings("rawtypes")
	Result queryBrowsingHistory(Integer userId,Integer pageNo,Integer pageSize);

	//清空游览记录
    Result ClearBrowsingHistory(Integer userId);
	
	/**
	 * 9.根据用户编号查找收藏文章
	* @Title: findByUserId   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param userId
	* @param @return    设定文件   
	* @return Result    返回类型   
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	Result findByUserId(Integer userId,Integer pageNo,Integer pageSize);
}