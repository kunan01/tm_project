package com.tangmo.emall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.ActivityDao;
import com.tangmo.emall.dao.CollectDao;
import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.dao.ProductDao;
import com.tangmo.emall.entity.*;
import com.tangmo.emall.entity.dto.ProductDto;
import com.tangmo.emall.service.ProductService;
import com.tangmo.emall.utils.MailUtil;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Resource
    private ActivityDao activityDao;

    @Resource
    private CollectDao collectDao;

    @Resource
    private FileDao fileDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public Result getProductList(ProductDto dto) {
        try {

            if(dto == null || dto.getPageSize() == null || dto.getPageNo() == null){
                return ResultUtil.paramError();
            }

            if(dto.getSorting() == null || dto.getSorting().toString().equals("")){
                dto.setSorting(null);
                dto.setSortingRules(null);
            }else{
                SortingWay sortingWay = productDao.getSortingWayById(Integer.parseInt(dto.getSorting().toString()));
                if(sortingWay != null){
                    dto.setSortingRules(sortingWay.getSortingRules());
                }
            }

            if(dto.getLIdList() == null || dto.getLIdList().length == 0){
                dto.setLIdList(null);
            }

            if(dto.getCategoryId() != null){
                CateGory cateGory = productDao.getCateGoryByPId(dto.getCategoryId());
                if(cateGory != null){
                    dto.setCategoryLevel(cateGory.getCategoryLevel());
                }
            }

            PageHelper.startPage(dto.getPageNo(),dto.getPageSize());

            PageInfo<Product> page = new PageInfo<>(productDao.getProductListByDto(dto));

            List<Product> productList = page.getList();

            if(productList != null && productList.size() != 0){

                for (int i = 0; i< productList.size(); i++){

                    List<ProductSpec> specs = productDao.getProductSpecListByPId(productList.get(i).getProductId());

                    List<String> key = new ArrayList<>();

                    List<String> value = new ArrayList<>();

                    if(specs != null){
                        for (int j = 0; j<specs.size(); j++){

                            if(dto.getDisType() != null && dto.getDisType() == 0){

                                Double discount = Double.parseDouble(productList.get(i).getDisProportion().toString())/100;

                                specs.get(j).setPreferentialPrice(discount*specs.get(j).getPrice());
                            }

                            String str = specs.get(j).getProductSpecs();

                            str = str.substring(1,str.length()-1);

                            String[] strings = str.split(",");

                            for (int k = 0;k < strings.length; k++){
                                String[] key_value = strings[k].split("\\:");
                                key.add(key_value[0].substring(1,key_value[0].length()-1));
                                value.add(strings[k]);
                            }

                            List<ProductImage> productImages = productDao.getProductImageListBySId(specs.get(j).getSpecId());

                            if(productImages != null){
                                specs.get(j).setProductImages(productImages);
                            }
                        }
                        productList.get(i).setSpecList(specs);
                    }

                    if(key.size() != 0){
                        key = MailUtil.removeDuplicate(key);
                    }

                    if(value.size() != 0){
                        value = MailUtil.removeDuplicate(value);
                        Map<String,List<String>> map = new HashMap<>();
                        if(key.size() != 0){
                            for (int j = 0; j < key.size(); j++){

                                List<String> val = new ArrayList<>();

                                for (int k = 0;k < value.size();k++){

                                    String[] strings = value.get(k).split("\\:");
                                    if(key.get(j).equals(strings[0].substring(1,strings[0].length() - 1))){

                                        val.add(strings[1].substring(1,strings[1].length() - 1));
                                    }
                                }
                                map.put(key.get(j),val);
                            }
                            productList.get(i).setMap(map);
                        }
                    }

                }
                page.setList(productList);
            }
            return ResultUtil.success(page);
        }catch (Exception e){
            log.error("商品模块：'查询商品'接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getProductParamList(Integer shopId) {
        try {

            if(shopId == null){
                return ResultUtil.paramError();
            }

            String rediskey = "ParamList";

            if(!jedisKeys.exists(rediskey)){
                List<ParamType> paramTypes = productDao.getParamTypeListByShopId(shopId);

                if(paramTypes != null){
                    for (int i = 0;i<paramTypes.size();i++){

                        List<ParamValue> paramValues = productDao.getParamValueListByTypeId(paramTypes.get(i).getTypeId());

                        if(paramValues != null){
                            for (int j = 0;j<paramValues.size();j++){
                                paramValues.get(j).setPropCount(productDao.getProductCount(paramValues.get(j).getValueId()));
                            }
                            paramTypes.get(i).setParamValues(paramValues);
                        }
                    }
                }

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(paramTypes));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);

                return ResultUtil.success(paramTypes);
            }else{
                List<ParamType> paramTypes = (List<ParamType>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));
                return ResultUtil.success(paramTypes);
            }

        }catch (Exception e){
            log.error("商品模块：'查询商品标签'接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getProductById(Integer productId,Integer userId) {
        try {

            if(productId == null){
                return ResultUtil.paramError();
            }

            //增加商品访问次数
            productDao.updProductViewCount(productId);

            Map<String,Object> maps = new HashMap<>();

            //商品基本信息
            Product product = productDao.getProductById(productId);

            if(product != null){

                CateGory cateGory = productDao.getCateGoryByPId(product.getCategoryId());
                if(cateGory.getParentId() == 18){
                    product.setIsPrescript(1);
                }else{
                    product.setIsPrescript(0);
                }

                if(userId != null){
                    Collect collect = new Collect();
                    collect.setUserId(userId);
                    collect.setProductId(productId);
                    if(collectDao.getCollectByUserIdAndPId(collect) != null){
                        product.setIsCollection(Byte.parseByte("1"));
                    }else{
                        product.setIsCollection(Byte.parseByte("0"));
                    }
                }else{
                    product.setIsCollection(Byte.parseByte("0"));
                }

                List<ProductSpec> specs = productDao.getProductSpecListByPId(product.getProductId());

                List<String> key = new ArrayList<>();

                List<String> value = new ArrayList<>();

                if(specs != null){
                    for (int j = 0; j<specs.size(); j++){

                        if(product.getDisProportion() != 100){

                            Double discount = Double.parseDouble(product.getDisProportion().toString())/100;

                            specs.get(j).setPreferentialPrice(discount*specs.get(j).getPrice());

                        }

                        String str = specs.get(j).getProductSpecs();

                        str = str.substring(1,str.length()-1);

                        String[] strings = str.split(",");

                        for (int k = 0;k < strings.length; k++){
                            String[] key_value = strings[k].split("\\:");
                            key.add(key_value[0].substring(1,key_value[0].length()-1));
                            value.add(strings[k]);
                        }

                        List<ProductImage> productImages = productDao.getProductImageListBySId(specs.get(j).getSpecId());

                        if(productImages != null){
                            specs.get(j).setProductImages(productImages);
                        }
                    }
                    product.setSpecList(specs);
                }

                if(key.size() != 0){
                    key = MailUtil.removeDuplicate(key);
                }

                if(value.size() != 0){
                    value = MailUtil.removeDuplicate(value);
                    Map<String,List<String>> map = new HashMap<>();
                    if(key.size() != 0){
                        for (int j = 0; j < key.size(); j++){

                            List<String> val = new ArrayList<>();

                            for (int k = 0;k < value.size();k++){

                                String[] strings = value.get(k).split("\\:");
                                if(key.get(j).equals(strings[0].substring(1,strings[0].length() - 1))){

                                    val.add(strings[1].substring(1,strings[1].length() - 1));
                                }
                            }
                            map.put(key.get(j),val);
                        }
                        product.setMap(map);
                    }
                }

                maps.put("product",product);
                maps.put("HotProduct",productDao.getHotProduct(productId));
                maps.put("StyleProduct",productDao.getStyleProduct(productId,product.getCategoryId()));
            }

            return ResultUtil.success(maps);
        }catch (Exception e){
            e.printStackTrace();
            log.error("商品模块：'查询商品详情'接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getCateGoryList(Integer level, Integer type) {
        try {
            //非空校验
            if(level == null || type == null){
                return ResultUtil.paramError();
            }

            if(level > 3 || level < 1 || type > 3 || type < 1){
                return ResultUtil.paramError();
            }

            String key = "CateGoryList"+level+type;

            if(!jedisKeys.exists(key)){
                List<CateGory> cateGories = productDao.getCateGoryList(level);

                if(cateGories != null && cateGories.size() != 0){
                    if(type != 1){
                        if(type == 2){
                            if(level != 3){
                                for (int i = 0; i < cateGories.size(); i++){
                                    List<CateGory> cateGoryList = productDao.getCateGoryListByPId(cateGories.get(i).getCategoryId());
                                    if(cateGoryList != null){
                                        cateGories.get(i).setCateGoryList(cateGoryList);
                                    }
                                }
                            }
                        }else{
                            if(level != 3){
                                if(level == 2){

                                    for (int i = 0; i < cateGories.size(); i++){

                                        List<CateGory> cateGoryList = productDao.getCateGoryListByPId(cateGories.get(i).getCategoryId());

                                        if(cateGoryList != null){
                                            cateGories.get(i).setCateGoryList(cateGoryList);
                                        }
                                    }

                                }else{
                                    for (int i = 0; i < cateGories.size(); i++){

                                        List<CateGory> cateGoryList = productDao.getCateGoryListByPId(cateGories.get(i).getCategoryId());

                                        if(cateGoryList != null){

                                            for (int j = 0;j < cateGoryList.size();j++){

                                                List<CateGory> cateGoryList1 = productDao.getCateGoryListByPId(cateGoryList.get(i).getCategoryId());

                                                cateGoryList.get(i).setCateGoryList(cateGoryList1);
                                            }

                                            cateGories.get(i).setCateGoryList(cateGoryList);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                jedisStrings.set(key.getBytes(), SerializeUtil.serialize(cateGories));
                //设置过期时间两个小时
                jedisStrings.expire(key.getBytes(),7200);
                return ResultUtil.success(cateGories);
            }else{
                List<CateGory> cateGories = (List<CateGory>)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));
                return ResultUtil.success(cateGories);
            }

        }catch (Exception e){
            log.error("商品模块：'获取商品类别接口'接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getNewGoods(Integer pageNo, Integer pageSize) {
        try {

            if(pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }

            String rediskey = "NewGoods";
            List<Product> products = null;

            if(!jedisKeys.exists(rediskey)){
                products = productDao.getNewGoods();
            }else{
                products = (List<Product>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));
            }

            PageHelper.startPage(pageNo,pageSize);

            PageInfo<Product> page = new PageInfo<>(products);

            List<Product> productList = page.getList();

            if(productList != null && productList.size() != 0){

                for (int i = 0; i< productList.size(); i++){

                    List<ProductSpec> specs = productDao.getProductSpecListByPId(productList.get(i).getProductId());

                    List<String> key = new ArrayList<>();

                    List<String> value = new ArrayList<>();

                    if(specs != null){
                        for (int j = 0; j<specs.size(); j++){

                            String str = specs.get(j).getProductSpecs();

                            str = str.substring(1,str.length()-1);

                            String[] strings = str.split(",");

                            for (int k = 0;k < strings.length; k++){
                                String[] key_value = strings[k].split("\\:");
                                key.add(key_value[0].substring(1,key_value[0].length()-1));
                                value.add(strings[k]);
                            }

                            List<ProductImage> productImages = productDao.getProductImageListBySId(specs.get(j).getSpecId());

                            if(productImages != null){
                                specs.get(j).setProductImages(productImages);
                            }
                        }
                        productList.get(i).setSpecList(specs);
                    }

                    if(key.size() != 0){
                        key = MailUtil.removeDuplicate(key);
                    }

                    if(value.size() != 0){
                        value = MailUtil.removeDuplicate(value);
                        Map<String,List<String>> map = new HashMap<>();
                        if(key.size() != 0){
                            for (int j = 0; j < key.size(); j++){

                                List<String> val = new ArrayList<>();

                                for (int k = 0;k < value.size();k++){

                                    String[] strings = value.get(k).split("\\:");
                                    if(key.get(j).equals(strings[0].substring(1,strings[0].length() - 1))){

                                        val.add(strings[1].substring(1,strings[1].length() - 1));
                                    }
                                }
                                map.put(key.get(j),val);
                            }
                            productList.get(i).setMap(map);
                        }
                    }

                }
                page.setList(productList);
            }

            return ResultUtil.success(page);

        }catch (Exception e){
            log.error("商品模块：'查询首页新品商品'接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getCommodityPriceRange() {
        try{
            String rediskey = "CommodityPriceRange";

            if(!jedisKeys.exists(rediskey)){
                Map<String,Object> map = new HashMap<>();
                map.put("small",productDao.getCommodityPriceRange(0));
                map.put("big",productDao.getCommodityPriceRange(1));

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(map));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);

                return ResultUtil.success(map);
            }else{
                Map<String,Object> map = (Map<String,Object>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));
                return ResultUtil.success(map);
            }
        }catch (Exception e){
            log.error("商品模块：'查询商品价格区间'接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getStoreDistribution(Integer shopId) {
        try{

            String rediskey = "StoreDistribution";

            if(!jedisKeys.exists(rediskey)){

                List<ShippingInfo> shippingInfos = productDao.getStoreDistribution(shopId);

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(shippingInfos));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);

                return ResultUtil.success(shippingInfos);
            }else{
                List<ShippingInfo> shippingInfos = (List<ShippingInfo>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));
                return ResultUtil.success(shippingInfos);
            }
        }catch (Exception e){
            log.error("商品模块：'获取店铺配送方式'接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getSortingWay() {
        try{

            String rediskey = "SortingWay0";

            if(!jedisKeys.exists(rediskey)){

                List<SortingWay> sortingWays = productDao.getSortingWay(0);

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(sortingWays));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);

                return ResultUtil.success(sortingWays);
            }else{
                List<SortingWay> sortingWays = (List<SortingWay>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));
                return ResultUtil.success(sortingWays);
            }
        }catch (Exception e){
            log.error("商品模块：'获取商品排序方式接口'接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
