package com.tangmo.yiliao.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 用户
 */
@Data
public class User {

    private String userId;
    private String roleId;
    private String userName;
    private String name;
    private String password;
    private String userPhone;
    private Byte userGender;
    private Integer userAge;
    private String userImgUrl;
    private String userCard;
    private Integer userIntegral;
    private Integer userTotal;
    private Integer userCons;
    private String city;
    private String county;
    private String token;
    private Integer state;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;
    private String inviter;

    private Byte wxOpenType;
    private String wxOpenId;


    private List<User> userList = new ArrayList<>();

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    private String roleName;

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


}
