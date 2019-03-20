package com.tangmo.yiliao.controller.webController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.Permission;
import com.tangmo.yiliao.entity.Role;
import com.tangmo.yiliao.utility.code.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-9-4
 * @description 后台用户权限以及角色
 */
@Api("system用户权限以及角色")
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    //获取所有权限信息
    @GetMapping("/getPremAll")
    public Result getPremAll(){
        return permissionService.getPremAll();
    }

    //获取所有角色信息
    @GetMapping("/getRoleAll")
    public Result getRoleAll(){
        return permissionService.getRoleAll();
    }

   //删除权限信息
    @GetMapping("/delPremById/{permId}/{userId}")
    public Result delPremById(@PathVariable String permId,@PathVariable String userId){
        return permissionService.delPremById(permId,userId);
    }

    //删除角色信息
    @GetMapping("/delRoleById/{roleId}/{userId}")
    public Result delRoleById(@PathVariable String roleId,@PathVariable String userId){
        return permissionService.delRoleById(roleId,userId);
    }

    //修改权限信息
    @GetMapping("/updPremById/{permId}/{permName}/{userId}/{mId}")
    public Result updPremById(@PathVariable String permId,@PathVariable String permName,@PathVariable String userId,@PathVariable String mId){
        return permissionService.updPremById(permId,permName,userId,mId);
    }

    //修改角色信息
    @GetMapping("/updRoleById/{roleId}/{roleName}/{userId}/{mId}")
    public Result updRoleById(@PathVariable String roleId,@PathVariable String roleName,@PathVariable String userId,@PathVariable String mId){
        return permissionService.updRoleById(roleId,roleName,userId,mId);
    }

    //获取所有权限并根据用户权限分类
    @GetMapping("/getRoleTwoAll/{id}")
    public Result getRoleTwoAll(@PathVariable String id){
        return permissionService.getRoleTwoAll(id);
    }

   //添加权限信息
    @PostMapping("/addPrem")
    public Result addPrem(@RequestBody Permission permission){
        return permissionService.addPrem(permission);
    }

   //添加角色信息
    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role){
        System.out.println(role);
        return permissionService.addRole(role);
    }
}
