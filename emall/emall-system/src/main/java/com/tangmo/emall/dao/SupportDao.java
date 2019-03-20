package com.tangmo.emall.dao;

import com.tangmo.emall.entity.CustomerService;
import com.tangmo.emall.entity.MessageUs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportDao {

    //获取客户反馈信息列表
    List<MessageUs> getUsersMessageList(@Param("state") Integer state);

    //通过id获取客户反馈信息
    MessageUs getUsersMessage(Integer mId);

    //通过id处理反馈信息（更换反馈状态）
    int updMessageUsState(Integer mId);

    //获取客服服务信息
    List<CustomerService> getCustomerService();

    //修改客服服务信息
    int updateCustomerService(CustomerService customerService);
}
