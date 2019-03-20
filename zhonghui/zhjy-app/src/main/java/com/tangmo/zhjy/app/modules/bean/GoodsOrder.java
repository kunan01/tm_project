package com.tangmo.zhjy.app.modules.bean;


import java.sql.Date;

/**
 * @author boge
 * @date 18/3/8
 * @description
 */
public class GoodsOrder {
    private Integer goId;
    private Integer cdId;
    private Integer uaId;

    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

    private Integer userId;
    private Integer merchantId;
    private Integer goCount;
    private Byte orderState;
    private String orderNumber;
    private String transNumber;
    private Double goodsPrice;
    private String payNumber;

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    /**物流*/
    private Double expressFee;
    private Double discountFee;
    private Double payFee;
    private String expressNo;


    private Date payTime;
    private Date createTime;
    private Date updateTime;

    /**商品信息*/
    private String imgId;
    private String title;
    private String content;
    private String cdSize;
    private String cdColor;

    private String[] imgList;

    private String imgIds;

    public String getImgIds() {
        return imgIds;
    }

    public void setImgIds(String imgIds) {
        this.imgIds = imgIds;
    }

    public String[] getImgList() {
        return imgList;
    }

    public void setImgList(String[] imgList) {
        this.imgList = imgList;
    }

    private UserAddress address;

    public Integer getGoId() {
        return goId;
    }

    public void setGoId(Integer goId) {
        this.goId = goId;
    }

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public Integer getUaId() {
        return uaId;
    }

    public void setUaId(Integer uaId) {
        this.uaId = uaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getGoCount() {
        return goCount;
    }

    public void setGoCount(Integer goCount) {
        this.goCount = goCount;
    }

    public Byte getOrderState() {
        return orderState;
    }

    public void setOrderState(Byte orderState) {
        this.orderState = orderState;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTransNumber() {
        return transNumber;
    }

    public void setTransNumber(String transNumber) {
        this.transNumber = transNumber;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(Double expressFee) {
        this.expressFee = expressFee;
    }

    public Double getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(Double discountFee) {
        this.discountFee = discountFee;
    }

    public Double getPayFee() {
        return payFee;
    }

    public void setPayFee(Double payFee) {
        this.payFee = payFee;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

    public String getImgId() {
        return imgId;
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

    public String getCdSize() {
        return cdSize;
    }

    public void setCdSize(String cdSize) {
        this.cdSize = cdSize;
    }

    public String getCdColor() {
        return cdColor;
    }

    public void setCdColor(String cdColor) {
        this.cdColor = cdColor;
    }
}
