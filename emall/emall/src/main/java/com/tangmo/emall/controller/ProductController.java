package com.tangmo.emall.controller;

import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.dto.ProductDto;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("商品")
@RestController
@RequestMapping("/web/product")
public class ProductController extends BizBaseController {

    /**
     * @api {POST} /product/getProductList 查询商品
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 查询商品
     * @apiParamExample {json} 请求样例:
     *                          {
     *                              productName:"商品名称"
     *                              categoryId:"商品分类id",  （选填）
     *                              lIdList:"标签id（数组格式）"（选填）
     *                              pageNo:"页码"   （必填）,
     *                              pageSize:"条数" （必填）,
     *                              sorting:"排序方式id"
     *                              startPrice:"开始价格"（选填）,
     *                              endPrice:"结束价格"（选填）,
     *                              trId:"趋势id"（选填）,
     *                              disType:"0:折扣商品"（选填）,
     *                              raId:"热门活动id"（选填）
     *                          }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              productId:"商品表主键",
     *                              shopId:"店铺id",
     *                              productName:"商品名",
     *                              brandId:"品牌id",
     *                              categoryId:"商品分类id",
     *                              price:"商品价格",
     *                              publishStatus:"上下架状态 0未上架 1已上架",
     *                              auditStatus:"审核状态 0正在审核 1审核通过 2审核未通过",
     *                              publishDate:"上架日期",
     *                              descript:"商品描述",
     *                              title:"商品标题",
     *                              subtitle:"商品子标题",
     *                              isGood:"是否精品 0不是 1是",
     *                              isNew:"是否新品 0不是 1是",
     *                              isPopular:"是否热销 0不是 1是",
     *                              seoText:"seo描述",
     *                              baseProp:"基本属性 json串",
     *                              sellProp:"销售属性 json串",
     *                              productImage:"商品图片",
     *                              sellCount:"销售数量",
     *                              viewCount:"查看数量",
     *                              createdTime:"创建时间",
     *                              updatedTime:"修改时间",
     *                              key:"规格key集合",
     *                              value:"规格value集合"
     *                              preferentialType:"优惠类型 0满减 1打折 2免邮 3买送 4降价",
     *                              preferentialStr:"优惠信息 （满100减30）"
     *                              specList[      //商品规格集合
     *                                  {
     *                                      specId:"商品规格主键",
     *                                      productId:"商品id",
     *                                      productSpecs:"商品规格json",
     *                                      stock:"库存",
     *                                      price:"价格",
     *                                      preferentialPrice:"优惠价格",
     *                                      createdTime:"预留字段",
     *                                      updatedTime:"创建时间",
     *                                      productImage[
     *                                          {
     *                                              imageId:"商品图片表主键",
     *                                              imageUrl:"图片",
     *                                              imageDesc:"图片描述",
     *                                              isMaster:"是否主图 0不是 1是",
     *                                              imageStatus:"图片状态 0无效 1有效"
     *                                          },
     *                                          {
     *                                              imageId:"商品图片表主键",
     *                                              imageUrl:"图片",
     *                                              imageDesc:"图片描述",
     *                                              isMaster:"是否主图 0不是 1是",
     *                                              imageStatus:"图片状态 0无效 1有效"
     *                                          }.....
     *                                      ]
     *                                  }....
     *                              ]
     *                          }
     *                      }
     */
    @ApiOperation(value="查询商品",notes="查询商品")
    @PostMapping(value = "/getProductList")
    public Result getProductList(@RequestBody ProductDto productDto) {
        return productService.getProductList(productDto);
    }

    /**
     * @api {GET} /product/getProductParamList 查询店铺标签
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 查询店铺标签
     * @apiParam {Integer} shopId  店铺id
     * @apiParamExample {json} 请求样例:
     *      /product/getProductParamList?shopId=1
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
     *                              paramValues[ //参数value
     *                                  {
     *                                      valueId:"参数值主键",
     *                                      paramValue:"参数值内容",
     *                                      typeId:"店铺id",
     *                                      createdTime:"添加时间",
     *                                      paramImage:"参数图",
     *                                      propCount:"商品数量"
     *                                  }
     *                                  ...
     *                              ]
     *                          }
     *                      }
     */
    @ApiOperation(value="查询店铺标签",notes="查询店铺标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name="shopId",value="店铺id",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value = "/getProductParamList")
    public Result getProductParamList(Integer shopId) {
        return productService.getProductParamList(shopId);
    }

    /**
     * @api {POST} /product/getProductById 商品详情
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 商品详情
     * @apiParam {Integer} productId  商品id
     * @apiParamExample {json} 请求样例:
     *           /product/getProductById?productId=1
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              productId:"商品表主键",
     *                              shopId:"店铺id",
     *                              productName:"商品名",
     *                              brandId:"品牌id",
     *                              categoryId:"商品分类id",
     *                              price:"商品价格",
     *                              publishStatus:"上下架状态 0未上架 1已上架",
     *                              auditStatus:"审核状态 0正在审核 1审核通过 2审核未通过",
     *                              publishDate:"上架日期",
     *                              descript:"商品描述",
     *                              title:"商品标题",
     *                              subtitle:"商品子标题",
     *                              isGood:"是否精品 0不是 1是",
     *                              isNew:"是否新品 0不是 1是",
     *                              isPopular:"是否热销 0不是 1是",
     *                              seoText:"seo描述",
     *                              baseProp:"基本属性 json串",
     *                              sellProp:"销售属性 json串",
     *                              productImage:"商品图片",
     *                              sellCount:"销售数量",
     *                              viewCount:"查看数量",
     *                              createdTime:"创建时间",
     *                              updatedTime:"修改时间",
     *                              key:"规格key集合",
     *                              value:"规格value集合"
     *                              specList[      //商品规格集合
     *                                  {
     *                                      specId:"商品规格主键",
     *                                      productId:"商品id",
     *                                      productSpecs:"商品规格json",
     *                                      stock:"库存",
     *                                      price:"价格",
     *                                      createdTime:"预留字段",
     *                                      updatedTime:"创建时间",
     *                                      productImage[
     *                                          {
     *                                              imageId:"商品图片表主键",
     *                                              imageUrl:"图片",
     *                                              imageDesc:"图片描述",
     *                                              isMaster:"是否主图 0不是 1是",
     *                                              imageStatus:"图片状态 0无效 1有效"
     *                                          },
     *                                          {
     *                                              imageId:"商品图片表主键",
     *                                              imageUrl:"图片",
     *                                              imageDesc:"图片描述",
     *                                              isMaster:"是否主图 0不是 1是",
     *                                              imageStatus:"图片状态 0无效 1有效"
     *                                          }.....
     *                                      ]
     *                                  }....
     *                              ]
     *                          }
     *                      }
     */
    @ApiOperation(value="商品详情",notes="商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="productId",value="商品id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="userId",value="用户id",dataType="int",required=false,paramType="query")
    })
    @PostMapping(value = "/getProductById")
    public Result getProductById(Integer productId,Integer userId) {
        return productService.getProductById(productId,userId);
    }

    /**
     * @api {GET} /product/getProductCategory 查询商品类别
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 查询商品类别
     * @apiParam {Integer} level  分类级别 (1,2,3)
     * @apiParam {Integer} type   查询类别（1：查询当前级别，2查询当前级别及下一级级别，3查询当前级别以及下级所有级别）
     * @apiParamExample {json} 请求样例:
     *      /product/getProductCategory?level=1&type=1
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
    @ApiOperation(value="查询商品类别",notes="查询商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name="level",value="分类级别 (1,2,3)",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="查询类别（1：查询当前级别，2查询当前级别及下一级级别，3查询当前级别以及下级所有级别）",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value = "/getProductCategory")
    public Result getProductCategory(Integer level,Integer type) {
        return productService.getCateGoryList(level,type);
    }

    /**
     * @api {GET} /product/getNewGoods 查询首页新品商品
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 查询首页新品商品
     * @apiParam {Integer} pageNO  页码
     * @apiParam {Integer} pageSize  条数
     * @apiParamExample {json} 请求样例:
     *      /product/getNewGoods?pageNO=1&pageSize=10
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              productId:"商品表主键",
     *                              shopId:"店铺id",
     *                              productName:"商品名",
     *                              brandId:"品牌id",
     *                              categoryId:"商品分类id",
     *                              price:"商品价格",
     *                              publishStatus:"上下架状态 0未上架 1已上架",
     *                              auditStatus:"审核状态 0正在审核 1审核通过 2审核未通过",
     *                              publishDate:"上架日期",
     *                              descript:"商品描述",
     *                              title:"商品标题",
     *                              subtitle:"商品子标题",
     *                              isGood:"是否精品 0不是 1是",
     *                              isNew:"是否新品 0不是 1是",
     *                              isPopular:"是否热销 0不是 1是",
     *                              seoText:"seo描述",
     *                              baseProp:"基本属性 json串",
     *                              sellProp:"销售属性 json串",
     *                              productImage:"商品图片",
     *                              sellCount:"销售数量",
     *                              viewCount:"查看数量",
     *                              createdTime:"创建时间",
     *                              updatedTime:"修改时间",
     *                              key:"规格key集合",
     *                              value:"规格value集合"
     *                              specList[      //商品规格集合
     *                                  {
     *                                      specId:"商品规格主键",
     *                                      productId:"商品id",
     *                                      productSpecs:"商品规格json",
     *                                      stock:"库存",
     *                                      price:"价格",
     *                                      createdTime:"预留字段",
     *                                      updatedTime:"创建时间",
     *                                      productImage[
     *                                          {
     *                                              imageId:"商品图片表主键",
     *                                              imageUrl:"图片",
     *                                              imageDesc:"图片描述",
     *                                              isMaster:"是否主图 0不是 1是",
     *                                              imageStatus:"图片状态 0无效 1有效"
     *                                          },
     *                                          {
     *                                              imageId:"商品图片表主键",
     *                                              imageUrl:"图片",
     *                                              imageDesc:"图片描述",
     *                                              isMaster:"是否主图 0不是 1是",
     *                                              imageStatus:"图片状态 0无效 1有效"
     *                                          }.....
     *                                      ]
     *                                  }....
     *                              ]
     *                          }
     *                      }
     */
    @ApiOperation(value="查询首页新品商品",notes="查询首页新品商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNO",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value = "/getNewGoods")
    public Result getNewGoods(Integer pageNO,Integer pageSize) {
        return productService.getNewGoods(pageNO,pageSize);
    }

    /**
     * @api {GET} /product/getCommodityPriceRange 查询商品价格区间
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 查询商品价格区间
     * @apiParamExample {json} 请求样例:
     *      /product/getCommodityPriceRange
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {"100-120"}
     *                      }
     */
    @ApiOperation(value="查询商品价格区间",notes="查询商品价格区间")
    @GetMapping(value = "/getCommodityPriceRange")
    public Result getCommodityPriceRange() {
        return productService.getCommodityPriceRange();
    }

    /**
     * @api {GET} /product/getStoreDistribution 获取店铺配送方式
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 获取店铺配送方式
     * @apiParam {Integer} shopId 店铺id
     * @apiParamExample {json} 请求样例:
     *      /product/getStoreDistribution?shopId=1
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {}
     *                      }
     */
    @ApiOperation(value="获取店铺配送方式",notes="获取店铺配送方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name="shopId",value="店铺id",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value = "/getStoreDistribution")
    public Result getStoreDistribution(Integer shopId) {
        return productService.getStoreDistribution(shopId);
    }

    /**
     * @api {GET} /product/getSortingWay 获取商品排序方式规则
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 获取商品排序方式规则
     * @apiParamExample {json} 请求样例:
     *      /product/getSortingWay
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *                              sortingId:"主键id",
     *                              title:"描述"
     *                          }
     *                      }
     */
    @ApiOperation(value="获取商品排序方式规则",notes="获取商品排序方式规则")
    @GetMapping(value = "/getSortingWay")
    public Result getSortingWay() {
        return productService.getSortingWay();
    }
}
