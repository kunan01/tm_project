package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    //用户id
    private Integer userId;

    //邮箱
    private String userEmail;

    //登录密码
    private String password;

    //用户类型 0:普通用户, 1:店主    预留字段
    private Byte userType;

    //性别 0:男 1:女 2;未填写
    private Byte userSex;

    //名
    private String firstName;

    //姓
    private String lastName;

    //用户昵称
    private String nickName;

    //真实姓名
    private String realName;

    //生日
    private String birthday;

    //头像
    private String userPhoto;

    //手机
    private String userPhone;

    //注册来源 0:系统注册 1:facebook 2:google
    private Byte userFrom;

    //用户状态 0:停用 1:启用
    private Byte userStatus;

    //创建时间
    private String createdTime;

    //用户token
    private String token;

}
