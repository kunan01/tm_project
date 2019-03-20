package com.tangmo.yiliao.entity;

import lombok.Data;

/**
 * @author boge
 * @date 18/07/05
 * @description 文件资源实体类
 */

@Data
public class RsFile {
    private String address;
    private String account;
    private String userId;
    private String password;
    private String content;
    private String action;
    private Byte checkContent;

}
