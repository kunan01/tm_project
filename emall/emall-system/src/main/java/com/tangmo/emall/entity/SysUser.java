package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {

    //后台用户id
    private Integer sysUserId;

    //账号
    private String account;

    //密码
    private String password;

    //名称
    private String name;

    //创建时间
    private String createdTime;

    //用户token
    private String token;
}
