package com.tangmo.zhjy.app.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppCommunityInformBean;
@Mapper
public interface AppCommunityInformBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppCommunityInformBean record);

    int insertSelective(AppCommunityInformBean record);

    AppCommunityInformBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppCommunityInformBean record);

    int updateByPrimaryKeyWithBLOBs(AppCommunityInformBean record);

    int updateByPrimaryKey(AppCommunityInformBean record);
    
    List<AppCommunityInformBean> findByPage(@Param("title")String title,@Param("userId")Integer userId);

    List<AppCommunityInformBean> findCommunityInfomByUserId(@Param("type")Integer type,@Param("userId")Integer userId);

    /**
     * 根据社区编号查询用户关注未读信息
     * @param userId
     * @return
     */
    List<AppCommunityInformBean> findByUnread(@Param("userId")Integer userId);
    
    List<AppCommunityInformBean> findByCommunityId(@Param("communityId") Integer communityId);
}