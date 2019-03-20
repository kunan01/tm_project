package com.tangmo.yiliao.controller.appController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description 科室
 */
@Api("app科室")
@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController {

    /**
     * @api {GET} /department/partOfDepartment 获取首页部分科室
     * @apiGroup Department
     * @apiVersion 0.0.1
     * @apiDescription 获取首页部分科室
     * @apiParamExample {json} 请求样例：
     *  /department/partOfDepartment
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               dtId: 科室id，
     *                               dtName: 科室名称,
     *                               dtImgId:"科室图片",
     *                               sortingId:"排序"
     *                           }
     *                       }
     */
    @GetMapping("/partOfDepartment")
    public Result getPartOfDepartment(){
        return departmentService.getPartOfDepartment();
    }

    /**
     * @api {GET} /department/getDepartmentAll 获取全部科室
     * @apiGroup Department
     * @apiVersion 0.0.1
     * @apiDescription 获取全部科室
     * @apiParamExample {json} 请求样例：
     *  /department/getDepartmentAll
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               dtId: 科室id，
     *                               dtName: 科室名称,
     *                               dtImgId:"科室图片"
     *                           }
     *                       }
     */
    @GetMapping("/getDepartmentAll")
    public Result getDepartmentAll(){
        return departmentService.getDepartmentAll();
    }

    /**
     * @api {GET} /department/getDepartmentDetails/{dtId} 科室详情
     * @apiGroup Department
     * @apiVersion 0.0.1
     * @apiDescription 科室详情
     * @apiParam {String} dtId  科室id
     * @apiParamExample {json} 请求样例：
     *  /department/getDepartmentAll/
     * @apiSuccess (success) {GET} code  0:请求成功; 10005:参数有误
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                 "video":{
     *                                      dvId:"视频主键id",
     *                                      dvImgId:"视频图片id",
     *                                      dvTitle:"视频标题",
     *                                      dvIntegral:"积分"
     *                                 },
     *                                 "doctor":{
     *                                      ddId:"医生id",
     *                                      name:"医生姓名",
     *                                      ddField:"医生科室"
     *                                      ddPosition:"医院职务",
     *                                      userImgUrl:"医生头像"
     *                                 },
     *                                 "article":{
     *                                      saId:"文章id",
     *                                      saTitle:"文章标题",
     *                                      visitNumber:"游览次数",
     *                                      createTime:"发布时间"
     *                                 }
     *                           }
     *                       }
     */
    @GetMapping("/getDepartmentDetails/{dtId}")
    public Result getDepartmentDetails(@PathVariable String dtId){
        if(dtId == null){
            return ResultUtil.paramError();
        }
        System.out.println(dtId);
        return departmentService.getDepartmentDetails(dtId);
    }
}
