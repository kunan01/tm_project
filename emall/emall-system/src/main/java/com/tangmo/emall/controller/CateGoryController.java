package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.CateGory;
import com.tangmo.emall.entity.SortingWay;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("商品分类模块")
@RestController
@RequestMapping("/system/category")
public class CateGoryController extends BizBaseController {

    /**
     * @api {POST} /category/addCategory 增加商品分类
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 增加商品分类
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          categoryName:"分类名称",    (必填)
     *                          parentId:"父级id",         (必填 添加一级分类父级id填0)
     *                          categoryLevel:"当前等级",   (必填 例 1，2，3)
     *                          sysUserId:"操作用户id"      (必填)
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
    @ApiOperation(value="增加商品分类",notes="增加商品分类")
    @UserLoginToken
    @PostMapping(value = "/addCategory")
    public Result addCategory(@RequestBody CateGory cateGory) {
            return cateGoryService.addCategory(cateGory);
    }

    /**
     * @api {POST} /category/updCategory 修改商品分类名称
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 修改商品分类
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          categoryId:"分类id"，       (必填)
     *                          categoryName:"分类名称",    (必填)
     *                          sysUserId:"操作用户id"      (必填)
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
    @ApiOperation(value="修改商品分类",notes="修改商品分类")
    @UserLoginToken
    @PostMapping(value = "/updCategory")
    public Result updCategory(@RequestBody CateGory cateGory) {
        return cateGoryService.updCategory(cateGory);
    }

    /**
     * @api {GET} /category/getCateGoryList 获取一级商品分类
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 获取一级商品分类
     * @apiParam {Integer} state  查询状态 (0全部 1分页)
     * @apiParam {Integer} pageNo 页码
     * @apiParam {Integer} pageSize 条数
     * @apiParamExample {json} 请求样例:
     *      /category/getCateGoryList?state=1
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                                 categoryId:"主键id",
     *                                 categoryName:"分类名称",
     *                                 parentId:"父级id",
     *                                 categoryLevel:"当前级别",
     *                                 cateGoryList:"下级分类{...}"
     *                          }
     *                      }
     */
    @ApiOperation(value="获取一级商品分类",notes="获取一级商品分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name="state",value="查询状态 (0全部 1分页)",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=false,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getCateGoryList")
    public Result getCateGoryList(Integer state,Integer pageNo,Integer pageSize) {
        return cateGoryService.getCateGoryList(state,pageNo,pageSize);
    }

    /**
     * @api {GET} /category/queryCateGoryList 获取全部商品分类信息
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 获取全部商品分类信息
     * @apiParamExample {json} 请求样例:
     *      /category/queryCateGoryList
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                                 categoryId:"主键id",
     *                                 categoryName:"分类名称",
     *                                 parentId:"父级id",
     *                                 categoryLevel:"当前级别",
     *                                 cateGoryList:"下级分类{...}"
     *                          }
     *                      }
     */
    @ApiOperation(value="获取全部商品分类信息",notes="获取全部商品分类信息")
//    @UserLoginToken
    @GetMapping(value = "/queryCateGoryList")
    public Result queryCateGoryList() {
        return cateGoryService.queryCateGoryList();
    }

    /**
     * @api {GET} /category/getCateGoryListByTwoLevel 通过一级获取二级商品分类
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 通过一级获取二级商品分类
     * @apiParam {Integer} state  查询状态 (0全部 1分页)
     * @apiParam {Integer} pageNo 页码
     * @apiParam {Integer} pageSize 条数
     * @apiParamExample {json} 请求样例:
     *      /category/getCateGoryListByTwoLevel?state=1
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                                 categoryId:"主键id",
     *                                 categoryName:"分类名称",
     *                                 parentId:"父级id",
     *                                 categoryLevel:"当前级别",
     *                                 cateGoryList:"下级分类{...}"
     *                          }
     *                      }
     */
    @ApiOperation(value="通过一级获取二级商品分类",notes="通过一级获取二级商品分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name="state",value="查询状态 (0全部 1分页)",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="parentId",value="一级分类id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=false,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getCateGoryListByTwoLevel")
    public Result getCateGoryListByTwoLevel(Integer state,Integer parentId,Integer pageNo,Integer pageSize) {
        return cateGoryService.getCateGoryListByTwoLevel(state,parentId,pageNo,pageSize);
    }

    /**
     * @api {GET} /category/delCateGory 删除商品分类
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 删除商品分类（如果当前分类下有子集分类，则删除）
     * @apiParam {Integer} cId      商品分类id
     * @apiParamExample {json} 请求样例:
     *          /category/delCateGory?cId=1&userId=1
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "删除成功"
     *                      }
     */
    @ApiOperation(value="删除商品分类",notes="删除商品分类（如果当前分类下有子集分类，则删除）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="cId",value="商品分类id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/delCateGory")
    public Result delCateGory(Integer cId) {
        return cateGoryService.delCateGory(cId);
    }

    /**
     * @api {GET} /category/batchDelCateGory 批量删除商品分类
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 批量删除商品分类（如果当前分类下有子集分类，则删除）
     * @apiParamExample {json} 请求样例:
     *                  {
     *                      categoryIdList:[数组]
     *                  }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "删除成功"
     *                      }
     */
    @ApiOperation(value="批量删除商品分类",notes="批量删除商品分类（如果当前分类下有子集分类，则删除）")
    @UserLoginToken
    @PostMapping(value = "/batchDelCateGory")
    public Result batchDelCateGory(@RequestBody CateGory cateGory) {
        return cateGoryService.batchDelCateGory(cateGory);
    }

    /**
     * @api {GET} /category/getIsPType 校验当前分类下是否存在子集分类
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 校验当前分类下是否存在子集分类
     * @apiParam {Integer} cId      商品分类id
     * @apiParamExample {json} 请求样例:
     *          /category/getIsPType?cId=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "true:存在/false:不存在"
     *                      }
     */
    @ApiOperation(value="校验当前分类下是否存在子集分类",notes="true: 存在，false：不存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name="cId",value="商品分类id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getIsPType")
    public Result getIsPType(Integer cId) {
        return cateGoryService.getIsPType(cId);
    }


    /**
     * @api {POST} /category/addSortingWay 增加商品排序方式
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 增加商品排序方式
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          title:"排序名称",
     *                          sortingRules:"排序方式sql规则",
     *                          sortingType:"排序类别 0商品 1评论"
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
    @ApiOperation(value="增加商品排序方式",notes="增加商品排序方式")
    @UserLoginToken
    @PostMapping(value = "/addSortingWay")
    public Result addSortingWay(@RequestBody SortingWay sortingWay) {
        return cateGoryService.addSortingWay(sortingWay);
    }

    /**
     * @api {POST} /category/updateSortingWay 修改商品排序方式
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 修改商品排序方式
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          sortingId:"规则id",
     *                          title:"排序名称",
     *                          sortingRules:"排序方式sql规则"
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
    @ApiOperation(value="修改排序方式",notes="修改排序方式")
    @UserLoginToken
    @PostMapping(value = "/updateSortingWay")
    public Result updateSortingWay(@RequestBody SortingWay sortingWay) {
        return cateGoryService.updateSortingWay(sortingWay);
    }

    /**
     * @api {GET} /category/delSortingWay 删除商品筛选方式
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 删除商品筛选方式
     * @apiParam {Integer} sId 筛选方式id
     * @apiParamExample {json} 请求样例:
     *      /category/delSortingWay?sId=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "删除成功"
     *                      }
     */
    @ApiOperation(value="删除筛选方式",notes="删除筛选方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sId",value="筛选方式id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/delSortingWay")
    public Result delSortingWay(Integer sId) {
        return cateGoryService.delSortingWay(sId);
    }

    /**
     * @api {POST} /category/batchDelSortingWay 批量删除商品排序方式
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 批量删除商品排序方式
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          sortingIdList:[数组：筛选方式id]
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
    @ApiOperation(value="批量删除排序方式",notes="批量删除排序方式")
    @UserLoginToken
    @PostMapping(value = "/batchDelSortingWay")
    public Result batchDelSortingWay(@RequestBody SortingWay sortingWay) {
        return cateGoryService.batchDelSortingWay(sortingWay);
    }

    /**
     * @api {GET} /category/getSortingWay 获取排序方式
     * @apiGroup CateGory
     * @apiVersion 0.0.1
     * @apiDescription 获取排序方式
     * @apiParam {Integer} sortingType  排序类别：-1查询所有 0商品 1评论
     * @apiParam {Integer} pageNo   页码
     * @apiParam {Integer} pageSize   条数
     * @apiParamExample {json} 请求样例:
     *      /category/getSortingWay?pageN=1&pageNo=10
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              sortingId:"筛选方式id",
     *                              title:"描述",
     *                              sortingRules:"排序规则sql定义",
     *                              createdTime:"创建时间"
     *                              sortingType:排序类别：0商品 1评论
     *                          }
     *                      }
     */
    @ApiOperation(value="获取排序方式",notes="获取排序方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sortingType",value="排序类别：-1查询所有 0商品 1评论",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/getSortingWay")
    public Result getSortingWay(Integer sortingType,Integer pageNo,Integer pageSize) {
        return cateGoryService.getSortingWay(sortingType,pageNo,pageSize);
    }

}
