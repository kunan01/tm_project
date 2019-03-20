package com.tangmo.emall.service.impl;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.tangmo.emall.dao.OrderDao;
import com.tangmo.emall.entity.OrderDetail;
import com.tangmo.emall.utils.OrderRelated;
import com.tangmo.emall.utils.PaypalUtil;
import com.tangmo.emall.service.PaypalService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hanjialin
 * @date 2019/1/9.
 * @Description
 */
@Slf4j
@Service("paypalService")
public class PaypalServiceImpl implements PaypalService {

    @Resource
    private OrderDao orderDao;

    private APIContext apiContext = PaypalUtil.apiContext();

    private static final String PAYPAL_INTENT = "sale";             //付款意图（sale：立即付款）
    private static final String PAYPAL_PAYMENT_METHOD = "paypal";   //付款方式（paypal：PayPal钱包付款）
    private static final String PAYPAL_CURRENCY = "USD";            //货币类型 （USD：美元）
    private static final String PAYPAL_SUCCESS_URL = "http://114.115.211.170:8080/web/paypal/success";  //成功回调
    private static final String PAYPAL_CANCEL_URL = "http://114.115.211.170:8080/web/paypal/cancel";    //失败回调

    @Override
    @Transactional
    public Result createPayment(Integer userId,String detailId) {
        try {
            List<OrderDetail> orderDetails = new ArrayList<>();

            if(userId == null || detailId == null || detailId.equals("")){
                return ResultUtil.paramError();
            }
            //支付号
            String payNo = OrderRelated.getOrderNo();

            String[] strs = detailId.split(",");

            for (int i = 0;i<strs.length;i++){
                OrderDetail orderDetail = orderDao.getOrderDetailAllById(Integer.parseInt(strs[i]));
                if(!orderDetail.getOrder().getUserId().toString().equals(userId.toString())){
                    return ResultUtil.error("订单身份校验失败");
                }

                if(!orderDetail.getOrder().getOrderStatus().toString().equals("0")){
                    return ResultUtil.error("支付订单校验失败");
                }

                //更新订单支付单号
                orderDao.updPaypalNo(orderDetail.getOrderId(),payNo);

                orderDetails.add(orderDetail);
            }

            if(orderDetails.size() <= 0){
                return ResultUtil.error("订单校验失败");
            }

            String herf = createpaypal(orderDetails, payNo);

            if(herf == null){
                return ResultUtil.error("创建paypal支付失败");
            }else{
                //创建支付成功 返回给前台支付链接地址
                return ResultUtil.success(herf);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("paypal支付: '创建paypal支付'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }

    }


    public String createpaypal(List<OrderDetail> orderDetails,String payNo){

        //多个商品信息
        List<Item> items = new ArrayList<>();

        //地址信息
//        ShippingAddress shippingAddress = new ShippingAddress();

        //总价
        Double totalPrice = null;

        //实付金额
        Double payMoney = null;

        //总税费
        Double sku = 0.00;
        //总运费
        Double Shipping = null;
        //手续费
        Double HandlingFee = 0.00;
        //运输折扣
        Double ShippingDiscount = 0.00;
        //保险费
        Double Insurance = 0.00;

        for (int i = 0; i < orderDetails.size(); i++){

            if(totalPrice == null){
                //orderDetails.get(i).getProductCount() *
                totalPrice = orderDetails.get(i).getOrder().getOrderMoney();
            }else{
                totalPrice = totalPrice + orderDetails.get(i).getOrder().getOrderMoney();
            }

            if(payMoney == null){
                payMoney = orderDetails.get(i).getOrder().getPayMoney();
            }else{
                payMoney = payMoney + orderDetails.get(i).getOrder().getPayMoney();
            }

            if(Shipping == null){
                Shipping = orderDetails.get(i).getOrder().getExpressMoney() + orderDetails.get(i).getPrescriptionPrice();
            }else{
                Shipping = Shipping + orderDetails.get(i).getOrder().getExpressMoney() + orderDetails.get(i).getPrescriptionPrice();
            }

//            商品信息
            Item item = new Item();
            item.setName(orderDetails.get(i).getProductName());
            item.setDescription(orderDetails.get(i).getProduct().getDescript());
            item.setQuantity(orderDetails.get(i).getProductCount().toString());
            item.setPrice(orderDetails.get(i).getProductPrice().toString());
            item.setTax("0.00");//税
            item.setSku(orderDetails.get(i).getProduct().getProductId().toString());
            item.setCurrency("USD");//货币：美元
            items.add(item);
//            if(i == 0){
//                shippingAddress.setRecipientName(orderDetails.get(i).getOrder().getConsignee());
//                shippingAddress.setLine1(orderDetails.get(i).getOrder().getAddressLine1());
//                shippingAddress.setLine2(orderDetails.get(i).getOrder().getAddressLine2());
//                shippingAddress.setCity(orderDetails.get(i).getOrder().getCity());
//                shippingAddress.setCountryCode("US");//国家代码
//                shippingAddress.setPostalCode(orderDetails.get(i).getOrder().getZipCode());
//                shippingAddress.setPhone(orderDetails.get(i).getOrder().getUserPhone());
//                shippingAddress.setState("CA");
//            }

        }

//        totalPrice = payMoney - Shipping;

        //商品总结信息
        Details details = new Details();
        details.setSubtotal(payMoney.toString());//商品总价
        details.setTax(sku.toString());
        details.setShipping(Insurance.toString());
        details.setHandlingFee(HandlingFee.toString());
        details.setShippingDiscount(ShippingDiscount.toString());//-1.00
        details.setInsurance(Insurance.toString());

        //商品货币支持
        Amount amount = new Amount();
        amount.setCurrency(PAYPAL_CURRENCY);//货币
        amount.setTotal(String.format("%.2f", payMoney));//实付金额
        amount.setDetails(details);

        PaymentOptions options = new PaymentOptions();
        options.setAllowedPaymentMethod("INSTANT_FUNDING_SOURCE");//定死 允许支付方式:即时资金来源

        ItemList itemList = new ItemList();
        itemList.setItems(items);
//        itemList.setShippingAddress(shippingAddress);

        Transaction transaction = new Transaction();
        transaction.setDescription("付款说明");
        transaction.setAmount(amount);
//        transaction.setCustom(payNo);//自定义订单编号
        transaction.setInvoiceNumber(payNo);//发票号/自定义订单编号
        transaction.setPaymentOptions(options);
//        transaction.setSoftDescriptor("描述");//描述
//        transaction.setItemList(itemList);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(PAYPAL_PAYMENT_METHOD);

        Payment payment = new Payment();
        payment.setIntent(PAYPAL_INTENT);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();

        redirectUrls.setCancelUrl(PAYPAL_CANCEL_URL);
        redirectUrls.setReturnUrl(PAYPAL_SUCCESS_URL);

        payment.setRedirectUrls(redirectUrls);

        //翻译为：如对您的订单有任何疑问，请与我们联系。
        payment.setNoteToPayer("Contact us for any questions on your order.");

        try {
            Payment payment1 = payment.create(apiContext);
            for(Links links : payment1.getLinks()){
                if(links.getRel().equals("approval_url")){
                    //创建支付成功，返回请求paypal的链接地址
                    return links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public String executePayment(String paymentId, String payerId) {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        try {
            Payment payment1 = payment.execute(apiContext, paymentExecute);
            if(payment1.getState().equals("approved")){
                //支付成功处理逻辑

                //支付单号
                String payNo = payment1.getTransactions().get(0).getInvoiceNumber();

                //通过支付单号修改订单状态信息
                orderDao.updateOrderInformation(payNo);

                //提醒卖家用户已经支付

                return "success";
            }
        } catch (PayPalRESTException e) {
            log.error("paypal支付: 'paypal处理回调'接口异常："+e.getDetails());
            e.printStackTrace();
        }
        return "error";
    }
}
