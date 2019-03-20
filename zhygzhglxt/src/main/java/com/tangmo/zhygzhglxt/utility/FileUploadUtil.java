package com.tangmo.zhygzhglxt.utility;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Description : TODO(文件上传工具类)
 * ---------------------------------
 * @Author : chengge
 */
public class FileUploadUtil {

    @Autowired
    private Environment env;

    private final static String AGENT_MSIE = "MSIE";
    private final static String AGENT_TRIDENT = "Trident";
    private final static String AGENT_EDGE = "Edge";


    private static String imageUrl = "C://static";

    /**
     * @param @param  file
     * @param @param  path
     * @param @return 设定文件
     * @return Result    返回类型
     * @throws
     * @Title: upload
     * @Description: TODO(单文件上传)
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Result upload(MultipartFile file, String path) {
        //判断是否为空
        if (!file.isEmpty()) {
            //判断上传的是否是图片
            if (file.getContentType().contains("image")) {
                try {
                    // 获取图片的文件名
                    String fileName = file.getOriginalFilename();
                    // 获取图片的扩展名
                    //String extensionName = StringUtils.substringAfter(fileName, ".");
                    String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
                    //System.out.println("=============="+extensionName+"=============");
                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                    File dest = new File(path, newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    // 上传到指定目录
                    file.transferTo(dest);
                    path += "\\" + newFileName;
                    System.out.print("--------------------------------------------" + path);
                    path = path.substring(path.lastIndexOf("static") + 6);
                } catch (IllegalStateException | IOException e) {
                    return new Result(10010, "上传失败", null);
                }
            }
        }

        return new Result(ResultCode.SUCCESS, StringUtils.replace(path, "\\", "/"));
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Result uploadThreeD(MultipartFile file, String path, Integer type, String onlyKey) {
        //判断是否为空
        if (!file.isEmpty()) {
            //判断上传的是否是图片
            if (file.getContentType().contains("image")) {
                try {
                    // 获取图片的文件名
                    String fileName = file.getOriginalFilename();
                    // 获取图片的扩展名
                    //String extensionName = StringUtils.substringAfter(fileName, ".");
                    String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
                    //System.out.println("=============="+extensionName+"=============");
                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
                    //String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                    String newFileName = "";
                    if (type == 1) {
                        newFileName = "mobile_b." + extensionName;
                    }
                    if (type == 2) {
                        newFileName = "mobile_d." + extensionName;
                    }
                    if (type == 3) {
                        newFileName = "mobile_f." + extensionName;
                    }
                    if (type == 4) {
                        newFileName = "mobile_l." + extensionName;
                    }
                    if (type == 5) {
                        newFileName = "mobile_r." + extensionName;
                    }
                    if (type == 6) {
                        newFileName = "mobile_u." + extensionName;
                    }
                    if (type == 7) {
                        newFileName = "thumb." + extensionName;
                        //FileUploadUtil.upload(file,"C:/static/sys/room");
                    }
                    File dest = new File(path, onlyKey + "/" + newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    if (dest.exists()) {
                        dest.deleteOnExit();
                    }
                    // 上传到指定目录
                    path += "\\" + onlyKey + "\\" + newFileName;
                    File fil = new File(path);
                    if (fil.exists()) {
                        fil.delete();
                    }
                    //System.out.println("========"+(path+="\\"+onlyKey+"\\"+newFileName));
                    file.transferTo(dest);
                    System.out.print("------------" + path + "------------");
                    path = path.substring(path.lastIndexOf("demo") + 4);
                } catch (Exception e) {
                    System.out.println(e.toString() + "=======================");
                    return new Result(10010, "上传失败", null);
                }
            }
        }

        return new Result(ResultCode.SUCCESS, StringUtils.replace(path, "\\", "/"));
    }


    /**
     * @param @param  request
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: handleFileUpload
     * @Description: TODO(多文件上传)
     */

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Result handleFileUpload(List<MultipartFile> files, String path) {
        StringBuffer urls = new StringBuffer();
        for (MultipartFile multipartFile : files) {
            String uri = (String) upload(multipartFile, path).getData();
            uri = uri.substring(uri.lastIndexOf("static") + 6);
            urls.append(StringUtils.replace(uri, "\\", "/")).append(",");
        }
        return new Result(ResultCode.SUCCESS, urls.toString());
    }

    /**
     * 将文件流转换成base64字符串
     *
     * @param imageName 文件流
     * @return
     */
    public static String file2ImgStr(String imageName) {

        File screenshot = new File(imageUrl + imageName);
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
            if (data != null && data.length != -1) {
                // 对字节数组Base64编码
                return "data:image/png;base64," + new String(Base64.encodeBase64(data));
            }
            return "图片不存在";
        } catch (Exception e) {
            e.printStackTrace();
            return "获取图片失败";
        }
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost2(String url, String param) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String appcode = "95f4c04f24ba40c8a764089c3e8b220b";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            //conn.setRequestProperty("Authorization", "APPCODE " + appcode);
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
