/**     
* @Title: SysPermissionController.java   
* @Package com.tangmo.zhjy.modules.sys.controller   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月25日 上午12:52:49   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.modules.controller;

import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.service.SysPermissionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
 * @Date : 2018年1月25日 上午12:52:49
 */
@RestController
@RequestMapping("/system/permission")
@Api(value="菜单模块接口")
public class SysPermissionController extends BaseController {
	@Autowired
	private SysPermissionService sysPermissionServiceImpl;
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="查询所有菜单及操作",notes="")
	@GetMapping(value="/queryAll")
	public Result queryAll(){
		return sysPermissionServiceImpl.queryAll();
	}
}
