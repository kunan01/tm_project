/**     
 * @Title: AppSlideController.java   
 * @Package com.tangmo.zhjy.modules.app.controller   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月16日 下午6:47:15   
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
import com.tangmo.zhjy.system.modules.bean.SysSlideBean;
import com.tangmo.zhjy.system.modules.dto.SysSlideBeanDto;
import com.tangmo.zhjy.system.modules.service.SysSlideService;
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
 * @Description : TODO(轮播图管理控制层)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月16日 下午6:47:15
 */
@RestController
@RequestMapping("/app/slide")
@Api(value="轮播图管理")
public class SysSlideController extends BaseController {

	@Autowired
	private SysSlideService sysSlideServiceImpl;
	
	@Value("${app.slide.pageSize}")
	private String terraceProperties;

	/**
	 * 文件上传根目录
	 */
	@Value("${web.upload-path}")
	private String webUploadPath;


	@SuppressWarnings("rawtypes")
	@ApiOperation(value="上传轮播图",notes="")
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
		logTable.setParam("上传轮播图");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		Result result =upload(file, webUploadPath);
		/*if(result.getCode()==0){
			String path = (String) result.getData();
			path=path.substring(path.lastIndexOf("static")+6);
			return new Result(200,"上传成功",StringUtils.replace(path, "\\","/"));
		}*/
		return result;
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="添加轮播图信息",notes="")
	@ApiImplicitParam(name="appSlideBeanDto",dataType="SysSlideBeanDto",required=true)
	@PostMapping(value="/addSlide")
	public Result addSlide(@Valid @RequestBody SysSlideBeanDto appSlideBeanDto,BindingResult error){
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
		logTable.setParam("添加轮播图信息");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		//添加轮播图
		return sysSlideServiceImpl.addSlide(appSlideBeanDto);
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="修改轮播图",notes="修改 && 删除接口 && 恢复数据接口，isShow隐藏属性0：显示 1隐藏")
	@ApiImplicitParam(name="appSlideBeanDto",dataType="SysSlideBeanDto",required=true)
	@PutMapping(value="/modifySlide")
	public Result modifySlide(@Valid @RequestBody SysSlideBeanDto appSlideBeanDto,BindingResult error){
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
		logTable.setParam("修改轮播图");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		try {
			if(sysSlideServiceImpl.findById(appSlideBeanDto.getId())!=null){
				//修改轮播图
				return sysSlideServiceImpl.modify(appSlideBeanDto);
			}else{
				return new Result(ResultCode.PAPAMETE_ERROR);
			}
		} catch (Exception e) {
			System.out.println("修改轮播图异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}

	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页查询",notes="模糊分页查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name",value="模糊查询参数",dataType="string",required=false,paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
		@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query"),
	})
	@GetMapping(value="/findBySlidePage")
	public Result findByPage(String name,Integer pageNo,Integer pageSize){
		return sysSlideServiceImpl.findByPage(name, pageNo, pageSize);
	}

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@ApiOperation(value="根据Id查询",notes="根据轮播图数据编号查询进行修改")
	@GetMapping(value="/findSlideId/{id:\\d+}")
	public Result findById(@PathVariable Long id){
		SysSlideBean appSlideBean=sysSlideServiceImpl.findById(id.intValue());
		if(appSlideBean!=null){
			return new Result(ResultCode.SUCCESS,appSlideBean);
		}
		return new Result(ResultCode.PAPAMETE_ERROR);

	}

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@ApiOperation(value="根据Id彻底删除轮播图",notes="根据Id彻底删除轮播图")
	@DeleteMapping(value="/delById/{id:\\d+}")
	public Result delById(@PathVariable Long id){
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
		logTable.setParam("根据Id彻底删除轮播图");
		logService.addLog(logTable);
		return sysSlideServiceImpl.delById(id.intValue());

	}

}
