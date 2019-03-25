package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.Recommend;
import com.tangmo.emall.entity.RecommendAdvertising;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("首页热门活动模块")
@RestController
@RequestMapping("/system/advertising")
public class AdvertisingController extends BizBaseController {

    /**
     * @api {GET} /advertising/getAdvertisingList 获取热门活动广告
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 获取热门活动广告
     * @apiParam {Integer} pageNo   页码
     * @apiParam {Integer} pageSize   条数
     * @apiParamExample {json} 请求样例：
     *      /advertising/getAdvertisingList?pageN=1&pageNo=10
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
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
//    @UserLoginToken
    @GetMapping("/getAdvertisingList")
    public Result getAdvertisingList(Integer pageNo,Integer pageSize) {
        return advertisingService.getAdvertisingList(pageNo,pageSize);
    }

    /**
     * @api {POST} /advertising/addAdvertising 添加热门活动广告
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 添加热门活动广告
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      advertisingImage:"广告图片"
     *                  }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"添加成功"
     *                       }
     */
    @ApiOperation(value="添加热门活动广告",notes="添加热门活动广告")
    @UserLoginToken
    @PostMapping("/addAdvertising")
    public Result addAdvertising(@RequestBody RecommendAdvertising recommendAdvertising) {
        return advertisingService.addAdvertising(recommendAdvertising);
    }

    /**
     * @api {POST} /advertising/updateAdvertising 修改热门活动广告
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 修改热门活动广告
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      raId:"广告id" （必填）
     *                      advertisingImage:"广告图片" （可选）
     *                      descript:"描述语"（可选）
     *                      title:"标题"（可选）
     *                      productId:"商品id"（可选）
     *                  }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"修改成功"
     *                       }
     */
    @ApiOperation(value="修改热门活动广告",notes="修改热门活动广告")
    @UserLoginToken
    @PostMapping("/updateAdvertising")
    public Result updateAdvertising(@RequestBody RecommendAdvertising recommendAdvertising) {
        return advertisingService.updateAdvertising(recommendAdvertising);
    }

    /**
     * @api {GET} /advertising/delAdvertising 删除热门活动广告
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 删除热门活动广告
     * @apiParamExample {json} 请求样例：
     *       /advertising/delAdvertising?raId=1
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"删除成功"
     *                       }
     */
    @ApiOperation(value="删除热门活动广告",notes="删除热门活动广告")
    @ApiImplicitParams({
            @ApiImplicitParam(name="raId",value="热门活动广告id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/delAdvertising")
    public Result delAdvertising(Integer raId) {
        return advertisingService.delAdvertising(raId);
    }

    /**
     * @api {POST} /advertising/batchDelAdvertising 批量删除热门活动广告
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 批量删除热门活动广告
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      raIdList:[主键数组]
     *                  }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"删除成功"
     *                       }
     */
    @ApiOperation(value="批量删除热门活动广告",notes="批量删除热门活动广告")
    @UserLoginToken
    @PostMapping("/batchDelAdvertising")
    public Result batchDelAdvertising(@RequestBody RecommendAdvertising recommendAdvertising) {
        return advertisingService.batchDelAdvertising(recommendAdvertising);
    }

    /**
     * @api {POST} /advertising/addAdvertisingProduct 添加热门活动广告商品
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 添加热门活动广告商品
     * @apiParam {Integer} raId 热门活动广告id
     * @apiParam {Integer} productId 商品id
     * @apiParamExample {json} 请求样例：
     *      /advertising/addAdvertisingProduct?raId=1&productId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"添加成功"
     *                       }
     */
    @ApiOperation(value="添加热门活动广告商品",notes="添加热门活动广告商品")
    @UserLoginToken
    @PostMapping("/addAdvertisingProduct")
    public Result addAdvertisingProduct(@RequestBody Recommend recommend) {
        return advertisingService.addAdvertisingProduct(recommend);
    }

    /**
     * @api {POST} /advertising/batchAddAdvertisingProduct 批量添加热门活动广告商品
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 批量添加热门活动广告商品
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          productIdList:[商品id数组],
     *                          raId:热门活动广告id
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"添加成功"
     *                       }
     */
    @ApiOperation(value="批量添加热门活动广告商品",notes="批量添加热门活动广告商品")
    @UserLoginToken
    @PostMapping("/batchAddAdvertisingProduct")
    public Result batchAddAdvertisingProduct(@RequestBody Recommend recommend) {
        return advertisingService.batchAddAdvertisingProduct(recommend);
    }

    /**
     * @api {POST} /advertising/delAdvertisingProduct 删除热门活动广告商品
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 删除热门活动广告商品
     * @apiParam {Integer} trId 热门活动商品主键id
     * @apiParamExample {json} 请求样例：
     *      /advertising/delAdvertisingProduct?trId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"删除成功"
     *                       }
     */
    @ApiOperation(value="删除热门活动广告商品",notes="删除热门活动广告商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="trId",value="热门活动商品主键id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/delAdvertisingProduct")
    public Result delAdvertisingProduct(Integer trId) {
        return advertisingService.delAdvertisingProduct(trId);
    }

    /**
     * @api {POST} /advertising/batchDelAdvertisingProduct 批量删除热门活动广告商品
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 批量删除热门活动广告商品
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          trIdList:[数组：热门活动商品主键id]
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"删除成功"
     *                       }
     */
    @ApiOperation(value="批量删除热门活动广告商品",notes="批量删除热门活动广告商品")
    @UserLoginToken
    @PostMapping("/batchDelAdvertisingProduct")
    public Result batchDelAdvertisingProduct(@RequestBody Recommend recommend) {
        return advertisingService.batchDelAdvertisingProduct(recommend);
    }


    /**
     * @api {GET} /advertising/getAdvertisingProductList 获取热门活动广告商品
     * @apiGroup Activity
     * @apiVersion 0.0.1
     * @apiDescription 获取热门活动广告商品
     * @apiParam {Integer} raId 热门活动id
     * @apiParam {Integer} pageNo 页码
     * @apiParam {Integer} pageSize 条数
     * @apiParamExample {json} 请求样例：
     *      /advertising/getAdvertisingProductList?raId=1&pageNo=1&pageSize=10
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
    @ApiOperation(value="获取热门活动广告商品",notes="获取热门活动广告商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="raId",value="热门活动id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/getAdvertisingProductList")
    public Result getAdvertisingProductList(Integer raId,Integer pageNo,Integer pageSize) {
        return advertisingService.getAdvertisingProductList(raId,pageNo,pageSize);
    }

}
