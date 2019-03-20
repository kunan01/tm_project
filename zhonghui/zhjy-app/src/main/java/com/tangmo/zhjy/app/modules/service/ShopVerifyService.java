package com.tangmo.zhjy.app.modules.service;


import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.ShopVerify;

/**
 * @author boge
 * @date 18/3/9
 * @description
 */

public interface ShopVerifyService {

    /**
     * 增加商家审核信息
     *
     * @param shopVerify
     * @return
     */
    Result addShopVerify(ShopVerify shopVerify);

    /**
     * 用户查找商家审核信息
     *
     * @param userId
     * @return
     */
    Result searchSvInfo(Integer userId);

    /**
     * 查找所有商家审核信息
     *
     * @param start
     * @param end
     * @return
     */
    Result searchAllSvInfo(Integer start, Integer end);

    /**
     * 修改商家信息
     *
     * @param shopVerify
     * @return
     */
    Result changeSvInfo(ShopVerify shopVerify);

    /**
     * 修改商家审核信息
     *
     * @param svId
     * @param state
     * @return
     */
    Result updateSvState(Integer svId, Byte state);
}
