package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.ParamType;
import com.tangmo.emall.entity.ParamValue;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("店铺参数模块")
@RestController
@RequestMapping("/system/param")
public class ShopParamController extends BizBaseController {

    /**
     * @api {POST} /param/addShopParamType 增加店铺参数类型
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 增加店铺参数类型
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          typeName:"类型名称",
     *                          shopId:"店铺id"
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "添加成功"
     *                      }
     */
    @ApiOperation(value="增加店铺参数类型",notes="增加店铺参数类型")
    @UserLoginToken
    @PostMapping(value = "/addShopParamType")
    public Result addShopParamType(@RequestBody ParamType paramType) {
        return shopParamService.addShopParamType(paramType);
    }

    /**
     * @api {POST} /param/addShopParamValue 增加店铺参数值
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 增加店铺参数值
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          paramValue:"参数值",
     *                          typeId:"类型id",
     *                          paramImage:"参数图片" （可选）
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "添加成功"
     *                      }
     */
    @ApiOperation(value="增加店铺参数值",notes="增加店铺参数值")
    @UserLoginToken
    @PostMapping(value = "/addShopParamValue")
    public Result addShopParamValue(@RequestBody ParamValue paramValue) {
        return shopParamService.addShopParamValue(paramValue);
    }

    /**
     * @api {POST} /param/updShopParamType 修改店铺类型
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 修改店铺类型
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          typeName:"类型名称",
     *                          typeId:"类型id"
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
    @ApiOperation(value="修改店铺参数类型",notes="修改店铺类型")
    @UserLoginToken
    @PostMapping(value = "/updShopParamType")
    public Result updShopParamType(@RequestBody ParamType paramType) {
        return shopParamService.updShopParamType(paramType);
    }

    /**
     * @api {POST} /param/updShopParamValue 修改店铺参数值
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 修改店铺参数值
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          valueId:"主键id",     (必填)
     *                          paramValue:"参数值",
     *                          typeId:"类型id",
     *                          paramImage:"参数图片", （不能和原图一致）
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "添加成功"
     *                      }
     */
    @ApiOperation(value="修改店铺参数值",notes="修改店铺参数值")
    @UserLoginToken
    @PostMapping(value = "/updShopParamValue")
    public Result updShopParamValue(@RequestBody ParamValue paramValue) {
        return shopParamService.updShopParamValue(paramValue);
    }

    /**
     * @api {GET} /param/delShopParamValue 删除店铺参数值
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 删除店铺参数值
     * @apiParam {Integer} valueId 参数值主键id
     * @apiParamExample {json} 请求样例:
     *         /param/delShopParamValue?valueId=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "添加成功"
     *                      }
     */
    @ApiOperation(value="删除店铺参数值",notes="删除店铺参数值")
    @ApiImplicitParams({
            @ApiImplicitParam(name="valueId",value="参数值主键id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/delShopParamValue")
    public Result delShopParamValue(Integer valueId) {
        return shopParamService.delShopParamValue(valueId);
    }

    /**
     * @api {POST} /param/batchDelShopParamValue 批量删除店铺参数值
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 批量删除店铺参数值
     * @apiParamExample {json} 请求样例:
     *                  {
     *                      valueIdList:[主键数组]
     *                  }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "添加成功"
     *                      }
     */
    @ApiOperation(value="批量删除店铺参数值",notes="批量删除店铺参数值")
    @UserLoginToken
    @PostMapping(value = "/batchDelShopParamValue")
    public Result batchDelShopParamValue(@RequestBody ParamValue paramValue) {
        return shopParamService.batchDelShopParamValue(paramValue);
    }

    /**
     * @api {GET} /param/delShopParamType 删除店铺参数类型
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 删除店铺参数类型（如果当前类型下有value 则删除）
     * @apiParam {Integer} typeId 参数类型主键id
     * @apiParamExample {json} 请求样例:
     *         /param/delShopParamValue?typeId=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "添加成功"
     *                      }
     */
    @ApiOperation(value="删除店铺参数类型",notes="删除店铺参数类型（如果当前类型下有value 则删除）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="typeId",value="参数类型主键id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/delShopParamType")
    public Result delShopParamType(Integer typeId) {
        return shopParamService.delShopParamType(typeId);
    }

    /**
     * @api {POST} /param/batchDelShopParamType 批量删除店铺参数类型
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 批量删除店铺参数类型（如果当前类型下有value 则删除）
     * @apiParamExample {json} 请求样例:
     *                          {
     *                              typeIdList:[主键数组]
     *                          }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "添加成功"
     *                      }
     */
    @ApiOperation(value="批量删除店铺参数类型",notes="批量删除店铺参数类型（如果当前类型下有value 则删除）")
    @UserLoginToken
    @PostMapping(value = "/batchDelShopParamType")
    public Result batchDelShopParamType(@RequestBody ParamType paramType) {
        return shopParamService.batchDelShopParamType(paramType);
    }

    /**
     * @api {GET} /param/getShopParamKeyList 查询店铺参数类型
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 查询店铺参数类型
     * @apiParam {Integer} shopId  店铺id
     * @apiParam {Integer} state  查询状态 1查询全部 2分页查询
     * @apiParam {Integer} pageNo  页码
     * @apiParam {Integer} pageSize  条数
     * @apiParamExample {json} 请求样例:
     *         /param/getShopParamKeyList?shopId=1&state=1&pageNo=1&pageSize=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              typeId:"参数类型id",
     *                              typeName:"参数类型名称",
     *                              shopId:"店铺id",
     *                              createdTime:"添加时间",
     *                          }
     *                      }
     */
    @ApiOperation(value="查询店铺参数类型",notes="查询店铺参数类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name="shopId",value="店铺id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="state",value="查询状态 1查询全部 2分页查询",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=false,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getShopParamKeyList")
    public Result getShopParamKeyList(Integer shopId,Integer state,Integer pageNo,Integer pageSize) {
        return shopParamService.getShopParamList(shopId,state,pageNo,pageSize);
    }

    /**
     * @api {GET} /param/getShopParamValueList 查询店铺参数值
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 查询店铺参数值
     * @apiParam {Integer} typeId  类型id
     * @apiParam {Integer} pageNo  页码
     * @apiParam {Integer} pageSize  条数
     * @apiParamExample {json} 请求样例:
     *         /param/getShopParamKeyList?typeId=1&pageNo=1&pageSize=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                                 valueId:"参数值主键",
     *                                 paramValue:"参数值内容",
     *                                 typeId:"店铺id",
     *                                 createdTime:"添加时间",
     *                                 paramImage:"参数图"
     *                          }
     *                      }
     */
    @ApiOperation(value="查询店铺参数值",notes="查询店铺参数值")
    @ApiImplicitParams({
            @ApiImplicitParam(name="typeId",value="一级id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getShopParamValueList")
    public Result getShopParamValueList(Integer typeId,Integer pageNo,Integer pageSize) {
        return shopParamService.getShopParamValueList(typeId,pageNo,pageSize);
    }

    /**
     * @api {GET} /param/queryShopParamList 查询全部参数
     * @apiGroup Param
     * @apiVersion 0.0.1
     * @apiDescription 查询全部参数
     * @apiParamExample {json} 请求样例:
     *         /param/queryShopParamList
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              typeId:"参数类型id",
     *                              typeName:"参数类型名称",
     *                              shopId:"店铺id",
     *                              createdTime:"添加时间",
     *                          }
     *                      }
     */
    @ApiOperation(value="查询全部参数",notes="查询全部参数")
//    @UserLoginToken
    @GetMapping(value = "/queryShopParamList")
    public Result queryShopParamList() {
        return shopParamService.queryShopParamList();
    }
}
