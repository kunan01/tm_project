package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.TbComplain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbComplainMapper {

    /**
     * This method was generated by MyBatis Generator.
     */
    int deleteByPrimaryKey(String complainId);

    /**
     * 根据唯一标识概念性删除
     */
    int deleteByCode(@Param("code") String code);

    /**
     * This method was generated by MyBatis Generator.
     */
    int insert(TbComplain record);

    /**
     * This method was generated by MyBatis Generator.
     */
    int insertSelective(TbComplain record);

    /**
     * This method was generated by MyBatis Generator.
     */
    TbComplain selectByPrimaryKey(String complainId);

    /**
     * 根据用户逇唯一标识查询
     */
    List<TbComplain> selComplainByUserCode(@Param("name") String name, @Param("state") String state, @Param("userCode") String userCode);

    /**
     * 根据唯一标识查询
     */
    TbComplain selComplainByCode(@Param("code") String code);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKeySelective(TbComplain record);

    /**
     * 解决投诉并修改回馈信息
     */
    int updateByCode(TbComplain record);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKey(TbComplain record);
}