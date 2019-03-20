package com.tangmo.zhjy.app.utils;

import com.tangmo.zhjy.app.utils.wechat.HttpUtils;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boge
 * @date 18/4/10
 * @description
 */

public class AuthorizeUtil {

    /**
     * 原有框架代码spring security验证流程太麻烦,前段请求接口,后台自己获取token跳过框架验证
     */
//    public static String host = "http://60.8.218.156:8090/";
    public static String host = "http://127.0.0.1:8090/";
    public static String tokenPath = "/app/oauth/token";
    public static String sign = "Basic emhqeV9hcHA6emhqeV9zZWNyZXQ=";


    public static String getToken(String username,String password){

        BufferedReader in = null;
        String content = "";
        Map header = new HashMap<String,String>();
        header.put("Authorization",sign);
        Map param = new HashMap<String,String>();
        param.put("username",username);
        param.put("password",password);
        param.put("grant_type","password");
        param.put("scope","all");
        try {
            HttpResponse response = HttpUtils.doPost(host,tokenPath,header,param,null);
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            content = sb.toString();
            System.out.println("======执行完"+content+"+============");
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return content;
        }
    }
}
