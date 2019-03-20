package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbSysModule;
import com.tangmo.zhygzhglxt.entity.TbSysPermissions;
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
@Api("后台权限接口")
@RestController
@RequestMapping("/permissions")
public class TbSysPermissionsController extends BaseController {


    @ApiOperation(value = "增加权限信息", notes = "")
    @ApiImplicitParam(name = "tbSysPermissions", value = "增加权限信息", required = true, dataType = "TbSysPermissions")
    @PostMapping("/addSysPermissions")
    public Result addSysPermissions(@RequestBody TbSysPermissions tbSysPermissions) {
        return tbSysPermissionsService.addSysPermissions(tbSysPermissions);
    }

    @ApiOperation(value = "修改权限信息", notes = "")
    @ApiImplicitParam(name = "tbSysPermissions", value = "增加权限信息", required = true, dataType = "TbSysPermissions")
    @PostMapping("/updateSysPermissions")
    public Result updateSysPermissions(@RequestBody TbSysPermissions tbSysPermissions) {
        return tbSysPermissionsService.updateSysPermissions(tbSysPermissions);
    }

    @ApiOperation(value = "根据权限唯一标识code删除权限（概念性删除）", notes = "")
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/delSysPermissionsByCode/{code}")
    public Result delSysPermissionsByCode(@PathVariable String code) {
        return tbSysPermissionsService.delSysPermissionsByCode(code);
    }

    /*
     * 查询所有权限信息
     * */
    @ApiOperation(value = "查询所有权限信息(可模糊,可分页)", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模糊条件", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selSysPermissions")
    public Map selSysPermissions(String name, Integer pageSize, Integer pageNo) {

        Result result = tbSysPermissionsService.selSysPermissions(name, pageSize, pageNo);
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
     * 根据权限唯一标识code查询权限信息
     * */
    @ApiOperation(value = "根据权限唯一标识code查询权限信息", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "权限唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selSysPermissionsByCode")
    public Result selSysPermissionsByCode(String code) {

        return tbSysPermissionsService.selSysPermissionsByCode(code);
    }


}
