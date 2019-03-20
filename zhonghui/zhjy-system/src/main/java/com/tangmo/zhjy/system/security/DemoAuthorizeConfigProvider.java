/**     
* @Title: DemoAuthorizeConfigProvider.java   
* @Package com.tangmo.zhjy.security   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月14日 上午4:47:20   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.tangmo.zhjy.system.security.authorize.AuthorizeConfigProvider;


/**
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月14日 上午4:47:20
 */
@Component
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider{

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.anyRequest().access("@rbacService.hasPermission(request,authentication)");
	}

}
