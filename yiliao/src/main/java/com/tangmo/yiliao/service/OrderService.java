package com.tangmo.yiliao.service;

import com.tangmo.yiliao.entity.PayOrder;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2019-1-3
 * @description
 */
public interface OrderService {

    //添加订单
    Result addOrder(PayOrder payOrder);

    //积分换算规则数量
    Result getIntegral(Byte type);

    //获取用户是否绑定小程序openId状态
    Result getUserTypeOpenId(String userId);

    //绑定用户openId
    Result addUserOpenId(String userId,String code);
}
