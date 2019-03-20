package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysTwoClassify;
@Mapper
public interface SysTwoClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysTwoClassify record);

    int insertSelective(SysTwoClassify record);

    SysTwoClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysTwoClassify record);

    int updateByPrimaryKey(SysTwoClassify record);
    
    List<SysTwoClassify> findByPage(@Param("name")String name);
    
    List<SysTwoClassify> findByAppClassifyId(@Param("classifyId")Integer classifyId);
}