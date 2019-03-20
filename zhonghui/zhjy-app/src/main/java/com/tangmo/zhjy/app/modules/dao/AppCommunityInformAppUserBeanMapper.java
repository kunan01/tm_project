package com.tangmo.zhjy.app.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppCommunityInformAppUserBean;
@Mapper
public interface AppCommunityInformAppUserBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("list")List<AppCommunityInformAppUserBean> list);

    int insertSelective(AppCommunityInformAppUserBean record);

    AppCommunityInformAppUserBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppCommunityInformAppUserBean record);

    int updateByPrimaryKey(AppCommunityInformAppUserBean record);
    
    AppCommunityInformAppUserBean queryTotalAndRedTotal(@Param("community_informId")Integer community_informId);

    Integer getRead(@Param("userId")Integer userId,@Param("commId")Integer commId);

    int updateByCommIdAndUserId(@Param("userId")Integer userId,@Param("commId")Integer commId);

    Integer getUnreadCount(@Param("userId")Integer userId);
}