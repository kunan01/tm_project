/**     
* @Title: AppInformationController.java   
* @Package com.tangmo.zhjy.modules.app.controller   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月18日 上午3:30:37   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.modules.controller;

import javax.validation.Valid;

import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.dto.SysInformationDto;
import com.tangmo.zhjy.system.modules.service.SysInformationService;
import com.tangmo.zhjy.system.utils.ValidationUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月18日 上午3:30:37
 */
@RestController
@RequestMapping("/app/information")
@Api("发布办事、百科接口")
public class SysInformationController extends BaseController {
	@Autowired
	private SysInformationService appInformationServiceImpl;

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="增加文章",notes="")
	@PostMapping(value="/addInformation")
	public Result addInformation(@Valid @RequestBody SysInformationDto SysInformationDto,BindingResult error){
		ValidationUtil.verifyDispose(error);
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("增加文章");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appInformationServiceImpl.save(SysInformationDto);
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="修改文章",notes="")
	@PutMapping(value="/addInformation")
	public Result modifyInfomation(@Valid @RequestBody SysInformationDto SysInformationDto,BindingResult error){
		ValidationUtil.verifyDispose(error);
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("修改文章");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appInformationServiceImpl.modify(SysInformationDto);
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据文章ID查找",notes="")
	@GetMapping(value="/findById/{id:\\d+}")
	public Result findById(@PathVariable(name="id") Long id){
		return appInformationServiceImpl.findById(id.intValue());
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据文章ID彻底删除办事指南",notes="")
	@DeleteMapping(value="/delById/{id:\\d+}")
	public Result delById(@PathVariable(name="id") Long id){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("根据文章ID彻底删除办事指南");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appInformationServiceImpl.delById(id.intValue());
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="后台分页查询",notes="后台分页查询是没有进行某种排序")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name",value="模糊查询参数",dataType="string",required=false,paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="classifyId",value="一级分类ID",dataType="int",required=true,paramType="query"),
		@ApiImplicitParam(name="sysUserId",value="管理员id",dataType="int",required=false,paramType="query")
	})
	@GetMapping(value="/sysFindPage")
	public Result sysFindPage(String name,Integer pageNo,Integer pageSize,Integer classifyId,Integer sysUserId){
		return appInformationServiceImpl.sysFindPage(name, pageNo, pageSize,classifyId,sysUserId);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="后台分页二级查询",notes="后台分页查询是没有进行某种排序")
	@ApiImplicitParams({
			@ApiImplicitParam(name="name",value="模糊查询参数",dataType="string",required=false,paramType="query"),
			@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
			@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query"),
			@ApiImplicitParam(name="twoClassifyId",value="二级分类ID",dataType="int",required=false,paramType="query"),
			@ApiImplicitParam(name="sysUserId",value="管理员id",dataType="int",required=false,paramType="query")
	})
	@GetMapping(value="/sysFindPageNameType")
	public Result sysFindPageNameType(String name,Integer pageNo,Integer pageSize,Integer twoClassifyId,Integer sysUserId){
		return appInformationServiceImpl.sysFindPageNameType(name, pageNo, pageSize,twoClassifyId,sysUserId);
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
    @ApiOperation(value="后台查询未审核的文章列表",notes="后台查询未审核的文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title",value="模糊查询参数,必填：false",dataType="string",required=false,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="当前页,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="显示多少条参数,必填：false",dataType="int",required=false,paramType="query")
    })
    @GetMapping(value="/getInfoCheckedList")
    public Result getInfoCheckedList(String title,Integer pageNo,Integer pageSize){
        return appInformationServiceImpl.getInfoCheckedList(title,pageNo, pageSize);
    }


    @SuppressWarnings("rawtypes")
    @ApiOperation(value="后台审核文章",notes="后台审核文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="文章id,必填：true",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="state",value="审核状态 1通过，2驳回,必填：true",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value="/updInfoCheckedById")
    public Result updInfoCheckedById(Integer id,Integer state){
        return appInformationServiceImpl.updInfoCheckedById(id,state);
    }

}
