package com.tangmo.zhygzhglxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.tangmo.zhygzhglxt.dao")
public class ZhygzhglxtAppApplication extends SpringBootServletInitializer {

    //部署服务器打成war包需要解注释
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        // 注意这里要指向原先用main方法执行的Application启动类
//        return builder.sources(ZhygzhglxtAppApplication.class);
//    }


    //部署服务器打成war包需要注释
    public static void main(String[] args) {
        SpringApplication.run(ZhygzhglxtAppApplication.class, args);
    }

}
