package com.tangmo.zhygzhglxt.entity.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by chengge on 2019/1/14.
 */
public class BalanceOrderDto implements Serializable {


    private static final long serialVersionUID = 123347879457425L;

    /**
     * 用户的唯一标识code
     */
    @ApiModelProperty(value = "用户的唯一标识code")
    private String userCode;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    /**
     * 支付宝账户
     */
    @ApiModelProperty(value = "支付宝账户")
    private String payeeAccount;

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
