/**     
* @Title: AppCLassifyController.java   
* @Package com.tangmo.zhjy.modules.app.controller   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月18日 上午2:30:42   
* @version V1.0     
*/   
package com.tangmo.zhjy.app.modules.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.service.AppClassifyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月18日 上午2:30:42
 */
@Api("APP一级菜单接口")
@RequestMapping("/app/classify")
@RestController
public class AppClassifyController {
	
	@Autowired
	private AppClassifyService appClassifyServiceImpl;
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="查询所有一级菜单")
	@GetMapping(value="/queryAll")
	public Result queryAll(){
		
		return appClassifyServiceImpl.queryAll();
	}

}
