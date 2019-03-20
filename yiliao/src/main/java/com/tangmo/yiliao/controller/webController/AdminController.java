package com.tangmo.yiliao.controller.webController;


import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.DoctorApplied;
import com.tangmo.yiliao.entity.Module;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.entity.User;
import com.tangmo.yiliao.utility.code.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 后台用户 登录以及模块操作
 */
@Api("system用户相关")
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    //后台用户登录
    @PostMapping("/pwdLogin")
    public Result userLogin(@RequestBody User user){
        return userService.adminLogin(user);
    }

    //获取角色下模块信息
    @GetMapping("/getModuleByRoleId/{roleId}")
    public Result getModuleByRoleId(@PathVariable String roleId){
        return userService.getModuleByRoleId(roleId);
    }

    //获取所有模块信息
    @GetMapping("/getModuleAll")
    public Result getModuleAll(){
        return userService.getModuleAll();
    }

    //删除模块信息
    @GetMapping("/delModuleById/{userId}/{moId}/{type}")
    public Result delModuleById(@PathVariable String userId,@PathVariable String moId,@PathVariable String type){
        return userService.delModuleById(userId,moId,type);
    }

    //修改模块信息
    @GetMapping("/updModuleById/{userId}/{mId}/{mName}/{mPath}")
    public Result updModuleById(@PathVariable String userId,@PathVariable String mId,@PathVariable String mName,@PathVariable String mPath){
        return userService.updModuleById(userId,mId,mName,mPath);
    }

    //添加模块信息
    @PostMapping("/addModule")
    public Result addModule(@RequestBody Module module){
        return userService.addModule(module);
    }

    //获取所有二级模块
    @GetMapping("/getModuleTwoAll/{id}")
    public Result getModuleTwoAll(@PathVariable String id){
        return userService.getModuleTwoAll(id);
    }

    //后台获取所有用户
    @PostMapping("/getUserAll")
    public Result getUserAll(@RequestBody SelectUser selectUser){
        return userService.getUserAll(selectUser);
    }

    //后台获取用户数量
    @PostMapping("/getUserAllCount")
    public Result getUserAllCount(@RequestBody SelectUser selectUser){
        return userService.getUserAllCount(selectUser);
    }

    //后台所有角色信息
    @GetMapping("/getRoleAll")
    public Result getRoleAll(){
        return userService.getRoleAll();
    }

    //修改后台用户信息
    @PostMapping("/updBackGroundUserById")
    public Result updBackGroundUserById(@RequestBody User user){
        return userService.updBackGroundUserById(user);
    }

    //修改app用户信息
    @PostMapping("/updAppUserById")
    public Result updAppUserById(@RequestBody User user){
        return userService.updAppUserById(user);
    }

    //后台删除用户信息
    @GetMapping("/delUserById/{uId}/{userId}")
    public Result delUserById(@PathVariable String uId,@PathVariable String userId){
        return userService.delUserById(uId,userId);
    }

    //后台查看医生详情
    @GetMapping("/getUserDoctorById/{userId}")
    public Result getUserDoctorById(@PathVariable String userId){
        return userService.getUserDoctorById(userId);
    }

    //后台精确用户
    @GetMapping("/getUserById/{userId}")
    public Result getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    //后台通过手机号精确用户
    @GetMapping("/getUserByPhone/{userPhone}")
    public Result getUserByPhone(@PathVariable String userPhone){
        return userService.getUserByPhone(userPhone);
    }

    //后台添加用户
    @PostMapping("/addUserH")
    public Result addUserH(@RequestBody User user){
        return userService.addUserH(user);
    }

    //后台添加医生
    @PostMapping("/addDoctorH")
    public Result addDoctorH(@RequestBody DoctorApplied doctorApplied){
        return userService.addDoctorH(doctorApplied);
    }

    //获取当前用户下级关系
    @GetMapping("/getUserSJGXById/{userId}")
    public Result getUserSJGXById(@PathVariable String userId){
        return userService.getUserSJGXById(userId);
    }
}
