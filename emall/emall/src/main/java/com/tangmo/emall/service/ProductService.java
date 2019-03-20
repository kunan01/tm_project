package com.tangmo.emall.service;

import com.tangmo.emall.entity.dto.ProductDto;
import com.tangmo.emall.utils.Result;

public interface ProductService {

    //查询商品
    Result getProductList(ProductDto dto);

    //查询店铺标签
    Result getProductParamList(Integer shopId);

    //商品详情
    Result getProductById(Integer productId,Integer userId);

    //获取商品类别
    Result getCateGoryList(Integer level,Integer type);

    //查询首页新品商品
    Result getNewGoods(Integer pageNo,Integer pageSize);

    //查询商品价格区间
    Result getCommodityPriceRange();

    //获取店铺配送方式
    Result getStoreDistribution(Integer shopId);

    //获取商品排序方式
    Result getSortingWay();
}
