package com.tangmo.zhjy.app.modules.vo;

import lombok.Data;

import java.util.List;

/**
 * @author boge
 * @date 18/3/10
 * @description
 */

public class ExpressVO {
    private String company;
    private String number;
    private Integer deliverystatus;
    private String msg;
    private List<ExpressDetailVO> expressDetailVOs;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getDeliverystatus() {
        return deliverystatus;
    }

    public void setDeliverystatus(Integer deliverystatus) {
        this.deliverystatus = deliverystatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ExpressDetailVO> getExpressDetailVOs() {
        return expressDetailVOs;
    }

    public void setExpressDetailVOs(List<ExpressDetailVO> expressDetailVOs) {
        this.expressDetailVOs = expressDetailVOs;
    }
}
