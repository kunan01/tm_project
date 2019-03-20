package com.tangmo.emall.service;

import com.tangmo.emall.entity.Product;
import com.tangmo.emall.entity.ProductParam;
import com.tangmo.emall.entity.ProductSpec;
import com.tangmo.emall.entity.dto.ProductDto;
import com.tangmo.emall.utils.Result;

public interface ProductService {

    //添加商品
    Result addProduct(Product product);

    //修改商品
    Result updProduct(Product product);

    //添加商品规格
    Result addProductProp(ProductSpec productSpec);

    //修改商品规格
    Result updProductProp(ProductSpec productSpec);

    //删除商品规格
    Result delProductProp(ProductSpec productSpec);

    //删除商品
    Result delProduct(Product product);

    //上架商品
    Result shelvesProduct(Product product);

    //下架商品
    Result theShelvesProduct(Product product);

    //查询商品
    Result getProductList(ProductDto productDto);

    //通过商品id获取商品详细信息
    Result getProductListById(Integer productId);

    //设置或取消新品商品
    Result setOrCancelNewProduct(String productId,Integer isNew);

    //添加商品标签
    Result addProductParam(ProductParam productParam);

    //删除商品标签
    Result delProductParam(String lId);
}
