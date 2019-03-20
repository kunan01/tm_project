package com.tangmo.yiliao.entity;

import lombok.Data;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 科室
 */
@Data
public class Department {

    private String dtId;
    private String dtName;
    private String dtImgId;
    private Integer sortingId;
    private Byte dtState;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;

}
