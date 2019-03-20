package com.tangmo.zhjy.app.modules.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.utils.FileUploadUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="/app/informationImages")
@Api("发布文章图片上传")
public class AppInformationImagesController {
	
	@Value("${app.upload-content-path}")
	private String uploadPath;

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="图片上传接口",notes="")
	@PostMapping(value="/filesUpload",consumes="multipart/*",headers="content-type=multipart/form-data")
	public Result filesUpload(@ApiParam(name="file",value="图片上传") MultipartFile file){

		return FileUploadUtil.upload(file,uploadPath);
	}


}
