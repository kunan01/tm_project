package com.tangmo.yiliao.interceptor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author boge
 * @date 18/1/15
 * @description 检验请求合法性.
 */

public class TokenInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
//        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//        //此处判断正常用户请求header逻辑
//        String token = request.getHeader("token");
//        String userId = request.getHeader("userId");
//        System.out.println(token+"========="+userId);
//        if (token == null || userId == null) {
//            return false;
//        }
//        //获取Service实例
//        if (userService == null) {
//            userService = (UserService) factory.getBean("userService");
//        }
//        String checkToken = userService.selectToken(userId);
//        //检查头部请求
//        if (!checkToken.equals(token)) {
//            return false;
//        }
        return true;
    }
}
