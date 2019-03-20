package com.tangmo.zhjy.system.aop;



import com.alibaba.fastjson.JSON;
import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.dao.AppClassifyMapper;
import com.tangmo.zhjy.system.modules.dao.LogMapper;
import com.tangmo.zhjy.system.modules.dao.SysUserBeanMapper;
import com.tangmo.zhjy.system.utils.Transfor;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户进行拦截 ELMS Controller的每一个请求方法, 添加日志
 *
 * @author chengge
 * @date 2017/10/17
 */
@Log4j
@Component
@Aspect
public class ControllerAop {

    private final static String METHOD_GET = "GET";
    private final static String METHOD_DELETE = "DELETE";

    //匹配com.tangmo.zhjy.system.modules.controller包及其子包下的所有类的所有方法
    @Pointcut("execution(* com.tangmo.zhjy.system.modules.controller..*.*(..)))")
    public void executeService() {
    }

    /**
     * 前置通知，方法调用前被调用
     *
     * @param joinPoint
     */
    @Before("executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        Log logTable=new Log();
        //获取HttpServletRequest的信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取Session信息
        HttpSession session = request.getSession();
        //获取请求用户id和用户类型
        Object userId = request.getHeader("userId");
        //获取请求方式
        String method = request.getMethod();
        //获取请求的uri
        String uri = request.getRequestURI();
        //如果为get或者delete请求, 则获取parameterNames
        boolean enumerationIsNull = true;
        Map<String, String> parameterMap = new HashMap<>();
        if ((METHOD_DELETE.equals(method) || METHOD_GET.equals(method))) {
            Enumeration<String> enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String parameter = enumeration.nextElement();
                parameterMap.put(parameter, request.getParameter(parameter));
                enumerationIsNull = false;
            }
        }
        String parameterStrs = JSON.toJSONString(parameterMap);
        Object[] args = joinPoint.getArgs();
        StringBuilder logInfoSb = new StringBuilder("Method: ").append(method).append(", URI: ").append(uri);
        if (userId != null) {
            logInfoSb.append(", 用户ID: ").append(userId);
        }
        if (!enumerationIsNull) {
            logInfoSb.append(", 请求参数: ").append(parameterStrs);
            //logTable.setParam(parameterStrs);
        } else if (args.length > 0) {
            logInfoSb.append(", 请求参数: ");
            for (int i = 0; i < args.length; i++) {
                if (i != 0) {
                    logInfoSb.append(" | ");
                }
                logInfoSb.append(args[i]);

            }
            logInfoSb.append(";");
        }
        log.info(logInfoSb.toString());
    }
}
