package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbInformation {

    /**
     * 文章主键.
     */
    @ApiModelProperty(value = "文章主键")
    private String informationId;

    /**
     * 文章的唯一标识
     */
    @ApiModelProperty(value = "文章的唯一标识")
    private String informationCode;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String title;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private Date publishData;

    /**
     * 用户唯一标识
     */
    @ApiModelProperty(value = "用户唯一标识")
    private String userCode;

    /**
     * 二级栏目的唯一标识
     */
    @ApiModelProperty(value = "二级栏目的唯一标识")
    private String twoClassifyCode;

    /**
     * 文章状态
     */
    @ApiModelProperty(value = "文章状态")
    private String informationState;

    /**
     * 封面图片（1-2）
     */
    @ApiModelProperty(value = "封面图片（1-2）")
    private String coverImg;

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
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private String flag;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 二级栏目
     */
    @ApiModelProperty(value = "二级栏目")
    private String twoType;


    /**
     * 一级栏目的唯一标识
     */
    @ApiModelProperty(value = "一级栏目的唯一标识")
    private String oneClassifyCode;

    /**
     * 一级栏目的名称
     */
    @ApiModelProperty(value = "一级栏目的名称")
    private String oneType;

    /**
     * 发布人姓名
     */
    @ApiModelProperty(value = "发布人姓名")
    private String userName;


    public String getOneClassifyCode() {
        return oneClassifyCode;
    }

    public void setOneClassifyCode(String oneClassifyCode) {
        this.oneClassifyCode = oneClassifyCode;
    }

    public String getOneType() {
        return oneType;
    }

    public void setOneType(String oneType) {
        this.oneType = oneType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTwoType() {
        return twoType;
    }

    public void setTwoType(String twoType) {
        this.twoType = twoType;
    }

    public String getInformationId() {
        return informationId;
    }


    public void setInformationId(String informationId) {
        this.informationId = informationId == null ? null : informationId.trim();
    }

    public String getInformationCode() {
        return informationCode;
    }

    public void setInformationCode(String informationCode) {
        this.informationCode = informationCode == null ? null : informationCode.trim();
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }


    public Date getPublishData() {
        return publishData;
    }

    public void setPublishData(Date publishData) {
        this.publishData = publishData;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getTwoClassifyCode() {
        return twoClassifyCode;
    }

    public void setTwoClassifyCode(String twoClassifyCode) {
        this.twoClassifyCode = twoClassifyCode == null ? null : twoClassifyCode.trim();
    }

    public String getInformationState() {
        return informationState;
    }

    public void setInformationState(String informationState) {
        this.informationState = informationState == null ? null : informationState.trim();
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}