package com.tangmo.zhjy.app.modules.controller;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.OrderConst;
import com.tangmo.zhjy.app.modules.bean.DeliverRemind;
import com.tangmo.zhjy.app.modules.bean.GoodsOrderSimple;
import com.tangmo.zhjy.app.modules.bean.GoodsReturn;
import com.tangmo.zhjy.app.modules.bean.ShopCart;
import com.tangmo.zhjy.app.modules.service.DeliverRemindService;
import com.tangmo.zhjy.app.modules.service.GoodsOrderService;
import com.tangmo.zhjy.app.modules.service.GoodsReturnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 18/4/4
 * @description 商品订单控制层
 */
@Api("商品订单控制层")
@RestController
@RequestMapping("/order")
public class AppOrderController {

    @Resource
    private GoodsOrderService goodsOrderService;

    @Resource
    private DeliverRemindService deliverRemindService;

    @Resource
    private GoodsReturnService goodsReturnService;

    /**
     * @api {POST} /order 增加订单
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 增加订单
     * @apiParam {GoodsOrderSimple} goodsOrderSimple 订单对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userId:"用户Id",
     *                      uaId:"用户地址Id",
     *                      cdId:"商品Id",
     *                      goCount:"商品数量",
     *                      expressFee:"物流费用",
     *                      cdSize:"商品规格",
     *                      scId:"购物车id"
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="增加订单")
    @PostMapping("")
    public Result addOrder(@RequestBody GoodsOrderSimple goodsOrderSimple){
        return goodsOrderService.addOrder(goodsOrderSimple);
    }

    /**
     * @api {GET} /order/all/{userId}/{start}/{end} 所有订单列表
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiParam {int} userId 用户Id
     * @apiParam {int} start 分页起始索引
     * @apiParam {int} end 查询列表长度
     * @apiDescription 所有订单列表
     * @apiParamExample {json} 请求样例：
     *  /order/all/1/1/10
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                     [{
     *                        goId: "订单Id",
     *                        cdId: "商品id",
     *                        orderState: "订单状态",
     *                        imgId:"商品图片",
     *                        title:"商品标题",
     *                        content:"商品内容",
     *                        payFee:"支付费用",
     *                     ]
     *                     }
     */
    @ApiOperation(value="所有订单列表")
    @GetMapping("/all/{userId}/{start}/{end}")
    public Result searchAllOrder(@PathVariable Integer userId, @PathVariable Integer start, @PathVariable Integer end){
        return goodsOrderService.searchAllOrder(userId, start, end);
    }

    /**
     * @api {GET} /order/complete/{userId}/{start}/{end} 已完成订单列表
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiParam {int} userId 用户Id
     * @apiParam {int} start 分页起始索引
     * @apiParam {int} end 查询列表长度
     * @apiDescription 已完成订单列表
     * @apiParamExample {json} 请求样例：
     *  /order/complete/1/0/10
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                     [{
     *                        goId: "订单Id",
     *                        cdId: "商品id",
     *                        orderState: "订单状态",
     *                        imgId:"商品图片",
     *                        title:"商品标题",
     *                        content:"商品内容",
     *                        payFee:"支付费用",
     *                     ]
     *                     }
     */
    @ApiOperation(value="已完成订单列表")
    @GetMapping("/complete/{userId}/{start}/{end}")
    public Result searchCompleteOrder(@PathVariable Integer userId,@PathVariable Integer start,@PathVariable Integer end){
        return goodsOrderService.searchOrderByState(OrderConst.DEAL_DONE,userId,start,end);
    }

    /**
     * @api {GET} /order/incomplete/{userId}/{start}/{end} 未完成订单列表
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiParam {int} userId 用户Id
     * @apiParam {int} start 分页起始索引
     * @apiParam {int} end 查询列表长度
     * @apiDescription 未完成订单列表
     * @apiParamExample {json} 请求样例：
     *  /order/incomplete/1/0/10
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                     [{
     *                        goId: "订单Id",
     *                        cdId: "商品id",
     *                        orderState: "订单状态",
     *                        imgId:"商品图片",
     *                        title:"商品标题",
     *                        content:"商品内容",
     *                        payFee:"支付费用",
     *                     ]
     *                     }
     */
    @ApiOperation(value="未完成订单列表")
    @GetMapping("/incomplete/{userId}/{start}/{end}")
    public Result searchCommentOrder(@PathVariable Integer userId,@PathVariable Integer start,@PathVariable Integer end){
        return goodsOrderService.searchOrderByState(OrderConst.ORDER_NEW,userId,start,end);
    }

    /**
     * @api {GET} /order/detail/{goId} 订单详情
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiParam {int} cdId 商品主键
     * @apiParam {int} userId 商品类型
     * @apiDescription 订单详情
     * @apiParamExample {json} 请求样例：
     *  /commodity/detail/1
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                      goId:"订单Id",
     *                      cdId:"商品id",
     *                      userId:"用户id",
     *                      merchantId:商户Id,
     *                      goCount:"商品数量",
     *                      orderState:"订单状态",
     *                      orderNumber:"订单号",
     *                      expressFee:"物流费用",
     *                      discountFee:"打折费用",
     *                      payFee:"实际支付费用",
     *                      createTime:"创建时间",
     *                      imgId:"商品图片",
     *                      cdColor:"商品颜色,
     *                      cdSize:"商品规格",
     *                      goodsPrice:"商品单价",
     *                      title:"商品标题",
     *                      content:"商品内容",
     *                      "address": {
     *                          "uaId": 1,
     *                          "city": "市",
     *                          "district": "区",
     *                          "address": "地址",
     *                          "recipient": "收件人",
     *                          "mobile": "手机号",
     *                          "userId": null,
     *                          "isDefault": 1,
     *                       }
     *                    }
     *                   }
     */
    @ApiOperation(value="订单详情")
    @GetMapping("/detail/{goId}")
    public Result getDetail(@PathVariable Integer goId){
        return goodsOrderService.searchOrderDetail(goId);
    }

    /**
     * @api {PUT} /order/cancel/{goId} 取消订单
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 取消订单
     * @apiParamExample {json} 请求样例:
     *           /order/cancel/1
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="取消订单")
    @PutMapping("/cancel/{goId}")
    public Result cancelOrder(@PathVariable Integer goId){
        return goodsOrderService.changeOrderState(goId,OrderConst.ORDER_CANCEL);
    }

    /**
     * @api {PUT} /order/deliver/confirm/{goId} 确认收货
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 取消订单
     * @apiParamExample {json} 请求样例:
     *           /order/deliver/confirm/1
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="确认收货")
    @PutMapping("/deliver/confirm/{goId}")
    public Result confirmDeliver(@PathVariable Integer goId){
        return goodsOrderService.changeOrderState(goId,OrderConst.DELIVER_RECEIVE);
    }

    /**
     * @api {DELETE} /order/{goId} 删除订单
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiParam {int} goId 订单表主键
     * @apiDescription 删除订单
     * @apiParamExample {json} 请求样例：
     *  /order/12
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {DELETE} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="删除订单")
    @DeleteMapping("/{goId}")
    public Result delOrder(@PathVariable Integer goId){
        return goodsOrderService.delOrder(goId);
    }

    /**
     * @api {POST} /order/deliver/remind 发货提醒
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 发货提醒
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userId:"用户Id",
     *                      goId:"订单Id",
     *                      merchantId:"商家Id"
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="发货提醒")
    @PostMapping("/deliver/remind")
    public Result addDeliverRemind(@RequestBody DeliverRemind deliverRemind){
        return deliverRemindService.addRemind(deliverRemind);
    }

    /**
     * @api {POST} /order/deliver/return 申请退货
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 申请退货
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userId:"用户Id",
     *                      goId:"订单Id",
     *                      merchantId:"商家Id",
     *                      explain:"退货说明",
     *                      reason:"退货原因---原因列表前台写死"
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="申请退货")
    @PostMapping("/deliver/return")
    public Result returnDeliver(@RequestBody GoodsReturn goodsReturn){
        //必须要有退货原因
        return goodsReturnService.addGoodsReturn(goodsReturn);
    }

    /**
     * @api {GET} /order/express/{goId} 查看订单物流
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiParam {int} goId 订单主键
     * @apiDescription 查看订单物流
     * @apiParamExample {json} 请求样例：
     *  /order/express/1
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                      deliverystatus:"物流状态 1在途中 2派件中 3已签收 4派送失败",
     *                      company:"快递公司",
     *                      number:"快递单号",
     *                      "list": [{
     *                          "time": 时间,
     *                          "status": "快递状态",
     *                       },{}]
     *                    }
     *                   }
     */
    @ApiOperation(value="查看订单物流")
    @GetMapping("/express/{goId}")
    public Result getExpressInfo(@PathVariable Integer goId){
        return goodsOrderService.searchExpress(goId);
    }

    /**
     * @api {POST} /order/addCart 加入购物车
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 申请退货
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userId:"用户Id",
     *                      cId:"商品Id",
     *                      scCount:"购买数量",
     *                      cdSize:"商品规格"
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="加入购物车")
    @PostMapping("/addCart")
    public Result addCart(@RequestBody ShopCart shopCart){
        return goodsOrderService.addCart(shopCart);
    }

    /**
     * @api {POST} /order/delCartById 删除指定购物车商品
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 申请退货
     * @apiParamExample {json} 请求样例:
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="删除指定购物车商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="scId",value="购物车id,必填：false",dataType="int",required=false,paramType="query")
    })
    @PostMapping("/delCartById")
    public Result delCartById(Integer scId){
        return goodsOrderService.delCartById(scId);
    }


    /**
     * @api {POST} /order/updCartCountById 修改指定购物车商品数量
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 申请退货
     * @apiParamExample {json} 请求样例:
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="修改指定购物车商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name="scId",value="购物车id,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="count",value="商品数量,必填：false",dataType="int",required=false,paramType="query")
    })
    @PostMapping("/updCartCountById")
    public Result updCartCountById(Integer scId,Integer count){
        return goodsOrderService.updCartCountById(scId,count);
    }

    /**
     * @api {POST} /order/getCartList 获取购物车商品列表
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 申请退货
     * @apiParamExample {json} 请求样例:
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="获取购物车商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id,必填：false",dataType="int",required=false,paramType="query")
    })
    @GetMapping("/getCartList")
    public Result getCartList(Integer userId){
        return goodsOrderService.getCartList(userId);
    }
}
