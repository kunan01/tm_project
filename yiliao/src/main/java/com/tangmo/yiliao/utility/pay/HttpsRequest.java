package com.tangmo.yiliao.utility.pay;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;


public class HttpsRequest {
	
	public String HttpsRequest(String requestUrl,String requestMethod,String outStr) throws Exception{
		
		SSLContext sslContext  = SSLContext.getInstance("SSL","SunJSSE");
		TrustManager[] tm = {new MyX509TrustManager()};
		//初始化
		sslContext.init(null, tm, new java.security.SecureRandom());
		//获取SSLSocketFactory对象
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		
		//建立连接
		StringBuffer sb = null;
		URL url = new URL(requestUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod(requestMethod);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setSSLSocketFactory(ssf);
		conn.connect();
		
		if(outStr!=null){
			OutputStream os = conn.getOutputStream();
			os.write(outStr.getBytes("UTF-8"));
			os.close();
		}
		
		
		//读取服务器内容
		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		sb= new StringBuffer();
		String line = null;
		while((line=br.readLine())!=null){
			sb.append(line);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(new HttpsRequest().HttpsRequest("https://api.weixin.qq.com/sns/jscode2session", "GET", "appid=wxf2d90e18e6b719b3&secret=59803643c6a6ff6d0b16025838e5b790&js_code=001w4pkN1irYx81KU0iN1EPukN1w4pkh&grant_type=authorization_code"));
	}

}
