package com.tangmo.yiliao.controller.appController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description 视频
 */
@Api("app视频相关")
@RestController
@RequestMapping("/video")
public class VideoController extends BaseController {

    /**
     * @api {GET} /video/shufflingVideo 获取首页轮播视频
     * @apiGroup Video
     * @apiVersion 0.0.1
     * @apiDescription 获取首页轮播视频
     * @apiParamExample {json} 请求样例：
     *  /video/shufflingVideo
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               dvId: 视频主键id，
     *                               dvImgId: 视频图片路径
     *                           }
     *                       }
     */
    @GetMapping("/shufflingVideo")
    public Result getShufflingVideo(){
        return videoService.getShufflingVideo();
    }

    /**
     * @api {GET} /video/homePageDisplay 获取首页展示视频
     * @apiGroup Video
     * @apiVersion 0.0.1
     * @apiDescription 获取首页轮播视频
     * @apiParamExample {json} 请求样例：
     *      /video/homePageDisplay
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                knowledge{     //知识课堂 16条
     *                                   dvId: 视频主键id,
     *                                   dvImgId:"视频展示图片id",
     *                                   dvTitle:"视频标题",
     *                                   dvIntegral:"积分",
     *                                   visitNumber:"游览次数",
     *                                   commCount:"评论数"
     *                                }
     *                                shortVideo{     //短视频 6条
     *                                   dvId: 视频主键id,
     *                                   dvImgId:"视频展示图片id",
     *                                   dvTitle:"视频标题",
     *                                   dvIntegral:"积分",
     *                                   visitNumber:"游览次数",
     *                                   commCount:"评论数"
     *                                }
     *                           }
     *                       }
     */
    @GetMapping("/homePageDisplay")
    public Result homePageDisplay(){
        return videoService.homePageDisplay();
    }

    /**
     * @api {GET} /video/getDepartmentAllVideoTop4 获取所有科室下排名前四视频
     * @apiGroup Video
     * @apiVersion 0.0.1
     * @apiDescription 获取所有科室下排名前四视频
     * @apiParamExample {json} 请求样例：
     *  /video/getDepartmentAllVideoTop4
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               [dtId:"科室id",dtName:"科室名称"]:{
     *                                      dvId: 视频主键id,
     *                                      dvImgId:"视频展示图片id",
     *                                      dvTitle:"视频标题",
     *                                      dvIntegral:"积分"
     *                               }
     *                           }
     *                       }
     */
    @GetMapping("/getDepartmentAllVideoTop4")
    public Result getDepartmentAllVideoTop4(){
        return videoService.getDepartmentAllVideoTop4();
    }

    /**
     * @api {GET} /video/getDepartmentByIdVideoAll/{dtId}/{start}/{end}  根据科室id获取科室下所有视频(分页)
     * @apiGroup Video
     * @apiVersion 0.0.1
     * @apiDescription 根据科室id获取科室下所有视频(分页)
     * @apiParam {String} dtId  科室id
     * @apiParam {int} start  页数
     * @apiParam {int} end    条数
     * @apiParamExample {json} 请求样例：
     *  /video/getDepartmentByIdVideoAll/{dtId}/{start}/{end}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                dvId: 视频主键id,
     *                                dvImgId:"视频展示图片id",
     *                                dvTitle:"视频标题",
     *                                dvIntegral:"积分",
     *                                visitNumber:"游览次数",
     *                                commCount:"评论数"
     *                           }
     *                       }
     */
    @GetMapping("/getDepartmentByIdVideoAll/{dtId}/{start}/{end}")
    public Result getDepartmentByIdVideoAll(@PathVariable String dtId,@PathVariable Integer start,@PathVariable Integer end){
        if(dtId == null || start == 0){
            return ResultUtil.paramError();
        }
        return videoService.getDepartmentByIdVideoAll(dtId,start,end);
    }

    /**
     * @api {Post} /video/getDepartmentBySearch/{dtId}/{start}/{end}  快搜视频(分页)
     * @apiGroup Video
     * @apiVersion 0.0.1
     * @apiDescription 根据科室id获取科室下所有视频(分页)
     * @apiParamExample {json} 请求样例：
     *                      {
     *                           "condName":"疾病名称",
     *                           "start":"页数",
     *                           "end":"条数"
     *                      }
     * @apiSuccess (success) {Post} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                dvId: 视频主键id,
     *                                dvImgId:"视频展示图片id",
     *                                dvTitle:"视频标题",
     *                                dvIntegral:"积分",
     *                                visitNumber:"游览次数",
     *                                commCount:"评论数"
     *                           }
     *                       }
     */
    @PostMapping("/getDepartmentBySearch")
    public Result getDepartmentBySearch(@RequestBody SelectUser selectUser){
        if(selectUser == null){
            return ResultUtil.paramError();
        }
        return videoService.getDepartmentBySearch(selectUser);
    }

    /**
     * @api {GET} /video/getVideoList/{category}/{start}/{end}  通过视频分类获取视频列表(分页)
     * @apiGroup Video
     * @apiVersion 0.0.1
     * @apiDescription 根据科室id获取科室下所有视频(分页)
     * @apiParam {Byte} category  0短视频  1知识课堂
     * @apiParam {int} start  页数
     * @apiParam {int} end    条数
     * @apiParamExample {json} 请求样例：
     *     /video/getVideoList/{category}/{start}/{end}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                dvId: 视频主键id,
     *                                dvImgId:"视频展示图片id",
     *                                dvTitle:"视频标题",
     *                                dvIntegral:"积分",
     *                                visitNumber:"游览次数",
     *                                commCount:"评论数"
     *                           }
     *                       }
     */
    @GetMapping("/getVideoList/{category}/{start}/{end}")
    public Result getVideoList(@PathVariable Byte category,@PathVariable Integer start,@PathVariable Integer end){
        if(category == null || start == 0){
            return ResultUtil.paramError();
        }
        return videoService.getVideoList(category,start,end);
    }

    /**
     * @api {GET} /video/getVideoDDById/{dvId}/{userId} 获取视频详情
     * @apiGroup Video
     * @apiVersion 0.0.1
     * @apiDescription 获取视频详情
     * @apiParam {String} dvId  视频id
     * @apiParam {String} userId 用户id
     * @apiParamExample {json} 请求样例：
     *  /video/getVideoDDById/{dvId}/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                "video":{
     *                                      dvId: 视频主键id,
     *                                      userId:"用户id"
     *                                      userImgId:"头像id"
     *                                      name:"姓名",
     *                                      ddHospital:"医院",
     *                                      ddPosition:"职位",
     *                                      ddField:"科室",
     *                                      dvVideo:"视频id",
     *                                      dvTitle:"视频标题",
     *                                      dvContent:"视频介绍",
     *                                      dvIntegral:"积分",
     *                                      visitNumber:"游览次数"
     *                                      createTime:"发布时间"
     *                                }
     *                                "comments":{    //评论
     *                                       cId:"评论id",
     *                                       userName:"评论人姓名",
     *                                       userImgUrl:"评论人头像",
     *                                       cContent:"评论内容"
     *                                       cAttribute:"评论属性 0文字 1语音"
     *                                       cStatus:"评论类别 0评论 1回复"
     *                                       replyCount:"总回复数量"
     *                                       roleId:"评论人身份  DOCTOR:"医生"，MEMBER:"普通用户" ",
     *                                       createTime:"评论时间"
     *                                       commentsList:{   //回复
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
     *                               "recommended":{
     *                                     dvId: 视频主键id,
     *                                     dvTitle:"视频标题",
     *                                     dvIntegral:"积分",
     *                                     dvImgId:"视频展示图片id"
     *                               }
     *                           }
     *                       }
     */
    @GetMapping("/getVideoDDById/{dvId}/{userId}")
    public Result getVideoDDById(@PathVariable String dvId,@PathVariable String userId){
        if(dvId == null || userId == null){
            return ResultUtil.paramError();
        }
        return videoService.getVideoDDById(dvId,userId);
    }

    /**
     * @api {GET} /video/getVideoComm/{dvId}/{type}/{start}/{end} 获取视频评论
     * @apiGroup Video
     * @apiVersion 0.0.1
     * @apiDescription 获取视频详情
     * @apiParam {String} dvId  视频id或文章id
     * @apiParam {Integer} type 0文章 1视频
     * @apiParamExample {json} 请求样例：
     *  /video/getVideoComm/{dvId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                           }
     *                       }
     */
    @GetMapping("/getVideoComm/{dvId}/{type}/{start}/{end}")
    public Result getVideoComm(@PathVariable String dvId,@PathVariable Integer type,@PathVariable Integer start,@PathVariable Integer end){
        if(dvId == null){
            return ResultUtil.paramError();
        }
        return videoService.getVideoComm(dvId,type,start,end);
    }

    /**
     * @api {GET} /video/getVideoCommH/{cId}/{type}/{start}/{end} 通过评论id获取视频回复
     * @apiGroup Video
     * @apiVersion 0.0.1
     * @apiDescription 通过评论id获取视频回复
     * @apiParam {String} cId  评论id
     * @apiParam {Integer} type 0文章 1视频
     * @apiParamExample {json} 请求样例：
     *  /video/getVideoComm/{dvId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:"参数有误"
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                           }
     *                       }
     */
    @GetMapping("/getVideoCommH/{cId}/{type}/{start}/{end}")
    public Result getVideoCommH(@PathVariable String cId,@PathVariable Integer type,@PathVariable Integer start,@PathVariable Integer end){
        if(cId == null){
            return ResultUtil.paramError();
        }
        return videoService.getVideoCommH(cId,type,start,end);
    }
}
