package com.tangmo.zhjy.app.modules.bean;

import lombok.Data;

/**
 * @author boge
 * @date 18/3/9
 * @description 商家审核实体类
 */

public class ShopVerify {

    private Integer svId;
    private Integer userId;
    private String mobile;
    private String userName;
    private String idNumber;
    private String idFrontImg;
    private String idRearImg;
    private String shopName;
    private String logoImg;
    private String shopIntro;
    private String shopAddress;
    private String licenseImg;
    private Byte shopState;
    private String createTime;
    private String updateTime;

    public Integer getSvId() {
        return svId;
    }

    public void setSvId(Integer svId) {
        this.svId = svId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdFrontImg() {
        return idFrontImg;
    }

    public void setIdFrontImg(String idFrontImg) {
        this.idFrontImg = idFrontImg;
    }

    public String getIdRearImg() {
        return idRearImg;
    }

    public void setIdRearImg(String idRearImg) {
        this.idRearImg = idRearImg;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public String getShopIntro() {
        return shopIntro;
    }

    public void setShopIntro(String shopIntro) {
        this.shopIntro = shopIntro;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getLicenseImg() {
        return licenseImg;
    }

    public void setLicenseImg(String licenseImg) {
        this.licenseImg = licenseImg;
    }

    public Byte getShopState() {
        return shopState;
    }

    public void setShopState(Byte shopState) {
        this.shopState = shopState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
