package com.tangmo.zhjy.app.modules.vo;


/**
 * @author boge
 * @date 18/3/13
 * @description 商家商品信息管理页面展示
 */
public class CdManageVO {
    private Integer cdId;
    private String imgId;
    private String title;
    private Double priceNow;
    private Integer sellCount;
    private Integer cdCount;
    private Byte state;

    private String imgIds;

    private String[] imgList;

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

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
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

    public Double getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(Double priceNow) {
        this.priceNow = priceNow;
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public Integer getCdCount() {
        return cdCount;
    }

    public void setCdCount(Integer cdCount) {
        this.cdCount = cdCount;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
