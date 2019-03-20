package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.entity.ShopCart;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("购物车模块")
@RestController
@RequestMapping("/web/cart")
public class ShopCartController extends BizBaseController {

    /**
     * @api {POST} /cart/addShopCart 加入购物车
     * @apiGroup Cart
     * @apiVersion 0.0.1
     * @apiDescription 加入购物车
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",
     *                          productId:"商品id",
     *                          specId:"规格id",
     *                          productNum:"商品数量",
     *                          prescriptionId:"处方id",
     *                          prescriptionImage:"处方图片",
     *                          prescriptionPrice:"处方价格"
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"添加成功"
     *                       }
     */
    @ApiOperation(value="加入购物车",notes="加入购物车")
    @UserLoginToken
    @PostMapping("/addShopCart")
    public Result addShopCart(@RequestBody ShopCart cart) {
        return shopCartService.addShopCart(cart);
    }

    /**
     * @api {GET} /cart/delShopCart 删除购物车
     * @apiGroup Cart
     * @apiVersion 0.0.1
     * @apiDescription 删除购物车
     * @apiParam {Integer} userId  当前用户id
     * @apiParam {String}  cartId  购物车id（多个以逗号隔开）
     * @apiParamExample {json} 请求样例：
     *      /cart/delShopCart?userId=1&cartId=1
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"删除成功"
     *                       }
     */
    @ApiOperation(value="删除购物车",notes="删除购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="cartId",value="购物车id（多个以逗号隔开）",dataType="String",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/delShopCart")
    public Result delShopCart(Integer userId,String cartId) {
        return shopCartService.delShopCart(userId,cartId);
    }

    /**
     * @api {GET} /cart/emptyShopCart 清空购物车
     * @apiGroup Cart
     * @apiVersion 0.0.1
     * @apiDescription 清空购物车
     * @apiParam {Integer} userId  当前用户id
     * @apiParamExample {json} 请求样例：
     *         /cart/emptyShopCart?userId=1
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"清空完成"
     *                       }
     */
    @ApiOperation(value="清空购物车",notes="清空购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/emptyShopCart")
    public Result emptyShopCart(Integer userId) {
        return shopCartService.emptyShopCart(userId);
    }

    /**
     * @api {POST} /cart/updateTheNumber 修改购物车商品数量
     * @apiGroup Cart
     * @apiVersion 0.0.1
     * @apiDescription 修改购物车商品数量
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          cartId:"购物车id",
     *                          userId:"用户ID"
     *                          productNum:"最终数量"
     *                      }
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":"清空完成"
     *                       }
     */
    @ApiOperation(value="修改购物车商品数量",notes="修改购物车商品数量")
    @UserLoginToken
    @PostMapping("/updateTheNumber")
    public Result updateTheNumber(@RequestBody ShopCart shopCart) {
        return shopCartService.updateTheNumber(shopCart);
    }

    /**
     * @api {GET} /cart/getShopCartList 获取用户购物车信息
     * @apiGroup Cart
     * @apiVersion 0.0.1
     * @apiDescription 获取用户购物车信息
     * @apiParam {Integer} userId  当前用户id
     * @apiParam {Integer} pageNo  页码
     * @apiParam {Integer} pageSize  条数
     * @apiParamExample {json} 请求样例：
     *      /cart/getShopCartList?userId=1&pageNo=1&pageSize=10
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               cartId:"购物车主键",
     *                               userId:"用户id",
     *                               productId:"商品id",
     *                               specId:"规格id",
     *                               productNum:"商品数量",
     *                               createdTime:"创建时间",
     *                               dataFailure:"是否失效 0未失效 1已失效"
     *                               product:{                  //商品信息
     *                                    productId:"商品表主键",
     *                                    shopId:"店铺id",
     *                                    productName:"商品名",
     *                                    brandId:"品牌id",
     *                                    categoryId:"商品分类id",
     *                                    price:"商品价格",
     *                                    publishStatus:"上下架状态 0未上架 1已上架",
     *                                    auditStatus:"审核状态 0正在审核 1审核通过 2审核未通过",
     *                                    publishDate:"上架日期",
     *                                    descript:"商品描述",
     *                                    title:"商品标题",
     *                                    subtitle:"商品子标题",
     *                                    isGood:"是否精品 0不是 1是",
     *                                    isNew:"是否新品 0不是 1是",
     *                                    isPopular:"是否热销 0不是 1是",
     *                                    seoText:"seo描述",
     *                                    baseProp:"基本属性 json串",
     *                                    sellProp:"销售属性 json串",
     *                                    productImage:"商品图片",
     *                                    sellCount:"销售数量",
     *                                    viewCount:"查看数量",
     *                                    createdTime:"创建时间"
     *                               },
     *                               productSpec:{              //规格信息
     *                                    specId:"商品规格主键",
     *                                    productId:"商品id",
     *                                    productSpecs:"商品规格json",
     *                                    stock:"库存",
     *                                    price:"价格",
     *                                    createdTime:"预留字段",
     *                                    updatedTime:"创建时间",
     *                                    productImage{
     *                                        imageId:"商品图片表主键",
     *                                        imageUrl:"图片",
     *                                        imageDesc:"图片描述",
     *                                        isMaster:"是否主图 0不是 1是",
     *                                        imageStatus:"图片状态 0无效 1有效"
     *                                    }
     *                               }
     *                           }
     *                       }
     */
    @ApiOperation(value="获取用户购物车信息",notes="获取用户购物车信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageNo",value="页码",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="条数",dataType="int",required=true,paramType="query")
    })
    @UserLoginToken
    @GetMapping("/getShopCartList")
    public Result getShopCartList(Integer userId,Integer pageNo,Integer pageSize) {
        return shopCartService.getShopCartList(userId,pageNo,pageSize);
    }
}
