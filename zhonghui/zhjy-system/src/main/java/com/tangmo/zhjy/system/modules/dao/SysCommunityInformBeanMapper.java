package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysCommunityInformBean;
@Mapper
public interface SysCommunityInformBeanMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByCommunityId(Integer communityId);

    int insert(SysCommunityInformBean record);

    int insertSelective(SysCommunityInformBean record);

    SysCommunityInformBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCommunityInformBean record);

    int updateByPrimaryKeyWithBLOBs(SysCommunityInformBean record);

    int updateByPrimaryKey(SysCommunityInformBean record);
    
    List<SysCommunityInformBean> findByPage(@Param("title")String title);

    List<SysCommunityInformBean> findByPageSysUserId(@Param("title")String title,@Param("sysUserId")Integer sysUserId);
    
    List<SysCommunityInformBean> findByUnread(@Param("userId")Integer userId);
    
    List<SysCommunityInformBean> findByCommunityId(@Param("communityId")Integer communityId);
}