package com.tangmo.yiliao.controller.appController;


import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.Comments;
import com.tangmo.yiliao.utility.code.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-9-12
 * @description 评论
 */
@Api("app评论")
@RestController
@RequestMapping("/comments")
public class CommentsController extends BaseController {

    /**
     * @api {POST} /comments/addComments 添加评论
     * @apiGroup Comments
     * @apiVersion 0.0.1
     * @apiDescription 添加评论
     * @apiParam {Comments} comments 评论对象
     * @apiParamExample {json} 请求样例：
     *                 {
     *                      cType:"评论类型 0文章 1视频",
     *                      ArticlesOrVideoId:"文章id或视频id",
     *                      userId:"评论人id",
     *                      cContent:"评论内容",
     *                      cAttribute:"评论属性 0文字 1语音",
     *                      cStatus:"评论类别 0评论 1回复",
     *                      replyId:"回复id(评论id)",(可以为空)
     *                 }
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @PostMapping("/addComments")
    public Result addComments(@RequestBody Comments comments){
        return commentsService.addComments(comments);
    }

    /**
     * @api {GET} /comments/getCommentsDById/{cId} 通过评论id查询所有回复
     * @apiGroup Comments
     * @apiVersion 0.0.1
     * @apiDescription 通过评论id查询所有回复
     * @apiParam {String} cId 评论id
     * @apiParamExample {json} 请求样例：
     *       /comments/getCommentsDById/{cId}
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                cId:"评论id",
     *                                userName:"评论人姓名",
     *                                userImgUrl:"评论人头像",
     *                                cContent:"评论内容",
     *                                cAttribute:"评论属性 0文字 1语音",
     *                                cStatus:"评论类别 0评论 1回复",
     *                                replyCount:"总回复数量",
     *                                roleId:"评论人身份  DOCTOR:"医生"，MEMBER:"普通用户" ",
     *                                createTime:"评论时间"
     *                                commentsList:{   //回复
     *                                       cId:"回复id",
     *                                       userName:"评论人姓名",
     *                                       userImgUrl:"评论人头像",
     *                                       cContent:"回复内容",
     *                                       cAttribute:"回复属性 0文字 1语音",
     *                                       cStatus:"回复类别 0评论 1回复",
     *                                       roleId:"评论人身份  DOCTOR:"医生"，MEMBER:"普通用户" ",
     *                                       createTime:"回复时间"
     *                                }
     *                           }
     *                       }
     */
    @GetMapping("/getCommentsDById/{cId}")
    public Result getCommentsDById(@PathVariable String cId){
        return commentsService.getCommentsDById(cId);
    }


    /**
     * @api {GET} /comments/getCommentsALLWById/{id}/{type} 通过视频或者文章id查询所有评论以及评论下3条回复
     * @apiGroup Comments
     * @apiVersion 0.0.1
     * @apiDescription 通过视频或者文章id查询所有评论以及评论下3条回复
     * @apiParam {String} id 文章id或视频id
     * @apiParam {Byte} type 0文章 1视频
     * @apiParamExample {json} 请求样例：
     *       /comments/getCommentsALLWById/{cId}
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                cId:"评论id",
     *                                userName:"评论人姓名",
     *                                userImgUrl:"评论人头像",
     *                                cContent:"评论内容"
     *                                cAttribute:"评论属性 0文字 1语音"
     *                                cStatus:"评论类别 0评论 1回复"
     *                                replyCount:"总回复数量"
     *                                roleId:"评论人身份  DOCTOR:"医生"，MEMBER:"普通用户" ",
     *                                createTime:"评论时间"
     *                                commentsList:{   //回复
     *                                       cId:"回复id",
     *                                       userName:"评论人姓名",
     *                                       userImgUrl:"评论人头像",
     *                                       cContent:"回复内容",
     *                                       cAttribute:"回复属性 0文字 1语音",
     *                                       cStatus:"回复类别 0评论 1回复",
     *                                       roleId:"评论人身份  DOCTOR:"医生"，MEMBER:"普通用户" ",
     *                                       createTime:"回复时间"
     *                                }
     *                           }
     *                       }
     */
    @GetMapping("/getCommentsALLWById/{id}/{type}")
    public Result getCommentsALLWById(@PathVariable String id,@PathVariable Integer type){
        return commentsService.getCommentsALLWById(id,type);
    }

}
