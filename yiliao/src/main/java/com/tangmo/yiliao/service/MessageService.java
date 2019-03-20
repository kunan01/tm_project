package com.tangmo.yiliao.service;

import com.tangmo.yiliao.entity.Dialogue;
import com.tangmo.yiliao.entity.Message;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2018-9-13
 * @description
 */
public interface MessageService {

    //获取消息标题
    Result getMessageTitle(String roleId,String userId);

    //获取消息读取状态
    Result getMessageState(String roleId,String userId);

    //分类获取消息集合(分页)
    Result getMessageAllByType(Byte type,String userId,Integer start,Integer end);

    //积分打赏
    Result IntegralExceptional(Message message);

    //积分打赏医生
    Result IntegralExceptionalDoctor(Message message);

    //获取对话信息
    Result getUserDia(SelectUser selectUser);

    //用户进入对话
    Result EnterTheDialogue(SelectUser selectUser);

    //添加会话消息
    Result addUserDia(Dialogue dialogue);

    //获取咨询消息列表
    Result getConsultingAll(SelectUser selectUser);
}
