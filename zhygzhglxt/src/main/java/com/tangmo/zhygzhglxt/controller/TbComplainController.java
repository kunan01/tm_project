package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbComplain;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chengge on 2018/10/29.
 */
@Api("投诉的接口")
@RestController
@RequestMapping("/complain")
public class TbComplainController extends BaseController {


    @ApiOperation(value = "增加投诉信息", notes = "")
    @ApiImplicitParam(name = "tbComplain", value = "增加投诉信息", required = true, dataType = "TbComplain")
    @PostMapping("/addComplain")
    public Result addComplain(@RequestBody TbComplain tbComplain) {

        return tbComplainService.addComplain(tbComplain);
    }

    @ApiOperation(value = "根据投诉的唯一标识删除投诉信息", notes = "")
    @SuppressWarnings("rawtypes")
    @GetMapping("/delComplainByCode/{code}")
    public Result delComplainByCode(@PathVariable String code) {

        return tbComplainService.delComplainByCode(code);
    }

    /*
     * 查询投诉信息
     *fbState：传0 查询未读 传1查询已读 不传：查询全部
     * */
    @ApiOperation(value = "根据状态查询投诉信息(可模糊) 传0 查询未读 传1查询已读", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "关键字", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "state", value = "状态", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selComplain")
    public Result selComplain(String name, String state, Integer pageSize, Integer pageNo) {

        return tbComplainService.selComplain(name, state, pageSize, pageNo);
    }

    /*
     * 根据用户的唯一标识查询我的投诉
     * */
    @ApiOperation(value = "根据用户的唯一标识查询我的投诉", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "关键字", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "state", value = "投诉状态", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "userCode", value = "用户的唯一标识", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selComplainByUserCode")
    public Result selComplainByUserCode(String name, String state, String userCode, Integer pageSize, Integer pageNo) {

        return tbComplainService.selComplainByUserCode(name, state, userCode, pageSize, pageNo);
    }

    /*
     * 审核投诉信息
     * */
    @ApiOperation(value = "审核投诉信息", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParam(name = "tbComplain", value = "审核投诉信息", required = true, dataType = "TbComplain")
    @PostMapping("/verifyComplain")
    public Result verifyComplain(@RequestBody TbComplain tbComplain) {

        return tbComplainService.verifyComplain(tbComplain);
    }


    /*
     * 查询投诉详情
     * */
    @ApiOperation(value = "查询投诉详情", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selComplainByCode")
    public Result selComplainByCode(String code) {

        return tbComplainService.selComplainByCode(code);
    }
}
