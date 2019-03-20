package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.utility.Result;

/**
 * Created by chengge on 2019/3/12.
 */
public interface TbListenOrderService {

    Result addListenOrder(String passengerOrderCode);

    Result passengerListenOrder(String passengerOrderCode);

    Result driverListenOrder(String driverOrderCode);

    Result updateListenOrder(String driverOrderCode, String state);

    Result listenAllOrder();
}
