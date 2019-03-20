package com.tangmo.emall.controller.base;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Chamber
 * @date 2019/1/9.
 * @Description
 */
public class BaseController {

    /**
     * 得到request对象
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 得到session对象
     */
    protected HttpSession getSession() {
        return this.getRequest().getSession();
    }

    /**
     * 得到requestContext
     */
    protected RequestContext getRequestContext() {
        RequestContext requestContext = new RequestContext(this.getRequest());
        return requestContext;
    }

    protected Integer getUserId() {
        //// TODO: 2019/1/9 待补充,从session中获取
        return 1;
    }
}
