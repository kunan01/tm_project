package com.tangmo.zhjy.system.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @author Chamber
 * @date 2017/8/22.
 * @description 密码相关工具
 */
public class EncryptUtil {

    public static String get32Uuid(){
        String uuid = UUID.randomUUID().toString().trim().replace("-","");
        return uuid;
    }

    public static void main(String[] args) {
    }

    /**
     * md5加密
     *
     * @param str 要加密的字符串
     * @return md5加密后的字符串
     */
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 生成6位随机密码
     *
     * @return
     */
    public static String randomPwd() {
        StringBuilder val = new StringBuilder("");
        Random random = new Random();
        final int paswordLength = 6;
        for(int i = 0; i < paswordLength; i++) {
            String type = random.nextInt(2) % 2 == 0 ? "c" : "n";
            //输出字母还是数字
            if( "c".equalsIgnoreCase(type)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val = val.append((char)(random.nextInt(26) + temp));
            } else if("n".equalsIgnoreCase(type) ) {
                val = val.append(String.valueOf(random.nextInt(10)));
            }
        }
        return val.toString();
    }


}
