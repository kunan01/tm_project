package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;

/**
 * @author boge
 * @date 2018/4/29
 * @description 商家审核服务
 */

public interface SysShopService {

    /**
     * 后台商家审核信息分页
     *
     * @param state
     * @return
     */
    Result searchSvInfo(Byte state,Integer pageNo,Integer pageSize);

    /**
     * 根据用户id查询商家审核通过的信息
     */
    Result verifyByUserId(Integer userId);

    /**
     * 修改商家审核状态
     *
     * @param svId
     * @param state
     * @return
     */
    Result updateSvState(Integer svId, Byte state);
}
