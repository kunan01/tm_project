package com.tangmo.emall.dao;

import com.tangmo.emall.entity.*;
import com.tangmo.emall.entity.dto.ProductDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    //通过商品id获取商品基本信息
    Product getProductById(Integer pId);

    //通过规格id获取规格基本信息
    ProductSpec getProductSpecById(Integer sId);

    //通过规格id获取图片集合
    List<ProductImage> getProductImageListBySId(Integer sId);

    //通过商品id获取规格集合
    List<ProductSpec> getProductSpecListByPId(Integer pId);

    //查询商品
    List<Product> getProductListByDto(ProductDto productDto);

    //通过shopId查询参数类型
    List<ParamType> getParamTypeListByShopId(Integer shopId);

    //通过typeId查询参数值
    List<ParamValue> getParamValueListByTypeId(Integer typeId);

    //增加商品访问次数
    int updProductViewCount(Integer productId);

    //根据属性查询商品数量
    Integer getProductCount(Integer valueId);

    //减少商品库存
    int destocking(@Param("specId") Integer specId,@Param("count") Integer count);

    //增加商品库存
    int increaseInventory(@Param("specId") Integer specId,@Param("count") Integer count);

    //通过级别获取商品分类
    List<CateGory> getCateGoryList(Integer level);

    //通过父级id获取下级所有分类
    List<CateGory> getCateGoryListByPId(Integer cId);

    //通过id分类信息
    CateGory getCateGoryByPId(Integer cId);

    //查询新品商品
    List<Product> getNewGoods();

    //查询热门活动商品，不包含当前商品
    List<Product> getHotProduct(Integer pId);

    //查询当前分类活动商品，不包含当前商品
    List<Product> getStyleProduct(@Param("pId") Integer pId,@Param("typeId") Integer typeId);

    //通过分类id查询商品
    List<Product> getCategoryProduct(Integer typeId);

    //通过限时活动id查询限时商品
    List<Product> getProductByActivity(Integer disId);

    //通过热门活动id查询商品
    List<Product> getProductByRaId(Integer raId);

    //通过趋势id查询商品集合
    List<Product> getProductByTaId(Integer taId);

    //查询商品价格区间   0最小金额 1最大金额
    Double getCommodityPriceRange(@Param("typeId") Integer typeId);

    //获取店铺配送方式
    List<ShippingInfo> getStoreDistribution(Integer shopId);

    //通过配送方式id获取配送详情
    ShippingInfo getStoreDistributionById(Integer shipId);

    //获取排序方式
    List<SortingWay> getSortingWay(Integer sortingType);

    //通过排序方式id获取排序规则
    SortingWay getSortingWayById(Integer sortingId);
}
