package com.tangmo.zhjy.app.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.AppCommunityBean;
import com.tangmo.zhjy.app.modules.bean.AppCommunityInformBean;
import com.tangmo.zhjy.app.modules.bean.AppCommunityUserBean;
import com.tangmo.zhjy.app.modules.bean.AppUserBean;
import com.tangmo.zhjy.app.modules.dao.AppCommunityBeanMapper;
import com.tangmo.zhjy.app.modules.dao.AppCommunityInformBeanMapper;
import com.tangmo.zhjy.app.modules.dao.AppCommunityUserBeanMapper;
import com.tangmo.zhjy.app.modules.dao.AppUserBeanMapper;
import com.tangmo.zhjy.app.modules.dto.AppCommunityDto;
import com.tangmo.zhjy.app.modules.service.AppCommunityService;

@Service
@Transactional
public class AppCommunityServiceImple implements AppCommunityService {

	@Autowired
	private AppCommunityBeanMapper appCommunityBeanMapper;

	@Autowired 
	private AppCommunityUserBeanMapper  appCommunityUserBeanMapper;

	@Autowired
	private AppUserBeanMapper appUserBeanMapper;

	@Autowired
	private AppCommunityInformBeanMapper appCommunityInformBeanMapper;

	Logger logger = Logger.getLogger(AppCommunityServiceImple.class);

	/* (非 Javadoc)   
	 * <p>Title: saveCommunity</p>   
	 * <p>Description: </p>   
	 * @param appCommunityDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#saveCommunity(com.tangmo.zhjy.modules.app.dto.AppCommunityDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result saveCommunity(AppCommunityDto appCommunityDto){
		try {
			AppCommunityBean  appCommunityBean= new AppCommunityBean(appCommunityDto.getLogo()
					,appCommunityDto.getName()
					, appCommunityDto.getIntro(),
					appCommunityDto.getLongitude(),
					appCommunityDto.getLatitude(),
					appCommunityDto.getAddress(),
					appCommunityDto.getPhone(),
					appCommunityDto.getProvince(),
					appCommunityDto.getCity(),
					appCommunityDto.getDistrict());
			appCommunityBeanMapper.insert(appCommunityBean);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("添加社区接口异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	 * <p>Title: modify</p>   
	 * <p>Description: </p>   
	 * @param appCommunityDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#modify(com.tangmo.zhjy.modules.app.dto.AppCommunityDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modify(AppCommunityDto appCommunityDto){
		Integer id = appCommunityDto.getId();
		try {
			if(id!=null){
				if(appCommunityBeanMapper.selectByPrimaryKey(id)!=null){


					AppCommunityBean  appCommunityBean= new AppCommunityBean(id,appCommunityDto.getLogo()
							,appCommunityDto.getName()
							, appCommunityDto.getIntro(),
							appCommunityDto.getLongitude(),
							appCommunityDto.getLatitude(),
							appCommunityDto.getAddress(),
							appCommunityDto.getPhone(),
							appCommunityDto.getProvince(),
							appCommunityDto.getCity(),
							appCommunityDto.getDistrict(),
							appCommunityDto.getIsShow());
					appCommunityBeanMapper.updateByPrimaryKeySelective(appCommunityBean);
					return new Result(ResultCode.SUCCESS);
				}
			}
		} catch (Exception e) {
			logger.info("修改社区接口异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}
	/* (非 Javadoc)   
	 * <p>Title: findByPage</p>   
	 * <p>Description: </p>   
	 * @param title
	 * @param pageNo
	 * @param pageSize
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#findByPage(java.lang.String, java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByPage(String title,Integer pageNo,Integer pageSize){
		try {
			if(title!=null && !StringUtil.isEmpty(title)){
				title="%"+title+"%";
			}else{
				title="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appCommunityBeanMapper.findBySysPage(title)));
		} catch (Exception e) {
			logger.info("分页查询社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result queryDistance(String longitude,String latitude,Integer pageNo,Integer pageSize,Integer userId,String city){
		try {
			if(longitude==null ||latitude==null){
				return new Result(ResultCode.FAIL,"请提供经度，纬度");
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appCommunityBeanMapper.queryDistance(longitude,latitude,userId,city)));
		} catch (Exception e) {
			logger.info("分页查询社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

    @Override
    public Result queryCity(Integer userId) {
        List<String> stringList = appCommunityBeanMapper.queryCity(userId);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i< stringList.size();i++){
            strings.add(stringList.get(i).split("-")[1]);
        }
        return new Result(ResultCode.SUCCESS,strings);
    }

    /* (非 Javadoc)
	 * <p>Title: queryAll</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#queryAll()   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result queryAll(Integer userId,Integer pageNo,Integer pageSize){
		try {
		    PageHelper.startPage(pageNo,pageSize);
            return new Result(ResultCode.SUCCESS,new PageInfo(appCommunityBeanMapper.queryAllNoAttention(userId)));
//			List<AppCommunityBean> appCommunityBeans=appCommunityBeanMapper.findBySysPage("%%");

//			List<AppCommunityBean> attentionCommunitys=appCommunityBeanMapper.queryAll(userId);
//
//			for (AppCommunityBean appCommunityBean : appCommunityBeans) {
//				for (AppCommunityBean appCommunityBean2 : attentionCommunitys) {
//					if(appCommunityBean2.getId()==appCommunityBean.getId() && appCommunityBean2.getIsAttention2()!=null){
//						appCommunityBean.setAttention(true);
//					}
//				}
//			}
//			return new Result(ResultCode.SUCCESS,appCommunityBeans);
		} catch (Exception e) {
			logger.info("查询所有社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	 * <p>Title: addAttention</p>   
	 * <p>Description: </p>   
	 * @param userId
	 * @param communityId
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#addAttention(java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result addAttention(Integer userId,Integer communityId){
		try {
			AppCommunityUserBean appCommunityUserBean =	appCommunityUserBeanMapper.findBycommunityIdAndUserId(userId, communityId);
			if(appCommunityUserBean==null){
				AppUserBean appUserBean=appUserBeanMapper.selectByPrimaryKey(userId);
				AppCommunityBean appCommunityBean = appCommunityBeanMapper.selectByPrimaryKey(communityId);
				if(appUserBean!=null && appCommunityBean!=null){
					appCommunityUserBean = new AppCommunityUserBean(userId,communityId);
					appCommunityUserBeanMapper.insert(appCommunityUserBean);
					return new Result(ResultCode.SUCCESS);
				}
			}else{
				return new Result(10000,"已经关注该社区",null);
			}
		} catch (Exception e) {
			logger.info("关注社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}

		return new Result(ResultCode.PAPAMETE_ERROR);
	}

	/* (非 Javadoc)   
	 * <p>Title: removeAttention</p>   
	 * <p>Description: </p>   
	 * @param userId
	 * @param communityId
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#removeAttention(java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result removeAttention(Integer userId,Integer communityId){
		try {
			AppCommunityUserBean appCommunityUserBean =	appCommunityUserBeanMapper.findBycommunityIdAndUserId(userId, communityId);
			if(appCommunityUserBean!=null){
				appCommunityUserBeanMapper.deleteByPrimaryKey(userId, communityId);
				return new Result(ResultCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.info("取消关注社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}

	/**
	 * 
	 * @Title: findById   
	 * @Description: TODO(根据编号查找)   
	 * @param @param id
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Result findById(Integer id){
		try {
			AppCommunityBean appCommunityBean =	appCommunityBeanMapper.selectByPrimaryKey(id);
			if(appCommunityBean!=null){
				return new Result(ResultCode.SUCCESS,appCommunityBean);
			}
		} catch (Exception e) {
			logger.info("根据社区编号查找接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}





	/**
	 * 查询未读的社区通知
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Result findByUnreadInform(Integer userId){
		try {
			List<AppCommunityInformBean>  unreadInforms=appCommunityInformBeanMapper.findByUnread(userId);
			List<AppCommunityBean> appCommunityBeans = null;
			if(unreadInforms.size()>0){
				appCommunityBeans = appCommunityBeanMapper.findByUnreadCommunity(unreadInforms);
				for (AppCommunityBean appCommunityBean : appCommunityBeans) {
					List<AppCommunityInformBean>  unreadInformList = new ArrayList<AppCommunityInformBean>();
					for (AppCommunityInformBean unreadInform : unreadInforms) {
						if(appCommunityBean.getId()==unreadInform.getCommunityId()){
							unreadInformList.add(unreadInform);
						}
					}
					appCommunityBean.setAppCommunityInforms(unreadInformList);
				}
			}
			return new Result(ResultCode.SUCCESS,appCommunityBeans);
		} catch (Exception e) {
			logger.info("根据社区编号查找接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}








	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Result findAttention(Integer userId){
		try {
			return new Result(ResultCode.SUCCESS,appCommunityBeanMapper.findAttention(userId));
		} catch (Exception e) {
			logger.info("根据用户编号查找加入社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

}
