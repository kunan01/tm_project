package com.tangmo.yiliao.controller;

import com.tangmo.yiliao.service.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author boge
 * @date 2017/12/22.
 * @description 基础控制器
 */

public class BaseController {

    @Resource
    protected UserService userService;

    @Resource
    protected ArticleService articleService;

    @Resource
    protected DepartmentService departmentService;

    @Resource
    protected DoctorService doctorService;

    @Resource
    protected VideoService videoService;

    @Resource
    protected  ImgFileService imgFileService;

    @Resource
    protected  PermissionService permissionService;

    @Resource
    protected IntegralService integralService;

    @Resource
    protected ArticleOrVideoService articleOrVideoService;

    @Resource
    protected DepartmentOrAuditService departmentOrAuditService;

    @Resource
    protected CommentsService commentsService;

    @Resource
    protected MessageService messageService;

    @Resource
    protected VersionService versionService;

    @Resource
    protected PayService payService;

    @Resource
    protected OrderService orderService;

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
}
