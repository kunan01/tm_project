package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.Help;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("帮助中心模块")
@RestController
@RequestMapping("/system/help")
public class HelpController extends BizBaseController {

    /**
     * @api {GET} /help/getSysHelpList 查询帮助信息
     * @apiGroup Help
     * @apiVersion 0.0.1
     * @apiDescription 查询帮助信息
     * @apiParam {Integer} level   查询级别 （1，2）
     * @apiParam {Integer} isPage   是否分页 0全部 1分页
     * @apiParam {Integer} parentId  父级id（查询级别等于2时必填）
     * @apiParam {Integer} pageNo   页码
     * @apiParam {Integer} pageSize   条数
     * @apiParamExample {json} 请求样例:
     *      /help/getSysHelpList?pageNo=1&pageSize=10
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data":{
     *                              helpId:"帮助信息主键id",
     *                              helpTitle:"类目",
     *                              helpInstructions:"说明",
     *                              level:"级别",
     *                              parentId:"父级id",
     *                              createdTime:"创建时间",
     *                           }
     *                      }
     */
    @ApiOperation(value="查询帮助信息",notes="查询帮助信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="level",value="查询级别 （1，2）",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="isPage",value="是否分页 0全部 1分页",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="parentId",value="父级id（查询级别等于2时必填）",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=false,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getSysHelpList")
    public Result getSysHelpList(Integer level,Integer isPage,Integer parentId,Integer pageNo, Integer pageSize) {
        return helpService.getSysHelpList(level,isPage,parentId,pageNo,pageSize);
    }

    /**
     * @api {POST} /help/getSysHelpList 添加帮助信息
     * @apiGroup Help
     * @apiVersion 0.0.1
     * @apiDescription 添加帮助信息
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          helpTitle:"类目",  (必填)
     *                          helpInstructions:"说明", (必填)
     *                          level:"级别(1,2)", (必填)
     *                          parentId:"父级id(如果级别为2，则父级id必传)", (选填)
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data":{"添加成功"}
     *                      }
     */
    @ApiOperation(value="添加帮助信息",notes="添加帮助信息")
    @UserLoginToken
    @PostMapping(value = "/addHelp")
    public Result addHelp(@RequestBody Help help) {
        return helpService.addHelp(help);
    }

    /**
     * @api {POST} /help/updateSysHelpList 修改帮助信息
     * @apiGroup Help
     * @apiVersion 0.0.1
     * @apiDescription 修改帮助信息
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          helpId:"帮助信息id"  （必填）
     *                          helpTitle:"类目",  (选填)
     *                          helpInstructions:"说明", (选填)
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data":{"修改成功"}
     *                      }
     */
    @ApiOperation(value="修改帮助信息",notes="修改帮助信息")
    @UserLoginToken
    @PostMapping(value = "/updateSysHelpList")
    public Result updateSysHelpList(@RequestBody Help help) {
        return helpService.updateSysHelpList(help);
    }

    /**
     * @api {GET} /help/deleteSysHelpList 删除帮助信息
     * @apiGroup Help
     * @apiVersion 0.0.1
     * @apiDescription 删除帮助信息
     * @apiParam {Integer} helpId 帮助信息id
     * @apiParamExample {json} 请求样例:
     *          /help/deleteSysHelpList?helpId=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data":{"删除成功"}
     *                      }
     */
    @ApiOperation(value="删除帮助信息",notes="删除帮助信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="helpId",value="帮助信息id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/deleteSysHelpList")
    public Result deleteSysHelpList(Integer helpId) {
        return helpService.deleteSysHelpList(helpId);
    }

    /**
     * @api {POST} /help/batchDeleteSysHelpList 批量删除帮助信息
     * @apiGroup Help
     * @apiVersion 0.0.1
     * @apiDescription 批量删除帮助信息
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          helpIdList:[数组：帮助信息id]
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data":{"删除成功"}
     *                      }
     */
    @ApiOperation(value="批量删除帮助信息",notes="批量删除帮助信息")
    @UserLoginToken
    @PostMapping(value = "/batchDeleteSysHelpList")
    public Result batchDeleteSysHelpList(@RequestBody Help help) {
        return helpService.batchDeleteSysHelpList(help);
    }
}
