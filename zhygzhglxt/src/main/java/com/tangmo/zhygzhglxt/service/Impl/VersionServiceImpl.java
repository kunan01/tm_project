package com.tangmo.zhygzhglxt.service.Impl;

import com.tangmo.zhygzhglxt.dao.VersionMapper;
import com.tangmo.zhygzhglxt.entity.Version;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.VersionService;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chengge on 2018/10/9.
 */
@Service
public class VersionServiceImpl implements VersionService {

    @Autowired
    private Environment env;

    private final static String AGENT_MSIE = "MSIE";
    private final static String AGENT_TRIDENT = "Trident";
    private final static String AGENT_EDGE = "Edge";

    @Resource
    private VersionMapper versionMapper;

    @Override
    public Result<Version> getVersion(String versionNumber, String type) {

        if (versionNumber == null || "".equals(versionNumber)) {
            return new Result(ResultCode.FAIL, "版本号不能为空");
        }
        if (type == null || "".equals(type)) {
            return new Result(ResultCode.FAIL, "客户端类型不明确！");
        }

        Version version = versionMapper.getVersion(type);

        if (version == null) {
            return new Result(ResultCode.FAIL, "还未添加版本！");
        }

        if (versionNumber.equals(version.getVersionNumber()) || versionNumber.compareTo(version.getVersionNumber()) >= 1) {//如果相同或者高于，说明版本号已是最新
            return new Result(ResultCode.VERSION_NUMBER1, version);
        } else {//如果不相同，说明版本号不是最新的
            return new Result(ResultCode.VERSION_NUMBER2, version);
        }
    }

    @Override
    public Result addVersion(Version version) {

        if (version == null) {
            return new Result(ResultCode.FAIL, "版本不能为空");
        }

        //如果要设置为默认地址,则先设置其他地址为默认地址
        if (version.getVersionNumber() == null || "".equals(version.getVersionNumber())) {
            return new Result(ResultCode.FAIL, "版本号不能为空");
        }
        if (version.getType() == null || "".equals(version.getType())) {
            return new Result(ResultCode.FAIL, "客户端类型不能为空！");
        }
        int a = versionMapper.insertSelective(version);
        if (a > 0) {
            return new Result(ResultCode.SUCCESS);
        } else {
            return new Result(ResultCode.FAIL, "添加版本号失败！");
        }
    }

    @Override
    public ResponseEntity<byte[]> uploadVersion1(String userAgent) {

        ResponseEntity<byte[]> responseEntity = null;
        try {
            //获取文件目录
            String basePath = env.getProperty("RF.BASE_DIR");
            //String basePath = "C:/zhygzhglxt";
            String resourceName = "/zhygzhglxt1.apk"; // /43/000fddc102ce4f5989fb5d4ae95453c5.jpg
            String filePath = new StringBuilder(basePath).append("/").append(resourceName).toString();
            String fileType = resourceName.substring(resourceName.indexOf(".") + 1);
            responseEntity = downloadEntity(userAgent, filePath, "zhygzhglxt1", fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<byte[]> uploadVersion2(String userAgent) {

        ResponseEntity<byte[]> responseEntity = null;
        try {
            //获取文件目录
            String basePath = env.getProperty("RF.BASE_DIR");
            String resourceName = "/zhygzhglxt2.apk"; // /43/000fddc102ce4f5989fb5d4ae95453c5.jpg
            String filePath = new StringBuilder(basePath).append("/").append(resourceName).toString();
            String fileType = resourceName.substring(resourceName.indexOf(".") + 1);
            responseEntity = downloadEntity(userAgent, filePath, "zhygzhglxt2", fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    /**
     * 下载文件实体
     *
     * @param agent    浏览器标识
     * @param srcFile  源文件路径
     * @param fileName 下载后的文件名
     * @param fileType 下载后的类型
     * @return
     * @throws Exception
     */
    private static ResponseEntity<byte[]> downloadEntity(String agent, String srcFile, String fileName, String fileType)
            throws Exception {
        byte[] body;
        InputStream in = null;
        ResponseEntity<byte[]> response = null;
        try {
            in = new FileInputStream(new File(srcFile));
            //预估文件的有效大小,放入一个body输出
            body = new byte[in.available()];
            in.read(body);
            HttpHeaders headers = new HttpHeaders();
            //浏览器辨别
            if (agent == null) {
                throw new Exception("浏览器标识未获取");
            }
            if (agent.indexOf(AGENT_MSIE) != -1 || agent.indexOf(AGENT_TRIDENT) != -1 || agent.indexOf(AGENT_EDGE) != -1) {
                fileName = fileName.replace(" ", "_");
                fileName = java.net.URLEncoder.encode(fileName, "UTF8");
            } else {// 火狐,chrome等
                fileName = new String(fileName.trim().getBytes("UTF-8"), "iso-8859-1");
            }
            //文件下载后的名称和类型
            headers.add("Content-Disposition", "attachment;filename=" + fileName + "." + fileType.trim());
            //响应状态码ok=200
            HttpStatus statusCode = HttpStatus.OK;
            //返回的数据
            System.out.println("======开始下载文件======");
            response = new ResponseEntity<byte[]>(body, headers, statusCode);
            System.out.println("======文件下载完成======");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("下载文件异常：" + e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                throw new Exception("文件输入流关闭时发生异常");
            }
        }
        return response;
    }
}
