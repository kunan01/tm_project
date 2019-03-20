package com.tangmo.yiliao.controller.appController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.RsFile;
import com.tangmo.yiliao.entity.User;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.http.SendMsg;
import com.tangmo.yiliao.utility.jedis.JedisUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 个人中心
 */
@Api("app用户相关")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    /**
     * @api {GET} /user/mobile/auth/{mobile} 获取手机验证码
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 获取手机验证码
     * @apiParamExample {json} 请求样例：
     *  /user/mobile/auth/18710829356
     *
     * @apiSuccess (success) {POST} code  0:请求成功; 10005：请求参数错误; 10006:请求次数超限; 10007:当前账号已禁止请求验证码;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"验证码"}
     *                       }
     */
    @GetMapping("/mobile/auth/{mobile}")
    public Result getAuthCode(@PathVariable String mobile){
        if(mobile == null || mobile.equals("")){
            return ResultUtil.paramError();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        if (jedisKeys.exists(mobile)) {
            String jsonString = jedisStrings.get(mobile+"date");
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000*60*60*24);
                long hour = (l / (1000*60*60)-day*24);
                long min = ((l/(1000*60))-day*24*60-hour*60);
                long s = (l/1000-day*24*60*60-hour*60*60-min*60);
                if(((day*24*60*60)+(hour*60*60)+(min*60)+s) <= 60){
                    return ResultUtil.beyondError();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(!userService.getcode(mobile)){
            return ResultUtil.limitError();
        }

        String authCode = SendMsg.sendMsg(mobile,userService.getRsF());
        if(authCode == null){
            return ResultUtil.serverError();
        }else{
            jedisStrings.set(mobile, authCode);
            jedisStrings.set(mobile+"date", df.format(new Date()).toString());
            return ResultUtil.success(authCode);
        }
    }

    /**
     * @api {POST} /user/register 增加用户
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 增加用户
     * @apiParam {User} user 用户对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userCard: "登录名",
     *                      userPhone:"手机号",
     *                      userCons: "验证码",
     *                      password: "加密后的密码"
     *                      state:"0app注册  1邀请注册"
     *                      updateUserId:"邀请人id"
     *                   }
     * @apiSuccess (success) {POST} code  0:请求成功; 10001:该手机号已注册; 10004:验证码错误; 10005:请求参数错误; 10010:服务器发生未知错误
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                           "code":"0",
     *                           "msg": "请求成功",
     *                           "data":""
     *                       }
     */
    @PostMapping("/register")
    public Result addUserInfo(@RequestBody User user){
        System.out.println(user);
        if(user.getUserPhone() == null || user.getUserCons() == null || user.getPassword() == null){
            return ResultUtil.paramError();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = user.getUserPhone();
        if (!jedisKeys.exists(key)) {
            return ResultUtil.codeError();
        }else{
            //若存在，则直接从redis里面取出相应数
            String code = jedisStrings.get(key);
            String jsonString = jedisStrings.get(key+"date");
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000*60*60*24);
                long hour = (l / (1000*60*60)-day*24);
                long min = ((l/(1000*60))-day*24*60-hour*60);
                long s = (l/1000-day*24*60*60-hour*60*60-min*60);
                if(((day*24*60*60)+(hour*60*60)+(min*60)+s) > 180){
                    return ResultUtil.codeError();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(!code.equals(user.getUserCons().toString())){
                return ResultUtil.codeError();
            }
            jedisKeys.del(key);
            jedisKeys.del(key+"date");
        }
        return userService.addUser(user);
    }

    /**
     * @api {POST} /user/pwdLogin 用户密码登录
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 用户登录
     * @apiParam {User} user 用户对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      password:"加密后的新密码",
     *                      userPhone:"18710889234",
     *                      userCard:"hanakjsdnkjj12"
     *                   }
     * @apiSuccess (success) {POST} code  0:请求成功; 10002:账号密码错误; 10003：该手机号未注册 10005:请求参数错误;10008:当前账号已封停;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"0",
     *                     "msg": "请求成功",
     *                     "data":{
     *                         userId:"用户id",
     *                         token:"登录验证",
     *                     }}
     */
    @PostMapping("/pwdLogin")
    public Result userLogin(@RequestBody User user){
        return userService.pwdLogin(user);
    }

    /**
     * @api {POST} /user/backPwd 找回密码
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 用户登录
     * @apiParam {User} user 用户对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      password:"加密后的新密码",
     *                      userPhone:"手机号",
     *                      userCons: "验证码"
     *                   }
     * @apiSuccess (success) {POST} code  0:请求成功; 10003: 该手机号未注册; 10004:验证码错误; 10005:请求参数错误; 10008:当前账号已封停;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"0"
     *                     "msg": "请求成功",
     *                     "data":""}
     */
    @PostMapping("/backPwd")
    public Result backPwd(@RequestBody User user){
        if(user.getUserPhone() == null || user.getUserCons() == null || user.getPassword() == null){
            return ResultUtil.paramError();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = user.getUserPhone();
        if (!jedisKeys.exists(key)) {
            return ResultUtil.codeError();
        }else{
            //若存在，则直接从redis里面取出相应数
            String code = jedisStrings.get(key);
            String jsonString = jedisStrings.get(key+"date");
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000*60*60*24);
                long hour = (l / (1000*60*60)-day*24);
                long min = ((l/(1000*60))-day*24*60-hour*60);
                long s = (l/1000-day*24*60*60-hour*60*60-min*60);
                if(((day*24*60*60)+(hour*60*60)+(min*60)+s) > 180){
                    return ResultUtil.codeError();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(!code.equals(user.getUserCons().toString())){
                return ResultUtil.codeError();
            }
            jedisKeys.del(key);
            jedisKeys.del(key+"date");
        }
        return userService.backPwd(user);
    }

    /**
     * @api {POST} /user/updPwd 修改密码
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 用户登录
     * @apiParam {User} user 用户对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userId:"用户id",
     *                      password:"加密后的旧密码",
     *                      userCard:"加密后的新密码"
     *                   }
     * @apiSuccess (success) {POST} code  0:请求成功; -1:"原密码不正确", 10005:请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {
     *                     "code":"0"
     *                     "msg": "请求成功",
     *                     "data":""}
     */
    @PostMapping("/updPwd")
    public Result updPwd(@RequestBody User user){
        if(user.getUserId() == null || user.getUserCard() == null || user.getPassword() == null){
            return ResultUtil.paramError();
        }
        return userService.updPwd(user);
    }


    /**
     * @api {GET} /user/getBasicInformationById/{userId}  获取用户基本信息
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 获取用户基本信息
     * @apiParam {String} userId  用户id
     * @apiParamExample {json} 请求样例：
     *  /user/getBasicInformationById/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                userId: 用户id,
     *                                name:"性名",
     *                                roleId:"角色", DOCTOR:"医生"  MEMBER:"用户"
     *                                userImgUrl:"头像id"
     *                           }
     *                       }
     */
    @GetMapping("/getBasicInformationById/{userId}")
    public Result getBasicInformationById(@PathVariable String userId){
        if(userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        return userService.getBasicInformationById(userId);
    }

    /**
     * @api {GET} /user/getPersonalInformation/{userId}  获取用户个人信息
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 获取用户个人信息
     * @apiParam {String} userId  用户id
     * @apiParamExample {json} 请求样例：
     *  /user/getPersonalInformation/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                userId: 用户id,
     *                                name:"性名",
     *                                userName:"昵称",
     *                                userImgUrl:"头像id",
     *                                userGender:"性别",  1男2女
     *                                userAge:"年龄",
     *                                city:"城市",
     *                                county:"区县"
     *                           }
     *                       }
     */
    @GetMapping("/getPersonalInformation/{userId}")
    public Result getPersonalInformation(@PathVariable String userId){
        if(userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        return userService.getPersonalInformation(userId);
    }


    /**
     * @api {POST} /user/updUser 修改用户个人信息 (改什么传什么)
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 修改用户个人信息 (改什么传什么)
     * @apiParam {User} user 用户对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userId:"用户id",
     *                      name:"姓名",
     *                      userName:"昵称",
     *                      userImgUrl:"头像id",
     *                      userGender:"性别",  1男 2女
     *                      userAge:"年龄",
     *                      city:"城市",
     *                      county:"区县"
     *                   }
     * @apiSuccess (success) {POST} code  0:请求成功; 10005:请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {
     *                     "code":"0"
     *                     "msg": "请求成功",
     *                     "data":""}
     */
    @PostMapping("/updUser")
    public Result updUser(@RequestBody User user){
        if(user.getUserId() == null){
            return ResultUtil.paramError();
        }
        return userService.updUser(user);
    }

    /**
     * @api {GET} /user/getUserIntegral/{userId}  获取用户积分余额
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 获取用户积分余额
     * @apiParam {String} userId  用户id
     * @apiParamExample {json} 请求样例：
     *  /user/getUserIntegral/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @GetMapping("/getUserIntegral/{userId}")
    public Result getUserIntegral(@PathVariable String userId){
        if(userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        return userService.getUserIntegral(userId);
    }

    /**
     * @api {GET} /user/getUserIntegralDetail/{userId}  获取用户积分明细
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 获取用户积分明细
     * @apiParam {String} userId  用户id
     * @apiParamExample {json} 请求样例：
     *  /user/getUserIntegralDetail/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               levelName:"明细名称",
     *                               superiorId:"明细类型", 注：ADD_RECORDS="增加"   RECORDS_OF_CONSUMPTION="消费"
     *                               syBean:"数量",
     *                               createTime:"时间"
     *                           }
     *                       }
     */
    @GetMapping("/getUserIntegralDetail/{userId}")
    public Result getUserIntegralDetail(@PathVariable String userId){
        if(userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        return userService.getUserIntegralDetail(userId);
    }

    /**
     * @api {GET} /user/getUserArticleFootprint/{userId}/{start}/{end}  获取用户文章足迹(分页)
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 获取用户足迹
     * @apiParam {String} userId  用户id
     * @apiParam {Integer} start  页数
     * @apiParam {Integer} start  条数
     * @apiParamExample {json} 请求样例：
     *  /user/getUserArticleFootprint/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               saId: 文章id，
     *                               saTitle: 文章标题,
     *                               visitNumber:"游览次数",
     *                               createTime:"游览时间"
     *                           }
     *                       }
     */
    @GetMapping("/getUserArticleFootprint/{userId}/{start}/{end}")
    public Result getUserArticleFootprint(@PathVariable String userId,@PathVariable Integer start,@PathVariable Integer end){
        if(userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        return userService.getUserArticleFootprint(userId,start,end);
    }

    /**
     * @api {GET} /user/getUserVideoFootprint/{userId}/{start}/{end}  获取用户视频足迹(分页)
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 获取用户足迹
     * @apiParam {String} userId  用户id
     * @apiParam {Integer} start  页数
     * @apiParam {Integer} start  条数
     * @apiParamExample {json} 请求样例：
     *  /user/getUserVideoFootprint/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               dvId: 视频id，
     *                               dvImgId: 视频展示图片,
     *                               dvTitle:"视频标题",
     *                               visitNumber:"游览次数",
     *                               createTime:"游览时间"
     *                           }
     *                       }
     */
    @GetMapping("/getUserVideoFootprint/{userId}/{start}/{end}")
    public Result getUserVideoFootprint(@PathVariable String userId,@PathVariable Integer start,@PathVariable Integer end){
        if(userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        return userService.getUserVideoFootprint(userId,start,end);
    }

    /**
     * @api {GET} /user/clearUserFootprint/{userId}  清空用户足迹
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 获取用户足迹
     * @apiParam {String} userId  用户id
     * @apiParamExample {json} 请求样例：
     *     /user/clearUserFootprint/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @GetMapping("/clearUserFootprint/{userId}")
    public Result clearUserFootprint(@PathVariable String userId){
        if(userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        return userService.clearUserFootprint(userId);
    }

    /**
     * @api {GET} /user/getUserDoctorVideoById/{userId}/{start}/{end}我的视频(分页)
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 获取某个科室下所有医师(分页)
     * @apiParam {String} userId  科室id
     * @apiParam {int} start  页数
     * @apiParam {int} end    条数
     * @apiParamExample {json} 请求样例：
     *  /user/getUserDoctorVideoById/{dtId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:参数有误
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                  dvId: 视频主键id,
     *                                  dvImgId:"视频展示图片",
     *                                  dvTitle:"视频标题",
     *                                  dvIntegral:"所获积分"
     *                           }
     *                       }
     */
    @GetMapping("/getUserDoctorVideoById/{userId}/{start}/{end}")
    public Result getUserDoctorVideoById(@PathVariable String userId,@PathVariable Integer start,@PathVariable Integer end){
        if(userId == null || start == 0){
            return ResultUtil.paramError();
        }
        return userService.getUserDoctorVideoById(userId,start,end);
    }


}
