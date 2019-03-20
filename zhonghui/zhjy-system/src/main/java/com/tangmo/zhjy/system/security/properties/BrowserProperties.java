/**     
* @Title: BrowserProperties.java   
* @Package com.tangmo.zhjy.core.properties   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月5日 下午6:17:41   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.security.properties;

/**
 *
 * @Description : TODO(浏览器安全配置类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月5日 下午6:17:41
 */
public class BrowserProperties {
	
	//登录页配置
	private String loginPage = "/login";
	//登录返回类型
	private LoginType loginType = LoginType.JSON;
	//记住我秒数配置
	private int rememberMeSeconds=360000;
	//private int rememberMeSeconds=1;
	//访问图片路径
	private String pathUrl;
	

	public String getPathUrl() {
		return pathUrl;
	}

	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	
	

}
