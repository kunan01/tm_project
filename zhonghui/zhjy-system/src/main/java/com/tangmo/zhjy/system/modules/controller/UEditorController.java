package com.tangmo.zhjy.system.modules.controller;


import com.alibaba.fastjson.JSON;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.utils.FileUploadUtil;
import com.tangmo.zhjy.system.utils.ReturnUploadImage;
import com.tangmo.zhjy.system.utils.UeditorConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chengge on 2018/9/20.
 */
@RestController
@RequestMapping("/ueditor")
public class UEditorController {

    /**
     * 文件上传根目录
     */
    @Value("${web.upload-path}")
    private String webUploadPath;


    @RequestMapping("/exec")
    public void getUEditorConfig(String action, HttpServletRequest request, HttpServletResponse response) {
        //String rootPath = "src/main/resources;
//        response.setContentType("application/octet-stream;charset=UTF-8");
       // response.setHeader("X-Content-Type-Options", null);
        response.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题
        try {
            //String exec = new ActionEnter(request, rootPath).exec();
            if("uploadimage".equals(action)){
                MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
                MultipartFile multipartFile=multipartHttpServletRequest.getFile("upfile");
                Result result= FileUploadUtil.upload(multipartFile,webUploadPath);
                ReturnUploadImage rui=new ReturnUploadImage();
                rui.setState("SUCCESS");
                System.out.println("====path===="+result.getData());
                rui.setUrl(result.getData().toString());
                rui.setTitle(multipartFile.getOriginalFilename());
                rui.setOriginal(multipartFile.getOriginalFilename());
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(rui));
                writer.flush();
                writer.close();
            }else{
                PrintWriter writer = response.getWriter();
                writer.write(UeditorConfig.UEDITOR_CONFIG);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}