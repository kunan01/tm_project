package com.tangmo.zhjy.app.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tangmo.zhjy.app.modules.dao.AppCommunityBeanMapper;
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
import com.tangmo.zhjy.app.modules.bean.AppCommunityInformAppUserBean;
import com.tangmo.zhjy.app.modules.bean.AppCommunityInformBean;
import com.tangmo.zhjy.app.modules.bean.AppCommunityUserBean;
import com.tangmo.zhjy.app.modules.dao.AppCommunityInformAppUserBeanMapper;
import com.tangmo.zhjy.app.modules.dao.AppCommunityInformBeanMapper;
import com.tangmo.zhjy.app.modules.dao.AppCommunityUserBeanMapper;
import com.tangmo.zhjy.app.modules.dto.AppCommunityInformDto;
import com.tangmo.zhjy.app.modules.service.AppCommunityInfomService;

@Service
@Transactional
public class AppCommunityInfomServiceImpl implements AppCommunityInfomService {

	@Autowired
	private AppCommunityInformBeanMapper appCommunityInformBeanMapper;

	@Autowired
	private AppCommunityInformAppUserBeanMapper appCommunityInformAppUserBeanMapper;

	@Autowired
	private AppCommunityUserBeanMapper appCommunityUserBeanMapper;

	@Autowired
	private AppCommunityBeanMapper appCommunityBeanMapper;



	Logger logger = Logger.getLogger(AppCommunityInfomServiceImpl.class);
	/* (非 Javadoc)   
	 * <p>Title: saveCommunityInform</p>   
	 * <p>Description: </p>   
	 * @param appCommunityInformDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#saveCommunityInform(com.tangmo.zhjy.modules.app.dto.AppCommunityInformDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result saveCommunityInform(AppCommunityInformDto appCommunityInformDto){
		try {
			AppCommunityInformBean appCommunityInformBean = new AppCommunityInformBean(appCommunityInformDto.getTitle(),
					appCommunityInformDto.getCover(),
					appCommunityInformDto.getIsChecked(),
					appCommunityInformDto.getCommunityId(),
					appCommunityInformDto.getContent());
			appCommunityInformBeanMapper.insert(appCommunityInformBean);
			//查询所有关注的用户
			List<AppCommunityUserBean> list=appCommunityUserBeanMapper.findById(appCommunityInformDto.getCommunityId());
			List<AppCommunityInformAppUserBean>  commInfoApps = new ArrayList<AppCommunityInformAppUserBean>();
			if(list.size()>0){
				for (AppCommunityUserBean appCommunityUserBean : list) {
					AppCommunityInformAppUserBean appCommunityInformAppUserBean = new AppCommunityInformAppUserBean();
					appCommunityInformAppUserBean.setAppCommunityInform(appCommunityInformBean.getId());
					appCommunityInformAppUserBean.setAppUserId(appCommunityUserBean.getUserId());
					commInfoApps.add(appCommunityInformAppUserBean);
				}
				appCommunityInformAppUserBeanMapper.insert(commInfoApps);
			}
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("添加社区消息异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}
	/* (非 Javadoc)   
	 * <p>Title: modifyCommunityInform</p>   
	 * <p>Description: </p>   
	 * @param appCommunityInformDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppCommunityService#modifyCommunityInform(com.tangmo.zhjy.modules.app.dto.AppCommunityInformDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modifyCommunityInform(AppCommunityInformDto appCommunityInformDto){

		try {
			if(appCommunityInformBeanMapper.selectByPrimaryKey(appCommunityInformDto.getId())!=null){

				AppCommunityInformBean appCommunityInformBean = new AppCommunityInformBean(appCommunityInformDto.getId(),
						appCommunityInformDto.getTitle(),
						appCommunityInformDto.getCover(),
						appCommunityInformDto.getIsChecked(),
						appCommunityInformDto.getIsShow(),
						appCommunityInformDto.getCommunityId(),
						appCommunityInformDto.getContent());
				appCommunityInformBeanMapper.updateByPrimaryKeySelective(appCommunityInformBean);
				return new Result(ResultCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.info("修改社区消息异常："+e);
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
	public Result findByPage(String title,Integer userId,Integer pageNo,Integer pageSize){
		try {
			if(title!=null && !StringUtil.isEmpty(title)){
				title="%"+title+"%";
			}else{
				title="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}

			List<AppCommunityInformBean> appCommunityInformBeans = appCommunityInformBeanMapper.findByPage(title,userId);

			//查询每一个通知的总人数和总阅读数
			for (AppCommunityInformBean appCommunityInformBean : appCommunityInformBeans) {
				AppCommunityInformAppUserBean communityInformAppUserBean=appCommunityInformAppUserBeanMapper.queryTotalAndRedTotal(appCommunityInformBean.getId());
				appCommunityInformBean.setTotal(communityInformAppUserBean.getTotal());
				appCommunityInformBean.setReadTotal(communityInformAppUserBean.getRedTotal());
				appCommunityInformBean.setCommunityName(appCommunityBeanMapper.selectByPrimaryKey(appCommunityInformBean.getCommunityId()).getName());
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appCommunityInformBeans));
		} catch (Exception e) {
			logger.info("分页查询社区通知接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

    @Override
    public Result findCommunityInfomByUserId(Integer type,Integer userId, Integer pageNo, Integer pageSize) {
        try {
            if(pageSize!=null && pageNo!=null){
                PageHelper.startPage(pageNo, pageSize);
            }

            List<AppCommunityInformBean> appCommunityInformBeans = appCommunityInformBeanMapper.findCommunityInfomByUserId(type,userId);

            //查询每一个通知的总人数和总阅读数
            for (AppCommunityInformBean appCommunityInformBean : appCommunityInformBeans) {

                //查询当前用户是否阅读过
                Integer read = appCommunityInformAppUserBeanMapper.getRead(userId,appCommunityInformBean.getId());
                if(read == null || read == 0){
                    appCommunityInformBean.setIsRead(Byte.parseByte("0"));
                }else{
                    appCommunityInformBean.setIsRead(Byte.parseByte("1"));
                }

                AppCommunityInformAppUserBean communityInformAppUserBean=appCommunityInformAppUserBeanMapper.queryTotalAndRedTotal(appCommunityInformBean.getId());
                appCommunityInformBean.setTotal(communityInformAppUserBean.getTotal());
                appCommunityInformBean.setReadTotal(communityInformAppUserBean.getRedTotal());
                appCommunityInformBean.setCommunityName(appCommunityBeanMapper.selectByPrimaryKey(appCommunityInformBean.getCommunityId()).getName());
            }
            return new Result(ResultCode.SUCCESS,new PageInfo(appCommunityInformBeans));
        } catch (Exception e) {
            logger.info("分页查询社区通知接口异常："+e);
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByCommunityId(Integer communityId) {
		try {
			return	new Result(ResultCode.SUCCESS,appCommunityInformBeanMapper.findByCommunityId(communityId));
		} catch (Exception e) {
			logger.info("根据社区编号查找所有通知异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Result findCommunityInfomById(Integer id) {
        try {
            AppCommunityInformBean appCommunityInformBean = appCommunityInformBeanMapper.selectByPrimaryKey(id);
            AppCommunityInformAppUserBean communityInformAppUserBean=appCommunityInformAppUserBeanMapper.queryTotalAndRedTotal(appCommunityInformBean.getId());
            appCommunityInformBean.setTotal(communityInformAppUserBean.getTotal());
            appCommunityInformBean.setReadTotal(communityInformAppUserBean.getRedTotal());
            appCommunityInformBean.setCommunityName(appCommunityBeanMapper.selectByPrimaryKey(appCommunityInformBean.getCommunityId()).getName());
            return new Result(ResultCode.SUCCESS, appCommunityInformBean);
        } catch (Exception e) {
            logger.info("根据社区通知编号查询通知详情：" + e);
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }

    @Override
    public Result getCommunityInfomById(Integer id, Integer userId) {
        try {

            Integer read = appCommunityInformAppUserBeanMapper.getRead(userId,id);

            if(read != null && read == 0){
                appCommunityInformAppUserBeanMapper.updateByCommIdAndUserId(userId,id);
            }

            AppCommunityInformBean appCommunityInformBean = appCommunityInformBeanMapper.selectByPrimaryKey(id);
            AppCommunityInformAppUserBean communityInformAppUserBean=appCommunityInformAppUserBeanMapper.queryTotalAndRedTotal(appCommunityInformBean.getId());
            appCommunityInformBean.setTotal(communityInformAppUserBean.getTotal());
            appCommunityInformBean.setReadTotal(communityInformAppUserBean.getRedTotal());
            appCommunityInformBean.setCommunityName(appCommunityBeanMapper.selectByPrimaryKey(appCommunityInformBean.getCommunityId()).getName());
            return new Result(ResultCode.SUCCESS, appCommunityInformBean);
        } catch (Exception e) {
            logger.info("根据社区通知编号查询通知详情：" + e);
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }

    @Override
    public Result getUnreadCount(Integer userId) {
        return new Result(ResultCode.SUCCESS,appCommunityInformAppUserBeanMapper.getUnreadCount(userId));
    }
}
