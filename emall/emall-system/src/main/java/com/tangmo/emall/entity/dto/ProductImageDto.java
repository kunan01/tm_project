package com.tangmo.emall.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductImageDto {

    @ApiModelProperty(value="商品图片id")
    private Integer imageId;

    @ApiModelProperty(value="图片描述")
    private String imageDesc;

    @ApiModelProperty(value="图片地址")
    private String imageUrl;

    @ApiModelProperty(value="是否主图 0不是 1是")
    private Byte isMaster;

    @ApiModelProperty(value="图片排序")
    private Integer imageOrder;

    @ApiModelProperty(value="图片状态 0无效 1有效")
    private Byte imageStatus;
}
