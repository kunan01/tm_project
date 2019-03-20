package com.tangmo.zhjy.app.modules.bean;

import lombok.Data;

/**
 * @author boge
 * @date 17/12/22
 * @description 文件资源实体类
 */

@Data
public class RsFile {
    private String rfId;
    private String path;
    private Integer userId;
}
