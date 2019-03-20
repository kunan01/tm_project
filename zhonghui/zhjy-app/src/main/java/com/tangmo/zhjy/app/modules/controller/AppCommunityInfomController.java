/**     
 * @Title: AppCommunityInfomController.java   
 * @Package com.tangmo.zhjy.modules.app.controller   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月19日 上午6:31:17   
 * @version V1.0     
 */   
package com.tangmo.zhjy.app.modules.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.service.AppCommunityInfomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月19日 上午6:31:17
 */
@RestController
@RequestMapping("/app/communityInfor")
@Api("社区通知模块")
public class AppCommunityInfomController {

	@Autowired
	private AppCommunityInfomService appCommunityInfomServiceImpl;

	@Value("${app.upload-community-cover}")
	private String community_cover;


	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据社区编号查找所有信息",notes="")
	@GetMapping(value="/findByCommunityId/{communityId}")
	public Result findByCommunityId(@PathVariable Integer communityId) {
		return  appCommunityInfomServiceImpl.findByCommunityId(communityId);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="获取所有社区通知",notes="获取所有社区通知")
	@ApiImplicitParams({
			@ApiImplicitParam(name="name",value="模糊查询参数,必填：false",dataType="string",required=false,paramType="query"),
            @ApiImplicitParam(name="userId",value="用户id,必填：true",dataType="string",required=true,paramType="query"),
			@ApiImplicitParam(name="pageNo",value="当前页,必填：true",dataType="int",required=true,paramType="query"),
			@ApiImplicitParam(name="pageSize",value="显示多少条参数,必填：true",dataType="int",required=true,paramType="query")
	})
	@GetMapping(value="/findCommunityInfom")
	public Result findCommunityInfom(String name,Integer userId,Integer pageNo,Integer pageSize){
		return appCommunityInfomServiceImpl.findByPage(name,userId, pageNo, pageSize);
	}

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="获取我的社区通知",notes="获取我的社区通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name="type",value="0:已关注的所有 1已关注的未读,必填：true",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="userId",value="用户id,必填：true",dataType="string",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="当前页,必填：true",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="显示多少条参数,必填：true",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value="/findCommunityInfomByUserId")
    public Result findCommunityInfomByUserId(Integer type,Integer userId,Integer pageNo,Integer pageSize){
        return appCommunityInfomServiceImpl.findCommunityInfomByUserId(type,userId, pageNo, pageSize);
    }


    @SuppressWarnings("rawtypes")
    @ApiOperation(value="通过社区通知id获取通知详情",notes="通过社区通知id获取通知详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="模糊查询参数,必填：false",dataType="int",required=false,paramType="query"),
    })
    @GetMapping(value="/findCommunityInfomById")
    public Result findCommunityInfomById(Integer id){
        return appCommunityInfomServiceImpl.findCommunityInfomById(id);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="app获取获取通知详情",notes="通过社区通知id获取通知详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="社区id,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="userId",value="用户id,必填：false",dataType="int",required=false,paramType="query")
    })
    @GetMapping(value="/getCommunityInfomById")
    public Result getCommunityInfomById(Integer id,Integer userId){
        return appCommunityInfomServiceImpl.getCommunityInfomById(id,userId);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="获取未读通知数量",notes="通过社区通知id获取通知详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id,必填：false",dataType="int",required=false,paramType="query")
    })
    @GetMapping(value="/getUnreadCount")
    public Result getUnreadCount(Integer userId){
        return appCommunityInfomServiceImpl.getUnreadCount(userId);
    }
}
