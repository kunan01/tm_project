/**     
 * @Title: AppCommunityInfomController.java   
 * @Package com.tangmo.zhjy.modules.app.controller   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月19日 上午6:31:17   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.controller;

import static com.tangmo.zhjy.system.utils.FileUploadUtil.upload;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
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
import com.tangmo.zhjy.system.modules.dto.SysCommunityInformDto;
import com.tangmo.zhjy.system.modules.service.SysCommunityInfomService;
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
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月19日 上午6:31:17
 */
@RestController
@RequestMapping("/app/communityInfor")
@Api("社区通知模块")
public class SysCommunityInfomController extends BaseController {

	@Autowired
	private SysCommunityInfomService appCommunityInfomServiceImpl;

	@Value("${app.upload-community-cover}")
	private String community_cover;

	/**
	 * 1.添加社区通知
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="添加社区通知",notes="")
	@PostMapping(value="/saveCommunityInfom")
	public Result saveAppCommunity(@Valid @RequestBody SysCommunityInformDto appCommunityInformDto){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("添加社区通知");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appCommunityInfomServiceImpl.saveCommunityInform(appCommunityInformDto);
	}

	/**
	 * 1.彻底删除社区通知
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="彻底删除社区通知",notes="")
	@DeleteMapping(value="/delCommunityInfom/{id:\\d+}")
	public Result delCommunityInfom(@PathVariable(name="id") Integer id){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("彻底删除社区通知");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appCommunityInfomServiceImpl.delCommunityInfom(id);
	}


	/**
	 * 2.修改社区通知
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="修改社区通知信息",notes="")
	@PostMapping(value="/modifyCommunityInfom")
	public Result modifyCommunityInfom(@Valid @RequestBody SysCommunityInformDto appCommunityInformDto,BindingResult error,HttpServletRequest request){
		//生成日志
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("修改社区通知信息");
		logService.addLog(logTable);
		ValidationUtil.verifyDispose(error);
		Enumeration<String> headerNames = request.getHeaderNames();
		//获取获取的消息头名称，获取对应的值，并输出
		while(headerNames.hasMoreElements()){
			String nextElement = headerNames.nextElement();
			System.out.println(nextElement+":"+request.getHeader(nextElement));
		}
		return appCommunityInfomServiceImpl.modifyCommunityInform(appCommunityInformDto);
	}

	/**
	 * 3.分页查询社区通知
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页查询通知信息",notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name",value="模糊查询参数",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="sysUserId",value="用户管理员id",dataType="int",required=true,paramType="query")
	})
	@GetMapping(value="/findByPage")
	public Result findByPage(String name,Integer pageNo,Integer pageSize,Integer sysUserId){
		return appCommunityInfomServiceImpl.findByPage(name, pageNo, pageSize,sysUserId);
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="上传社区通知封面图",notes="")
	@PostMapping(value="/fileUpload",consumes="multipart/*",headers="content-type=multipart/form-data")
	public Result uploadCover(@ApiParam(value="上传封面",required=true) MultipartFile file){
		//生成日志
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("上传社区通知封面图");
		logService.addLog(logTable);
		Result result =upload(file, community_cover);
		return result;
	}


}
