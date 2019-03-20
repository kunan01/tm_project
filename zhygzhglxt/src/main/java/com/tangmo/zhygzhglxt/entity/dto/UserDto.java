package com.tangmo.zhygzhglxt.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chengge on 2018/7/11.
 */
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 新手机号码
     */
    @ApiModelProperty(value = "新手机号码")
    private String userNewPhone;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码")
    private String userNewPassWord;

    @ApiModelProperty(value = "支付密码")
    private String payPassword;

    /**
     * 新的支付密码
     */
    @ApiModelProperty(value = "新的支付密码")
    private String userNewPayPassWord;


    /**
     * 手机验证码
     */
    @ApiModelProperty(value = "手机验证码")
    private String authCode;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String userCard;

    /**
     * 头像路径
     */
    @ApiModelProperty(value = "头像路径")
    private String imgUrl;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户电话")
    @Length(max = 11)
    private String phone;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createUserid;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updateUserid;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private String state;

    /**
     * 用户身份
     */
    @ApiModelProperty(value = "用户身份")
    private String userStatus;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

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
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 出身日期
     */
    @ApiModelProperty(value = "出身日期")
    private Date born;

    /**
     * 籍贯
     */
    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String area;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private String roleCode;


    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

    /**
     * 用户的唯一标识
     */
    @ApiModelProperty(value = "用户的唯一标识")
    private String code;

    private String parmId;

    private String parmTypeId;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String id;

    /**
     * 用户token
     */
    @ApiModelProperty(value = "用户token")
    private String token;

    /**
     * 客户端的类型
     */
    @ApiModelProperty(value = "客户端的类型:0乘客端 1司机端 2管理后台")
    private String type;    //0乘客端 1司机端 2管理后台

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParmId() {
        return parmId;
    }

    public void setParmId(String parmId) {
        this.parmId = parmId;
    }

    public String getParmTypeId() {
        return parmTypeId;
    }

    public void setParmTypeId(String parmTypeId) {
        this.parmTypeId = parmTypeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getUserNewPhone() {
        return userNewPhone;
    }

    public void setUserNewPhone(String userNewPhone) {
        this.userNewPhone = userNewPhone;
    }

    public String getUserNewPassWord() {
        return userNewPassWord;
    }

    public void setUserNewPassWord(String userNewPassWord) {
        this.userNewPassWord = userNewPassWord;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getUserNewPayPassWord() {
        return userNewPayPassWord;
    }

    public void setUserNewPayPassWord(String userNewPayPassWord) {
        this.userNewPayPassWord = userNewPayPassWord;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    public String getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
