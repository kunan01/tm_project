package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysInformationImages;
@Mapper
public interface SysInformationImagesMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByInfomationId(Integer id);

    int deleteByInfomation(Integer id);

    int deleteBTwoClassifyId(Integer twoClassifyId);

    int insert(@Param("appInfoId")Integer id,@Param("urls")String[] urls);

    int insertSelective(SysInformationImages record);

    List<SysInformationImages> selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysInformationImages record);

    int updateByPrimaryKey(SysInformationImages record);
}