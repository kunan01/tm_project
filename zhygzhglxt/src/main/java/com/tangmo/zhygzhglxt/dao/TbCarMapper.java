package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.TbCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbCarMapper {

    /**
     * This method was generated by MyBatis Generator.
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     */
    int insert(TbCar record);

    /**
     * This method was generated by MyBatis Generator.
     */
    int insertSelective(TbCar record);

    /**
     * This method was generated by MyBatis Generator.
     */
    TbCar selectByPrimaryKey(String id);

    /**
     * 查询所有车辆
     */
    List<TbCar> selAllTbCar(String name);

    /**
     * 根据车辆的设备号查询信息
     */
    TbCar selCarByDeviceNumber(@Param("deviceNumber") String deviceNumber);

    /**
     * 根据车辆的车牌号查询信息
     */
    TbCar selCarByCarNumber(@Param("carNumber") String carNumber);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKeySelective(TbCar record);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKey(TbCar record);
}