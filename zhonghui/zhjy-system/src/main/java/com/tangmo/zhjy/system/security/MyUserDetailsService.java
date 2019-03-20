package com.tangmo.zhjy.system.security;

import java.util.List;

import com.tangmo.zhjy.system.modules.bean.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Component;

import com.tangmo.zhjy.system.modules.bean.SysRoleBean;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.dao.SysRoleBeanMapper;
import com.tangmo.zhjy.system.modules.dao.SysUserBeanMapper;

/**
 * @Description : TODO(后台管理登录服务层)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2017年12月31日 下午3:23:38
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private SysUserBeanMapper sysUserBeanMapper;

	@Autowired 
	private SysRoleBeanMapper sysRoleBeanMapper;

	/**
	 * 登录
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("登录用户："+username);
		return buildUser(username);
	}


	private SocialUserDetails buildUser(String username) {
		SysUserBean sysUserBean=sysUserBeanMapper.selectByUserName(username);
//		SysUserBean sysUserBean=null;
//		try{
//			sysUserBean=sysUserBeanMapper.selectByUserName(username);
//		}catch(Exception e){
//			e.printStackTrace();
//		}

		//判断是否是管理员登录
		if(sysUserBean!=null){
			//查询用户所拥有的角色
			List<SysRoleBean> roles = sysRoleBeanMapper.findByRelevanceRole(sysUserBean.getUsername());
			//把角色名称变成以,隔开的字符串
			StringBuffer roleString = new StringBuffer();
			for (SysRoleBean sysRoleBean : roles) {
				roleString.append(sysRoleBean.getName()).append(",");
			}
			//3.账户是否过期 4.账户是否锁定 5.密码是否过期 6.账户是否可用
			return new SocialUser(sysUserBean.getUsername(),
					sysUserBean.getPassword(),
					true,
					sysUserBean.getFreeze()==0?true:false,
							true,
							sysUserBean.getIsShow()==0?true:false,
									AuthorityUtils.commaSeparatedStringToAuthorityList(roleString.length()==0?"":roleString.substring(0,roleString.length()-1)));
		}
		
		throw new IllegalArgumentException(
				"用户名、密码不存在");
	}

}
