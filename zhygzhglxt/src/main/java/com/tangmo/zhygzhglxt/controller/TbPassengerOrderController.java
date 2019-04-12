package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbPassengerOrder;
import com.tangmo.zhygzhglxt.entity.dto.OrderDto;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengge on 2018/10/24.
 */
@Api("乘客订单")
@RestController
@RequestMapping("/passengerOrder")
public class TbPassengerOrderController extends BaseController {

    @ApiOperation(value = "添加乘客订单信息", notes = "")
    @ApiImplicitParam(name = "tbPassengerOrder", value = "乘客订单实体类", required = true, dataType = "TbPassengerOrder")
    @PostMapping("/addPassengerOrder")
    public Result addPassengerOrder(@RequestBody TbPassengerOrder tbPassengerOrder) {

        return tbPassengerOrderService.addPassengerOrder(tbPassengerOrder);
    }

    @ApiOperation(value = "后台查询乘客的全部订单", notes = "")
    @ApiImplicitParam(name = "orderDto", value = "订单条件实体类", required = true, dataType = "OrderDto")
    @PostMapping("/selOrderByCondition")
    public Result addPassengerOrder(@RequestBody OrderDto orderDto) {

        return tbPassengerOrderService.selOrderByCondition(orderDto);
    }

    @ApiOperation(value = "根据用户的唯一标识模糊查找用户所有订单")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "订单号 出发地 目的地", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "state", value = "0未接 1已接 2完成 3取消", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "userCode", value = "用户唯一标识", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "endAbout", value = "0预约 1实时", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selPassOrderByUserCode")
    public Map selPassOrderByUserCode(String name, String state, String userCode, String endAbout, Integer pageSize, Integer pageNo) {

        Result result = tbPassengerOrderService.selPassOrderByUserCode(name, state, userCode, endAbout, pageSize, pageNo);
        PageInfo pageInfo = (PageInfo) result.getData();
        Map map = new HashMap();
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("data", pageInfo.getList());
        map.put("pageNo", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @ApiOperation(value = "根据用户的唯一标识模糊查找用户的当前订单和历史订单")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模糊参数", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "userCode", value = "用户唯一标识", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "0当前，1历史", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selPassOrderByUserCodeAndType")
    public Map selPassOrderByUserCodeAndType(String name, String userCode, String type, Integer pageSize, Integer pageNo) {

        Result result = tbPassengerOrderService.selPassOrderByUserCodeAndType(name, userCode, type, pageSize, pageNo);
        PageInfo pageInfo = (PageInfo) result.getData();
        Map map = new HashMap();
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("data", pageInfo.getList());
        map.put("pageNo", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        map.put("total", pageInfo.getTotal());
        return map;
    }


    @ApiOperation(value = "乘客修改订单状态")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderCode", value = "订单唯一标识", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "orderState", value = "0未接 1已接 2完成 3取消", dataType = "string", required = false, paramType = "query")
    })
    @PutMapping("/jtUpdateById")
    public Result jtUpdateById(String orderCode, String orderState) {

        return tbPassengerOrderService.jtUpdateById(orderCode, orderState);
    }

    @ApiOperation(value = "后台查询所有订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "开始地，结束地", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "orderState", value = "0未接 1已接 2完成 3取消", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "endAbout", value = "0预约  1实时", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", defaultValue = "10", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "开始时间（2019-04-07）", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间（2019-04-11）", dataType = "String", paramType = "query")
    })
    @GetMapping("/jtQueryByList")
    public Map jtQueryByList(String name, String orderState, String endAbout, Integer pageSize, Integer pageNo, String startDate, String endDate) {

        Result result = tbPassengerOrderService.jtQueryByList(name, orderState, endAbout, pageSize, pageNo, startDate, endDate);
        PageInfo pageInfo = (PageInfo) result.getData();
        Map map = new HashMap();
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("data", pageInfo.getList());
        map.put("pageNo", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @ApiOperation(value = "根据用户的唯一标识查找是否有正在进行中的订单")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户的唯一标识code", dataType = "string", required = false, paramType = "query")
    })
    @GetMapping("/selOrderStateByCode")
    public Result selOrderStateByCode(String userCode) {

        return tbPassengerOrderService.selOrderStateByUserCode(userCode);
    }

    @ApiOperation(value = "根据乘客订单的唯一标识查询订单详情（可包含司机信息）")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "passengerOrderCode", value = "乘客订单唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selOrderDetailByOrderCode")
    public Result selOrderDetailByOrderCode(String passengerOrderCode) {

        return tbPassengerOrderService.selOrderDetailByOrderCode(passengerOrderCode);
    }

    @ApiOperation(value = "乘客端实时查询订单是否有被接单")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "passengerOrderCode", value = "乘客订单唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selOrderStateByOrderCode")
    public Result selOrderStateByOrderCode(String passengerOrderCode) {

        return tbPassengerOrderService.selOrderStateByOrderCode(passengerOrderCode);
    }

    @ApiOperation(value = "乘客端取消订单")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "passengerOrderCode", value = "乘客订单唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/cancelByOrderCode")
    public Result cancelByOrderCode(String passengerOrderCode) {

        return tbPassengerOrderService.cancelByOrderCode(passengerOrderCode);
    }

    @ApiOperation(value = "乘客对司机订单的点评（评价）")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "passengerOrderCode", value = "乘客订单唯一标识", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "opinion", value = "意见", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "rating", value = "好评度", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/ratingByOrderCode")
    public Result ratingByOrderCode(String passengerOrderCode, String opinion, String rating) {

        return tbPassengerOrderService.ratingByOrderCode(passengerOrderCode, opinion, rating);
    }

    @ApiOperation(value = "根据乘客订单的唯一标识查询订单的时间和当前时间")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "passengerOrderCode", value = "乘客订单唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selTimeByOrderCode")
    public Result selTimeByOrderCode(String passengerOrderCode) {

        return tbPassengerOrderService.selTimeByOrderCode(passengerOrderCode);
    }


    @ApiOperation(value = "乘客端在被接单后实时查询该订单是否完成，是否是发起线上支付")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "passengerOrderCode", value = "乘客订单唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selPayWayByOrderCode")
    public Result selPayWayByOrderCode(String passengerOrderCode) {

        return tbPassengerOrderService.selPayWayByOrderCode(passengerOrderCode);
    }


    @ApiOperation(value = "订单统计：后台根据年月查询订单的完成量和取消量")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timeDate", value = "年月 2018-11", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selOrderByDate")
    public Result selOrderByDate(String timeDate) {

        return tbPassengerOrderService.selOrderByDate(timeDate);
    }

    @ApiOperation(value = "订单统计：后台根据年月查询订单 实时单完成单量 预约单完成单量 取消的实时单量 取消的预约单单量")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timeDate", value = "年月 2018-11", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selOrderCountByDate")
    public Result selOrderCountByDate(String timeDate) {

        return tbPassengerOrderService.selOrderCountByDate(timeDate);
    }

    @ApiOperation(value = "订单统计：后台根据年月查询订单 这个月内每天的用户的单量的统计和完成单量")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timeDate", value = "年月 2018-11", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selOrderCountListByDate")
    public Result selOrderCountListByDate(String timeDate) {

        return tbPassengerOrderService.selOrderCountListByDate(timeDate);
    }

    @ApiOperation(value = "订单统计：根据开始时间和结束时间查询每日的订单统计量(列表)")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始时间2018-11-10", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间2018-12-30", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selOrderByStartDateEndDate")
    public Map selOrderByStartDateEndDate(String startDate, String endDate, Integer pageSize, Integer pageNo) {

        Result result = tbPassengerOrderService.selOrderByStartDateEndDate(startDate, endDate, pageSize, pageNo);
        PageInfo pageInfo = (PageInfo) result.getData();
        Map map = new HashMap();
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("data", pageInfo.getList());
        map.put("pageNo", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        map.put("total", pageInfo.getTotal());

        return map;
    }

    @ApiOperation(value = "订单统计：根据开始时间和结束时间且订单类型查询每日的通乡客运的订单统计量(列表)")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模糊参数", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "开始时间2018-11-10", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间2018-12-30", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "orderState", value = "订单状态", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "carNumber", value = "车牌号", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", defaultValue = "10", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", defaultValue = "1", paramType = "query")
    })
    @GetMapping("/selOrderByCountry")
    public Map selOrderByCountry(String name, String startDate, String endDate, String orderState, String carNumber, Integer pageSize, Integer pageNo) {

        Result result = tbPassengerOrderService.selOrderByCountry(name, startDate, endDate, orderState, carNumber, pageSize, pageNo);
        PageInfo pageInfo = (PageInfo) result.getData();
        Map map = new HashMap();
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("data", pageInfo.getList());
        map.put("pageNo", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        map.put("total", pageInfo.getTotal());

        return map;
    }


}
