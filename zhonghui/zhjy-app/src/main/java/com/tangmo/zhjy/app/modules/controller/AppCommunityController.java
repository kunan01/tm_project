/**     
 * @Title: AppCommunityController.java   
 * @Package com.tangmo.zhjy.modules.app.controller   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月19日 上午3:03:28   
 * @version V1.0     
 */   
package com.tangmo.zhjy.app.modules.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.AppCommunityUserBean;
import com.tangmo.zhjy.app.modules.service.AppCommunityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description : TODO(社区控制层)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月19日 上午3:03:28
 */
@RestController
@RequestMapping("/app/community")
@Api("社区模块所有接口")
public class AppCommunityController {

	@Autowired
	private AppCommunityService appCommunityServiceImpl;
	
	@Value("${app.upload-logo-path}")
	private String webUploadPath;
	

	/**
	 * 3.根据编号查找
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据社区编号查找",notes="")
	@GetMapping(value="/findById/{id:\\d+}")
	public Result findById(@PathVariable Integer id){
		return appCommunityServiceImpl.findById(id);
	}

	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="查询所有社区",notes="isAttention=true关注 || isAttention=false 没有关注 ")
	@GetMapping(value="/queryAll/{userId:\\d+}/{pageNo}/{pageSize}")
	public Result queryAll(@PathVariable Integer userId,@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		return appCommunityServiceImpl.queryAll(userId,pageNo,pageSize);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="关注社区接口",notes="")
	@PostMapping(value="/addAttention")
	public Result addAttention(@RequestBody AppCommunityUserBean appCommunityUserBean){
		return appCommunityServiceImpl.addAttention(appCommunityUserBean.getUserId(), appCommunityUserBean.getCommunityId());
	}
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="取消关注接口",notes="")
	@PostMapping(value="/removeAttention")
	public Result removeAttention(@RequestBody AppCommunityUserBean appCommunityUserBean){
		return appCommunityServiceImpl.removeAttention(appCommunityUserBean.getUserId(), appCommunityUserBean.getCommunityId());
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="查询附近所有社区",notes="")
	@GetMapping(value="/queryDistance/{longitude}/{latitude}/{pageNo}/{pageSize}/{userId}/{city}")
	public Result queryDistance(@PathVariable String longitude,@PathVariable String latitude,@PathVariable Integer pageNo,@PathVariable Integer pageSize,@PathVariable Integer userId,@PathVariable String city){
		return appCommunityServiceImpl.queryDistance(longitude,latitude,pageNo,pageSize,userId,city);
	}

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="查询社区区域",notes="")
    @GetMapping(value="/queryCity/{userId}")
    public Result queryCity(@PathVariable Integer userId){
        return appCommunityServiceImpl.queryCity(userId);
    }

	@ApiOperation(value="查询关注社区未读通知",notes="")
	@GetMapping(value="/findByUnreadInform/{userId:\\d+}")
	public Result findByUnreadInform(@PathVariable Integer userId){
		return appCommunityServiceImpl.findByUnreadInform(userId);
	}


	@SuppressWarnings("rawtypes")
	@ApiOperation(value="查询已经关注的社区列表")
	@GetMapping(value="/findAttention/{userId:\\d+}")
	public Result findAttention(@PathVariable Integer userId){
		return appCommunityServiceImpl.findAttention(userId);
	}

}
