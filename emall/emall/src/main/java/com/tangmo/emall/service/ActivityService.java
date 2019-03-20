package com.tangmo.emall.service;

import com.tangmo.emall.entity.DiscountUser;
import com.tangmo.emall.utils.Result;

public interface ActivityService {

    //获取热门活动广告
    Result getAdvertisingList();

    //获取趋势列表
    Result getTrendList();

    //获取限时活动广告
    Result getTimeLimitedAdvertisingList();

    //用户获取兑换码
    Result getExchangeCode(DiscountUser discountUser);

    //获取当前是否存在限时活动
    Result getTimeLimitedAdvertisingState();
}
