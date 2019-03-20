/**     
* @Title: SystemAuthenticationSuccessHandler.java   
* @Package com.tangmo.zhjy.security.authentication   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月5日 下午4:44:14   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.security.authentication;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysRoleBean;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.dao.LogMapper;
import com.tangmo.zhjy.system.modules.dao.SysRoleBeanMapper;
import com.tangmo.zhjy.system.modules.dao.SysUserBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangmo.zhjy.system.security.properties.LoginType;
import com.tangmo.zhjy.system.security.properties.SecurityProperties;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 *
 * @Description : TODO(后台登录成功后)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月5日 下午4:44:14
 */
@Component("systemAuthenticationSuccessHandler")
public class SystemAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private SysUserBeanMapper sysUserBeanMapper;

	@Autowired
	private SysRoleBeanMapper sysRoleBeanMapper;

	@Autowired
	private LogMapper logMapper;


	
	/* (非 Javadoc)   
	* <p>Title: onAuthenticationSuccess</p>   
	* <p>Description: </p>   
	* @param request
	* @param response
	* @param authentication
	* @throws IOException
	* @throws ServletException   
	* @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)   
	*/
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println(objectMapper.writeValueAsString(authentication));
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
			response.setContentType("application/json;charset=UTF-8");
			System.out.println(authentication.getName()+"----------------------");
			SysUserBean sysUserBean=sysUserBeanMapper.selectByUserName(authentication.getName());
				//判断是否是管理员登录
				if(sysUserBean!=null) {
					//查询用户所拥有的角色
					List<SysRoleBean> roles = sysRoleBeanMapper.findByRelevanceRole(sysUserBean.getUsername());
					//把角色名称变成以,隔开的字符串
					StringBuffer roleString = new StringBuffer();
					for (SysRoleBean sysRoleBean : roles) {
						roleString.append(sysRoleBean.getName()).append(",");
					}
					sysUserBean.setRoleName(new String(roleString));
					request.getSession().setAttribute("User", sysUserBean);
					//生成日志
					Log logTable=new Log();
					logTable.setUserId(sysUserBean.getId());
					logTable.setRoleName(sysUserBean.getRoleName());
					logTable.setParam("用户登录信息");
					logMapper.insertSelective(logTable);
				}
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		}else{
			//跳转
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
