package com.tangmo.emall.controller;

import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * @author Chamber
 * @date 2019/1/9.
 * @Description
 */
@Api("paypal支付")
@RestController
@RequestMapping("/web/paypal")
public class PaymentController extends BizBaseController {


    /**
     * @api {POST} /paypal/createPay 创建paypal支付
     * @apiGroup Paypal
     * @apiVersion 0.0.1
     * @apiDescription 创建paypal支付
     * @apiParam {Integer} userId  用户id
     * @apiParam {String}  detailId  订单明细id（多个以,号隔开）
     * @apiParamExample {json} 请求样例：
     *      /paypal/createPay?userId=1&detailId=1
     * @apiSuccess (success) {POST} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{"请求地址"}
     *                       }
     */
    @ApiOperation(value="创建paypal支付",notes="创建paypal支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",dataType="int",required=true,paramType="query"),
            @ApiImplicitParam(name="detailId",value="订单明细id（多个以,号隔开）",dataType="String",required=true,paramType="query")
    })
//    @UserLoginToken
    @PostMapping("/createPay")
    public Result createPay(Integer userId,String detailId){
        return paypalService.createPayment(userId,detailId);
    }

    //取消支付回调
    @GetMapping(value = "/cancel")
    public void cancelPay(HttpServletResponse response) throws Exception{
        //取消支付处理逻辑
        System.out.println("返回商家");
        //取消支付  47.254.179.109
        response.sendRedirect("http://114.115.211.170:8080/#/tipsPay?payId=0");
    }

    //支付完成回调
    @GetMapping(value = "/success")
    public void successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,HttpServletResponse response) throws Exception{
        System.out.println(paymentId);

        String str = paypalService.executePayment(paymentId, payerId);

        if(str.equals("success")){
            //支付成功
            response.sendRedirect("http://114.115.211.170:8080/#/tipsPay?payId=1");
        }else{
            //支付失败
            response.sendRedirect("http://114.115.211.170:8080/#/tipsPay?payId=0");
        }
    }



}
