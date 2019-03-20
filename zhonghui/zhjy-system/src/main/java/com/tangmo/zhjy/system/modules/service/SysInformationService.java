package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.dto.SysInformationDto;

public interface SysInformationService {

	/**
	 * 1.添加文章
	 */
	@SuppressWarnings("rawtypes")
	Result save(SysInformationDto sysInformationDto);

	/**
	 * 2.修改+概念删除文章
	 */
	@SuppressWarnings("rawtypes")
	Result modify(SysInformationDto sysInformationDto);

	/**
	 * 2.彻底删除文章
	 */
	@SuppressWarnings("rawtypes")
	Result delById(Integer id);

	/**
	 * 3.根据编号查找
	 */
	@SuppressWarnings("rawtypes")
	Result findById(Integer id);

	/**
	 * 4.后台分页查询
	 */
	@SuppressWarnings("rawtypes")
	Result sysFindPage(String name, Integer pageNo, Integer pageSize,Integer classifyId,Integer sysUserId);

	/**
	 * 4.后台二级栏目分页查询
	 */
	@SuppressWarnings("rawtypes")
	Result sysFindPageNameType(String name, Integer pageNo, Integer pageSize,Integer twoClassifyId,Integer sysUserId);


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


	Result getInfoCheckedList(String title,Integer pageNo,Integer pageSize);


	Result updInfoCheckedById(Integer id,Integer state);


}