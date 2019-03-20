package com.tangmo.zhjy.app.modules.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.service.AppTwoClassifyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app/twoClassify")
@Api(value="APP二级分类接口",description="办事、百科二级分类接口")
public class AppTwoClassifyController {

	@Autowired
	private AppTwoClassifyService  appTwoClassifyServiceImpl;


	/**
	 * 文件上传根目录
	 */
	@Value("${app.upload-icon-path}")
	private String webUploadPath;


	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据Id查找",notes="根据Id查找二级分类")
	@GetMapping(value="/findBytwoClassifyId/{id:\\d+}")
	public Result findById(@PathVariable Long id){
		return appTwoClassifyServiceImpl.findById(id.intValue());
	}


	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据classifyId查询",notes="根据一级菜单ID查找二级菜单")
	@GetMapping(value="/findByAppClassifyId/{classifyId:\\d+}")
	public Result findByAppClassifyId(@PathVariable Long classifyId){
		return appTwoClassifyServiceImpl.findByAppClassifyId(classifyId.intValue());
	}

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="查询除办事指南的所有二级分类",notes="查询除办事指南的所有二级分类")
    @GetMapping(value="/findByAppClassById")
    public Result findByAppClassById(){
        return appTwoClassifyServiceImpl.findByAppClassById();
    }

}
