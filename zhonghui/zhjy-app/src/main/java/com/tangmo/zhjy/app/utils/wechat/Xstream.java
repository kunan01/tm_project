package com.tangmo.zhjy.app.utils.wechat;


import com.tangmo.zhjy.app.modules.bean.Pay;

public class Xstream {
	
	public static void getXml(){
		
		Pay pay = new Pay();
		pay.setAppid("wxd930ea5d5a258f4f");
		pay.setBody("test");
		pay.setDevice_info("1000");
		pay.setMch_id("10000100");
		pay.setNonce_str("ibuaiVcKdpRxkhJA");
		
		//实体类转换XML文件
//		XStream  stream = new XStream();
		Xstreamutil.stream.alias("xml", Pay.class);
		String xml=Xstreamutil.stream.toXML(pay).replace("__", "_");
		System.out.println(xml);
		
	}
	
	public static void main(String[] args) {
		getXml();
	}
}
