package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbInformation;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chengge on 2019/3/12.
 */
@Api("监听订单接口")
@RestController
@RequestMapping("/listenOrder")
public class TbListenOrderController extends BaseController {

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "乘客订单端点击完成订单", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "passengerOrderCode", value = "乘客订单的唯一标识code", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping(value = "/addListenOrder")
    public Result addListenOrder(String passengerOrderCode) {

        return tbListenOrderService.addListenOrder(passengerOrderCode);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "乘客端根据乘客订单的code轮询监听司机是否有对订单的操作", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "passengerOrderCode", value = "乘客订单的唯一标识code", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping(value = "/passengerListenOrder")
    public Result passengerListenOrder(String passengerOrderCode) {

        return tbListenOrderService.passengerListenOrder(passengerOrderCode);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "司机端根据司机订单的code轮询监听是否有乘客点击完成订单", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "driverOrderCode", value = "司机订单的唯一标识code", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping(value = "/driverListenOrder")
    public Result driverListenOrder(String driverOrderCode) {

        return tbListenOrderService.driverListenOrder(driverOrderCode);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "司机端弹框是否完成乘客端发起的完成订单请求", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "driverOrderCode", value = "司机订单的唯一标识code", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "state", value = "是否完成的状态 1同意完成 2拒绝完成", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping(value = "/updateListenOrder")
    public Result updateListenOrder(String driverOrderCode, String state) {

        return tbListenOrderService.updateListenOrder(driverOrderCode, state);
    }
}
