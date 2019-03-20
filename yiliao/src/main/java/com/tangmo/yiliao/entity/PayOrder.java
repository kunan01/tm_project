package com.tangmo.yiliao.entity;

import lombok.Data;

@Data
public class PayOrder {

    private String  goId;
    private Byte    goType;
    private String  userId;
    private String  uaId;
    private String  cdId;
    private Byte    orderState;
    private Integer goCount;
    private String  expressNo;
    private String  cdSize;
    private String  cdColor;
    private Double  expressFee;
    private Double  discountFee;
    private Double  payFee;
    private String  orderNumber;
    private String  payNumber;
    private String  createTime;
    private String  payTime;

}
