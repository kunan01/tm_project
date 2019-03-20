package com.tangmo.emall.service;

import com.tangmo.emall.entity.ShopCart;
import com.tangmo.emall.utils.Result;

public interface ShopCartService {

    //添加购物车
    Result addShopCart(ShopCart shopCart);

    //删除购物车
    Result delShopCart(Integer userId,String cartId);

    //清空购物车
    Result emptyShopCart(Integer userId);

    //更改购物车数量
    Result updateTheNumber(ShopCart shopCart);

    //查看用户购物车
    Result getShopCartList(Integer userId,Integer pageNo,Integer pageSize);
}
