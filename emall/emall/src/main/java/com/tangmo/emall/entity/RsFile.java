package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RsFile implements Serializable {

    //图片id
    private String fId;

    //图片文件
    private String fBase;

    //图片状态 1已使用  2未使用
    private Byte fState;

    //创建时间
    private String createdTime;

}
