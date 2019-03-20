package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

public class Pay {

    /**
     * 公众号ID
     */
    @ApiModelProperty(value = "公众号ID")
    private String appid;//公众号ID

    /**
     * 商户号
     */
    @ApiModelProperty(value = "商户号")
    private String mch_id;//商户号

    /**
     * 随机字符串
     */
    @ApiModelProperty(value = "随机字符串")
    private String nonce_str;//随机字符串

    /**
     * 签名
     */
    @ApiModelProperty(value = "签名")
    private String sign;//签名

    /**
     * 商品描述
     */
    @ApiModelProperty(value = "商品描述")
    private String body;//商品描述

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String out_trade_no;//订单号

    /**
     * 总金额
     */
    @ApiModelProperty(value = "总金额")
    private int total_fee;//总金额

    /**
     * 终端IP
     */
    @ApiModelProperty(value = "终端IP")
    private String spbill_create_ip;//终端IP

    /**
     * 通知地址
     */
    @ApiModelProperty(value = "通知地址")
    private String notify_url;//通知地址

    /**
     * 交易类型
     */
    @ApiModelProperty(value = "交易类型")
    private String trade_type;//交易类型

    /**
     * 支付用户唯一open_id
     */
    @ApiModelProperty(value = "价格")
    private String openid; //支付用户唯一open_id;

    /**
     * 设备信息
     */
    @ApiModelProperty(value = "设备信息")
    private String device_info;

    /**
     * 商户退款单号
     */
    @ApiModelProperty(value = "价格")
    private String out_refund_no;//商户退款单号;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回信息")
    private String return_msg;

    /**
     * 返回结果
     */
    @ApiModelProperty(value = "返回结果")
    private String result_code;

    /**
     * 用户的唯一标识
     */
    @ApiModelProperty(value = "用户的唯一标识")
    private String userCode;

    /**
     * 支付目标
     */
    @ApiModelProperty(value = "支付目标")
    private String payTarget;

    /**
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型")
    private String payType;

    public Pay() {
    }

    public Pay(String appid, String mch_id, String nonce_str, String sign, String body) {
        this.appid = appid;
        this.mch_id = mch_id;
        this.nonce_str = nonce_str;
        this.sign = sign;
        this.body = body;
    }

    public Pay(WeChatPayResultBean weChatPayResultBean) {
        this.out_trade_no = weChatPayResultBean.getOut_trade_no();
        this.userCode = weChatPayResultBean.getUserCode();
        this.trade_type = weChatPayResultBean.getTrade_type();
        this.total_fee = weChatPayResultBean.getTotal_fee();
    }

    @Override
    public String toString() {
        return "Pay [appid=" + appid + ", mch_id=" + mch_id + ", nonce_str="
                + nonce_str + ", sign=" + sign + ", body=" + body
                + ", out_trade_no=" + out_trade_no + ", total_fee=" + total_fee
                + ", spbill_create_ip=" + spbill_create_ip + ", notify_url="
                + notify_url + ", trade_type=" + trade_type + ", openid="
                + openid + ", device_info=" + device_info + "]";
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPayTarget() {
        return payTarget;
    }

    public void setPayTarget(String payTarget) {
        this.payTarget = payTarget;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
