package com.tangmo.zhygzhglxt.utility.sendMsg;

import java.util.Random;

/**
 * @author Chamber
 * @date 2018/1/17.
 */
public class RandomString {

    /**
     * 生成六位随机数
     *
     * @return
     */
    public static String sixRandomNumber() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        return result;
    }
}
