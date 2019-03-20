package com.tangmo.yiliao.utility.code;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 返回Result工具类
 */
public class ResultUtil {

    public static Result createResult(String code,String msg,Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static Result success(Object data) {
        return createResult("0","请求成功", data);
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(String errorInfo){
        return createResult("-1",errorInfo,null);
    }

    public static Result serverError(){return createResult("10010","服务器发生未知错误",null);}

    public static Result registered(){return createResult("10001","该手机号已注册",null);}

    public static Result inviter(){return createResult("10003","该账户未注册",null);}

    public static Result pwdError(){return createResult("10002","用户名密码错误",null);}

    public static Result codeError(){return createResult("10004","验证码错误,请重新获取",null);}

    public static Result paramError(){
        return createResult("10005","参数有误",null);
    }

    public static Result beyondError(){
        return createResult("10006","一段时间内请求次数超限",null);
    }

    public static Result limitError(){
        return createResult("10007","由于此账号可能存在恶意耍验证码的行为，当前账号已禁止请求验证码! 如有疑问，请联系客服。",null);
    }

    public static Result userError(){
        return createResult("10008","当前账号已封停",null);
    }
}
