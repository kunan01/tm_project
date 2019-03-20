/**     
 * @Title: SecurityCoreConfig.java   
 * @Package com.tangmo.zhjy.core   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月5日 下午6:19:08   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tangmo.zhjy.system.security.properties.SecurityProperties;

/**
 * @Description : TODO()
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月5日 下午6:19:08
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class) //让securityProperties生效
public class SecurityCoreConfig {


	/**
	 * 
	 * @Title: passwordEncoder   
	 * @Description: TODO(获取密码加密对象)   
	 * @param @return    设定文件   
	 * @return PasswordEncoder    返回类型   
	 * @throws
	 */
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}





}
