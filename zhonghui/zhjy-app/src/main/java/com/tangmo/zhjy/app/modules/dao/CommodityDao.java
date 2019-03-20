package com.tangmo.zhjy.app.modules.dao;

import com.tangmo.zhjy.app.modules.bean.AppClassType;
import com.tangmo.zhjy.app.modules.bean.Commodity;
import com.tangmo.zhjy.app.modules.bean.GoodsComment;
import com.tangmo.zhjy.app.modules.dto.CommodityDto;
import com.tangmo.zhjy.app.modules.vo.CdManageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author boge
 * @date 17/12/27
 * @description 商品Dao接口
 */
@Mapper
public interface CommodityDao {

    /**
     * 增加商品信息
     *
     * @param commodity 商品实体类
     * @return
     */
    int insertSelective(Commodity commodity);

    /**
     * 通过主键更新商品信息
     *
     * @param commodity 商品实体类
     * @return
     */
    int updateById(Commodity commodity);

    /**
     * 商品的总数量
     */
    int cdCount();

    /**
     * 二手商品的总数量
     */
    int cdTwoCount();

    /**
     * 通过主键删除商品信息
     *
     * @param cdId 主键id
     * @return
     */
    int deleteById(Integer cdId);

    /**
     * 查询商品信息列表
     *
     * @return
     */
    List<Commodity> selectList();


    /**
     * 查询所有2手商品信息列表
     *
     * @return
     */
    List<Commodity> selectListTwo();


    /**
     * 根据用户id查询商品信息
     *
     * @param userId
     * @return
     */
    List<Commodity> selectListByUserId(@Param("userId") Integer userId);

    /**
     * 查询商品详细信息
     *
     * @param cdId
     * @return
     */
    Commodity selectCommodityDetail(Integer cdId);

    /**
     * 查询精品商品
     *
     * @param start
     * @param end
     * @return
     */
    List<Commodity> selectQualityList(@Param("start") Integer start, @Param("end") Integer end);


    /**
     * 通过条件筛选
     *
     * @param commodityDto 商品查询条件对象
     * @return
     */
    List<Commodity> selectCdByCondition(CommodityDto commodityDto);

    /**
     * 商品好评+1
     *
     * @param cdId 商品主键
     * @return
     */
    int updateCdStar(Integer cdId);

    /**
     * 获取指定类型的精品列表
     *
     * @param cdType
     * @param start
     * @param end
     * @return
     */
    List<Commodity> selectQualityListByType(@Param("cdType") Byte cdType, @Param("start") Integer start, @Param("end") Integer end);


    /**
     * 修改商品上下架状态
     *
     * @param cdId
     * @param state
     * @return
     */
    int updateCdState(@Param("cdId") Integer cdId, @Param("state") Byte state);

    /**
     * 上架/下架商品销售记录和库存
     *
     * @param userId
     * @param state
     * @return
     */
    List<CdManageVO> selectCdManageRecord(@Param("userId") Integer userId, @Param("state") Byte state);

    /**
     * 获取商品评论列表
     *
     * @param goodsId 商品id
     * @param start   开始索引
     * @param end     结束索引
     * @return
     */
    List<GoodsComment> selectCommentByGoodsId(@Param("goodsId") Integer goodsId, @Param("start") Integer start,
                                              @Param("end") Integer end);

    /**
     * 增加商品评论
     *
     * @param goodsComment
     * @return
     */
    int insertComment(GoodsComment goodsComment);

    /**
     * 商品数量减少
     *
     * @param cdId
     * @return
     */
    int updateCdCount(@Param("cdId") Integer cdId,@Param("count") Integer count);

    //获取商品分类
    List<AppClassType> getCommentClassType(@Param("id") Integer id);

    //通过商品分类id查询商品列表
    List<Commodity> getCommentListByCId(@Param("type") Integer type,@Param("cId") Integer cId,@Param("district") String district);

    AppClassType getCommentClassTypeById(@Param("cId") Integer cId);

    //
    List<Commodity> getCommentRECByShop(@Param("cId") Integer cId,@Param("type") Integer type);

    //获取商品售出数量
    Integer getCommentSellCount(@Param("cdId")Integer cdId);


    List<String> getDisByCity(@Param("city")String city,@Param("type")Integer type);
}
