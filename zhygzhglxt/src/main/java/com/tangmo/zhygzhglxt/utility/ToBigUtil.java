package com.tangmo.zhygzhglxt.utility;

import java.util.List;

/**
 * Created by chengge on 2019/1/19.
 */
public class ToBigUtil {

    public static String comporeTo(List<Double> list) {
        Double min, max;
        max = list.get(0);
        min = list.get(0);
        System.out.print("数组A的元素包括：");
        for (int i = 0; i < list.size(); i++) {// i<5
            System.out.print(list.get(i) + " " + "=" + i + "==,");
            if (list.get(i) > max)   // 判断最大值
                max = list.get(i);
            if (list.get(i) < min)   // 判断最小值
                min = list.get(i);
        }
        System.out.println("\n数组的最大值是：" + max); // 输出最大值
        System.out.println("数组的最小值是：" + min + "==下标为"); // 输出最小值
        return String.valueOf(min);
    }
}
