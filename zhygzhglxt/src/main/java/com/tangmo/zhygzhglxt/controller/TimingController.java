package com.tangmo.zhygzhglxt.controller;

import com.tangmo.zhygzhglxt.controller.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by chengge on 2018/11/26.
 */
@Api("定时执行请求对外数据")
@RestController
@RequestMapping("/time")
public class TimingController extends BaseController {


//    @ApiOperation(value="获取公交信息",notes="")
//    @Scheduled(initialDelay = 500,fixedDelay = 10000)
//    @RequestMapping("/taskTimer")
//    public void getData() throws IOException {
//        System.out.println("我开始定时执行任务了----------"+new Date().getTime());
//    }

    @Scheduled(initialDelay = 500, fixedDelay = 100000)
    @RequestMapping("/taskTimer")
    public void getData() throws Exception {

        tbListenOrderService.listenAllOrder();//定时执行异常订单
        tbBusService.busLogin();//接入公交车定位的登录
        tbBusService.gpsLogin();//接入同乡客运的的登录
        System.out.println("我开始定时执行任务了----------" + new Date().getTime());

        //ForeignUtil.getInformation1();
//        System.out.println("============get请求结束====================");

//        ForeignUtil.postInformation1();
//        System.out.println("=============post请求结束===================");
    }

}
