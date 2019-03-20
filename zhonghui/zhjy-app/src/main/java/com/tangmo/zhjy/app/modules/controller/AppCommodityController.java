package com.tangmo.zhjy.app.modules.controller;

import com.tangmo.zhjy.app.modules.bean.Commodity;
import com.tangmo.zhjy.app.modules.dto.CommodityDto;
import com.tangmo.zhjy.app.modules.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.tangmo.zhjy.app.Result;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author boge
 * @date 18/4/4
 * @description
 */
@Api("商品信息")
@RestController
@RequestMapping("/commodity")
public class AppCommodityController {
    @Resource
    private CommodityService commodityService;

    /**
     * @api {POST} /commodity/add 增加商品信息
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiDescription 增加商品信息
     * @apiParam {Commodity} commodity 商品信息对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userId:"用户id",
     *                      title:"标题是什么",
     *                      content:"内容是什么",
     *                      province:"省",
     *                      city:"市",
     *                      district:"区",
     *                      address："详细地址"
     *                      pricePre:"原价(发布商城商品所用)",
     *                      priceNow:"现价",
     *                      cdType:"商品类型 1:代表商城商品，2：代表二手",
     *                      cdClass:"商品分类id",
     *                      cdCount:"商品数量(发布商城商品所用)",
     *                      expressFee:"物流费用(发布商城商品所用)",
     *                      cdSize:"商品规格(发布商城商品所用)",
     *                      imgId:商品图片Id,
     *                      phone:"联系电话(发布二手商品所用)"
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="增加商品信息",notes="")
    @PostMapping("/add")
    public Result addCommodity(@Valid @RequestBody Commodity commodity)
    {
        return commodityService.addCommodity(commodity);
    }

    /**
     * @api {POST} /commodity/img/{userId} 上传商品图片
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiDescription 上传商品图片
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      file:"图片文件"
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="上传商品图片",notes="")
    @PostMapping("/img/{userId}")
    public Result addCdImg(@PathVariable Integer userId, MultipartFile file){
        return commodityService.addCdImg(userId, file);
    }

    /**
     * @api {PUT} /commodity 修改商品信息
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiDescription 增加商品信息
     * @apiParam {Commodity} commodity 商品信息对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      cdId:"商品信息主键",
     *                      title:"标题是什么",
     *                      content:"内容是什么",
     *                      province:"省",
     *                      city:"市",
     *                      district:"区",
     *                      address："详细地址"
     *                      pricePre:"原价(发布商城商品所用)",
     *                      priceNow:"现价",
     *                      cdClass:"商品分类id",
     *                      cdCount:"商品数量(发布商城商品所用)",
     *                      expressFee:"物流费用(发布商城商品所用)",
     *                      cdSize:"商品规格(发布商城商品所用)",
     *                      imgId:商品图片Id,
     *                      phone:"联系电话(发布二手商品所用)"
     *                   }
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="修改商品信息",notes="")
    @PutMapping("")
    public Result changeCommodity(@RequestBody Commodity commodity){
        return commodityService.changeCommodity(commodity);
    }

    /**
     * @api {GET} /commodity/list/{start}/{end} 获取指定类型商品
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiParam {int} start 分页起始索引
     * @apiParam {int} end 查询列表长度
     * @apiDescription 获取指定类型商品 区分全新汽配,二手汽配...
     * @apiParamExample {json} 请求样例：
     *  /commodity/list/1/10
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                     [{
     *                        cdId: 1,
     *                        title: "商品信息标题1",
     *                        imgId:"商品图片",
     *                        province:"省",
     *                        city:"市",
     *                        district:"区",
     *                        content: "商品信息内容1",
     *                        pricePre:"商品原价1",
     *                        priceNow:"商品现价1",
     *                        condition:"新旧程度",
     *                        cdType:"商品类型1",},
     *                     {
     *                        cdId: 2,
     *                        title: "商品信息标题2",
     *                        imgId:"商品图片",
     *                        province:"省",
     *                        city:"市",
     *                        district:"区",
     *                        content: "商品信息内容2",
     *                        pricePre:"商品原价2",
     *                        condition:"新旧程度",
     *                        priceNow:"商品现价2",
     *                        cdType:"商品类型2"},
     *                     }]
     *                     }
     */
    @ApiOperation(value="获取全部商品",notes="")
    @GetMapping("/list/{start}/{end}")
    public Result getCommodityList(@PathVariable Integer start, @PathVariable Integer end){
        return commodityService.searchCdList(start,end);
    }

    /**
     * @api {GET} /commodity/list/{start}/{end} 获取二手商品
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiParam {int} start 分页起始索引
     * @apiParam {int} end 查询列表长度
     * @apiDescription 获取二手商品
     */
    @ApiOperation(value="获取二手商品",notes="")
    @GetMapping("/listTwo/{start}/{end}")
    public Result getCommodityListTwo(@PathVariable Integer start, @PathVariable Integer end){
        return commodityService.selectListTwo(start,end);
    }

    /**
     * @api {GET} /commodity/user/{userId}/{start}/{end} 获取指定用户的二手商品
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiParam {int} start 分页起始索引
     * @apiParam {int} end 查询列表长度
     * @apiParam {int} userId 商品类型
     * @apiDescription 获取指定用户的商品
     * @apiParamExample {json} 请求样例：
     *  /commodity/user/1/1/10
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                     [{
     *                        cdId: 1,
     *                        title: "商品信息标题1",
     *                        imgId:"商品图片",
     *                        province:"省",
     *                        city:"市",
     *                        district:"区",
     *                        content: "商品信息内容1",
     *                        pricePre:"商品原价1",
     *                        priceNow:"商品现价1",
     *                        condition:"新旧程度",
     *                        cdType:"商品类型1"},
     *                     {
     *                        cdId: 2,
     *                        title: "商品信息标题2",
     *                        imgId:"商品图片",
     *                        province:"省",
     *                        city:"市",
     *                        district:"区",
     *                        content: "商品信息内容2",
     *                        pricePre:"商品原价2",
     *                        priceNow:"商品现价2",
     *                        condition:"新旧程度",
     *                        cdType:"商品类型2"},
     *                     }]
     *                     }
     */
    @ApiOperation(value="获取指定用户的二手商品",notes="")
    @GetMapping("/user/{userId}/{start}/{end}")
    public Result getUserCdList(@PathVariable Integer userId,@PathVariable Integer start,@PathVariable Integer end){
        return commodityService.searchUserCdList(userId,start,end);
    }

    /**
     * @api {GET} /commodity/detail/{userId}/{cdId} 获取商品详情
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiParam {int} cdId 商品主键
     * @apiParam {int} userId 商品类型
     * @apiDescription 获取商品详情
     * @apiParamExample {json} 请求样例：
     *  /commodity/detail/1/1
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                      title:"标题是什么",
     *                      content:"内容是什么",
     *                      imgId:"图片base64编码",
     *                      province:"省",
     *                      city:"市",
     *                      district:"区",
     *                      pricePre:"原价",
     *                      priceNow:"现价",
     *                      cdType:"商品类型",
     *                      condition:"新旧程度",
     *                      cdCount:"商品数量",
     *                      cdColor:"商品颜色,多种颜色以逗号分隔,
     *                      cdSize:"商品规格,多种规格以逗号分隔"
     *                   }
     *                   }
     */
    @ApiOperation(value="获取商品详情",notes="")
    @GetMapping("/detail/{cdId}")
    public Result getCdDetail(@PathVariable Integer cdId){
        return commodityService.getCommodityDetail(cdId);
    }

    /**
     * @api {DELETE} /commodity/{cdId} 删除商品信息
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiParam {int} cdId 商品表主键
     * @apiDescription 删除用户地址信息
     * @apiParamExample {json} 请求样例：
     *  /commodity/12
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {DELETE} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="删除商品信息",notes="")
    @DeleteMapping("/{cdId}")
    public Result delCommodity(@PathVariable Integer cdId){
        return commodityService.delCommodity(cdId);
    }



    /**
     * @api {PUT} /commodity/star 商品增加好评
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiDescription 商品增加好评
     * @apiParam {Commodity} commodity 商品对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      cdId:"商品Id",
     *                      userId:"用户Id"
     *                   }
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="商品增加好评",notes="")
    @PutMapping("/star")
    public Result addStar(Commodity commodity){
        return commodityService.addCdStar(commodity.getCdId(),commodity.getUserId());
    }

    /**
     * @api {POST} /commodity/select 通过条件筛选商品
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiDescription 通过条件筛选商品
     * @apiParam {CommodityDto} commodityDto 商品查询对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      cdType:商品类型,必填(1商城商品。2二手商品),
     *                      city:"城市",
     *                      district:"区域",
     *                      cdClass:"商品分类",
     *                      price:"价格排序 0,正序,1倒序"
     *                      priceStart:"价格开始区间",
     *                      priceEnd:"价格结束区间",
     *                      start:"分页开始索引,必填",
     *                      end:"分页查询长度,必填",
     *                      star:"好评排序,0正序,1倒序"
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {和返回商品信息字段相同}
     */
    @ApiOperation(value="通过条件筛选商品",notes="")
    @PostMapping("/select")
    public Result getByCondition(@RequestBody CommodityDto commodityDto){
        return commodityService.selectByCondition(commodityDto);
    }

    /**
     * @api {PUT} /remove/{cdId} 商品下架
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiDescription 商品下架
     * @apiParamExample {json} 请求样例:
     *             /commodity/remove/1
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="商品下架",notes="")
    @PutMapping("/remove/{cdId}")
    public Result removeCommodity(@PathVariable Integer cdId){
        return commodityService.changeCdState(cdId, (byte) 0);
    }

    /**
     * @api {PUT} /sell/{cdId} 商品上架
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiDescription 商品上架
     * @apiParamExample {json} 请求样例:
     *             /commodity/sell/1
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="商品上架",notes="")
    @PutMapping("/sell/{cdId}")
    public Result sellCommodity(@PathVariable Integer cdId){
        return commodityService.changeCdState(cdId,(byte)1);
    }

    /**
     * @api {GET} /commodity/record/{userId}/{state}/{start}/{end} 查询上架/下架商品销售记录
     * @apiGroup Commodity
     * @apiVersion 0.0.1
     * @apiParam {int} start 分页起始索引
     * @apiParam {int} end 查询列表长度
     * @apiParam {int} userId 用户Id
     * @apiParam {int} state 0:下架  1:上架
     * @apiDescription 查询上架/下架商品销售记录
     * @apiParamExample {json} 请求样例：
     *  /commodity/record/1/1/1/10
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                     [{
     *                        cdId: 1,
     *                        title: "商品信息标题1",
     *                        imgId:"商品图片",
     *                        sellCount:"卖出数量",
     *                        priceNow:"商品现价1",
     *                        cdCount:"剩余数量"
     *                      },
     *                     {...}]
     *                     }
     */
    @ApiOperation(value="查询上架/下架商品销售记录",notes="")
    @GetMapping("/record/{userId}/{state}/{start}/{end}")
    public Result getSellRecourd(@PathVariable Integer userId,@PathVariable Byte state,
                                 @PathVariable Integer start,@PathVariable Integer end){
        return commodityService.searchSellRecord(userId, state, start, end);
    }


    @SuppressWarnings("rawtypes")
    @ApiOperation(value="查询商城商品分类",notes="查询商城商品分类")
    @GetMapping(value="/getCommentClassType")
    public Result getCommentClassType(){
        return commodityService.getCommentClassType();
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="查询二手商品分类",notes="查询商城商品分类")
    @GetMapping(value="/getCommentClassTwoType")
    public Result getCommentClassTwoType(){
        return commodityService.getCommentClassTwoType();
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="通过分类id和区域查询商品",notes="通过分类id查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNo",value="当前页,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="显示多少条参数,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="type",value="1商城商品/2二手商品,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="cId",value="商品分类Id",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="district",value="区",dataType="String",required=false,paramType="query")
    })
    @GetMapping(value="/getCommentListByCId")
    public Result getCommentListByCId(Integer type,Integer cId,Integer pageNo,Integer pageSize,String district){
        return commodityService.getCommentListByCId(type,cId,pageNo,pageSize,district);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="获取商品推荐",notes="获取商品推荐")
    @ApiImplicitParams({
            @ApiImplicitParam(name="cId",value="商品Id,必填：true",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value="/getCommentRecommended")
    public Result getCommentRecommended(Integer cId){
        return commodityService.getCommentRecommended(cId);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="获取二手商品推荐",notes="获取二手商品推荐")
    @ApiImplicitParams({
            @ApiImplicitParam(name="cId",value="商品Id,必填：true",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value="/getCommentRecommendedByTwo")
    public Result getCommentRecommendedByTwo(Integer cId){
        return commodityService.getCommentRecommendedByTwo(cId);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="通过市获取商品区域",notes="通过市获取商品区域")
    @ApiImplicitParams({
            @ApiImplicitParam(name="city",value="市，例如西安市,必填：true",dataType="String",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="1商品 2二手,必填：true",dataType="int",required=true,paramType="query")
    })
    @GetMapping(value="/getDisByCity")
    public Result getDisByCity(String city,Integer type){
        return commodityService.getDisByCity(city,type);
    }
}
