package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by chengge on 2019/1/14.
 */
public class TbBalanceOrder {

    @ApiModelProperty(value = "余额订单主键")
    private String balanceOrderId;

    @ApiModelProperty(value = "余额订单的唯一标识")
    private String balanceOrderCode;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "类型（1充值 2提现）")
    private String type;

    @ApiModelProperty(value = "用户的唯一标识")
    private String userCode;

    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    private String flag;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "修改时间")
    private String updateTime;

    @ApiModelProperty(value = "状态（0未成功 1成功）")
    private String state;

    @ApiModelProperty(value = "使用类型（1微信 2支付宝）")
    private String useType;

    @ApiModelProperty(value = "交易类型 app")
    private String tradeType;

    @ApiModelProperty(value = "交易订单号")
    private String balanceNumber;

    @ApiModelProperty(value = "交易名称")
    private String tradeName;

    @ApiModelProperty(value = "交易的支付宝账号")
    private String payeeAccount;

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getBalanceNumber() {
        return balanceNumber;
    }

    public void setBalanceNumber(String balanceNumber) {
        this.balanceNumber = balanceNumber;
    }

    public String getBalanceOrderId() {
        return balanceOrderId;
    }

    public void setBalanceOrderId(String balanceOrderId) {
        this.balanceOrderId = balanceOrderId;
    }

    public String getBalanceOrderCode() {
        return balanceOrderCode;
    }

    public void setBalanceOrderCode(String balanceOrderCode) {
        this.balanceOrderCode = balanceOrderCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
