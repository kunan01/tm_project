package com.tangmo.zhygzhglxt.utility;

/**
 * @Description : TODO(字符串工具类)
 * ---------------------------------
 * @Author : chengge
 * @Date : 2018年08月07日
 */
public class StringUtil {

    /**
     * @param @param  str
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: isEmpty
     * @Description: TODO(判断字符串是否为空或者是空字符串)
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}