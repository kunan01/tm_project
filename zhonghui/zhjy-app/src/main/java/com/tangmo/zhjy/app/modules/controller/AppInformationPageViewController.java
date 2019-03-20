package com.tangmo.zhjy.app.modules.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.dto.AppInformationPageViewDto;
import com.tangmo.zhjy.app.modules.service.AppInformationPageViewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app/informationPageView")
@Api("添加文章访问次数")
public class AppInformationPageViewController {
	
	@Autowired
	private AppInformationPageViewService appInformationPageViewService;
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="添加用户访问那篇文章接口",notes="用来统计最热帖子然后排序")
	@PostMapping("/save")
	public Result save(@RequestBody AppInformationPageViewDto appInformationPageViewDto){
		return appInformationPageViewService.save(appInformationPageViewDto);
	}
}
