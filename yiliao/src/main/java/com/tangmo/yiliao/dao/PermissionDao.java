package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.Permission;
import com.tangmo.yiliao.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hanjialin
 * @date 2018-9-4
 * @description
 */
@Mapper
public interface PermissionDao {

    //获取所有权限
    List<Permission> getPremAll();

    //通过权限id获取权限信息
    Permission getPremById(String permId);

    //获取所有角色
    List<Role> getRoleAll();

    //通过权限id查询角色使用权限数量
    Integer getRoleCountByPermId(String permId);

    //通过角色id查询角色使用数量
    Integer getUserCountByRoleId(String roleId);

    //删除权限
    int delPermById(@Param("permId") String permId,@Param("newPermId") String newPermId,@Param("userId") String userId);

    //删除角色
    int delRoleById(@Param("roleId") String roleId,@Param("newRoleId") String newRoleId,@Param("userId") String userId);

    //修改权限
    int updPremById(@Param("permId") String permId,@Param("permName") String permName,@Param("userId") String userId,@Param("mId") String mId);

    //修改角色
    int updRoleById(@Param("roleId") String roleId,@Param("roleName") String roleName,@Param("userId") String userId,@Param("mId") String mId);

    //添加权限
    int addPrem(Permission permission);

    //添加角色
    int addRole(Role role);

}
