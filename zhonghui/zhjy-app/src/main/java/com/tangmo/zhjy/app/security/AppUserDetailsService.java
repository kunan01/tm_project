package com.tangmo.zhjy.app.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Component;

import com.tangmo.zhjy.app.modules.bean.AppUserBean;
import com.tangmo.zhjy.app.modules.dao.AppUserBeanMapper;


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
 * @Description : TODO(APP登录服务层)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2017年12月31日 下午3:23:38
 */
@Component
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserBeanMapper appUserBeanMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * 登录
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("======denglu===");
		System.out.println("登录用户："+username);
		return buildUser(username);
	}


	private SocialUserDetails buildUser(String username) {
		AppUserBean appUserBean = appUserBeanMapper.findByPhone(username);

		//3.账户是否过期 4.账户是否锁定 5.密码是否过期 6.账户是否可用
		System.out.println("=====密码===="+appUserBean.getPassword()+"======");
		return new SocialUser(appUserBean.getPhone(),
				"$2a$10$Qz7hyx48qVBSzmN539uYluFixqahgtzerKjR7TzkrtIFF0TS1Choa",
				true,
				"0".contentEquals(appUserBean.getFreeze())?true:false,
						true,
						true,
						AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
	}
	

	public String passwordEncoder(String password){

		return passwordEncoder.encode(password);
	}

}
