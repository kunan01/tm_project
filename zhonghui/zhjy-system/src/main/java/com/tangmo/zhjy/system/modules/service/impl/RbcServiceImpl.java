/**     
* @Title: RbcServiceImpl.java   
* @Package com.tangmo.zhjy.modules.sys.service.impl   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月14日 下午2:59:19   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.modules.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.tangmo.zhjy.system.modules.bean.SysOperation;
import com.tangmo.zhjy.system.modules.bean.SysPermission;
import com.tangmo.zhjy.system.modules.dao.SysOperationMapper;
import com.tangmo.zhjy.system.modules.dao.SysPermissionMapper;
import com.tangmo.zhjy.system.modules.service.RbacService;

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
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月14日 下午2:59:19
 */
@Component("rbacService")
public class RbcServiceImpl implements RbacService {
	
	@Autowired
	SysPermissionMapper sysPermissionMapper;
	
	@Autowired
	SysOperationMapper sysOperationMapper;
	
	AntPathMatcher pntPathMatcher = new AntPathMatcher();
	
	public boolean hasPermission(HttpServletRequest request,Authentication authentication){
		Object principal =authentication.getPrincipal();
		boolean hasPermission = true;
		if(principal instanceof UserDetails){
			String username = ((UserDetails)principal).getUsername();
			//查询该用户的所有菜单路径
			List<SysPermission> permissionPaths = sysPermissionMapper.queryMenuPath(username);
			//查询操作菜单路径
			List<SysOperation> operations=sysOperationMapper.findByPath(username);
			Set<String> urls = new HashSet<String>();
			for (SysPermission sysPermission : permissionPaths) {
				urls.add(sysPermission.getUrl());
			}
			for (SysOperation sysOperation : operations) {
				urls.add(sysOperation.getUrl());
			}
			System.out.println(request.getRequestURI());
			for (String string : urls) {
				if(pntPathMatcher.match(string, request.getRequestURI())){
					hasPermission=true;
					return hasPermission;
				}
			}
			
		}
		
		return hasPermission;
	}

}
