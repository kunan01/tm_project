package com.tangmo.emall.dao;

import com.tangmo.emall.entity.Trend;
import com.tangmo.emall.entity.TrendAdvertising;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrendDao {

    //获取趋势列表
    List<TrendAdvertising> getTrendList();

    //通过趋势id获取趋势信息
    TrendAdvertising getTrendById(Integer taId);

    //修改趋势信息
    int updTrend(TrendAdvertising trendAdvertising);

    //通过描述语位置获取趋势数量
    Integer getTrendCountByLocation(Byte location);

    //添加趋势信息
    int addTrend(TrendAdvertising trendAdvertising);

    //删除趋势信息
    int delTrend(Integer taId);

    //添加趋势商品
    int addTrendProduct(Trend trend);

    //通过id删除趋势商品
    int delTrendProduct(Integer ttId);

    //通过趋势id删除趋势商品
    int delTrendProductByTaId(Integer taId);
}
