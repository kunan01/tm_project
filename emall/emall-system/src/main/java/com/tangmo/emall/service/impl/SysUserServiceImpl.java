package com.tangmo.emall.service.impl;

import com.tangmo.emall.dao.SysUserDao;
import com.tangmo.emall.entity.SysUser;
import com.tangmo.emall.service.SysUserService;
import com.tangmo.emall.utils.JWTUtil;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public Result userSystemLogin(SysUser sysUser) {
        try {

            if(sysUser == null || sysUser.getAccount() == null || sysUser.getPassword() == null){
                return ResultUtil.paramError();
            }

            SysUser sysUser1 = sysUserDao.getUserByAccount(sysUser.getAccount());
            if(sysUser1 == null){
                return ResultUtil.inviter();
            }

            if(!sysUser1.getPassword().equals(sysUser.getPassword())){
                return ResultUtil.pwdError();
            }

            SysUser user2 = new SysUser();
            user2.setSysUserId(sysUser1.getSysUserId());
            user2.setToken(JWTUtil.createTokenWithClaim(sysUser1));

            return ResultUtil.success(user2);

        }catch (Exception e){
            System.out.println("后台用户登录接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getSysUserById(Integer sysUserId) {
        try {

            if(sysUserId == null){
                return ResultUtil.paramError();
            }

            SysUser sysUser1 = sysUserDao.getSysUserById(sysUserId);
            if(sysUser1 == null){
                return ResultUtil.inviter();
            }

            return ResultUtil.success(sysUser1);
        }catch (Exception e){
            System.out.println("通过后台用户id获取用户信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
