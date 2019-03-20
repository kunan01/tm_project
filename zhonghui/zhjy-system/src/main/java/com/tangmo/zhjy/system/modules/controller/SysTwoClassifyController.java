package com.tangmo.zhjy.system.modules.controller;

import static com.tangmo.zhjy.system.utils.FileUploadUtil.upload;

import javax.validation.Valid;

import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.dto.SysTwoClassifyDto;
import com.tangmo.zhjy.system.modules.service.SysTwoClassifyService;
import com.tangmo.zhjy.system.utils.ValidationUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/app/twoClassify")
@Api(value="APP二级分类接口",description="办事、百科二级分类接口")
public class SysTwoClassifyController extends BaseController {
	
	@Autowired
	private SysTwoClassifyService  appTwoClassifyServiceImpl;
	
	
	/**
	 * 文件上传根目录
	 */
	@Value("${app.upload-icon-path}")
	private String webUploadPath;
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="增加二级分类",notes="")
	@PostMapping(value="/addTwoClassify")
	public Result save(@Valid @RequestBody SysTwoClassifyDto SysTwoClassifyDto,BindingResult error){
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
		logTable.setParam("增加二级分类");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appTwoClassifyServiceImpl.saveTwoClassify(SysTwoClassifyDto);
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="修改二级分类",notes="修改菜单+概念删除")
	@PutMapping(value="/modifyTwoClassify")
	public Result modify(@Valid @RequestBody SysTwoClassifyDto SysTwoClassifyDto,BindingResult error){
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
		logTable.setParam("修改二级分类");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appTwoClassifyServiceImpl.modify(SysTwoClassifyDto);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页查询",notes="模糊分页查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name",value="模糊查询参数",dataType="string",required=false,paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query")
	})
	@GetMapping(value="/findByPage")
	public Result findByPage(String name,Integer pageNo,Integer pageSize){
		return appTwoClassifyServiceImpl.findByPage(name, pageNo, pageSize);
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据classifyId查询",notes="根据一级菜单ID查找二级菜单")
	@GetMapping(value="/findByAppClassifyId/{classifyId:\\d+}")
	public Result findByAppClassifyId(@PathVariable Long classifyId){
		return appTwoClassifyServiceImpl.findByAppClassifyId(classifyId.intValue());
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="上传二级分类ICON图标",notes="")
	@PostMapping(value="/twoClassifyIconUpload",consumes="multipart/*",headers="content-type=multipart/form-data")
	public Result uploadIcon(@ApiParam(value="上传文件",required=true) MultipartFile file){
		Result result =upload(file, webUploadPath);
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("上传二级分类ICON图标");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据二级菜单id彻底删除有关所有信息",notes="")
	@DeleteMapping(value="/delByTwoClassifyId/{twoClassifyId:\\d+}")
	public Result delByTwoClassifyId(@PathVariable Long twoClassifyId){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("根据二级菜单id彻底删除有关所有信息");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appTwoClassifyServiceImpl.delByTwoClassifyId(twoClassifyId.intValue());
	}
	

}
