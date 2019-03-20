package com.tangmo.emall.utils;

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

    public static Result success(Object data) {return createResult("0","请求成功", data);}

    public static Result success() {return createResult("0","请求成功", null);}

    public static Result paramError(){return createResult("-1","参数错误",null);}

    public static Result error(Object data) {return createResult("-2","请求失败", data);}

    public static Result registered(){return createResult("-3","该账户已注册",null);}

    public static Result inviter(){return createResult("-4","该账户未注册",null);}

    public static Result pwdError(){return createResult("-5","账号密码错误",null);}

    public static Result userError(){
        return createResult("-6","当前账号已封停",null);
    }

    public static Result serviceError(){return createResult("-7","系统错误！",null);}

    public static Result updPwdError(){return createResult("-8","新旧密码不能一致",null);}

    public static Result updPwdTwoError(){return createResult("-9","密码输入有误",null);}

    public static Result codeError(){return createResult("-10","验证码错误，请重新输入",null);}

    public static Result emailError(){return createResult("-11","新旧邮箱不能一致",null);}

    public static Result addressError(){return createResult("-12","地址数量超出限制，每位用户最多10条地址",null);}

    public static Result addressListError(){return createResult("-13","用户暂未添加地址",null);}

    public static Result addressUserError(){return createResult("-14","用户身份校验失败，请重试！",null);}

    public static Result emailCodeError(){return createResult("-15","发送失败！可能是邮箱地址未找到！请更新后重试！",null);}

    public static Result linkError(){return createResult("-16","验证码已过期！",null);}

    public static Result FeedbackError(){return createResult("-17","今天的反馈太频繁了，请明天在试！",null);}

    public static Result imgError(){return createResult("-18","图片时效已过期！请重新上传",null);}

    public static Result giveError(){return createResult("-19","您已经点过赞了",null);}

    public static Result dataError(){return createResult("-20","当前数据已经存在",null);}

    public static Result dataNoError(){return createResult("-21","当前数据不存在",null);}

    public static Result dataNoByError(){return createResult("-22","当前数据中存在错误信息",null);}

    public static Result jsonError(){return createResult("-23","json数据格式有误",null);}

    public static Result publishYesError(){return createResult("-24","该商品已上架",null);}

    public static Result publishNoError(){return createResult("-25","该商品未上架",null);}

    public static Result tokenError(){return createResult("-30","token验证失败",null);}
}
