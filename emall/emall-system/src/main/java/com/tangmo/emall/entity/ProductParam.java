package com.tangmo.emall.entity;

import lombok.Data;

@Data
public class ProductParam {

    private Integer lId;
    private Integer productId;
    private Integer valueId;

    private ParamValue paramValue;
}
