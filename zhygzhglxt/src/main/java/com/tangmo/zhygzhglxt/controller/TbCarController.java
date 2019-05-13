package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbCar;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengge on 2019/3/4.
 */
@Api("同乡客运车辆信息")
@RestController
@RequestMapping("/car")
public class TbCarController extends BaseController {

    @ApiOperation(value = "增加同乡客运车辆信息", notes = "")
    @ApiImplicitParam(name = "tbCar", value = "同乡客运车辆信息", required = true, dataType = "TbCar")
    @PostMapping("/addCar")
    public Result addCar(@RequestBody TbCar tbCar) {
        return tbCarService.addCar(tbCar);
    }

    @ApiOperation(value = "根据主键id删除同乡客运车辆信息", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "车辆主键", dataType = "string", required = true, paramType = "query")
    })
    @DeleteMapping("/delCarById")
    public Result delCarById(@PathVariable String id) {
        return tbCarService.delCarById(id);
    }

    /*
     * 查询同乡客运车辆信息
     * */
    @ApiOperation(value = "查询同乡客运车辆信息", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "关键字", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selAllTbCar")
    public Map selAllTbCar(String name, Integer pageSize, Integer pageNo) {

        Result result = tbCarService.selAllTbCar(name, pageSize, pageNo);

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

    /*
     * 根据设备号查询车辆
     * */
    @ApiOperation(value = "根据设备号查询车辆", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceNumber", value = "设备号", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selCarByDeviceNumber")
    public Result selCarByDeviceNumber(String deviceNumber) {

        return tbCarService.selCarByDeviceNumber(deviceNumber);
    }

    /*
     * 根据车牌查询车辆
     * */
    @ApiOperation(value = "根据车牌查询车辆", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carNumber", value = "车牌号", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selCarByCarNumber")
    public Result selCarByCarNumber(String carNumber) {

        return tbCarService.selCarByCarNumber(carNumber);
    }

    @ApiOperation("返回所有车辆位置信息")
    @GetMapping("/getAllCarLocation")
    public Result getAllCarLocation() {
        return tbCarService.getAllCarLocation();
    }

}
