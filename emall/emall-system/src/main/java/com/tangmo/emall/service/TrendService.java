package com.tangmo.emall.service;

import com.tangmo.emall.entity.Trend;
import com.tangmo.emall.entity.TrendAdvertising;
import com.tangmo.emall.utils.Result;

public interface TrendService {

    //获取趋势列表
    Result getTrendList(Integer pageNo,Integer pageSize);

    //修改趋势信息
    Result updTrend(TrendAdvertising trendAdvertising);

    //添加趋势信息
    Result addTrend(TrendAdvertising trendAdvertising);

    //删除趋势信息
    Result delTrend(Integer taId);

    //批量删除趋势信息
    Result batchDelTrend(TrendAdvertising trendAdvertising);

    //添加趋势商品
    Result addTrendProduct(Integer taId,Integer productId);

    //批量添加趋势商品
    Result batchAddTrendProduct(Trend trend);

    //删除趋势商品
    Result delTrendProduct(Integer ttId);

    //批量删除趋势商品
    Result batchDelTrendProduct(Trend trend);

    //获取趋势商品信息
    Result getTrendProductList(Integer taId,Integer pageNo,Integer pageSize);

}
