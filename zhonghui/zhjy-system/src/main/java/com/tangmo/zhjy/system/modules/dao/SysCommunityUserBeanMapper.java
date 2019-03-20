package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysCommunityUserBean;
@Mapper
public interface SysCommunityUserBeanMapper {

    int deleteByPrimaryKey(@Param("userId")Integer userId,@Param("communityId")Integer communityId);

    //根据社区id删除所有该社区被关注的app用户列
    int deleteByCommunityId(Integer communityId);

    int insert(SysCommunityUserBean record);

    int insertSelective(SysCommunityUserBean record);

    SysCommunityUserBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCommunityUserBean record);

    int updateByPrimaryKey(SysCommunityUserBean record);
    
    SysCommunityUserBean findBycommunityIdAndUserId(@Param("userId")Integer userId,@Param("communityId")Integer communityId);
    
    List<SysCommunityUserBean> findById(@Param("id")Integer id);
}