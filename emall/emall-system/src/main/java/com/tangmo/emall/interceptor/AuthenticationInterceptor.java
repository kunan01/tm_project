package com.tangmo.emall.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.tangmo.emall.annotation.UserLoginToken;
import com.tangmo.emall.service.SysUserService;
import com.tangmo.emall.utils.JWTUtil;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    SysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token

        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    //无token，请重新登录！
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.append(JSON.toJSONString(ResultUtil.tokenError()));
                    out.close();
                    return false;
                }

                // 获取token中的sysUserId
                Integer sysUserId;
                try {
                    sysUserId = JWTUtil.verifyToken(token);
                } catch (JWTDecodeException j) {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.append(JSON.toJSONString(ResultUtil.tokenError()));
                    out.close();
                    return false;
                }

                //获取Service实例
                if (sysUserService == null) {
                    sysUserService = (SysUserService) factory.getBean("sysUserService");
                }

                //验证用户状态
                Result result = sysUserService.getSysUserById(sysUserId);

                if (!result.getCode().equals("0")) {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.append(JSON.toJSONString(ResultUtil.tokenError()));
                    out.close();
                    return false;
                }

                return true;
            }
        }
        return true;
    }

}
