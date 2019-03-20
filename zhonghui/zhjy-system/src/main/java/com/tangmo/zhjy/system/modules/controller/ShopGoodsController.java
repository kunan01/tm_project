package com.tangmo.zhjy.system.modules.controller;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.ShopGoods;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import com.tangmo.zhjy.system.modules.service.ShopGoodsService;
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
@Api("店铺商品信息")
@RestController
@RequestMapping("/shop")
public class ShopGoodsController extends BaseController {

    @Resource
    private ShopGoodsService shopGoodsService;

    /**
     * @api {PUT} /shop/service 修改商家服务
     * @apiGroup ShopGoods
     * @apiVersion 0.0.1
     * @apiDescription 修改商家服务
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      sgId:"主键"
     *                      shopContent:"店铺介绍",
     *                      shopName:"店铺名"
     *                      price:"起步价",
     *                      serviceType:"3:汽车美容,5:故障快处,6:道路救援 8:需求通讯",
     *                      address:"店铺地址",
     *                      imgId:图片Id,
     *                      discountNote:打折活动,
     *                      mobile:手机号
     *                   }
     * @apiSuccess (success) {PUT} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {PUT} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value=" 修改商家服务")
    @PutMapping("/service/changeShopService")
    public Result changeShopService(@RequestBody ShopGoods shopGoods){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("修改商家服务");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
        return shopGoodsService.changeShopService(shopGoods);
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


    @ApiOperation(value=" 审核未通过的同城服务信息,相当于修改")
    @PutMapping("/service/verify/{state}/{sgId}")
    public Result audit(@PathVariable Integer state,@PathVariable Integer sgId){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("审核未通过的同城服务信息,相当于修改");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
        return shopGoodsService.audit(state,sgId);
    }

    @ApiOperation(value=" 查询同城服务信息:state 1:未审核 2审核通过 3审核失败")
    @GetMapping("/service/selectByState/{state}/{pageSize}/{pageNo}/{name}")
    public Result selectByState(@PathVariable Integer state,@PathVariable Integer pageSize, @PathVariable Integer pageNo, @PathVariable String name){
        return shopGoodsService.selectByState(state,pageSize,pageNo,name);
    }

    /**
     * 删除发布的服务信息
     */
    @ApiOperation(value="删除发布的服务信息")
    @DeleteMapping("service/deleteUserShopGoods/{id}")
    public Result deleteUserShopGoods(@PathVariable Integer id){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("删除发布的服务信息");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
     return shopGoodsService.deleteById(id);
    }

}
