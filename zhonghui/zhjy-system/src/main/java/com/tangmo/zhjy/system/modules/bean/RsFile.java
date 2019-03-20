package com.tangmo.zhjy.system.modules.bean;


/**
 * @author boge
 * @date 17/12/22
 * @description 文件资源实体类
 */

public class RsFile {
    private String rfId;
    private String path;
    private Integer userId;

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
