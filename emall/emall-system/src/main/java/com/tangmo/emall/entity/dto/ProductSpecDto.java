package com.tangmo.emall.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductSpecDto {

    @ApiModelProperty(value="规格id")
    private Integer specId;

    @ApiModelProperty(value="规格json")
    private String productSpecs;

    @ApiModelProperty(value="库存")
    private Integer stock;

    @ApiModelProperty(value="价格")
    private Double price;

    @ApiModelProperty(value="是否删除 0否 1是")
    private Byte dataFlag;

    @ApiModelProperty(value="商品图片集合")
    private List<ProductImageDto> productImageDtoList;


}
