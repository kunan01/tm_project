package com.tangmo.yiliao.service;


import com.tangmo.yiliao.entity.Permission;
import com.tangmo.yiliao.entity.Role;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2018-9-4
 * @description
 */

public interface PermissionService {

    //获取所有权限信息
    Result getPremAll();

    //获取所有角色信息
    Result getRoleAll();

    //删除权限信息
    Result delPremById(String permId,String userId);

    //删除角色信息
    Result delRoleById(String roleId,String userId);

    //修改权限信息
    Result updPremById(String permId,String permName,String userId,String mId);

    //修改角色信息
    Result updRoleById(String roleId,String roleName,String userId,String mId);

    //获取所有权限并根据用户权限分类
    Result getRoleTwoAll(String id);

    //添加权限
    Result addPrem(Permission permission);

    //添加角色
    Result addRole(Role role);
}
