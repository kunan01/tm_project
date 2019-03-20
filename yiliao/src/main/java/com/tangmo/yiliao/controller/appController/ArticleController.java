package com.tangmo.yiliao.controller.appController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description 文章
 */
@Api("app文章")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {


    /**
     * @api {POST} /article/homeSearch 首页搜索
     * @apiGroup Article
     * @apiVersion 0.0.1
     * @apiDescription 获取首页部分科室
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          condName:"搜索内容"
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               doctorList{      //医生集合
     *                                  userId:"用户id",
     *                                  name:"姓名",
     *                                  ddHospital:"坐诊医院",
     *                                  ddPosition:"医院职务",
     *                                  integral:"积分",
     *                                  ddService:"擅长疾病",
     *                                  userImgUrl:"头像"
     *                               },
     *                               articleList{
     *                                  saId: 文章id，
     *                                  dtId: 科室id，
     *                                  saTitle: 文章标题,
     *                                  visitNumber:"游览次数",
     *                                  createTime:"发布时间"，
     *                                  saSource:"文章来源",
     *                                  saImgId:"文章导图"
     *                               }
     *                               videoList{
     *                                   dvId: 视频主键id,
     *                                   dvImgId:"视频展示图片id",
     *                                   dvTitle:"视频标题",
     *                                   dvIntegral:"积分",
     *                                   visitNumber:"游览次数",
     *                                   commCount:"评论数"
     *                               }
     *                           }
     *                       }
     */
    @PostMapping("/homeSearch")
    public Result homeSearch(@RequestBody SelectUser selectUser){
        return articleService.homeSearch(selectUser);
    }


    /**
     * @api {GET} /article/partOfArticle/{start}/{end} 获取首页部分问答
     * @apiGroup Article
     * @apiVersion 0.0.1
     * @apiDescription 获取首页部分科室
     * @apiParamExample {json} 请求样例：
     *      /article/partOfArticle/{start}/{end}
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               saId: 文章id，
     *                               saTitle: 文章标题,
     *                               saContent:"文章内容",
     *                               saImgId:"文章导图",
     *                               userId:"用户id",
     *                               visitNumber:"游览次数",
     *                               saIntegral:"积分",
     *                               commCount:"评论数",
     *                               createTime:"发布时间"
     *                           }
     *                       }
     */
    @GetMapping("/partOfArticle/{start}/{end}")
    public Result getPartOfArticle(@PathVariable Integer start,@PathVariable Integer end){
        return articleService.getPartOfArticle(start,end);
    }

    /**
     * @api {GET} /article/getArticleById/{userId}/{start}/{end} 获取我的问答
     * @apiGroup Article
     * @apiVersion 0.0.1
     * @apiDescription 获取我的问答
     * @apiParamExample {json} 请求样例：
     *      /article/getArticleById/{userId}/{start}/{end}
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               saId: 文章id，
     *                               saTitle: 文章标题,
     *                               saContent:"文章内容",
     *                               saImgId:"文章导图",
     *                               userId:"用户id",
     *                               visitNumber:"游览次数",
     *                               saIntegral:"积分",
     *                               commCount:"评论数",
     *                               createTime:"发布时间"
     *                           }
     *                       }
     */
    @GetMapping("/getArticleById/{userId}/{start}/{end}")
    public Result getArticleById(@PathVariable String userId,@PathVariable Integer start,@PathVariable Integer end){
        return articleService.getArticleById(userId,start,end);
    }


    /**
     * @api {GET} /article/getArticleById/{aId}/{userId} 获取文章详情
     * @apiGroup Article
     * @apiVersion 0.0.1
     * @apiDescription 获取首页部分科室
     * @apiParam {String} aId 文章id
     * @apiParam {String} userId 当前用户id
     * @apiParamExample {json} 请求样例：
     *      /article/partOfArticle/{aId}/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               "article":{    //文章
     *                                      saTitle:"文章标题",
     *                                      saContent:"文章内容",
     *                                      visitNumber:"游览次数",
     *                                      createTime:"发布时间"
     *                               }
     *                               "comments":{    //评论
     *                                       cId:"评论id",
     *                                       userName:"评论人姓名",
     *                                       userImgUrl:"评论人头像",
     *                                       cContent:"评论内容"
     *                                       cAttribute:"评论属性 0文字 1语音"
     *                                       cStatus:"评论类别 0评论 1回复"
     *                                       replyCount:"总回复数量"
     *                                       roleId:"评论人身份  DOCTOR:"医生"，MEMBER:"普通用户" ",
     *                                       createTime:"评论时间"
     *                                       commentsList:{//回复
     *                                              cId:"回复id",
     *                                              userName:"评论人姓名",
     *                                              userImgUrl:"评论人头像",
     *                                              cContent:"回复内容",
     *                                              cAttribute:"回复属性 0文字 1语音",
     *                                              cStatus:"回复类别 0评论 1回复",
     *                                              roleId:"评论人身份  DOCTOR:"医生"，MEMBER:"普通用户" ",
     *                                              createTime:"回复时间"
     *                                       }
     *                               }
     *                               "recommended":{     //推荐文章
     *                                      saId:"文章id",
     *                                      saTitle:"文章标题"
     *                                      visitNumber:"游览次数"
     *                                      createTime:"发布时间"
     *                               }
     *                           }
     *                       }
     */
    @GetMapping("/getArticleById/{aId}/{userId}")
    public Result getArticleById(@PathVariable String aId,@PathVariable String userId){
        return articleService.getArticleById(aId,userId);
    }


}
