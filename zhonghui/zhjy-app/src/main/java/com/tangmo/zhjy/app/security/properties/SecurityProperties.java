/**     
* @Title: SecurityProperties.java   
* @Package com.tangmo.zhjy.core.properties   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月5日 下午6:20:33   
* @version V1.0     
*/   
package com.tangmo.zhjy.app.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
 * @Description : TODO(读取配置文件类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月5日 下午6:20:33
 */
@ConfigurationProperties(prefix="tangmo.zhjy")
public class SecurityProperties {
	
	
	private OAuth2Properties  oauth2= new OAuth2Properties();
	
	private BrowserProperties browser;

	public OAuth2Properties getOauth2() {
		return oauth2;
	}

	public void setOauth2(OAuth2Properties oauth2) {
		this.oauth2 = oauth2;
	}

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browserProperties) {
		this.browser = browserProperties;
	}
	
	
	
	
}
