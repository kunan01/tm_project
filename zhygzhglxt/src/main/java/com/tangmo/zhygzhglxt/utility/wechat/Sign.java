package com.tangmo.zhygzhglxt.utility.wechat;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class Sign {


    /*	public static String sign(){

            String appid = "wxd930ea5d5a258f4f";
            String mch_id = "10000100";
            String device_info="1000";
            String body ="test";
            String nonce_str="ibuaiVcKdpRxkhJA";

            SortedMap<Object,Object> parameters = new TreeMap<Object, Object>();
            parameters.put("appid", appid);
            parameters.put("mch_id", mch_id);
            parameters.put("device_info", device_info);
            parameters.put("body", body);
            parameters.put("nonce_str", nonce_str);
            String sign=createSign(parameters);
            System.out.println(sign);
            return null;
        }*/
    public static String createSign(SortedMap<Object, Object> parameters, String key) {

        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator<?> it = es.iterator();
        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }

        }
        sb.append("key=" + key);
        System.out.println("===二次要转成大写的签名=========" + sb.toString() + "===============");
        return MD5Utils.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
    }

}
