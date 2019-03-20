package com.tangmo.zhjy.system.modules.controller;

import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.utils.FileUploadUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="/app/informationImages")
@Api("发布信息图片上传")
public class SysInformationImagesController extends BaseController {
	
	@Value("${app.upload-content-path}")
	private String uploadPath;

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="多图片上传接口",notes="")
	@PostMapping(value="/filesUpload",consumes="multipart/*",headers="content-type=multipart/form-data")
	public Result filesUpload(@ApiParam(name="file",value="图片上传") MultipartFile file){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("多图片上传接口");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return FileUploadUtil.upload(file,uploadPath);
	}


}
