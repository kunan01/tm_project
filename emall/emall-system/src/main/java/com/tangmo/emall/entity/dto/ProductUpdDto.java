package com.tangmo.emall.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductUpdDto {

    @NotNull
    @ApiModelProperty(value="商品id")
    private Integer productId;

    @ApiModelProperty(value="商品名")
    private String productName;

    @ApiModelProperty(value="商品分类id")
    private Integer categoryId;

    @ApiModelProperty(value="商品描述")
    private String descript;

    @ApiModelProperty(value="基本属性 json串")
    private String baseProp;

    @ApiModelProperty(value="商品展示图片id")
    private String productImage;

    @ApiModelProperty(value="商品展示价格")
    private Double price;

    @ApiModelProperty(value="上下架状态 0未上架 1已上架")
    private Byte publishStatus;

    @ApiModelProperty(value="商品标签数组")
    private Integer[] ParamIdList;

    @ApiModelProperty(value="商品规格集合")
    private List<ProductSpecDto> productSpecDtoList;
}
