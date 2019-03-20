package com.tangmo.yiliao.controller.webController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.Integral;
import com.tangmo.yiliao.entity.Message;
import com.tangmo.yiliao.entity.RsFile;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-9-5
 * @description 后台积分和消息
 */
@Api("system积分和消息")
@RestController
@RequestMapping("/integral")
public class IntegralController extends BaseController {

    //后台统计用户积分总额数量
    @PostMapping("/getUserIntegralAllCount")
    public Result getUserIntegralAllCount(@RequestBody SelectUser selectUser){
        return integralService.getUserIntegralAllCount(selectUser);
    }

    //后台统计用户积分总额
    @PostMapping("/getUserIntegralAll")
    public Result getUserIntegralAll(@RequestBody SelectUser selectUser){
        return integralService.getUserIntegralAll(selectUser);
    }

    //获取所有积分规则信息
    @GetMapping("/getIntegralAll")
    public Result getIntegralAll(){
        return integralService.getIntegralAll();
    }

    //删除积分规则信息
    @GetMapping("/delIntegralById/{lrId}/{userId}")
    public Result delIntegralById(@PathVariable String lrId,@PathVariable String userId){
        return integralService.delIntegralById(lrId,userId);
    }

    //修改积分规则信息
    @GetMapping("/updIntegralById/{lrId}/{userId}/{lrName}/{bean}")
    public Result updIntegralById(@PathVariable String lrId,@PathVariable String userId,@PathVariable String lrName,@PathVariable Integer bean){
        return integralService.updIntegralById(lrId,userId,lrName,bean);
    }

    //添加积分规则信息
    @PostMapping("/addIntegralById")
    public Result addIntegralById(@RequestBody Integral integral){
        return integralService.addIntegralById(integral);
    }

    //条件获取所有系统消息(分页)
    @PostMapping("/getSystemMessage")
    public Result getSystemMessage(@RequestBody SelectUser selectUser){
        return integralService.getSystemMessage(selectUser);
    }

    //条件获取系统消息数量
    @PostMapping("/getSystemMessageCount")
    public Result getSystemMessageCount(@RequestBody SelectUser selectUser){
        return integralService.getSystemMessageCount(selectUser);
    }

    //后台添加系统消息
    @PostMapping("/addSystemMessage")
    public Result addSystemMessage(@RequestBody Message message){
        return integralService.addSystemMessage(message);
    }

    //后台删除系统消息
    @GetMapping("/delSystemMessageById/{msId}")
    public Result delSystemMessageById(@PathVariable String msId){
        return integralService.delSystemMessageById(msId);
    }

    //后台修改系统消息
    @PostMapping("/updSystemMessage")
    public Result updSystemMessage(@RequestBody Message message){
        return integralService.updSystemMessage(message);
    }

    //后台获取短信请求地址
    @GetMapping("/getCode")
    public Result getCode(){
        return integralService.getCode();
    }

    //后台修改短信信息
    @PostMapping("/updCode")
    public Result updCode(@RequestBody RsFile rsFile){
        return integralService.updCode(rsFile);
    }



}
