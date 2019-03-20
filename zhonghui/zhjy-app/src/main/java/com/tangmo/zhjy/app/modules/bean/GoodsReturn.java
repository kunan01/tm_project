package com.tangmo.zhjy.app.modules.bean;


/**
 * @author boge
 * @date 18/3/10
 * @description 退款数据
 */
public class GoodsReturn {

    private Integer grId;
    private Integer userId;
    private Integer goId;
    private Integer merchantId;
    private String reason;
    private String grExplain;

    public String getGrExplain() {
        if(grExplain == null){
            return "";
        }
        return grExplain;
    }

    public void setGrExplain(String grExplain) {
        if(grExplain == null){
            grExplain = "";
        }
        this.grExplain = grExplain;
    }

    public Integer getGrId() {
        return grId;
    }

    public void setGrId(Integer grId) {
        this.grId = grId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoId() {
        return goId;
    }

    public void setGoId(Integer goId) {
        this.goId = goId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
