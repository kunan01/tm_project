package com.tangmo.zhygzhglxt.utility;

import com.tangmo.zhygzhglxt.enums.ResultCode;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description : TODO(返回信息结果类)
 * ---------------------------------
 * @Author : chengge
 * @Date : 2018年09月26日 下午1:52:16
 */
public class Result<T> {

    //状态码
    @ApiModelProperty(value = "状态码")
    private int code;

    //信息
    @ApiModelProperty(value = "信息")
    private String msg;

    //返回结果数据
    @ApiModelProperty(value = "返回结果数据")
    private T data;


    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
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
