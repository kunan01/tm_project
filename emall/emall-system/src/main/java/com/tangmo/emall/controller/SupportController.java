package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.CustomerService;
import com.tangmo.emall.entity.MessageUs;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("客户支持模块")
@RestController
@RequestMapping("/system/support")
public class SupportController extends BizBaseController {

    /**
     * @api {GET} /support/getUsersMessageList 获取客户反馈信息列表
     * @apiGroup Support
     * @apiVersion 0.0.1
     * @apiDescription 获取客户反馈信息列表
     * @apiParam {Integer} state  查询状态 (0查询全部,1查询未处理的反馈信息,2查询已处理的反馈信息)
     * @apiParam {Integer} pageNo  页码
     * @apiParam {Integer} pageSize  分页条数
     * @apiParamExample {json} 请求样例:
     *      /support/getUsersMessageList?state=1&pageNo=1&pageSize=2
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              mId:"主键id",
     *                              userId:"用户ID",
     *                              mTopic:"主题",
     *                              mEmail:"邮箱地址",
     *                              mContent:"内容",
     *                              firstName:"名",
     *                              lastName:"性",
     *                              orderNumber:"订单号",
     *                              state:"反馈状态 0未处理  1已处理",
     *                              createdTime:"发布时间"
     *                          }
     *                      }
     */
    @ApiOperation(value="获取客户反馈信息列表",notes="获取客户反馈信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="state",value="查询状态 (0查询全部,1查询未处理的反馈信息,2查询已处理的反馈信息)",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="分页条数",dataType="int",required=false,paramType="query")
    })
//    @UserLoginToken
    @GetMapping(value = "/getUsersMessageList")
    public Result getUsersMessageList(Integer state, Integer pageNo, Integer pageSize) {
        return supportService.getUsersMessageList(state,pageNo,pageSize);
    }

    /**
     * @api {GET} /support/dealWithMessage 处理客户反馈信息
     * @apiGroup Support
     * @apiVersion 0.0.1
     * @apiDescription 处理客户反馈信息
     * @apiParam {Integer} mId 信息主键id
     * @apiParamExample {json} 请求样例:
     *      /support/dealWithMessage?mId=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "处理完成"
     *                      }
     */
    @ApiOperation(value="处理客户反馈信息",notes="处理客户反馈信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mId",value="信息主键id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/dealWithMessage")
    public Result dealWithMessage(Integer mId) {
        return supportService.dealWithMessage(mId);
    }

    /**
     * @api {POST} /support/batchDealWithMessage 批量处理客户反馈信息
     * @apiGroup Support
     * @apiVersion 0.0.1
     * @apiDescription 批量处理客户反馈信息
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          mIdList:[数组:主键id]
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "处理完成"
     *                      }
     */
    @ApiOperation(value="批量处理客户反馈信息",notes="批量处理客户反馈信息")
    @UserLoginToken
    @PostMapping(value = "/batchDealWithMessage")
    public Result batchDealWithMessage(@RequestBody MessageUs messageUs) {
        return supportService.batchDealWithMessage(messageUs);
    }

    /**
     * @api {GET} /support/getCustomerService 获取客服服务信息列表
     * @apiGroup Support
     * @apiVersion 0.0.1
     * @apiDescription 获取客服服务信息列表
     * @apiParamExample {json} 请求样例:
     *      /support/getCustomerService
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                          }
     *                      }
     */
    @ApiOperation(value="获取客服服务信息列表",notes="获取客服服务信息列表")
    @UserLoginToken
    @GetMapping(value = "/getCustomerService")
    public Result getCustomerService() {
        return supportService.getCustomerService();
    }

    /**
     * @api {POST} /support/updateCustomerService 修改客服服务信息
     * @apiGroup Support
     * @apiVersion 0.0.1
     * @apiDescription 修改客服服务信息
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          serviceId:"",
     *                          serviceText:""
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "修改成功"
     *                      }
     */
    @ApiOperation(value="修改客服服务信息",notes="修改客服服务信息")
    @UserLoginToken
    @PostMapping(value = "/updateCustomerService")
    public Result updateCustomerService(@RequestBody CustomerService customerService) {
        return supportService.updateCustomerService(customerService);
    }

}
