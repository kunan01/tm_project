package com.tangmo.zhygzhglxt.service.Impl;

import com.tangmo.zhygzhglxt.dao.*;
import com.tangmo.zhygzhglxt.entity.*;
import com.tangmo.zhygzhglxt.enums.NotifyUrlConst;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.PayService;
import com.tangmo.zhygzhglxt.utility.Result;
import com.tangmo.zhygzhglxt.utility.wechat.HttpsRequest;
import com.tangmo.zhygzhglxt.utility.wechat.PayUtil;
import com.tangmo.zhygzhglxt.utility.wechat.Sign;
import com.tangmo.zhygzhglxt.utility.wechat.Xstreamutil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author chengge
 * @date 18/12/24
 * @description
 */
@Service("payService")
public class PayServiceImpl implements PayService {

    @Autowired
    private TbPassengerOrderMapper tbPassengerOrderMapper;  //乘客订单

    @Autowired
    private TbDriverOrderMapper tbDriverOrderMapper;  //乘客订单

    @Autowired
    private PayDao payDao;

    @Autowired
    private TbSysUserMapper tbSysUserMapper;  //用户

    @Autowired
    private TbOrderRouteMapper tbOrderRouteMapper;  //订单路线


    //    石泉的
    String Key = "xiaoli66477519913772238082xiaoli";//需要修改
    String appid = "wxb2324a22119b07bd"; //应用ID 必填：true//需要修改
    String mch_id = "1520617671";//商户号    必填：true //需要修改
    //    众惠的
//    String Key = "C0FB5394A44C380BE3297B69A23A7D3D";
//    String appid = "wx7329bea10eb17a5e"; //应用ID 必填：true
//    String mch_id = "1495087612";//商户号    必填：true
    String spbill_create_ip = "114.115.211.170"; //终端IP 必填：true
    String device_info = "WEB";//设备号  必填：false
    String body = "司机线上收款";//商品描述 必填：true
    String trade_type = "APP";//交易类型  必填：true

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

        Pay pay = payDao.selectByTradeNo(tradeNo);

        if (pay.getResult_code() == null) {
            return new Result(ResultCode.PENDING);
        } else {
            if (pay.getResult_code().equals("SUCCESS")) {
                return new Result(ResultCode.SUCCESS);
            } else {
                return new Result(ResultCode.INTERNAL_ERROR, pay.getReturn_msg());
            }
        }
    }

    @Override
    @Transactional
    public Result payOrder(String userCode, String passengerOrderCode) {

        //查询订单信息
        TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selOrderDetailByOrderCode(passengerOrderCode);

        if (tbPassengerOrder == null) {
            return new Result(ResultCode.FAIL, "订单不存在！");
        }

        System.out.println("=====" + tbPassengerOrder.getPrice());
        BigDecimal decimal = new BigDecimal(100);
        int fee = (tbPassengerOrder.getPrice().multiply(decimal)).intValue();

        String orderNo = tbPassengerOrder.getOrderNumber();//得到订单号

        WeChatPayResultBean payResultBean = getWeChatPayInfo(fee, orderNo, NotifyUrlConst.ORDER_URL);//价格，订单号，回调地址

        if (payResultBean == null) {
            return new Result(ResultCode.FAIL, "微信服务故障");
        }

        Pay result = payDao.selectByTradeNo(payResultBean.getOut_trade_no());
        //先查询是否有相同订单
        if (result == null) {
            payResultBean.setUserCode(userCode);
            Pay pay = new Pay(payResultBean);
            pay.setPayTarget("司机线上收款");
            pay.setPayType("1");
            pay.setTotal_fee(fee);
            addPayInfo(pay);
        }
        return new Result(ResultCode.SUCCESS, payResultBean);
    }


    @Override
    public WeChatPayResultBean getWeChatPayInfo(int total_fee, String order_no, String url) {

        String notify_url = url;//回调地址

        PayCallBackBean payCallBackBean = new PayCallBackBean();

        //生成随机字符串
        String nonce_str = PayUtil.getRandomString(6);

        //订单号
        String out_trade_no = order_no;
        payCallBackBean.setOrdercode(out_trade_no);
        payCallBackBean.setPayway("微信支付");


        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("nonce_str", nonce_str);
        parameters.put("trade_type", trade_type);
        parameters.put("notify_url", notify_url);
        parameters.put("total_fee", total_fee);
        parameters.put("body", body);
        parameters.put("spbill_create_ip", spbill_create_ip);
        parameters.put("out_trade_no", out_trade_no);

        //创建签名
        String sign = Sign.createSign(parameters, Key);
        System.out.println("=============第一步创建签名完成！====================");
        System.out.println("签名1===：" + sign);
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
        pay.setTotal_fee(total_fee);

        Xstreamutil.stream.alias("xml", Pay.class);
        String xml = Xstreamutil.stream.toXML(pay).replace("__", "_");
        String requestUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String requestxml = null;
        String prepay_id = null;
        System.out.println("=============第二步开始创建预支付信息！====================");
        try {
            requestxml = new HttpsRequest().HttpsRequest(requestUrl, "POST", xml);
            System.out.println("+++++++++++" + requestxml + "=============");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            Document doc = DocumentHelper.parseText(requestxml);
            Element element_xml = doc.getRootElement();
            List<Element> elementList = element_xml.elements();
            for (int i = 0; i < elementList.size(); i++) {
                System.out.println(elementList.get(i).getName() + " " + elementList.get(i).getText());
                if ("prepay_id".equals(elementList.get(i).getName())) {
                    prepay_id = elementList.get(i).getTextTrim();
                    if (prepay_id == null) {
                        return null;
                    }
                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }

        Long timeStamp = System.currentTimeMillis() / 1000;
        //String Package = "prepay_id="+prepay_id;
        SortedMap<Object, Object> h5parameter = new TreeMap<Object, Object>();
        h5parameter.put("appid", appid);
        h5parameter.put("noncestr", nonce_str);
        h5parameter.put("package", "Sign=WXPay");
        //h5parameter.put("package",Package);
        h5parameter.put("partnerid", mch_id);
        h5parameter.put("prepayid", prepay_id);
        h5parameter.put("timestamp", timeStamp);

        System.out.println("第二次签名开始==========");
        String Paysign = Sign.createSign(h5parameter, Key);
        System.out.println("=============第二次创建签名完成！====================");
        System.out.println("签名2===：" + Paysign);
        weChatPayResultBean = new WeChatPayResultBean();
        weChatPayResultBean.setPartnerId(mch_id);
        weChatPayResultBean.setNonceStr(nonce_str);
        weChatPayResultBean.setPackage2("Sign=WXPay");
        weChatPayResultBean.setPrepayId(prepay_id);
        weChatPayResultBean.setTimeStamp(timeStamp);
        weChatPayResultBean.setSign(Paysign);
        weChatPayResultBean.setTrade_type(trade_type);
        weChatPayResultBean.setOut_trade_no(out_trade_no);
        weChatPayResultBean.setTotal_fee(total_fee);
        return weChatPayResultBean;
    }

    /**
     * 1:回调通知后更新自己的支付信息表,更新result_code为SUCCESS
     * 2:更新乘客订单为已完成司机订单为已完成
     * 3:将司机用户余额增加
     * todo 通知商家
     *
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
        pay.setPayType("1");
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

            if (!"0".equals(tbPassengerOrder.getPayWay())) {
                //司机余额合计增加
                if (tbPassengerOrder.getPrice() != null) {
                    TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selByPassengerOrderCode(tbPassengerOrder.getOrderCode());
                    tbSysUserMapper.updateBanlanceByUserCode(tbDriverOrder.getDriverId(), tbPassengerOrder.getPrice());
                }
            }
        }
//        //商品数量-此次购买数量
//        GoodsOrder goodsOrder = goodsOrderDao.selectByOrderNo(pay.getOut_trade_no());
//        Integer cdId = goodsOrder.getCdId();
//        commodityDao.updateCdCount(cdId,goodsOrder.getGoCount());
//        //增加卖出记录,买入记录
//        SellRecord sellRecord = new SellRecord(goodsOrder);
//        goodsRecordDao.insertSellRecord(sellRecord);
//        BuyRecord buyRecord = new BuyRecord(goodsOrder);
//        goodsRecordDao.insertBuyRecord(buyRecord);
        return 1;
    }


}
