package com.tangmo.zhjy.app.utils.wechat;

import java.util.Date;
import java.util.Random;

public class PayUtil {

	//自动生成随机字符串
	public static String getRandomString(int length){
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(3);
			long result = 0;
			switch (number) {
			case 0:
				result = Math.round(Math.random()*25+65);
				sb.append(String.valueOf((char)result));
				break;
			case 1:
				result = Math.round(Math.random()*25+97);
				sb.append(String.valueOf((char)result));
				break;
			case 2:
				sb.append(String.valueOf(new Random().nextInt(10)));
				break;
			}
		}
		
		return sb.toString();
	}
	
	public static String getTempStamp(){
		
		Date date = new Date();
		long a = date.getTime();
		String TempStamp = String .valueOf(a);
		return TempStamp;
	}
	
	public static void main(String[] args) {
		
		System.out.println(new PayUtil().getRandomString(6));
	}
}
