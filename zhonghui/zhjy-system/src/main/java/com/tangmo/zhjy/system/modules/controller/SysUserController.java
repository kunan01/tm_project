/**     
 * @Title: SysUserController.java   
 * @Package com.tangmo.zhjy.modules.sys.controller   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月10日 上午11:35:56   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.controller;

import javax.validation.Valid;

import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.SysRoleBean;
import com.tangmo.zhjy.system.modules.controller.baseController.BaseController;
import com.tangmo.zhjy.system.modules.dto.SysSlideBeanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.exception.SystemUserNotExistException;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.dto.SysCommunitySysuserDto;
import com.tangmo.zhjy.system.modules.dto.SysUserDto;
import com.tangmo.zhjy.system.modules.service.SysUserService;
import com.tangmo.zhjy.system.utils.ValidationUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月10日 上午11:35:56
 */
@RestController
@RequestMapping("/system/user")
@Api(value="管理员模块")
public class SysUserController extends BaseController {

	@Autowired
	SysUserService sysUserServiceImpl;
	@Autowired
	PasswordEncoder passwordEncoder;

	@SuppressWarnings("rawtypes")
	@ApiOperation(notes="", value = "新增管理员")
	@ApiImplicitParam(name="sysUserDto",dataType="SysUserDto",required=true)
	//@ApiImplicitParam(name="sysUserDto",value="管理员DTO对象",dataType="SysUserDto",required=true)
	@PostMapping(value="/saveSysUser.do")
	public Result saveSysUser(@Valid @RequestBody SysUserDto sysUserDto, BindingResult error){
		ValidationUtil.verifyDispose(error);
		System.out.println("==============");
		System.out.println(sysUserDto);
		System.out.println("==============");
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("新增管理员");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		//根据账号查找
		SysUserBean selectSysUserBean = sysUserServiceImpl.selectByUserName(sysUserDto.getUsername());
		//根据工号查找是否重复工号
		SysUserBean jobUser = sysUserServiceImpl.selectByJobNumber(sysUserDto.getJobNumber());
		//判断是否有重复账号存在
		if(selectSysUserBean==null && jobUser==null){
			try {
				//创建添加管理员实体类
				SysUserBean sysUserBean = new SysUserBean(sysUserDto.getUsername(),
						passwordEncoder.encode(sysUserDto.getPassword()),
						sysUserDto.getFreeze());
				sysUserBean.setRoles(sysUserDto.getRoles());
				sysUserBean.setName(sysUserDto.getName());
				sysUserBean.setJobNumber(sysUserDto.getJobNumber());
				return sysUserServiceImpl.saveSysUser(sysUserBean);
			} catch (Exception e) {
				e.printStackTrace();    
				throw new SystemUserNotExistException(ResultCode.PAPAMETE_ERROR);
			}
		}else{
			throw new SystemUserNotExistException(ResultCode.SYSTEM_ADMIN_USER_ERROR);
		}

	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="修改管理员信息",notes="根据ID修改管理员密码、是否冻结")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="管理员ID",dataType="Long",paramType="path",required=true),
		@ApiImplicitParam(name="sysUserDto",value="管理员DTO",dataType="SysUserDto",required=true)

	})
	@PutMapping(value="/modifySysUser.do/{id:\\d+}")
	public Result modifySysUser(@PathVariable Long id,@Valid @RequestBody SysUserDto sysUserDto,BindingResult error){
		ValidationUtil.verifyDispose(error);
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("修改管理员信息");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		SysUserBean sysUserBean=(SysUserBean) sysUserServiceImpl.findById(id.intValue()).getData();
		if(sysUserBean==null){
			throw new SystemUserNotExistException(ResultCode.PAPAMETE_ERROR);
		}
		if(sysUserDto.getPassword()!=null && !"".equals(sysUserDto.getPassword())){
			sysUserBean.setPassword(passwordEncoder(sysUserDto.getPassword()));
		}
		sysUserBean.setFreeze(sysUserDto.getFreeze());
		sysUserBean.setRoles(sysUserDto.getRoles());
		sysUserBean.setIsShow(sysUserDto.getIsShow());
		sysUserBean.setJobNumber(sysUserDto.getJobNumber());
		sysUserBean.setName(sysUserDto.getName());
		return sysUserServiceImpl.updateSysUser(sysUserBean);

	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="删除管理员",notes="根据管理员ID概念性删除管理员")
	@ApiImplicitParam(name="id",value="管理员ID",required=true,dataType="Long",paramType="path")
	@DeleteMapping(value="/deleteSysUser.do/{id:\\d+}")
	public Result deleteSysUser(@PathVariable Long id){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("删除管理员");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		SysUserBean sysUserBean = (SysUserBean) sysUserServiceImpl.findById(id.intValue()).getData();
		if(sysUserBean==null){
			throw new SystemUserNotExistException(ResultCode.PAPAMETE_ERROR);
		}
		if(sysUserBean.getIsShow()!=1){
			return  sysUserServiceImpl.deleteSysUser(sysUserBean);
		}
		return new Result(ResultCode.SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="查看管理员详情",notes="显示该管理员所有角色和权限信息")
	@GetMapping(value="/findSelectUserName/{username}")
	public Result findSelectUserId(@ApiParam(name="username",required=true,value="根据管理员账号查询")@PathVariable String username){
		return sysUserServiceImpl.findSelectUserName(username);
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页查询App用户",notes="模糊查询分页接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name="name",value="模糊查询参数",dataType="String",required=false,paramType="query"),
			@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
			@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query")
	})
	@GetMapping(value="/selectByAppPage")
	public Result selectByPage(String name, Integer pageNo,Integer pageSize){
		return sysUserServiceImpl.selectByPage(name, pageNo, pageSize);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据App用户id查询用户详情",notes="根据App用户id查询用户详情")
	@GetMapping(value="/selectByAppUserId/{userId}")
	public Result selectByAppUserId(@PathVariable("userId") Integer userId){
		return sysUserServiceImpl.selectByAppUserId(userId);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页查询用户",notes="模糊查询分页接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name="name",value="模糊查询参数",dataType="String",required=false,paramType="query"),
			@ApiImplicitParam(name="pageNo",value="当前页",dataType="int",required=false,paramType="query"),
			@ApiImplicitParam(name="pageSize",value="显示多少条参数",dataType="int",required=false,paramType="query")
	})
	@GetMapping(value="/findByUserPage")
	public Result findByPage(String name,
							 Integer pageNo,
							 Integer pageSize){
		return sysUserServiceImpl.findByPage(name, pageNo, pageSize);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据管理员ID查询",notes="")
	@GetMapping(value="/queryCommunity/{sysUserId:\\d+}")
	public Result queryCommunity(@PathVariable Integer sysUserId){
		return sysUserServiceImpl.queryCommunity(sysUserId);
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分配社区",notes="给管理员分配社区")
	@PostMapping(value="/allotCommunity")
	public Result  allotCommunity(@Valid @RequestBody SysCommunitySysuserDto sysCommunitySysuserDto,BindingResult error){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("分配社区");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return sysUserServiceImpl.allotCommunity(sysCommunitySysuserDto);
	}


	/**
	 * 
	 * @Title: passwordEncoder   
	 * @Description: TODO(管理员密码加密)   
	 * @param @param password
	 * @param @return    设定文件   
	 * @return String    返回类型   
	 * @throws
	 */
	public String passwordEncoder(String password){
		return passwordEncoder.encode(password);
	}

	@ApiOperation(value="获取用户信息",notes="")
	@GetMapping("/me")
	public Result getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		Result result =null;
		if(user!=null){
			Object sysUser=sysUserServiceImpl.findSelectUserName(user.getUsername());
			if(user!=null){
				SysUserBean sysUserBean=sysUserServiceImpl.selectByUserName(user.getUsername());
				sysUserBean.setSysRoleBeans(((List<SysRoleBean>)((Result)sysUser).getData()));
				result = new Result(ResultCode.SUCCESS,sysUserBean);
				return result;
			}
		}
		return new Result(ResultCode.PAPAMETE_ERROR,"获取用户信息失败");
	}

	/**
	 * 认证用户
	 * @return
     */
	@PutMapping("/verify/{userId}")
	public Result verifyUser(@PathVariable Integer userId){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("认证用户");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return sysUserServiceImpl.changeVerifyState(userId,"2");
	}

	/**
	 * 驳回用户实名认证请求
	 * @param userId
	 * @return
     */
	@PutMapping("/unverify/{userId}")
	public Result unVerifyUser(@PathVariable Integer userId){
		//生成日志
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0){
			ip = getRequest().getRemoteAddr();
		}
		SysUserBean userBean=(SysUserBean)getSession().getAttribute("User");
		Log logTable=new Log();
		logTable.setUserId(userBean.getId());
		logTable.setRoleName(userBean.getRoleName());
		logTable.setParam("驳回用户实名认证请求");
		logTable.setMethodUrl(ip);
		logService.addLog(logTable);
		return sysUserServiceImpl.changeVerifyState(userId,"3");
	}

	/**
	 * 查询已实名认证用户
	 * @return
     */
	@GetMapping("/info/verify/{pageNo}/{pageSize}")
	public Result getVerifyUser(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		return sysUserServiceImpl.queryVerifyInfo("2",pageNo,pageSize);
	}

	/**
	 * 查询未实名认证用户
	 * @return
     */
	@GetMapping("/info/unverify/{pageNo}/{pageSize}")
	public Result getUnVerifyUser(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		return sysUserServiceImpl.queryVerifyInfo("1",pageNo,pageSize);
	}


	/**
	 * 根据实名用户id查询已实名信息
	 * @return
	 */
	@GetMapping("/info/getVerifyByUserId/{userId}")
	public Result getVerifyByUserId(@PathVariable("userId") Integer userId){
		return sysUserServiceImpl.getVerifyByUserId(userId);
	}

    /**
     * 查询所有未审核数据
     * @return
     */
    @GetMapping("/info/getNotAuditAll")
    public Result getNotAuditAll(){
        return sysUserServiceImpl.getNotAuditAll();
    }

    /**
     * 统计数量
     * @return
     */
    @GetMapping("/info/statistical")
    public Result statistical(){
        return sysUserServiceImpl.statisticalUser();
    }


}
