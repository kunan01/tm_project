package com.tangmo.zhjy.app.modules.dao;

import java.util.List;

import com.tangmo.zhjy.app.modules.bean.AppInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppInformationImages;
@Mapper
public interface AppInformationImagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("appInfoId")Integer id,@Param("urls")String[] urls);

    int insertSelective(AppInformationImages record);

    List<AppInformationImages> selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppInformationImages record);

    int updateByPrimaryKey(AppInformationImages record);

}