package com.tangmo.zhjy.app.modules.dao;

import com.tangmo.zhjy.app.modules.bean.SysUserBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserBeanMapper {

    SysUserBean selectByPrimaryKey(Integer id);

}