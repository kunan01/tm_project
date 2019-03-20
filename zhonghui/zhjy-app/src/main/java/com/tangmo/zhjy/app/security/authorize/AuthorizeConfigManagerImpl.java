package com.tangmo.zhjy.app.security.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorizeConfigManagerImpl implements AuthorizeConfigManager {
	
	@Autowired
	private List<AuthorizeConfigProvider> authorizeConfigProviders;
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {

			//让所有实现AuthorizeConfigProvider接口中的config方法授权生效
			authorizeConfigProvider.config(config);
		}
		//剩下的请求都需要认证
		config.anyRequest().authenticated();
	}

}
