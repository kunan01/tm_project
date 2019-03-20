/**     
* @Title: ValidationUtil.java   
* @Package com.tangmo.zhjy.modules.sys.dto   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月10日 下午5:59:58   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.utils;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.tangmo.zhjy.system.enums.ResultCode;
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
 * @Description : TODO(Validation校验处理工具类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月10日 下午5:59:58
 */
public class ValidationUtil {
	
	/**
	 * 
	* @Title: verifyDispose   
	* @Description: TODO(校验处理方法)   
	* @param @param error    设定文件   
	* @return void    返回类型   
	* @throws
	 */
	public static void verifyDispose(BindingResult error){
		List<FieldError> errors=error.getFieldErrors();
		if(errors.size()>0){
			throw new SystemUserNotExistException(ResultCode.PAPAMETE_ERROR);
		}
	}

}
