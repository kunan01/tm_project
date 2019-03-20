package com.tangmo.zhjy.system.modules.controller;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import com.tangmo.zhjy.system.modules.service.LogService;
import com.tangmo.zhjy.system.security.properties.SecurityProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chengge on 2018/8/12.
 */
@RestController
@RequestMapping("/log")
@Api("日志接口")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;


    @ApiOperation(value="删除日志",notes="")
    @DeleteMapping("/deleteByPrimaryKey/{logId}")
    public Result deleteByPrimaryKey(@PathVariable Integer logId){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("删除日志");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
        return logService.deleteByPrimaryKey(logId);
    }

    @ApiOperation(value="分页查询所有日志信息",notes="日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNo",value="当前页,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="显示多少条参数,必填：false",dataType="int",required=false,paramType="query"),
    })
    @GetMapping("/serchListLog")
    public Result serchListLog(Integer pageNo,Integer pageSize){
        return logService.serchListLog(pageNo,pageSize);
    }
}
