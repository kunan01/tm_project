package com.tangmo.yiliao.utility.Job;

import com.tangmo.yiliao.service.ArticleService;
import com.tangmo.yiliao.utility.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
public class DayDataUpdateUtils {

//    @Autowired
//    private ArticleService articleService;

    @Scheduled(cron = "0 0 8 * * ?") // 每天早晨8点触发
    public void execute() {
//        if(DateUtil.DateTimeToString(new Date()).substring(9,11).indexOf("1") != -1){
//            //每个月刷新请求验证码
//            articleService.updaucodeByMonth();
//        }else{
//            //每天刷新请求验证码
//            articleService.updaucodeByDay();
//        }


    }



}