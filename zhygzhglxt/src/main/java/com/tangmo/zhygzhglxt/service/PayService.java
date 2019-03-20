package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.Pay;
import com.tangmo.zhygzhglxt.entity.WeChatPayResultBean;
import com.tangmo.zhygzhglxt.utility.Result;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author chengge
 * @date 18/12/24
 * @description
 */

public interface PayService {

    /**
     * 增加支付信息
     *
     * @param pay
     * @return
     */
    Result addPayInfo(Pay pay);

    /**
     * 增加支付信息
     *
     * @param map
     * @return
     */
    Result addPayInfo(Map<String, String> map);

    /**
     * 查询订单信息
     *
     * @param tradeNo
     * @return
     */
    Result selectByTradeNo(String tradeNo);

    /**
     * 更新微信回调的支付结果
     *
     * @param map
     * @return
     */
    int updatePayResult(Map<String, String> map);

    /**
     * 支付订单
     *
     * @param userCode
     * @param passengerOrderCode
     * @return
     */
    Result payOrder(String userCode, String passengerOrderCode);

    /**
     * 支付订单时获取预支付信息
     *
     * @param total_fee 总金额
     * @param orderNo   订单号
     * @param url       回调地址
     * @return
     */
    WeChatPayResultBean getWeChatPayInfo(int total_fee, String orderNo, String url);
}
