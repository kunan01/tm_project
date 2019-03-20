package com.tangmo.zhjy.app.modules.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tangmo.zhjy.app.modules.bean.AppBrowsingHistoryBean;

@Mapper
public interface AppBrowsingHistoryBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByInfoId(Integer id);

    int insert(AppBrowsingHistoryBean record);

    int insertSelective(AppBrowsingHistoryBean record);

    AppBrowsingHistoryBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppBrowsingHistoryBean record);

    int updateByPrimaryKey(AppBrowsingHistoryBean record);
}