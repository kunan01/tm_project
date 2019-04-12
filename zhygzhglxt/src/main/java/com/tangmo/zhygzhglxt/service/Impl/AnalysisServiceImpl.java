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
    public Result getOrderCountByCar(Integer pageNo, Integer pageSize, String startDate, String endDate, String carNumber, String driverPhone, Integer dateType) {
        // 根据查询类型确定日期格式(dateType 默认为 1 按月份统计)
        String dateFormat = getDateFormat(dateType);
        PageHelper.startPage(pageNo, pageSize, true);
        List<Map<String, Object>> list = analysisMapper.getOrderCountByCar(startDate, endDate, carNumber, driverPhone, dateType, dateFormat);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return new Result(ResultCode.SUCCESS, pageInfo);
    }

    @Override
    public Result getTotalKmByCar(Integer pageNo, Integer pageSize, String startDate, String endDate, String carNumber, String driverPhone, Integer dateType) {
        // 根据查询类型确定日期格式(dateType 默认为 1 按月份统计)
        String dateFormat = getDateFormat(dateType);
        PageHelper.startPage(pageNo, pageSize, true);
        List<Map<String, Object>> list = analysisMapper.getTotalKmByCar(startDate, endDate, carNumber, driverPhone, dateType, dateFormat);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return new Result(ResultCode.SUCCESS, pageInfo);
    }

    // 根据查询类型确定日期格式(dateType 默认为 1 按月份统计)
    public String getDateFormat (Integer dateType) {
        String dateFormat = "%Y-%m"; // 按月统计
        if (dateType == 0) {
            dateFormat = "%Y-%m-%d"; // 按天统计
        } else if (dateType == 2){
            dateFormat = "%Y"; // 按年统计
        }
        return dateFormat;
    }
}
