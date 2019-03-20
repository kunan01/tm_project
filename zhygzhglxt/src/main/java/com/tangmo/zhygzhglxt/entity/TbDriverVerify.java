package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbDriverVerify {
    /**
     * 车主审核的主键
     */
    @ApiModelProperty(value = "车主审核的主键")
    private String driverVerifyId;

    /**
     * 车主审核的唯一标识
     */
    @ApiModelProperty(value = "车主审核的唯一标识")
    private String driverVerifyCode;

    /**
     * 车主名称
     */
    @ApiModelProperty(value = "车主名称")
    private String driverName;

    /**
     * 申请车主的当前用户唯一标识code
     */
    @ApiModelProperty(value = "申请车主的当前用户唯一标识code")
    private String driverUserid;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String userCard;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌号码")
    private String carNumber;

    /**
     * 运营公司
     */
    @ApiModelProperty(value = "运营公司")
    private String company;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private String carType;

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
    @ApiModelProperty(value = "车牌照")
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
     * 审核状态（0待审核 1已审核 2审核驳回）
     */
    @ApiModelProperty(value = "审核状态（0待审核 1已审核 2审核驳回）")
    private String state;

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

    /**
     * 车辆类型名称
     */
    @ApiModelProperty(value = "车辆类型名称")
    private String carTypeName;

    /**
     * 当前司机的纬度
     */
    @ApiModelProperty(value = "当前司机的纬度")
    private String carLa;

    /**
     * 当前司机的经度
     */
    @ApiModelProperty(value = "当前司机的经度")
    private String carLo;

    /**
     * 用户基本信息
     */
    @ApiModelProperty(value = "用户基本信息")
    private TbSysUser tbSysUser;

    public String getCarLa() {
        return carLa;
    }

    public void setCarLa(String carLa) {
        this.carLa = carLa;
    }

    public String getCarLo() {
        return carLo;
    }

    public void setCarLo(String carLo) {
        this.carLo = carLo;
    }

    public TbSysUser getTbSysUser() {
        return tbSysUser;
    }

    public void setTbSysUser(TbSysUser tbSysUser) {
        this.tbSysUser = tbSysUser;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getDriverVerifyId() {
        return driverVerifyId;
    }

    public void setDriverVerifyId(String driverVerifyId) {
        this.driverVerifyId = driverVerifyId == null ? null : driverVerifyId.trim();
    }

    public String getDriverVerifyCode() {
        return driverVerifyCode;
    }

    public void setDriverVerifyCode(String driverVerifyCode) {
        this.driverVerifyCode = driverVerifyCode == null ? null : driverVerifyCode.trim();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }


    public String getDriverUserid() {
        return driverUserid;
    }

    public void setDriverUserid(String driverUserid) {
        this.driverUserid = driverUserid == null ? null : driverUserid.trim();
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard == null ? null : userCard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getIdcardFace() {
        return idcardFace;
    }

    public void setIdcardFace(String idcardFace) {
        this.idcardFace = idcardFace == null ? null : idcardFace.trim();
    }

    public String getIdcardBack() {
        return idcardBack;
    }

    public void setIdcardBack(String idcardBack) {
        this.idcardBack = idcardBack == null ? null : idcardBack.trim();
    }

    public String getDriverIcence() {
        return driverIcence;
    }

    public void setDriverIcence(String driverIcence) {
        this.driverIcence = driverIcence == null ? null : driverIcence.trim();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate == null ? null : licensePlate.trim();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport == null ? null : transport.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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