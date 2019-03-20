package com.tangmo.zhjy.app.modules.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppMyChannelBean;

import java.util.List;

@Mapper
public interface AppMyChannelBeanMapper {
    int deleteByPrimaryKey(@Param("userId")Integer id,@Param("twoClassifyId")Integer twoClassifyId);

    int insert(AppMyChannelBean record);

    int insertSelective(AppMyChannelBean record);

    AppMyChannelBean selectByPrimaryKey(Integer id);

    AppMyChannelBean selectByValue(@Param("userId") Integer userId,@Param("twoClassifyid") Integer twoClassifyid);

    int updateByPrimaryKeySelective(AppMyChannelBean record);

    int updateByPrimaryKey(AppMyChannelBean record);

    int delByPrimaryKey(Integer id);

    List<AppMyChannelBean> getMyChannel(Integer userId);
}