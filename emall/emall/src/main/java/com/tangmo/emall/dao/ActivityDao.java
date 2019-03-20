package com.tangmo.emall.dao;

import com.tangmo.emall.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityDao {

    //获取热门活动广告
    List<RecommendAdvertising> getAdvertisingList();

    //获取趋势列表
    List<TrendAdvertising> getTrendList();

    //获取限时活动广告
    List<Discount> getDiscountList();

    //获取限时活动数量
    Integer getDiscountListCount();

    //通过限时活动id获取活动详情
    Discount getDiscountById(Integer disId);

    //减去限时活动领取数量
    int updDisObject(Integer disId);

    //获取用户优惠码
    DiscountUser getDisUserByOne(@Param("disId") Integer disId,@Param("userId") Integer userId);

    //添加用户优惠码
    int insertDisUser(DiscountUser discountUser);

}
