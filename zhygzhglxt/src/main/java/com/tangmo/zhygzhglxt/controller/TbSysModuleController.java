package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbFeedBack;
import com.tangmo.zhygzhglxt.entity.TbSysModule;
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
 * Created by chengge on 2018/10/23.
 */
@Api("后台模块接口")
@RestController
@RequestMapping("/module")
public class TbSysModuleController extends BaseController {

    @ApiOperation(value = "增加模块信息", notes = "")
    @ApiImplicitParam(name = "tbSysModule", value = "增加模块信息", required = true, dataType = "TbSysModule")
    @PostMapping("/addSysModule")
    public Result addSysModule(@RequestBody TbSysModule tbSysModule) {
        return tbSysModuleService.addSysModule(tbSysModule);
    }

    @ApiOperation(value = "修改模块信息", notes = "")
    @ApiImplicitParam(name = "tbSysModule", value = "增加模块信息", required = true, dataType = "TbSysModule")
    @PostMapping("/updateSysModule")
    public Result updateSysModule(@RequestBody TbSysModule tbSysModule) {
        return tbSysModuleService.updateSysModule(tbSysModule);
    }

    @ApiOperation(value = "根据模块唯一标识code删除模块（概念性删除）", notes = "")
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/delSysModuleByCode/{code}")
    public Result delSysModuleByCode(@PathVariable String code) {
        return tbSysModuleService.delSysModuleByCode(code);
    }

    /*
     * 查询所有模块信息
     * */
    @ApiOperation(value = "查询所有模块信息(可模糊,可分页)", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模糊条件", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selSysModule")
    public Map selSysModule(String name, Integer pageSize, Integer pageNo) {

        Result result = tbSysModuleService.selSysModule(name, pageSize, pageNo);
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
     * 根据模块唯一标识code查询模块信息
     * */
    @ApiOperation(value = "根据模块唯一标识code查询模块信息", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "模块唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selSysModuleByCode")
    public Result selSysModuleByCode(String code) {

        return tbSysModuleService.selSysModuleByCode(code);
    }


}
