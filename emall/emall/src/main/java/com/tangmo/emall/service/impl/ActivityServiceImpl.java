package com.tangmo.emall.service.impl;

import com.tangmo.emall.dao.ActivityDao;
import com.tangmo.emall.dao.ProductDao;
import com.tangmo.emall.entity.*;
import com.tangmo.emall.service.ActivityService;
import com.tangmo.emall.utils.*;
import com.tangmo.emall.utils.jedis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityDao activityDao;

    @Resource
    private ProductDao productDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public Result getAdvertisingList() {
        try {

            String rediskey = "AdvertisingList";
            if(!jedisKeys.exists(rediskey)){

                List<RecommendAdvertising> recommendAdvertisings = activityDao.getAdvertisingList();

                if(recommendAdvertisings == null){
                    return ResultUtil.dataNoError();
                }
                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(recommendAdvertisings));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);

                return ResultUtil.success(recommendAdvertisings);
            }else{
                List<RecommendAdvertising> recommendAdvertisings = (List<RecommendAdvertising>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));

                return ResultUtil.success(recommendAdvertisings);
            }

        }catch (Exception e){
            log.error("活动模块 : '获取热门活动广告'接口发成异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getTrendList() {
        try {

            String rediskey = "TrendList";

            List<TrendAdvertising> trendAdvertisings = null;

            if(!jedisKeys.exists(rediskey)){
                trendAdvertisings = activityDao.getTrendList();

                if(trendAdvertisings == null){
                    return ResultUtil.dataNoError();
                }

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(trendAdvertisings));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);

            }else{
                trendAdvertisings = (List<TrendAdvertising>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));

            }

            if(trendAdvertisings != null){
                for (int i = 0;i < trendAdvertisings.size();i++){
                    trendAdvertisings.get(i).setProducts(productDao.getProductByTaId(trendAdvertisings.get(i).getTaId()));
                }
            }

            return ResultUtil.success(trendAdvertisings);
        }catch (Exception e){
            log.error("活动模块 : '获取趋势列表'接口异常 :"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getTimeLimitedAdvertisingList() {
        try {
            List<Discount> discounts = activityDao.getDiscountList();

            return ResultUtil.success(discounts);
        }catch (Exception e){
            log.error("活动模块 : '获取限时活动广告'接口异常 :"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result getExchangeCode(DiscountUser discountUser) {

        try {

            if(discountUser == null || discountUser.getUserId() == null || discountUser.getDisId() == null){
                return ResultUtil.paramError();
            }

            //校验优惠券是否存在
            Discount discounts = activityDao.getDiscountById(discountUser.getDisId());
            if(discounts == null){
                return ResultUtil.dataNoError();
            }

            //校验当前用户是否已经领取
            DiscountUser discountUser1 = activityDao.getDisUserByOne(discountUser.getDisId(),discountUser.getUserId());
            if(discountUser1 != null){
                return ResultUtil.error("当前活动已经领取");
            }

            if(discounts.getIsCount().toString().equals("1")){
                if(discounts.getObjectCount() <= 0){
                    return ResultUtil.error("当前活动已领取完");
                }

                //减去优惠券可用数量
                activityDao.updDisObject(discountUser.getDisId());
            }

            //生成兑换码
            String code = EncryptUtil.get32Uuid()+EncryptUtil.randomPwd();

            discountUser.setConversionCode(code);

            //保存用户兑换码
            activityDao.insertDisUser(discountUser);

            return ResultUtil.success(code);
        }catch (Exception e){
            log.error("活动模块 : '用户获取兑换码'接口异常 :"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getTimeLimitedAdvertisingState() {
        try {
            if(activityDao.getDiscountListCount() > 0){
                return ResultUtil.success(true);
            }else{
                return ResultUtil.success(false);
            }
        }catch (Exception e){
            log.error("活动模块 : '获取当前是否存在限时活动'接口异常 :"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
