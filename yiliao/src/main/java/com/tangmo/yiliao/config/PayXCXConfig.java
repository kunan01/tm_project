package com.tangmo.yiliao.config;

public class PayXCXConfig {

    public static final String Key = "7Bh7J7m5dl5Zxglj9d22rH23os9uhq55";

    public static final String appid = "wxf2d90e18e6b719b3"; //小程序Id 必填：true

    public static final String mch_id = "1452763402";//商户号    必填：true

    //111.230.242.116
    public static final String spbill_create_ip="114.115.211.170"; //终端IP 必填：true

    public static final String device_info="WEB";//设备号  必填：false

    public static final String body = "康珍视积分充值";//商品描述 必填：true

    public static final String trade_type="JSAPI";//交易类型  必填：true

    public static final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static final String order_url = "https://bjzh.yl120.cn:8443/pay/order/callback";
}
