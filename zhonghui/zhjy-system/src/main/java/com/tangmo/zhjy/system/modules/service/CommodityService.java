package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.Commodity;

/**
 * @author boge
 * @date 18/4/4
 * @description 商品服务类
 */

public interface CommodityService {

    /**
     * 增加商品信息
     *
     * @param commodity 商品实体
     * @return
     */
    Result addCommodity(Commodity commodity);

    /**
     * 审核商品
     *
     * @param cdId
     * @return
     */
    Result changeCommodity(Integer state,Integer cdId);

    /**
     * 推送精品商品
     * @param isQuality 1:推送2
     * @param cdId
     * @return
     */
    Result isQuality(Integer isQuality,Integer cdId);


    /**
     * 获取商品列表
     * @param cdType 商品类型 1：商品 2：二手商品
     * @param state 商品状态 0：未审核 1：审核通过 2：审核未通过
     * @param pageSize  当前页几条数据
     * @param pageNo    第几页
     * @return
     */
    Result searchCdList(Integer cdType,Integer state,Integer pageSize, Integer pageNo,String name);

    /**
     * 删除指定商品信息
     *
     * @param cdId
     * @return
     */
    Result delCommodity(Integer cdId);

    /**
     * 得到商品详细信息
     *
     * @param cdId
     * @return
     */
    Result getCommodityDetail(Integer cdId);


}
