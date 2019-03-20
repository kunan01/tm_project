package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.SysUser;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("后台用户模块")
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BizBaseController {

    /**
     * @api {POST} /user/login 账号密码登录
     * @apiGroup User
     * @apiVersion 0.0.1
     * @apiDescription 账号密码登录
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
    @ApiOperation(value="账号密码登录",notes="账号密码登录")
    @PostMapping("/login")
    public Result login(@RequestBody SysUser sysUser) {
        return sysUserService.userSystemLogin(sysUser);
    }

}
