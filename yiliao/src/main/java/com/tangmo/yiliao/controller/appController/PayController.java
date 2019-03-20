package com.tangmo.yiliao.controller.appController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.utility.code.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 支付
 */
@Api("支付")
@RestController
@RequestMapping("/pay")
public class PayController extends BaseController {

    /**
     * @api {POST} /pay/payTreasure/order/{userId}/{goId} 提交支付宝支付订单
     * @apiGroup Pay
     * @apiVersion 0.0.1
     * @apiDescription  提交支付宝支付订单
     * @apiParam {String} userId : 用户id
     * @apiParam {String} goId : 订单id（多个订单以逗号（,）分割）
     * @apiParamExample {json} 请求样例：
     *      /pay/payTreasure/order/1/1
     * @apiSuccess (success) {POST} code 0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"前端吊起支付宝所需参数"}
     *                       }
     */
    @ApiOperation(value="提交支付宝支付订单")
    @PostMapping("/payTreasure/order/{userId}/{goId}")
    public Result payTreasure(@PathVariable String userId, @PathVariable String goId) {
        return payService.payZHBOrder(userId, goId);
    }

    //支付宝异步回调
    @PostMapping("/payTreasure/order/notify_url")
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
        String status = payService.notify(conversionParams);
        return status;
    }

    //支付宝同步回调
    @PostMapping("/payTreasure/order/return_url")
    public String return_url(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("==================支付宝的App回调接口开始");
        return "";
    }

    /**
     * @api {POST} /pay/payWeChat/order/{userId}/{goId} 提交微信支付订单
     * @apiGroup Pay
     * @apiVersion 0.0.1
     * @apiDescription  提交微信支付订单
     * @apiParam {String} userId : 用户id
     * @apiParam {String} goId : 订单id（多个订单以逗号（,）分割）
     * @apiParamExample {json} 请求样例：
     *      /pay/payWeChat/order/1/1
     * @apiSuccess (success) {POST} code 0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"前端吊起微信所需参数"}
     *                       }
     */
    @ApiOperation(value="提交微信支付订单")
    @PostMapping("/payWeChat/order/{userId}/{goId}")
    public Result payWeChat(@PathVariable String userId, @PathVariable String goId) {
        return payService.payWeChat(userId, goId);
    }

    //微信的支付回调接口(订单)
    @PostMapping("/order/callback")
    public String callBack(HttpServletRequest request) {
        Map<String, String> smap = null;
        try {
            smap = getCallBackInfo(request);
        } catch (Exception e) {
            e.printStackTrace();
            return returnXML(smap.get("return_code"));
        }
        //商品数量-1,通知商家,检查库存是否足够
        payService.updatePayResult(smap);
        //System.out.println("=========================huidiao========================");
        return returnXML(smap.get("return_code"));
    }

    private Map<String, String> getCallBackInfo(HttpServletRequest request) throws DocumentException, IOException {
        BufferedReader reader = null;

        reader = request.getReader();
        String line = "";
        StringBuffer inputString = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            inputString.append(line);
        }
        //微信返回的XML格式解析,用map接收
        SortedMap<String, String> smap = new TreeMap<String, String>();
        Document doc = DocumentHelper.parseText(inputString.toString());
        Element root = doc.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
            Element e = (Element) iterator.next();
            smap.put(e.getName(), e.getText());
        }
        return smap;
    }

    private String returnXML(String return_code) {
        return "<xml><return_code><![CDATA["
                + return_code
                + "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

    /**
     * @api {POST} /pay/payWeChat/order/{userId}/{goId} 提交小程序支付订单
     * @apiGroup Pay
     * @apiVersion 0.0.1
     * @apiDescription  提交微信支付订单
     * @apiParam {String} userId : 用户id
     * @apiParam {String} goId : 订单id（多个订单以逗号（,）分割）
     * @apiParamExample {json} 请求样例：
     *      /pay/payWeChat/order/1/1
     * @apiSuccess (success) {POST} code 0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"前端吊起微信所需参数"}
     *                       }
     */
    @ApiOperation(value="提交微信支付订单")
    @PostMapping("/payWeChatXCX/order/{userId}/{goId}")
    public Result payWeChatXCX(@PathVariable String userId, @PathVariable String goId) {
        return payService.payWeChatXCX(userId, goId);
    }

    /**
     * @api {POST} /pay/TreasureWithdrawal/order/{userId}/{goId} 支付宝提现
     * @apiGroup Pay
     * @apiVersion 0.0.1
     * @apiDescription  支付宝提现
     * @apiParam {String} userId  : 用户id
     * @apiParam {String} phone  : 电话
     * @apiParam {String} code  : 验证码
     * @apiParam {String} account : 支付宝账号
     * @apiParam {Integer} count  :  提现积分数量
     * @apiParam {Double}  amount :  提现金额
     * @apiParamExample {json} 请求样例：
     *      /pay/payTreasure/order/1
     * @apiSuccess (success) {POST} code 0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{""}
     *                       }
     */
    @ApiOperation(value="支付宝提现")
    @PostMapping("/TreasureWithdrawal/order/{userId}/{phone}/{code}/{account}/{count}/{amount}")
    public Result TreasureWithdrawal(@PathVariable String userId,@PathVariable String phone,@PathVariable String code, @PathVariable String account,@PathVariable Integer count,@PathVariable Double amount) {
        return payService.TreasureWithdrawal(userId,phone,code, account,count,amount);
    }

}
