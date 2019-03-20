package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author boge
 * @date 2018-9-5
 * @description
 */
@Mapper
public interface IntegralDao {

    //后台统计用户积分总额数量
    Integer getUserIntegralAllCount(SelectUser selectUser);

    //后台统计用户积分总额
    List<User> getUserIntegralAll(SelectUser selectUser);

    //获取所有积分规则信息
    List<Integral> getIntegralAll();

    //删除积分规则信息
    int delIntegralById(@Param("lrId") String lrId,@Param("newLrId") String newLrId,@Param("userId") String userId);

    //修改积分规则信息
    int updIntegralById(@Param("lrId") String lrId,@Param("userId") String userId,@Param("lrName") String lrName,@Param("bean") Integer bean);

    //添加积分规则信息
    int addIntegralById(Integral integral);

    //条件获取所有系统消息
    List<Message> getSystemMessage(SelectUser selectUser);

    //条件获取所有系统消息数量
    Integer getSystemMessageCount(SelectUser selectUser);

    //修改用户系统消息读取状态
    int updMessageStateO();

    //添加系统消息
    int addMessage(Message message);

    //后台删除系统消息
    int delSystemMessageById(String msId);

    //后台修改系统消息
    int updSystemMessage(Message message);

    //后台获取短信实例
    RsFile getcode();

    //后台修改短信信息
    int updCode(RsFile rsFile);
}
