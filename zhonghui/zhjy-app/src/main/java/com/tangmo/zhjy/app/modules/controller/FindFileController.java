package com.tangmo.zhjy.app.modules.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.security.properties.SecurityProperties;
import com.tangmo.zhjy.app.utils.FileUploadUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app")
@Api("获取图片绝对路径")
public class FindFileController {

	@Autowired
	private SecurityProperties securityProperties;

	@SuppressWarnings("unused")
	private final ResourceLoader resourceLoader;

	@Autowired  
	public FindFileController(ResourceLoader resourceLoader) {  
		this.resourceLoader = resourceLoader;  
	}  

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="获取图片绝对路径",notes="通过相对路径换绝对路径")
	@GetMapping(value="/findAbsolutePath")
	public Result findAbsolutePath(@RequestParam("pathUrl") String pathUrl){
		if(pathUrl!=null){
			String path=securityProperties.getBrowser().getPathUrl();
			StringBuffer stringBuffer = new StringBuffer();
			String[] pathUrls=pathUrl.split(",");
			for (String string : pathUrls) {
				stringBuffer.append(path+"/"+string).append(",");
			}

			return new Result(ResultCode.SUCCESS,stringBuffer.toString());
		}else{
			return new Result(ResultCode.PAPAMETE_ERROR);
		}
	}

	//显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png  
	@ApiOperation(value="获取图片Base64",notes="")
	@ApiImplicitParam(name="filename",value="图片相对路径",required=true,dataType="string",paramType="query")
	@GetMapping(value = "/getBase64")
	public String getFile(@RequestParam("filename") String filename) {  
		return  FileUploadUtil.file2ImgStr(filename.replace("-", "/"));
	}
	
	@ApiOperation(value="APP展示图片",notes="")
	@GetMapping(value="/getImage")
	public String getImage(@RequestParam("path") String path,HttpServletResponse response) throws IOException{
		path=path.replace("-", "/");

		ServletOutputStream out = null;  
		FileInputStream ips = null;  
		try {  
			//获取图片存放路径  
			String imgPath = "C:/static/"+ path;  
			ips = new FileInputStream(new File(imgPath));  
			response.setHeader("Content-Type","image/jpeg"); 
			out = response.getOutputStream();  
			//读取文件流  
			int len = 0;  
			byte[] buffer = new byte[1024 * 10];  
			while ((len = ips.read(buffer)) != -1){  
				out.write(buffer,0,len);  
			}  
			out.flush();
		}catch (Exception e){  
			e.printStackTrace();  
		}finally {  
			out.close();  
			ips.close();  
		}  
		return null;  
	}


	@ApiOperation(value="下载",notes="")
	@GetMapping(value="/downFile")
	public String downFile(@RequestParam("path") String path,HttpServletResponse response) throws IOException{
		path=path.replace("-", "/");

		ServletOutputStream out = null;
		FileInputStream ips = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			//获取文件存放路径
			String imgPath = "C:/static/"+ path;
			File file=new File(imgPath);
			ips = new FileInputStream(file);
			//文件下载后的名称和类型
			headers.add("Content-Disposition", "attachment;filename=" + file.getName());
			//response.setHeader("Content-Type","image/jpeg");
			out = response.getOutputStream();
			//读取文件流
			int len = 0;
			byte[] buffer = new byte[1024 * 10];
			while ((len = ips.read(buffer)) != -1){
				out.write(buffer,0,len);
			}
			out.flush();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			out.close();
			ips.close();
		}
		return null;
	}

}
