package com.tangmo.zhygzhglxt.service.Impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.tangmo.zhygzhglxt.config.PayConfig;
import com.tangmo.zhygzhglxt.dao.*;
import com.tangmo.zhygzhglxt.entity.Pay;
import com.tangmo.zhygzhglxt.entity.TbBalanceOrder;
import com.tangmo.zhygzhglxt.entity.TbPassengerOrder;
import com.tangmo.zhygzhglxt.entity.TbSysUser;
import com.tangmo.zhygzhglxt.entity.dto.BalanceOrderDto;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.AliwithdrawalService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.OrderRelated;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by chengge on 2019/1/14.
 */
@Service
public class AliwithdrawalServiceImpl implements AliwithdrawalService {

    @Autowired
    private PayDao payDao;

    @Autowired
    private TbBalanceOrderMapper tbBalanceOrderMapper;  //用户余额订单

    @Autowired
    private TbSysUserMapper tbSysUserMapper;  //用户

    @Override
    @Transactional
    public Result aliwithdrawalOrder(BalanceOrderDto balanceOrderDto) {

        if (balanceOrderDto == null) {
            return new Result(ResultCode.FAIL, "参数不能为空！");
        }

        if (balanceOrderDto.getUserCode() == null || balanceOrderDto.getPrice() == null || "".equals(balanceOrderDto.getUserCode()) || "".equals(balanceOrderDto.getPrice())) {
            return new Result(ResultCode.FAIL, "用户的唯一标识不能为空或者价格不能为空！");
        }

        if (balanceOrderDto.getPayeeAccount() == null) {
            return new Result(ResultCode.FAIL, "交易的支付宝账号不能为空！");
        }

        //查询当前用户的余额
        TbSysUser tbSysUser = tbSysUserMapper.selectByCode(balanceOrderDto.getUserCode());

        if (tbSysUser == null) {

            return new Result(ResultCode.FAIL, "该用户不存在！");
        }
        if (balanceOrderDto.getPrice().compareTo(tbSysUser.getBalance()) != -1) {

            return new Result(ResultCode.FAIL, "提现金额不能大于当前余额！");
        }

        String orderNumber = OrderRelated.getOrderIdByUUId();  //订单单号
        String tradeName = "用户发起提现";                    //交易名称

        //生成余额订单表
        TbBalanceOrder tbBalanceOrder = new TbBalanceOrder();

        tbBalanceOrder.setBalanceOrderId(EncryptUtil.get32Uuid());  //主键
        tbBalanceOrder.setBalanceOrderCode(EncryptUtil.get32Uuid());//余额订单的唯一标识
        tbBalanceOrder.setPrice(balanceOrderDto.getPrice());        //余额订单的价格
        tbBalanceOrder.setTradeType("APP");                         //交易类型
        tbBalanceOrder.setUserCode(balanceOrderDto.getUserCode());   //当前用户的唯一标识
        tbBalanceOrder.setUseType("2");                             //使用类型（1微信 2支付宝）
        tbBalanceOrder.setType("2");                                //类型（1充值 2提现）
        tbBalanceOrder.setBalanceNumber(orderNumber);                //订单号
        tbBalanceOrder.setTradeName(tradeName);                      //交易名称
        tbBalanceOrder.setPayeeAccount(balanceOrderDto.getPayeeAccount());//支付宝账号

        int a = tbBalanceOrderMapper.insertSelective(tbBalanceOrder);

        BigDecimal fee = balanceOrderDto.getPrice();            //提现金额
        String orderNo = orderNumber;                           //订单号
        String payeeAccount = balanceOrderDto.getPayeeAccount();//支付宝账号
        a = 1;
        if (a > 0) {                                             //说明添加订单成功
            String payResultBean = getAliWithdrawalInfo(fee, orderNo, payeeAccount, tbSysUser.getCode());//价格，订单号，商品名称

            if (payResultBean == null || "".equals(payResultBean)) {
                return new Result(ResultCode.FAIL, "支付宝服务故障");
            } else {
                //注意事项，看订单号是否在支付时不能重复问题，待商榷测试
                return new Result(ResultCode.SUCCESS, payResultBean);
            }
        } else {
            return new Result(ResultCode.FAIL, "生成订单失败！");
        }
    }


    public String getAliWithdrawalInfo(BigDecimal free, String orderNo, String payeeAccount, String userCode) {

        //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
        AlipayClient alipayClient = new DefaultAlipayClient(PayConfig.URL, PayConfig.APPID, PayConfig.RSA_PRIVATE_KEY, PayConfig.FORMAT, PayConfig.CHARSET, PayConfig.ALIPAY_PUBLIC_KEY, PayConfig.SIGNTYPE);

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：//alipay.trade.app.pay
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();

        //直接封装参数的类开始
        //AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
        //商户转账唯一订单号
        //model.setOutBizNo("2018101149542322343211");
        //收款方账户类型。
        //1、PayeeType=ALIPAY_USERID：PayeeAccount传值pid ,以2088开头的16位纯数字组成。
        //2、PayeeType=ALIPAY_LOGONID：PayeeAccount传值支付宝登录号(邮箱或手机号)
        //model.setPayeeType("ALIPAY_LOGONID");
        //收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户。
        //model.setPayeeAccount("dvnvqi3054@sandbox.com");
        //测试金额必须大于等于0.1，只支持2位小数，小数点前最大支持13位
        //model.setAmount("1.10");
        //当付款方为企业账户且转账金额达到（大于等于）50000元，remark不能为空。
        //model.setRemark("单笔到到支付账户转账备注");
        //request.setBizModel(model);
        //AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
        //直接封装参数的类结束

        //业务参数传入,可以传很多，参考API
        request.setBizContent("{" +
                "\"out_biz_no\":\"" + orderNo + "\"," +           //（必填）订单号（需要修改）
                "\"payee_type\":\"ALIPAY_LOGONID\"," +          //（必填）收款方账户类型。可取值： 1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。 2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。
                "\"payee_account\":\"" + payeeAccount + "\"," +         //（必填需要修改）收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户
                "\"amount\":\"" + free + "\"," +                         //（必填需要修改）转账金额，单位：元。 只支持2位小数，小数点前最大支持13位，金额必须大于等于0.1元
                //"\"payer_real_name\":\"上海交通卡公司\"," +       //付款方真实姓名（最长支持100个英文/50个汉字）
                //"\"payer_show_name\":\"上海交通卡退款\"," +       //付款方姓名（最长支持100个英文/50个汉字）
                //"\"payee_real_name\":\"张三\"," +                 //收款方真实姓名（最长支持100个英文/50个汉字）
                "\"remark\":\"转账备注\"," +                       //转账备注（支持200个英文/100个汉字）
                "\"ext_param\":\"{\\\"order_title\\\":\\\"石泉运管余额提现\\\"}\"" +    //扩展参数，json字符串格式，目前仅支持的key=order_title，表示收款方的转账账单标题，value可以根据自己的业务定制
                "  }");
        //AlipayFundTransToaccountTransferResponse response = null;
        String resultString = "";
        try {

            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
            System.out.println("------" + response.isSuccess());

            if (response.isSuccess()) {  //如果是true代表成功
                BigDecimal bigDecimal = new BigDecimal(-1);
                resultString = response.getBody();//就是orderString 可以直接给APP请求，无需再做处理。
                //如果提现成功将余额减去提现部分
                tbSysUserMapper.updateBanlanceByUserCode(userCode, free.multiply(bigDecimal));
            } else {
                System.out.println("与支付宝交互出错，未能生成订单，请检查代码！");
            }
            System.out.println(resultString);

        } catch (AlipayApiException e) {

            e.printStackTrace();
            System.out.println("与支付宝交互出错，未能生成订单，请检查代码！");
            return null;
        }
        return resultString;
    }

}
