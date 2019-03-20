package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.Log;

/**
 * Created by chengge on 2018/8/12.
 */
public interface LogService {

    /**
     * 删除日志
     */
    Result deleteByPrimaryKey(Integer logId);

    /**
     * 分页查询所有日志信息
     */
    Result serchListLog(Integer pageNo,Integer pageSize);

    /**
     * 增加日志信息
     */
    Result addLog(Log tableLog);
}
