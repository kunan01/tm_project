package com.tangmo.emall.entity.dto;

import lombok.Data;


@Data
public class ProductDto {

    //标签id
    private Integer[] lIdList;

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

    //排序方式id
    private Byte sorting;

    //排序规则方式
    private String sortingRules;

    //开始价格
    private Integer startPrice;

    //结束价格
    private Integer endPrice;

    //页码
    private Integer pageNo;

    //条数
    private Integer pageSize;

    //商品分类id
    private Integer categoryId;

    //趋势id
    private Integer taId;

    //折扣状态
    private Integer disType;

    //热门活动id
    private Integer raId;

}
