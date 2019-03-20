package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysCommunitySysuserBean;
@Mapper
public interface SysCommunitySysuserBeanMapper {

    int deleteByPrimaryKey(Integer id);

    //根据社区id删除所有有关联的社区管理员分配表
    int deleteByCommunityId(Integer communityId);

    //根据管理员id删除所有有关联的社区管理员分配表
    int deleteBySysUserId(Integer sysUserId);

    int insert(@Param("sysUserId")Integer userId,@Param("list")List<Integer> communityIds);

    int insertSelective(SysCommunitySysuserBean record);

    SysCommunitySysuserBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCommunitySysuserBean record);

    int updateByPrimaryKey(SysCommunitySysuserBean record);
}