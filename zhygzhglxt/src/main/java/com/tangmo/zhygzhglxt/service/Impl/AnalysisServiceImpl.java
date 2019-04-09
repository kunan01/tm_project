package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.AnalysisMapper;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.AnalysisService;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public Result getOrderCountByCar(Integer pageNo, Integer pageSize, String startTime, String endTime, String carNumber, String driverPhone) {
        PageHelper.startPage(pageNo, pageSize, true);
        List<Map<String, Object>> list = analysisMapper.getOrderCountByCar(startTime, endTime, carNumber, driverPhone);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return new Result(ResultCode.SUCCESS, pageInfo);
    }
}
