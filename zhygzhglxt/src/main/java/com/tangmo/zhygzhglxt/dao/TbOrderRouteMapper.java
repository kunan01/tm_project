package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.TbOrderRoute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbOrderRouteMapper {

    /**
     * This method was generated by MyBatis Generator.
     */
    int deleteByPrimaryKey(String orderRouteId);

    /**
     * This method was generated by MyBatis Generator.
     */
    int insert(TbOrderRoute record);

    /**
     * 动态添加
     */
    int insertSelective(TbOrderRoute record);

    /**
     * This method was generated by MyBatis Generator.
     */
    TbOrderRoute selectByPrimaryKey(String orderRouteId);

    /**
     * 根据司机订单的唯一标识查询订单路线
     */
    List<TbOrderRoute> selectByDriverOrderCode(@Param("driverOrderCode") String driverOrderCode);

    /**
     * 根据司机订单的唯一标识查询订单路线第一个
     */
    TbOrderRoute selectByDriverOrderCodeOne(@Param("driverOrderCode") String driverOrderCode);

    /**
     * 根据司机订单的唯一标识查询订单路线最后一个
     */
    TbOrderRoute selectByDriverOrderCodeLast(@Param("driverOrderCode") String driverOrderCode);

    /**
     *
     */
    int updateByPrimaryKeySelective(TbOrderRoute record);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKey(TbOrderRoute record);

}