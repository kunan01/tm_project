package com.tangmo.yiliao.entity;

import lombok.Data;

@Data
public class Pay {

    private String appid;//公众号ID
    private String mch_id;//商户号
    private String nonce_str;//随机字符串
    private String sign;//签名
    private String sign_type;
    private String body;//商品描述
    private String out_trade_no;//订单号
    private int total_fee;//总金额
    private Double total_fee1;
    private String spbill_create_ip;//终端IP
    private String notify_url;//通知地址
    private String trade_type;//交易类型
    private String openid; //支付用户唯一open_id;
    private String device_info;
    private String out_refund_no;//商户退款单号;
    private String return_msg;
    private String result_code;
    private String userId;
    private String payTarget;
    private Byte payType;

//    out_trade_no
//    user_id
//    trade_type
//    result_meg
//    result_code
//    total_fee
//    pay_target
//    pay_type
}
