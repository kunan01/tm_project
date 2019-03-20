package com.tangmo.yiliao.entity;

import lombok.Data;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 验证码规则
 */
@Data
public class VerificationCode {
    private String phone;
    private Byte state;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
