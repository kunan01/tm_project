package com.tangmo.zhjy.app.modules.controller;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.ShopVerify;
import com.tangmo.zhjy.app.modules.service.ShopVerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 18/3/9
 * @description
 */

@Api("商家商铺审核信息")
@RestController
@RequestMapping("/shop")
public class ShopVerifyController{
    @Resource
    private ShopVerifyService shopVerifyService;
    /**
     * @api {POST} /shop/verify 增加商家审核信息
     * @apiGroup ShopVerify
     * @apiVersion 0.0.1
     * @apiDescription 增加商家审核信息
     * @apiParam {ShopVerify} shopVerify 商家审核对象
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      userId:"用户Id",
     *                      userName:"代表人姓名",
     *                      idNumber:"身份证号",
     *                      mobile:"手机号",
     *                      shopName:"店铺名称",
     *                      shopIntro:"店铺描述",
     *                      shopAddress:"店铺地址",
     *                      idFrontImg:"身份证正面照",
     *                      idRearImg:"身份证反面照",
     *                      logoImg:"logo图片",
     *                      licenseImg:"营业执照照片"
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value=" 增加商家审核信息")
    @PostMapping("/verify")
    public Result addShopVerify(@RequestBody ShopVerify shopVerify){
        if(shopVerify == null){
            return new Result(ResultCode.FAIL,"信息不完整");
        }
        if(shopVerify.getLogoImg() == null || shopVerify.getIdRearImg() == null
                || shopVerify.getIdFrontImg() ==null || shopVerify.getLicenseImg() == null){
            return new Result(ResultCode.FAIL,"信息不完整");
        }
        return shopVerifyService.addShopVerify(shopVerify);
    }

    /**
     * @api {PUT} /shop/info 修改商家信息
     * @apiGroup ShopVerify
     * @apiVersion 0.0.1
     * @apiDescription 修改商家信息
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      svId: 信息主键,
     *                      userId:"用户Id",
     *                      shopName:"店铺名称",
     *                      shopIntro:"店铺描述",
     *                      shopAddress:"店铺地址",
     *                   }
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value=" 修改商家信息")
    @PutMapping("/info")
    public Result changeShopVerify(@RequestBody ShopVerify shopVerify){
        return shopVerifyService.changeSvInfo(shopVerify);
    }

    /**
     * @api {GET} /shop/info/{userId} 查询商铺审核信息
     * @apiGroup ShopVerify
     * @apiVersion 0.0.1
     * @apiDescription 查询商铺审核信息
     * @apiParamExample {json} 请求样例：
     *  /shop/info/1
     * @apiParamExample {json} 返回样例:
     *                   {
     *                      userId:"用户Id",
     *                      userName:"代表人姓名",
     *                      idNumber:"身份证号",
     *                      mobile:"手机号",
     *                      shopName:"店铺名称",
     *                      shopIntro:"店铺描述",
     *                      shopAddress:"店铺地址",
     *                      idFrontImg:"身份证正面照",
     *                      idRearImg:"身份证反面照",
     *                      logoImg:"logo图片",
     *                      licenseImg:"营业执照照片"
     *                   }
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value=" 查询商铺审核信息")
    @GetMapping("/info/{userId}")
    public Result searchShopInfo(@PathVariable Integer userId){
        return shopVerifyService.searchSvInfo(userId);
    }

}
