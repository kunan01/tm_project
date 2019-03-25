package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.Slides;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("轮播图")
@RestController
@RequestMapping("/system/slides")
public class SlidesController extends BizBaseController {

    /**
     * @api {POST} /slides/addSlides 添加轮播图
     * @apiGroup Slide
     * @apiVersion 0.0.1
     * @apiDescription 添加轮播图
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          slideImage:"图片",
     *                          slideSort:"排序",
     *                          objectType:"目标分类 1商品类别 2商品",
     *                          objectId:"目标id"
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "添加成功"
     *                      }
     */
    @ApiOperation(value="添加轮播图",notes="添加轮播图")
    @UserLoginToken
    @PostMapping(value = "/addSlides")
    public Result addSlides(@RequestBody Slides slides) {
        return slidesService.addSlides(slides);
    }

    /**
     * @api {POST} /slides/updSlides 修改轮播图
     * @apiGroup Slide
     * @apiVersion 0.0.1
     * @apiDescription 修改轮播图
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          slidesId:"轮播图id"             (必传)
     *                          slideImage:"图片",              (选填)
     *                          slideSort:"排序",               (选填)
     *                          isEffect:"是否有效 1有效 2无效"    (选填)
     *                          objectType:"目标分类 1商品类别 2商品", (选填)
     *                          objectId:"目标id"             (选填)
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;z
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "修改成功"
     *                      }
     */
    @ApiOperation(value="修改轮播图",notes="修改轮播图")
    @UserLoginToken
    @PostMapping(value = "/updSlides")
    public Result updSlides(@RequestBody Slides slides) {
        return slidesService.updSlides(slides);
    }

    /**
     * @api {POST} /slides/delSlides 删除轮播图
     * @apiGroup Slide
     * @apiVersion 0.0.1
     * @apiDescription 删除轮播图
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          slidesId:"轮播图id"             (必传)
     *                          sysUserId:"平台用户id"          (必传)
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "删除成功"
     *                      }
     */
    @ApiOperation(value="删除轮播图",notes="删除轮播图")
    @UserLoginToken
    @PostMapping(value = "/delSlides")
    public Result delSlides(@RequestBody Slides slides) {
        return slidesService.delSlides(slides);
    }

    /**
     * @api {POST} /slides/batchDeleteSlides 批量删除轮播图
     * @apiGroup Slide
     * @apiVersion 0.0.1
     * @apiDescription 批量删除轮播图
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          slidesIdList:"轮播图id数组"(必传)
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "删除成功"
     *                      }
     */
    @ApiOperation(value="批量删除轮播图",notes="批量删除轮播图")
    @UserLoginToken
    @PostMapping(value = "/batchDeleteSlides")
    public Result batchDeleteSlides(@RequestBody Slides slides) {
        return slidesService.batchDeleteSlides(slides);
    }


    /**
     * @api {GET} /slides/getSlidesList 查询轮播图
     * @apiGroup Slide
     * @apiVersion 0.0.1
     * @apiDescription 查询轮播图
     * @apiParam {Integer} pageNo   页码
     * @apiParam {Integer} pageSize   条数
     * @apiParamExample {json} 请求样例:
     *      /slides/getSlidesList?pageNo=1&pageSize=10
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data":{
     *                              slidesId:"轮播图id",
     *                              slideImage:"图片",
     *                              slideSort:"排序",
     *                              imageUrl:"图片跳转链接",
     *                              isEffect:"是否有效",
     *                              createdTime:"创建时间",
     *                              objectType:"目标分类 1商品类别 2商品", (选填)
     *                              objectId:"目标id"             (选填)
     *                           }
     *                      }
     */
    @ApiOperation(value="查询轮播图",notes="查询轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
//    @UserLoginToken
    @GetMapping(value = "/getSlidesList")
    public Result getSlidesList(Integer pageNo,Integer pageSize) {
        return slidesService.getSlidesList(pageNo,pageSize);
    }

}
