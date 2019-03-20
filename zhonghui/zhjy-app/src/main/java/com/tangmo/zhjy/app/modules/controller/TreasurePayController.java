package com.tangmo.zhjy.app.modules.controller;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.service.TreasurePayService;
import com.tangmo.zhjy.app.modules.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Api("支付宝支付")
@RestController
@RequestMapping("/treasure")
public class TreasurePayController {

    @Resource
    private PayService payService;

    @Resource
    private TreasurePayService treasurePayService;

    @ApiOperation(value="app支付宝支付订单")
    @GetMapping("/payTreasure/order/{userId}/{goId}")
    public Result payTreasure(@PathVariable Integer userId, @PathVariable String goId) {
        return treasurePayService.payOrder(userId, goId,0);
    }

    @ApiOperation(value="pc支付宝支付订单")
    @GetMapping("/payTreasurePC/order/{userId}/{goId}")
    public Result payTreasurePC(@PathVariable Integer userId, @PathVariable String goId) {
        return treasurePayService.payOrder(userId, goId,1);
    }

    @ApiOperation(value="支付宝的支付回调接口(订单)")
    @PostMapping("/order/notify_url")
    public String notify_url(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("==================支付宝异步返回支付结果开始");
        //1.从支付宝回调的request域中取值
        //获取支付宝返回的参数集合
        Map<String, String[]> aliParams = request.getParameterMap();
        //用以存放转化后的参数集合
        Map<String, String> conversionParams = new HashMap<>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext();) {
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
        System.out.println("==================返回参数集合："+conversionParams);
        String status = treasurePayService.notify(conversionParams);
        return status;
    }



    @ApiOperation(value="支付宝的App回调接口")
    @PostMapping("/order/return_url")
    public String return_url(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("==================支付宝的App回调接口开始");
        return "";
    }
}
