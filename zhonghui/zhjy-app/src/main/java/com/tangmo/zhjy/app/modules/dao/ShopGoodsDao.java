package com.tangmo.zhjy.app.modules.dao;


import com.tangmo.zhjy.app.modules.bean.GoodsComment;
import com.tangmo.zhjy.app.modules.bean.ShopGoods;
import com.tangmo.zhjy.app.modules.dto.ShopServiceDto;
import com.tangmo.zhjy.app.modules.vo.ShopServiceVO;
import com.tangmo.zhjy.app.modules.vo.SimpleShopGoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * @author boge
 * @date 17/12/29
 * @description 店铺商品Dao接口
 */
@Mapper
public interface ShopGoodsDao {

    /**
     * 增加店铺商服务信息
     *
     * @param shopGoods
     * @return
     */
    int insertSelective(ShopGoods shopGoods);

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
     * 查询精品信息列表
     *
     * @param type
     * @param
     * @param
     * @return
     */
    List<SimpleShopGoodsVO> selectByType(@Param("type") Integer type,@Param("district") String district,@Param("isTime") Integer isTime);


    /**
     * 查询用户服务信息列表
     * @param userId
     * @return
     */
    List<SimpleShopGoodsVO> selectUserShopGoods(@Param("userId") Integer userId);

    /**
     * 查询商品服务详情
     *
     * @param sgId
     * @return
     */
    ShopServiceVO selectById(Integer sgId);

    /**
     * 根据条件筛选商品服务
     *
     * @param shopServiceDto
     * @return
     */
    List<SimpleShopGoodsVO> selectByCondition(ShopServiceDto shopServiceDto);

    List<SimpleShopGoodsVO> getRECService(@Param("sgId") Integer sgId);

    List<String> getDisByCity(@Param("city") String city);


}
