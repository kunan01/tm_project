package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.OrderDetail;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("订单模块")
@RestController
@RequestMapping("/web/order")
public class OrderController extends BizBaseController {

    /**
     * @api {POST} /order/addOrder 添加订单
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 添加订单（每个订单 十分钟内有效）
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId : "用户id",
     *                          productId:"商品id",
     *                          specId:"规格id",
     *                          productCount:"购买数量",
     *                          productPrice:"购买单价",
     *                          addressId:"地址id",
     *                          disId:"优惠券id",（预留）
     *                          expressId:"配送方式id"（预留）,
     *                          prescriptionId:"处方id",
     *                          prescriptionImage:"处方图片",
     *                          prescriptionPrice:"处方价格"
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"添加成功"
     *                       }
     */
    @ApiOperation(value="添加订单",notes="添加订单（每个订单 十分钟内有效）")
    @UserLoginToken
    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody OrderDetail orderDetail) {
        return orderService.addOrder(orderDetail);
    }

    /**
     * @api {POST} /order/cancelOrder 取消订单
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 取消订单
     * @apiParam {Integer} detailId  订单明细id
     * @apiParamExample {json} 请求样例：
     *      /order/cancelOrder?detailId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"成功取消"
     *                       }
     */
    @ApiOperation(value="取消订单",notes="取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="detailId",value="订单明细id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/cancelOrder")
    public Result cancelOrder(Integer detailId) {
        return orderService.cancelOrder(detailId);
    }

    /**
     * @api {POST} /order/deleteOrder 删除订单
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 删除订单
     * @apiParam {Integer} detailId 订单明细id
     * @apiParamExample {json} 请求样例：
     *      /order/deleteOrder?detailId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"成功取消"
     *                       }
     */
    @ApiOperation(value="删除订单",notes="删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="detailId",value="订单明细id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/deleteOrder")
    public Result deleteOrder(Integer detailId) {
        return orderService.deleteOrder(detailId);
    }

    /**
     * @api {POST} /order/queryOrderList 查看订单列表
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 查看订单列表
     * @apiParam {Integer} userId  用户id
     * @apiParam {Integer} state  查询状态 (0=全部,1=代付款,2=代发货,3=派送中,4=已收获,5=退款订单)
     * @apiParam {Integer} pageNo  页码
     * @apiParam {Integer} pageSize  条数
     * @apiParamExample {json} 请求样例：
     *      /order/queryOrderList?userId=1&state=1&pageNo=1&pageSize=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              detailId:"订单明细主键",
     *                              orderId:"订单id",
     *                              productId:"商品id",
     *                              shopId:"店铺id",
     *                              specId:"规格id",
     *                              productName:"商品名称",
     *                              productCount:"购买数量",
     *                              productPrice:"购买单价",
     *                              updatedTime:"修改时间",
     *                              order{"订单详细信息"},
     *                              product{"商品详细信息"},
     *                              productSpec{"规格详细信息"}
     *                              shop{"店铺基本信息"}（预留）
     *                           }
     *                       }
     */
    @ApiOperation(value="查看订单列表",notes="查看订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="state",value="查询状态 (0=全部,1=代付款,2=代发货,3=派送中,4=已收获,5=退款订单)",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/queryOrderList")
    public Result queryOrderList(Integer userId,Integer state,Integer pageNo,Integer pageSize) {
        return orderService.queryOrderList(userId,state,pageNo,pageSize);
    }

    /**
     * @api {POST} /order/getOrderDetailById 查看订单详情
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 查看订单详情
     * @apiParam {Integer} detailId 订单明细id
     * @apiParamExample {json} 请求样例：
     *      /order/getOrderDetailById?detailId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              detailId:"订单明细主键",
     *                              orderId:"订单id",
     *                              productId:"商品id",
     *                              shopId:"店铺id",
     *                              specId:"规格id",
     *                              productName:"商品名称",
     *                              productCount:"购买数量",
     *                              productPrice:"购买单价",
     *                              updatedTime:"修改时间",
     *                              order{"订单详细信息"},
     *                              product{"商品详细信息"},
     *                              productSpec{"规格详细信息"}
     *                              shop{"店铺基本信息"}（预留）
     *                           }
     *                       }
     */
    @ApiOperation(value="查看订单详情",notes="查看订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="detailId",value="订单明细id",dataType="int",required=true,paramType="query"),
    })
    @UserLoginToken
    @PostMapping("/getOrderDetailById")
    public Result getOrderDetailById(Integer detailId) {
        return orderService.getOrderDetailById(detailId);
    }

    /**
     * @api {POST} /order/ConfirmOrder 确认收货
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 确认收货
     * @apiParam {Integer} detailId 订单明细id
     * @apiParamExample {json} 请求样例：
     *      /order/ConfirmOrder?detailId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"确认成功"
     *                       }
     */
    @ApiOperation(value="确认收货",notes="确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name="detailId",value="订单明细id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/ConfirmOrder")
    public Result ConfirmOrder(Integer detailId) {
        return orderService.ConfirmOrder(detailId);
    }

    /**
     * @api {POST} /order/getPayInformation 获取支付页面信息
     * @apiGroup Order
     * @apiVersion 0.0.1
     * @apiDescription 获取支付页面信息
     *      /order/getPayInformation?detailId=1,2
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               "":"",
     *                           }
     *                       }
     */
    @ApiOperation(value="获取支付页面信息",notes="获取支付页面信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="detailId",value="订单明细id,多个以，隔开",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/getPayInformation")
    public Result getPayInformation(String detailId) {
        return orderService.getPayInformation(detailId);
    }

}
