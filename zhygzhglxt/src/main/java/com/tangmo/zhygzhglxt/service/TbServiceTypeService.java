package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.utility.Result;

/**
 * 服务类型Service
 */
public interface TbServiceTypeService {


    /**
     * 根据主键查询服务类型
     *
     * @param serviceTypeId 主键
     * @return
     */
    Result jtQueryById(String serviceTypeId);

}
