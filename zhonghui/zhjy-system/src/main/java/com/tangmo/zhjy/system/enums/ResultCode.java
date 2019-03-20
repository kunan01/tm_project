/**     
* @Title: ResultCode.java   
* @Package com.tangmo.zhjy.enums   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月8日 下午2:02:37   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.enums;

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
	WEAK_NET_WORK(-1,"网络异常,请稍后重试"),
	PASSWORD_ERROR(10001,"用户名或密码错误"),
	PAPAMETE_ERROR(10101,"参数错误"),
	SYSTEM_ADMIN_USER_ERROR(10002,"账号已存在"),
	FAIL(1,"请求失败"),
	SYSTEM_ADMIN_ROLE_ERROR(10003,"角色已存在");
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
