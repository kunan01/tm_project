package com.tangmo.zhjy.app.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppTwoClassify;
@Mapper
public interface AppTwoClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppTwoClassify record);

    int insertSelective(AppTwoClassify record);

    AppTwoClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppTwoClassify record);

    int updateByPrimaryKey(AppTwoClassify record);
    
    List<AppTwoClassify> findByPage(@Param("name")String name);
    
    List<AppTwoClassify> findByAppClassifyId(@Param("classifyId")Integer classifyId);

    List<AppTwoClassify> findByAppClassById();
}