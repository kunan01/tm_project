package com.tangmo.zhjy.system.swaggerUi;

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
 * @Author : xiaoming
 * @Date : 2017年12月29日 下午1:13:59
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.tangmo.zhjy"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("众惠家园API")
				.description("email：tangmo@163.com \r tel:029-65612559")
				.termsOfServiceUrl("http://114.115.211.170:8001")
				.contact("tangmo")
				.version("1.0")
				.build();
	}


}
