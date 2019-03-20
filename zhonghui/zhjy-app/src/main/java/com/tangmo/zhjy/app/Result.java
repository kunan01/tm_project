package com.tangmo.zhjy.app;

import com.tangmo.zhjy.app.enums.ResultCode;

/**
 * @Description : TODO(返回信息结果类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月8日 下午1:52:16
 */
public class Result<T> {
	
	//状态码
	private int code;
	//信息
	private String msg;
	//返回结果数据
	private T data;
	
	public Result() {
	}
	public Result(int code,String msg,T data){
		this.code=code;
		this.msg=msg;
		this.data =data;
	}
	
	public Result(ResultCode resultCode,T data){
		this(resultCode);
		this.data=data;
	}
	
	public Result(ResultCode resultCode){
		this.code=resultCode.getCode();
		this.msg=resultCode.getMsg();
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
