package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.Version;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by chengge on 2018/10/9.
 */
@RestController
@RequestMapping("/version")
@Api("app版本相关接口")
public class VersionController extends BaseController {

    @ApiOperation(value = "比对最新版本号", notes = "版本号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionNumber", value = "版本号", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "客户端类型（1乘客端 2司机端）", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/getVersion")
    public Result<Version> getVersion(String versionNumber, String type) {

        return versionService.getVersion(versionNumber, type);
    }


    /**
     * 添加最新版本
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "添加最新版本", notes = "添加最新版本")
    @PostMapping(value = "/addVersion")
    public Result addVersion(@RequestBody Version version) {

        return versionService.addVersion(version);
    }

    /**
     * 下载最新版本的乘客端
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "下载最新版本的乘客端", notes = "下载最新版本的乘客端")
    @GetMapping(value = "/uploadVersion1")
    public ResponseEntity<byte[]> uploadVersion1() {
        String userAgent = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("USER-AGENT");
        return versionService.uploadVersion1(userAgent);
    }

    /**
     * 下载最新版本的司机端
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "下载最新版本的司机端", notes = "下载最新版本的司机端")
    @GetMapping(value = "/uploadVersion2")
    public ResponseEntity<byte[]> uploadVersion2() {
        String userAgent = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("USER-AGENT");
        return versionService.uploadVersion2(userAgent);
    }

}
