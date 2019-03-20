/**     
* @Title: SimpleResponse.java   
* @Package com.tangmo.zhjy.support   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月5日 下午6:08:10   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.security.support;

/**
 * @Description : TODO(响应客户端信息类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月5日 下午6:08:10
 */
public class SimpleResponse {
	
	//信息
	private Object content;
	
	public SimpleResponse(Object content) {
		this.content=content;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "SimpleResponse [content=" + content + "]";
	}
	
	
}
