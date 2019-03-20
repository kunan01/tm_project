package com.tangmo.zhjy.system;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.SysRoleBean;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.service.SysUserService;
import com.tangmo.zhjy.system.security.properties.SecurityProperties;
import com.tangmo.zhjy.system.security.support.SimpleResponse;

import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@PropertySource(value = { "classpath:prop/path.properties"})
public class SystemApplication {
	
	private RequestCache requestCache=new HttpSessionRequestCache();

	//日志对象
	private Logger logger = Logger.getLogger(SystemApplication.class);
	//重定向对象
	private RedirectStrategy redirectStrategy =  new DefaultRedirectStrategy();

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private SysUserService sysUserServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="获取用户信息",notes="")
	@GetMapping("/me")
	public Result getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		Result result =null;
		if(user!=null){
			Object sysUser=sysUserServiceImpl.findSelectUserName(user.getUsername());
			if(user!=null){
				SysUserBean sysUserBean=sysUserServiceImpl.selectByUserName(user.getUsername());
				sysUserBean.setSysRoleBeans(((List<SysRoleBean>)((Result)sysUser).getData()));
				result = new Result(ResultCode.SUCCESS,sysUserBean);
				return result;
			}
		}
		return new Result(ResultCode.PAPAMETE_ERROR,"获取用户信息失败");
	}

	/**
	 * @throws IOException
	 *
	* @Title: requireAuthentication
	* @Description: TODO(当后台管理系统需要身份认证的时候跳转这里)
	* @param @param request
	* @param @param response
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */

	@ApiOperation(value="后台登录表单提交接口",notes="触发登录请求如果不是.html后缀,会返回提示信息：访问的服务需要身份认证，请引导用户到登录页。登录成功后会自动跳转到触发登录接口的URL")
	@RequestMapping(value="/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED) //返回401
	public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//获取触发授权的URL
		SavedRequest saveRequest=requestCache.getRequest(request, response);

		if(saveRequest != null){
			String target = saveRequest.getRedirectUrl();
			logger.info("引发跳转的URL："+target);
			if(StringUtils.endsWithIgnoreCase(target,".html")){
				redirectStrategy.sendRedirect(request, response,securityProperties.getBrowser().getLoginPage());
			}
		}
		return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
	}

	/**
	 * 
	* @Title: sessionInvalid   
	* @Description: TODO(session失效处理地址)   
	* @param @return    设定文件   
	* @return SimpleResponse    返回类型   
	* @throws
	 */

	@GetMapping(value="/session/invalid")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse sessionInvalid(){

		//String message = "信息已失效，请重新登录！";
		//return new SimpleResponse(null);
		return null;
	}


	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setSessionTimeout(86400);// 单位为S
			}
		};
	}


}
