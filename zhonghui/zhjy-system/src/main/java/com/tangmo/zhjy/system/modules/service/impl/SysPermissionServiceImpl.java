/**     
 * @Title: SysPermissionServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.sys.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月25日 上午12:21:19   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.SysPermission;
import com.tangmo.zhjy.system.modules.dao.SysOperationMapper;
import com.tangmo.zhjy.system.modules.dao.SysPermissionMapper;
import com.tangmo.zhjy.system.modules.service.SysPermissionService;

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
 * @Date : 2018年1月25日 上午12:21:19
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Autowired
	private SysOperationMapper sysOperationMapper;
	
	private Logger logger = Logger.getLogger(SysPermissionServiceImpl.class);

	/* (非 Javadoc)   
	* <p>Title: queryAll</p>   
	* <p>Description: </p>   
	* @return   
	* @see com.tangmo.zhjy.modules.sys.service.impl.SysPermissionService#queryAll()   
	*/  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result queryAll(){
		try {
			//1.获取一级菜单
			List<SysPermission> ones = sysPermissionMapper.queryAll();
			//2.获取二级菜单
			List<SysPermission> tos = sysPermissionMapper.queryAll(ones);
			//3.查找二级菜单有哪些操作
			for (SysPermission sysPermission : tos) {
				sysPermission.setSysOperations(sysOperationMapper.findByPermissionId(sysPermission.getId()));
			}
			//4.查找一级菜单有哪些操作
			for (SysPermission sysPermission : ones) {
				List<SysPermission> permissions = new ArrayList<SysPermission>();
				sysPermission.setSysOperations(sysOperationMapper.findByPermissionId(sysPermission.getId()));
				//5.二级菜单封装进一级菜单
				for (SysPermission sysPermission2 : tos) {
					if(sysPermission.getId()==sysPermission2.getPid()){
						permissions.add(sysPermission2);
					}
				}
				sysPermission.setPermissionTos(permissions);
			}
			
			return new Result(ResultCode.SUCCESS,ones);
		} catch (Exception e) {
			logger.info("查询所有菜单接口一场："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		
	}
}
