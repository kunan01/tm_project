package com.tangmo.zhjy.system.modules.bean;

import java.util.Date;

public class Log {

    /**
     * 日志主键id
     */
    private Integer logId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 请求方式
     */
    private String methodType;

    /**
     * 请求路径
     */
    private String methodUrl;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 请求时间
     */
    private Date getTime;

    /**
     * 备注
     */
    private String state;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户角色
     */
    private String RoleName;

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType == null ? null : methodType.trim();
    }

    public String getMethodUrl() {
        return methodUrl;
    }

    public void setMethodUrl(String methodUrl) {
        this.methodUrl = methodUrl == null ? null : methodUrl.trim();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}