package com.tangmo.emall.dao;

import com.tangmo.emall.entity.ShopCart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopCartDao {

    //加入购物车
    int addShopCart(ShopCart shopCart);

    //通过商品id和规格id查询购物车信息
    ShopCart getCartByProduct(ShopCart shopCart);

    //通过购物车id查询购物车信息
    ShopCart getCartById(Integer cId);

    //删除购物车
    int delShopCart(Integer cId);

    //清空用户购物车
    int emptyShopCart(Integer userId);

    //通过用户id获取用户购物车集合
    List<ShopCart> getCartListByUserId(Integer userId);

    //修改购物车数量
    int updShopCart(ShopCart shopCart);
}
