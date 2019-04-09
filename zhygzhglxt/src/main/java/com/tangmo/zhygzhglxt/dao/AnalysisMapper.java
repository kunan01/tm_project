package com.tangmo.zhygzhglxt.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AnalysisMapper {
    // 每辆车每天接单数量统计
    List<Map<String, Object>> getOrderCountByCar(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("carNumber") String carNumber, @Param("driverPhone") String driverPhone);
}
