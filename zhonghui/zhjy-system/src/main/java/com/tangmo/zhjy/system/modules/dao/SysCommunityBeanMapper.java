package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysCommunityBean;
import com.tangmo.zhjy.system.modules.bean.SysCommunityInformBean;
@Mapper
public interface SysCommunityBeanMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysCommunityBean record);

    int insertSelective(SysCommunityBean record);

    SysCommunityBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCommunityBean record);

    int updateByPrimaryKey(SysCommunityBean record);
    
    List<SysCommunityBean> findBySysPage(@Param("name")String title);

    List<SysCommunityBean> findBySysPageBySysUserId(@Param("name")String title,@Param("userId")Integer userId);
    
    List<SysCommunityBean> queryAll(@Param("userId")Integer userId);
//    查询未读的分类
    List<SysCommunityBean> findByUnreadCommunity(@Param("list")List<SysCommunityInformBean> list);
    //查询关注的社区
    List<SysCommunityBean> findAttention(@Param("userId")Integer userId);
    
    List<Integer> findBySysUserId(@Param("userId")Integer userId);
    
}