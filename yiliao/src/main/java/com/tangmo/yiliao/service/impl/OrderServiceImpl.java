package com.tangmo.yiliao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tangmo.yiliao.dao.OrderDao;
import com.tangmo.yiliao.dao.UserDao;
import com.tangmo.yiliao.entity.PayOrder;
import com.tangmo.yiliao.service.OrderService;
import com.tangmo.yiliao.utility.code.OrderRelated;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.pay.HttpsRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author hanjialin
 * @date 2019-1-3
 * @description
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private UserDao userDao;

    @Override
    @Transactional
    public Result addOrder(PayOrder payOrder) {
        if(payOrder == null){
            return ResultUtil.paramError();
        }

        if(payOrder.getGoType().toString().equals("1")){
            //购买商品处理逻辑
        }

        payOrder.setGoId(UUID.randomUUID().toString());
        payOrder.setOrderNumber(OrderRelated.getOrderNo());
        orderDao.addOrder(payOrder);

        return ResultUtil.success(payOrder.getGoId());
    }

    @Override
    public Result getIntegral(Byte type) {

        if(type == null){
            return ResultUtil.paramError();
        }

        return ResultUtil.success(userDao.selectIntegralRules(type));

    }

    @Override
    public Result getUserTypeOpenId(String userId) {

        if(userId == null){
            return ResultUtil.paramError();
        }

        Integer row = userDao.getUserTypeOpenId(userId);

        if(row == 0){
            return ResultUtil.success(false);
        }else{
            return ResultUtil.success(true);
        }
    }

    @Override
    @Transactional
    public Result addUserOpenId(String userId, String code) {
        if(userId == null || code == null){
            return ResultUtil.paramError();
        }

//        String requestxml = null;
//        String openId = null;
//        try {
//            requestxml = new HttpsRequest().HttpsRequest("https://api.weixin.qq.com/sns/jscode2session","GET","appid=wxf2d90e18e6b719b3&secret=ddc46ec98224b740544b303d2f586f73&js_code="+code+"&grant_type=authorization_code");
//            System.out.println(requestxml);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtil.error("微信服务器异常");
//        }
//
//        try {
//            JSONObject jsonObject = JSONObject.parseObject(requestxml);
//            openId = jsonObject.get("openid").toString();
////            Document doc= DocumentHelper.parseText(requestxml);
////            Element element_xml=doc.getRootElement();
////            List<Element> elementList = element_xml.elements();
////            for (int i = 0; i <elementList.size(); i++) {
////                System.out.println(elementList.get(i).getName()+" "+elementList.get(i).getText());
////                if("openid".equals(elementList.get(i).getName())){
////                    openId = elementList.get(i).getTextTrim();
////                }
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if(openId == null){
//            return ResultUtil.error("通过code获取用户openid失败");
//        }

        //更新用户openid
        userDao.addUserOpenId(userId,code);

        return ResultUtil.success();
    }
}
