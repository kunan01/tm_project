package com.tangmo.yiliao.service;

import com.tangmo.yiliao.utility.code.Result;

import java.util.Map;

/**
 * @author hanjialin
 * @date 2019-1-3
 * @description
 */
public interface PayService {

    //支付宝支付统一下单
    Result payZHBOrder(String userId,String goId);

    //处理支付宝回调
    String notify(Map<String, String> conversionParams);

    //微信支付统一下单
    Result payWeChat(String userId,String goId);

    //处理微信回调
    Result updatePayResult(Map<String, String> smap);

    //微信小程序支付统一下单
    Result payWeChatXCX(String userId,String goId);

    //支付宝提现
    Result TreasureWithdrawal(String userId,String phone,String userCode,String account,Integer count,Double amount);

}
