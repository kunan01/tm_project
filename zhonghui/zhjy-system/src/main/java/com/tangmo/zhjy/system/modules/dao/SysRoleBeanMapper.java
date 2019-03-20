package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysRoleBean;

@Mapper
public interface SysRoleBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleBean record);

    int insertSelective(SysRoleBean record);

    SysRoleBean selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(SysRoleBean record);

    int updateByPrimaryKey(SysRoleBean record);
    
    SysRoleBean selectByName(@Param("name")String roleName);
    
    List<SysRoleBean> findByPage(@Param("name")String roleName);
    
    List<SysRoleBean> findByRelevanceRole(@Param("username")String username);
    
    int batchRole(@Param("list")List<Integer> list);
}