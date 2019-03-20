/**     
 * @Title: AppCommunityController.java   
 * @Package com.tangmo.zhjy.modules.app.controller   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月19日 上午3:03:28   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.controller;

import static com.tangmo.zhjy.system.utils.FileUploadUtil.upload;

import javax.validation.Valid;

import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.dto.SysCommunityDto;
import com.tangmo.zhjy.system.modules.service.SysCommunityService;
import com.tangmo.zhjy.system.utils.ValidationUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 *
 * @Description : TODO(社区控制层)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月19日 上午3:03:28
 */
@RestController
@RequestMapping("/app/community")
@Api("社区模块所有接口")
public class SysCommunityController extends BaseController {

	@Autowired
	private SysCommunityService appCommunityServiceImpl;
	
	@Value("${app.upload-logo-path}")
	private String webUploadPath;
	
	/**
	 * 1.增加社区
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="添加社区",notes="除了隐藏属性全部必填")
	@PostMapping(value="/saveCommunity")
	public Result saveCommunity(@Valid @RequestBody SysCommunityDto appCommunityDto,BindingResult error){
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
		logTable.setParam("添加社区");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appCommunityServiceImpl.saveCommunity(appCommunityDto);
	}
	/**
	 * 2.修改社区信息
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="修改社区信息",notes="")
	@PutMapping(value="/updateCommunity")
	public Result modifyCommunity(@RequestBody SysCommunityDto appCommunityDto){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("修改社区信息");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appCommunityServiceImpl.modify(appCommunityDto);
	}


	/**
	 * 3.根据编号查找
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据社区编号查找",notes="")
	@GetMapping(value="/findById/{id:\\d+}")
	public Result findById(@PathVariable Integer id){
		return appCommunityServiceImpl.findById(id);
	}


	/**
	 * 3.彻底删除社区
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="彻底删除社区",notes="")
	@DeleteMapping(value="/delById/{id:\\d+}")
	public Result delById(@PathVariable Integer id){
		//生成日志
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("彻底删除社区");
		logService.addLog(logTable);
		return appCommunityServiceImpl.delById(id);
	}

	/**
	 * 4.分页查询
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页查询社区",notes="前台分页查询是根据访问量来排序")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name",value="模糊查询参数",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="sysUserId",value="管理员用户id",dataType="int",required=false,paramType="query")
	})
	@GetMapping(value="/findByPage")
	public Result appFindPage( String name, Integer pageNo, Integer pageSize, Integer sysUserId){

		return appCommunityServiceImpl.findByPage(name, pageNo, pageSize,sysUserId);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="查询所有社区",notes="isAttention=true关注 || isAttention=false 没有关注 ")
	@GetMapping(value="/queryAll/{userId:\\d+}")
	public Result queryAll(@PathVariable Integer userId){
		return appCommunityServiceImpl.queryAll(userId);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="上传Logo",notes="")
	@PostMapping(value="/uploadLogo",consumes="multipart/*",headers="content-type=multipart/form-data")
	public Result uploadLogo(@ApiParam(value="上传文件",required=true) MultipartFile file){
		//生成日志
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("上传Logo");
		logService.addLog(logTable);
		Result result =upload(file, webUploadPath);
		return result;
	}
}
