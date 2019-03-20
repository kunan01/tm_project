package com.tangmo.yiliao.controller.appController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.Dialogue;
import com.tangmo.yiliao.entity.Message;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description 消息/打赏
 */
@Api("app消息/打赏")
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {

    /**
     * @api {GET} /message/getMessageTitle/{roleId}/{userId} 获取消息标题
     * @apiGroup Message
     * @apiVersion 0.0.1
     * @apiDescription 获取消息标题
     * @apiParam {String} roleId 身份标识
     * @apiParamExample {json} 请求样例：
     *  /message/getMessageTitle/{roleId}
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              "system":"系统消息标题",
     *                              "reply":"回复消息标题",
     *                              "exceptional":"打赏消息标题",
     *                              "remind":"提醒消息标题",
     *                              "advisory":"咨询消息标题"。
     *                           }
     *                       }
     */
    @GetMapping("/getMessageTitle/{roleId}/{userId}")
    public Result getMessageTitle(@PathVariable String roleId,@PathVariable String userId){
        return messageService.getMessageTitle(roleId,userId);
    }

    /**
     * @api {GET} /message/getMessageState/{roleId}/{userId} 获取消息读取状态
     * @apiGroup Message
     * @apiVersion 0.0.1
     * @apiDescription 获取消息读取状态
     * @apiParam {String} roleId 身份标识
     * @apiParam {String} userId 用户id
     * @apiParamExample {json} 请求样例：
     *  /message/getMessageState/{roleId}/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              "systemMessage":"系统消息读取状态",      0未读 1已读
     *                              "replyMessage":"回复消息读取状态",       0未读 1已读
     *                              "exceptionalMessage":"打赏消息读取状态"  0未读 1已读
     *                              "remindMessage":"提醒消息读取状态"       0未读 1已读
     *                              "advisoryMessages":"咨询消息读取状态"    0未读 1已读
     *                           }
     *                       }
     */
    @GetMapping("/getMessageState/{roleId}/{userId}")
    public Result getMessageState(@PathVariable String roleId,@PathVariable String userId){
        return messageService.getMessageState(roleId,userId);
    }

    /**
     * @api {GET} /message/getMessageAllByType/{type}/{userId}/{start}/{end} 获取消息集合(分页)
     * @apiGroup Message
     * @apiVersion 0.0.1
     * @apiDescription 获取消息读取状态
     * @apiParam {Byte} type   0系统消息  1回复消息  2打赏消息
     * @apiParam {String} userId 用户id
     * @apiParamExample {json} 请求样例：
     *      /message/getMessageState/{type}/{userId}/{start}/{end}
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              "title":"标题",
     *                              "miContent":"内容",
     *                              "commId":"评论id",
     *                              "createTime":"创建时间"
     *                           }
     *                       }
     */
    @GetMapping("/getMessageAllByType/{type}/{userId}/{start}/{end}")
    public Result getMessageAllByType(@PathVariable Byte type,@PathVariable String userId,@PathVariable Integer start,@PathVariable Integer end){
        return messageService.getMessageAllByType(type,userId,start,end);
    }

    /**
     * @api {POST} /message/IntegralExceptional 积分打赏
     * @apiGroup Message
     * @apiVersion 0.0.1
     * @apiDescription 获取消息读取状态
     * @apiParamExample {json} 请求样例：
     *                        {
     *                            "miCategory":"0评论  1视频",
     *                            "miId":"医生id或者视频id或问答id",
     *                            "title":"文章标题或者视频标题",
     *                            "userId":"当前用户id",
     *                            "count":"打赏数量"
     *                        }
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @PostMapping("/IntegralExceptional")
    public Result IntegralExceptional(@RequestBody Message message){
        return messageService.IntegralExceptional(message);
    }

    /**
     * @api {POST} /message/IntegralExceptionalDoctor 积分打赏医生
     * @apiGroup Message
     * @apiVersion 0.0.1
     * @apiDescription 积分打赏医生
     * @apiParamExample {json} 请求样例：
     *                        {
     *                            "miId":"医生id",
     *                            "userId":"当前用户id",
     *                            "count":"打赏数量"
     *                        }
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @PostMapping("/IntegralExceptionalDoctor")
    public Result IntegralExceptionalDoctor(@RequestBody Message message){
        return messageService.IntegralExceptionalDoctor(message);
    }

    /**
     * @api {POST} /message/EnterTheDialogue 用户进入对话调用接口
     * @apiGroup Message
     * @apiVersion 0.0.1
     * @apiDescription 用户进入对话调用接口
     * @apiParamExample {json} 请求样例：
     *                        {
     *                            originatorId:"用户id",
     *                            peopleId:"医生id",
     *                            type:"当前用户身份。0用户，1医生"
     *                        }
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @PostMapping("/EnterTheDialogue")
    public Result EnterTheDialogue(@RequestBody SelectUser selectUser){
        return messageService.EnterTheDialogue(selectUser);
    }

    /**
     * @api {POST} /message/getUserDia 获取对话信息
     * @apiGroup Message
     * @apiVersion 0.0.1
     * @apiDescription 获取对话信息
     * @apiParamExample {json} 请求样例：
     *                        {
     *                          originatorId:"用户id",
     *                          peopleId:"医生id",
     *                          start:"页数",
     *                          end:"条数"
     *                        }
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              dlId:"主键",
     *                              originatorId:"用户id",
     *                              peopleId:"医生id"
     *                              dType:"类型： 0文字 1语音",
     *                              dContent:"内容",
     *                              createTime:"发表时间",
     *                              createUserId:"发表人id",
     *                              userImgUrl:"发表人头像",
     *                           }
     *                       }
     */
    @PostMapping("/getUserDia")
    public Result getUserDia(@RequestBody SelectUser selectUser){
        return messageService.getUserDia(selectUser);
    }

    /**
     * @api {POST} /message/addUserDia 发表对话信息
     * @apiGroup Message
     * @apiVersion 0.0.1
     * @apiDescription 发表对话信息
     * @apiParamExample {json} 请求样例：
     *                        {
     *                          originatorId:"用户id",
     *                          peopleId:"医生id",
     *                          dType:"类型： 0文字 1语音",
     *                          dContent:"内容",
     *                          createUserId:"发表人id",
     *                        }
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @PostMapping("/addUserDia")
    public Result addUserDia(@RequestBody Dialogue dialogue){
        return messageService.addUserDia(dialogue);
    }

    /**
     * @api {POST} /message/getConsultingAll 获取咨询消息列表
     * @apiGroup Message
     * @apiVersion 0.0.1
     * @apiDescription 获取咨询消息列表
     * @apiParamExample {json} 请求样例：
     *                        {
     *                          originatorId:"当前用户id",
     *                          type:"当前用户身份。0用户，1医生",
     *                          start:"页数",
     *                          end:"条数"
     *                        }
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               peopleId:"医生或用户id"
     *                               dContent:"内容",
     *                               dType:"类型：0文字 1语音",
     *                               userImgUrl:"头像",
     *                               name:"姓名"
     *                           }
     *                       }
     */
    @PostMapping("/getConsultingAll")
    public Result getConsultingAll(@RequestBody SelectUser selectUser){
        return messageService.getConsultingAll(selectUser);
    }



}
