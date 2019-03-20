package com.tangmo.zhjy.system.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;

/**
 * @Description : TODO(文件上传工具类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月17日 下午1:05:42
 */
public class FileUploadUtil {

	private  static String imageUrl="C://static//";
	/**
	 * 
	 * @Title: upload   
	 * @Description: TODO(单文件上传)   
	 * @param @param file
	 * @param @param path
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Result upload(MultipartFile file,String path){
		//判断是否为空
		if(!file.isEmpty()){
			//判断上传的是否是图片
			if(file.getContentType().contains("image")){
				try {
					// 获取图片的文件名
					String fileName = file.getOriginalFilename();
					// 获取图片的扩展名
					String extensionName = StringUtils.substringAfter(fileName, ".");
					// 新的图片文件名 = 获取时间戳+"."图片扩展名
					String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
					File dest = new File(path, newFileName);
					if (!dest.getParentFile().exists()) {
						dest.getParentFile().mkdirs();
					}
					// 上传到指定目录
					file.transferTo(dest);
					path+="\\"+newFileName;
					path=path.substring(path.lastIndexOf("static")+6);
				} catch (IllegalStateException | IOException e) {
					return new Result(10010,"上传失败",null);
				}
			}
		}

		return new Result(ResultCode.SUCCESS,StringUtils.replace(path, "\\","/"));
	}

	/**
	 * 
	 * @Title: handleFileUpload   
	 * @Description: TODO(多文件上传)   
	 * @param @param request
	 * @param @return    设定文件   
	 * @return String    返回类型   
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Result handleFileUpload(List<MultipartFile> files,String path) {
		StringBuffer urls = new StringBuffer();
		for (MultipartFile multipartFile : files) {
			String uri=(String) upload(multipartFile, path).getData();
			uri=uri.substring(uri.lastIndexOf("static")+6);
			urls.append(StringUtils.replace(uri, "\\","/")).append(",");
		}
		return new Result(ResultCode.SUCCESS,urls.toString());
	}

	/**
	 * 将文件流转换成base64字符串
	 * @param screenshot    文件流
	 * @return
	 */
	public static String file2ImgStr(String imageName){

		File screenshot = new File(imageUrl+imageName);
		try {
			byte[] data = null;
			// 读取图片字节数组
			try {
				//得到输入流
				InputStream in = new FileInputStream(screenshot);
				data = new byte[in.available()];
				in.read(data);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(data!=null && data.length!=-1) {
				// 对字节数组Base64编码
				return "data:image/png;base64,"+new String(Base64.encodeBase64(data));
			}
			return "图片不存在";
		} catch (Exception e) {
			e.printStackTrace();
			return "获取图片失败";
		}
	}



}
