package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.Pay;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengge
 * @date 18/12/24
 * @description
 */
@Mapper
public interface PayDao {

    /**
     * 增加预支付信息
     *
     * @param pay
     * @return
     */
    int insertPay(Pay pay);

    /**
     * 查询订单信息
     *
     * @param tradeNo
     * @return
     */
    Pay selectByTradeNo(String tradeNo);

    /**
     * 更新预支付结果
     *
     * @param pay
     * @return
     */
    int updateByTradeNo(Pay pay);

}
