package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 省市区
 */
@Data
@ToString
public class TbArea {

    @ApiModelProperty(value = "省市区主键")
    private String areaId;//省市区主键

    @ApiModelProperty(value = "省")
    private String areaProvince;//省

    @ApiModelProperty(value = "市")
    private String areaCity;//市

    @ApiModelProperty(value = "区")
    private String areaQu;//区

    @ApiModelProperty(value = "假删")
    private String flag;//假删

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaProvince() {
        return areaProvince;
    }

    public void setAreaProvince(String areaProvince) {
        this.areaProvince = areaProvince;
    }

    public String getAreaCity() {
        return areaCity;
    }

    public void setAreaCity(String areaCity) {
        this.areaCity = areaCity;
    }

    public String getAreaQu() {
        return areaQu;
    }

    public void setAreaQu(String areaQu) {
        this.areaQu = areaQu;
    }
}
