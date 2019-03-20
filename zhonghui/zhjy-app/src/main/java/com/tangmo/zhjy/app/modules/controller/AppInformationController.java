/**     
* @Title: AppInformationController.java   
* @Package com.tangmo.zhjy.modules.app.controller   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月18日 上午3:30:37   
* @version V1.0     
*/   
package com.tangmo.zhjy.app.modules.controller;

import com.tangmo.zhjy.app.modules.dto.AppInformationDto;
import com.tangmo.zhjy.app.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.service.AppInformationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

/**
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月18日 上午3:30:37
 */
@RestController
@RequestMapping("/app/information")
@Api("发布办事、百科接口")
public class AppInformationController {
	@Autowired
	private AppInformationService appInformationServiceImpl;
	

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据文章ID查找",notes="")
	@GetMapping(value="/findById/{id:\\d+}")
	public Result findById(@PathVariable(name="id") Long id){
		return appInformationServiceImpl.findById(id.intValue());
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="前台分页查询",notes="前台分页查询是根据访问量来排序")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name",value="模糊查询参数,必填：false",dataType="string",required=false,paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页,必填：false",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数,必填：false",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="twoClassifyId",value="二级菜单ID,必填：false",dataType="int",required=false,paramType="query")
	})
	@GetMapping(value="/appFindPage")
	public Result appFindPage(Integer twoClassifyId,String name,Integer pageNo,Integer pageSize){
		return appInformationServiceImpl.appFindPage(twoClassifyId, name, pageNo, pageSize);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据一级菜单编号查找文章",notes="根据一级菜单编号查找文章及其搜索")
	@ApiImplicitParams({
		@ApiImplicitParam(name="title",value="模糊查询参数,必填：false",dataType="string",required=false,paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页,必填：false",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数,必填：false",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="classifId",value="一级菜单ID,必填：true",dataType="int",required=false,paramType="query")
	})
	@GetMapping(value="/findClassifyId")
	public Result findClassifyId(Integer classifId,Integer pageNo,Integer pageSize,String title){
		return appInformationServiceImpl.findClassifyId(classifId, pageNo, pageSize, title);
	}

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="根据一级菜单编号查找精选图片",notes="根据一级菜单编号精选图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classifId",value="一级菜单ID,必填：true",dataType="int",required=false,paramType="query")
    })
    @GetMapping(value="/findClassImgById")
    public Result findClassImgById(Integer classifId){
        return appInformationServiceImpl.findClassImgById(classifId);
    }

    @ApiOperation(value="根据二级菜单编号查找文章",notes="根据二级菜单编号查找文章及其搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title",value="模糊查询参数,必填：false",dataType="string",required=false,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="当前页,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="显示多少条参数,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="twoclassifId",value="二级菜单ID,必填：true",dataType="int",required=false,paramType="query")
    })
    @GetMapping(value="/findTwoClassifyId")
    public Result findTwoClassifyId(Integer twoclassifId,Integer pageNo,Integer pageSize,String title){
        return appInformationServiceImpl.findTwoClassifyId(twoclassifId, pageNo, pageSize, title);
    }

    @ApiOperation(value="根据二级菜单编号查找精选图片",notes="根据二级菜单编号查找精选图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name="twoclassifId",value="二级菜单ID,必填：true",dataType="int",required=false,paramType="query")
    })
    @GetMapping(value="/findTwoClassifImgById")
    public Result findTwoClassifImgById(Integer twoclassifId){
        return appInformationServiceImpl.findTwoClassifImgById(twoclassifId);
    }
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="浏览记录",notes="根据用户ID获取浏览记录")
	@GetMapping(value="/queryBrowsingHistory/{userId}/{pageNo}/{pageSize}")
	public Result queryBrowsingHistory(@PathVariable Integer userId,@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		return appInformationServiceImpl.queryBrowsingHistory(userId,pageNo,pageSize);
	}

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="清空浏览记录",notes="根据用户ID获取浏览记录")
    @GetMapping(value="/ClearBrowsingHistory/{userId}")
    public Result ClearBrowsingHistory(@PathVariable Integer userId){
        return appInformationServiceImpl.ClearBrowsingHistory(userId);
    }

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="增加文章",notes="")
	@PostMapping(value="/addInformation")
	public Result addInformation(@Valid @RequestBody AppInformationDto AppInformationDto, BindingResult error){
		ValidationUtil.verifyDispose(error);
		return appInformationServiceImpl.save(AppInformationDto);
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="修改文章",notes="")
	@PutMapping(value="/updateInformation")
	public Result modifyInfomation(@Valid @RequestBody AppInformationDto AppInformationDto,BindingResult error){
		ValidationUtil.verifyDispose(error);
		return appInformationServiceImpl.modify(AppInformationDto);
	}

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="获取我发布的文章",notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id,必填：false",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="start",value="页数,必填：false",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="end",value="每页显示条数,必填：false",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value="/getInformationById")
    public Result getInformationById(Integer userId,Integer start,Integer end){
        return appInformationServiceImpl.getInformationById(userId,start,end);
    }


    @SuppressWarnings("rawtypes")
    @ApiOperation(value="删除我发布的文章",notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="文章id,必填：true",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value="/delInformationById")
    public Result delInformationById(Integer id){
        return appInformationServiceImpl.delInformationById(id);
    }


    @SuppressWarnings("rawtypes")
    @ApiOperation(value="热点推荐",notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNo",value="当前页,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="显示多少条参数,必填：false",dataType="int",required=false,paramType="query")
    })
    @GetMapping(value="/getHotInformation")
    public Result getHotInformation(Integer pageNo,Integer pageSize){
        return appInformationServiceImpl.getHotInformation(pageNo,pageSize);
    }

}
