package com.tangmo.zhjy.system.modules.service;


import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.ShopGoods;

/**
 * @author boge
 * @date 18/1/2
 * @description 店铺商品相关操作服务
 */

public interface ShopGoodsService {

    /**
     * 修改店铺服务信息
     *
     * @param shopGoods
     * @return
     */
    Result changeShopService(ShopGoods shopGoods);

    /**
     * 获取商品列表（未审核）
     * @param pageSize
     * @param pageNo
     * @return
     */
    Result selectByState(Integer state,Integer pageSize, Integer pageNo,String name);

    /**
     * 查询用户服务列表
     *
     * @param userId
     * @param start
     * @param end
     * @return
     */
    Result searchUserService(Integer userId, Integer start, Integer end);

    /**
     * 获取服务详情
     *
     * @param sgId
     * @return
     */
    Result searchServiceDetail(Integer sgId);


    /**
     * 删除店铺服务信息
     *
     * @param sgId
     * @return
     */
    Result deleteById(Integer sgId);

    /**
     * 审核店铺服务信息
     * @param state
     * @param sgId
     * @return
     */
    Result audit(Integer state,Integer sgId);



}
