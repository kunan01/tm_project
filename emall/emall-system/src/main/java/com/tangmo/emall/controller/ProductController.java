package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.Product;
import com.tangmo.emall.entity.ProductParam;
import com.tangmo.emall.entity.ProductSpec;
import com.tangmo.emall.entity.dto.ProductDto;
import com.tangmo.emall.entity.dto.ProductUpdDto;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("商品模块")
@RestController
@RequestMapping("/system/product")
public class ProductController extends BizBaseController {

    /**
     * @api {POST} /product/addProduct 增加商品基本信息
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 增加商品基本信息
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          shopId:"店铺id",
     *                          productName:"商品名",
     *                          brandId:"品牌id",
     *                          categoryId:"商品分类id",
     *                          descript:"商品描述",
     *                          title:"商品标题",
     *                          subtitle:"商品子标题",
     *                          seoText:"seo描述",
     *                          baseProp:"基本属性，json串（例：{'厂家':'陕西省某某制造厂','材质':'钢化玻璃'}）",
     *                          sellProp:"销售属性，json串 (例：{'厂家':'陕西省某某制造厂','材质':'钢化玻璃'})",
     *                          productImage:"此图为商品的展示图片，只能一张！",
     *                          price:"价格：此为商品展示价格(真实价格还需填充规格)"
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
    @ApiOperation(value="增加商品基本信息",notes="增加商品基本信息")
    @UserLoginToken
    @PostMapping(value = "/addProduct")
    public Result addProduct(@RequestBody ProductUpdDto productUpdDto) {
        return productService.addProduct(productUpdDto);
    }

    /**
     * @api {POST} /product/updProduct 修改商品信息
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 修改商品信息
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          productId:"商品id",               (必填)
     *                          productName:"商品名",
     *                          brandId:"品牌id",
     *                          categoryId:"商品分类id  三级id",
     *                          descript:"商品描述",
     *                          title:"商品标题",
     *                          subtitle:"商品子标题",
     *                          seoText:"seo描述",
     *                          baseProp:"基本属性，json串（例：{'厂家':'陕西省某某制造厂','材质':'钢化玻璃'}）",
     *                          sellProp:"销售属性，json串 (例：{'厂家':'陕西省某某制造厂','材质':'钢化玻璃'})",
     *                          productImage:"图片id：此图为商品的展示图片，只能一张！",
     *                          price:"价格：此为商品展示价格(真实价格还需填充规格)"
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
    @ApiOperation(value="修改商品信息",notes="修改商品信息")
    @UserLoginToken
    @PostMapping(value = "/updProduct")
    public Result updProduct(@RequestBody ProductUpdDto productUpdDto) {
        return productService.updProduct(productUpdDto);
    }

    /**
     * @api {POST} /product/addProductProp 添加商品规格
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 添加商品规格
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          productId:"商品id",
     *                          productSpecs:"商品规格json（例：{'Color':'#00000|Black','Size':'Size库id'}）",
     *                          stock:"库存",
     *                          price:"价格",
     *                          productImages[
     *                              {
     *                                  imageUrl:"规格图片",
     *                                  imageDesc:"图片描述",  (选填)
     *                                  isMaster:"是否主图 0不是 1是"
     *                                  imageOrder:"图片排序",
     *                              },
     *                              {
     *                                  imageUrl:"规格图片",
     *                                  imageDesc:"图片描述",  (选填)
     *                                  isMaster:"是否主图 0不是 1是"
     *                                  imageOrder:"图片排序",
     *                              },
     *                              ....
     *                          ]
     *
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
    @ApiOperation(value="添加商品规格",notes="添加商品规格")
    @UserLoginToken
    @PostMapping(value = "/addProductProp")
    public Result addProductProp(@RequestBody ProductSpec productSpec) {
        return productService.addProductProp(productSpec);
    }

    /**
     * @api {POST} /product/updProductProp 修改商品规格
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 修改商品规格
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          specId:"规格id",
     *                          productSpecs:"商品规格json（例：{'颜色':'红色','尺码':'L'}）",    (选填)
     *                          stock:"库存",     (选填)
     *                          price:"价格",     (选填)
     *                          productImages:[   (选填)
     *                              {
     *                                  imageId:"图片主键",
     *                                  imageUrl:"规格图片", (选填)
     *                                  imageDesc:"图片描述",(选填)
     *                                  isMaster:"是否主图 0不是 1是"  (选填)
     *                                  imageOrder:"图片排序", (选填)
     *                              },
     *                              {
     *                                  imageId:"图片主键",
     *                                  imageUrl:"规格图片",  (选填)
     *                                  imageDesc:"图片描述",  (选填)
     *                                  isMaster:"是否主图 0不是 1是"   (选填)
     *                                  imageOrder:"图片排序", (选填)
     *                              },
     *                              ....
     *                          ]
     *
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
    @ApiOperation(value="修改商品规格",notes="修改商品规格")
    @UserLoginToken
    @PostMapping(value = "/updProductProp")
    public Result updProductProp(@RequestBody ProductSpec productSpec) {
        return productService.updProductProp(productSpec);
    }

    /**
     * @api {POST} /product/delProductProp 删除商品规格
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 删除商品规格
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          specId:"规格id",
     *                          shopUserId:"店铺工作人员id"
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
    @ApiOperation(value="删除商品规格",notes="删除商品规格")
    @UserLoginToken
    @PostMapping(value = "/delProductProp")
    public Result delProductProp(@RequestBody ProductSpec productSpec) {
        return productService.delProductProp(productSpec);
    }

    /**
     * @api {POST} /product/delProduct 删除商品
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 删除商品
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          productIdList:"商品id,数组",
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
    @ApiOperation(value="批量删除删除商品",notes="批量删除删除商品")
    @UserLoginToken
    @PostMapping(value = "/delProduct")
    public Result delProduct(@RequestBody ProductDto productDto) {
        return productService.delProduct(productDto);
    }

    /**
     * @api {POST} /product/shelvesProduct 上架商品
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 上架商品（上架成功后不能对商品进行编辑）
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          productId:"商品id",
     *                          shopUserId:"店铺工作人员id"
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "上架成功"
     *                      }
     */
    @ApiOperation(value="上架商品",notes="上架商品（上架成功后不能对商品进行编辑）")
    @UserLoginToken
    @PostMapping(value = "/shelvesProduct")
    public Result shelvesProduct(@RequestBody Product product) {
        return productService.shelvesProduct(product);
    }

    /**
     * @api {POST} /product/theShelvesProduct 下架商品
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 下架商品（下架操作会导致用户购物车或者未支付的订单不能进行购买，请谨慎操作）
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          productId:"商品id",
     *                          shopUserId:"店铺工作人员id"
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "上架成功"
     *                      }
     */
    @ApiOperation(value="下架商品",notes="下架操作会导致用户购物车或者未支付的订单不能进行购买，并且已经添加过的商品标签会失效！请谨慎操作")
    @UserLoginToken
    @PostMapping(value = "/theShelvesProduct")
    public Result theShelvesProduct(@RequestBody Product product) {
        return productService.theShelvesProduct(product);
    }

    /**
     * @api {POST} /product/setProductDiscounts 设置商品折扣
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 设置商品折扣
     * @apiParamExample {json} 请求样例:
     *                  {
     *                       productIdList:"商品主键id，数组",
     *                       discount:"折扣率"
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
    @ApiOperation(value="设置商品折扣",notes="设置商品折扣")
    @UserLoginToken
    @PostMapping(value = "/setProductDiscounts")
    public Result setProductDiscounts(@RequestBody ProductDto productDto) {
        return productService.setProductDiscounts(productDto);
    }

    /**
     * @api {POST} /product/getProductList 查询商品
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 查询商品
     * @apiParamExample {json} 请求样例:
     *                         {
     *                             shopId:"店铺id",                       （可选）
     *                             productName:"商品名模糊",               （可选）
     *                             brandId:"品牌id",                      （可选）
     *                             publishStatus:"上下架状态 0未上架 1已上架",（可选）
     *                             auditStatus:"审核状态 0正在审核 1审核通过 2审核未通过",    （可选）
     *                             isGood:"是否精品 0不是 1是",              （可选）
     *                             isNew:"是否新品 0不是 1是",               （可选）
     *                             isPopular:"是否热销 0不是 1是",           （可选）
     *                             sorting:"排序方式 0发布时间正序 1发布时间倒叙 2销售量正序 3销售量倒叙 4游览量正序 5浏览量倒叙" （必填）
     *                             pageNo:"页码",                           （必填）
     *                             pageSize:"条数"                          （必填）
     *                         }
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
    @ApiOperation(value="查询商品",notes="查询商品")
    @UserLoginToken
    @PostMapping(value = "/getProductList")
    public Result getProductList(@RequestBody ProductDto productDto) {
        return productService.getProductList(productDto);
    }

    /**
     * @api {POST} /product/getTrendProductList 查询商品
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 查询商品
     * @apiParamExample {json} 请求样例:
     *      /product/getTrendProductList
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *
     *                          }
     *                      }
     */
    @ApiOperation(value="筛选趋势商品",notes="筛选趋势商品")
    @UserLoginToken
    @PostMapping(value = "/getTrendProductList")
    public Result getTrendProductList(@RequestBody ProductDto productDto) {
        return productService.getTrendProductList(productDto);
    }

    /**
     * @api {POST} /product/getAdvertisingProductList 筛选活动商品
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 筛选活动商品
     * @apiParamExample {json} 请求样例:
     *      /product/getTrendProductList
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : {
     *
     *                          }
     *                      }
     */
    @ApiOperation(value="筛选活动商品",notes="筛选活动商品")
    @UserLoginToken
    @PostMapping(value = "/getAdvertisingProductList")
    public Result getAdvertisingProductList(@RequestBody ProductDto productDto) {
        return productService.getAdvertisingProductList(productDto);
    }

    /**
     * @api {GET} /product/getProductListById 通过商品id查询商品详细信息
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 通过商品id查询商品详细信息
     * @apiParamExample {json} 请求样例:
     *         /product/getProductListById?product=1
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
    @ApiOperation(value="查询商品详情",notes="查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="productId",value="商品id",dataType="String",required=true,paramType="query")
    })
//    @UserLoginToken
    @GetMapping(value = "/getProductListById")
    public Result getProductListById(Integer productId) {
        return productService.getProductListById(productId);
    }

    /**
     * @api {POST} /product/setOrCancelNewProduct 设置或取消新品商品
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 设置或取消新品商品
     * @apiParam {String} productId 商品id（多个以逗号隔开）
     * @apiParam {Integer} isNew 是否新品：0不是 1是
     * @apiParamExample {json} 请求样例:
     *      /product/setOrCancelNewProduct?productId=1,2&isNew=0
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "上架成功"
     *                      }
     */
    @ApiOperation(value="设置或取消新品商品",notes="设置或取消新品商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="productId",value="商品id（多个以逗号隔开）",dataType="String",required=true,paramType="query"),
            @ApiImplicitParam(name="isNew",value="是否新品：0不是 1是",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @PostMapping(value = "/setOrCancelNewProduct")
    public Result setOrCancelNewProduct(String productId,Integer isNew) {
        return productService.setOrCancelNewProduct(productId,isNew);
    }

    /**
     * @api {POST} /product/addProductParam 添加商品标签
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 添加商品标签
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          productId:"商品id",
     *                          valueId:"属性值id"
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
    @ApiOperation(value="添加商品标签",notes="添加商品标签")
    @UserLoginToken
    @PostMapping(value = "/addProductParam")
    public Result addProductParam(@RequestBody ProductParam productParam) {
        return productService.addProductParam(productParam);
    }

    /**
     * @api {GET} /product/delProductParam 删除商品标签(多个以逗号隔开)
     * @apiGroup Product
     * @apiVersion 0.0.1
     * @apiDescription 删除商品标签(多个以逗号隔开)
     * @apiParam {String} lId : 标签主键id
     * @apiParamExample {json} 请求样例:
     *      /product/delProductParam?lId=1,2
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "删除成功"
     *                      }
     */
    @ApiOperation(value="删除商品标签(多个以逗号隔开)",notes="删除商品标签(多个以逗号隔开)")
    @ApiImplicitParams({
            @ApiImplicitParam(name="lId",value="标签主键id",dataType="String",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping(value = "/delProductParam")
    public Result delProductParam(String lId) {
        return productService.delProductParam(lId);
    }
}
