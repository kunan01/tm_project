package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TbSysUser {


    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private String id;

    /**
     * 用户的唯一标识code
     */
    @ApiModelProperty(value = "用户的唯一标识code")
    private String code;

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
     * 身份证号.
     */
    @ApiModelProperty(value = "身份证号")
    private String userCard;

    /**
     * 头像路径
     */
    @ApiModelProperty(value = "头像路径")
    private String imgUrl;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createUserid;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updateUserid;

    /**
     * 用户的状态
     */
    @ApiModelProperty(value = "用户的状态(0正常 1冻结)")
    private String state;

    /**
     * 用户的身份
     */
    @ApiModelProperty(value = "用户的身份")
    private String userStatus;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
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
     * 假删 0未删 1已删
     */
    @ApiModelProperty(value = " 假删 0未删 1已删")
    private String flag;

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

    /**
     * 余额
     */
    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

    /**
     * 支付密码
     */
    @ApiModelProperty(value = "支付密码")
    private String payPassword;

    /**
     * token
     */
    @ApiModelProperty(value = "用户登录判定token")
    private String token;

    /**
     * roleName角色名称
     */
    @ApiModelProperty(value = "roleName角色名称")
    private String roleName;

    private String parmId;

    private String parmTypeId;

    /**
     * 司机信息
     */
    @ApiModelProperty(value = "司机信息")
    private TbDriverVerify tbDriverVerify;

    /**
     * 用户拥有的所有模块信息
     */
    @ApiModelProperty(value = "用户拥有的所有模块信息")
    private List<TbSysModule> tbSysModules;

    public List<TbSysModule> getTbSysModules() {
        return tbSysModules;
    }

    public void setTbSysModules(List<TbSysModule> tbSysModules) {
        this.tbSysModules = tbSysModules;
    }

    public TbDriverVerify getTbDriverVerify() {
        return tbDriverVerify;
    }

    public void setTbDriverVerify(TbDriverVerify tbDriverVerify) {
        this.tbDriverVerify = tbDriverVerify;
    }

    @Override
    public String toString() {
        return "TbSysUser{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", realName='" + realName + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", userCard='" + userCard + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", createUserid='" + createUserid + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserid='" + updateUserid + '\'' +
                ", state='" + state + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", email='" + email + '\'' +
                ", idcardFace='" + idcardFace + '\'' +
                ", idcardBack='" + idcardBack + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", born=" + born +
                ", flag='" + flag + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", balance=" + balance +
                ", payPassword='" + payPassword + '\'' +
                ", token='" + token + '\'' +
                ", roleName='" + roleName + '\'' +
                ", parmId='" + parmId + '\'' +
                ", parmTypeId='" + parmTypeId + '\'' +
                '}';
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
}