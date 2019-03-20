/**     
* @Title: SystemAuthenticationFailureHandler.java   
* @Package com.tangmo.zhjy.security.authentication   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月6日 下午5:55:33   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangmo.zhjy.system.security.properties.LoginType;
import com.tangmo.zhjy.system.security.properties.SecurityProperties;
import com.tangmo.zhjy.system.security.support.SimpleResponse;

/**
 * @Description : TODO(登录失败处理器)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月6日 下午5:55:33
 */
@Component("systemAuthenticationFailureHandler")
public class SystemAuthenticationFailureHandler extends  SimpleUrlAuthenticationFailureHandler{
	
	private Logger logger = LoggerFactory.getLogger(SystemAuthenticationFailureHandler.class);
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/* (非 Javadoc)   
	* <p>Title: onAuthenticationFailure</p>   
	* <p>Description: </p>   
	* @param request
	* @param response
	* @param exception
	* @throws IOException
	* @throws ServletException   
	* @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)   
	*/
	@SuppressWarnings("deprecation")
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		String[] sessions=request.getSession().getValueNames();
		for (String string : sessions) {
			System.out.println("================"+string+"==========================");
		}
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
			logger.info("登录失败");
			System.out.println(exception.getMessage());
			//返回500错误码
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
		}else{
			super.onAuthenticationFailure(request, response, exception);
		}
	}

}
