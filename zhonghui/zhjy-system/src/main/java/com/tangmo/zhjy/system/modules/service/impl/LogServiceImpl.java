package com.tangmo.zhjy.system.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.dao.LogMapper;
import com.tangmo.zhjy.system.modules.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chengge on 2018/8/12.
 */
@Service
public class LogServiceImpl implements LogService {


    @Autowired
    private LogMapper logMapper;

    @Override
    public Result deleteByPrimaryKey(Integer logId) {
        return new Result(ResultCode.SUCCESS,logMapper.deleteByPrimaryKey(logId));
    }

    @Override
    public Result serchListLog(Integer pageNo, Integer pageSize) {

        if(pageSize!=null && pageNo!=null){
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Log> logs=logMapper.findPageLog();
        return new Result(ResultCode.SUCCESS,new PageInfo(logs));
    }

    @Override
    public Result addLog(Log tableLog) {

        return new Result(ResultCode.SUCCESS,logMapper.insertSelective(tableLog));
    }
}
