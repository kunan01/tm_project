package com.tangmo.zhjy.app.modules.dao;

import java.util.List;

import com.tangmo.zhjy.app.modules.bean.AppClassType;
import org.apache.ibatis.annotations.Mapper;

import com.tangmo.zhjy.app.modules.bean.AppClassify;
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