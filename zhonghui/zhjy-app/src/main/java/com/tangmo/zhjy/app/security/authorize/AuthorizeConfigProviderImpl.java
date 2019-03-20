/**     
 * @Title: AuthorizeConfigProviderImpl.java   
 * @Package com.tangmo.zhjy.security.authorize   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月14日 上午3:55:24   
 * @version V1.0     
 */   
package com.tangmo.zhjy.app.security.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;

/**
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月14日 上午3:55:24
 */
@Component
@Order(Integer.MIN_VALUE)
public class AuthorizeConfigProviderImpl implements AuthorizeConfigProvider {
	
	/**
	 * 安全配置封装类
	 */
	@SuppressWarnings("unused")
	@Autowired
	private SecurityProperties securityProperties;
	
	/* (非 Javadoc)   
	 * <p>Title: config</p>   
	 * <p>Description: </p>   
	 * @param config   
	 * @see com.tangmo.zhjy.security.authorize.AuthorizeConfigProvider#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)   
	 */
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(""
							).permitAll()
					.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
	/*	config.antMatchers("/swagger-ui.html",
						   "/swagger-*",
						   "/v2/*",
						   "/configuration/*"
						   ).hasAnyRole("admin");*/
	}

}
