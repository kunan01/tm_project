package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysRelatierolOperation;
@Mapper
public interface SysRelatierolOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRelatierolOperation record);

    int insertSelective(SysRelatierolOperation record);

    SysRelatierolOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRelatierolOperation record);

    int updateByPrimaryKey(SysRelatierolOperation record);
    
    int batchAdd(@Param("sysRelatierolOperations")SysRelatierolOperation sysRelatierolOperations);
    
    List<SysRelatierolOperation> findByRelatierolId(@Param("id")Integer id);
}