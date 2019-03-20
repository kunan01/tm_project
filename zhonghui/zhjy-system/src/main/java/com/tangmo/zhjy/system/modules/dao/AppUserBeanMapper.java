package com.tangmo.zhjy.system.modules.dao;


import com.tangmo.zhjy.system.modules.bean.AppUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppUserBeanMapper {

    AppUserBean selectByPrimaryKey(Integer id);

    List<AppUserBean> selectByPage(String name);

    AppUserBean selectByAppUserId(Integer userId);



}