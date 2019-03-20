package com.tangmo.zhjy.system.modules.controller;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.Commodity;
import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import com.tangmo.zhjy.system.modules.service.CommodityService;
import com.tangmo.zhjy.system.utils.Transfor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
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
public class AppCommodityController extends BaseController {

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
     * @api {PUT} /commodity 审核app发布的商品信息
     */
    @ApiOperation(value="审核商品",notes="")
    @PutMapping("/verify/{state}/{cdId}")
    public Result changeCommodity(@PathVariable Integer state,@PathVariable Integer cdId){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("审核商品");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
        return commodityService.changeCommodity(state,cdId);
    }

    /**
     * @api {PUT} /推送精品商品
     */
    @ApiOperation(value="推送/精品商品",notes="")
    @PutMapping("/isQuality/{isQuality}/{cdId}")
    public Result isQuality(@PathVariable Integer isQuality,@PathVariable Integer cdId){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("推送/精品商品");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
        return commodityService.isQuality(isQuality,cdId);
    }

    @ApiOperation(value="获取商品列表信息",notes="")
    @GetMapping("/list/{cdType}/{state}/{pageSize}/{pageNo}/{name}")
    public Result getCommodityList(@PathVariable Integer cdType,@PathVariable Integer state,@PathVariable Integer pageSize, @PathVariable Integer pageNo, @PathVariable String name){
        return commodityService.searchCdList(cdType,state,pageSize,pageNo,name);
    }

    @ApiOperation(value="获取商品详情",notes="")
    @GetMapping("/detail/{cdId}")
    public Result getCdDetail(@PathVariable Integer cdId){
        return commodityService.getCommodityDetail(cdId);
    }


    @ApiOperation(value="删除商品信息",notes="")
    @DeleteMapping("/delete/{cdId}")
    public Result delCommodity(@PathVariable Integer cdId){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean = (SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("删除商品信息");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
        return commodityService.delCommodity(cdId);
    }

}
