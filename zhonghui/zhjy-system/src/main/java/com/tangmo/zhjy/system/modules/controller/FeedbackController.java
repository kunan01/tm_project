package com.tangmo.zhjy.system.modules.controller;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.Feedback;
import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import com.tangmo.zhjy.system.modules.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chengge on 2018/6/8.
 */
@Api("反馈接口")
@RestController
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {

    @Autowired
    private FeedbackService feedbackServiceImpl;

    @ApiOperation(value="增加反馈信息",notes="")
    @PostMapping("/addFeedBack")
    public Result addFeedBack(@RequestBody Feedback feedback){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("增加反馈信息");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
        return feedbackServiceImpl.addFeedBack(feedback);
    }

    @ApiOperation(value="删除反馈",notes="")
    @DeleteMapping("/deleteByPrimaryKey/{fbId}")
    public Result deleteByPrimaryKey(@PathVariable Integer fbId){
        //生成日志
        String ip = getRequest().getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0){
            ip = getRequest().getRemoteAddr();
        }
        SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
        Log logTable=new Log();
        logTable.setUserId(userBean.getId());
        logTable.setRoleName(userBean.getRoleName());
        logTable.setParam("删除反馈");
        logTable.setMethodUrl(ip);
        logService.addLog(logTable);
        return feedbackServiceImpl.deleteByPrimaryKey(fbId);
    }

    @ApiOperation(value="分页查询所有反馈信息",notes="反馈信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNo",value="当前页,必填：false",dataType="int",required=false,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="显示多少条参数,必填：false",dataType="int",required=false,paramType="query"),
    })
    @GetMapping("/serchListFeedback")
    public Result serchListFeedback(Integer pageNo,Integer pageSize){
        return feedbackServiceImpl.serchListFeedback(pageNo,pageSize);
    }


}
