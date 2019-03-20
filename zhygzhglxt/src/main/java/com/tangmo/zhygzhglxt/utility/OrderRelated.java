package com.tangmo.zhygzhglxt.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author chengge
 */

public class OrderRelated {

    private static long orderNum = 0l;
    private static String date;

    /**
     * 生成商品订单编号
     *
     * @return
     */
    public static synchronized String getOrderNo(Integer number) {
        Random random = new Random();
        Integer x = random.nextInt(number * 1000);//180629173800011506200
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

    //产生13位的订单号
    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%012d", hashCodeV);
    }


//    public static void main(String[] args) {
//        System.out.println(getOrderIdByUUId());
//    }
}
