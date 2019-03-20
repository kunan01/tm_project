package com.tangmo.zhjy.system.modules.dao;

import com.tangmo.zhjy.system.modules.bean.ShopVerify;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author boge
 * @date 18/3/9
 * @description
 */
@Mapper
public interface SysShopMapper {
    /**
     * 根据审核状态查询
     *
     * @param state
     * @return
     */
    List<ShopVerify> selectPageByState(@Param("state") Byte state);

    /**
     * 根据用户id查询
     *
     * @param userId
     * @return
     */
    ShopVerify verifyByUserId(@Param("userId") Integer userId);

    /**
     * 修改商家审核状态
     *
     * @param svId
     * @param state
     * @return
     */
    int updateSvState(@Param("svId") Integer svId, @Param("state") Byte state);

    /**
     * 获取商家用户Id
     * @param svId
     * @return
     */
    Integer selectUserId(Integer svId);

    /**
     * 根据商家Id获得店铺信息
     * @param userId
     * @return
     */
    ShopVerify selectByUserId(Integer userId);
}
