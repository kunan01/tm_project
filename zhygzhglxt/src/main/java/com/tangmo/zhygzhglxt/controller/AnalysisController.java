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

    @ApiOperation(value = "每辆车每天接单数量统计", notes = "每辆车每天接单数量统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页码", dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数", dataType = "int", defaultValue = "10", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "carNumber", value = "车牌号码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "driverPhone", value = "车主手机号码", dataType = "String", paramType = "query")
    })
    @GetMapping("/getOrderCountByCar")
    public Result getOrderCountByCar(Integer pageNo, Integer pageSize, String startTime, String endTime, String carNumber, String driverPhone){
        return analysisService.getOrderCountByCar(pageNo, pageSize, startTime, endTime, carNumber, driverPhone);
    }
}
