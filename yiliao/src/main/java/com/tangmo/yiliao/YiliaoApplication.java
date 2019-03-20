package com.tangmo.yiliao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.tangmo.yiliao.*")
@ComponentScan(basePackages = "com.tangmo.yiliao.*")
@MapperScan("com.tangmo.yiliao.dao")
@PropertySource(value = { "classpath:prop/path.properties"})
public class YiliaoApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(YiliaoApplication.class, args);
    }

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(YiliaoApplication.class);
	}

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("100MB");
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }

}
