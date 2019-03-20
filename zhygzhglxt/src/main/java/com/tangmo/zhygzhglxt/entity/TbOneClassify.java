package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

public class TbOneClassify {

    /**
     * 文章一级分类id
     */
    @ApiModelProperty(value = "文章一级分类id")
    private String oneClassifyId;

    /**
     * 文章一级分类的唯一标识
     */
    @ApiModelProperty(value = "文章一级分类的唯一标识")
    private String oneClassifyCode;

    /**
     * 一级分类的名称
     */
    @ApiModelProperty(value = "一级分类的名称")
    private String name;

    public String getOneClassifyId() {
        return oneClassifyId;
    }

    public void setOneClassifyId(String oneClassifyId) {
        this.oneClassifyId = oneClassifyId == null ? null : oneClassifyId.trim();
    }

    public String getOneClassifyCode() {
        return oneClassifyCode;
    }

    public void setOneClassifyCode(String oneClassifyCode) {
        this.oneClassifyCode = oneClassifyCode == null ? null : oneClassifyCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}