package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tangmo.zhjy.system.modules.bean.SysSlideBean;

@Mapper
public interface SysSlideBeanMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByInfomationId(Integer id);

    int deleteByInfomation(Integer id);

    int deleteByTwoClassifyId(Integer twoClassifyId);

    int insert(SysSlideBean record);

    int insertSelective(SysSlideBean record);

    SysSlideBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysSlideBean record);

    int updateByPrimaryKey(SysSlideBean record);
    
    List<SysSlideBean> findByPage(String name);
    
}