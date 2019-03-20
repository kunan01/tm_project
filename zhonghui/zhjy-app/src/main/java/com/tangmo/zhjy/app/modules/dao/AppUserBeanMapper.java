package com.tangmo.zhjy.app.modules.dao;

import java.util.List;

import com.tangmo.zhjy.app.modules.dto.AppUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.app.modules.bean.AppUserBean;
@Mapper
public interface AppUserBeanMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(AppUserBean record);

    int insertSelective(AppUserBean record);

    AppUserBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppUserBean record);

    int updateByPrimaryKey(AppUserBean record);
    
    AppUserBean findByPhone(@Param("phone")String phone);
    
    List<AppUserBean> findByCommunityInformId(@Param("communityInformId")Integer communityInformId);
    
    AppUserBean findByUserAndPassword(@Param("phone")String phone,@Param("password")String password);
}