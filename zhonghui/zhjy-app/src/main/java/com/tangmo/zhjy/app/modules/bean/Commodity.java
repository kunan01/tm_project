package com.tangmo.zhjy.app.modules.bean;


import java.io.Serializable;
import java.sql.Date;

/**
 * @author boge
 * @date 17/12/25
 * @description 商品信息实体类PO
 */

public class Commodity implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer cdId;
    private Integer userId;
    private String username;
    private String avatarId;
    private String imgId;
    private String[] imgList;
    private String title;
    private String content;
    private Byte cdType;
    private Integer cdCount;
    private Double pricePre;
    private Double priceNow;
    private String cdColor;
    private String cdSize;
    private Byte isQuality;
    private Byte isSell;
    private Integer SellCount;

    public Integer getSellCount() {
        return SellCount;
    }

    public void setSellCount(Integer sellCount) {
        SellCount = sellCount;
    }

    private String province;

    private String shop_name;

    private String phone;
    private String degree;
    private Integer state;//0未审核 1：审核通过 2：审核未通过

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
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

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getCdType() {
        return cdType;
    }

    public void setCdType(Byte cdType) {
        this.cdType = cdType;
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

    public Byte getIsQuality() {
        return isQuality;
    }

    public void setIsQuality(Byte isQuality) {
        this.isQuality = isQuality;
    }

    public Byte getIsSell() {
        return isSell;
    }

    public void setIsSell(Byte isSell) {
        this.isSell = isSell;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public Integer getCdClass() {
        return cdClass;
    }

    public void setCdClass(Integer cdClass) {
        this.cdClass = cdClass;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(Double discountFee) {
        this.discountFee = discountFee;
    }

    public Double getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(Double expressFee) {
        this.expressFee = expressFee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private String city;
    private String district;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Integer cdClass;
    private String cdName;
    private Integer star;
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

    public String getCdName() {
        return cdName;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName;
    }

    private String condition;
    private Double discountFee;
    private Double expressFee;
    private Date createTime;
    private Date updateTime;

    public Byte getCdState() {
        return cdState;
    }

    public void setCdState(Byte cdState) {
        this.cdState = cdState;
    }

    private Byte cdState;
}
