package com.tangmo.emall.service;

import com.tangmo.emall.entity.SysUser;
import com.tangmo.emall.utils.Result;

public interface SysUserService {

    //后台用户登录
    Result userSystemLogin(SysUser sysUser);

    //通过后台用户id获取用户信息
    Result getSysUserById(Integer sysUserId);
}
