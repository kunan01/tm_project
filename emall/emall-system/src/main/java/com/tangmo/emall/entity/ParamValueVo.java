package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParamValueVo implements Serializable {

    private static final long serialVersionUID = -7273852055849787763L;

    //参数值主键
    private Integer valueId;

    //参数值
    private String paramValue;

    //参数类型值
    private String typeValue;

    //创建时间
    private String createdTime;

    //参数图
    private String paramImage;

    //状态 0正在审核 1通过 2未通过
    private Byte state;

    //是否删除 0未删除 1已删除
    private Byte dataFlag;

}
