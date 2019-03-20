package com.tangmo.zhjy.app.modules.dto;

import lombok.Data;

/**
 * @author boge
 * @date 18/3/17
 * @description
 */

public class ShopServiceDto {

    private Byte type;
    private Double priceStart;
    private Double priceEnd;
    private Byte price;
    private String city;
    private String district;
    private Integer start;
    private Integer end;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
