package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.IntegralSubsidiary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description 存放执行多次的公共接口
 */
@Mapper
public interface ManyTimesDao {

    //增加积分余额明细记录
    int addIntegralSubsidiary(IntegralSubsidiary integralSubsidiary);

    //获取积分明细
    List<IntegralSubsidiary> getUserIntegralDetail(String userId);
}
