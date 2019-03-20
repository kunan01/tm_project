package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.DistributionDao;
import com.tangmo.emall.entity.ShippingInfo;
import com.tangmo.emall.service.DistributionService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("distributionService")
public class DistributionServiceImpl implements DistributionService {

    @Resource
    private DistributionDao distributionDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    public Result getStoreDistribution(Integer shopId,Integer pageNo,Integer pageSize) {
        try{
            if(shopId == null || pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }
            PageHelper.startPage(pageNo,pageSize);

            PageInfo<ShippingInfo> page = new PageInfo<>(distributionDao.getStoreDistribution(shopId));

            return ResultUtil.success(page);
        }catch (Exception e){
            System.out.println("获取店铺配送方式接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updateDistribution(ShippingInfo shippingInfo) {
        try{
            if(shippingInfo == null || shippingInfo.getShipId() == null){
                return ResultUtil.paramError();
            }

            ShippingInfo shippingInfo1 = distributionDao.getDistributionById(shippingInfo.getShipId());

            if(shippingInfo1 == null){
                return ResultUtil.dataNoError();
            }

            if(shippingInfo.getPrice() == null && (shippingInfo.getDescript() == null || shippingInfo.getDescript().equals(""))){
                return ResultUtil.success("修改成功");
            }

            distributionDao.updateDistribution(shippingInfo);

            String rediskey = "StoreDistribution";
            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("修改成功");
        }catch (Exception e){
            System.out.println("修改店铺配送方式接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result addDistribution(ShippingInfo shippingInfo) {
        try{
            if(shippingInfo == null || shippingInfo.getShopId() == null || shippingInfo.getDescript() == null
                    || shippingInfo.getPrice() == null){
                return ResultUtil.paramError();
            }

            if(shippingInfo.getDescript().equals("")){
                return ResultUtil.paramError();
            }

            distributionDao.addDistribution(shippingInfo);
            String rediskey = "StoreDistribution";
            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("添加成功");
        }catch (Exception e){
            System.out.println("添加店铺配送方式接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delDistribution(Integer shipId) {
        try{
            if(shipId == null){
                return ResultUtil.paramError();
            }

            ShippingInfo shippingInfo = distributionDao.getDistributionById(shipId);

            if(shippingInfo == null){
                return ResultUtil.dataNoError();
            }

            distributionDao.delDistribution(shipId);

            String rediskey = "StoreDistribution";
            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除店铺配送方式接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelDistribution(ShippingInfo shippingInfos) {
        try{
            if(shippingInfos == null || shippingInfos.getShipIdList() == null){
                return ResultUtil.paramError();
            }
            if(shippingInfos.getShipIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (int i = 0;i<shippingInfos.getShipIdList().length;i++){
                ShippingInfo shippingInfo = distributionDao.getDistributionById(shippingInfos.getShipIdList()[i]);

                if(shippingInfo != null){
                    distributionDao.delDistribution(shippingInfos.getShipIdList()[i]);
                }
            }

            String rediskey = "StoreDistribution";
            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("批量删除店铺配送方式接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
