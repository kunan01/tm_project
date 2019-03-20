package com.tangmo.zhjy.app.modules.dao;

import com.tangmo.zhjy.app.modules.bean.Version;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VersionMapper {

    /**
     * 根据主键删除
     */
    int deleteByPrimaryKey(Integer versionId);

    /**
     * 动态添加版本
     */
    int insertSelective(Version record);

    /**
     * 根据主键查新
     */
    Version selectByPrimaryKey(Integer versionId);

    /**
     * 获取最新版本号
     */
    Version getVersion();
}