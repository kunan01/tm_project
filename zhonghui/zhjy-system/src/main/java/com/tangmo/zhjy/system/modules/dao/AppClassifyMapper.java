package com.tangmo.zhjy.system.modules.dao;

import com.tangmo.zhjy.system.modules.bean.AppClassify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppClassify record);

    int insertSelective(AppClassify record);

    AppClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppClassify record);

    int updateByPrimaryKey(AppClassify record);
    
    List<AppClassify> queryAll();
}