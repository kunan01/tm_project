package com.tangmo.zhjy.app.modules.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.dto.AppUserDto;

import javax.servlet.http.HttpSession;

public interface AppUserService {

    /**
     * 1.注册
     */
    @SuppressWarnings("rawtypes")
    Result addUser(AppUserDto appUserDto);


    /**
     * 短信验证登录
     */
    @SuppressWarnings("rawtypes")
    Result smsLogin(String phone, String code, HttpSession session);

    /**
     * 用户名密码登录
     */
    @SuppressWarnings("rawtypes")
    Result smsLoginByPass(String phone, String password);

    /**
     * 获取用户详细信息
     */
    @SuppressWarnings("rawtypes")
    Result getUserById(Integer id);

    /**
     * 2.添加频道
     */
    @SuppressWarnings("rawtypes")
    Result saveChannel(Integer userId, Integer twoClassifyid);

    Result getMyChannel(Integer userId);

    /**
     * 3.删除频道
     */
    @SuppressWarnings("rawtypes")
    Result removeChannel(Integer userId, Integer twoClassifyid);

    /**
     * 4.修改用户信息
     */
    @SuppressWarnings("rawtypes")
    Result modify(AppUserDto appUserDto);

    /**
     * 5.根据Phone查询用户信息
     */
    @SuppressWarnings("rawtypes")
    Result findByPhone(String phone);

    /**
     * @param @param  phone
     * @param @param  password
     * @param @return 设定文件
     * @return Result    返回类型
     * @throws
     * @Title: updatePassword
     * @Description: TODO(6.忘记密码接口)
     */
    @SuppressWarnings("rawtypes")
    Result updatePassword(String phone, String password);


    @SuppressWarnings("rawtypes")
    Result updPassword(Integer id,String password,String newPassword);

    /**
     * @param @param  phone
     * @param @param  password
     * @param @return 设定文件
     * @return Result    返回类型
     * @throws
     * @Title: findByUserAndPassword
     * @Description: TODO(校验用户名密码是否正确)
     */
    @SuppressWarnings("rawtypes")
    Result findByUserAndPassword(String phone, String password, PasswordEncoder passwordEncoder);

}