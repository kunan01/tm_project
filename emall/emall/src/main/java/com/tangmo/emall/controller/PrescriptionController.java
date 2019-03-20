package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.Prescription;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("处方模块")
@RestController
@RequestMapping("/web/prescription")
public class PrescriptionController extends BizBaseController {

    /**
     * @api {GET} /prescription/getPrescriptionKVList 获取处方定义信息
     * @apiGroup Prescription
     * @apiVersion 0.0.1
     * @apiDescription 获取处方定义信息
     * @apiParamExample {json} 请求样例：
     *      /prescription/getPrescriptionKVList
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              pkName:"key说明",
     *                              state:"0处方使用信息 1处方度数信息"
     *                              valueList{
     *                                  pvDetailed:"标题/度数的取值范围（范围用_表示 逗号后面表示差值）"
     *                                  pvIntroduce:"介绍/插入的其他选择值"
     *                                  pvPrice:"价格"
     *                                  stringList:"select标签里的数据"
     *                              }
     *                           }
     *                       }
     */
    @ApiOperation(value="获取处方定义信息",notes="获取处方定义信息")
    @GetMapping("/getPrescriptionKVList")
    public Result getPrescriptionKVList() {
        return prescriptionService.getPrescriptionKVList();
    }

    /**
     * @api {GET} /prescription/addPrescriptionByUser 添加用户处方信息
     * @apiGroup Prescription
     * @apiVersion 0.0.1
     * @apiDescription 添加用户处方信息
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      prescriptionName:"处方名称",
     *                      rightSphere:"镜片度数",
     *                      rightCylinder:"散光度数",
     *                      rightAxis:"散光轴度"
     *                      rightAdd:"需要增加的度数"
     *                      rightPd:"远用瞳距"
     *                      leftSphere:"镜片度数"
     *                      leftCylinder:"散光度数"
     *                      leftAxis:"散光轴度"
     *                      leftAdd:"需要增加的度数"
     *                      leftPd:"远用瞳距"
     *                      nearPd:"近用瞳距"
     *                      userId:"用户id"
     *                      comment:"附加信息"
     *                  }
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"添加成功返回处方id"}
     *                       }
     */
    @ApiOperation(value="添加用户处方信息",notes="添加用户处方信息")
    @UserLoginToken
    @PostMapping("/addPrescriptionByUser")
    public Result addPrescriptionByUser(@RequestBody Prescription prescription) {
        return prescriptionService.addPrescriptionByUser(prescription);
    }

    /**
     * @api {GET} /prescription/deletePrescription 删除用户处方信息
     * @apiGroup Prescription
     * @apiVersion 0.0.1
     * @apiDescription 删除用户处方信息
     * @apiParam {Integer} prescriptionId 处方id
     * @apiParamExample {json} 请求样例：
     *      /prescription/deletePrescription?prescriptionId=1
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"删除成功"}
     *                       }
     */
    @ApiOperation(value="删除用户处方信息",notes="删除用户处方信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="prescriptionId",value="处方id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/deletePrescription")
    public Result deletePrescription(Integer prescriptionId) {
        return prescriptionService.deletePrescription(prescriptionId);
    }

    /**
     * @api {GET} /prescription/getPrescriptionUserList 获取用户处方信息
     * @apiGroup Prescription
     * @apiVersion 0.0.1
     * @apiDescription 获取用户处方信息
     * @apiParam {Integer} userId 用户id
     * @apiParamExample {json} 请求样例：
     *      /prescription/getPrescriptionUserList?userId=1
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              prescriptionId:"处方id"
     *                              prescriptionName:"处方名称",
     *                              rightSphere:"镜片度数",
     *                              rightCylinder:"散光度数",
     *                              rightAxis:"散光轴度",
     *                              rightAdd:"需要增加的度数",
     *                              rightPd:"远用瞳距",
     *                              leftSphere:"镜片度数",
     *                              leftCylinder:"散光度数",
     *                              leftAxis:"散光轴度",
     *                              leftAdd:"需要增加的度数",
     *                              leftPd:"远用瞳距",
     *                              nearPd:"近用瞳距",
     *                              userId:"用户id",
     *                              comment:"附加信息",
     *                              createdTime:"创建时间",
     *                           }
     *                       }
     */
    @ApiOperation(value="获取用户处方信息",notes="获取用户处方信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/getPrescriptionUserList")
    public Result getPrescriptionUserList(Integer userId) {
        return prescriptionService.getPrescriptionUserList(userId);
    }
}
