package com.tangmo.zhygzhglxt.controller;


import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Api("支付宝支付")
@RestController
@RequestMapping("/alipay")
public class AlipayController extends BaseController {


    @ApiOperation(value = "支付宝支付订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户的唯一标识", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "passengerOrderCode", value = "乘客订单的唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/pay/order")
    public Result payOrder(String userCode, String passengerOrderCode) {
        return aliPayService.payOrder(userCode, passengerOrderCode);
    }

    @ApiOperation(value = "支付宝的支付回调接口(订单)")
    @PostMapping("/order/notify_url")
    public String notify_url(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("==================支付宝异步返回支付结果开始");
        //1.从支付宝回调的request域中取值
        //获取支付宝返回的参数集合
        Map<String, String[]> aliParams = request.getParameterMap();
        //用以存放转化后的参数集合
        Map<String, String> conversionParams = new HashMap<>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext(); ) {
            String key = iter.next();
            String[] values = aliParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "uft-8");
            conversionParams.put(key, valueStr);
        }
        System.out.println("==================返回参数集合：" + conversionParams);
        String status = aliPayService.notify(conversionParams);
        return status;
    }


    @ApiOperation(value = "支付宝的App回调接口")
    @PostMapping("/order/return_url")
    public String return_url(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("==================支付宝的App回调接口开始");
        return "";
    }
}
