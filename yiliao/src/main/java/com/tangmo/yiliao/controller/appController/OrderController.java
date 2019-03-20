package com.tangmo.yiliao.controller.appController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.PayOrder;
import com.tangmo.yiliao.utility.code.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description 订单
 */
@Api("app订单")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    /**
     * @api {POST} /order/addOrder 生成订单
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription  提交支付订单
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          "goType"："订单类型 0购买积分 1购买商品（必传）"
     *                          "userId"："用户id（必传）"
     *                          "uaId"："用户地址id（订单类型为1时必传）"
     *                          "cdId"："商品id（订单类型为1时必传）"
     *                          "goCount"："数量（必传！订单类型为0代表积分数量，为1代表购买商品数量）"
     *                          "cdSize"："商品规格id（订单类型为1时必传）"
     *                          "cdColor"："商品颜色id（订单类型为1时必传）"
     *                          "expressFee"："物流费用（订单类型为1时必传）"
     *                          "discountFee"："打折费用（订单类型为1时必传）"
     *                          "payFee"："订单总价（必传）"
     *                      }
     * @apiSuccess (success) {POST} code 0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data": "订单id"
     *                       }
     */
    @ApiOperation(value="生成订单")
    @PostMapping("/addOrder")
    public Result payTreasure(@RequestBody PayOrder payOrder) {
        return orderService.addOrder(payOrder);
    }

    /**
     * @api {GET} /order/getIntegral/{type} 积分换算规则数量
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription  积分换算规则数量
     * @apiParam {Byte} type：0用户注册 1邀请用户 2购买积分 3积分提现 4单次提现最低金额 5用户累计多少积分方可提现 6单次提现最大金额 7单日最多提现次数
     * @apiParamExample {json} 请求样例：
     *          /order/getIntegral/2
     * @apiSuccess (success) {GET} code 0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data": "规则数量"
     *                       }
     */
    @ApiOperation(value="积分换算规则数量")
    @GetMapping("/getIntegral/{type}")
    public Result getIntegral(@PathVariable Byte type) {
        return orderService.getIntegral(type);
    }

    /**
     * @api {GET} /order/getUserTypeOpenId/{userId} 获取用户是否绑定小程序openId状态
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription  获取用户是否绑定小程序openId状态
     * @apiParam {String} userId： 用户id
     * @apiParamExample {json} 请求样例：
     *          /order/getUserTypeOpenId/2
     * @apiSuccess (success) {GET} code 0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data": "true:已绑定/false:未绑定"
     *                       }
     */
    @ApiOperation(value="获取用户是否绑定小程序openId状态")
    @GetMapping("/getUserTypeOpenId/{userId}")
    public Result getUserTypeOpenId(@PathVariable String userId) {
        return orderService.getUserTypeOpenId(userId);
    }

    /**
     * @api {POST} /order/addUserOpenId/{userId}/{code} 绑定用户于小程序的openId
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription  绑定用户于小程序的openId
     * @apiParam {String} userId： 用户id
     * @apiParamExample {json} 请求样例：
     *          /order/addUserOpenId/2
     * @apiSuccess (success) {POST} code 0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data": "true:已绑定/false:未绑定"
     *                       }
     */
    @ApiOperation(value="绑定用户于小程序的openId")
    @PostMapping("/addUserOpenId/{userId}/{code}")
    public Result addUserOpenId(@PathVariable String userId,@PathVariable String code) {
        return orderService.addUserOpenId(userId,code);
    }

}
