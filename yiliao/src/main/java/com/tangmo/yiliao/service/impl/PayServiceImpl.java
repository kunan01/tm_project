package com.tangmo.yiliao.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.tangmo.yiliao.config.PayWXConfig;
import com.tangmo.yiliao.config.PayXCXConfig;
import com.tangmo.yiliao.config.PayZFBConfig;
import com.tangmo.yiliao.dao.ManyTimesDao;
import com.tangmo.yiliao.dao.OrderDao;
import com.tangmo.yiliao.dao.PayDao;
import com.tangmo.yiliao.dao.UserDao;
import com.tangmo.yiliao.entity.*;
import com.tangmo.yiliao.service.PayService;
import com.tangmo.yiliao.utility.code.OrderRelated;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.jedis.JedisUtil;
import com.tangmo.yiliao.utility.pay.HttpsRequest;
import com.tangmo.yiliao.utility.pay.PayUtil;
import com.tangmo.yiliao.utility.pay.Sign;
import com.tangmo.yiliao.utility.pay.Xstreamutil;
import com.tangmo.yiliao.utility.util.EncryptUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author hanjialin
 * @date 2019-1-3
 * @description
 */
@Service("payService")
public class PayServiceImpl implements PayService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private PayDao payDao;

    @Resource
    private UserDao userDao;

    @Resource
    private ManyTimesDao manyTimesDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;



    @Override
    @Transactional
    public Result payZHBOrder(String userId, String goId) {
        if(userId == null || goId == null){
            return ResultUtil.paramError();
        }
        String[] goIds = goId.split(",");
        System.out.println(goIds);
        String pay_no = OrderRelated.getOrderNo();
        String commName = "";
        Double fee = 0.0;
        int a = 0;

        for (int i = 0;i<goIds.length; i++){
            PayOrder payOrder = orderDao.getOrderById(goIds[i]);
            if(payOrder == null){
                return ResultUtil.error("订单信息有误");
            }
            if(payOrder.getGoType().toString().equals("1")){
                //购买商品时的处理逻辑
                a = 1;
            }else{
                commName = "康珍视积分";
                fee = fee + payOrder.getPayFee();
            }
            orderDao.updPayNo(goIds[i],pay_no);
        }
        String payResultBean = getAliPayInfo(fee,pay_no,commName);
        if(payResultBean == null){
            return ResultUtil.error("支付宝服务故障");
        }

        Pay result = payDao.getPayById(pay_no);
        //先查询是否有相同订单
        if(result == null){
            Pay pay = new Pay();
            pay.setOut_trade_no(pay_no);
            pay.setUserId(userId);
            pay.setTrade_type("ALiPay");
            if(a == 0){
                pay.setPayTarget("购买积分");
            }else{
                pay.setPayTarget("购买商品");
            }
            pay.setTotal_fee1(fee);
            pay.setPayType(Byte.parseByte("1"));
            payDao.insertPay(pay);
        }
        return ResultUtil.success(payResultBean);
    }

    public String getAliPayInfo(Double total_fee,String order_no,String commName) {

        //最终返回加签之后的，app需要传给支付宝app的订单信息字符串
        String orderString = "";
        System.out.println("==================支付宝下单,商户订单号为："+order_no);

        try{
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
            AlipayClient alipayClient = new DefaultAlipayClient(PayZFBConfig.URL, PayZFBConfig.APPID, PayZFBConfig.RSA_PRIVATE_KEY, PayZFBConfig.FORMAT, PayZFBConfig.CHARSET, PayZFBConfig.ALIPAY_PUBLIC_KEY, PayZFBConfig.SIGNTYPE);

            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();

            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

            //业务参数传入,可以传很多，参考API
            model.setBody("康珍视积分充值");//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
            model.setSubject(commName);//商品名称
            model.setOutTradeNo(order_no);//商户订单号(自动生成)
            model.setTimeoutExpress("90m"); //交易超时时间
            model.setTotalAmount(total_fee.toString());//支付金额
            model.setProductCode("QUICK_MSECURITY_PAY");//销售产品码（固定值）
            ali_request.setBizModel(model);
            System.out.println("====================异步通知的地址为："+ PayZFBConfig.notify_url);
            ali_request.setNotifyUrl(PayZFBConfig.notify_url);//异步回调地址（后台）
            ali_request.setReturnUrl(PayZFBConfig.return_url);//同步回调地址（APP）
            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse alipayTradeAppPayResponse = alipayClient.sdkExecute(ali_request); //返回支付宝订单信息(预处理)

            System.out.println("------"+alipayTradeAppPayResponse.isSuccess());
            orderString=alipayTradeAppPayResponse.getBody();//就是orderString 可以直接给APP请求，无需再做处理。
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
            signVerified = AlipaySignature.rsaCheckV1(conversionParams, PayZFBConfig.ALIPAY_PUBLIC_KEY, PayZFBConfig.CHARSET, PayZFBConfig.SIGNTYPE);

        } catch (AlipayApiException e) {
            System.out.println("==================验签失败 ！");
            e.printStackTrace();
        }

        //对验签进行处理
        if (signVerified) {
            //验签通过
            //获取需要保存的数据
            String appId=conversionParams.get("app_id");//支付宝分配给开发者的应用Id
            String notifyTime=conversionParams.get("notify_time");//通知时间:yyyy-MM-dd HH:mm:ss
            String gmtCreate=conversionParams.get("gmt_create");//交易创建时间:yyyy-MM-dd HH:mm:ss
            String gmtPayment=conversionParams.get("gmt_payment");//交易付款时间
            String gmtRefund=conversionParams.get("gmt_refund");//交易退款时间
            String gmtClose=conversionParams.get("gmt_close");//交易结束时间
            String tradeNo=conversionParams.get("trade_no");//支付宝的交易号
            String outTradeNo = conversionParams.get("out_trade_no");//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
            String outBizNo=conversionParams.get("out_biz_no");//商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
            String buyerLogonId=conversionParams.get("buyer_logon_id");//买家支付宝账号
            String sellerId=conversionParams.get("seller_id");//卖家支付宝用户号
            String sellerEmail=conversionParams.get("seller_email");//卖家支付宝账号
            String totalAmount=conversionParams.get("total_amount");//订单金额:本次交易支付的订单金额，单位为人民币（元）
            String receiptAmount=conversionParams.get("receipt_amount");//实收金额:商家在交易中实际收到的款项，单位为元
            String invoiceAmount=conversionParams.get("invoice_amount");//开票金额:用户在交易中支付的可开发票的金额
            String buyerPayAmount=conversionParams.get("buyer_pay_amount");//付款金额:用户在交易中支付的金额
            String tradeStatus = conversionParams.get("trade_status");// 获取交易状态

            if(PayZFBConfig.APPID.equals(appId)){
                if(tradeStatus.equals("TRADE_SUCCESS")) {  //只处理支付成功的订单: 修改交易表状态,支付成功
                    Pay pay = new Pay();
                    pay.setReturn_msg("200");
                    pay.setResult_code("SUCCESS");
                    pay.setOut_trade_no(outTradeNo);
                    //更新支付信息
                    payDao.updateByTradeNo(pay);
                    //更新订单状态
                    orderDao.updateOrderDone(outTradeNo,Byte.parseByte("1"));

                    //查询订单信息
                    List<PayOrder> payOrder = orderDao.getOrderByPayNo(outTradeNo);
                    for (int i = 0;i<payOrder.size();i++){
                        if(payOrder.get(i).getGoType().toString().equals("1")) {
                            //此处为购买商品的逻辑部分
                        }else{
                            //此处为购买积分的逻辑部分

                            //增加用户积分
                            userDao.updUserIntegralById(payOrder.get(i).getUserId(),payOrder.get(i).getGoCount());
                            IntegralSubsidiary integralSubsidiary = new IntegralSubsidiary();

                            //增加用户积分明细
                            integralSubsidiary.setSyId(EncryptUtil.get32Uuid());
                            integralSubsidiary.setUserId(payOrder.get(i).getUserId());
                            integralSubsidiary.setLtId("BUY_CREDITS");
                            integralSubsidiary.setSyBean(payOrder.get(i).getGoCount());
                            integralSubsidiary.setCreateUserId(payOrder.get(i).getUserId());
                            manyTimesDao.addIntegralSubsidiary(integralSubsidiary);
                        }
                    }
                    return "success";
                }else{
                    orderDao.updateOrderDone(outTradeNo,Byte.parseByte("3"));
                    return "fail";
                }
            }else{
                System.out.println("==================支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）,不一致！返回fail");
                return"fail";
            }
        } else { //验签不通过
            System.out.println("==================验签不通过 ！");
            return "fail";
        }
    }

    @Override
    @Transactional
    public Result payWeChat(String userId, String goId) {
        if(userId == null || goId == null){
            return ResultUtil.paramError();
        }
        String[] goIds = goId.split(",");
        System.out.println(goIds);
        String pay_no = OrderRelated.getOrderNo();
        String commName = "";
        Double fee = 0.0;
        int fee1 = 0;
        int a = 0;

        for (int i = 0;i<goIds.length; i++){
            PayOrder payOrder = orderDao.getOrderById(goIds[i]);
            if(payOrder == null){
                return ResultUtil.error("订单信息有误");
            }
            if(payOrder.getGoType().toString().equals("1")){
                //购买商品时的处理逻辑
                a = 1;
            }else{
                commName = "康珍视积分";
                fee = fee + payOrder.getPayFee();
                fee1 = fee1 + (int)(payOrder.getPayFee()*100);
            }
            orderDao.updPayNo(goIds[i],pay_no);
        }

        WeChatPayResultBean payResultBean = getWeChatPayInfo(fee,fee1,pay_no,PayWXConfig.order_url);//价格，订单号，回调地址
        if(payResultBean == null){
            return ResultUtil.error("微信服务故障");
        }

        Pay result = payDao.getPayById(payResultBean.getOut_trade_no());
        //先查询是否有相同订单
        if(result == null){
            payResultBean.setUserId(userId);
            Pay pay = new Pay();
            pay.setOut_trade_no(pay_no);
            pay.setUserId(userId);
            pay.setTrade_type("APP");
            if(a == 0){
                pay.setPayTarget("购买积分");
            }else{
                pay.setPayTarget("购买商品");
            }
            pay.setTotal_fee1(fee);
            pay.setPayType(Byte.parseByte("0"));
            payDao.insertPay(pay);
        }
        return ResultUtil.success(payResultBean);
    }

    public WeChatPayResultBean getWeChatPayInfo(Double fee,int total_fee,String order_no,String url) {
        System.out.println(total_fee);
        String notify_url = url;
//        PayCallBackBean payCallBackBean = new PayCallBackBean();
        //生成随机字符串
        String nonce_str = PayUtil.getRandomString(6);
        //订单号
        String out_trade_no = order_no;
//        payCallBackBean.setOrdercode(out_trade_no);
//        payCallBackBean.setPayway("微信支付");


        SortedMap<Object,Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", PayWXConfig.appid);
        parameters.put("mch_id", PayWXConfig.mch_id);
        parameters.put("nonce_str", nonce_str);
        parameters.put("trade_type",PayWXConfig.trade_type);
//        parameters.put("trade_type","NATIVE");
        parameters.put("notify_url", notify_url);
        parameters.put("total_fee",total_fee);
        parameters.put("body",PayWXConfig.body);
        parameters.put("spbill_create_ip", PayWXConfig.spbill_create_ip);
        parameters.put("out_trade_no", out_trade_no);

        //创建签名
        String sign= Sign.createSign(parameters,PayWXConfig.Key);

        //创建预支付相关信息
        Pay pay = new Pay();
        pay.setAppid(PayWXConfig.appid);
        pay.setBody(PayWXConfig.body);
        pay.setMch_id(PayWXConfig.mch_id);
        pay.setNonce_str(nonce_str);
        pay.setNotify_url(notify_url);
        pay.setOut_trade_no(out_trade_no);
        pay.setSign(sign);
        pay.setSpbill_create_ip(PayWXConfig.spbill_create_ip);
        pay.setTrade_type(PayWXConfig.trade_type);
//        pay.setTrade_type("NATIVE");
        pay.setTotal_fee(total_fee);

        Xstreamutil.stream.alias("xml", Pay.class);
        String xml=Xstreamutil.stream.toXML(pay).replace("__", "_");
        System.out.println("-----"+xml);
        String requestUrl = PayWXConfig.url;
        String requestxml = null;
        String prepay_id = null;
        try {
            requestxml = new HttpsRequest().HttpsRequest(requestUrl,"POST",xml);
            System.out.println(requestxml);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            Document doc= DocumentHelper.parseText(requestxml);
            Element element_xml=doc.getRootElement();
            List<Element> elementList = element_xml.elements();
            for (int i = 0; i <elementList.size(); i++) {
                System.out.println(elementList.get(i).getName()+" "+elementList.get(i).getText());
                if("prepay_id".equals(elementList.get(i).getName())){
                    prepay_id = elementList.get(i).getTextTrim();
                    if(prepay_id == null){
                        return null;
                    }

                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }

        Long timeStamp = System.currentTimeMillis()/1000;
        //String Package = "prepay_id="+prepay_id;
        SortedMap<Object, Object> h5parameter = new TreeMap<Object, Object>();
        h5parameter.put("appid", PayWXConfig.appid);
        h5parameter.put("noncestr", nonce_str);
        h5parameter.put("package","Sign=WXPay");
//        h5parameter.put("package",Package);
        h5parameter.put("partnerid",PayWXConfig.mch_id);
        h5parameter.put("prepayid", prepay_id);
        h5parameter.put("timestamp", timeStamp);

        String Paysign = Sign.createSign(h5parameter, PayWXConfig.Key);

        WeChatPayResultBean weChatPayResultBean = new WeChatPayResultBean();
        weChatPayResultBean.setPartnerId(PayWXConfig.mch_id);
        weChatPayResultBean.setNonceStr(nonce_str);
        weChatPayResultBean.setPackage2("Sign=WXPay");
        weChatPayResultBean.setPrepayId(prepay_id);
        weChatPayResultBean.setTimeStamp(timeStamp);
        weChatPayResultBean.setSign(Paysign);
        weChatPayResultBean.setTrade_type(PayWXConfig.trade_type);
//        weChatPayResultBean.setTrade_type("NATIVE");
        weChatPayResultBean.setOut_trade_no(out_trade_no);
        weChatPayResultBean.setTotal_fee(total_fee);
        weChatPayResultBean.setTotal_fee1(fee);
        return weChatPayResultBean;
    }

    @Override
    public Result updatePayResult(Map<String, String> smap) {
        Pay pay = new Pay();
        pay.setReturn_msg(smap.get("return_msg"));
        pay.setResult_code(smap.get("result_code"));
        pay.setOut_trade_no(smap.get("out_trade_no"));
        //更新支付信息
        payDao.updateByTradeNo(pay);
        //更新订单状态
        orderDao.updateOrderDone(pay.getOut_trade_no(),Byte.parseByte("1"));

        //查询订单信息
        List<PayOrder> payOrder = orderDao.getOrderByPayNo(pay.getOut_trade_no());
        for (int i = 0;i<payOrder.size();i++){
            if(payOrder.get(i).getGoType().toString().equals("1")) {
                //此处为购买商品的逻辑部分



            }else{
                //此处为购买积分的逻辑部分

                //增加用户积分
                userDao.updUserIntegralById(payOrder.get(i).getUserId(),payOrder.get(i).getGoCount());
                IntegralSubsidiary integralSubsidiary = new IntegralSubsidiary();

                //增加用户积分明细
                integralSubsidiary.setSyId(EncryptUtil.get32Uuid());
                integralSubsidiary.setUserId(payOrder.get(i).getUserId());
                integralSubsidiary.setLtId("BUY_CREDITS");
                integralSubsidiary.setSyBean(payOrder.get(i).getGoCount());
                integralSubsidiary.setCreateUserId(payOrder.get(i).getUserId());
                manyTimesDao.addIntegralSubsidiary(integralSubsidiary);
            }
        }
        return ResultUtil.success();
    }

    @Override
    public Result payWeChatXCX(String userId, String goId) {
        if(userId == null || goId == null){
            return ResultUtil.paramError();
        }

        User user = userDao.getBasicInformationById(userId);

        if(user.getWxOpenType().toString().equals("0")){
            return ResultUtil.error("当前用户未授权");
        }


        String[] goIds = goId.split(",");
        System.out.println(goIds);
        String pay_no = OrderRelated.getOrderNo();
        String commName = "";
        Double fee = 0.0;
        int fee1 = 0;
        int a = 0;

        for (int i = 0;i<goIds.length; i++){
            PayOrder payOrder = orderDao.getOrderById(goIds[i]);
            if(payOrder == null){
                return ResultUtil.error("订单信息有误");
            }
            if(payOrder.getGoType().toString().equals("1")){
                //购买商品时的处理逻辑
                a = 1;
            }else{
                commName = "康珍视积分";
                fee = fee + payOrder.getPayFee();
                fee1 = fee1 + (int)(payOrder.getPayFee()*100);
            }
            orderDao.updPayNo(goIds[i],pay_no);
        }

        WeChatPayResultBean payResultBean = getWeChatXCXPayInfo(fee,fee1,pay_no,PayXCXConfig.order_url,user.getWxOpenId());//价格，订单号，回调地址
        if(payResultBean == null){
            return ResultUtil.error("微信服务故障");
        }

        Pay result = payDao.getPayById(payResultBean.getOut_trade_no());
        //先查询是否有相同订单
        if(result == null){
            payResultBean.setUserId(userId);
            Pay pay = new Pay();
            pay.setOut_trade_no(pay_no);
            pay.setUserId(userId);
            pay.setTrade_type("JSAPI");
            if(a == 0){
                pay.setPayTarget("购买积分");
            }else{
                pay.setPayTarget("购买商品");
            }
            pay.setTotal_fee1(fee);
            pay.setPayType(Byte.parseByte("0"));
            payDao.insertPay(pay);
        }
        return ResultUtil.success(payResultBean);
    }

    public WeChatPayResultBean getWeChatXCXPayInfo(Double fee,int total_fee,String order_no,String url,String openid) {
        System.out.println(total_fee);
        String notify_url = url;
//        PayCallBackBean payCallBackBean = new PayCallBackBean();
        //生成随机字符串
        String nonce_str = PayUtil.getRandomString(6);
        //订单号
        String out_trade_no = order_no;
//        payCallBackBean.setOrdercode(out_trade_no);
//        payCallBackBean.setPayway("微信支付");


        SortedMap<Object,Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", PayXCXConfig.appid);
        parameters.put("mch_id", PayXCXConfig.mch_id);
        parameters.put("nonce_str", nonce_str);
        parameters.put("trade_type",PayXCXConfig.trade_type);
        parameters.put("notify_url", notify_url);
        parameters.put("total_fee",total_fee);
        parameters.put("body",PayXCXConfig.body);
        parameters.put("spbill_create_ip", PayXCXConfig.spbill_create_ip);
        parameters.put("out_trade_no", out_trade_no);
        parameters.put("openid",openid);

        //创建签名
        String sign= Sign.createSign(parameters,PayXCXConfig.Key);

        //创建预支付相关信息
        Pay pay = new Pay();
        pay.setAppid(PayXCXConfig.appid);
        pay.setBody(PayXCXConfig.body);
        pay.setMch_id(PayXCXConfig.mch_id);
        pay.setNonce_str(nonce_str);
        pay.setNotify_url(notify_url);
        pay.setOut_trade_no(out_trade_no);
        pay.setSign(sign);
        pay.setSpbill_create_ip(PayXCXConfig.spbill_create_ip);
        pay.setTrade_type(PayXCXConfig.trade_type);
        pay.setTotal_fee(total_fee);
        pay.setOpenid(openid);
//        pay.setSign_type("MD5");

        Xstreamutil.stream.alias("xml", Pay.class);
        String xml=Xstreamutil.stream.toXML(pay).replace("__", "_");
        System.out.println("-----"+xml);
        String requestUrl = PayWXConfig.url;
        String requestxml = null;
        String prepay_id = null;
        try {
            requestxml = new HttpsRequest().HttpsRequest(requestUrl,"POST",xml);
            System.out.println(requestxml);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            Document doc= DocumentHelper.parseText(requestxml);
            Element element_xml=doc.getRootElement();
            List<Element> elementList = element_xml.elements();
            for (int i = 0; i <elementList.size(); i++) {
                System.out.println(elementList.get(i).getName()+" "+elementList.get(i).getText());
                if("prepay_id".equals(elementList.get(i).getName())){
                    prepay_id = elementList.get(i).getTextTrim();
                    if(prepay_id == null){
                        return null;
                    }

                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }

        Long timeStamp = System.currentTimeMillis()/1000;
        //String Package = "prepay_id="+prepay_id;
        SortedMap<Object, Object> h5parameter = new TreeMap<Object, Object>();
        h5parameter.put("appId", PayXCXConfig.appid);
        h5parameter.put("timeStamp", timeStamp);
        h5parameter.put("nonceStr", nonce_str);
        h5parameter.put("package","prepay_id="+prepay_id);
//        h5parameter.put("package",Package);
//        h5parameter.put("partnerid",PayXCXConfig.mch_id);
//        h5parameter.put("prepay_id", prepay_id);
        h5parameter.put("signType", "MD5");
//        h5parameter.put("openid",openid);
        String Paysign = Sign.createSign(h5parameter, PayXCXConfig.Key);

        WeChatPayResultBean weChatPayResultBean = new WeChatPayResultBean();
        weChatPayResultBean.setPartnerId(PayXCXConfig.mch_id);
        weChatPayResultBean.setNonceStr(nonce_str);
        weChatPayResultBean.setPackage2("Sign=WXPay");
        weChatPayResultBean.setPrepayId(prepay_id);
        weChatPayResultBean.setTimeStamp(timeStamp);
        weChatPayResultBean.setSign(Paysign);
        weChatPayResultBean.setTrade_type(PayXCXConfig.trade_type);
//      weChatPayResultBean.setTrade_type("NATIVE");
        weChatPayResultBean.setOut_trade_no(out_trade_no);
        weChatPayResultBean.setTotal_fee(total_fee);
        weChatPayResultBean.setTotal_fee1(fee);
        return weChatPayResultBean;
    }

    @Override
    @Transactional
    public Result TreasureWithdrawal(String userId,String phone,String userCode, String account, Integer count,Double amount) {
        System.out.println(userId);

        if(userId == null|| phone == null || userCode == null || account == null || count == null || amount == null){
            return ResultUtil.paramError();
        }

        //校验用户是否存在
        String userPhone = userDao.getUserPhoneById(userId);
        if(!userPhone.equals(phone)){
            return ResultUtil.error("手机号不一致");
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = phone;
        if (!jedisKeys.exists(key)) {
            return ResultUtil.codeError();
        }else{
            //若存在，则直接从redis里面取出相应数
            String code = jedisStrings.get(key);
            String jsonString = jedisStrings.get(key+"date");
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000*60*60*24);
                long hour = (l / (1000*60*60)-day*24);
                long min = ((l/(1000*60))-day*24*60-hour*60);
                long s = (l/1000-day*24*60*60-hour*60*60-min*60);
                if(((day*24*60*60)+(hour*60*60)+(min*60)+s) > 180){
                    return ResultUtil.codeError();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(!code.equals(userCode)){
                return ResultUtil.codeError();
            }
            jedisKeys.del(key);
            jedisKeys.del(key+"date");
        }

        //获取用户积分余额
        Integer userCount = userDao.getUserIntegral(userId);

        //获取用户积分总额
        Integer userTotal = userDao.getUserIntegralAll(userId);

        if(userCount == null || userTotal == null){
            return ResultUtil.error("用户信息有误");
        }

        if(userCount < count){
            return ResultUtil.error("用户积分余额不足");
        }

        //校验提现规则
        Integer money = userDao.selectIntegralRules(Byte.parseByte("3"));

        Double a = Double.parseDouble(count.toString()) / Double.parseDouble(money.toString());
        if(!a.toString().equals(amount.toString())){
            return ResultUtil.error("价格有误");
        }

        //单次最低提现金额
        Integer minimum = userDao.selectIntegralRules(Byte.parseByte("4"));

        if(amount < userDao.selectIntegralRules(Byte.parseByte("4"))){
            return ResultUtil.error("单次最低提现"+minimum+"元");
        }

        //单次提现最大金额
        Integer minimumReserve = userDao.selectIntegralRules(Byte.parseByte("6"));

        if(amount > minimumReserve){
            return ResultUtil.error("单次最多提现"+minimumReserve+"元");
        }

        //用户累计多少分方可提现
        Integer accumulated = userDao.selectIntegralRules(Byte.parseByte("5"));
        if(accumulated > userTotal){
            return ResultUtil.error("累计获取"+accumulated+"积分方可提现");
        }

        //单日最多提现次数
        Integer Number = userDao.selectIntegralRules(Byte.parseByte("7"));

        //获取用户单日提现次数
        Integer UserNumber = userDao.getWithdrawalCount(userId);

        if(Number <= UserNumber){
            return ResultUtil.error("单日最多提现"+Number+"次，今日已用完，请明天再来！");
        }

        try{
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
            AlipayClient alipayClient = new DefaultAlipayClient(PayZFBConfig.URL, PayZFBConfig.APPID, PayZFBConfig.RSA_PRIVATE_KEY, PayZFBConfig.FORMAT, PayZFBConfig.CHARSET, PayZFBConfig.ALIPAY_PUBLIC_KEY, PayZFBConfig.SIGNTYPE);

            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayFundTransToaccountTransferRequest ali_request = new AlipayFundTransToaccountTransferRequest();

            AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();

            Long time = System.currentTimeMillis()/1000;

            model.setOutBizNo(time.toString());

            model.setPayeeType("ALIPAY_LOGONID"); //收款方账户类型

            model.setPayeeAccount(account);       //收款方账号

            model.setAmount(amount.toString());            //转账金额

            model.setRemark("康珍视积分提现");

            ali_request.setBizModel(model);
//            ali_request.setApiVersion("1.0");

            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(ali_request);

            System.out.println(response.getBody());
            System.out.println(response.getCode());
            System.out.println(response.getMsg());

            System.out.println(response.getOrderId());

            System.out.println(response.getOutBizNo());
            if(response.isSuccess()){

                if(response.getCode().toString().equals("10000")){
                    //调用成功
                    String orderid = response.getOrderId();

                    //增加用户积分
                    userDao.delUserIntegralById(userId,count);
                    IntegralSubsidiary integralSubsidiary = new IntegralSubsidiary();

                    //增加用户积分明细
                    integralSubsidiary.setSyId(EncryptUtil.get32Uuid());
                    integralSubsidiary.setUserId(userId);
                    integralSubsidiary.setLtId("INTEGRAL_WITHDRAWAL");
                    integralSubsidiary.setSyBean(count);
                    integralSubsidiary.setCreateUserId(userId);
                    integralSubsidiary.setOrderId(orderid);
                    manyTimesDao.addIntegralSubsidiary(integralSubsidiary);

                    //增加用户提现记录
                    WithdrawalRecord withdrawalRecord = new WithdrawalRecord();
                    withdrawalRecord.setWithdrawalId(EncryptUtil.get32Uuid());
                    withdrawalRecord.setUserId(userId);
                    withdrawalRecord.setAccount(account);
                    withdrawalRecord.setIntegralMoney(amount);
                    withdrawalRecord.setIntegralNumber(count);
                    userDao.addWithdrawalRecord(withdrawalRecord);

                    return ResultUtil.success();
                }else{
                    return ResultUtil.error("请检查数据后重试");
                }
            }else{

                return ResultUtil.error("请检查数据后重试");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.serverError();
    }
}
