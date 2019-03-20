package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.Collect;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("收藏模块")
@RestController
@RequestMapping("/web/collect")
public class CollectController extends BizBaseController {

    /**
     * @api {POST} /collect/AddCollection 添加或取消收藏
     * @apiGroup Collect
     * @apiVersion 0.0.1
     * @apiDescription 添加或取消收藏
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",
     *                          productId:"商品id",
     *                          dataFailure:"收藏状态 0取消收藏 1收藏"
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"添加成功"
     *                       }
     */
    @ApiOperation(value="添加或取消收藏",notes="添加或取消收藏")
    @UserLoginToken
    @PostMapping("/AddCollection")
    public Result AddCollection(@RequestBody Collect collect) {
        return collectService.AddCollection(collect);
    }

    /**
     * @api {POST} /collect/delCollection 删除收藏
     * @apiGroup Collect
     * @apiVersion 0.0.1
     * @apiDescription 删除收藏
     * @apiParam {Integer}  userId     用户id
     * @apiParam {String}   collectId  收藏id（多个以逗号隔开）
     * @apiParamExample {json} 请求样例：
     *      /product/delCollection?userId=1&collectId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"删除成功"
     *                       }
     */
    @ApiOperation(value="删除收藏",notes="删除收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="collectId",value="收藏id（多个以逗号隔开）",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/delCollection")
    public Result delCollection(Integer userId,String collectId) {
        return collectService.delCollection(userId,collectId);
    }

    /**
     * @api {POST} /collect/EmptyCollection 清空收藏
     * @apiGroup Collect
     * @apiVersion 0.0.1
     * @apiDescription 清空收藏
     * @apiParam {Integer} userId  用户id
     * @apiParamExample {json} 请求样例：
     *      /product/EmptyCollection?userId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"删除成功"
     *                       }
     */
    @ApiOperation(value="清空收藏",notes="清空收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping("/EmptyCollection")
    public Result EmptyCollection(Integer userId) {
        return collectService.EmptyCollection(userId);
    }

    /**
     * @api {GET} /collect/QueryCollectionList 获取用户收藏列表
     * @apiGroup Collect
     * @apiVersion 0.0.1
     * @apiDescription 清空收藏
     * @apiParam {Integer} userId    用户id
     * @apiParam {Integer} pageNo    页码
     * @apiParam {Integer} pageSize  条数
     * @apiParamExample {json} 请求样例：
     *      /product/QueryCollectionList?userId=1&pageNo=1&pageSize=1
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               collectId:"收藏信息主键",
     *                               userId:"用户id",
     *                               productId:"商品id",
     *                               createdTime:"添加时间",
     *                               dataFailure:"是否失效 0为失效 1已失效",
     *                               product{
     *                                   商品信息.....
     *                               }
     *                           }
     *                       }
     */
    @ApiOperation(value="获取用户收藏列表",notes="获取用户收藏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/QueryCollectionList")
    public Result QueryCollectionList(Integer userId,Integer pageNo,Integer pageSize) {
        return collectService.QueryCollectionList(userId,pageNo,pageSize);
    }
}
