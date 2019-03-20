package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShippingInfo implements Serializable {

    private Integer shipId;
    private String descript;
    private Double price;
    private Integer shopId;
    private String createdTime;
}
