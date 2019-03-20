package com.tangmo.zhjy.app.modules.bean;

/**
 * @author Chamber
 * @date 2018/3/8. 订单列表展示信息
 */

public class GoodsOrderSimple {

    private Integer goId;
    private Integer cdId;
    private Integer uaId;
    private Integer userId;
    private Byte orderState;
    private String orderNumber;
    private Integer goCount;
    private String cdSize;
    private String cdColor;
    private Double expressFee;
    private Double discountFee;
    private Double payFee;
    /**商品信息*/
    private String imgId;
    private String imgIds;
    private String[] imgList;
    private String title;
    private String content;
    private String priceNow;
    private Integer scId;

    public String getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(String priceNow) {
        this.priceNow = priceNow;
    }

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

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

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

    public Integer getGoCount() {
        return goCount;
    }

    public void setGoCount(Integer goCount) {
        this.goCount = goCount;
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
}
