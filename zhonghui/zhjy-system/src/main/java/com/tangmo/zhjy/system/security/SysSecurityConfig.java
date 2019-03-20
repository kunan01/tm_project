/**     
 * @Title: BrowserSecurityConfig.java   
 * @Package com.tangmo.zhjy.security   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2017年12月31日 下午3:03:46   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangmo.zhjy.system.security.authentication.SystemAuthenticationFailureHandler;
import com.tangmo.zhjy.system.security.authentication.SystemAuthenticationSuccessHandler;
import com.tangmo.zhjy.system.security.authorize.AuthorizeConfigManagerImpl;
import com.tangmo.zhjy.system.security.properties.SecurityProperties;

/**
 * @Description : TODO(web应用适配器类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2017年12月31日 下午3:03:46
 */
@Configuration
public class SysSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 自定义登录成功处理器
	 */
	@Autowired
	private SystemAuthenticationSuccessHandler systemAuthenticationSuccessHandler;

	@Autowired
	private SystemAuthenticationFailureHandler systemAuthenticationFailureHandler;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthorizeConfigManagerImpl authorizeConfigManagerImpl;

	@SuppressWarnings("unused")
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		//创建存储token表
		//		tokenRepositoryImpl.setCreateTableOnStartup(true);
		return tokenRepositoryImpl;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println(logoutSuccessHandler);
		http
				/*
				* 表单登录适用于浏览器向restful api发起请求;如果是后台程序直接发起请求访问restful api,则设为HTTP BASIC模式
				*/
		.formLogin()
		.loginPage("/authentication/require")
		.loginProcessingUrl("/authentication/form")//登录时的请求
		.successHandler(systemAuthenticationSuccessHandler) //登录成功处理类配置
		.failureHandler(systemAuthenticationFailureHandler)//登录失败处理类配置
		.and()
				/*
             * 调用rememberMe()方法启用记住我功能,通过在cookie中存储一个token完成
             */
		.rememberMe()
				//指定保存token的仓库，此处实现为保存到指定的数据库中
		.tokenRepository(persistentTokenRepository())
				//tokenValiditySeconds()指定token的有效时间
		.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				//指定登录用户、密码和权限
		.userDetailsService(userDetailsService)
		.and()
		.sessionManagement()
		.invalidSessionUrl("/session/invalid")  //session失效处理控制器
		.and()
		.headers().frameOptions().disable()			//springSecurty使用X-Frame-Options防止网页被Frame,将它取消掉
		.and()
		//退出配置
		.logout()//.logoutSuccessHandler(logoutSuccessHandler)
		.logoutSuccessUrl("/sysLogin.html")
		.deleteCookies("JSESSIONID")
		.and()
				/*
             * 禁用CSRF(跨站请求伪造)防护功能
             */
		.csrf().disable();
		authorizeConfigManagerImpl.config(http.authorizeRequests());
	}


}
