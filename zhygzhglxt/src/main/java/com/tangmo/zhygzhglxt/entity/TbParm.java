package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class TbParm implements Serializable {

    private static final long serialVersionUID = 54444566753546L;

    @ApiModelProperty(value = "参数id")
    private String parmId;

    @ApiModelProperty(value = "参数名称")
    private String parmName;

    @ApiModelProperty(value = "参数类型")
    private String parmType;

    public String getParmId() {
        return parmId;
    }

    public void setParmId(String parmId) {
        this.parmId = parmId;
    }

    public String getParmName() {
        return parmName;
    }

    public void setParmName(String parmName) {
        this.parmName = parmName == null ? null : parmName.trim();
    }

    public String getParmType() {
        return parmType;
    }

    public void setParmType(String parmType) {
        this.parmType = parmType == null ? null : parmType.trim();
    }
}