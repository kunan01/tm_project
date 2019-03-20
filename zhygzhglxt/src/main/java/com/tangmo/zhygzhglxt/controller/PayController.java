package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.service.PayService;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Api("微信支付")
@RestController
@RequestMapping("/pay")
public class PayController extends BaseController {

    /**
     * @api {GET} /pay/wechat/order 微信支付订单
     * @apiGroup Pay
     * @apiVersion 0.0.1
     * @apiParam {String} userCode 用户Id
     * @apiParam {String} passengerOrderCode 乘客订单的唯一标识
     * @apiDescription 微信支付订单
     * @apiParamExample {json} 请求样例：
     * /pay/wechat/order/1/15
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (success) {GET} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     */
    @ApiOperation(value = "微信支付订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户的唯一标识", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "passengerOrderCode", value = "乘客订单的唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/wechat/order")
    public Result payOrder(String userCode, String passengerOrderCode) {
        return payService.payOrder(userCode, passengerOrderCode);
    }


    /**
     * 微信的支付回调接口(订单)
     *
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    @ApiOperation(value = "微信的支付回调接口(订单)")
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
        System.out.println("=========================huidiao========================");
        return returnXML(smap.get("return_code"));
    }

    /**
     * 前台获取预支付信息
     *
     * @param trade_no
     * @return
     */
    @ApiOperation(value = "前台获取预支付信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "trade_no", value = "订单号", dataType = "string", required = true, paramType = "query"),
    })
    @GetMapping("/result")
    public Result getPayResult(String trade_no) {
        return payService.selectByTradeNo(trade_no);
    }

    /**
     * 微信回调之后,通知微信回调成功XML
     *
     * @param return_code
     * @return
     */

    private String returnXML(String return_code) {
        return "<xml><return_code><![CDATA["
                + return_code
                + "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

    /**
     * 解析微信支付回调信息
     *
     * @param request
     * @return
     * @throws DocumentException
     * @throws IOException
     */
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
}
