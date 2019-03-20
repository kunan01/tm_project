package com.tangmo.zhjy.app.modules.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class PayCallBackBean implements Serializable {

    private Integer id;
    private Integer dicid;
    private BigDecimal money;
    private Integer userid;
    private String payway;
    private String phone;
    private String ordercode;
    private Integer payUserId;
    private String remark;
    private Integer state;
    private static final long serialVersionUID = 1L;
    

    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPayUserId() {
		return payUserId;
	}

	public void setPayUserId(Integer payUserId) {
		this.payUserId = payUserId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDicid() {
        return dicid;
    }

    public void setDicid(Integer dicid) {
        this.dicid = dicid;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway == null ? null : payway.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }
}