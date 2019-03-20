package com.tangmo.yiliao.controller.webController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.DoctorVideo;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.entity.SystemTheArticle;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-9-5
 * @description 后台文章和视频
 */
@Api("system文章和视频")
@RestController
@RequestMapping("/articleOrVideo")
public class ArticleOrVideoController extends BaseController {

    /**
     * @api {GET} /articleOrVideo/getArticleAllByApp/{start}/{end} 获取所有文章(分页)
     * @apiGroup Article
     * @apiVersion 0.0.1
     * @apiDescription 获取所有文章(分页)
     * @apiParamExample {json} 请求样例：
     *  /articleOrVideo/getArticleAll/{start}/{end}
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               saId: 文章id，
     *                               saTitle: 文章标题,
     *                               saImgId: 文章导图,
     *                               saSource:文章来源,
     *                               visitNumber:"游览次数",
     *                               createTime:"发布时间"
     *                           }
     *                       }
     */
    @GetMapping("/getArticleAllByApp/{start}/{end}")
    public Result getArticleAllByApp(@PathVariable Integer start,@PathVariable Integer end){
        return articleOrVideoService.getArticleAllByApp(start,end);
    }

    //获取所有文章(分页)
    @PostMapping("/getArticleAll")
    public Result getArticleAll(@RequestBody SelectUser selectUser){
        return articleOrVideoService.getArticleAll(selectUser);
    }

    //后台删除文章信息
    @GetMapping("/delArticleById/{saId}/{userId}")
    public Result delArticleById(@PathVariable String saId,@PathVariable String userId){
        return articleOrVideoService.delArticleById(saId,userId);
    }

    //修改文章信息
    @PostMapping("/upArticleById")
    public Result upArticleById(@RequestBody SystemTheArticle systemTheArticle){
        return articleOrVideoService.upArticleById(systemTheArticle);
    }

    /**
     * @api {POST} /articleOrVideo/addArticle 添加文章信息
     * @apiGroup Article
     * @apiVersion 0.0.1
     * @apiDescription 添加文章信息
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          dtId:"问答领域",
     *                          saTitle:"问答标题",
     *                          saContent:"问答内容",
     *                          userId:"用户ID",
     *                          saImgId:"问答导图"
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                           }
     *                       }
     */
    @PostMapping("/addArticle")
    public Result addArticle(@RequestBody SystemTheArticle systemTheArticle){
        return articleOrVideoService.addArticle(systemTheArticle);
    }

    //查询文章数量
    @PostMapping("/getArticleCount")
    public Result getArticleCount(@RequestBody SelectUser selectUser){
        return articleOrVideoService.getArticleCount(selectUser);
    }

    //获取所有视频(分页)
    @PostMapping("/getVideoAll")
    public Result getVideoAll(@RequestBody SelectUser selectUser){
        return articleOrVideoService.getVideoAll(selectUser);
    }

    //获取视频详情
    @GetMapping("/getVideoDDById/{dvId}")
    public Result getVideoDDById(@PathVariable String dvId){
        if(dvId == null){
            return ResultUtil.paramError();
        }
        return articleOrVideoService.getVideoDDById(dvId);
    }

    //获取所有视频数量
    @PostMapping("/getVideoCountAll")
    public Result getVideoCountAll(@RequestBody SelectUser selectUser){
        return articleOrVideoService.getVideoCountAll(selectUser);
    }

    //删除视频
    @GetMapping("/delVideoById/{dvId}/{userId}")
    public Result delVideoById(@PathVariable String dvId,@PathVariable String userId){
        return articleOrVideoService.delVideoById(dvId,userId);
    }

    /**
     * @api {POST} /articleOrVideo/addVideo 添加视频信息
     * @apiGroup Article
     * @apiVersion 0.0.1
     * @apiDescription 添加视频信息
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",
     *                          dvVideo:"视频id",
     *                          dvImgId:"视频展示图片",
     *                          dvTitle:"视频标题",
     *                          dvContent:"视频内容",
     *                          dtId:"视频领域",
     *                          category:"视频分类 0短视频 1知识课堂"
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody DoctorVideo doctorVideo){
        return articleOrVideoService.addVideo(doctorVideo);
    }

    //修改视频
    @PostMapping("/updVideo")
    public Result updVideo(@RequestBody DoctorVideo doctorVideo){
        return articleOrVideoService.updVideo(doctorVideo);
    }

    //后台获取视频所有评论
    @GetMapping("/getVideoComments/{dvId}/{type}")
    public Result getVideoComments(@PathVariable String dvId,@PathVariable Integer type){
        return articleOrVideoService.getVideoComments(dvId,type);
    }

    //通过评论id删除评论
    @GetMapping("/delCommentsById/{cId}")
    public Result delCommentsById(@PathVariable String cId){
        return articleOrVideoService.delCommentsById(cId);
    }

    //后台获取验证码异常账号
    @PostMapping("/getMesCode")
    public Result getMesCode(@RequestBody SelectUser selectUser){
        return articleService.getMesCode(selectUser);
    }

    //后台禁止或解除用户验证
    @GetMapping("/banMesCod/{type}/{phone}")
    public Result banMesCod(@PathVariable Integer type,@PathVariable String phone){
        return articleService.banMesCod(type,phone);
    }

}
