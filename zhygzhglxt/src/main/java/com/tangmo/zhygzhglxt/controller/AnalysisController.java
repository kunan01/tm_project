package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("统计分析模块")
@RequestMapping("/analysis")
@RestController
@Slf4j
public class AnalysisController extends BaseController {

    @ApiOperation(value = "每辆车的接单数量统计（日/月/年）", notes = "每辆车的接单数量统计（日/月/年）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页码", dataType = "int", required = true, defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数", dataType = "int", required = true, defaultValue = "20", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "开始时间(YYYY-MM-DD)", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间(YYYY-MM-DD)", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "carNumber", value = "车牌号码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "driverPhone", value = "车主手机号码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "dateType", value = "日期类型 0-天 1-月 2-年", dataType = "int", required = true, defaultValue = "1", paramType = "query")
    })
    @GetMapping("/getOrderCountByCar")
    public Result getOrderCountByCar(Integer pageNo, Integer pageSize, String startDate, String endDate, String carNumber, String driverPhone, Integer dateType){
        return analysisService.getOrderCountByCar(pageNo, pageSize, startDate, endDate, carNumber, driverPhone, dateType);
    }

    @ApiOperation(value = "每辆车跑的公里数统计（日/月/年）", notes = "每辆车跑的公里数统计（日/月/年）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页码", dataType = "int", required = true, defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数", dataType = "int", required = true, defaultValue = "20", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "开始时间(YYYY-MM-DD)", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间(YYYY-MM-DD)", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "carNumber", value = "车牌号码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "driverPhone", value = "车主手机号码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "dateType", value = "日期类型 0-天 1-月 2-年", dataType = "int", required = true, defaultValue = "1", paramType = "query")
    })
    @GetMapping("/getTotalKmByCar")
    public Result getTotalKmByCar(Integer pageNo, Integer pageSize, String startDate, String endDate, String carNumber, String driverPhone, Integer dateType){
        return analysisService.getTotalKmByCar(pageNo, pageSize, startDate, endDate, carNumber, driverPhone, dateType);
    }
}
