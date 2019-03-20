package com.tangmo.emall;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.tangmo.emall.dao")
public class EmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmallApplication.class, args);
	}

}

