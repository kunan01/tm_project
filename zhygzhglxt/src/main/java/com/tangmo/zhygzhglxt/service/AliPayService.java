package com.tangmo.zhygzhglxt.service;


import com.tangmo.zhygzhglxt.utility.Result;

import java.util.Map;

public interface AliPayService {

    /**
     * 支付订单
     *
     * @param userCode
     * @param passengerOrderCode
     * @return
     */
    @SuppressWarnings("rawtypes")
    Result payOrder(String userCode, String passengerOrderCode);

    String notify(Map<String, String> conversionParams);

}
