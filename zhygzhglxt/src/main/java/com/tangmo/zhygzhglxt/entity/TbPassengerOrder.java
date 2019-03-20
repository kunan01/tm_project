package com.tangmo.zhygzhglxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class TbPassengerOrder {

    /**
     * 乘客订单主键
     */
    @ApiModelProperty(value = "乘客订单主键")
    private String orderId;

    /**
     * 乘客订单唯一标识
     */
    @ApiModelProperty(value = "乘客订单唯一标识")
    private String orderCode;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    /**
     * 用户唯一标识
     */
    @ApiModelProperty(value = "用户唯一标识")
    private String userCode;

    /**
     * 开始地
     */
    @ApiModelProperty(value = "开始地")
    private String startAddress;

    /**
     * 结束地
     */
    @ApiModelProperty(value = "结束地")
    private String endAddress;

    /**
     * 行程多少km
     */
    @ApiModelProperty(value = "行程多少km")
    private BigDecimal km;

    /**
     * 预估价位
     */
    @ApiModelProperty(value = "预估价位")
    private BigDecimal price;

    /**
     * 人数
     */
    @ApiModelProperty(value = "人数")
    private Integer peopleNumber;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间")
    private String appointmentTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date creatTime;

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
     * 订单的状态
     */
    @ApiModelProperty(value = "订单的状态")
    private String orderState;

    /**
     * 开始经度
     */
    @ApiModelProperty(value = "开始经度")
    private String startLongitude;

    /**
     * 开始纬度
     */
    @ApiModelProperty(value = "开始纬度")
    private String startLatitude;

    /**
     * 结束经度
     */
    @ApiModelProperty(value = "结束经度")
    private String endLongitude;

    /**
     * 结束纬度
     */
    @ApiModelProperty(value = "结束纬度")
    private String endLatitude;

    /**
     * 是否是预约单（0是 1否）
     */
    @ApiModelProperty(value = "是否是预约单（0是 1否）")
    private String endAbout;

    /**
     * 网约车 货车
     */
    @ApiModelProperty(value = "车辆类型 网约车 货车 同乡客运")
    private String tbParmId;

    /**
     * 货物名称
     */
    @ApiModelProperty(value = "货物名称")
    private String goodsName;

    /**
     * 货物详情
     */
    @ApiModelProperty(value = "货物详情")
    private String goodsDetails;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;

    /**
     * 大概时间
     */
    @ApiModelProperty(value = "大概时间")
    private String dgTime;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String payWay;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String contact;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 是否老年帮扶
     */
    @ApiModelProperty(value = "是否老年帮扶")
    private String audit;

    /**
     * 预约客运班车的车牌号
     */
    @ApiModelProperty(value = "预约客运班车的车牌号")
    private String carNumber;

    /**
     * 班车路线的唯一标识
     */
    @ApiModelProperty(value = "班车路线的唯一标识")
    private String busRoute;

    /**
     * 司机姓名
     */
    @ApiModelProperty(value = "司机姓名")
    private String driverName;

    /**
     * 司机车牌号
     */
    @ApiModelProperty(value = "司机车牌号")
    private String driverNumber;

    /**
     * 司机联系电话
     */
    @ApiModelProperty(value = "司机联系电话")
    private String driverPhone;

    /**
     * 叫车类型
     */
    @ApiModelProperty(value = "叫车类型")
    private String carType;

    /**
     * 好评度
     */
    @ApiModelProperty(value = "好评度")
    private String rating;

    /**
     * 评价的状态
     */
    @ApiModelProperty(value = "评价的状态")
    private String ratingState;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatingState() {
        return ratingState;
    }

    public void setRatingState(String ratingState) {
        this.ratingState = ratingState;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(String busRoute) {
        this.busRoute = busRoute;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getDgTime() {
        return dgTime;
    }

    public void setDgTime(String dgTime) {
        this.dgTime = dgTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

    public String getTbParmId() {
        return tbParmId;
    }

    public void setTbParmId(String tbParmId) {
        this.tbParmId = tbParmId;
    }

    public String getEndAbout() {
        return endAbout;
    }

    public void setEndAbout(String endAbout) {
        this.endAbout = endAbout;
    }

    public String getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(String startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(String startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(String endLongitude) {
        this.endLongitude = endLongitude;
    }

    public String getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(String endLatitude) {
        this.endLatitude = endLatitude;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress == null ? null : startAddress.trim();
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress == null ? null : endAddress.trim();
    }

    public BigDecimal getKm() {
        return km;
    }

    public void setKm(BigDecimal km) {
        this.km = km;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
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

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }


}