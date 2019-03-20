package com.tangmo.zhjy.system.modules.dao;

import com.tangmo.zhjy.system.modules.bean.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {

    /**
     * 根据主键删除日志
     */
    int deleteByPrimaryKey(Integer logId);

    /**
     * 增加日志
     */
    int insert(Log record);

    /**
     * 动态增加日志
     */
    int insertSelective(Log record);

    /**
     * 根据主键id查询日志
     */
    Log selectByPrimaryKey(Integer logId);

    /**
     * 分页查询日志信息
     */
    List<Log> findPageLog();

    /**
     * 根据主键动态修改日志
     */
    int updateByPrimaryKeySelective(Log record);

    /**
     * 根据主键修改
     */
    int updateByPrimaryKey(Log record);
}