package com.tangmo.emall.dao;

import com.tangmo.emall.entity.ShippingInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributionDao {

    //获取店铺配送方式
    List<ShippingInfo> getStoreDistribution(Integer shopId);

    //修改店铺配送方式
    int updateDistribution(ShippingInfo shippingInfo);

    //通过配送方式id获取配送详情
    ShippingInfo getDistributionById(Integer shipId);

    //添加店铺配送方式
    int addDistribution(ShippingInfo shippingInfo);

    //删除店铺配送方式
    int delDistribution(Integer shipId);
}
