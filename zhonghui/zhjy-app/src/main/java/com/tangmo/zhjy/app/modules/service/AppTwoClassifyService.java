package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.dto.AppTwoClassifyDto;

public interface AppTwoClassifyService {

	/**
	 * 1.增加分类
	 */
	@SuppressWarnings("rawtypes")
	Result saveTwoClassify(AppTwoClassifyDto appTwoClassifyDto);

	/**
	 * 2.修改、概念删除分类信息
	 */
	@SuppressWarnings("rawtypes")
	Result modify(AppTwoClassifyDto appTwoClassifyDto);

	/**
	 * 3.根据编号查找
	 */
	@SuppressWarnings("rawtypes")
	Result findById(Integer id);

	/**
	 * 4.分页查询
	 */
	@SuppressWarnings("rawtypes")
	Result findByPage(String name, Integer pageNo, Integer pageSize);

	/**
	 * 5.根据appClassifyId查询
	 */
	@SuppressWarnings("rawtypes")
	Result findByAppClassifyId(Integer classifyId);

	Result findByAppClassById();

}