package com.tangmo.emall.controller;


import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.ProductComment;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("商品评论")
@RestController
@RequestMapping("/web/comment")
public class ProductCommentController extends BizBaseController {

    /**
     * @api {POST} /comment/addComment 添加商品评论
     * @apiGroup Comment
     * @apiVersion 0.0.1
     * @apiDescription 添加商品评论
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          content:"评论内容",                (必填)
     *                          imgId:"评论图片（多个以逗号隔开）",   (必填)
     *                          productId:"商品id",               (必填)
     *                          orderId:"订单id",                 (必填)
     *                          userId:"用户id",                  (必填)
     *                          commentStar:"评分（星星数）"        (可选)
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"添加成功"}
     *                       }
     */
    @ApiOperation(value="添加商品评论",notes="添加商品评论")
    @UserLoginToken
    @PostMapping("/addComment")
    public Result addComment(@RequestBody ProductComment productComment) {
        return productCommentService.addComment(productComment);
    }

    /**
     * @api {GET} /comment/getCommentList 获取商品评论
     * @apiGroup Comment
     * @apiVersion 0.0.1
     * @apiDescription 添加商品评论
     * @apiParam {Integer}  sType 0：按时间倒序，（其他预留）(必填)
     * @apiParam {Integer}  productId  商品id            (必填)
     * @apiParam {Integer}  pageNo  页码                  (必填)
     * @apiParam {Integer}  pageSize  条数                (必填)
     * @apiParam {Integer}  userId  用户id                (可选)
     * @apiParamExample {json} 请求样例：
     *         /comment/getCommentList?productId=1&pageNo=1&pageSize=1
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              commentId:"评论id",
     *                              imgList:"评论图片集合"
     *                              content:"评论内容",
     *                              productId:"商品id",
     *                              orderId:"订单id",
     *                              userId:"用户id",
     *                              commentStar:"评分（星星数量）",
     *                              auditStatus:"审核状态 0未审核 1已审核",
     *                              auditTime:"审核时间",
     *                              upvoteNum:"",
     *                              createdTime:"",
     *                              updatedTime:""
     *                           }
     *                       }
     */
    @ApiOperation(value="获取商品评论",notes="获取商品评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sType",value="排序方式id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="productId",value="商品id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="userId",value="用户id",dataType="int",required=false,paramType="query")
    })
    @GetMapping("/getCommentList")
    public Result getCommentList(Integer sType,Integer productId,Integer pageNo,Integer pageSize,Integer userId) {
        return productCommentService.getCommentList(sType,productId,pageNo,pageSize,userId);
    }

    /**
     * @api {GET} /comment/giveALike 点赞评论
     * @apiGroup Comment
     * @apiVersion 0.0.1
     * @apiDescription 添加商品评论
     * @apiParam {Integer}  userId  用户id
     * @apiParam {Integer}  commentId  评论id
     * @apiParamExample {json} 请求样例：
     *         /comment/giveALike?userId=1&commentId=1
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"点赞成功"}
     *                       }
     */
    @ApiOperation(value="点赞评论",notes="点赞评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="commentId",value="评论id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/giveALike")
    public Result giveALike(Integer userId,Integer commentId) {
        return productCommentService.giveALike(userId,commentId);
    }

    /**
     * @api {GET} /comment/getCommentSortingWay 获取评论排序方式规则
     * @apiGroup Comment
     * @apiVersion 0.0.1
     * @apiDescription 获取评论排序方式规则
     * @apiParamExample {json} 请求样例:
     *      /comment/getCommentSortingWay
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              sortingId:"主键id",
     *                              title:"描述"
     *                          }
     *                      }
     */
    @ApiOperation(value="获取评论排序方式规则",notes="获取评论排序方式规则")
    @GetMapping(value = "/getCommentSortingWay")
    public Result getCommentSortingWay() {
        return productCommentService.getCommentSortingWay();
    }

    /**
     * @api {GET} /comment/canYouComment 用户是否可以对当前商品进行评论
     * @apiGroup Comment
     * @apiVersion 0.0.1
     * @apiDescription 用户是否可以对当前商品进行评论
     * @apiParam {Integer}  userId  用户id
     * @apiParam {Integer}  productId  商品id
     * @apiParamExample {json} 请求样例:
     *      /comment/canYouComment?userId=1&productId=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {false/"评论订单字符"}
     *                      }
     */
    @ApiOperation(value="用户是否可以对当前商品进行评论",notes="用户是否可以对当前商品进行评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="productId",value="商品id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/canYouComment")
    public Result canYouComment(Integer userId,Integer productId) {
        return productCommentService.canYouComment(userId,productId);
    }

}
