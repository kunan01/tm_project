package com.tangmo.emall.dao;

import com.tangmo.emall.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao {

    //通过账号获取用户信息
    SysUser getUserByAccount(String account);

    //通过id获取后台用户信息
    SysUser getSysUserById(Integer sysUserId);
}
