package com.tangmo.zhjy.app.modules.service;


import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.ShopGoods;
import com.tangmo.zhjy.app.modules.dto.ShopServiceDto;

/**
 * @author boge
 * @date 18/1/2
 * @description 店铺商品相关操作服务
 */

public interface ShopGoodsService {

    /**
     * 增加店铺服务信息
     *
     * @param shopGoods
     * @return
     */
    Result addShopService(ShopGoods shopGoods);

    /**
     * 修改店铺服务信息
     *
     * @param shopGoods
     * @return
     */
    Result changeShopService(ShopGoods shopGoods);

    /**
     * 获取商品列表(通过类型)
     *
     * @param type
     * @param start
     * @param end
     * @return
     */
    Result searchServiceByType(Integer type, String district, Integer start, Integer end,Integer isTime);

    Result getServiceClassType();

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
     * 筛选服务列表
     *
     * @param shopServiceDto
     * @return
     */
    Result searchByCondition(ShopServiceDto shopServiceDto);

    /**
     * 删除店铺服务信息
     *
     * @param sgId
     * @return
     */
    Result deleteById(Integer sgId);


    Result getRECService(Integer sgId);

    Result getDisByCity(String city);
}
