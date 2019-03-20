package com.tangmo.zhygzhglxt.service.Impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.tangmo.zhygzhglxt.config.PayConfig;
import com.tangmo.zhygzhglxt.dao.*;
import com.tangmo.zhygzhglxt.entity.Pay;
import com.tangmo.zhygzhglxt.entity.TbDriverOrder;
import com.tangmo.zhygzhglxt.entity.TbOrderRoute;
import com.tangmo.zhygzhglxt.entity.TbPassengerOrder;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.AliPayService;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service("aliPayService")
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private PayDao payDao;

    @Autowired
    private TbPassengerOrderMapper tbPassengerOrderMapper;  //乘客订单

    @Autowired
    private TbDriverOrderMapper tbDriverOrderMapper;  //乘客订单

    @Autowired
    private TbSysUserMapper tbSysUserMapper;  //用户

    @Autowired
    private TbOrderRouteMapper tbOrderRouteMapper;  //订单路线


    @Override
    @Transactional
    public Result payOrder(String userCode, String passengerOrderCode) {

        if (userCode == null || passengerOrderCode == null || "".equals(userCode) || "".equals(passengerOrderCode)) {
            return new Result(ResultCode.FAIL, "乘客订单的唯一标识或者用户的唯一标识不能为空！");
        }

        String commName = "司机线上收款";
        //查询订单信息
        TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selOrderDetailByOrderCode(passengerOrderCode);

        if (tbPassengerOrder == null) {
            return new Result(ResultCode.FAIL, "订单不存在！");
        }
        System.out.println("=====" + tbPassengerOrder.getPrice());

        BigDecimal fee = tbPassengerOrder.getPrice();
        String orderNo = tbPassengerOrder.getOrderNumber();//得到订单号
        //注意事项，看订单号是否在支付时不能重复问题，待商榷测试
        String payResultBean = getAliPayInfo(fee, orderNo, commName);//价格，订单号，商品名称

        if (payResultBean == null || "".equals(payResultBean)) {
            return new Result(ResultCode.FAIL, "支付宝服务故障");
        }

        //先查询是否有相同订单,没有则将预支付信息添加
        Pay result = payDao.selectByTradeNo(orderNo);
        if (result == null) {
            BigDecimal decimal = new BigDecimal(100);
            fee = fee.multiply(decimal);
            Pay pay = new Pay();
            pay.setOut_trade_no(orderNo);
            pay.setUserCode(userCode);
            pay.setTrade_type("APP");
            pay.setPayTarget("司机线上收款");
            pay.setTotal_fee(fee.intValue());
            pay.setPayType("2");
            payDao.insertPay(pay);
        }
        return new Result(ResultCode.SUCCESS, payResultBean);
    }

    public String getAliPayInfo(BigDecimal total_fee, String order_no, String commName) {

        //最终返回加签之后的，app需要传给支付宝app的订单信息字符串
        String orderString = "";
        System.out.println("==================支付宝下单,商户订单号为：" + order_no);

        try {
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
            AlipayClient alipayClient = new DefaultAlipayClient(PayConfig.URL, PayConfig.APPID, PayConfig.RSA_PRIVATE_KEY, PayConfig.FORMAT, PayConfig.CHARSET, PayConfig.ALIPAY_PUBLIC_KEY, PayConfig.SIGNTYPE);

            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();

            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            //业务参数传入,可以传很多，参考API
            //model.setPassbackParams(URLEncoder.encode(request.getBody().toString())); //公用参数（附加数据）
            model.setBody("司机APP线上收款");//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
            System.out.println(commName + "---" + order_no + "---" + total_fee.toString());
            model.setSubject(commName);//商品名称
            model.setOutTradeNo(order_no);//商户订单号(自动生成)
            model.setTimeoutExpress("90m"); //交易超时时间
            model.setTotalAmount(total_fee.toString());//支付金额
            model.setProductCode("QUICK_MSECURITY_PAY");//销售产品码（固定值）
            ali_request.setBizModel(model);
            System.out.println("====================异步通知的地址为：" + PayConfig.notify_url);
            ali_request.setNotifyUrl(PayConfig.notify_url);//异步回调地址（后台）
            ali_request.setReturnUrl(PayConfig.return_url);//同步回调地址（APP）
            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse alipayTradeAppPayResponse = alipayClient.sdkExecute(ali_request); //返回支付宝订单信息(预处理)

            System.out.println("------" + alipayTradeAppPayResponse.isSuccess());
            if (alipayTradeAppPayResponse.isSuccess()) {  //如果是true代表成功
                orderString = alipayTradeAppPayResponse.getBody();//就是orderString 可以直接给APP请求，无需再做处理。
            } else {
                System.out.println("与支付宝交互出错，未能生成订单，请检查代码！");
            }
            System.out.println(orderString);

        } catch (AlipayApiException e) {
            e.printStackTrace();
            System.out.println("与支付宝交互出错，未能生成订单，请检查代码！");
            return null;
        }
        return orderString;
    }

    @Override
    public String notify(Map<String, String> conversionParams) {

        System.out.println("==================支付宝异步请求逻辑处理");

        //签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        boolean signVerified = false;

        try {
            //调用SDK验证签名
            signVerified = AlipaySignature.rsaCheckV1(conversionParams, PayConfig.ALIPAY_PUBLIC_KEY, PayConfig.CHARSET, PayConfig.SIGNTYPE);

        } catch (AlipayApiException e) {
            System.out.println("==================验签失败 ！");
            e.printStackTrace();
        }

        //对验签进行处理
        if (signVerified) {
            //验签通过
            //获取需要保存的数据
            String appId = conversionParams.get("app_id");//支付宝分配给开发者的应用Id
            String notifyTime = conversionParams.get("notify_time");//通知时间:yyyy-MM-dd HH:mm:ss
            String gmtCreate = conversionParams.get("gmt_create");//交易创建时间:yyyy-MM-dd HH:mm:ss
            String gmtPayment = conversionParams.get("gmt_payment");//交易付款时间
            String gmtRefund = conversionParams.get("gmt_refund");//交易退款时间
            String gmtClose = conversionParams.get("gmt_close");//交易结束时间
            String tradeNo = conversionParams.get("trade_no");//支付宝的交易号
            String outTradeNo = conversionParams.get("out_trade_no");//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
            String outBizNo = conversionParams.get("out_biz_no");//商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
            String buyerLogonId = conversionParams.get("buyer_logon_id");//买家支付宝账号
            String sellerId = conversionParams.get("seller_id");//卖家支付宝用户号
            String sellerEmail = conversionParams.get("seller_email");//卖家支付宝账号
            String totalAmount = conversionParams.get("total_amount");//订单金额:本次交易支付的订单金额，单位为人民币（元）
            String receiptAmount = conversionParams.get("receipt_amount");//实收金额:商家在交易中实际收到的款项，单位为元
            String invoiceAmount = conversionParams.get("invoice_amount");//开票金额:用户在交易中支付的可开发票的金额
            String buyerPayAmount = conversionParams.get("buyer_pay_amount");//付款金额:用户在交易中支付的金额
            String tradeStatus = conversionParams.get("trade_status");// 获取交易状态

            if (PayConfig.APPID.equals(appId)) {
                if (tradeStatus.equals("TRADE_SUCCESS")) {  //只处理支付成功的订单: 修改交易表状态,支付成功
                    Pay pay = new Pay();
                    pay.setReturn_msg("200");
                    pay.setResult_code("SUCCESS");
                    pay.setOut_trade_no(outTradeNo);
                    pay.setPayType("2");
                    //更新支付信息
                    payDao.updateByTradeNo(pay);
                    //更新订单状态
                    TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selectByPassOrderNumber(pay.getOut_trade_no());

                    if (tbPassengerOrder != null) {
                        //将乘客订单的状态改为以支付
                        tbPassengerOrderMapper.updateStateByPassengerOrderCode(tbPassengerOrder.getOrderCode(), "2");
                        //将司机的订单状态改为已支付
                        tbDriverOrderMapper.updateStateByPassengerOrderCode("1", tbPassengerOrder.getOrderCode());

                        if ("1018".equals(tbPassengerOrder.getTbParmId())) {
                            TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selByPassengerOrderCode(tbPassengerOrder.getOrderCode());
                            if (tbDriverOrder != null) {
                                TbOrderRoute tbOrderRoute = tbOrderRouteMapper.selectByDriverOrderCodeLast(tbDriverOrder.getDriverOrderCode());
                                if (tbOrderRoute != null) {
                                    tbPassengerOrderMapper.updateLastLaLoByPassengerOrderCode(tbDriverOrder.getPassengerOrderCode(), tbOrderRoute.getLa(), tbOrderRoute.getLo());
                                }
                            }
                        }

                        //判断是否是线上支付
                        if (!"0".equals(tbPassengerOrder.getPayWay())) {
                            //司机余额合计增加
                            if (tbPassengerOrder.getPrice() != null) {
                                TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selByPassengerOrderCode(tbPassengerOrder.getOrderCode());
                                tbSysUserMapper.updateBanlanceByUserCode(tbDriverOrder.getDriverId(), tbPassengerOrder.getPrice());
                            }
                        }
                    }
                    return "success";
                } else {
                    return "fail";
                }

            } else {
                System.out.println("==================支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）,不一致！返回fail");
                return "fail";
            }

        } else { //验签不通过
            System.out.println("==================验签不通过 ！");
            return "fail";
        }
    }
}
