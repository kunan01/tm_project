/**
 * @Title: ResultCode.java
 * @Package com.tangmo.zhjy.enums
 * @Description: TODO(用一句话描述该文件做什么)
 * @author xiaoming
 * @date 2018年1月8日 下午2:02:37
 * @version V1.0
 */
package com.tangmo.zhygzhglxt.enums;

/**
 * @Description : TODO(同意异常处理枚举类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月8日 下午2:02:37
 */
public enum ResultCode {

    SUCCESS(0, "请求成功"),
    FAIL(1, "请求失败"),
    WEAK_NET_WORK(-1, "网络异常,请稍后重试"),
    SMSTIME_ERROR(1001, "验证码已超时！"),
    PHONE_PASSWORD_ERROR(1002, "用户名或密码不能为空！"),
    PHONE_ERROR(1003, "该手机号已被注册"),
    USER_ERROR(1004, "该用户不存在！"),
    PASSWORD_ERROR(1005, "密码错误！"),
    PASSWORD_ERROR2(1006, "支付密码错误！"),
    PHONE_ERROR1(1007, "该手机号不存在！"),
    SMS_ERROR1(1008, "验证码未发送！"),
    SMS_ERROR2(1009, "验证码错误！"),
    PAPAMETE_ERROR(1011, "参数错误！"),
    PENDING(1012, "支付处理中"),
    SYSTEM_ADMIN_USER_ERROR(1013, "账号已存在！"),
    SYSTEM_ADMIN_ROLE_ERROR(1014, "角色已存在！"),
    INTERNAL_ERROR(1015, "系统错误,请刷新后重试！"),
    SMS_ERROR(10016, "手机号或验证码错误！"),
    DRIVER_ERROR(10017, "车主审核信息已存在，请勿重复提交！"),
    ORDER_ERROR(10018, "有订单正在进行中！"),
    BUS_ERROR1(10019, "该路线暂时未开通车辆！"),
    BUS_ERROR2(10020, "该路线暂时未开通司机接单！"),
    BUS_ERROR3(10021, "暂无公交车！"),
    BUS_ERROR4(10022, "暂无公交车！"),
    VERSION_NUMBER1(10011, "版本已是最新！"),
    VERSION_NUMBER2(10012, "该版本未升级！"),
    LISTEN_ORDER1(10013, "该订单司机不同意完成！"),
    LISTEN_ORDER2(10014, "该订单司机同意完成！"),
    LISTEN_ORDER3(10015, "乘客提交完成订单！"),
    ;
    //状态
    private int code;
    //信息
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
