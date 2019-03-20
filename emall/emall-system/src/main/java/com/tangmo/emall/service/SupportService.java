package com.tangmo.emall.service;

import com.tangmo.emall.entity.CustomerService;
import com.tangmo.emall.entity.MessageUs;
import com.tangmo.emall.utils.Result;

public interface SupportService {

    //获取客户反馈信息列表
    Result getUsersMessageList(Integer state, Integer pageNo, Integer pageSize);

    //处理客户反馈信息
    Result dealWithMessage(Integer mId);

    //批量处理客户反馈信息
    Result batchDealWithMessage(MessageUs messageUs);

    //获取客服服务信息
    Result getCustomerService();

    //修改客服服务信息
    Result updateCustomerService(CustomerService customerService);
}
