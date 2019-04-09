package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.utility.Result;

public interface AnalysisService {
    // 每辆车每天接单数量统计
    Result getOrderCountByCar(Integer pageNo, Integer pageSize, String startTime, String endTime, String carNumber, String driverPhone);
}
