package com.tangmo.emall.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderRelated {

    private static long orderNum = 0l;
    private static String date;

    /**
     * 生成商品订单编号
     *
     * @return
     */
    public static synchronized String getOrderNo() {
        Random random = new Random();
        Integer x = random.nextInt(123456);//180629173800011506200
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if (date == null || !date.equals(str)) {
            date = str;
            orderNum = 0l;
        }
        orderNum++;
        long orderNo = Long.parseLong((date)) * 10000;
        orderNo += orderNum;
        return orderNo + x.toString() + "00";
    }
}
