package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.Trend;
import com.tangmo.emall.entity.TrendAdvertising;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("首页趋势活动模块")
@RestController
@RequestMapping("/system/trend")
public class TrendController extends BizBaseController {

    /**
     * @api {GET} /trend/getTrendList 获取趋势列表
     * @apiGroup Trend
     * @apiVersion 0.0.1
     * @apiDescription 获取趋势列表
     * @apiParam {Integer} pageNo 页码
     * @apiParam {Integer} pageSize 条数
     * @apiParamExample {json} 请求样例：
     *      /trend/getTrendList?pageNo=1&pageSize=1
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
     *                               location:"描述语位置 12345",
     *                               trends:"包含4个商品信息"
     *                               createdTime:"创建时间"
     *                           }
     *                       }
     */
    @ApiOperation(value="获取趋势列表",notes="获取趋势列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/getTrendList")
    public Result getTrendList(Integer pageNo,Integer pageSize) {
        return trendService.getTrendList(pageNo,pageSize);
    }

    /**
     * @api {POST} /trend/updTrend 修改趋势信息
     * @apiGroup Trend
     * @apiVersion 0.0.1
     * @apiDescription 修改趋势信息
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      taId:"主键id",
     *                      title:"趋势标题",
     *                      advertisingImage:"广告图",
     *                      descript:"描述语",
     *                      location:"样式状态 1-5"
     *                  }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"修改成功"
     *                       }
     */
    @ApiOperation(value="修改趋势信息",notes="修改趋势信息")
    @UserLoginToken
    @PostMapping("/updTrend")
    public Result updTrend(@RequestBody TrendAdvertising trendAdvertising) {
        return trendService.updTrend(trendAdvertising);
    }

    /**
     * @api {POST} /trend/addTrend 添加趋势信息
     * @apiGroup Trend
     * @apiVersion 0.0.1
     * @apiDescription 添加趋势信息
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      title:"趋势标题",
     *                      advertisingImage:"广告图",
     *                      descript:"描述语",
     *                      location:"样式状态 1-5"
     *                  }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"添加成功"
     *                       }
     */
    @ApiOperation(value="添加趋势信息",notes="添加趋势信息")
    @UserLoginToken
    @PostMapping("/addTrend")
    public Result addTrend(@RequestBody TrendAdvertising trendAdvertising) {
        return trendService.addTrend(trendAdvertising);
    }

    /**
     * @api {GET} /trend/delTrend 删除趋势信息
     * @apiGroup Trend
     * @apiVersion 0.0.1
     * @apiDescription 删除趋势信息
     * @apiParam {Integer} taId 趋势id
     * @apiParamExample {json} 请求样例：
     *      /trend/delTrend?taId=1
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"删除成功"}
     *                       }
     */
    @ApiOperation(value="删除趋势信息",notes="删除趋势信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taId",value="趋势id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/delTrend")
    public Result delTrend(Integer taId) {
        return trendService.delTrend(taId);
    }

    /**
     * @api {POST} /trend/batchDelTrend 批量删除趋势信息
     * @apiGroup Trend
     * @apiVersion 0.0.1
     * @apiDescription 批量删除趋势信息
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          taIdList:[数组：趋势id]
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"删除成功"}
     *                       }
     */
    @ApiOperation(value="批量删除趋势信息",notes="批量删除趋势信息")
    @UserLoginToken
    @PostMapping("/batchDelTrend")
    public Result batchDelTrend(@RequestBody TrendAdvertising trendAdvertising) {
        return trendService.batchDelTrend(trendAdvertising);
    }

    /**
     * @api {POST} /trend/addTrendProduct 添加趋势商品
     * @apiGroup Trend
     * @apiVersion 0.0.1
     * @apiDescription 添加趋势商品
     * @apiParamExample {json} 请求样例：
     *          /trend/addTrendProduct?taId=1&productId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"添加成功"
     *                       }
     */
    @ApiOperation(value="添加趋势商品",notes="添加趋势商品")
    @UserLoginToken
    @PostMapping("/addTrendProduct")
    public Result addTrendProduct(@RequestBody Trend trend) {
        return trendService.addTrendProduct(trend);
    }

    /**
     * @api {POST} /trend/batchAddTrendProduct 批量添加趋势商品
     * @apiGroup Trend
     * @apiVersion 0.0.1
     * @apiDescription 批量添加趋势商品
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      taId:趋势id,
     *                      productIdList:[数组：商品id]
     *                  }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"添加成功"
     *                       }
     */
    @ApiOperation(value="批量添加趋势商品",notes="批量添加趋势商品")
    @UserLoginToken
    @PostMapping("/batchAddTrendProduct")
    public Result batchAddTrendProduct(@RequestBody Trend trend) {
        return trendService.batchAddTrendProduct(trend);
    }

    /**
     * @api {POST} /trend/delTrendProduct 删除趋势商品
     * @apiGroup Trend
     * @apiVersion 0.0.1
     * @apiDescription 删除趋势商品
     * @apiParam {Integer} ttId 趋势商品信息id
     * @apiParamExample {json} 请求样例：
     *          /trend/delTrendProduct?productId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"删除成功"
     *                       }
     */
    @ApiOperation(value="删除趋势商品",notes="删除趋势商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ttId",value="趋势商品信息id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/delTrendProduct")
    public Result delTrendProduct(Integer ttId) {
        return trendService.delTrendProduct(ttId);
    }

    /**
     * @api {POST} /trend/batchDelTrendProduct 批量删除趋势商品
     * @apiGroup Trend
     * @apiVersion 0.0.1
     * @apiDescription 批量删除趋势商品
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          ttIdList:[数组：趋势商品信息id]
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"删除成功"
     *                       }
     */
    @ApiOperation(value="批量删除趋势商品",notes="批量删除趋势商品")
    @UserLoginToken
    @PostMapping("/batchDelTrendProduct")
    public Result batchDelTrendProduct(@RequestBody Trend trend) {
        return trendService.batchDelTrendProduct(trend);
    }


    /**
     * @api {GET} /trend/getTrendProductList 删除趋势信息（多个以逗号隔开）
     * @apiGroup Trend
     * @apiVersion 0.0.1
     * @apiDescription 删除趋势信息（多个以逗号隔开）
     * @apiParamExample {json} 请求样例：
     *      /trend/getTrendProductList?taId=1
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"删除成功"}
     *                       }
     */
    @ApiOperation(value="获取趋势商品信息",notes="获取趋势商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taId",value="趋势id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
//    @UserLoginToken
    @GetMapping("/getTrendProductList")
    public Result getTrendProductList(Integer taId,Integer pageNo,Integer pageSize) {
        return trendService.getTrendProductList(taId,pageNo,pageSize);
    }
}
