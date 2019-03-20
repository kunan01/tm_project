package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.Pay;
import com.tangmo.yiliao.entity.PayOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hanjialin
 * @date 2019-1-3
 * @description
 */
@Mapper
public interface PayDao {

    //通过id查询支付信息
    Pay getPayById(String tradeNo);

    //新增支付信息
    int insertPay(Pay pay);

    //回调后修改支付信息
    int updateByTradeNo(Pay pay);

}
