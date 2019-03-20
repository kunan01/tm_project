package com.tangmo.zhjy.system.modules.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppBrowsingHistoryBeanMapper {

    int deleteByInfomationId(Integer id);


    int deleteByInfomation(Integer id);

    int deleteByTwoClassifyId(Integer twoClassifyId);
}