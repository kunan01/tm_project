package com.tangmo.zhjy.app.modules.service;


import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.Pay;
import com.tangmo.zhjy.app.modules.bean.WeChatPayResultBean;

import java.util.Map;

/**
 * @author boge
 * @date 18/3/27
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
     * @param goId
     * @param userId
     * @return
     */
    Result payOrder(Integer userId, String goId);


    Result payOrderPC(Integer userId, String goId);


    /**
     * 支付订单时获取预支付信息
     *
     * @param total_fee 总金额
     * @param orderNo   订单号
     * @param url       回调地址
     * @return
     */
    WeChatPayResultBean getWeChatPayInfo(Double fee,int total_fee, String orderNo, String url);
}
