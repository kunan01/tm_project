package com.tangmo.zhjy.app.modules.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tangmo.zhjy.app.modules.bean.AppInformationPageView;
@Mapper
public interface AppInformationPageViewMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByInfoId(Integer id);

    int insert(AppInformationPageView record);

    int insertSelective(AppInformationPageView record);

    AppInformationPageView selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppInformationPageView record);

    int updateByPrimaryKey(AppInformationPageView record);
}