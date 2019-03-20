package com.tangmo.emall.controller;

import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("轮播图")
@RestController
@RequestMapping("/web/slides")
public class SlidesController extends BizBaseController {

    /**
     * @api {GET} /slides/getSlidesList 获取轮播图
     * @apiGroup Slides
     * @apiVersion 0.0.1
     * @apiDescription 获取轮播图
     * @apiParamExample {json} 请求样例：
     *      /slides/getSlidesList
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              slidesId:"轮播图id",
     *                              slideImage:"图片",
     *                              slideSort:"排序",
     *                              imageUrl:"图片跳转链接",
     *                              createdTime:"创建时间"
     *                           }
     *                       }
     */
    @ApiOperation(value="获取轮播图",notes="获取轮播图")
    @GetMapping("/getSlidesList")
    public Result getSlidesList() {
        return slidesService.getSlidesList();
    }
}
