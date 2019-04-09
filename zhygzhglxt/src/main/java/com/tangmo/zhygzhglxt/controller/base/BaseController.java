package com.tangmo.zhygzhglxt.controller.base;

import com.tangmo.zhygzhglxt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author chengge
 * @date 2017/12/22.
 * @description 基础控制器
 */

public class BaseController {

    @Autowired
    protected TbFeedBackService tbFeedBackService;//反馈

    @Autowired
    protected TbSysUserService tbSysUserService;//用户

    @Autowired
    protected TbDriverVerifyService tbDriverVerifyService;//司机审核

    @Autowired
    protected TbParmService tbParmService;//参数

    @Autowired
    protected TbSysModuleService tbSysModuleService;//后台模块

    @Autowired
    protected TbSysPermissionsService tbSysPermissionsService;//权限

    @Autowired
    protected TbSysRoleService tbSysRoleService;//角色

    @Autowired
    protected TbDriverOrderService tbDriverOrderService;//车主订单

    @Autowired
    protected TbPassengerOrderService tbPassengerOrderService;//乘客订单

    @Autowired
    protected TbComplainService tbComplainService;//投诉

    @Autowired
    protected TbInformationService tbInformationService;//文章

    @Autowired
    protected TbBusService tbBusService;//公交车

    @Autowired
    protected AliPayService aliPayService;//支付宝

    @Autowired
    protected PayService payService; //微信

    @Autowired
    protected AliwithdrawalService aliwithdrawalService; //支付宝提现

    @Autowired
    protected VersionService versionService; //版本

    @Autowired
    protected TbCarService tbCarService; //通想客运车辆讯息

    @Autowired
    protected TbListenOrderService tbListenOrderService; //监听订单

    @Autowired
    protected AnalysisService analysisService;// 统计分析模块service


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
}
