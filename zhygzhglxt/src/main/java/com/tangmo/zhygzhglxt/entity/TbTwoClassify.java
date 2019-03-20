package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbTwoClassify {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 二级分类的唯一标识code
     */
    @ApiModelProperty(value = "二级分类的唯一标识code")
    private String twoClassifyCode;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 缩略图
     */
    @ApiModelProperty(value = "缩略图")
    private String icon;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String url;

    /**
     * 一级分类的唯一标识code
     */
    @ApiModelProperty(value = "一级分类的唯一标识code")
    private String oneClassifyCode;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 是否删除（0正常 1删除）
     */
    @ApiModelProperty(value = "是否删除（0正常 1删除）")
    private String flag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTwoClassifyCode() {
        return twoClassifyCode;
    }

    public void setTwoClassifyCode(String twoClassifyCode) {
        this.twoClassifyCode = twoClassifyCode == null ? null : twoClassifyCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getOneClassifyCode() {
        return oneClassifyCode;
    }

    public void setOneClassifyCode(String oneClassifyCode) {
        this.oneClassifyCode = oneClassifyCode == null ? null : oneClassifyCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}