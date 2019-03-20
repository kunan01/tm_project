package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbArea;
import com.tangmo.zhygzhglxt.utility.Result;

/**
 * 省市区Service
 */
public interface TbAreaService {

    /**
     * 根据主键查询省市区
     *
     * @param areaId 主键
     * @return
     */
    Result jtquery(String areaId);

    /**
     * 添加省市区
     */
    Result jtAdd(TbArea tbArea);


    /**
     * 查询省市区
     *
     * @param name 省 市 区
     * @return
     */
    Result jtQueryList(String name, Integer pageNo, Integer pageSize);
}
