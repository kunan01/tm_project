package com.tangmo.zhygzhglxt.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 服务类型类目
 */

@Data
@ToString
public class TbServiceType {

    @ApiModelProperty(value = "服务类型主键")
    private String serviceTypeId;//服务类型主键

    @ApiModelProperty(value = "名称")
    private String serviceTypeName;//名称

    @ApiModelProperty(value = "假删 0未删 1已删")
    private String flag;//假删 0未删 1已删

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
