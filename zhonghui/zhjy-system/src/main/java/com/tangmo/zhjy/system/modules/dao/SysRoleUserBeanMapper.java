package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysRoleUserBean;

@Mapper
public interface SysRoleUserBeanMapper {
	
    int deleteByPrimaryKey(@Param("userId")Integer userId,@Param("roleId")Integer roleId);

    int deleteBySysUserId(Integer id);

    int insert(@Param("id")int id,@Param("roles")List<Integer> roles);
    
    int relevanceAdmin(@Param("roleId")int roleId,@Param("users")List<Integer> users);

    int insertSelective(SysRoleUserBean record);

    SysRoleUserBean selectByPrimaryKey(Integer id);

    SysRoleUserBean selectBySysUserId(Integer userId);

    int updateByPrimaryKeySelective(SysRoleUserBean record);

    int updateByPrimaryKey(SysRoleUserBean record);
}