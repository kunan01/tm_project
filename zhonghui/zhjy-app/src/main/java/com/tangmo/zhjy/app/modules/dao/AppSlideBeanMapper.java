package com.tangmo.zhjy.app.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tangmo.zhjy.app.modules.bean.AppSlideBean;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppSlideBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppSlideBean record);

    int insertSelective(AppSlideBean record);

    AppSlideBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppSlideBean record);

    int updateByPrimaryKey(AppSlideBean record);
    
    List<AppSlideBean> findByPage(@Param("title") String title);
    
}