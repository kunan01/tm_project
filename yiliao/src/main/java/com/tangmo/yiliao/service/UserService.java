package com.tangmo.yiliao.service;

import com.tangmo.yiliao.entity.*;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description
 */
public interface UserService {

    //获取验证码请求地址
    RsFile getRsF();

    //验证码限制
    boolean getcode(String mobile);

    //添加用户
    Result addUser(User user);

    //增加积分余额明细记录
    int insertIntegralSubsidiary(String userId,String key,Integer count);

    //用户密码登录
    Result pwdLogin(User user);

    //找回密码
    Result backPwd(User user);

    //修改密码
    Result updPwd(User user);

    //获取用户基本信息
    Result getBasicInformationById(String userId);

    //获取用户个人信息
    Result getPersonalInformation(String userId);

    //修改用户个人信息
    Result updUser(User user);

    //获取用户积分余额
    Result getUserIntegral(String userId);

    //获取用户积分明细
    Result getUserIntegralDetail(String userId);

    //获取用户文章足迹(分页)
    Result getUserArticleFootprint(String userId,Integer start,Integer end);

    //获取用户视频足迹(分页)
    Result getUserVideoFootprint(String userId,Integer start,Integer end);

    //清空用户足迹
    Result clearUserFootprint(String userId);

    //我的视频(分页)
    Result getUserDoctorVideoById(String userId,Integer start,Integer end);

    //后台用户登录
    Result adminLogin(User user);

    //获取角色下模块信息
    Result getModuleByRoleId(String roleId);

    //获取所有模块信息
    Result getModuleAll();

    //删除模板信息
    Result delModuleById(String userId,String moId,String type);

    //修改模块信息
    Result updModuleById(String userId,String mId,String mName,String mPath);

    //添加模块信息
    Result addModule(Module module);

    //获取二级模块
    Result getModuleTwoAll(String id);

    //获取后台所有用户
    Result getUserAll(SelectUser selectUser);

    //获取后台用户数量
    Result getUserAllCount(SelectUser selectUser);

    //获取所有角色
    Result getRoleAll();

    //后台修改用户信息
    Result updBackGroundUserById(User user);

    //后台app用户信息
    Result updAppUserById(User user);

    //后台删除用户信息
    Result delUserById(String uId,String userId);

    //后台查看医生详情
    Result getUserDoctorById(String userId);

    //后台查看用户详情
    Result getUserById(String userId);

    //后台通过手机号获取用户信息
    Result getUserByPhone(String userPhone);

    //后台添加用户
    Result addUserH(User user);

    //后台添加医生
    Result addDoctorH(DoctorApplied doctorApplied);

    //
    Result getUserSJGXById(String userId);
}
