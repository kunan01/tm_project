package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;

import java.util.Map;

public interface TreasurePayService {

    /**
     * 支付订单
     *
     * @param goId
     * @param userId
     * @return
     */
    @SuppressWarnings("rawtypes")
    Result payOrder(Integer userId, String goId,Integer type);

    String notify(Map<String, String> conversionParams);
}
