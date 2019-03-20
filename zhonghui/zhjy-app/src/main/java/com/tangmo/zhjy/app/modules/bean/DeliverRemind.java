package com.tangmo.zhjy.app.modules.bean;


/**
 * @author boge
 * @date 18/3/10
 * @description 发货提醒数据
 */
public class DeliverRemind {
    private Integer drId;
    private Integer goId;
    private Integer userId;
    private Integer merchantId;
    private Byte isRead;

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public Integer getGoId() {
        return goId;
    }

    public void setGoId(Integer goId) {
        this.goId = goId;
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

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }
}
