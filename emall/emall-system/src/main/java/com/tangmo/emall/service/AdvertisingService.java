package com.tangmo.emall.service;

import com.tangmo.emall.entity.Recommend;
import com.tangmo.emall.entity.RecommendAdvertising;
import com.tangmo.emall.utils.Result;

public interface AdvertisingService {

    //获取热门活动广告
    Result getAdvertisingList(Integer pageNo,Integer pageSize);

    //添加热门活动广告
    Result addAdvertising(RecommendAdvertising recommendAdvertising);

    //修改热门活动广告
    Result updateAdvertising(RecommendAdvertising recommendAdvertising);

    //删除热门活动广告
    Result delAdvertising(Integer raId);

    //批量删除热门活动广告
    Result batchDelAdvertising(RecommendAdvertising recommendAdvertising);

    //添加热门活动广告商品
    Result addAdvertisingProduct(Recommend recommend);

    //批量添加热门活动广告商品
    Result batchAddAdvertisingProduct(Recommend recommend);

    //删除热门活动广告商品
    Result delAdvertisingProduct(Integer trId);

    //批量删除活动广告商品
    Result batchDelAdvertisingProduct(Recommend recommend);

    //获取热门活动商品
    Result getAdvertisingProductList(Integer raId,Integer pageNo,Integer pageSize);


}
