package com.tangmo.zhjy.system.modules.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppCollectMapper {

    int deleteByInfomationId(Integer id);

    int deleteByInfomation(Integer id);

    int deleteByTwoClassifyId(Integer twoClassifyId);
}