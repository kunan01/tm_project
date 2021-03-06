/**     
* @Title: SystemUserNotExistException.java   
* @Package com.tangmo.zhjy.exception   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月8日 上午11:17:55   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.exception;

import com.tangmo.zhjy.system.enums.ResultCode;

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
 * @Description : TODO(结果异常，会被 ExceptionHandler 捕捉并返回给前端)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月8日 上午11:17:55
 */
public class SystemUserNotExistException extends RuntimeException {

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/   
	private static final long serialVersionUID = 1L;
	
	private ResultCode resultCode;
	
	public SystemUserNotExistException(ResultCode resultCode){
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}
	
	

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}
	
	

}
