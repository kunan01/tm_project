package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.entity.TbShuttleBus;
import com.tangmo.zhygzhglxt.entity.TbSysModule;
import com.tangmo.zhygzhglxt.service.TbShuttleBusService;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api("班车接口")
@RestController
@RequestMapping("/shuttlebus")
public class TbShuttleBusController {


    @Autowired
    private TbShuttleBusService tbShuttleBusService;


    @ApiOperation(value = "查询所有班车")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "途径站名", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "areaId", value = "区域id", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/jtQueryShuttleBus")
    public Map jtQueryShuttleBus(String name, String areaId, Integer pageNo, Integer pageSize) {

        Result result = tbShuttleBusService.jtQueryShuttleBus(name, areaId, pageNo, pageSize);
        PageInfo pageInfo = (PageInfo) result.getData();
        Map map = new HashMap();
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("data", pageInfo.getList());
        map.put("pageNo", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @ApiOperation(value = "添加班车")
    @PostMapping("/jtAdd")
    public Result jtAdd(@RequestBody TbShuttleBus tbShuttleBus) {

        return tbShuttleBusService.jtAdd(tbShuttleBus);
    }

    @ApiOperation(value = "删除指定班车车次")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shuttleBusCode", value = "班车的唯一标识code", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/jtDelete")
    public Result jtDelete(String shuttleBusCode) {

        return tbShuttleBusService.jtDelete(shuttleBusCode);
    }

    @ApiOperation(value = "修改指定班车车次", notes = "")
    @PostMapping("/jtUpdate")
    public Result jtUpdate(@RequestBody TbShuttleBus tbShuttleBus) {

        return tbShuttleBusService.jtUpdate(tbShuttleBus);
    }


}
