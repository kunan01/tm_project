package com.tangmo.zhjy.system.modules.dao;

import com.tangmo.zhjy.system.modules.bean.Commodity;
import com.tangmo.zhjy.system.modules.vo.CommodityVO;
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
     * 通过主键审核商品状态
     * @param state 审核状态 0：未审核 1：已审核 2：审核未通过
     * @param cdId 商品id
     * @return
     */
    int updateById(@Param("state") Integer state,@Param("cdId") Integer cdId);

    /**
     * 推送精品商品
     * @param isQuality
     * @param cdId 商品id
     * @return
     */
    int isQuality(@Param("isQuality") Integer isQuality,@Param("cdId") Integer cdId);

    /**
     * 通过主键删除商品信息
     * @param cdId 主键id

     * @return
     */
    int deleteById(Integer cdId);

    /**
     * 查询商品信息列表
     *
     * @param cdType  商品类型
     * @param cdType  商品状态
     * @return
     */
    List<CommodityVO> selectList(@Param("cdType") Integer cdType, @Param("state") Integer state, @Param("name") String name);

    /**
     * 查询商品详细信息
     *
     * @param cdId
     * @return
     */
    Commodity selectCommodityDetail(Integer cdId);


}
