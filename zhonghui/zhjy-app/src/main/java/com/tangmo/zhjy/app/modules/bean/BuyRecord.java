package com.tangmo.zhjy.app.modules.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author boge
 * @date 18/1/11
 * @description 购买记录实体
 */
@Data
public class BuyRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer brId;
    private Integer userId;
    private Double amount;
    private Byte isShow;
    private Date createTime;
    private Integer brCount;
    private String brColor;
    private String brSize;

    /**
     * 商品简要信息
     */
    private Integer goodsId;
    private String imgId;
    private String title;
    private String content;
    private Double price;


    public BuyRecord() {
    }

    /**
     * 订单信息转换为购买记录
     * @param goodsOrder
     */
    public BuyRecord(GoodsOrder goodsOrder){
        this.userId = goodsOrder.getUserId();
        this.brCount = goodsOrder.getGoCount();
        this.goodsId = goodsOrder.getCdId();
        this.brColor = goodsOrder.getCdColor();
        this.brSize = goodsOrder.getCdSize();
        this.amount = goodsOrder.getPayFee();
    }
}
