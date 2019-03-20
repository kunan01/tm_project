package com.tangmo.zhjy.app.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppCommunityBean;
import com.tangmo.zhjy.app.modules.bean.AppCommunityInformBean;
@Mapper
public interface AppCommunityBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppCommunityBean record);

    int insertSelective(AppCommunityBean record);

    AppCommunityBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppCommunityBean record);

    int updateByPrimaryKey(AppCommunityBean record);
    
    List<AppCommunityBean> queryDistance(@Param("longitude")String longitude,@Param("latitude")String latitude,@Param("userId")Integer userId,@Param("city")String city);

    List<String> queryCity(@Param("userId")Integer userId);

    List<AppCommunityBean> findBySysPage(@Param("name")String title);
    
    List<AppCommunityBean> queryAll(@Param("userId")Integer userId);



    List<AppCommunityBean> queryAllNoAttention(@Param("userId")Integer userId);


   /**
    * 查询关注社区未读通知
    */
    List<AppCommunityBean> findByUnreadCommunity(@Param("list")List<AppCommunityInformBean> list);










    //查询关注的社区
    List<AppCommunityBean> findAttention(@Param("userId")Integer userId);



    List<Integer> findBySysUserId(@Param("userId")Integer userId);
    
}