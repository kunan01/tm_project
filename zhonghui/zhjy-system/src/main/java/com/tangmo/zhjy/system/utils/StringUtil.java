package com.tangmo.zhjy.system.utils;

/**
 * @Description : TODO(字符串工具类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月11日 下午8:27:18
 */
public class StringUtil {
	
	/**
	 * 
	* @Title: isEmpty   
	* @Description: TODO(判断字符串是否为空)   
	* @param @param str
	* @param @return    设定文件   
	* @return boolean    返回类型   
	* @throws
	 */
	public static boolean isEmpty(String str) {  
        return str == null || str.length() == 0;  
    }
}