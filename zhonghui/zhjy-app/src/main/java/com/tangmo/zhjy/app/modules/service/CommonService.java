package com.tangmo.zhjy.app.modules.service;


import com.tangmo.zhjy.app.Result;

/**
 * @author Chamber
 * @date 2018/3/9.
 */
public interface CommonService {

    /**
     * 获取区域
     *
     * @param city
     * @return
     */
    Result searchDistrict(String city);

    //获取系统消息
    Result getMessage(Integer userId,Integer pageNo,Integer pageSize);
}
