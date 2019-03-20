package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.GoodsOrderSimple;
import com.tangmo.zhjy.app.modules.bean.ShopCart;

/**
 * @author boge
 * @date 18/4/4
 * @description 订单服务层
 */

public interface GoodsOrderService {

    /**
     * 增加订单
     *
     * @param goodsOrderSimple
     * @return
     */
    Result addOrder(GoodsOrderSimple goodsOrderSimple);

    /**
     * 根据条件查询订单
     *
     * @param userId 用户id
     * @param state  订单状态
     * @param start
     * @param end
     * @return
     */
    Result searchOrderByState(Byte state, Integer userId, Integer start, Integer end);

    /**
     * 查询所有用户订单
     *
     * @param userId
     * @param start
     * @param end
     * @return
     */
    Result searchAllOrder(Integer userId, Integer start, Integer end);

    /**
     * 查询订单详情
     *
     * @param goId
     * @return
     */
    Result searchOrderDetail(Integer goId);

    /**
     * 支付订单
     *
     * @param goId 订单Id
     * @return
     */
    Result payOrder(Integer goId);

    /**
     * 删除订单
     *
     * @param goId
     * @return
     */
    Result delOrder(Integer goId);

    /**
     * 修改订单状态
     *
     * @param goId
     * @return
     */
    Result changeOrderState(Integer goId, Byte state);

    /**
     * 查询物流状态
     *
     * @param goId
     * @return
     */
    Result searchExpress(Integer goId);

    //加入购物车
    Result addCart(ShopCart shopCart);

    //删除指定购物车商品
    Result delCartById(Integer scId);

    //修改指定购物车商品数量
    Result updCartCountById(Integer scId,Integer count);

    //获取购物车商品列表
    Result getCartList(Integer userId);

}
