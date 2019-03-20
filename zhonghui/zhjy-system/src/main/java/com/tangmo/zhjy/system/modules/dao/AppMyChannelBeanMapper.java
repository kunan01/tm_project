package com.tangmo.zhjy.system.modules.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppMyChannelBeanMapper {

    int deleteByTwoClassifyId(Integer twoClassifyId);

}