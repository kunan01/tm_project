package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysPermission;
import com.tangmo.zhjy.system.modules.bean.SysRoleBean;

@Mapper
public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

   SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
    
    List<SysPermission> findByPermissionAndOperation(@Param("roleBeans")List<SysRoleBean> roleBeans);
    
    List<SysPermission> queryMenuPath(@Param("username")String username);
    
    List<SysPermission> queryAll();
    
    List<SysPermission> queryAll(@Param("permissionOnes")List<SysPermission> permissionOnes);
}