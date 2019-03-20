package com.tangmo.zhjy.app.modules.dao;


import com.tangmo.zhjy.app.modules.bean.GoodsOrder;
import com.tangmo.zhjy.app.modules.bean.GoodsOrderSimple;
import com.tangmo.zhjy.app.modules.bean.ShopCart;
import com.tangmo.zhjy.app.modules.bean.ShopGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author boge
 * @date 18/3/8
 * @description 商品订单Dao层
 */
@Mapper
public interface GoodsOrderDao {

    /**
     * 增加订单
     *
     * @param goodsOrderSimple
     * @return
     */
    int insertGo(GoodsOrderSimple goodsOrderSimple);

    /**
     * 根据订单状态查询订单
     *
     * @param state
     * @param userId 用户id
     * @return
     */
    List<GoodsOrderSimple> selectOrderByState(@Param("state") Byte state, @Param("userId") Integer userId);

    /**
     * 修改订单
     *
     * @param goId
     * @return
     */
    int updateSelective(Integer goId);

    /**
     * 所有用户订单
     *
     * @param userId
     * @return
     */
    List<GoodsOrderSimple> selectAllOrder(@Param("userId") Integer userId);

    /**
     * 订单详情
     *
     * @param goId
     * @return
     */
    GoodsOrder selectById(Integer goId);

    /**
     * 修改订单状态
     *
     * @param goId
     * @param state
     * @return
     */
    int updateOrderState(@Param("goId") Integer goId, @Param("state") Byte state);

    /**
     * 修改订单为交易完成
     *
     * @param orderNum
     * @return
     */
    int updateOrderDone(String orderNum);

    /**
     * 删除订单
     *
     * @param goId
     * @return
     */
    int deleteById(Integer goId);

    /**
     * 查询订单编号
     *
     * @param goId
     * @return
     */
    String selectExpress(Integer goId);

    /**
     * 根据订单编号查询订单信息
     *
     * @param orderNum
     * @return
     */
    List<GoodsOrder> selectByOrderNo(String orderNum);

    //增加购物车信息
    int addCart(ShopCart shopCart);

    //删除指定购物车商品
    int delCartById(@Param("scId") Integer scId);

    //通过购物车主键获取商品库存数量
    Integer getCommCountByCartId(@Param("scId") Integer scId);

    //修改指定购物车商品数量
    int updCartCountById(@Param("scId") Integer scId,@Param("count") Integer count);

    //查询购物车是否有相同物品
    ShopCart getShopCarByCdIdAndUserId(@Param("cdId") Integer cdId,@Param("userId") Integer userId);

    List<ShopCart> getCartList(@Param("userId") Integer userId);

    //修改支付单号
    int updPayNo(@Param("goId") Integer goId,@Param("payNo") String payNo);

}
