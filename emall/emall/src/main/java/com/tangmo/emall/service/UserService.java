package com.tangmo.emall.service;

import com.tangmo.emall.entity.MessageUs;
import com.tangmo.emall.entity.dto.UserDto;
import com.tangmo.emall.utils.Result;

public interface UserService {

    //系统用户注册
    Result addUserBySystem(UserDto userDto);

    //邮箱密码登陆
    Result userSystemLogin(UserDto userDto);

    //获取用户详细信息
    Result getUserById(Integer userId);

    //修改用户密码
    Result updUserPassword(UserDto userDto);

    //修改用户昵称
    Result updUserNikeName(UserDto userDto);

    //修改用户电话
    Result updUserPhone(UserDto userDto);

    //修改用户邮箱
    Result updUserEmail(UserDto userDto);

    //修改用户头像
    Result updUserPhoto(UserDto userDto);

    //找回密码
    Result retrievePassword(UserDto userDto);

    //发送邮箱链接（找回密码）
    Result getEmailCode(String email);

    //添加客户反馈
    Result customerFeedback(MessageUs us);

    //获取客服服务信息
    Result getCustomerService();

    //faceBook登录
    Result faceBookLogin(UserDto userDto);

    Result getHelpList();


}
