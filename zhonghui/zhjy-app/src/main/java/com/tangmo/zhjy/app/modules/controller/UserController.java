package com.tangmo.zhjy.app.modules.controller;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.AppUserBean;
import com.tangmo.zhjy.app.modules.service.AppUserService;
import com.tangmo.zhjy.app.security.properties.SecurityProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api("用户获取信息接口")
public class UserController {

	@Autowired
	private AppUserService appUserServiceImpl;

	@Autowired
	private SecurityProperties securityProperties;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/app_me")
	public Object getCurrentUser(Authentication user,HttpServletRequest request) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException{

			String header  = request.getHeader("Authorization");
			String token =StringUtils.substringAfter(header, "bearer ");

			String path=securityProperties.getBrowser().getPathUrl();
			//解析器
			Claims claims =Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
					.parseClaimsJws(token).getBody();
			if(claims!=null){
				Result result=appUserServiceImpl.findByPhone((String)claims.get("user_name"));
				AppUserBean appUserBean=(AppUserBean)result.getData();
				appUserBean.setPassword("想知道密码，不告诉你");
				//修改图片为绝对路径
//			appUserBean.setHeadImage(path+appUserBean.getHeadImage());
				return result;
			}else{
				return new Result(ResultCode.PAPAMETE_ERROR,"获取用户信息失败");
			}
	}


}
