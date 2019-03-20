package com.tangmo.zhygzhglxt.entity.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by chengge on 2018/10/24.
 */
public class TbDriverVerifyDto implements Serializable {

    private static final long serialVersionUID = 1345L;

    /**
     * 司机的唯一标识
     */
    @ApiModelProperty(value = "司机的唯一标识")
    private String driverUserid;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    /**
     * 身份证号.
     */
    @ApiModelProperty(value = "身份证号")
    private String userCard;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private String carType;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 手机验证码
     */
    @ApiModelProperty(value = "手机验证码")
    private String authCode;

    /**
     * 身份证正面
     */
    @ApiModelProperty(value = "身份证正面")
    private String idcardFace;

    /**
     * 身份证反面
     */
    @ApiModelProperty(value = "身份证反面")
    private String idcardBack;

    /**
     * 驾驶证
     */
    @ApiModelProperty(value = "驾驶证")
    private String driverIcence;

    /**
     * 车牌照
     */
    @ApiModelProperty(value = "驾驶证")
    private String licensePlate;

    /**
     * 资格证
     */
    @ApiModelProperty(value = "资格证")
    private String certificate;

    /**
     * 运输证
     */
    @ApiModelProperty(value = "运输证")
    private String transport;

    /**
     * 用户身份
     */
    @ApiModelProperty(value = "用户身份")
    private String userStatus;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String imgUrl;

    /**
     * 判断是司机端还是乘客端
     */
    @ApiModelProperty(value = "判断是司机端还是乘客端 1 乘客端 2司机端")
    private String type;


    public String getDriverUserid() {
        return driverUserid;
    }

    public void setDriverUserid(String driverUserid) {
        this.driverUserid = driverUserid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getIdcardFace() {
        return idcardFace;
    }

    public void setIdcardFace(String idcardFace) {
        this.idcardFace = idcardFace;
    }

    public String getIdcardBack() {
        return idcardBack;
    }

    public void setIdcardBack(String idcardBack) {
        this.idcardBack = idcardBack;
    }

    public String getDriverIcence() {
        return driverIcence;
    }

    public void setDriverIcence(String driverIcence) {
        this.driverIcence = driverIcence;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
