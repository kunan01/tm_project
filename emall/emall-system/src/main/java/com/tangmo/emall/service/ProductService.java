package com.tangmo.emall.service;

import com.tangmo.emall.entity.Product;
import com.tangmo.emall.entity.ProductParam;
import com.tangmo.emall.entity.ProductSpec;
import com.tangmo.emall.entity.dto.ProductDto;
import com.tangmo.emall.entity.dto.ProductUpdDto;
import com.tangmo.emall.utils.Result;

public interface ProductService {

    //添加商品
    Result addProduct(ProductUpdDto productUpdDto);

    //修改商品
    Result updProduct(ProductUpdDto productUpdDto);

    //添加商品规格
    Result addProductProp(ProductSpec productSpec);

    //修改商品规格
    Result updProductProp(ProductSpec productSpec);

    //删除商品规格
    Result delProductProp(ProductSpec productSpec);

    //删除商品
    Result delProduct(ProductDto productDto);

    //上架商品
    Result shelvesProduct(Product product);

    //下架商品
    Result theShelvesProduct(Product product);

    //设置商品折扣
    Result setProductDiscounts(ProductDto productDto);

    //查询商品
    Result getProductList(ProductDto productDto);

    //筛选趋势商品
    Result getTrendProductList(ProductDto productDto);

    //筛选活动商品
    Result getAdvertisingProductList(ProductDto productDto);

    //通过商品id获取商品详细信息
    Result getProductListById(Integer productId);

    //设置或取消新品商品
    Result setOrCancelNewProduct(String productId,Integer isNew);

    //添加商品标签
    Result addProductParam(ProductParam productParam);

    //删除商品标签
    Result delProductParam(String lId);
}
