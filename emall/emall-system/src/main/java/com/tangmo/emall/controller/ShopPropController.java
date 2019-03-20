package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.PropKey;
import com.tangmo.emall.entity.PropValue;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api("店铺规格模块")
@RestController
@RequestMapping("/system/shopProp")
public class ShopPropController extends BizBaseController {

    /**
     * @api {POST} /shopProp/addPropKey 添加店铺规格key
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 添加店铺规格key
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          keyName:"属性名称",
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
    @ApiOperation(value="添加店铺规格key",notes="添加店铺规格key")
    @UserLoginToken
    @PostMapping(value = "/addPropKey")
    public Result addPropKey(@RequestBody PropKey propKey) {
        return shopPropService.addPropKey(propKey);
    }

    /**
     * @api {POST} /shopProp/addPropValue 添加店铺规格Value
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 添加店铺规格Value
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          keyId:"规格keyId",
     *                          propValue:"规格value",
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
    @ApiOperation(value="添加店铺规格Value",notes="添加店铺规格Value")
    @UserLoginToken
    @PostMapping(value = "/addPropValue")
    public Result addPropValue(@RequestBody PropValue propValue) {
        return shopPropService.addPropValue(propValue);
    }

    /**
     * @api {POST} /shopProp/batchDelPropValue 批量删除店铺规格Value
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 批量删除店铺规格Value
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          valueIdList:[数组:规格Value主键id]
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "删除成功"
     *                      }
     */
    @ApiOperation(value="批量删除店铺规格Value",notes="批量删除店铺规格Value")
    @UserLoginToken
    @PostMapping(value = "/batchDelPropValue")
    public Result batchDelPropValue(@RequestBody PropValue propValue) {
        return shopPropService.batchDelPropValue(propValue);
    }

    /**
     * @api {POST} /shopProp/delPropValue 删除店铺规格Value
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 删除店铺规格Value
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          valueId:"规格Value主键id",
     *                          shopUserId:"操作用户id"
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "删除成功"
     *                      }
     */
    @ApiOperation(value="删除店铺规格Value",notes="删除店铺规格Value")
    @UserLoginToken
    @PostMapping(value = "/delPropValue")
    public Result delPropValue(@RequestBody PropValue propValue) {
        return shopPropService.delPropValue(propValue);
    }


    /**
     * @api {POST} /shopProp/delPropKey 删除店铺规格key（如果当前key下存在子集value，则删除）
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 删除店铺规格key（如果当前key下存在子集value，则删除）
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          keyId:"规格key主键id"
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "删除成功"
     *                      }
     */
    @ApiOperation(value="删除店铺规格key（如果当前key下存在子集value，则删除）",notes="删除店铺规格key（如果当前key下存在子集value，则删除）")
    @UserLoginToken
    @PostMapping(value = "/delPropKey")
    public Result delPropKey(@RequestBody PropKey propKey) {
        return shopPropService.delPropKey(propKey);
    }

    /**
     * @api {POST} /shopProp/batchDelPropKey 批量删除店铺规格key（如果当前key下存在子集value，则删除）
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 批量删除店铺规格key（如果当前key下存在子集value，则删除）
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          KeyIdList:[数组:规格key主键id]
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "删除成功"
     *                      }
     */
    @ApiOperation(value="批量删除店铺规格key（如果当前key下存在子集value，则删除）",notes="批量删除店铺规格key（如果当前key下存在子集value，则删除）")
    @UserLoginToken
    @PostMapping(value = "/batchDelPropKey")
    public Result batchDelPropKey(@RequestBody PropKey propKey) {
        return shopPropService.batchDelPropKey(propKey);
    }


    /**
     * @api {GET} /shopProp/getIsValueByKey 校验当前key下是否存在value
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 校验当前key下是否存在value
     * @apiParam {Integer} keyId    规格KeyId
     * @apiParamExample {json} 请求样例:
     *          /shopProp/getIsValueByKey?keyId=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "true:存在/false:不存在"
     *                      }
     */
    @ApiOperation(value="校验当前key下是否存在value",notes="true: 存在，false：不存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name="keyId",value="规格keyId",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getIsValueByKey")
    public Result getIsValueByKey(Integer keyId) {
        return shopPropService.getIsValueByKey(keyId);
    }

    /**
     * @api {GET} /shopProp/getListKey 获取规格key集合
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 获取规格key集合
     * @apiParam {Integer} shopId   店铺id
     * @apiParam {Integer} state  查询状态 1查询全部 2分页查询
     * @apiParam {Integer} pageNo   页码
     * @apiParam {Integer} pageSize  条数
     * @apiParamExample {json} 请求样例:
     *          /shopProp/getListKey?shopId=1&state=1&pageNo=1&pageSize=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              keyId:"key主键id"
     *                              keyName:"key属性名"
     *                              shopId:"店铺id"
     *                              createdTime:"创建时间"
     *                          }
     *                      }
     */
    @ApiOperation(value="获取规格key集合",notes="获取规格key集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name="shopId",value="店铺id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="state",value="查询状态 1查询全部 2分页查询",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=false,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getListKey")
    public Result getListKey(Integer shopId,Integer state,Integer pageNo,Integer pageSize) {
        return shopPropService.getListKey(shopId,state,pageNo,pageSize);
    }

    /**
     * @api {GET} /shopProp/getListValue 获取规格value集合
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 获取规格value集合
     * @apiParam {Integer} keyId   规格keyId
     * @apiParam {Integer} pageNo   页码
     * @apiParam {Integer} pageSize  条数
     * @apiParamExample {json} 请求样例:
     *          /shopProp/getListValue?keyId=1&pageNo=1&pageSize=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              valueId:""
     *                          }
     *                      }
     */
    @ApiOperation(value="获取规格value集合",notes="获取规格value集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name="keyId",value="规格keyId",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getListValue")
    public Result getListValue(Integer keyId,Integer pageNo,Integer pageSize) {
        return shopPropService.getListValue(keyId,pageNo,pageSize);
    }

    /**
     * @api {POST} /shopProp/updPropKey 修改店铺规格key 属性名称
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 修改店铺规格key 属性名称
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          keyId:"主键"
     *                          keyName:"属性名称",
     *                          shopUserId:"操作用户id"
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
    @ApiOperation(value="修改店铺规格key 属性名称",notes="修改店铺规格key 属性名称")
    @UserLoginToken
    @PostMapping(value = "/updPropKey")
    public Result updPropKey(@RequestBody PropKey propKey) {
        return shopPropService.updPropKey(propKey);
    }


    /**
     * @api {POST} /shopProp/updPropValue 修改店铺规格value 属性名称
     * @apiGroup Prop
     * @apiVersion 0.0.1
     * @apiDescription 修改店铺规格value 属性名称
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          valueId:"主键"
     *                          propValue:"属性名称",
     *                          shopUserId:"操作用户id"
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
    @ApiOperation(value="修改店铺规格value 属性名称",notes="修改店铺规格value 属性名称")
    @UserLoginToken
    @PostMapping(value = "/updPropValue")
    public Result updPropValue(@RequestBody PropValue propValue) {
        return shopPropService.updPropValue(propValue);
    }
}
