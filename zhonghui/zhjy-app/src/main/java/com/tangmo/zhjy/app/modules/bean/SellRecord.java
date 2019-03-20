package com.tangmo.zhjy.app.modules.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author boge
 * @date 18/1/11
 * @description 卖出记录实体
 */
@Data
public class SellRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer srId;
    private Integer userId;
    private Double amount;
    private Byte isShow;
    private Date createTime;
    private Integer srCount;
    private String srColor;
    private String srSize;

    /**
     * 商品简要信息
     */
    private Integer goodsId;
    private String imgId;
    private String title;
    private String content;
    private Double price;



    public SellRecord() {
    }

    public SellRecord(GoodsOrder goodsOrder){
        this.userId = goodsOrder.getUserId();
        this.srCount = goodsOrder.getGoCount();
        this.goodsId = goodsOrder.getCdId();
        this.srColor = goodsOrder.getCdColor();
        this.srSize = goodsOrder.getCdSize();
        this.amount = goodsOrder.getPayFee();
    }
}
