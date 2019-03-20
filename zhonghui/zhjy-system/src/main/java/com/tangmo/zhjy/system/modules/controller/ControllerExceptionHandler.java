/**     
* @Title: ControllerExceptionHandler.java   
* @Package com.tangmo.zhjy.modules.sys.controller   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月10日 下午1:25:25   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.modules.controller;

import java.util.HashMap;
import java.util.Map;

import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tangmo.zhjy.system.exception.SystemUserNotExistException;

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
 * @Date : 2018年1月10日 下午1:25:25
 */
@ControllerAdvice
public class ControllerExceptionHandler extends BaseController {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(SystemUserNotExistException.class)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String,Object> handleUserNotExistException(SystemUserNotExistException error){
		Map<String,Object> result = new HashMap();
		result.put("code", error.getResultCode().getCode());
		result.put("message",error.getResultCode().getMsg());
		return result;
	}

}
