package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbDriverVerify;
import com.tangmo.zhygzhglxt.entity.TbParm;
import com.tangmo.zhygzhglxt.entity.dto.TbDriverVerifyDto;
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
@Api("车主接口")
@RestController
@RequestMapping("/driverVerify")
public class TbDriverVerifyController extends BaseController {


    @ApiOperation(value = "申请成为车主", notes = "")
    @ApiImplicitParam(name = "tbDriverVerify", value = "车主审核实体", required = true, dataType = "TbDriverVerify")
    @PostMapping("/addDriverVerify")
    public Result addDriverVerify(@RequestBody TbDriverVerify tbDriverVerify) {

        return tbDriverVerifyService.addDriverVerify(tbDriverVerify);
    }

    /*GET /driverVerify/jtQueryById
     * 查询车主审核信息（可模糊查，可分页，可根据状态查）
     *state：传0 查询未读 传1查询已读 不传：查询全部
     * */
    @ApiOperation(value = "查询车主审核信息（可模糊查，可分页，可根据状态查", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "关键字", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "state", value = "状态", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selDriverVerify")
    public Map selDriverVerify(String name, String state, Integer pageSize, Integer pageNo) {
        Result result = tbDriverVerifyService.selDriverVerify(name, state, pageSize, pageNo);
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
     * 根据用户的唯一标识查询车主的审核信息
     * */
    @ApiOperation(value = "根据用户的唯一标识查询车主的审核信息", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selDriverVerifyByUserCode")
    public Result selDriverVerifyByUserCode(String userCode) {

        return tbDriverVerifyService.selDriverVerifyByUserCode(userCode);
    }

    /*
     * 审核车主信息（state 1通过 2驳回）
     * */
    @ApiOperation(value = "审核车主信息（state 1通过 2驳回）", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "状态", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "driverVerifyCode", value = "唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/verifyDriver")
    public Result verifyDriver(String state, String driverVerifyCode) {

        return tbDriverVerifyService.verifyDriver(state, driverVerifyCode);
    }

    /*
     * 概念性删除车主的审核信息
     * */
    @ApiOperation(value = "概念性删除车主的审核信息", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @PutMapping("/delDriverVerifyByCode")
    public Result delDriverVerifyByCode(String code) {

        return tbDriverVerifyService.delDriverVerifyByCode(code);
    }


    @ApiOperation(value = "根据唯一标识code查找车主详细信息")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/jtQueryById")
    public Result jtQueryById(String code) {

        return tbDriverVerifyService.jtQueryById(code);
    }

    @ApiOperation(value = "实时更新司机的经度纬度")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "司机用户的唯一标识", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "carLa", value = "当前纬度", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "carLo", value = "当前经度", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "driverOrderCode", value = "司机订单的唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/updateCarLaLo")
    public Result updateCarLaLo(String userCode, String carLa, String carLo, String driverOrderCode) {

        return tbDriverVerifyService.updateCarLaLo(userCode, carLa, carLo, driverOrderCode);
    }

    @ApiOperation(value = "根据司机用户的唯一标识获取当前司机的实时的经度纬度")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "driverUserCode", value = "司机用户的唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/selCarLaLoByUserCode")
    public Result selCarLaLoByUserCode(String driverUserCode) {

        return tbDriverVerifyService.selCarLaLoByUserCode(driverUserCode);
    }

}
