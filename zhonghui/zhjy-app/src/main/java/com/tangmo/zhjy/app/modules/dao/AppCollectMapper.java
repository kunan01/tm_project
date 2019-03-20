package com.tangmo.zhjy.app.modules.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppCollect;
@Mapper
public interface AppCollectMapper {
    int deleteByPrimaryKey(@Param("informationId")Integer informationid,@Param("userId")Integer userId);

    int insert(AppCollect record);

    int insertSelective(AppCollect record);

    List<AppCollect> selectByPrimaryKey(AppCollect record);

    int updateByPrimaryKeySelective(AppCollect record);

    int updateByPrimaryKey(AppCollect record);
}