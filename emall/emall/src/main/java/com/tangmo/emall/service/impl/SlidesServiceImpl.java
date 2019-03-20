package com.tangmo.emall.service.impl;

import com.tangmo.emall.dao.SlidesDao;
import com.tangmo.emall.entity.CustomerService;
import com.tangmo.emall.entity.Slides;
import com.tangmo.emall.service.SlidesService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("slidesService")
public class SlidesServiceImpl implements SlidesService {

    @Resource
    private SlidesDao slidesDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public Result getSlidesList() {
        try {
            String key = "SlidesList";
            if(!jedisKeys.exists(key)){
                List<Slides> slides = slidesDao.getSlidesList();
                jedisStrings.set(key.getBytes(), SerializeUtil.serialize(slides));
                //设置过期时间两个小时
                jedisStrings.expire(key.getBytes(),7200);
                return ResultUtil.success(slides);
            }else {
                List<Slides> slides = (List<Slides>)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));

                return ResultUtil.success(slides);
            }

        }catch (Exception e){
            log.error("购物车模块：'获取轮播图接口异常'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
