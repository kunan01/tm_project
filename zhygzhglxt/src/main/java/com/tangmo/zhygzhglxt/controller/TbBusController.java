package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.utility.Result;
import com.tangmo.zhygzhglxt.utility.jedis.JedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chengge on 2018/11/12.
 */
@Api("公交车")
@RestController
@RequestMapping("/bus")
public class TbBusController extends BaseController {


    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    /*
     * 模糊搜索公交车（根据站点名称或者公交车名称分页查询）
     * */
    @ApiOperation(value = "查询所有公交车（分页查询）", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页几条", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "busType", value = "公交类型", dataType = "String", required = false, paramType = "query")
    })
    @GetMapping("/likeBusByName")
    public Result likeBusByName(Integer pageSize, Integer pageNo, String busType) {

        return tbBusService.likeBusByName(pageSize, pageNo, busType);
    }

    /*
     * 公交车站点详情
     * */
    @ApiOperation(value = "公交车站点详情", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeCode", value = "线路表id", dataType = "String", required = false, paramType = "query")
    })
    @GetMapping("/getBusDetails")
    public Result getBusDetails(String routeCode) {
        return tbBusService.getBusDetails(routeCode);
    }


    /*
     * 获取线路地图信息
     * */
    @ApiOperation(value = "获取线路地图信息", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "busId", value = "公车表id", dataType = "String", required = false, paramType = "query")
    })
    @GetMapping("/getBusGPS")
    public Result getBusGPS(String busId) {
        return tbBusService.getBusGPS(busId);
    }

    /*
     * 根据busId和站点的经纬度找出最近的车辆需要几分钟到达
     * */
    @ApiOperation(value = "根据busId和当前站点的经纬度找出最近的车辆需要几分钟到达", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "busId", value = "公车表id", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "routeCode", value = "路线的唯一标识code", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "siteLa", value = "站点纬度", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "siteLo", value = "站点经度", dataType = "String", required = true, paramType = "query")
    })
    @GetMapping("/getInfoByBusIdSiteLoLa")
    public Result getInfoByBusIdSiteLoLa(String busId, String routeCode, String siteLa, String siteLo) {
        return tbBusService.getInfoByBusIdSiteLoLa(busId, routeCode, siteLa, siteLo);
    }

    /*
     * 根据busId和站点的经纬度找出最近的车辆需要几分钟到达
     * */
    @ApiOperation(value = "根据busId和当前站点的经纬度找出最近的车辆", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "busId", value = "公车表id", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "routeCode", value = "路线的唯一标识code", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "siteLa", value = "站点纬度", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "siteLo", value = "站点经度", dataType = "String", required = true, paramType = "query")
    })
    @GetMapping("/getBusInfoByBusIdSiteLoLa")
    public Result getBusInfoByBusIdSiteLoLa(String busId, String routeCode, String siteLa, String siteLo) {
        return tbBusService.getBusInfoByBusIdSiteLoLa(busId, routeCode, siteLa, siteLo);
    }

    /*
     * 根据路线code的唯一标识查询最近的站点
     * */
    @ApiOperation(value = "根据路线code的唯一标识查询最近的站点", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeCode", value = "路线的唯一标识code", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "NowLa", value = "当前的纬度", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "NowLo", value = "当前的经度", dataType = "String", required = true, paramType = "query")
    })
    @GetMapping("/getSiteByLoLa")
    public Result getSiteByLoLa(String routeCode, String NowLa, String NowLo) {
        return tbBusService.getSiteByLoLa(routeCode, NowLa, NowLo);
    }

    /*
     * 乘客端根据公交车的号如901，实时查询公交车的运动轨迹
     * */
    @ApiOperation(value = "乘客端根据公交车的号如：901，实时查询公交车的运动轨迹", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "busNumber", value = "公交车的号", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selBusByTime")
    public Result selBusByTime(String busNumber) {

        return tbBusService.selBusByTime(busNumber);
    }


    /*
     * 公交车站点路线详情
     * */
    @ApiOperation(value = "公交车站点路线详情（地图绘制公交路线）", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeCode", value = "线路id", dataType = "String", required = true, paramType = "query")
    })
    @GetMapping("/getBusRoutelDetails")
    public Result getBusRoutelDetails(String routeCode) {
        return tbBusService.getBusRoutelDetails(routeCode);
    }

    /*
     * 获取最新接入的Gps历史数据
     * */
    @ApiOperation(value = "获取最新接入的Gps历史数据", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imei", value = "设备号", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "分页，每页最多条数（默认100条）", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "totalNumber", value = "最多取总条数（默认5000条）", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/getInfoByImei")
    public Result getInfoByImei(String imei, String startTime, String endTime, Integer pageNumber, Integer totalNumber) {
        return tbBusService.getInfoByImei(imei, startTime, endTime, pageNumber, totalNumber);
    }

    /*
     * 请求下一页的Gps历史数据
     * */
    @ApiOperation(value = "请求下一页的Gps历史数据", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imei", value = "设备号", dataType = "String", required = true, paramType = "query")
    })
    @GetMapping("/getNextInfoByImei")
    public Result getNextInfoByImei(String imei) {
        return tbBusService.getNextInfoByImei(imei);
    }

    /*
     * 结束请求Gps历史数据
     * */
    @ApiOperation(value = "结束请求Gps历史数据", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imei", value = "设备号", dataType = "String", required = true, paramType = "query")
    })
    @GetMapping("/overInfoByImei")
    public Result overInfoByImei(String imei) {
        return tbBusService.overInfoByImei(imei);
    }

    /*
     * 获取最最后一次最新的Gps数据
     * */
    @ApiOperation(value = "获取最最后一次最新的Gps数据", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imei", value = "设备号", dataType = "String", required = true, paramType = "query")
    })
    @GetMapping("/getLastInfoByImei")
    public Result getLastInfoByImei(String imei) {

        return tbBusService.getLastInfoByImei(imei);
    }


}
