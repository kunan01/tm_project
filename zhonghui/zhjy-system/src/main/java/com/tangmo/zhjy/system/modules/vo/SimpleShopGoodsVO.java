package com.tangmo.zhjy.system.modules.vo;

import java.io.Serializable;

/**
 * @author boge
 * @date 18/3/13
 * @description 店铺服务列表简要信息vo
 */

public class SimpleShopGoodsVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer sgId;
    private String shopName;
    private String shopContent;
    private String imgId;
    private Double price;
    private String city;
    private String district;


    private Integer userId;
    private String userName;
    private String mobile;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setImgId(String imgId) {
        if(imgId != null){
            String[] list = imgId.split(",");
            this.imgId = list[0];
        }else{
            this.imgId = imgId;
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSgId() {
        return sgId;
    }

    public void setSgId(Integer sgId) {
        this.sgId = sgId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopContent() {
        return shopContent;
    }

    public void setShopContent(String shopContent) {
        this.shopContent = shopContent;
    }

    public String getImgId() {
        return imgId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
}
