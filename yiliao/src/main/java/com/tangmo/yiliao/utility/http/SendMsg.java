package com.tangmo.yiliao.utility.http;

import com.tangmo.yiliao.entity.RsFile;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author boge
 * @date 2018-8-27
 * @description 发送短信
 */
public class SendMsg {

    public static String sendMsg(String mobile, RsFile rsFile){
        String host = rsFile.getAddress();
        String authCode = sixRandomNumber();
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("action", rsFile.getAction());
        querys.put("userid", rsFile.getUserId());
        querys.put("account", rsFile.getAccount());
        querys.put("password", rsFile.getPassword());
        querys.put("mobile", mobile);
        String contents = "as@asd";
        String[] strings = rsFile.getContent().split("@");
        querys.put("content", strings[0]+authCode+strings[1]);
        querys.put("sendTime", "");
        querys.put("checkcontent",rsFile.getCheckContent().toString());
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost request = new HttpPost(HttpUtils.buildUrl(host,"",querys));
            HttpResponse response = httpClient.execute(request);
            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity);
            SAXReader reader = new SAXReader();
            System.out.println(content);
            Document document = reader.read(new ByteArrayInputStream(content.getBytes("UTF-8")));
            System.out.println(document.toString());
            Element element = document.getRootElement();
            System.out.println(element.toString());
            Element key_element = element.element("returnstatus");
            if(key_element.getStringValue().equals("Success")){
                return authCode;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成六位随机数
     *
     * @return
     */
    public static String sixRandomNumber() {
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        return result;
    }
}
