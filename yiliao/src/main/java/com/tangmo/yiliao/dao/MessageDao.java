package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.Dialogue;
import com.tangmo.yiliao.entity.Message;
import com.tangmo.yiliao.entity.Read;
import com.tangmo.yiliao.entity.SelectUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hanjialin
 * @date 2018-9-12
 * @description
 */
@Mapper
public interface MessageDao {

    //获取最新消息标题  0系统 1回复 2打赏 3提醒
    String getMessageTitle(@Param("type") Integer type,@Param("userId") String userId);

    //获取消息读取状态
    Read getMessageState(String userId);

    //修改读取状态  type:消息类型     state:状态
    int updMessageState(@Param("type") Byte type,@Param("state") Integer state,@Param("userId") String userId);

    //分类获取消息集合(分页)
    List<Message> getMessageAll(@Param("type") Byte type,@Param("userId") String userId,@Param("start") Integer start,@Param("end") Integer end);

    //增加医生积分余额
    int updDoctorCount(@Param("count") Integer count,@Param("userId") String userId);

    //减少用户积分余额
    int updUserCountJ(@Param("count") Integer count,@Param("userId") String userId);

    //通过用户id获取用户昵称
    String getUserName(String userId);
    String getName(String userId);

    //通过视频id获取医生id
    String getDoctorId(String sId);

    //通过问答id获取用户id
    String getUserId(String sId);

    //增加视频积分余额
    int updDoctorVideoCount(@Param("count") Integer count,@Param("dvId") String dvId);

    //添加消息
    int addMessage(Message message);

    //添加聊天
    int addDic(Dialogue dialogue);

    //获取聊天信息
    List<Dialogue> getDic(SelectUser selectUser);

    //获取用户的咨询列表中所有医生id
    List<String> getUserConsultingAll(SelectUser selectUser);

    //通过医生id获取咨询列表
    Dialogue getUserConsAll(@Param("originatorId") String originatorId,@Param("peopleId") String peopleId);

    //通过用户id获取咨询列表
    Dialogue getUserByIdConsAll(@Param("originatorId") String originatorId,@Param("peopleId") String peopleId);
}
