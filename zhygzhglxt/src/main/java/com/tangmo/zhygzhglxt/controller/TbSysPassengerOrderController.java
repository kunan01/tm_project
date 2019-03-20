package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;

import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api("后台订单接口")
@RestController
@RequestMapping("/passengerOrder")
public class TbSysPassengerOrderController extends BaseController {


    @ApiOperation(value = "查询指定订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderCode", value = "订单唯一标识", dataType = "string", required = false, paramType = "query")
    })
    @GetMapping("/jtQueryByListByCode")
    public Result jtQueryByListByCode(String orderCode) {

        return tbPassengerOrderService.jtQueryByListByCode(orderCode);
    }

    @ApiOperation(value = "删除指定订单（假删）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderCode", value = "订单唯一标识", dataType = "string", required = false, paramType = "query")
    })
    @PutMapping("/jtDelete")
    public Result jtDelete(String orderCode) {

        return tbPassengerOrderService.jtDelete(orderCode);
    }


}
