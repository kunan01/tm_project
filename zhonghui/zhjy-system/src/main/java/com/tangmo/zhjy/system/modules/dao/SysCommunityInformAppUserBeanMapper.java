package com.tangmo.zhjy.system.modules.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysCommunityInformAppUserBean;
@Mapper
public interface SysCommunityInformAppUserBeanMapper {
	
    int deleteByPrimaryKey(Integer id);

    int deleteByCommunityInformId(Integer communityId);

    int deleteByCommunityInform(Integer communityInformId);

    int insert(@Param("list")List<SysCommunityInformAppUserBean> list);

    int insertSelective(SysCommunityInformAppUserBean record);

    SysCommunityInformAppUserBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCommunityInformAppUserBean record);

    int updateByPrimaryKey(SysCommunityInformAppUserBean record);
    
    SysCommunityInformAppUserBean queryTotalAndRedTotal(@Param("community_informId")Integer community_informId);
}