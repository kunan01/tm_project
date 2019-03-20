package com.tangmo.zhjy.app.modules.service.impl;


import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.NotifyUrlConst;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.*;
import com.tangmo.zhjy.app.modules.dao.*;
import com.tangmo.zhjy.app.modules.service.PayService;
import com.tangmo.zhjy.app.utils.OrderRelated;
import com.tangmo.zhjy.app.utils.wechat.HttpsRequest;
import com.tangmo.zhjy.app.utils.wechat.PayUtil;
import com.tangmo.zhjy.app.utils.wechat.Sign;
import com.tangmo.zhjy.app.utils.wechat.Xstreamutil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author boge
 * @date 18/3/27
 * @description
 */
@Service("payService")
public class PayServiceImpl implements PayService {
    @Resource
    private PayDao payDao;

    @Resource
    private GoodsOrderDao goodsOrderDao;

    @Resource
    private CommodityDao commodityDao;

    @Resource
    private GoodsRecordDao goodsRecordDao;

    @Resource
    private UserAddressDao userAddressDao;

    String Key = "XJzlhy2018COMXJzlhy2018COMZHJYCN";
    String appid = "wxaefe755fe119e4bf"; //应用ID 必填：true
    String mch_id = "1504277061";//商户号    必填：true
    String spbill_create_ip="111.230.242.116"; //终端IP 必填：true
    String device_info="WEB";//设备号  必填：false
    String body = "zhonghui goods";//商品描述 必填：true
    String trade_type="APP";//交易类型  必填：true

    private WeChatPayResultBean weChatPayResultBean;

    @Override
    public Result addPayInfo(Pay pay) {
        payDao.insertPay(pay);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result addPayInfo(Map<String, String> map) {
        Pay pay = new Pay();
        pay.setReturn_msg(map.get("return_msg"));
        pay.setResult_code(map.get("result_code"));
        return addPayInfo(pay);
    }

    @Override
    public Result selectByTradeNo(String tradeNo) {
        Map<String,Object> map = new HashMap<>();
        Pay pay = payDao.selectByTradeNo(tradeNo);
        List<GoodsOrder> goodsOrder = goodsOrderDao.selectByOrderNo(tradeNo);

        if(pay.getResult_code() == null){
            map.put("resultCode","FAIL");
        }else{
            if(pay.getResult_code().equals("SUCCESS")){
                map.put("resultCode","SUCCESS");
            }else{
                map.put("resultCode","FAIL");
            }
        }
        map.put("total_fee",pay.getTotal_fee1());

        String title = "";

        int kz= 0;

        for (int i = 0;i < goodsOrder.size();i++){
            UserAddress userAddress = userAddressDao.selectListById(goodsOrder.get(i).getUaId());
            Commodity commodity = commodityDao.selectCommodityDetail(goodsOrder.get(i).getCdId());

            if(title.equals("")){
                title = commodity.getTitle();
            }else{
                title = title + "," + commodity.getTitle();
            }

            if(kz == 0){
                map.put("province",userAddress.getProvince());
                map.put("city",userAddress.getCity());
                map.put("district",userAddress.getDistrict());
                map.put("address",userAddress.getAddress());
                map.put("recipient",userAddress.getRecipient());
                map.put("mobile",userAddress.getMobile());
                kz = 1;
            }
        }
        map.put("cdTitle",title);

        return new Result(ResultCode.SUCCESS,map);
    }

    /**
     * 1:回调通知后更新自己的支付信息表,更新result_code为SUCCESS
     * 2:更新订单表为已购买
     * 3:商品数量减去此次购买数量
     * 4:增加买入记录和卖出记录
     * todo 通知商家
     * @param map
     * @return
     */
    @Override
    @Transactional
    public int updatePayResult(Map<String, String> map) {
        Pay pay = new Pay();
        pay.setReturn_msg(map.get("return_msg"));
        pay.setResult_code(map.get("result_code"));
        pay.setOut_trade_no(map.get("out_trade_no"));
        //更新支付信息
        payDao.updateByTradeNo(pay);
        //更新订单状态
        goodsOrderDao.updateOrderDone(pay.getOut_trade_no());
        //商品数量-此次购买数量,
        List<GoodsOrder> goodsOrder = goodsOrderDao.selectByOrderNo(pay.getOut_trade_no());
        for (int i = 0;i<goodsOrder.size();i++){
            Integer cdId = goodsOrder.get(i).getCdId();
            commodityDao.updateCdCount(cdId,goodsOrder.get(i).getGoCount());
            //增加卖出记录,买入记录
            SellRecord sellRecord = new SellRecord(goodsOrder.get(i));
            goodsRecordDao.insertSellRecord(sellRecord);
            BuyRecord buyRecord = new BuyRecord(goodsOrder.get(i));
            goodsRecordDao.insertBuyRecord(buyRecord);
        }

        return 1;
    }

    @Override
    @Transactional
    public Result payOrder(Integer userId,String goId) {
        String[] goIds = goId.split(",");
        System.out.println(goIds);
        Double fee = 0.0;
        int fee1 = 0;
        String pay_no = OrderRelated.getOrderNo(userId);
        for (int i = 0;i<goIds.length; i++){
            //查询订单信息
            GoodsOrder goodsOrder = goodsOrderDao.selectById(Integer.parseInt(goIds[i]));
            if(goodsOrder == null){
                return new Result(ResultCode.FAIL,"订单信息有误");
            }
            //商品信息查询数量
            Commodity commodity = commodityDao.selectCommodityDetail(goodsOrder.getCdId());
            if(commodity == null){
                return new Result(ResultCode.FAIL,"商品信息有误");
            }
            if(goodsOrder.getGoCount() > commodity.getCdCount()){
                return new Result(ResultCode.FAIL,commodity.getTitle()+":商品存货不足");
            }
            fee1 = fee1 + (int)(goodsOrder.getPayFee()*100) + (int)(goodsOrder.getExpressFee()*100);
            fee = fee + goodsOrder.getPayFee() + goodsOrder.getExpressFee();
            goodsOrderDao.updPayNo(Integer.parseInt(goIds[i]),pay_no);
        }
        System.out.println(fee);
        WeChatPayResultBean payResultBean = getWeChatPayInfo(fee,fee1,pay_no, NotifyUrlConst.ORDER_URL);//价格，订单号，回调地址
        if(payResultBean == null){
            return new Result(ResultCode.FAIL,"微信服务故障");
        }
        Pay result = payDao.selectByTradeNo(payResultBean.getOut_trade_no());
        //先查询是否有相同订单
        if(result == null){
            payResultBean.setUserId(userId);
            Pay pay = new Pay(payResultBean);
            pay.setPayTarget("商城订单");
            pay.setPayType(0);
            addPayInfo(pay);
        }
        return new Result(ResultCode.SUCCESS,payResultBean);
    }

    @Override
    @Transactional
    public Result payOrderPC(Integer userId,String goId) {
        String[] goIds = goId.split(",");
        System.out.println(goIds);
        Double fee = 0.0;
        int fee1 = 0;
        String pay_no = OrderRelated.getOrderNo(userId);
        String commName = "";
        Integer uaid = 0;
        for (int i = 0;i<goIds.length; i++){
            //查询订单信息
            GoodsOrder goodsOrder = goodsOrderDao.selectById(Integer.parseInt(goIds[i]));

            if(i == 0){
                uaid = goodsOrder.getUaId();
            }

            if(goodsOrder == null){
                return new Result(ResultCode.FAIL,"订单信息有误");
            }
            //商品信息查询数量
            Commodity commodity = commodityDao.selectCommodityDetail(goodsOrder.getCdId());
            if(commodity == null){
                return new Result(ResultCode.FAIL,"商品信息有误");
            }
            if(goodsOrder.getGoCount() > commodity.getCdCount()){
                return new Result(ResultCode.FAIL,commodity.getTitle()+":商品存货不足");
            }
            fee1 = fee1 + (int)(goodsOrder.getPayFee()*100) + (int)(goodsOrder.getExpressFee()*100);
            fee = fee + goodsOrder.getPayFee() + goodsOrder.getExpressFee();
            goodsOrderDao.updPayNo(Integer.parseInt(goIds[i]),pay_no);
            if(commName.equals("")){
                commName = commodity.getTitle();
            }else{
                commName = commName + ","+ commodity.getTitle();
            }

        }
        WeChatPayResultBean payResultBean = getWeChatPayInfoPC(fee,fee1,pay_no, NotifyUrlConst.ORDER_URL);//价格，订单号，回调地址
        if(payResultBean == null){
            return new Result(ResultCode.FAIL,"微信服务故障");
        }

        UserAddress userAddress = userAddressDao.selectListById(uaid);
        Map<String,Object> map = new HashMap<>();

        map.put("province",userAddress.getProvince());
        map.put("city",userAddress.getCity());
        map.put("district",userAddress.getDistrict());
        map.put("address",userAddress.getAddress());
        map.put("recipient",userAddress.getRecipient());
        map.put("mobile",userAddress.getMobile());
        map.put("commName",commName);
        map.put("totalFee",fee);
        payResultBean.setMap(map);
        Pay result = payDao.selectByTradeNo(payResultBean.getOut_trade_no());
        //先查询是否有相同订单
        if(result == null){
            payResultBean.setUserId(userId);
            Pay pay = new Pay(payResultBean);
            pay.setPayTarget("商城订单");
            pay.setPayType(0);
            addPayInfo(pay);
        }
        return new Result(ResultCode.SUCCESS,payResultBean);
    }




    @Override
    public WeChatPayResultBean getWeChatPayInfo(Double fee,int total_fee,String order_no,String url) {
        System.out.println(total_fee);
        String notify_url = url;
        PayCallBackBean payCallBackBean = new PayCallBackBean();
        //生成随机字符串
        String nonce_str = PayUtil.getRandomString(6);
        //订单号
        String out_trade_no = order_no;
        payCallBackBean.setOrdercode(out_trade_no);
        payCallBackBean.setPayway("微信支付");


        SortedMap<Object,Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("nonce_str", nonce_str);
        parameters.put("trade_type",trade_type);
//        parameters.put("trade_type","NATIVE");
        parameters.put("notify_url", notify_url);
        parameters.put("total_fee",total_fee);
        parameters.put("body",body);
        parameters.put("spbill_create_ip", spbill_create_ip);
        parameters.put("out_trade_no", out_trade_no);

        //创建签名
        String sign= Sign.createSign(parameters,Key);

        //创建预支付相关信息
        Pay pay = new Pay();
        pay.setAppid(appid);
        pay.setBody(this.body);
        pay.setMch_id(mch_id);
        pay.setNonce_str(nonce_str);
        pay.setNotify_url(notify_url);
        pay.setOut_trade_no(out_trade_no);
        pay.setSign(sign);
        pay.setSpbill_create_ip(spbill_create_ip);
        pay.setTrade_type(trade_type);
//        pay.setTrade_type("NATIVE");
        pay.setTotal_fee(total_fee);

        Xstreamutil.stream.alias("xml", Pay.class);
        String xml=Xstreamutil.stream.toXML(pay).replace("__", "_");
        System.out.println("-----"+xml);
        String requestUrl ="https://api.mch.weixin.qq.com/pay/unifiedorder";
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
        h5parameter.put("appid", appid);
        h5parameter.put("noncestr", nonce_str);
       h5parameter.put("package","Sign=WXPay");
//        h5parameter.put("package",Package);
        h5parameter.put("partnerid",mch_id);
        h5parameter.put("prepayid", prepay_id);
        h5parameter.put("timestamp", timeStamp);

        String Paysign = Sign.createSign(h5parameter, Key);
        weChatPayResultBean = new WeChatPayResultBean();
        weChatPayResultBean.setPartnerId(mch_id);
        weChatPayResultBean.setNonceStr(nonce_str);
        weChatPayResultBean.setPackage2("Sign=WXPay");
        weChatPayResultBean.setPrepayId(prepay_id);
        weChatPayResultBean.setTimeStamp(timeStamp);
        weChatPayResultBean.setSign(Paysign);
        weChatPayResultBean.setTrade_type(trade_type);
//        weChatPayResultBean.setTrade_type("NATIVE");
        weChatPayResultBean.setOut_trade_no(out_trade_no);
        weChatPayResultBean.setTotal_fee(total_fee);
        weChatPayResultBean.setTotal_fee1(fee);
        return weChatPayResultBean;
    }


    public WeChatPayResultBean getWeChatPayInfoPC(Double fee,int total_fee,String order_no,String url) {
        System.out.println(total_fee);
        String notify_url = url;
        PayCallBackBean payCallBackBean = new PayCallBackBean();
        //生成随机字符串
        String nonce_str = PayUtil.getRandomString(6);
        //订单号
        String out_trade_no = order_no;
        payCallBackBean.setOrdercode(out_trade_no);
        payCallBackBean.setPayway("微信支付");


        SortedMap<Object,Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("nonce_str", nonce_str);
//        parameters.put("trade_type",trade_type);
        parameters.put("trade_type","NATIVE");
        parameters.put("notify_url", notify_url);
        parameters.put("total_fee",total_fee);
        parameters.put("body",body);
        parameters.put("spbill_create_ip", "60.8.218.156");
        parameters.put("out_trade_no", out_trade_no);

        //创建签名
        String sign= Sign.createSign(parameters,Key);

        //创建预支付相关信息
        Pay pay = new Pay();
        pay.setAppid(appid);
        pay.setBody(this.body);
        pay.setMch_id(mch_id);
        pay.setNonce_str(nonce_str);
        pay.setNotify_url(notify_url);
        pay.setOut_trade_no(out_trade_no);
        pay.setSign(sign);
        pay.setSpbill_create_ip("60.8.218.156");
//        pay.setTrade_type(trade_type);
        pay.setTrade_type("NATIVE");
        pay.setTotal_fee(total_fee);

        Xstreamutil.stream.alias("xml", Pay.class);
        String xml=Xstreamutil.stream.toXML(pay).replace("__", "_");
        System.out.println("-----"+xml);
        String requestUrl ="https://api.mch.weixin.qq.com/pay/unifiedorder";
        String requestxml = null;
        String prepay_id = null;
        String code_url = null;
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
                if("code_url".equals(elementList.get(i).getName())){
                    code_url = elementList.get(i).getTextTrim();
                    if(code_url == null){
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
        h5parameter.put("appid", appid);
        h5parameter.put("noncestr", nonce_str);
        h5parameter.put("package","Sign=WXPay");
//        h5parameter.put("package",Package);
        h5parameter.put("partnerid",mch_id);
        h5parameter.put("prepayid", prepay_id);
        h5parameter.put("timestamp", timeStamp);

        String Paysign = Sign.createSign(h5parameter, Key);
        weChatPayResultBean = new WeChatPayResultBean();
        weChatPayResultBean.setPartnerId(mch_id);
        weChatPayResultBean.setNonceStr(nonce_str);
        weChatPayResultBean.setPackage2("Sign=WXPay");
        weChatPayResultBean.setPrepayId(prepay_id);
        weChatPayResultBean.setTimeStamp(timeStamp);
        weChatPayResultBean.setSign(Paysign);
//        weChatPayResultBean.setTrade_type(trade_type);
        weChatPayResultBean.setTrade_type("NATIVE");
        weChatPayResultBean.setOut_trade_no(out_trade_no);
        weChatPayResultBean.setTotal_fee(total_fee);
        weChatPayResultBean.setTotal_fee1(fee);
        weChatPayResultBean.setCodeUrl(code_url);
        return weChatPayResultBean;
    }
}
