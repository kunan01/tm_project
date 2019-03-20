package com.tangmo.zhygzhglxt.interceptor;

import com.tangmo.zhygzhglxt.service.TbSysUserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chengge
 * @date 18/1/15
 * @description 检验请求合法性.
 */

public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TbSysUserService tbSysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());

        tbSysUserService = (TbSysUserService) factory.getBean("tbSysUserService");

        String ip = request.getRemoteAddr();
        //此处判断正常用户请求header逻辑
        String token = request.getHeader("token");
        String userCode = request.getHeader("userCode");
        if (token == null || userCode == null) {
            return false;
        }
        //获取Service实例
        if (tbSysUserService == null) {
            tbSysUserService = (TbSysUserService) factory.getBean("tbSysUserService");
        }
//        //增加日活量
//        if (ip != null) {
//            String result = adminService.searchAccessIp(ip);
//            if(result != null && !result.equals("")){
//                adminService.addAccessCount(ip);
//            }
//        }
        String checkToken = tbSysUserService.selectToken(userCode);
        //检查头部请求
        if (!checkToken.equals(token)) {
            return false;
        }
        return true;
    }
}
