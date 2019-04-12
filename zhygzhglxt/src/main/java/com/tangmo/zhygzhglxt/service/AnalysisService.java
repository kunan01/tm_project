package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.utility.Result;

public interface AnalysisService {
    // 每辆车每天接单数量统计
    Result getOrderCountByCar(Integer pageNo, Integer pageSize, String startDate, String endDate, String carNumber, String driverPhone, Integer dateType);

    // 每辆车跑的公里数统计（日/月/年）
    Result getTotalKmByCar(Integer pageNo, Integer pageSize, String startDate, String endDate, String carNumber, String driverPhone, Integer dateType);
}
