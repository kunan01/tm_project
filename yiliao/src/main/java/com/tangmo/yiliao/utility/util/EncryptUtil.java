package com.tangmo.yiliao.utility.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @author Chamber
 * @date 2018/8/7.
 * @description 密码相关工具
 */
public class EncryptUtil {

    /**
     * 获取UUID
     */
    public static String get32Uuid(){
        String uuid = UUID.randomUUID().toString().trim().replace("-","");
        return uuid;
    }

    /**
     * md5加密
     */
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 生成6位随机字符
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
