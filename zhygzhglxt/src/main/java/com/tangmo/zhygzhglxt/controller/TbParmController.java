package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbParm;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengge on 2018/7/16.
 */
@Api("类别参数的接口")
@RestController
@RequestMapping("/parm")
public class TbParmController extends BaseController {

    @ApiOperation(value = "增加类别参数信息", notes = "")
    @ApiImplicitParam(name = "tbParm", value = "类别参数实体类", required = true, dataType = "TbParm")
    @PostMapping("/addParm")
    public Result addParm(@RequestBody TbParm tbParm) {
        return tbParmService.addParm(tbParm);
    }

    @ApiOperation(value = "根据类别参数id删除", notes = "")
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/deParmById/{parmId}")
    public Result delParmById(@PathVariable String parmId) {
        return tbParmService.delParmById(parmId);
    }

    /*
     * 根据类型查询数字参数信息
     *numberType：1查询车辆类型 2投诉类型 3车辆颜色
     * */
    @ApiOperation(value = "根据类型查询类别参数信息 numberType：传1查询车辆类型 2投诉类型 3车辆颜色", notes = "")
    @SuppressWarnings("rawtypes")
    @GetMapping("/selParmByType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parmType", value = "类别参数类型", dataType = "String", required = false, paramType = "query"),
    })
    public Result<List<TbParm>> selParmByType(String parmType) {
        return tbParmService.selParmByType(parmType);
    }

    /*
     * 根据类型查询数字参数信息
     * bus 代表查询公交类型
     * */
    @ApiOperation(value = "根据类型查询类别参数信息 ：传 busType 代表查询公交类型", notes = "根据类型查询类别参数信息 busType：传 bus 代表查询公交类型")
    @SuppressWarnings("rawtypes")
    @GetMapping("/selBusType")
    public Result<List<TbParm>> selBusType() {
        return tbParmService.selBusType();
    }

    /*
     * 专门为党诚林所写根据类型查询数字参数信息
     *numberType：1查询车辆类型 2投诉类型 3车辆颜色
     * */
    @ApiOperation(value = "根据类型查询类别参数信息 numberType：传 2投诉类型 3车辆颜色 4查询车辆类型(专为乘客端所写)", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parmType", value = "类别参数类型", dataType = "String", required = false, paramType = "query"),
    })
    @GetMapping("/selParmByParmType")
    public Result selParmByParmType(String parmType) {
        Result result = tbParmService.selParmByType(parmType);
        List<TbParm> tbParms = (List<TbParm>) result.getData();
        List<Map> maps = new ArrayList();
        for (TbParm tbParm : tbParms) {
            Map map = new HashMap();
            map.put("id", tbParm.getParmId());
            map.put("text", tbParm.getParmName());
            maps.add(map);
        }
        result.setData(maps);
        return result;
    }

    @ApiOperation(value = "根据类别参数id查询信息", notes = "")
    @SuppressWarnings("rawtypes")
    @GetMapping("/selParmById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parmId", value = "类别参数id", dataType = "string", required = true, paramType = "query"),
    })
    public Result<TbParm> selParmById(String parmId) {
        return tbParmService.selParmById(parmId);
    }

}
