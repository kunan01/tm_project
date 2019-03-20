package com.tangmo.zhjy.system.modules.dao;


import com.tangmo.zhjy.system.modules.bean.ShopGoods;
import com.tangmo.zhjy.system.modules.vo.ShopServiceVO;
import com.tangmo.zhjy.system.modules.vo.SimpleShopGoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author boge
 * @date 17/12/29
 * @description 店铺商品Dao接口
 */
@Mapper
public interface ShopGoodsDao {

    /**
     * 修改店铺服务信息
     *
     * @param shopGoods
     * @return
     */
    int updateById(ShopGoods shopGoods);

    /**
     * 删除店铺服务信息
     *
     * @param sgId
     * @return
     */
    int deleteById(Integer sgId);

    /**
     * 审核店铺服务信息
     * @param state
     * @param sgId
     * @return
     */
    int auditById(@Param("state") Integer state,@Param("sgId") Integer sgId);

    /**
     * 查询同城信息
     * @param state
     * @return
     */
    List<SimpleShopGoodsVO> selectByState(@Param("state") Integer state,@Param("name") String name);

    /**
     * 查询用户服务信息列表
     * @param userId
     * @param start
     * @param end
     * @return
     */
    List<SimpleShopGoodsVO> selectUserShopGoods(@Param("userId") Integer userId, @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 查询商品服务详情
     *
     * @param sgId
     * @return
     */
    ShopServiceVO selectById(Integer sgId);


}
