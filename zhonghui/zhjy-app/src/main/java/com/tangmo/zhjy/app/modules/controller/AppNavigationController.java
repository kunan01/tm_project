/**     
* @Title: AppNavigationController.java   
* @Package com.tangmo.zhjy.modules.app.controller   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月17日 下午4:41:19   
* @version V1.0     
*/   
package com.tangmo.zhjy.app.modules.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.service.AppNavigationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Description : TODO(app首页导航菜单控制类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月17日 下午4:41:19
 */
@RestController
@RequestMapping("/app/navigation")
@Api("导航菜单接口")
public class AppNavigationController {
	
	@Autowired
	private AppNavigationService appNavigationServiceImpl;
	
	@Value("${app.upload-navigation-path}")
	private String terraceProperties;
	
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="首页展示导航",notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=true,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=true,paramType="query")
	})
	@GetMapping(value="/appShow")
	public Result appShow(Integer pageNo,Integer pageSize){
		return appNavigationServiceImpl.appShow(pageNo, pageSize);
	}

}
