package com.tangmo.yiliao.config;

public class PayWXConfig {

    public static final String Key = "7Bh7J7m5dl5Zxglj9d22rH23os9uhq55";

    public static final String appid = "wx4c34bc52f437271e"; //应用ID 必填：true

    public static final String mch_id = "1452763402";//商户号    必填：true

    //111.230.242.116
    public static final String spbill_create_ip="114.115.211.170"; //终端IP 必填：true

    public static final String device_info="WEB";//设备号  必填：false

    public static final String body = "康珍视积分充值";//商品描述 必填：true

    public static final String trade_type="APP";//交易类型  必填：true

    public static final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static final String order_url = "https://bjzh.yl120.cn:8443/pay/order/callback";
}
