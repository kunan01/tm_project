package com.tangmo.zhygzhglxt.controller;


import com.tangmo.zhygzhglxt.utility.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/app")
@Api("图片处理")
public class FindFileController {

    @SuppressWarnings("unused")
    private final ResourceLoader resourceLoader;

    @Autowired
    public FindFileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    //显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png
    @ApiOperation(value = "获取图片Base64", notes = "")
    @ApiImplicitParam(name = "filename", value = "图片相对路径", required = true, dataType = "string", paramType = "query")
    @GetMapping(value = "/getBase64")
    public String getFile(@RequestParam("filename") String filename) {
        return FileUploadUtil.file2ImgStr(filename.replace("-", "/"));
    }

    @ApiOperation(value = "APP展示图片", notes = "")
    @GetMapping(value = "/getImage")
    public String getImage(@RequestParam("path") String path, HttpServletResponse response) throws IOException {
        if (path == null || "".equals(path)) {
            return "图片路径不能为空";
        }

        path = path.replace("-", "/");

        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            String imgPath = "C:/Users/Administrator/Desktop/apache-tomcat-7.0.62/webapps/static/" + path;
            File file = new File(imgPath);
            if (!file.exists()) {
                return "该图片不存在";
            }
            ips = new FileInputStream(file);
            response.setHeader("Content-Type", "image/jpeg");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (ips != null) {
                ips.close();
            }
        }
        return "图片为空，获取失败";
    }

}
