
package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbShuttleBus;
import com.tangmo.zhygzhglxt.utility.Result;

/**
 * 班车类目Service
 */
public interface TbShuttleBusService {


    /**
     * 查询所有班车
     *
     * @param name     途径站名
     * @param areaId   区域id
     * @param pageNo   当前页
     * @param pageSize 每页几条
     * @return
     */
    Result jtQueryShuttleBus(String name, String areaId, Integer pageNo, Integer pageSize);

    /**
     * 添加班车
     *
     * @param tbShuttleBus
     * @return
     */
    Result jtAdd(TbShuttleBus tbShuttleBus);

    /**
     * 删除指定班车车次
     *
     * @param shuttleBusCode 班车表唯一标识
     * @return
     */
    Result jtDelete(String shuttleBusCode);

    /**
     * 修改指定班车车次
     *
     * @param tbShuttleBus 班车表唯一标识
     * @return
     */
    Result jtUpdate(TbShuttleBus tbShuttleBus);
}
