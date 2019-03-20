package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.DiscountUser;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("活动模块")
@RestController
@RequestMapping("/web/activity")
public class ActivityController extends BizBaseController {

    /**
     * @api {GET} /activity/getAdvertisingList 获取热门活动广告
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 获取热门活动广告
     * @apiParamExample {json} 请求样例：
     *      /activity/getAdvertisingList
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               raId:"热门活动广告id",
     *                               advertisingImage:"广告图片",
     *                               createdTime:"创建时间"
     *                           }
     *                       }
     */
    @ApiOperation(value="获取热门活动广告",notes="获取热门活动广告")
    @GetMapping("/getAdvertisingList")
    public Result getAdvertisingList() {
        return activityService.getAdvertisingList();
    }

    /**
     * @api {GET} /activity/getTrendList 获取趋势列表
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 获取趋势列表
     * @apiParamExample {json} 请求样例：
     *      /activity/getTrendList
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               taId:"趋势广告主键",
     *                               title:"趋势标题"
     *                               advertisingImage:"广告图片",
     *                               descript:"描述语",
     *                               location:"描述语位置 1左 2下 3右",
     *                               trends:"包含4个商品信息"
     *                               createdTime:"创建时间"
     *                           }
     *                       }
     */
    @ApiOperation(value="获取趋势列表",notes="获取趋势列表")
    @GetMapping("/getTrendList")
    public Result getTrendList() {
        return activityService.getTrendList();
    }

    /**
     * @api {GET} /activity/getTimeLimitedAdvertisingList 获取限时活动广告
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 获取限时活动广告
     * @apiParamExample {json} 请求样例：
     *      /activity/getTimeLimitedAdvertisingList
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               discountId:"优惠活动主键",
     *                               title:"活动标题",
     *                               description:"宣传语",
     *                               adImage:"宣传图片",
     *                               startDate:"开始日期",
     *                               endDate:"结束日期",
     *                               eventType:"活动类型  0满减 1打折 2免邮 3买送 4降价",
     *                               eventRule:"活动规则",
     *                               createdTime:"创建时间",
     *                               objectType:"活动目标  1商品分类 2商品",
     *                               objectId:"商品分类id"
     *                           }
     *                       }
     */
    @ApiOperation(value="获取限时活动广告",notes="获取限时活动广告")
    @GetMapping("/getTimeLimitedAdvertisingList")
    public Result getTimeLimitedAdvertisingList() {
        return activityService.getTimeLimitedAdvertisingList();
    }

    /**
     * @api {POST} /activity/getExchangeCode 用户获取兑换码
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 用户获取兑换码
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",
     *                          disId:"优惠活动id"
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"兑换码"}
     *                       }
     */
    @ApiOperation(value="用户获取兑换码",notes="用户获取兑换码")
    @UserLoginToken
    @PostMapping("/getExchangeCode")
    public Result getExchangeCode(@RequestBody DiscountUser discountUser) {
        return activityService.getExchangeCode(discountUser);
    }

    /**
     * @api {GET} /activity/getTimeLimitedAdvertisingState 获取当前是否存在限时活动
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 获取当前是否存在限时活动
     * @apiParamExample {json} 请求样例：
     *      /activity/getTimeLimitedAdvertisingState
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{true:存在，false:不存在}
     *                       }
     */
    @ApiOperation(value="获取当前是否存在限时活动",notes="获取当前是否存在限时活动")
    @GetMapping("/getTimeLimitedAdvertisingState")
    public Result getTimeLimitedAdvertisingState() {
        return activityService.getTimeLimitedAdvertisingState();
    }

}
