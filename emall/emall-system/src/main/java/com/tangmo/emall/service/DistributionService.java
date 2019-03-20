package com.tangmo.emall.service;

import com.tangmo.emall.entity.ShippingInfo;
import com.tangmo.emall.utils.Result;

public interface DistributionService {

    //获取店铺配送方式
    Result getStoreDistribution(Integer shopId,Integer pageNo,Integer pageSize);

    //修改店铺配送方式
    Result updateDistribution(ShippingInfo shippingInfo);

    //添加店铺配送方式
    Result addDistribution(ShippingInfo shippingInfo);

    //删除店铺配送方式
    Result delDistribution(Integer shipId);

    //批量删除店铺配送方式
    Result batchDelDistribution(ShippingInfo shippingInfo);
}
