package com.tangmo.zhygzhglxt.entity;


import java.math.BigDecimal;

public class WeChatPayResultBean {

    private String partnerId;

    private String prepayId;

    private String userCode;

    private String nonceStr;

    private Long timeStamp;

    private String package2;

    private String sign;

    private String out_trade_no;

    private int total_fee;

    private String trade_type;


    @Override
    public String toString() {
        return "WeChatPayResult [partnerId=" + partnerId + ", prepayId=" + prepayId + ", nonceStr=" + nonceStr
                + ", timeStamp=" + timeStamp + ", package2=" + package2 + ", sign=" + sign + "]";
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPackage2() {
        return package2;
    }

    public void setPackage2(String package2) {
        this.package2 = package2;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
}
