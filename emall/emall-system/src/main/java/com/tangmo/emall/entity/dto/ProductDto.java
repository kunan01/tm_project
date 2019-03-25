package com.tangmo.emall.entity.dto;

import lombok.Data;

@Data
public class ProductDto {

    //店铺id
    private Integer shopId;

    //商品名
    private String productName;

    //品牌id
    private Integer brandId;

    //上下架状态 0未上架 1已上架
    private Byte publishStatus;

    //审核状态 0正在审核 1审核通过 2审核未通过
    private Byte auditStatus;

    //是否精品 0不是 1是
    private Byte isGood;

    //是否新品 0不是 1是
    private Byte isNew;

    //是否热销 0不是 1是
    private Byte isPopular;

    //排序方式
    private Byte sorting;

    private Integer pageNo;

    private Integer pageSize;

    private Integer[] productIdList;

    private Integer discount;

    private Integer taId;

    private Integer raId;
}
