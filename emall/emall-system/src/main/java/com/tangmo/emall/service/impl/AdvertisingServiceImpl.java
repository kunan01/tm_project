package com.tangmo.emall.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tangmo.emall.dao.AdvertisingDao;
import com.tangmo.emall.dao.CateGoryDao;
import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.dao.ProductDao;
import com.tangmo.emall.entity.*;
import com.tangmo.emall.service.AdvertisingService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("advertisingService")
public class AdvertisingServiceImpl implements AdvertisingService {

    @Resource
    private AdvertisingDao advertisingDao;

    @Resource
    private FileDao fileDao;

    @Resource
    private ProductDao productDao;

    @Resource
    private CateGoryDao cateGoryDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    public Result getAdvertisingList(Integer pageNo,Integer pageSize) {
        try {

            if(pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }

            PageHelper.startPage(pageNo,pageSize);

            List<RecommendAdvertising> recommendAdvertisingList = advertisingDao.getAdvertisingList();
            for (RecommendAdvertising recommend: recommendAdvertisingList) {
                if(recommend.getLocation().toString().equals("5") || recommend.getLocation().toString().equals("6")){
                    Product product = productDao.getProductById(recommend.getProductId());
                    if(product != null){
                        recommend.setProduct(product);
                    }
                }
            }

            PageInfo<RecommendAdvertising> page = new PageInfo<>(recommendAdvertisingList);

            return ResultUtil.success(page);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("获取热门活动广告接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result addAdvertising(RecommendAdvertising recommendAdvertising) {
        try {

            if(recommendAdvertising == null || recommendAdvertising.getAdvertisingImage() == null
                    || recommendAdvertising.getAdvertisingImage().equals("")){
                return ResultUtil.paramError();
            }

            //校验图片
            RsFile rsFile = fileDao.getFileById(recommendAdvertising.getAdvertisingImage());

            if(rsFile == null){
                return ResultUtil.imgError();
            }

            //修改图片为已用状态
            fileDao.updFile(recommendAdvertising.getAdvertisingImage());

            //添加热门广告
            advertisingDao.addAdvertising(recommendAdvertising);

            //清理缓存
            String key = "AdvertisingList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("添加成功");
        }catch (Exception e){
            System.out.println("添加热门活动广告接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updateAdvertising(RecommendAdvertising recommendAdvertising) {
        try {

            if(recommendAdvertising == null || recommendAdvertising.getRaId() == null){
                return ResultUtil.paramError();
            }

            //校验广告是否存在
            RecommendAdvertising recommendAdvertising1 = advertisingDao.getAdvertisingById(recommendAdvertising.getRaId());

            if(recommendAdvertising1 == null){
                return ResultUtil.dataNoError();
            }

            if(!StringUtil.isEmpty(recommendAdvertising.getAdvertisingImage())){
                if(!recommendAdvertising1.getAdvertisingImage().equals(recommendAdvertising.getAdvertisingImage())){
                    //校验图片
                    RsFile rsFile = fileDao.getFileById(recommendAdvertising.getAdvertisingImage());

                    if(rsFile == null){
                        return ResultUtil.imgError();
                    }
                    //删除原有图片
                    fileDao.delFile(recommendAdvertising1.getAdvertisingImage());

                    //修改图片为已用状态
                    fileDao.updFile(recommendAdvertising.getAdvertisingImage());
                }

            }

            RecommendAdvertising advertising = new RecommendAdvertising();
            advertising.setRaId(recommendAdvertising1.getRaId());
            advertising.setAdvertisingImage(recommendAdvertising.getAdvertisingImage());

            if(recommendAdvertising1.getLocation().toString().equals("5") || recommendAdvertising1.getLocation().toString().equals("6")){
                advertising.setProductId(recommendAdvertising.getProductId());
            }else{
                advertising.setTitle(recommendAdvertising.getTitle());
                advertising.setDescript(recommendAdvertising.getDescript());
            }

            //修改活动广告
            advertisingDao.updateAdvertising(advertising);

            //清理缓存
            String key = "AdvertisingList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("修改成功");
        }catch (Exception e){
            System.out.println("修改热门活动广告接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delAdvertising(Integer raId) {
        try {

            if(raId == null){
                return ResultUtil.paramError();
            }

            //校验广告是否存在
            RecommendAdvertising recommendAdvertising1 = advertisingDao.getAdvertisingById(raId);

            if(recommendAdvertising1 == null){
                return ResultUtil.dataNoError();
            }

            //删除图片
            fileDao.delFile(recommendAdvertising1.getAdvertisingImage());

            //删除广告
            advertisingDao.delAdvertising(raId);

            //删除广告下对应的商品数据
            advertisingDao.delAdvertisingProduct(raId);

            //清理缓存
            String key = "AdvertisingList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("修改成功");
        }catch (Exception e){
            System.out.println("修改热门活动广告接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelAdvertising(RecommendAdvertising recommendAdvertising) {
        try {

            if(recommendAdvertising == null || recommendAdvertising.getRaIdList() == null){
                return ResultUtil.paramError();
            }
            if(recommendAdvertising.getRaIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (int i = 0;i<recommendAdvertising.getRaIdList().length;i++){
                //校验广告是否存在
                RecommendAdvertising recommendAdvertising1 = advertisingDao.getAdvertisingById(recommendAdvertising.getRaIdList()[i]);

                if(recommendAdvertising1 != null){

                    //删除图片
                    fileDao.delFile(recommendAdvertising1.getAdvertisingImage());

                    //删除广告
                    advertisingDao.delAdvertising(recommendAdvertising.getRaIdList()[i]);

                    //删除广告下对应的商品数据
                    advertisingDao.delAdvertisingProduct(recommendAdvertising.getRaIdList()[i]);

                }
            }

            //清理缓存
            String key = "AdvertisingList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除热门活动广告接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result addAdvertisingProduct(Recommend recommend) {
        try {

            if(recommend == null || recommend.getProductIdList() == null || recommend.getRaId() == null){
                return ResultUtil.paramError();
            }
            if(recommend.getProductIdList().length == 0){
                return ResultUtil.paramError();
            }

            //校验广告
            RecommendAdvertising advertising = advertisingDao.getAdvertisingById(recommend.getRaId());
            if(advertising == null){
                return ResultUtil.dataNoError();
            }
            Recommend recommend1 = new Recommend();

            recommend1.setRaId(recommend.getRaId());

            int i = 0;
            int j = 0;
            String name = "";
            for (Integer productId: recommend.getProductIdList()) {
                //校验商品
                Product product = productDao.getProductById(productId);
                if(product != null){
                    i++;
                    recommend1.setProductId(productId);
                    advertisingDao.addAdvertisingProduct(recommend1);
                }else{
                    j++;
                    if(name.equals("")){
                        name = product.getProductName();
                    }else{
                        name = name + product.getProductName();
                    }
                }
            }
            if(j == 0){
                return ResultUtil.success("添加成功");
            }else{
                return ResultUtil.error("成功添加"+i+"个商品，添加异常"+j+"个商品，异常商品名为："+name);
            }
        }catch (Exception e){
            System.out.println("添加热门活动广告接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchAddAdvertisingProduct(Recommend recommend) {
        try {

            if(recommend == null || recommend.getRaId() == null || recommend.getProductIdList() == null){
                return ResultUtil.paramError();
            }
            if(recommend.getProductIdList().length == 0){
                return ResultUtil.paramError();
            }

            //校验广告
            RecommendAdvertising advertising = advertisingDao.getAdvertisingById(recommend.getRaId());
            if(advertising == null){
                return ResultUtil.dataNoError();
            }
            Recommend recommend1 = new Recommend();
            recommend1.setRaId(recommend.getRaId());

            for (Integer productId: recommend.getProductIdList()) {
                //校验商品
                Product product = productDao.getProductById(productId);
                if(product != null){
                    recommend1.setProductId(productId);
                    advertisingDao.addAdvertisingProduct(recommend1);
                }
            }

            return ResultUtil.success("添加成功");
        }catch (Exception e){
            System.out.println("添加热门活动广告接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delAdvertisingProduct(Integer trId) {
        try {

            if(trId == null){
                return ResultUtil.paramError();
            }

            Recommend recommend = advertisingDao.getRecommendById(trId);
            if(recommend == null){
                return ResultUtil.dataNoError();
            }

            //删除热门广告商品
            advertisingDao.delAdvertisingProductById(trId);

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除热门活动广告接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelAdvertisingProduct(Recommend recommend) {
        try {

            if(recommend == null || recommend.getTrIdList() == null){
                return ResultUtil.paramError();
            }
            if(recommend.getTrIdList().length == 0){
                return ResultUtil.paramError();
            }
            for (Integer trId: recommend.getTrIdList()) {
                Recommend recommend1 = advertisingDao.getRecommendById(trId);
                if(recommend1 != null){
                    //删除热门广告商品
                    advertisingDao.delAdvertisingProductById(trId);
                }
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("批量删除热门活动广告接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getAdvertisingProductList(Integer raId,Integer pageNo,Integer pageSize) {
        try {

            if(raId == null || pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }

            PageHelper.startPage(pageNo,pageSize);
            List<Product> products = productDao.getProductByRaId(raId);

            PageInfo<Product> page = new PageInfo<>(products);
            return ResultUtil.success(page);
        }catch (Exception e){
            System.out.println("获取热门活动商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
