/**     
* @Title: SecurityProperties.java   
* @Package com.tangmo.zhjy.core.properties   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月5日 下午6:20:33   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description : TODO(读取配置文件类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月5日 下午6:20:33
 */
@ConfigurationProperties(prefix="tangmo.zhjy")
public class SecurityProperties {
	
	private BrowserProperties browser =  new BrowserProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
	
	
	
	
}
