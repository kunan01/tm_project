/**     
* @Title: AppNavigationController.java   
* @Package com.tangmo.zhjy.modules.app.controller   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月17日 下午4:41:19   
* @version V1.0     
*/   
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
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.dto.SysNavigationDto;
import com.tangmo.zhjy.system.modules.service.SysNavigationService;
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
 * @Description : TODO(app首页导航菜单控制类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月17日 下午4:41:19
 */
@RestController
@RequestMapping("/app/navigation")
@Api("导航菜单接口")
public class SysNavigationController extends BaseController {
	
	@Autowired
	private SysNavigationService appNavigationServiceImpl;
	
	@Value("${app.upload-navigation-path}")
	private String terraceProperties;
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="添加导航菜单",notes="")
	@PostMapping(value="/addNavigation")
	public Result saveNavigation(@Valid @RequestBody SysNavigationDto appNavigationDto,BindingResult error){
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
		logTable.setParam("添加导航菜单");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appNavigationServiceImpl.save(appNavigationDto);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="彻底删除首页导航菜单",notes="")
	@DeleteMapping(value="/delNavigation/{id:\\d+}")
	public Result delNavigation(@PathVariable Long id){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("彻底删除首页导航菜单");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return appNavigationServiceImpl.delNavigation(id.intValue());
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="修改导航菜单",notes="如果要恢复菜单：隐藏属性isShow:0显示、1隐藏")
	@PutMapping(value="/modifyNavigation")
	public Result modifyNavigation(@Valid @RequestBody SysNavigationDto appNavigationDto,BindingResult error){
		ValidationUtil.verifyDispose(error);
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setMethodUrl(ip);
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("修改导航菜单");
		logService.addLog(logTable);
		if(appNavigationServiceImpl.findById(appNavigationDto.getId()==null?0:appNavigationDto.getId()).getData()!=null){
			return appNavigationServiceImpl.modify(appNavigationDto);
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页查询",notes="模糊分页查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name",value="模糊查询参数",dataType="string",required=false,paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query")
	})
	@GetMapping(value="/findByNavigationPage")
	public Result findByPage(String name,Integer pageNo,Integer pageSize){
		return appNavigationServiceImpl.findByPage(name, pageNo, pageSize);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据编号查找",notes="")
	@GetMapping(value="/findById/{id:\\d+}")
	public Result findById(@PathVariable Long id){
		Result result= appNavigationServiceImpl.findById(id.intValue());
		if(result.getData()!=null){
			return result;
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}
	

	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="上传导航LOGO",notes="")
	@PostMapping(value="/fileUpload",consumes="multipart/*",headers="content-type=multipart/form-data")
	public Result fileUpload(@ApiParam(value="上传文件",required=true) MultipartFile file){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("上传导航LOGO");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		Result result =upload(file, terraceProperties);
		/*if(result.getCode()==0){
			String path = (String) result.getData();
			path=path.substring(path.lastIndexOf("static")+6);
			return new Result(200,"上传成功",StringUtils.replace(path, "\\","/"));
		}*/
		return result;
	}
}
