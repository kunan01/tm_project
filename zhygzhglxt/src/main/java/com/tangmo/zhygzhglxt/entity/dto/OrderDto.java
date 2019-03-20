package com.tangmo.zhygzhglxt.entity.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by chengge on 2018/11/13.
 */
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 123345L;

    /**
     * 年
     */
    @ApiModelProperty(value = "年")
    private String year;

    /**
     * 月
     */
    @ApiModelProperty(value = "月")
    private String month;

    /**
     * 日
     */
    @ApiModelProperty(value = "日")
    private String day;

    /**
     * 每页几行
     */
    @ApiModelProperty(value = "每页几行")
    private Integer pageSize;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer pageNo;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String orderState;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型")
    private String endAbout;

    /**
     * 模糊参数
     */
    @ApiModelProperty(value = "模糊参数")
    private String name;

    /**
     * 司机订单的唯一标识code
     */
    @ApiModelProperty(value = "司机订单的唯一标识code")
    private String driverOrderCode;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getEndAbout() {
        return endAbout;
    }

    public void setEndAbout(String endAbout) {
        this.endAbout = endAbout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDriverOrderCode() {
        return driverOrderCode;
    }

    public void setDriverOrderCode(String driverOrderCode) {
        this.driverOrderCode = driverOrderCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
