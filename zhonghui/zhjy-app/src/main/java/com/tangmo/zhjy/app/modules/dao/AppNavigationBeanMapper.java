package com.tangmo.zhjy.app.modules.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppNavigationBean;
@Mapper
public interface AppNavigationBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppNavigationBean record);

    int insertSelective(AppNavigationBean record);

    AppNavigationBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppNavigationBean record);

    int updateByPrimaryKey(AppNavigationBean record);
    
    List<AppNavigationBean>  findByPage(@Param("name")String name);
    
    List<AppNavigationBean> appShow();
}