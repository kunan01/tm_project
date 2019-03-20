/**     
* @Title: ResultCode.java   
* @Package com.tangmo.zhjy.enums   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月8日 下午2:02:37   
* @version V1.0     
*/   
package com.tangmo.zhjy.app.enums;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 *
 * @Description : TODO(同意异常处理枚举类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月8日 下午2:02:37
 */
public enum ResultCode {
	
	SUCCESS(0,"请求成功"),
	FAIL(1,"请求失败"),
	WEAK_NET_WORK(-1,"网络异常,请稍后重试"),
	PASSWORD_ERROR(10001,"用户名或密码错误"),
	USER_ERROR(10050,"该用户不存在!"),
	PAPAMETE_ERROR(10101,"参数错误"),
    PHONE_ERROR1(10017,"该手机号不存在！"),
	SYSTEM_ADMIN_USER_ERROR(10002,"账号已存在"),
	SYSTEM_ADMIN_ROLE_ERROR(10003,"角色已存在"),
	PENDING(10004,"支付处理中"),
	INTERNAL_ERROR(10104,"系统错误,请刷新后重试"),
	SMS_ERROR(10005,"验证码错误"),
    SMS_ERROR1(10014,"验证码未发送！"),
	SMSTIME_ERROR(10006,"验证码已超时!"),
	SHOP_STATE0(10007,"商家正在审核"),
	SHOP_STATE1(10008,"商家审核通过"),
	SHOP_STATE2(10009,"商家审核未通过，请重新提交审核信息"),
    SHOP_STATE3(10099,"暂无商家审核信息"),
	USER_ADDRESS(10010,"没有默认地址"),
	VERSION_NUMBER1(10011,"版本已是最新！"),
	VERSION_NUMBER2(10012,"该版本未升级！"),
	PHONE_ERROR(10013,"手机号错误！"),
    CHANNEL_ERROR(10086,"频道已关联，请勿重复操作！"),;
	//状态
	private int code;
	//信息
	private String msg;
	
	ResultCode(int code,String msg) {
		this.code=code;
		this.msg=msg;
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
