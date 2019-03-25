package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.dao.ProductDao;
import com.tangmo.emall.dao.TrendDao;
import com.tangmo.emall.entity.Product;
import com.tangmo.emall.entity.RsFile;
import com.tangmo.emall.entity.Trend;
import com.tangmo.emall.entity.TrendAdvertising;
import com.tangmo.emall.service.TrendService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("trendService")
public class TrendServiceImpl implements TrendService {

    @Resource
    private TrendDao trendDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Resource
    private FileDao fileDao;

    @Resource
    private ProductDao productDao;

    @Override
    public Result getTrendList(Integer pageNo,Integer pageSize) {
        try {

            if(pageNo != null && pageSize != null){
                PageHelper.startPage(pageNo,pageSize);
            }

            PageInfo<TrendAdvertising> pageInfo = new PageInfo<>(trendDao.getTrendList());

            return ResultUtil.success(pageInfo);
        }catch (Exception e){
            System.out.println("获取趋势列表接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updTrend(TrendAdvertising trendAdvertising) {
        try {

            if(trendAdvertising == null || trendAdvertising.getTaId() == null){
                return ResultUtil.paramError();
            }

            //校验趋势是否存在
            TrendAdvertising trendAdvertising1 = trendDao.getTrendById(trendAdvertising.getTaId());

            if(trendAdvertising1 == null){
                return ResultUtil.dataNoError();
            }

            if(trendAdvertising.getLocation() != null){
                if(!trendAdvertising.getLocation().toString().equals(trendAdvertising1.getLocation().toString())){
                    //校验是否存在描述语位置
                    if(trendDao.getTrendCountByLocation(trendAdvertising.getLocation()) >= 1){
                        return ResultUtil.error("位置样式重复,请更换样式后重试");
                    }
                }
            }

            if(trendAdvertising.getImageIdList() != null){
                if(trendAdvertising.getImageIdList().length != 0){

                    String imgs = "";

                    for (String img1: trendAdvertising1.getImages()) {
                        fileDao.updFileD(img1);
                    }

                    for (String img: trendAdvertising.getImageIdList()) {

                        //校验图片
                        RsFile rsFile = fileDao.getFileById(img);

                        if(rsFile == null){
                            return ResultUtil.imgError();
                        }

                        //修改图片为已用状态
                        fileDao.updFile(img);

                        if(imgs.equals("")){
                            imgs = img;
                        }else{
                            imgs = imgs + "," +img;
                        }
                    }
                    trendAdvertising.setAdvertisingImage(imgs);
                }
            }

            //修改趋势信息
            trendDao.updTrend(trendAdvertising);

            //清理缓存
            String key = "TrendList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success();

        }catch (Exception e){
            System.out.println("修改趋势列表接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result addTrend(TrendAdvertising trendAdvertising) {
        try {

            if(trendAdvertising == null || trendAdvertising.getAdvertisingImage() == null || trendAdvertising.getDescript() == null
                || trendAdvertising.getLocation() == null || trendAdvertising.getTitle() == null){
                return ResultUtil.paramError();
            }

            //校验是否存在描述语位置
            if(trendDao.getTrendCountByLocation(trendAdvertising.getLocation()) >= 1){
                return ResultUtil.error("位置样式重复,请更换样式后重试");
            }

            //校验图片
            RsFile rsFile = fileDao.getFileById(trendAdvertising.getAdvertisingImage());

            if(rsFile == null){
                return ResultUtil.imgError();
            }

            //修改图片为已用状态
            fileDao.updFile(trendAdvertising.getAdvertisingImage());

            //添加趋势信息
            trendDao.addTrend(trendAdvertising);

            //清理缓存
            String key = "TrendList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success(trendAdvertising.getTaId());
        }catch (Exception e){
            System.out.println("添加趋势列表接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delTrend(Integer taId) {
        try {

            if(taId == null){
                return ResultUtil.paramError();
            }

            TrendAdvertising trendAdvertising = trendDao.getTrendById(taId);

            if(trendAdvertising == null){
                return ResultUtil.dataNoError();
            }

            //删除图片
            fileDao.delFile(trendAdvertising.getAdvertisingImage());

            //删除趋势信息
            trendDao.delTrend(trendAdvertising.getTaId());

            //删除趋势商品
            trendDao.delTrendProductByTaId(trendAdvertising.getTaId());

            //清理缓存
            String key = "TrendList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除趋势信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelTrend(TrendAdvertising trendAdvertising) {
        try {

            if(trendAdvertising == null || trendAdvertising.getTaIdList() == null){
                return ResultUtil.paramError();
            }
            if(trendAdvertising.getTaIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (Integer taId: trendAdvertising.getTaIdList()) {
                TrendAdvertising trendAdvertising1 = trendDao.getTrendById(taId);

                if(trendAdvertising1 != null){

                    //删除图片
                    fileDao.delFile(trendAdvertising1.getAdvertisingImage());

                    //删除趋势信息
                    trendDao.delTrend(taId);

                    //删除趋势商品
                    trendDao.delTrendProductByTaId(taId);
                }

            }

            //清理缓存
            String key = "TrendList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除趋势信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result addTrendProduct(Trend trend) {
        try {
            if(trend == null || trend.getTaId() == null || trend.getProductIdList() == null){
                return ResultUtil.paramError();
            }
            if(trend.getProductIdList().length == 0){
                return ResultUtil.paramError();
            }

            //校验趋势是否存在
            TrendAdvertising trendAdvertising1 = trendDao.getTrendById(trend.getTaId());
            if(trendAdvertising1 == null){
                return ResultUtil.dataNoError();
            }

            Trend trend1 = new Trend();
            trend1.setTaId(trend.getTaId());
            int i = 0;
            int j = 0;
            String name = "";
            for (Integer productId: trend.getProductIdList()) {
                //校验商品是否存在
                Product product = productDao.getProductById(productId);
                if(product != null){
                    j++;
                    trend1.setProductId(productId);
                    //添加趋势商品
                    trendDao.addTrendProduct(trend1);
                }else{
                    i++;
                    if(name.equals("")){
                        name = product.getProductName();
                    }else{
                        name = name+","+product.getProductName();
                    }
                }
            }
            if(i == 0){
                return ResultUtil.success("添加成功");
            }else{
                return ResultUtil.error("成功添加"+j+"个商品，添加异常"+i+"个商品，异常商品名为："+name);
            }

        }catch (Exception e){
            System.out.println("添加趋势商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchAddTrendProduct(Trend trend) {
        try {
            if(trend == null || trend.getProductIdList() == null || trend.getTaId() == null){
                return ResultUtil.paramError();
            }
            if(trend.getProductIdList().length == 0){
                return ResultUtil.paramError();
            }

            //校验趋势是否存在
            TrendAdvertising trendAdvertising1 = trendDao.getTrendById(trend.getTaId());
            if(trendAdvertising1 == null){
                return ResultUtil.dataNoError();
            }

            Trend trend1 = new Trend();
            trend1.setTaId(trend.getTaId());
            for (Integer productId: trend.getProductIdList()) {

                //校验商品是否存在
                Product product = productDao.getProductById(productId);
                if(product != null){
                    trend1.setProductId(productId);
                    //添加趋势商品
                    trendDao.addTrendProduct(trend1);
                }
            }

            return ResultUtil.success("添加成功");
        }catch (Exception e){
            System.out.println("添加趋势商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delTrendProduct(Integer ttId) {
        try {
            if(ttId == null){
                return ResultUtil.paramError();
            }

            //删除趋势商品
            trendDao.delTrendProduct(ttId);

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除趋势商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelTrendProduct(Trend trend) {
        try {
            if(trend == null || trend.getTtIdList() == null){
                return ResultUtil.paramError();
            }
            if(trend.getTtIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (Integer ttId: trend.getTtIdList()) {

                //删除趋势商品
                trendDao.delTrendProduct(ttId);
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除趋势商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getTrendProductList(Integer taId,Integer pageNo,Integer pageSize) {
        try {
            if(taId == null || pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }

            //校验趋势是否存在
            TrendAdvertising trendAdvertising = trendDao.getTrendById(taId);
            if(trendAdvertising == null){
                return ResultUtil.dataNoError();
            }

            PageHelper.startPage(pageNo,pageSize);

            List<Product> products = productDao.getProductByTaId(taId);

            PageInfo<Product> page = new PageInfo<>(products);

            return ResultUtil.success(page);
        }catch (Exception e){
            System.out.println("获取趋势商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
