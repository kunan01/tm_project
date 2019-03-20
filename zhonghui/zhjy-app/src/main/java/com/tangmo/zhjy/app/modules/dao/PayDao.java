package com.tangmo.zhjy.app.modules.dao;

import com.tangmo.zhjy.app.modules.bean.Pay;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author boge
 * @date 18/3/27
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
