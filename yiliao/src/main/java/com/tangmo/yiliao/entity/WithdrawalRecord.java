package com.tangmo.yiliao.entity;

import lombok.Data;

@Data
public class WithdrawalRecord {

    private String withdrawalId;
    private String userId;
    private String account;
    private Integer IntegralNumber;
    private Double IntegralMoney;
    private String createTime;
}
