package com.tangmo.zhygzhglxt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description : TODO(Swagger-UI配置文件)
 * ---------------------------------
 * @Author : chengge
 * @Date : 2018年07月10日 下午2:52:51
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tangmo.zhygzhglxt"))//需要修改
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智慧运管综合管理系统API")
                .description("email：18329785735@163.com \r tel:029-65612559")
                .termsOfServiceUrl("http://127.0.0.1:8090")
                .contact("chengge")
                .version("1.0")
                .build();
    }


}
