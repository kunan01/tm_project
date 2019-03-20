package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.UserAddress;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("地址模块")
@RestController
@RequestMapping("/web/address")
public class AddressController extends BizBaseController {

    /**
     * @api {POST} /address/addUserAddress 添加用户地址
     * @apiGroup Address
     * @apiVersion 0.0.1
     * @apiDescription 添加用户地址
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId : "用户id",                              (必填)
     *                          userName : "收货人姓名",                         (必填)
     *                          addressLine1 : "地址行一：街道地址/邮政信箱",       (必填)
     *                          addressLine2 : "地址行二：公寓套房等"              (必填)
     *                          city : "城市",                                  (必填)
     *                          province : "州/省",                             (必填)
     *                          zipCode : "邮政编码",                            (必填)
     *                          country : "国家",                               (必填)
     *                          userPhone : "收货人电话",                        (必填)
     *                          instructions : "交货说明",                       (可选)
     *                          security : "送货说明：进入大楼的安全码或电话",        (可选)
     *                          isReceivePackage : "是否可以在周末交货 0不是 1是",  (必填)
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"添加成功"}
     *                       }
     */
    @ApiOperation(value="添加用户地址",notes="添加用户地址")
    @UserLoginToken
    @PostMapping("/addUserAddress")
    public Result addUserAddress(@RequestBody UserAddress userAddress) {
        return addressService.addUserAddress(userAddress);
    }

    /**
     * @api {POST} /address/getUserAddressListByUserId 获取用户地址集合
     * @apiGroup Address
     * @apiVersion 0.0.1
     * @apiDescription 获取用户地址集合
     * @apiParam {Integer} userId  用户id （必填）
     * @apiParamExample {json} 请求样例：
     *      /address/getUserAddressListByUserId?userId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               addressId : "地址id",
     *                               userId : "用户id",
     *                               userName : "收货人姓名",
     *                               addressLine1 : "地址行一：街道地址/邮政信箱",
     *                               addressLine2 : "地址行二：公寓套房等"
     *                               city : "城市",
     *                               province : "州/省",
     *                               zipCode : "邮政编码",
     *                               country : "国家",
     *                               userPhone : "收货人电话",
     *                               instructions : "交货说明",
     *                               security : "送货说明：进入大楼的安全码或电话",
     *                               isReceivePackage : "是否可以在周末交货 0不是 1是",
     *                               isDefault : "是否默认地址 0不是 1是",
     *                               dataStatus : "地址是否有效 0无效 1有效",
     *                               createdTime : "创建时间"
     *                           }
     *                       }
     */
    @ApiOperation(value="获取用户地址集合",notes="获取用户地址集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/getUserAddressListByUserId")
    public Result getUserAddressListByUserId(Integer userId) {
        return addressService.getUserAddressListByUserId(userId);
    }

    /**
     * @api {POST} /address/getUserAddressById 获取地址详情
     * @apiGroup Address
     * @apiVersion 0.0.1
     * @apiDescription 获取用户地址集合
     * @apiParam {Integer} addressId  地址id （必填）
     * @apiParam {Integer} userId  用户id （必填）
     * @apiParamExample {json} 请求样例：
     *      /address/getUserAddressById?addressId=1&userId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               addressId : "地址id",
     *                               userId : "用户id",
     *                               userName : "收货人姓名",
     *                               addressLine1 : "地址行一：街道地址/邮政信箱",
     *                               addressLine2 : "地址行二：公寓套房等"
     *                               city : "城市",
     *                               province : "州/省",
     *                               zipCode : "邮政编码",
     *                               country : "国家",
     *                               userPhone : "收货人电话",
     *                               instructions : "交货说明",
     *                               security : "送货说明：进入大楼的安全码或电话",
     *                               isReceivePackage : "是否可以在周末交货 0不是 1是",
     *                               isDefault : "是否默认地址 0不是 1是",
     *                               dataStatus : "地址是否有效 0无效 1有效",
     *                               createdTime : "创建时间"
     *                           }
     *                       }
     */
    @ApiOperation(value="获取地址详情",notes="获取地址详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="addressId",value="地址id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="userId",value="用户id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/getUserAddressById")
    public Result getUserAddressById(Integer addressId,Integer userId) {
        return addressService.getUserAddressById(addressId,userId);
    }

    /**
     * @api {POST} /address/updAddDefaultById 设置或取消默认地址
     * @apiGroup Address
     * @apiVersion 0.0.1
     * @apiDescription 设置或取消默认地址
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      addressId:"地址id",
     *                      userId:"用户id",
     *                      isDefault:"是否默认地址 0不是 1是"
     *                  }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @ApiOperation(value="设置或取消默认地址",notes="设置或取消默认地址")
    @UserLoginToken
    @PostMapping("/updAddDefaultById")
    public Result updAddDefaultById(@RequestBody UserAddress userAddress) {
        return addressService.updAddDefaultById(userAddress);
    }

    /**
     * @api {POST} /address/updAddById 修改用户地址信息
     * @apiGroup Address
     * @apiVersion 0.0.1
     * @apiDescription 修改用户地址信息
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      addressId:"地址id",                           (必填)
     *                      userId:"用户id",                              (必填)
     *                      userName:"收货人姓名",                         (可选)
     *                      addressLine1:"地址行一：街道地址/邮政信箱",       (可选)
     *                      addressLine2:"地址行二：公寓套房等",             (可选)
     *                      city:"城市",                                  (可选)
     *                      province:"州/省",                             (可选)
     *                      zipCode:"邮政编码",                            (可选)
     *                      country:"国家",                               (可选)
     *                      userPhone:"收货人电话",                        (可选)
     *                      instructions:"交货说明",                       (可选)
     *                      security:"送货说明：进入大楼的安全码或电话",        (可选)
     *                      isReceivePackage:"是否可以在周末交货 0不是 1是",   (可选)
     *                  }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"修改成功"}
     *                       }
     */
    @ApiOperation(value="修改用户地址信息",notes="修改用户地址信息")
    @UserLoginToken
    @PostMapping("/updAddById")
    public Result updAddById(@RequestBody UserAddress userAddress) {
        return addressService.updAddById(userAddress);
    }

    /**
     * @api {POST} /address/delAddById 删除用户地址信息
     * @apiGroup Address
     * @apiVersion 0.0.1
     * @apiDescription 删除用户地址信息
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      addressId:"地址id",       (必填)
     *                      userId:"用户id",          (必填)
     *                  }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"修改成功"}
     *                       }
     */
    @ApiOperation(value="删除用户地址信息",notes="删除用户地址信息")
    @UserLoginToken
    @PostMapping("/delAddById")
    public Result delAddById(@RequestBody UserAddress userAddress) {
        return addressService.delAddById(userAddress);
    }

    /**
     * @api {POST} /address/getDeliveryAddress 获取店铺发货地址
     * @apiGroup Address
     * @apiVersion 0.0.1
     * @apiDescription 获取店铺发货地址
     * @apiParam {Integer} shopId  店铺id （必填）
     * @apiParamExample {json} 请求样例：
     *      /address/getDeliveryAddress?shopId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               daId : "发货地址id",
     *                               shopId : "店铺id",
     *                               senderName : "发件人姓名",
     *                               senderPhone : "收货人电话",
     *                               addressLine1 : "地址行一：街道地址/邮政信箱",
     *                               addressLine2 : "地址行二：公寓套房等"
     *                               city : "城市",
     *                               province : "州/省",
     *                               zipCode : "邮政编码",
     *                               country : "国家",
     *                               createdTime : "创建时间"
     *                           }
     *                       }
     */
    @ApiOperation(value="获取店铺发货地址",notes="获取店铺发货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name="shopId",value="店铺id",dataType="int",required=true,paramType="query")
    })
    @GetMapping("/getDeliveryAddress")
    public Result getDeliveryAddress(Integer shopId) {
        return addressService.getDeliveryAddress(shopId);
    }

}
