package com.tangmo.emall.utils;

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

    /**
     * 将字符串转成ASCII
     */
    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }

    /**
     * 将ASCII转成字符串
     */
    public static String asciiToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    // 将字符串转换成二进制字符串，以空格相隔
    public static String StrToBinstr(String str) {
        char[] strChar = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {
            result += Integer.toBinaryString(strChar[i]) + ",";
        }
        return result;
    }

    // 将二进制字符串转换成Unicode字符串
    public static String BinstrToStr(String binStr) {
        String[] tempStr = StrToStrArray(binStr);
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    // 将初始二进制字符串转换成字符串数组，以空格相隔
    public static String[] StrToStrArray(String str) {
        return str.split(",");
    }

    // 将二进制字符串转换为char
    public static char BinstrToChar(String binStr) {
        int[] temp = BinstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    // 将二进制字符串转换成int数组
    public static int[] BinstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

}
