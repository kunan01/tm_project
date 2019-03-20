package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.TbArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 省市区DAO
 */
public interface TbAreaMapper {


    /**
     * 根据主键查询省市区
     *
     * @param areaId 主键
     * @return
     */
    List<TbArea> jtquery(@Param("areaId") String areaId);

    /**
     * 添加省市区
     */
    int jtAdd(TbArea tbArea);

    /**
     * 查询省市区
     *
     * @param name 省 市 区
     * @return
     */
    List<TbArea> jtQueryList(@Param("name") String name);

}
