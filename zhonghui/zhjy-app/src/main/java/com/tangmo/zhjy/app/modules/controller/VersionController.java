package com.tangmo.zhjy.app.modules.controller;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.Version;
import com.tangmo.zhjy.app.modules.service.VersionService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Created by chengge on 2018/10/9.
 */
@RestController
@RequestMapping("/version")
@Api("app版本相关接口")
public class VersionController {

    @Resource
    private VersionService versionServiceImpl;

    @ApiOperation(value="比对最新版本号",notes="版本号")
    @ApiImplicitParams({
            @ApiImplicitParam(name="versionNumber",value="版本号",dataType="string",required=true,paramType="query")
    })
    @GetMapping("/getVersion")
    public Result getVersion(String versionNumber){

        return versionServiceImpl.getVersion(versionNumber);
    }


    /**
     * 添加最新版本
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value="添加最新版本",notes="添加最新版本")
    @PostMapping(value="/addVersion")
    public Result addVersion( @RequestBody Version version){

        return versionServiceImpl.addVersion(version);
    }

}
