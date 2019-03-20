package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysInformation;
@Mapper
public interface SysInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByTwoClassifyId(Integer twoClassifyId);

    int insert(SysInformation record);

    int insertSelective(SysInformation record);

    SysInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysInformation record);

    int updateByPrimaryKeyWithBLOBs(SysInformation record);

    int updateByPrimaryKey(SysInformation record);
    
    List<SysInformation> sysFindPage(@Param("title")String title,@Param("classifyId")Integer classifyId,@Param("sysUserId")Integer sysUserId);

    List<SysInformation> sysFindPageNameType(@Param("title")String title,@Param("twoClassifyId")Integer twoClassifyId,@Param("sysUserId")Integer sysUserId);
    
    List<SysInformation> appFindPage(@Param("twoClassifyId")Integer twoClassifyId,@Param("title")String title);
    
    List<SysInformation> findClassifyId(@Param("classifId")Integer classifId,@Param("title")String title);
    
    List<SysInformation> queryBrowsingHistory(@Param("userId")Integer userId);
    
    List<SysInformation> findByUserId(Integer userId);

    List<SysInformation> getInfoCheckedList(@Param("title")String title);

    int updInfoCheckedById(@Param("id")Integer id,@Param("state")Integer state);

    Integer getInfoCount(@Param("isChecked")Integer isChecked);

    Integer getInfoByClassifyId(@Param("classifyId")Integer classifyId);
}