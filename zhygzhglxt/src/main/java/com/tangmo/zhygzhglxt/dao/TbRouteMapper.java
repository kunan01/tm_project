package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.TbRoute;

import java.util.List;

public interface TbRouteMapper {
    /**
     * This method was generated by MyBatis Generator.
     */
    int deleteByPrimaryKey(String routeId);

    /**
     * This method was generated by MyBatis Generator.
     */
    int insert(TbRoute record);

    /**
     * This method was generated by MyBatis Generator.
     */
    int insertSelective(TbRoute record);

    /**
     * This method was generated by MyBatis Generator.
     */
    TbRoute selectByPrimaryKey(String routeId);

    /**
     * 根据多个路线的唯一标识批量查询
     */
    TbRoute selectByRouteCodes(String routeCodes);

    /**
     * 批量查询
     */
    List<TbRoute> selectByRouteCodeses(String[] routeCodes);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKeySelective(TbRoute record);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKey(TbRoute record);
}