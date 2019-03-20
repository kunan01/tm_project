package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.TbShuttleBus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班车DAO
 */
public interface TbShuttleBusMapper {

    /**
     * 查询所有班车
     *
     * @param name   途径站名
     * @param areaId 区域id
     * @return
     */
    List<TbShuttleBus> jtQueryShuttleBus(@Param("name") String name, @Param("areaId") String areaId);

    /**
     * 添加班车
     *
     * @param tbShuttleBus
     * @return
     */
    int jtAdd(TbShuttleBus tbShuttleBus);

    /**
     * 删除指定班车车次
     *
     * @param shuttleBusCode 班车表唯一标识
     * @return
     */
    int jtDelete(String shuttleBusCode);

    /**
     * 修改指定班车车次
     *
     * @param tbShuttleBus 班车表唯一标识
     * @return
     */
    int jtUpdate(TbShuttleBus tbShuttleBus);


}
