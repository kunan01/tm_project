package com.tangmo.zhjy.system.modules.vo;


import java.io.Serializable;
import java.sql.Date;

/**
 * @author boge
 * @date 17/12/25
 * @description 商品信息实体类VO
 */

public class CommodityVO implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer cdId;
    private Integer userId;
    private String username;
    private String imgId;
    private String[] imgList;
    private String title;
    private Integer cdState;
    private Integer cdCount;
    private Integer isQuality;
    private Double pricePre;
    private Double priceNow;
    private Integer state;//0未审核 1：审核通过 2：审核未通过


    private String shop_name;
    private String phone;
    private String cdColor;
    private String cdSize;
    private String shopLog;
    private String content;

    public Integer getIsQuality() {
        return isQuality;
    }

    public void setIsQuality(Integer isQuality) {
        this.isQuality = isQuality;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShopLog() {
        return shopLog;
    }

    public void setShopLog(String shopLog) {
        this.shopLog = shopLog;
    }

    public String getCdColor() {
        return cdColor;
    }

    public void setCdColor(String cdColor) {
        this.cdColor = cdColor;
    }

    public String getCdSize() {
        return cdSize;
    }

    public void setCdSize(String cdSize) {
        this.cdSize = cdSize;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Integer getCdState() {
        return cdState;
    }

    public void setCdState(Integer cdState) {
        this.cdState = cdState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getImgId() {
        return imgId;
    }

    public String[] getImgList() {
        return imgList;
    }

    public void setImgList(String[] imgList) {
        this.imgList = imgList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getCdCount() {
        return cdCount;
    }

    public void setCdCount(Integer cdCount) {
        this.cdCount = cdCount;
    }

    public Double getPricePre() {
        return pricePre;
    }

    public void setPricePre(Double pricePre) {
        this.pricePre = pricePre;
    }

    public Double getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(Double priceNow) {
        this.priceNow = priceNow;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    private String imgIds;

    public String getImgIds() {
        return imgIds;
    }

    public void setImgId(String imgId) {
        if(imgId == null){
            this.imgList = new String[]{};
        }else {
            String[] list = imgId.split(",");
            this.imgIds = imgId;
            this.imgId = list[0];
            this.imgList = list;
        }
    }
    private String condition;
}
