package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.dto.SysTwoClassifyDto;

public interface SysTwoClassifyService {

	/**
	 * 1.增加分类
	 */
	@SuppressWarnings("rawtypes")
	Result saveTwoClassify(SysTwoClassifyDto SysTwoClassifyDto);

	/**
	 * 2.修改、概念删除分类信息
	 */
	@SuppressWarnings("rawtypes")
	Result modify(SysTwoClassifyDto SysTwoClassifyDto);

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

	/**
	 * 5.根据twoClassifyId彻底删除二级菜单下的所有信息
	 */
	@SuppressWarnings("rawtypes")
	Result delByTwoClassifyId(Integer TwoClassifyId);

}