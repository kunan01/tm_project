package com.tangmo.zhjy.app.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppInformation;
@Mapper
public interface AppInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppInformation record);

    int insertSelective(AppInformation record);

    AppInformation selectByPrimaryKey(Integer id);

    int addInfoVisitCount(Integer id);

    int updateByPrimaryKeySelective(AppInformation record);

    int updateByPrimaryKeyWithBLOBs(AppInformation record);

    int updateByPrimaryKey(AppInformation record);
    
    List<AppInformation> sysFindPage(@Param("title")String title,@Param("classifyId")Integer classifyId);
    
    List<AppInformation> appFindPage(@Param("twoClassifyId")Integer twoClassifyId,@Param("title")String title);
    
    List<AppInformation> findClassifyId(@Param("classifId")Integer classifId,@Param("title")String title);

    List<AppInformation> findTwoClassifyId(@Param("twoclassifId")Integer twoclassifId,@Param("title")String title);
    
    List<AppInformation> queryBrowsingHistory(@Param("userId")Integer userId);

    int ClearBrowsingHistory(@Param("userId")Integer userId);
    
    List<AppInformation> findByUserId(Integer userId);

    List<AppInformation> getInformationById(Integer userId);

    List<AppInformation> getHotInformation();
}