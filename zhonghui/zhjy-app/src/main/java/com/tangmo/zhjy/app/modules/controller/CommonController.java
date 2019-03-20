package com.tangmo.zhjy.app.modules.controller;


import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 18/1/15
 * @description 通用控制层
 */
@Api("通用控制层")
@RestController
@RequestMapping("/common")
public class CommonController{

    @Resource
    private CommonService commonService;

    /**
     * @api {GET} /common/district 获取行政区域
     * @apiGroup Common
     * @apiVersion 0.0.1
     * @apiDescription 获取行政区域
     * @apiParamExample {json} 请求样例：
     *          {
     *              city: 西安
     *          }
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success",
     *                     "data":{
     *                     [{
     *                        id: "主键",
     *                        district:"区",
     *                     ...]
     *                     }
     */
    @ApiOperation(value=" 获取行政区域")
    @GetMapping("/district/{city}")
    public Result getDistrict(@PathVariable String city){
        return commonService.searchDistrict(city);
    }


    @ApiOperation(value="获取系统消息")
    @GetMapping("/getMessage/{userId}")
    public Result getMessage(@PathVariable Integer userId,@PathVariable Integer pageNo,@PathVariable Integer pageSize){
        return commonService.getMessage(userId,pageNo,pageSize);
    }
}
