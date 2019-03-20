package com.tangmo.emall.service;

import com.tangmo.emall.utils.Result;

/**
 * @author hanjialin
 * @date 2019/1/9.
 * @Description
 */
public interface PaypalService {

    //创建paypal支付
    Result createPayment(Integer userId,String detailId);

    String executePayment(String paymentId,String payerId);
}
