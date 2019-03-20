package com.tangmo.zhjy.app.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppCommunityUserBean;
@Mapper
public interface AppCommunityUserBeanMapper {
    int deleteByPrimaryKey(@Param("userId")Integer userId,@Param("communityId")Integer communityId);

    int insert(AppCommunityUserBean record);

    int insertSelective(AppCommunityUserBean record);

    AppCommunityUserBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppCommunityUserBean record);

    int updateByPrimaryKey(AppCommunityUserBean record);
    
    AppCommunityUserBean findBycommunityIdAndUserId(@Param("userId")Integer userId,@Param("communityId")Integer communityId);
    
    List<AppCommunityUserBean> findById(@Param("id")Integer id);
}