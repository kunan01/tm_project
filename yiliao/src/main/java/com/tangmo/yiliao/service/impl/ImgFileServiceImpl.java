package com.tangmo.yiliao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tangmo.yiliao.entity.RsFile;
import com.tangmo.yiliao.service.ImgFileService;
import com.tangmo.yiliao.utility.code.OrderRelated;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.file.ImgUtil;
import com.tangmo.yiliao.utility.jedis.JedisUtil;
import com.tangmo.yiliao.utility.pay.HttpsRequest;
import com.tangmo.yiliao.utility.util.EncryptUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boge
 * @date 18/7/6
 * @description
 */
@Service("fileService")
public class ImgFileServiceImpl implements ImgFileService {

    @Autowired
    private Environment env;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    private static final String IMG_TYPE = "jpeg";
    private final static String AGENT_MSIE = "MSIE";
    private final static String AGENT_TRIDENT = "Trident";
    private final static String AGENT_EDGE = "Edge";

    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    @Override
    public Result downloadFile(String rfId) {
//        ResponseEntity<byte[]> responseEntity = null;
        try {
//            //获取文件目录
            String basePath = env.getProperty("RF.BASE_DIR");
            return ResultUtil.success(encodeBase64File(basePath + rfId));
//            String resourceName = rfId;
//            String filePath = new StringBuilder(basePath).append("/").append(resourceName).toString();
//            String fileType = resourceName.substring(resourceName.indexOf(".") + 1);
//            responseEntity = downloadEntity(userAgent, filePath, "a", fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.paramError();
    }

    @Override
    public void playVideo(HttpServletRequest request, HttpServletResponse response, String rfId) {
        String path = request.getServletPath();
        //获取文件目录
        String basePath = env.getProperty("RF.BASE_DIR");
        String resourceName = rfId;
        String filePath = new StringBuilder(basePath).append("/").append(resourceName).toString();
        BufferedInputStream bis = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                long p = 0L;
                long toLength = 0L;
                long contentLength = 0L;
                int rangeSwitch = 0; // 0,从头开始的全文下载；1,从某字节开始的下载（bytes=27000-）；2,从某字节开始到某字节结束的下载（bytes=27000-39000）
                long fileLength;
                String rangBytes = "";
                fileLength = file.length();

                // get file content
                InputStream ins = new FileInputStream(file);
                bis = new BufferedInputStream(ins);

                // tell the client to allow accept-ranges
                response.reset();
                response.setHeader("Accept-Ranges", "bytes");

                // client requests a file block download start byte
                String range = request.getHeader("Range");
                if (range != null && range.trim().length() > 0 && !"null".equals(range)) {
                    //实现手机端直接播放
                    response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);
                    rangBytes = range.replaceAll("bytes=", "");
                    if (rangBytes.endsWith("-")) {  // bytes=270000-
                        rangeSwitch = 1;
                        p = Long.parseLong(rangBytes.substring(0, rangBytes.indexOf("-")));
                        contentLength = fileLength - p;  // 客户端请求的是270000之后的字节（包括bytes下标索引为270000的字节）
                    }
                    else { // bytes=270000-320000
                        rangeSwitch = 2;
                        String temp1 = rangBytes.substring(0, rangBytes.indexOf("-"));
                        String temp2 = rangBytes.substring(rangBytes.indexOf("-") + 1, rangBytes.length());
                        p = Long.parseLong(temp1);
                        toLength = Long.parseLong(temp2);
                        contentLength = toLength - p + 1; // 客户端请求的是 270000-320000 之间的字节
                    }
                }
                else {
                    contentLength = fileLength;
                }

                // 如果设设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。
                // Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
                response.setHeader("Content-Length", new Long(contentLength).toString());

                // 断点开始
                // 响应的格式是:
                // Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
                if (rangeSwitch == 1) {
                    String contentRange = new StringBuffer("bytes ").append(new Long(p).toString()).append("-")
                            .append(new Long(fileLength - 1).toString()).append("/")
                            .append(new Long(fileLength).toString()).toString();
                    response.setHeader("Content-Range", contentRange);
                    bis.skip(p);
                }
                else if (rangeSwitch == 2) {
                    String contentRange = range.replace("=", " ") + "/" + new Long(fileLength).toString();
                    response.setHeader("Content-Range", contentRange);
                    bis.skip(p);
                }
                else {
                    String contentRange = new StringBuffer("bytes ").append("0-")
                            .append(fileLength - 1).append("/")
                            .append(fileLength).toString();
                    response.setHeader("Content-Range", contentRange);
                }

                String fileName = file.getName();
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

                OutputStream out = response.getOutputStream();

                int n = 0;
                long readLength = 0;
                int bsize = 1024;
                byte[] bytes = new byte[bsize];

                if (rangeSwitch == 2) {
                    // 针对 bytes=27000-39000 的请求，从27000开始写数据
                    while (readLength <= contentLength - bsize) {
                        n = bis.read(bytes);
                        readLength += n;
                        out.write(bytes, 0, n);
                    }

                    if (readLength <= contentLength) {
                        n = bis.read(bytes, 0, (int) (contentLength - readLength));
                        out.write(bytes, 0, n);
                    }

                }
                else {
                    while ((n = bis.read(bytes)) != -1) {
                        out.write(bytes, 0, n);
                    }
                }
                out.flush();
                out.close();
                bis.close();
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String uploadFile(MultipartFile file, String userId) {
        String[] strings = uploadFileReturnType(file, userId);

        return strings[0];
    }

    @Override
    public Result uploadImg(String userId, MultipartFile file) {
        if (userId == null) {
            return ResultUtil.error("用户信息不能为空");
        }
        if (file != null) {
            String uuid = uploadFile(file, userId);
            if (uuid == null) {
                return ResultUtil.error("图片上传失败,请稍后重试");
            }
            return ResultUtil.success(uuid);
        } else {
            return ResultUtil.error("请先选择图片");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public String[] uploadFileReturnType(MultipartFile file, String userId) {
        String[] rfInfo = new String[2];
        //验证文件格式
        String fileOriginalName = file.getOriginalFilename();
        int index = fileOriginalName.lastIndexOf(".") + 1;
        String fileType = fileOriginalName.substring(index);
        rfInfo[1] = fileType;
        String dir = env.getProperty("RF.BASE_DIR");
        String uuid = EncryptUtil.get32Uuid();
        StringBuilder sb = new StringBuilder("").append("/").append(userId).append("/")
            .append(uuid).append(".").append(fileType);
        String fileName = sb.toString();
        boolean bool = uploadFileEntity(file, dir, fileName);

        if (!bool) {
            return null;
        }
        rfInfo[0] = fileName;
        return rfInfo;
    }

    /**
     * 上传文件的方法
     * <p>
     * 1.使用MultipartFile 获取文件;
     * 2.将上传上来的文件存储与临时目录
     *
     * @param file MultipartFile 要上传的文件
     * @return fileName  文件名
     * @throws boolean 成功:true; 失败:false
     */
    private boolean uploadFileEntity(MultipartFile file, String dir, String fileName) {
        try {
            File newFile = new File(dir, fileName);
            //该方法内部会自动把用到的IO流关掉
            FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
        } catch (Exception e) {
            return false;
        }
        return true;
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
            }
            else {// 火狐,chrome等
                fileName = new String(fileName.trim().getBytes("UTF-8"), "iso-8859-1");
            }
            //文件下载后的名称和类型
            headers.add("Content-Disposition", "attachment;filename=" + fileName + "." + fileType.trim());
            //响应状态码ok=200
            HttpStatus statusCode = HttpStatus.OK;
            //返回的数据
            response = new ResponseEntity<byte[]>(body, headers, statusCode);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new Exception("下载文件异常：" + e);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e) {
                throw new Exception("文件输入流关闭时发生异常");
            }
        }

        return response;
    }


//    /**
//     * 根据用户id,获取文件具体信息 32位主键编码和存放路径
//     *
//     * @param userId   用户id
//     * @param fileType 文件类型
//     * @return
//     */
//    public RsFile getFileInfo(String userId, String fileType) {
//        String uuid = EncryptUtil.get32Uuid();
//        StringBuilder sb = new StringBuilder("").append("/").append(userId).append("/")
//                .append(uuid).append(".").append(fileType);
////        RsFile file = new RsFile();
////
////        file.setRfId(uuid);
////        file.setPath(sb.toString());
////        file.setUserId(userId);
//
//        return file;
//    }


    @Override
    public Result getShareCode(String path,String paramStr) {

        String pathkey = path+paramStr;
        if(!jedisKeys.exists(pathkey)) {

            String key = "accessToken";

            String token = null;

            if(!jedisKeys.exists(key)) {
                String requestxml = null;
                try {
                    requestxml = new HttpsRequest().HttpsRequest("https://api.weixin.qq.com/cgi-bin/token", "POST", "grant_type=client_credential&appid=wxf2d90e18e6b719b3&secret=260316eafbf1e3c9c7536eec427aa9d6");
                    System.out.println(requestxml);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResultUtil.error("微信服务器故障");
                }

                try {
                    JSONObject jsonObject = JSONObject.parseObject(requestxml);
                    token = jsonObject.get("access_token").toString();
                    System.out.println(token);
                    jedisStrings.set(key, token);
                    //设置过期时间两个小时
                    jedisStrings.expire(key,7080);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResultUtil.error("获取access_token失败");
                }
            }else{
                token = jedisStrings.get(key);
            }

            String str = ImgUtil.getminiqrQr(paramStr,token,path);

            if(str == null){
                return ResultUtil.error("获取二维码失败");
            }

            jedisStrings.set(pathkey, str);

            return ResultUtil.error(str);
        }else{
            return ResultUtil.success(jedisStrings.get(pathkey));
        }
    }
}
