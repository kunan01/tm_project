package com.tangmo.emall.dao;

import com.tangmo.emall.entity.Recommend;
import com.tangmo.emall.entity.RecommendAdvertising;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisingDao {

    //获取热门活动广告
    List<RecommendAdvertising> getAdvertisingList();

    //添加热门活动广告
    int addAdvertising(RecommendAdvertising recommendAdvertising);

    //通过活动广告id获取信息
    RecommendAdvertising getAdvertisingById(Integer raId);

    //修改活动广告
    int updateAdvertising(RecommendAdvertising recommendAdvertising);

    //删除活动广告
    int delAdvertising(Integer raId);

    //删除广告下对应的商品数据
    int delAdvertisingProduct(Integer raId);

    //删除广告下对应的商品数据
    int delAdvertisingProductById(Integer trId);

    //添加热门活动广告商品
    int addAdvertisingProduct(Recommend recommend);

    //获取热门活动商品详情
    Recommend getRecommendById(Integer trId);


}
