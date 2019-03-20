package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.ShippingInfo;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("店铺配送方式模块")
@RestController
@RequestMapping("/system/distribution")
public class DistributionController extends BizBaseController {

    /**
     * @api {GET} /distribution/getStoreDistribution 获取店铺配送方式
     * @apiGroup Distribution
     * @apiVersion 0.0.1
     * @apiDescription 获取店铺配送方式
     * @apiParam {Integer} shopId 店铺id(死值:1)
     * @apiParam {Integer} pageNo   页码
     * @apiParam {Integer} pageSize   条数
     * @apiParamExample {json} 请求样例:
     *      /distribution/getStoreDistribution?shopId=1&pageNo=1&pageSize=10
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              shipId:"店铺配送主键",
     *                              descript:"描述",
     *                              price:"价格",
     *                              shopId:"店铺id",
     *                              createdTime:"创建时间"
     *                          }
     *                      }
     */
    @ApiOperation(value="获取店铺配送方式",notes="获取店铺配送方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name="shopId",value="店铺id(死值:1)",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getStoreDistribution")
    public Result getStoreDistribution(Integer shopId,Integer pageNo,Integer pageSize) {
        return distributionService.getStoreDistribution(shopId,pageNo,pageSize);
    }

    /**
     * @api {POST} /distribution/updateDistribution 修改店铺配送方式
     * @apiGroup Distribution
     * @apiVersion 0.0.1
     * @apiDescription 修改店铺配送方式
     * @apiParamExample {json} 请求样例:
     *                         {
     *                             shipId:"店铺配送主键",(必填)
     *                             descript:"描述",     (可选)
     *                             price:"价格"         (可选)
     *                         }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {"修改成功"}
     *                      }
     */
    @ApiOperation(value="修改店铺配送方式",notes="修改店铺配送方式")
    @UserLoginToken
    @PostMapping(value = "/updateDistribution")
    public Result updateDistribution(@RequestBody ShippingInfo shippingInfo) {
        return distributionService.updateDistribution(shippingInfo);
    }

    /**
     * @api {POST} /distribution/addDistribution 添加店铺配送方式
     * @apiGroup Distribution
     * @apiVersion 0.0.1
     * @apiDescription 修改店铺配送方式
     * @apiParamExample {json} 请求样例:
     *                         {
     *                             shopId:"店铺配送主键",(必填)
     *                             descript:"描述",     (必填)
     *                             price:"价格"         (必填)
     *                         }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {"修改成功"}
     *                      }
     */
    @ApiOperation(value="修改店铺配送方式",notes="修改店铺配送方式")
    @UserLoginToken
    @PostMapping(value = "/addDistribution")
    public Result addDistribution(@RequestBody ShippingInfo shippingInfo) {
        return distributionService.addDistribution(shippingInfo);
    }

    /**
     * @api {GET} /distribution/delDistribution 删除店铺配送方式
     * @apiGroup Distribution
     * @apiVersion 0.0.1
     * @apiDescription 删除店铺配送方式
     * @apiParam {Integer} shipId 配送方式主键
     * @apiParamExample {json} 请求样例:
     *          /distribution/delDistribution?shipId=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {"修改成功"}
     *                      }
     */
    @ApiOperation(value="删除店铺配送方式",notes="删除店铺配送方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name="shipId",value="配送方式主键",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/delDistribution")
    public Result delDistribution(Integer shipId) {
        return distributionService.delDistribution(shipId);
    }

    /**
     * @api {POST} /distribution/batchDelDistribution 批量删除店铺配送方式
     * @apiGroup Distribution
     * @apiVersion 0.0.1
     * @apiDescription 批量删除店铺配送方式
     * @apiParamExample {json} 请求样例:
     *                  {
     *                      shipIdList:[主键数组]
     *                  }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {"修改成功"}
     *                      }
     */
    @ApiOperation(value="批量删除店铺配送方式",notes="批量删除店铺配送方式")
    @UserLoginToken
    @PostMapping(value = "/batchDelDistribution")
    public Result batchDelDistribution(@RequestBody ShippingInfo shippingInfo) {
        return distributionService.batchDelDistribution(shippingInfo);
    }

}
