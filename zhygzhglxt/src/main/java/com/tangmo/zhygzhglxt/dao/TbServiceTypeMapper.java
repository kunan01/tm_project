package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.TbServiceType;
import org.apache.ibatis.annotations.Param;

/*
 * 服务类型DAO
 */
public interface TbServiceTypeMapper {

    /**
     * 根据主键查询服务类型
     *
     * @param serviceTypeId 主键
     * @return
     */
    TbServiceType jtQueryById(@Param("serviceTypeId") String serviceTypeId);

}
