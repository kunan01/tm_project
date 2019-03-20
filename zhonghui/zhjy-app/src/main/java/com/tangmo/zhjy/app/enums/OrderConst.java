package com.tangmo.zhjy.app.enums;

/**
 * @author boge
 * @date 18/4/4
 * @description 订单状态
 */

public class OrderConst {

    /**
     * 订单取消
     */
    public static final Byte ORDER_CANCEL = -1;

    /**
     * 等待支付
     */
    public static final Byte ORDER_NEW = 0;

    /**
     * 交易完成
     */
    public static final Byte DEAL_DONE = 1;


    /**
     * 已发货
     */
    public static final Byte DELIVER = 2;


    /**
     * 已收货
     */
    public static final Byte DELIVER_RECEIVE = 3;

    /**
     * 申请退货
     */
    public static final Byte GOODS_RETURN = 4;

    /**
     * 退货完成
     */
    public static final Byte GOODS_RETURN_DONE = 5;

    /**
     * 交易关闭
     */
    public static final Byte DEAL_COLSE = 6;
}
