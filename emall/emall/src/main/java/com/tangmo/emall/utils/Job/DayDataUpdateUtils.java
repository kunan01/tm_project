package com.tangmo.emall.utils.Job;

import com.tangmo.emall.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class DayDataUpdateUtils {

    @Autowired
    private FileService fileService;

    @Scheduled(cron = "0 0/45 * * * ?") //每45分钟触发一次  清理过期图片
    public void execute() {
        fileService.updateFile();
    }

    @Scheduled(cron = "0/30 * * * * ?") //每30秒触发一次  清理过期订单
    public void execute1() {
        fileService.updateExpiredOrders();
    }

}