package com.tangmo.zhjy.app.modules.dto;


/**
 * @author boge
 * @date 18/1/10
 * @description 商品查询对象
 */

public class CommodityDto {

    private Byte cdType;
    private Byte cdClass;
    private Boolean star;
    private Double priceStart;
    private Double priceEnd;
    private Byte price;
    private String city;
    private String district;
    private Integer start;
    private Integer end;
    private Integer isQuality;
    private String condition;

    public Integer getIsQuality() {
        return isQuality;
    }

    public void setIsQuality(Integer isQuality) {
        this.isQuality = isQuality;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Byte getCdType() {
        return cdType;
    }

    public void setCdType(Byte cdType) {
        this.cdType = cdType;
    }

    public Byte getCdClass() {
        return cdClass;
    }

    public void setCdClass(Byte cdClass) {
        this.cdClass = cdClass;
    }

    public Boolean getStar() {
        return star;
    }

    public void setStar(Boolean star) {
        this.star = star;
    }

    public Double getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(Double priceStart) {
        this.priceStart = priceStart;
    }

    public Double getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(Double priceEnd) {
        this.priceEnd = priceEnd;
    }

    public Byte getPrice() {
        return price;
    }

    public void setPrice(Byte price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
