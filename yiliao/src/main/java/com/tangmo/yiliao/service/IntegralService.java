package com.tangmo.yiliao.service;

import com.tangmo.yiliao.entity.Integral;
import com.tangmo.yiliao.entity.Message;
import com.tangmo.yiliao.entity.RsFile;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2018-9-5
 * @description
 */
public interface IntegralService {

    //后台统计用户积分总额数量
    Result getUserIntegralAllCount(SelectUser selectUser);

    //后台统计用户积分总额
    Result getUserIntegralAll(SelectUser selectUser);

    //获取所有积分规则信息
    Result getIntegralAll();

    //删除积分规则信息
    Result delIntegralById(String lrId,String userId);

    //修改积分规则信息
    Result updIntegralById(String lrId,String userId,String lrName,Integer bean);

    //添加积分规则信息
    Result addIntegralById(Integral integral);

    //条件获取所有系统消息
    Result getSystemMessage(SelectUser selectUser);

    //条件获取系统消息数量
    Result getSystemMessageCount(SelectUser selectUser);

    //后台添加系统消息
    Result addSystemMessage(Message message);

    //后台删除系统消息
    Result delSystemMessageById(String msId);

    //后台修改系统消息
    Result updSystemMessage(Message message);

    //后台获取短信请求地址
    Result getCode();

    //后台修改短信信息
    Result updCode(RsFile rsFile);
}
