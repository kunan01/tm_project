package com.tangmo.zhygzhglxt.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AnalysisMapper {
    // 每辆车接单数量统计（日/月/年）
    List<Map<String, Object>> getOrderCountByCar(@Param("startDate") String startDate,
                                                 @Param("endDate") String endDate,
                                                 @Param("carNumber") String carNumber,
                                                 @Param("driverPhone") String driverPhone,
                                                 @Param("dateType") Integer dateType,
                                                 @Param("dateFormat") String dateFormat);

    // 每辆车跑的公里数统计（日/月/年）
    List<Map<String, Object>> getTotalKmByCar(@Param("startDate") String startDate,
                                              @Param("endDate") String endDate,
                                              @Param("carNumber") String carNumber,
                                              @Param("driverPhone") String driverPhone,
                                              @Param("dateType") Integer dateType,
                                              @Param("dateFormat") String dateFormat);
}
