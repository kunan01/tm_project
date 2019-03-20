package com.tangmo.zhjy.system.modules.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysNavigationBean;
@Mapper
public interface SysNavigationBeanMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysNavigationBean record);

    int insertSelective(SysNavigationBean record);

    SysNavigationBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysNavigationBean record);

    int updateByPrimaryKey(SysNavigationBean record);
    
    List<SysNavigationBean>  findByPage(@Param("name")String name);
    
    List<SysNavigationBean> appShow();
}