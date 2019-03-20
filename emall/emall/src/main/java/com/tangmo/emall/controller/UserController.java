package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.MessageUs;
import com.tangmo.emall.entity.dto.UserDto;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SCaptcha;
import com.tangmo.emall.utils.jedis.JedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api("用户模块")
@RestController
@RequestMapping("/web/user")
public class UserController extends BizBaseController {

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    /**
     * @api {POST} /user/addUserBySystem 系统用户注册
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 系统用户注册
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userEmail:"邮箱",(必填)
     *                          password:"密码", (必填)
     *                          lastName:"姓",   (必填)
     *                          firstName:"名",  (必填)
     *                          code:"验证码"     (必填)
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               "userId":"用户id"
     *                               "token":"token"
     *                           }
     *                       }
     */
    @ApiOperation(value="系统用户注册",notes="")
    @PostMapping("/addUserBySystem")
    public Result addUserBySystem(@RequestBody UserDto userDto) {
        return userService.addUserBySystem(userDto);
    }

    /**
     * @api {POST} /user/facebookLogin faceBook登录（默认注册）
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription faceBook或谷歌登录（默认注册）
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          lastName:"姓",   (必填)
     *                          firstName:"名",  (必填)
     *                          facebookUserId:"用户id" （必填）
     *                          accessToken:"token" （必填）
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               "userId":"用户id"
     *                               "token":"token"
     *                           }
     *                       }
     */
    @ApiOperation(value="faceBook登录（默认注册）",notes="faceBook登录（默认注册）")
    @PostMapping("/facebookLogin")
    public Result facebookLogin(@RequestBody UserDto userDto) {
        return userService.faceBookLogin(userDto);
    }

    /**
     * @api {POST} /user/login 邮箱密码登陆
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 邮箱密码登陆
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userEmail:"邮箱", (必填)
     *                          password:"密码"   (必填)
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               "userId":"用户id"
     *                               "token":"token"
     *                           }
     *                       }
     */
    @ApiOperation(value="邮箱密码登陆",notes="")
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        return userService.userSystemLogin(userDto);
    }


    /**
     * @api {POST} /user/getUserById 获取用户详细信息
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 获取用户详细信息
     * @apiParam {Integer} userId  用户Id （必填）
     * @apiParamExample {json} 请求样例：
     *         /user/getUserById?userId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @ApiOperation(value="获取用户详细信息",notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/getUserById")
    public Result getUserById(Integer userId) {
        return userService.getUserById(userId);
    }

    /**
     * @api {POST} /user/updUserPassword 修改用户密码
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 修改用户密码
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",     (必填)
     *                          password:"原密码",   (必填)
     *                          newPassword:"新密码" (必填)
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":
     *                       }
     */
    @ApiOperation(value="修改用户密码",notes="")
    @UserLoginToken
    @PostMapping("/updUserPassword")
    public Result updUserPassword(@RequestBody UserDto userDto) {
        return userService.updUserPassword(userDto);
    }

    /**
     * @api {POST} /user/updUserNikeName 修改用户昵称
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 修改用户昵称
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",      (必填)
     *                          nickName:"用户昵称"    (必填)
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":
     *                       }
     */
    @ApiOperation(value="修改用户昵称",notes="")
    @UserLoginToken
    @PostMapping("/updUserNikeName")
    public Result updUserNikeName(@RequestBody UserDto userDto) {
        return userService.updUserNikeName(userDto);
    }

    /**
     * @api {POST} /user/updUserPhone 修改用户电话
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 修改用户电话
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",(必填)
     *                          phone:"用户电话" (必填)
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":
     *                       }
     */
    @ApiOperation(value="修改用户电话",notes="")
    @UserLoginToken
    @PostMapping("/updUserPhone")
    public Result updUserPhone(@RequestBody UserDto userDto) {
        return userService.updUserPhone(userDto);
    }

    /**
     * @api {POST} /user/updUserEmail 修改用户邮箱
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 修改用户邮箱
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",      (必填)
     *                          userEmail:"用户旧邮箱",(必填)
     *                          newEmail:"用户新邮箱", (必填)
     *                          password:"用户密码",   (必填)
     *                          code:"图形验证码"      (必填)
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":
     *                       }
     */
    @ApiOperation(value="修改用户邮箱",notes="")
    @UserLoginToken
    @PostMapping("/updUserEmail")
    public Result updUserEmail(@RequestBody UserDto userDto) {
        if(userDto == null || userDto.getCode() == null){
            return ResultUtil.paramError();
        }
        String key = "verification"+userDto.getUserId();
        if (!jedisKeys.exists(key)) {
            return ResultUtil.codeError();
        }else{
            String code = jedisStrings.get(key);

            //判断验证码是否相同
            if(!code.equals(userDto.getCode())){
                return ResultUtil.codeError();
            }
            return userService.updUserEmail(userDto);
        }
    }

    /**
     * @api {POST} /user/verification 生成图文验证码
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 生成图文验证码
     * @apiParam {Integer} userId  用户Id （必填）
     * @apiParamExample {json} 请求样例：
     *             /user/verification?userId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":
     *                       }
     */
    @ApiOperation(value="生成图文验证码",notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/verification")
    public Result verification(Integer userId) throws IOException {

        //实例生成验证码对象
        SCaptcha instance = new SCaptcha();

        //redis的key
        String key = "verification"+userId;

        //存入的验证码
        String value = instance.getCode();

        jedisStrings.set(key, value);

        //设置过期时间30分钟
        jedisStrings.expire(key,1800);

        return ResultUtil.success(instance.getBase64());
    }

    /**
     * @api {POST} /user/getEmailCode/ 发送邮箱验证
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 发送邮箱验证
     * @apiParam {String} email 邮箱地址 （必填）
     * @apiParamExample {json} 请求样例：
     *             /user/getEmailCode?email=1473747181@qq.com
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"发送成功"
     *                       }
     */
    @ApiOperation(value="发送邮箱验证",notes="发送邮箱验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name="email",value="邮箱",dataType="String",required=true,paramType="query")
    })
    @PostMapping("/getEmailCode")
    public Result getEmailCode(String email) {
        return userService.getEmailCode(email);
    }

    /**
     * @api {POST} /user/updUserPhoto 修改用户头像
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 修改用户头像
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",      (必填)
     *                          userPhoto:"用户头像",  (必填)
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"修改成功"
     *                       }
     */
    @ApiOperation(value="修改用户头像",notes="修改用户头像")
    @UserLoginToken
    @PostMapping("/updUserPhoto")
    public Result updUserPhoto(@RequestBody UserDto userDto) {
        return userService.updUserPhoto(userDto);
    }

    /**
     * @api {POST} /user/retrievePassword 找回密码
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 修改用户头像
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          password:"密码",      (必填)
     *                          code :"验证码",       (必填)
     *                          userEmail:"邮箱",      (必填)
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"找回密码成功"
     *                       }
     */
    @ApiOperation(value="找回密码",notes="找回密码")
    @PostMapping("/retrievePassword")
    public Result retrievePassword(@RequestBody UserDto userDto) {
        return userService.retrievePassword(userDto);
    }

    /**
     * @api {POST} /user/customerFeedback 添加客户反馈
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 添加客户反馈
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",
     *                          mTopic:"主题",
     *                          mEmail:"邮箱地址",
     *                          mContent:"反馈内容",
     *                          firstName:"名",
     *                          lastName:"姓",
     *                          orderNumber:"订单号"
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"反馈成功"
     *                       }
     */
    @ApiOperation(value="添加客户反馈",notes="添加客户反馈")
    @UserLoginToken
    @PostMapping("/customerFeedback")
    public Result customerFeedback(@RequestBody MessageUs messageUs) {
        return userService.customerFeedback(messageUs);
    }

    /**
     * @api {GET} /user/getCustomerService 获取客服服务信息
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 添加客户反馈
     * @apiParamExample {json} 请求样例：
     *          /user/getCustomerService
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               customerCareHours:"分别对应前端展示的三个参数",
     *                               callMe:"分别对应前端展示的三个参数",
     *                               warrantySpare:"分别对应前端展示的三个参数"
     *                           }
     *                       }
     */
    @ApiOperation(value="获取客服服务信息",notes="获取客服服务信息")
    @GetMapping("/getCustomerService")
    public Result getCustomerService() {
        return userService.getCustomerService();
    }

    /**
     * @api {GET} /help/getHelpList 查询帮助信息
     * @apiGroup Help
     * @apiVersion 0.0.1
     * @apiDescription 查询帮助信息
     * @apiParamExample {json} 请求样例:
     *      /help/getHelpList
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data":{
     *                              helpId:"帮助信息主键id",
     *                              helpTitle:"类目",
     *                              helpInstructions:"说明",
     *                              level:"级别",
     *                              parentId:"父级id",
     *                              createdTime:"创建时间",
     *                           }
     *                      }
     */
    @ApiOperation(value="查询帮助信息",notes="查询帮助信息")
    @GetMapping(value = "/getHelpList")
    public Result getHelpList() {
        return userService.getHelpList();
    }

}
