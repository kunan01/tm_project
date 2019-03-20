package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.dto.BalanceOrderDto;
import com.tangmo.zhygzhglxt.utility.Result;

/**
 * Created by chengge on 2019/1/14.
 */
public interface AliwithdrawalService {

    Result aliwithdrawalOrder(BalanceOrderDto balanceOrderDto);


}
