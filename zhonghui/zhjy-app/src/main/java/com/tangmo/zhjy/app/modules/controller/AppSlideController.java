/**     
 * @Title: AppSlideController.java   
 * @Package com.tangmo.zhjy.modules.app.controller   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月16日 下午6:47:15   
 * @version V1.0     
 */   
package com.tangmo.zhjy.app.modules.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.AppSlideBean;
import com.tangmo.zhjy.app.modules.service.AppSlideService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Description : TODO(轮播图管理控制层)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月16日 下午6:47:15
 */
@RestController
@RequestMapping("/app/slide")
@Api(value="轮播图管理")
public class AppSlideController {

	@Autowired
	private AppSlideService appSlideServiceImpl;

	@Value("${app.slide.pageSize}")
	private String terraceProperties;

	/**
	 * 文件上传根目录
	 */
	@Value("${web.upload-path}")
	private String webUploadPath;


	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页查询",notes="模糊分页查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name",value="模糊查询参数",dataType="string",required=false,paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query"),
	})
	@GetMapping(value="/findBySlidePage")
	public Result findByPage(String name,Integer pageNo,Integer pageSize){
		//pageNo=1;
		pageSize=Integer.parseInt(terraceProperties);
		return appSlideServiceImpl.findByPage(name, pageNo, pageSize);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="根据Id查询",notes="根据轮播图数据编号查询进行修改")
	@GetMapping(value="/findSlideId/{id:\\d+}")
	public Result findById(@PathVariable Long id){
		AppSlideBean appSlideBean=appSlideServiceImpl.findById(id.intValue());
		if(appSlideBean!=null){
			return new Result(ResultCode.SUCCESS,appSlideBean);
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}

}
