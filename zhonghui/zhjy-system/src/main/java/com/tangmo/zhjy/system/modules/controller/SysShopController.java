package com.tangmo.zhjy.system.modules.controller;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import com.tangmo.zhjy.system.modules.service.SysShopService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 2018/4/29
 * @description 商家店铺审核
 */
@RestController
@RequestMapping("/shop")
public class SysShopController extends BaseController {

    @Resource
    private SysShopService sysShopService;
    /**
     * @api {GET} /system/shop/unverify 查询未审核商铺信息
     * @apiGroup Admin
     * @apiVersion 0.0.1
     * @apiDescription 查询未审核商铺信息
     * @apiParamExample {json} 请求样例：
     *  /system/shop/unverify
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
    @GetMapping("/unverify/{pageNo}/{pageSize}")
    public Result searchUnverify(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
        return sysShopService.searchSvInfo((byte) 0,pageNo,pageSize);
    }

    /**
     * @api {GET} /system/shop/verify 查询已审核商铺信息
     * @apiGroup Admin
     * @apiVersion 0.0.1
     * @apiDescription 查询已审核商铺信息
     * @apiParamExample {json} 请求样例：
     *  /system/shop/verify
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
    @GetMapping("/verify/{pageNo}/{pageSize}")
    public Result searchVerify(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
        return sysShopService.searchSvInfo((byte) 1,pageNo,pageSize);
    }

    @GetMapping("/verifyByUserId/{userId}")
    public Result verifyByUserId(@PathVariable("userId") Integer userId){
        return sysShopService.verifyByUserId(userId);
    }

    /**
     * @api {PUT} /system/shop/verify/{svId} 通过商家审核
     * @apiGroup Admin
     * @apiVersion 0.0.1
     * @apiDescription 通过商家审核
     * @apiParamExample {json} 请求样例:
     *              /shop/verify/1
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @PutMapping("/verify/{svId}")
    public Result verifyInfo(@PathVariable Integer svId){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("通过商家审核");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
        return sysShopService.updateSvState(svId, (byte) 1);
    }

    /**
     * @api {PUT} /system/shop/verify/{svId} 驳回商家审核
     * @apiGroup Admin
     * @apiVersion 0.0.1
     * @apiDescription 通过商家审核
     * @apiParamExample {json} 请求样例:
     *              /shop/verify/1
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @PutMapping("/unverify/{svId}")
    public Result unVerifyInfo(@PathVariable Integer svId){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("驳回商家审核");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
        return sysShopService.updateSvState(svId, (byte) 2);
    }

}
