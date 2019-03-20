package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysOperation;
import com.tangmo.zhjy.system.modules.bean.SysPermission;

@Mapper
public interface SysOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysOperation record);

    int insertSelective(SysOperation record);

    List<SysOperation> selectByPrimaryKey(@Param("permissions")List<SysPermission> sysPermissions);

    int updateByPrimaryKeySelective(SysOperation record);

    int updateByPrimaryKey(SysOperation record);
    
    List<SysOperation> batchSelect(@Param("operationIds")List<Integer> operationIds);
    
    List<SysOperation> findByPath(@Param("username")String username);
    
    List<SysOperation> findByPermissionId(@Param("permissionId")Integer permissionId);
    
    List<SysOperation> findById(@Param("ids")List<Integer> ids);
}