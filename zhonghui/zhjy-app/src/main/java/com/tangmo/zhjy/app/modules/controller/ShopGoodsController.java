package com.tangmo.zhjy.app.modules.controller;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.Commodity;
import com.tangmo.zhjy.app.modules.bean.ShopGoods;
import com.tangmo.zhjy.app.modules.dto.ShopServiceDto;
import com.tangmo.zhjy.app.modules.service.CommodityService;
import com.tangmo.zhjy.app.modules.service.ShopGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 18/1/2
 * @description 店铺商品信息控制层
 */
@Api("店铺商品信息/同城服务")
@RestController
@RequestMapping("/shop")
public class ShopGoodsController{

    @Resource
    private CommodityService commodityService;
    @Resource
    private ShopGoodsService shopGoodsService;

    /**
     * @api {POST} /shop/service 增加同城信息
     * @apiGroup ShopGoods
     * @apiVersion 0.0.1
     * @apiDescription 增加商家服务
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userId:"1",
     *                      shopContent:"介绍",
     *                      shopName:"标题"
     *                      shopClassType:"所属类别",
     *                      province:"省"
     *                      city:"城市",
     *                      district:"区域"
     *                      address:"店铺地址",
     *                      imgId:图片Id,
     *                      mobile:手机号,
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */

    @ApiOperation(value=" 增加同城信息")
    @PostMapping("/service")
    public Result addShopService(@RequestBody ShopGoods shopGoods){
        return shopGoodsService.addShopService(shopGoods);
    }

    /**
     * @api {PUT} /shop/service 修改同城信息
     * @apiGroup ShopGoods
     * @apiVersion 0.0.1
     * @apiDescription 修改商家服务
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      sgId:"主键"
     *                      shopContent:"介绍",
     *                      shopName:"标题",
     *                      shopClassType:"所属类别",
     *                      province:"省",
     *                      city:"城市",
     *                      district:"区域"
     *                      address:"详细地址",
     *                      imgId:图片Id,
     *                      mobile:手机号
     *                   }
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value=" 修改同城信息")
    @PutMapping("/service")
    public Result changeShopService(@RequestBody ShopGoods shopGoods){
        return shopGoodsService.changeShopService(shopGoods);
    }

    /**
     * @api {GET} /shop/service/list/{type}/{start}/{end} 根据类型和区域筛选同城信息列表
     * @apiGroup ShopGoods
     * @apiVersion 0.0.1
     * @apiParam {byte} type 服务类型
     * @apiParam {int} start 分页起始索引
     * @apiParam {int} end 查询列表长度
     * @apiDescription 获取指定类型服务列表
     * @apiParamExample {json} 请求样例：
     *  /shop/service/list/1/0/10
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                     [{
     *                        sgId: 1,
     *                        shopName "店铺名",
     *                        imgId:"商品图片",
     *                        shopContent: "店铺介绍",
     *                        price:"价格",
     *                        city:"城市",
     *                        district:"区域"
     *                      }
     *                     {
     *                        sgId: 2,
     *                        shopName: "店铺名",
     *                        imgId:"商品图片",
     *                        shopContent: "店铺介绍",
     *                        price:"价格",
     *                        city:"城市",
     *                        district:"区域"
     *                     }]
     *                     }
     */
    @ApiOperation(value="根据类型和区域筛选同城信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="start",value="当前页,必填：false",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="end",value="显示多少条参数,必填：false",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="同城服务类型",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="district",value="区域",dataType="String",required=false,paramType="query"),
            @ApiImplicitParam(name="isTime",value="时间选择 0不限 1小时内 2一天内 3一个月内",dataType="int",required=true,paramType="query")
    })
    @PostMapping("/service/list")
    public Result searchShopService(Integer start,Integer end ,Integer type,String district,Integer isTime){
        return shopGoodsService.searchServiceByType(type,district,start, end,isTime);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="查询同城服务分类",notes="查询同城服务分类")
    @GetMapping(value="/getServiceClassType")
    public Result getServiceClassType(){
        return shopGoodsService.getServiceClassType();
    }

    /**
     * @api {GET} /shop/service/detail/{sgId} 获取服务详情
     * @apiGroup ShopGoods
     * @apiVersion 0.0.1
     * @apiDescription 获取服务详情
     * @apiParamExample {json} 请求样例：
     *  /shop/service/detail/1
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                      username:"发布人",
     *                      shopName:"标题是什么",
     *                      shopContent:"内容是什么",
     *                      imgId:"图片base64编码",
     *                      imgList:"图片列表",
     *                      city:"市",
     *                      price:"商品价格",
     *                      address:"地址",
     *                      mobile:"手机",
     *                      discountNote:"打折活动"
     *                   }
     *                   }
     */
    @ApiOperation(value=" 获取服务详情")
    @GetMapping("/service/detail/{sgId}")
    public Result searchSgDetail(@PathVariable Integer sgId){
        return shopGoodsService.searchServiceDetail(sgId);
    }

    /**
     * @api {GET} /shop/service/select 筛选服务列表
     * @apiGroup ShopGoods
     * @apiVersion 0.0.1
     * @apiDescription 筛选服务列表
     * @apiParamExample {json} 请求样例：
     *                  {
     *                      type:服务类型,
     *                      city:"城市",
     *                      price:"价格排序 0,正序,1倒序"
     *                      priceStart:"价格开始区间",
     *                      priceEnd:"价格结束区间",
     *                      start:"分页开始索引,必填",
     *                      end:"分页查询长度,必填",
     *                   }
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                     [{
     *                        sgId: 1,
     *                        shopName "店铺名",
     *                        imgId:"商品图片",
     *                        shopContent: "店铺介绍",
     *                        price:"价格",
     *                        city:"城市",
     *                        district:"区域"
     *                      }
     *                     {
     *                        sgId: 2,
     *                        shopName: "店铺名",
     *                        imgId:"商品图片",
     *                        shopContent: "店铺介绍",
     *                        price:"价格",
     *                        city:"城市",
     *                        district:"区域"
     *                     }]
     *                     }
     */
    @ApiOperation(value=" 筛选服务列表")
    @PostMapping("/service/select")
    public Result searchByCondition(@RequestBody ShopServiceDto shopServiceDto){
        return shopGoodsService.searchByCondition(shopServiceDto);
    }

    /**
     * @api {POST} /shop/goods 增加商家商品
     * @apiGroup ShopGoods
     * @apiVersion 0.0.1
     * @apiDescription 增加商家商品
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userId:"1",
     *                      title:"标题是",
     *                      content:"商品介绍",
     *                      province:"省",
     *                      city:"市",
     *                      district:"区",
     *                      priceNow:"价格",
     *                      cdType:"商品类型 1:代表否，2：代表二手",
     *                      condition:"新旧程度",
     *                      cdCount:"商品数量",
     *                      cdColor:"商品颜色,多种颜色以逗号分隔,
     *                      cdSize:"商品规格,多种规格以逗号分隔",
     *                      imgId:商品图片Id,
     *                      discountFee:打折费用,
     *                      expressFee:物流费用
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value=" 增加商家商品")
    @PostMapping("/goods")
    public Result addShopGoods(@RequestBody Commodity commodity){
        return commodityService.addCommodity(commodity);
    }

    /**
     * @api {PUT} /shop/goods 修改商家商品
     * @apiGroup ShopGoods
     * @apiVersion 0.0.1
     * @apiDescription 修改商家商品
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      cdId:"商品信息主键",
     *                      title:"标题",
     *                      content:"商品介绍",
     *                      priceNow:"价格",
     *                      cdType:"商品类型 1:代表否，2：代表二手",
     *                      cdCount:"商品数量",
     *                      imgId:商品图片,
     *                      discountFee:"打折费用",
     *                      expressFee:"物流费用",
     *                      cdCount:"商品数量",
     *                      cdColor:"商品颜色,多种颜色以逗号分隔,
     *                      cdSize:"商品规格,多种规格以逗号分隔"
     *                   }
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value=" 改商家商品")
    @PutMapping("/goods")
    public Result changeCommodity(@RequestBody Commodity commodity){
        return commodityService.changeCommodity(commodity);
    }

    /**
     * 查询发布的服务信息
     */
    @ApiOperation(value="查询发布的同城服务信息")
    @GetMapping("/service/{userId}/{start}/{end}")
    public Result getUserShopGoods(@PathVariable Integer userId,@PathVariable Integer start,@PathVariable Integer end){
        return shopGoodsService.searchUserService(userId, start, end);
    }

    /**
     * 删除发布的服务信息
     */
    @ApiOperation(value="删除发布的同城服务信息")
    @DeleteMapping("service/{id}")
    public Result deleteUserShopGoods(@PathVariable Integer id){
     return shopGoodsService.deleteById(id);
    }

    /**
     * 获取同城推荐
     */
    @ApiOperation(value="获取同城推荐")
    @GetMapping("/getRECService/{sgId}")
    public Result getRECService(@PathVariable Integer sgId){
        return shopGoodsService.getRECService(sgId);
    }

    /**
     * 通过市获取同城区域
     */
    @ApiOperation(value="通过市获取同城区域")
    @GetMapping("/getDisByCity/{city}")
    public Result getDisByCity(@PathVariable String city){
        return shopGoodsService.getDisByCity(city);
    }
}
