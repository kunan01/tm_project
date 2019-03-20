package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbSysRole;
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
@Api("后台角色接口")
@RestController
@RequestMapping("/role")
public class TbSysRoleController extends BaseController {

    @ApiOperation(value = "增加角色信息", notes = "")
    @ApiImplicitParam(name = "tbSysRole", value = "增加角色信息", required = true, dataType = "TbSysRole")
    @PostMapping("/addSysRole")
    public Result addSysRole(@RequestBody TbSysRole tbSysRole) {
        return tbSysRoleService.addSysRole(tbSysRole);
    }

    @ApiOperation(value = "修改角色信息", notes = "")
    @ApiImplicitParam(name = "tbSysRole", value = "增加角色信息", required = true, dataType = "TbSysRole")
    @PostMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody TbSysRole tbSysRole) {
        return tbSysRoleService.updateSysRole(tbSysRole);
    }

    @ApiOperation(value = "根据角色唯一标识code删除角色（概念性删除）", notes = "")
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/delSysRoleByCode/{code}")
    public Result delSysRoleByCode(@PathVariable String code) {
        return tbSysRoleService.delSysRoleByCode(code);
    }

    /*
     * 查询所有角色信息
     * */
    @ApiOperation(value = "查询所有角色信息(可模糊,可分页)", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模糊条件", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selSysRole")
    public Map selSysRole(String name, Integer pageSize, Integer pageNo) {

        Result result = tbSysRoleService.selSysRole(name, pageSize, pageNo);
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
     * 根据角色唯一标识code查询角色拥有的模块
     * */
    @ApiOperation(value = "根据角色唯一标识code查询角色拥有的模块", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "角色唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selSysRoleByCode")
    public Result selSysRoleByCode(String code) {

        return tbSysRoleService.selSysRoleByCode(code);
    }

}
