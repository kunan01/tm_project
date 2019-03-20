package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.CollectDao;
import com.tangmo.emall.dao.ProductDao;
import com.tangmo.emall.entity.Collect;
import com.tangmo.emall.entity.TrendAdvertising;
import com.tangmo.emall.service.CollectService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("collectService")
public class CollectServiceImpl implements CollectService {

    @Resource
    private ProductDao productDao;

    @Resource
    private CollectDao collectDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    @Transactional
    public Result AddCollection(Collect collect) {
        try {

            if(collect == null || collect.getProductId() == null || collect.getUserId() == null || collect.getDataFailure() == null){
                return ResultUtil.paramError();
            }

            if(collect.getDataFailure().toString().equals("1")){
                //校验当前商品是否已经收藏
                Collect collect1 = collectDao.getCollectByUserIdAndPId(collect);

                if(collect1 != null){
                    return ResultUtil.dataError();
                }

                //添加收藏
                collectDao.addCollect(collect);
            }else{
                //校验当前商品是否已经收藏
                Collect collect1 = collectDao.getCollectByUserIdAndPId(collect);

                if(collect1 == null){
                    return ResultUtil.dataNoError();
                }

                collectDao.delCollect(collect1.getCollectId());
            }

            String rediskey = "CollectionList"+collect.getUserId();
            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey.getBytes());
            }
            return ResultUtil.success("收藏成功");
        }catch (Exception e){
            log.error("收藏模块: '添加收藏'接口异常 :"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delCollection(Integer userId, String collectId) {
        try {

            if(userId == null || collectId == null){
                return ResultUtil.paramError();
            }
            String[] strs = collectId.split(",");
            for (int i = 0; i < strs.length; i++){
                Collect collect = collectDao.getCollectById(Integer.parseInt(strs[i]));

                if(collect == null){
                    throw new RuntimeException();
                }

                if(collect.getUserId() != userId){
                    throw new RuntimeException();
                }

                //删除收藏
                collectDao.delCollect(collect.getCollectId());
            }

            String rediskey = "CollectionList"+userId;
            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey.getBytes());
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            log.error("收藏模块: '删除收藏'接口异常 :"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result EmptyCollection(Integer userId) {
        try {
            if(userId == null){
                return ResultUtil.paramError();
            }

            //清空收藏
            collectDao.clearCollect(userId);

            String rediskey = "CollectionList"+userId;
            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey.getBytes());
            }

            return ResultUtil.success("清空完成");
        }catch (Exception e){
            log.error("收藏模块: '清空收藏'接口异常 :"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result QueryCollectionList(Integer userId,Integer pageNo,Integer pageSize) {
        try {
            //非空校验
            if(userId == null){
                return ResultUtil.paramError();
            }

            String rediskey = "CollectionList"+userId;
            if(!jedisKeys.exists(rediskey)){
                List<Collect> collects = collectDao.getCollectListByUserId(userId);

                PageHelper.startPage(pageNo,pageSize);

                //分页查询用户收藏信息
                PageInfo<Collect> pageInfo = new PageInfo<Collect>(collects);

                //查询收藏中的商品信息
                List<Collect> list = pageInfo.getList();
                if(list != null){

                    for (int i = 0; i< list.size();i++){
                        list.get(i).setProduct(productDao.getProductById(list.get(i).getProductId()));
                    }
                    pageInfo.setList(list);
                }

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(collects));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);
                return ResultUtil.success(pageInfo);
            }else{
                List<Collect> collects = (List<Collect>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));

                PageHelper.startPage(pageNo,pageSize);

                //分页查询用户收藏信息
                PageInfo<Collect> pageInfo = new PageInfo<Collect>(collects);

                //查询收藏中的商品信息
                List<Collect> list = pageInfo.getList();
                if(list != null){

                    for (int i = 0; i< list.size();i++){
                        list.get(i).setProduct(productDao.getProductById(list.get(i).getProductId()));
                    }
                    pageInfo.setList(list);
                }
                return ResultUtil.success(pageInfo);
            }

        }catch (Exception e){
            log.error("收藏模块: '查询收藏'接口异常 :"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
