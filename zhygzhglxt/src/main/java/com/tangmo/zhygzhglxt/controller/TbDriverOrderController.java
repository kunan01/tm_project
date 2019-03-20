package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbDriverOrder;
import com.tangmo.zhygzhglxt.entity.TbFeedBack;
import com.tangmo.zhygzhglxt.entity.dto.OrderDto;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chengge on 2018/10/24.
 */
@Api("车主订单")
@RestController
@RequestMapping("/driverOrder")
public class TbDriverOrderController extends BaseController {

    @ApiOperation(value = "添加车主订单信息", notes = "")
    @ApiImplicitParam(name = "tbDriverOrder", value = "车主订单实体类", required = true, dataType = "TbDriverOrder")
    @PostMapping("/addDriverOrder")
    public Result addDriverOrder(@RequestBody TbDriverOrder tbDriverOrder) {

        return tbDriverOrderService.addDriverOrder(tbDriverOrder);
    }

    @ApiOperation(value = "车主发起线上支付", notes = "")
    @ApiImplicitParam(name = "orderDto", value = "订单Dto", required = true, dataType = "OrderDto")
    @PostMapping("/updateOrderPayWay")
    public Result updateOrderPayWay(@RequestBody OrderDto orderDto) {

        return tbDriverOrderService.updateOrderPayWay(orderDto);
    }

    @ApiOperation(value = "车主端实时查询订单是否完成，线下是否付钱！", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "driverOrderCode", value = "车主订单唯一code", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selStateByDriverOrderCode")
    public Result selStateByDriverOrderCode(String driverOrderCode) {

        return tbDriverOrderService.selStateByDriverOrderCode(driverOrderCode);
    }

    /*
     * 改变车主订单的状态
     * */
    @ApiOperation(value = "司机端改变车主订单的状态（0待完成 1完成 2取消）", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "状态", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/updateDriverOrderState")
    public Result updateDriverOrderState(String state, String code) {

        return tbDriverOrderService.updateDriverOrderState(state, code);
    }


    @ApiOperation(value = "司机端车主查询乘客所有单子")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "endAbout", value = "0预约 1实时", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "orderState", value = "0未接 1已接 2完成 3取消", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "tbParmId", value = "网约车1015,货车1016，客运班车1017,同乡客运1018", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "userCode", value = "用户的唯一标识code", dataType = "string", required = false, paramType = "query")
    })
    @GetMapping("/jtQuery")
    public Result jtQuery(String endAbout, String orderState, String tbParmId, String userCode) {


        return tbDriverOrderService.jtQuery(endAbout, orderState, tbParmId, userCode);
    }

    @ApiOperation(value = "车主查询单子详情")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "driverOrderCode", value = "车主订单唯一code", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/jtQueryOrderCode")
    public Result jtQueryOrderCode(String driverOrderCode) {

        return tbDriverOrderService.jtQueryOrderCode(driverOrderCode);
    }

    @ApiOperation(value = "根据司机订单的唯一标识driverCde查询同乡客运的订单路线路")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "driverOrderCode", value = "车主订单唯一code", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selectByDriverOrderCode")
    public Result selectByDriverOrderCode(String driverOrderCode) {

        return tbDriverOrderService.selectByDriverOrderCode(driverOrderCode);
    }

    @ApiOperation(value = "车主查询自己所有单子状态")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "driverId", value = "司机id", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "driverOrderState", value = "0 未完成  1完成", dataType = "string", required = false, paramType = "query"),
    })
    @GetMapping("/jtQyeryById")
    public Result jtQyeryById(String driverId, String driverOrderState) {

        return tbDriverOrderService.jtQyeryById(driverId, driverOrderState);
    }


}
