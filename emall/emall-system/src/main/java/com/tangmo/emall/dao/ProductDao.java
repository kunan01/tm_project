package com.tangmo.emall.dao;

import com.tangmo.emall.entity.Product;
import com.tangmo.emall.entity.ProductImage;
import com.tangmo.emall.entity.ProductParam;
import com.tangmo.emall.entity.ProductSpec;
import com.tangmo.emall.entity.dto.ProductDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    //添加商品
    int addProduct(Product product);

    //修改商品
    int updProduct(Product product);

    //通过商品id获取商品基本信息
    Product getProductById(Integer pId);

    //添加商品规格
    int addProductProp(ProductSpec productSpec);

    //添加商品规格图片
    int addProductPropImage(ProductImage productImage);

    //通过规格id获取规格基本信息
    ProductSpec getProductSpecById(Integer sId);

    //通过规格图片id获取图片基本信息
    ProductImage getProductImageById(Integer imgId);

    //修改商品规格
    int updProductProp(ProductSpec productSpec);

    //修改商品规格图片
    int updProductImage(ProductImage productImage);

    //通过规格id删除商品规格
    int delProductSpecById(Integer sId);

    //通过商品id删除商品规格
    int delProductSpecByPId(Integer pId);

    //通过规格id删除商品规格图片信息
    int delProductImageById(Integer sId);

    //通过商品id删除商品规格图片信息
    int delProductImageByPId(Integer pId);

    //删除商品
    int delProduct(Integer pId);

    //通过商品id获取规格集合
    List<ProductSpec> getProductSpecListByPId(Integer pId);

    //通过规格id获取图片集合
    List<ProductImage> getProductImageListBySId(Integer sId);

    //通过商品id更改购物车中的商品为失效状态
    int updShopCartByPId(Integer pId);

    //通过商品id更改收藏单中的商品为失效状态
    int updCollectByPId(Integer pId);

    //通过规格id更改购物车中的商品为失效状态
    int updShopCartBySId(Integer sId);

    //通过商品id更改订单未支付的为失效状态
    int updOrderByPId(Integer pId);

    //通过规格id更改订单未支付的为失效状态
    int updOrderBySId(Integer sId);

    //查询商品
    List<Product> getProductListByDto(ProductDto productDto);

    //筛选趋势商品
    List<Product> getTrendProductListByDto(ProductDto productDto);

    //筛选活动商品
    List<Product> getAdvertisingProductList(ProductDto productDto);

    //通过商品id和valueId查询标签信息
    ProductParam getProductParamByName(ProductParam productParam);

    //通过商品id标签信息
    List<ProductParam> getProductParamByProductId(Integer productId);

    //添加商品标签
    int addProductParam(ProductParam productParam);

    //通过标签id查询标签信息
    ProductParam getProductParamByById(Integer lId);

    //通过标签id删除标签信息
    int delProductParam(Integer lId);

    //通过商品id删除标签信息
    int delProductParamByPId(Integer pId);

    //通过热门活动id查询商品
    List<Product> getProductByRaId(Integer raId);

    //通过趋势id查询商品
    List<Product> getProductByTaId(Integer taId);

    //设置或取消新品商品
    int setOrCancelNewProduct(@Param("productId") Integer productId,@Param("isNew") Integer isNew);

    //获取商品规格库存的总量
    Integer getProductSpecTotal(@Param("productId") Integer productId);
}
