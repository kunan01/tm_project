package com.tangmo.yiliao;

import com.alibaba.fastjson.JSONObject;
import com.tangmo.yiliao.dao.*;
import com.tangmo.yiliao.entity.*;
import com.tangmo.yiliao.service.*;
import com.tangmo.yiliao.utility.code.OrderRelated;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.code.SQMsg;
import com.tangmo.yiliao.utility.file.ImgUtil;
import com.tangmo.yiliao.utility.http.HttpUtils;
import com.tangmo.yiliao.utility.pay.HttpsRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YiliaoApplicationTests {

    @Resource
    private VideoService videoService;

    @Resource
    private VersionService versionService;

    @Resource
    private VersionDao versionDao;

    @Resource
    private ImgFileService imgFileService;


    @Test
    public void contextLoads() {
//        String host = "https://cdcxdxjk.market.alicloudapi.com";
//        String path = "/chuangxin/dxjk";
//        String method = "POST";
//        String appcode = "bbb9177921fc4e0ab182b965ca96fa0c";
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("content", "【渠村委会】尊敬的贾小杰、贾先生你好，村委会提醒你，明天苏州多云转晴，气温7-13度，空气潮湿，出门注意保暖。");
//        querys.put("mobile", "17602964507");
//        Map<String, String> bodys = new HashMap<String, String>();
//
//        try {
//            /**
//             * 重要提示如下:
//             * HttpUtils请从
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//             * 下载
//             *
//             * 相应的依赖请参照
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//             */
//            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
//            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }






}
