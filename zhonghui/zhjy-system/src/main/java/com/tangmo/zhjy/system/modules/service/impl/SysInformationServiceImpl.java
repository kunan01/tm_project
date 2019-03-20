/**     
 * @Title: AppInformationServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.app.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月18日 上午4:20:17   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.service.impl;

import java.util.List;

import com.tangmo.zhjy.system.modules.bean.SysTwoClassify;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.dao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.SysInformation;
import com.tangmo.zhjy.system.modules.dto.SysInformationDto;
import com.tangmo.zhjy.system.modules.service.SysInformationService;
import com.tangmo.zhjy.system.utils.PageInfo;
import com.tangmo.zhjy.system.utils.StringUtil;

/**
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月18日 上午4:20:17
 */
@Service
@Transactional
public class SysInformationServiceImpl implements SysInformationService {

	@Autowired
	private SysInformationMapper SysInformationMapper;

	@Autowired
	private SysInformationImagesMapper appInformationImagesMapper;

	@Autowired
	private AppBrowsingHistoryBeanMapper appBrowsingHistoryBeanMapper;

	@Autowired
	private AppCollectMapper appCollectMapper;

	@Autowired
	private AppInformationPageViewMapper appInformationPageViewMapper;

	@Autowired
	private SysSlideBeanMapper sysSlideBeanMapper;

	@Autowired
	private AppUserBeanMapper appUserBeanMapper;

	@Autowired
	private SysUserBeanMapper sysUserBeanMapper;

	@Autowired
	private SysTwoClassifyMapper appTwoClassifyMapper;

	Logger logger = Logger.getLogger(SysInformationServiceImpl.class);

	/* (非 Javadoc)   
	 * <p>Title: save</p>   
	 * <p>Description: </p>   
	 * @param appInformationDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppInformationService#save(com.tangmo.zhjy.modules.app.dto.AppInformationDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result save(SysInformationDto appInformationDto){
		try {
			SysInformation appInformation = new SysInformation(appInformationDto.getTwoClassifyId(),
					appInformationDto.getTitle(),
					appInformationDto.getFreeze(),
					appInformationDto.getSysUserid(),
					appInformationDto.getContent());
			SysInformationMapper.insert(appInformation);
			if(appInformationDto.getUrls()!=null && appInformationDto.getUrls().length>0){
				appInformationImagesMapper.insert(appInformation.getId(),appInformationDto.getUrls());
			}
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("添加文章出现异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}

	}

	/* (非 Javadoc)   
	 * <p>Title: modify</p>   
	 * <p>Description: </p>   
	 * @param appInformationDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppInformationService#modify(com.tangmo.zhjy.modules.app.dto.AppInformationDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modify(SysInformationDto appInformationDto){
		if(appInformationDto.getId()!=null){
			if(SysInformationMapper.selectByPrimaryKey(appInformationDto.getId())!=null){
				try {
					SysInformation appInformation = new SysInformation(appInformationDto.getId(),appInformationDto.getTitle(),
							appInformationDto.getFreeze(),
							appInformationDto.getSysUserid(),
							appInformationDto.getContent(),
							appInformationDto.getIsShow(),
							1);
					System.out.println("==========="+appInformationDto.getTwoClassifyId()+"============");
					appInformation.setTwoClassifyId(appInformationDto.getTwoClassifyId());
					SysInformationMapper.updateByPrimaryKeySelective(appInformation);
					if(appInformationDto.getUrls()!=null && appInformationDto.getUrls().length>0){
						appInformationImagesMapper.insert(appInformation.getId(),appInformationDto.getUrls());
					}
					return new Result(ResultCode.SUCCESS);
				} catch (Exception e) {
					logger.info("修改文章出现异常："+e);
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return new Result(ResultCode.WEAK_NET_WORK);
				}
			}
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}


	/* (非 Javadoc)   
	 * <p>Title: findById</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppInformationService#findById(java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findById(Integer id){
		try {
			SysInformation appinformation=SysInformationMapper.selectByPrimaryKey(id);
			if(appinformation.getSysUserid()!=null){
				appinformation.setUname(sysUserBeanMapper.selectByPrimaryKey(appinformation.getSysUserid()).getUsername());
			}else{
				appinformation.setUname(appUserBeanMapper.selectByPrimaryKey(appinformation.getAppUserid()).getNikeName());
			}
			appinformation.setClassifyId(appTwoClassifyMapper.selectByPrimaryKey(appinformation.getTwoClassifyId()).getAppClassifyid());
			appinformation.setSysInformationImages(appInformationImagesMapper.selectByPrimaryKey(appinformation.getId()));
			return new Result(ResultCode.SUCCESS,appinformation);
		} catch (Exception e) {
			logger.info("根据编号查找文章出现异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public Result delById(Integer id){
		try {
			//删除有关文章的浏览记录
			appBrowsingHistoryBeanMapper.deleteByInfomation(id);
			//删除有关文章的收藏
			appCollectMapper.deleteByInfomation(id);
			// 删除有关文章的访问量
			appInformationPageViewMapper.deleteByInfomation(id);
			//删除有关文章的封面图
			appInformationImagesMapper.deleteByInfomation(id);
			//删除有关文章的轮播图
			sysSlideBeanMapper.deleteByInfomation(id);
			//根据id删除文章表
			SysInformationMapper.deleteByPrimaryKey(id);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("彻底删除文章出现异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}

	}

	/* (非 Javadoc)   
	 * <p>Title: sysFindPage</p>   
	 * <p>Description: </p>   
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppInformationService#sysFindPage(java.lang.String, java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result sysFindPage(String name,Integer pageNo,Integer pageSize,Integer classifyId,Integer sysUserId){
		try {
			if(name!=null && !StringUtil.isEmpty(name)){
				name="%"+name+"%";
			}else{
				name="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			List<SysInformation> listSysInformation=SysInformationMapper.sysFindPage(name,classifyId,sysUserId);
			for(SysInformation sysInformation:listSysInformation){
				SysTwoClassify sysTwoClassify=appTwoClassifyMapper.selectByPrimaryKey(sysInformation.getTwoClassifyId());
				sysInformation.setClassTwoName(sysTwoClassify.getName());
				if(sysInformation.getSysUserid()==null && sysInformation.getAppUserid()!=null){
					String appUserName=appUserBeanMapper.selectByPrimaryKey(sysInformation.getAppUserid()).getNikeName();
					SysUserBean sysUserBean=new SysUserBean();
					sysUserBean.setUsername(appUserName);
					sysInformation.setSysUser(sysUserBean);
				}else{
					SysUserBean sysUserBeans =sysUserBeanMapper.selectByPrimaryKey(sysInformation.getSysUserid());
					System.out.print(sysUserBeans);
					String sysUserName=sysUserBeans.getUsername();
					SysUserBean sysUserBean=new SysUserBean();
					sysUserBean.setUsername(sysUserName);
					sysInformation.setSysUser(sysUserBean);
				}
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(listSysInformation));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("后台查找发布文章异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result sysFindPageNameType(String name,Integer pageNo,Integer pageSize,Integer twoClassifyId,Integer sysUserId){
		try {
			if(name!=null && !StringUtil.isEmpty(name)){
				name="%"+name+"%";
			}else{
				name=null;
			}
			if(twoClassifyId==null ||"".equals(twoClassifyId)){
				twoClassifyId=null;
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			List<SysInformation> listSysInformation=SysInformationMapper.sysFindPageNameType(name,twoClassifyId,sysUserId);
			for(SysInformation sysInformation:listSysInformation){
				SysTwoClassify sysTwoClassify=appTwoClassifyMapper.selectByPrimaryKey(sysInformation.getTwoClassifyId());
				sysInformation.setClassTwoName(sysTwoClassify.getName());
				if(sysInformation.getSysUserid()==null){
					String appUserName=appUserBeanMapper.selectByPrimaryKey(sysInformation.getAppUserid()).getNikeName();
					SysUserBean sysUserBean=new SysUserBean();
					sysUserBean.setUsername(appUserName);
					sysInformation.setSysUser(sysUserBean);
				}else{
					SysUserBean sysUserBeans =sysUserBeanMapper.selectByPrimaryKey(sysInformation.getSysUserid());
					System.out.print(sysUserBeans);
					String sysUserName=sysUserBeans.getUsername();
					SysUserBean sysUserBean=new SysUserBean();
					sysUserBean.setUsername(sysUserName);
					sysInformation.setSysUser(sysUserBean);
				}
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(listSysInformation));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("后台查找发布文章异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findClassifyId(Integer classifId,Integer pageNo,Integer pageSize,String title) {
		try {
			if(title!=null && !"".equals(title)){
				title="%"+title+"%";
			}else{
				title="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			List<SysInformation>  appInformations=SysInformationMapper.findClassifyId(classifId,title);
			if(appInformations.size()>0){
				for (SysInformation appInformation : appInformations) {
					appInformation.setSysInformationImages(appInformationImagesMapper.selectByPrimaryKey(appInformation.getId()));
				}
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appInformations));
		} catch (Exception e) {
			logger.info("根据一级菜单及搜索接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}


    @Override
    public Result getInfoCheckedList(String title,Integer pageNo, Integer pageSize) {
        try {
            if(title!=null && !"".equals(title)){
                title="%"+title+"%";
            }else{
                title="%%";
            }
            if(pageSize!=null && pageNo!=null){
                PageHelper.startPage(pageNo, pageSize);
            }

            List<SysInformation>  appInformations=SysInformationMapper.getInfoCheckedList(title);
            for(SysInformation sysInformation:appInformations){
                SysTwoClassify sysTwoClassify=appTwoClassifyMapper.selectByPrimaryKey(sysInformation.getTwoClassifyId());
                sysInformation.setClassTwoName(sysTwoClassify.getName());
                if(sysInformation.getSysUserid()==null){
                    String appUserName=appUserBeanMapper.selectByPrimaryKey(sysInformation.getAppUserid()).getNikeName();
                    SysUserBean sysUserBean = new SysUserBean();
                    sysUserBean.setUsername(appUserName);
                    sysInformation.setSysUser(sysUserBean);
                }else{
                    SysUserBean sysUserBeans =sysUserBeanMapper.selectByPrimaryKey(sysInformation.getSysUserid());
                    System.out.print(sysUserBeans);
                    String sysUserName=sysUserBeans.getUsername();
                    SysUserBean sysUserBean=new SysUserBean();
                    sysUserBean.setUsername(sysUserName);
                    sysInformation.setSysUser(sysUserBean);
                }
            }
            return new Result(ResultCode.SUCCESS,new PageInfo(appInformations));
        } catch (Exception e) {
            logger.info("后台查询未审核的文章列表接口异常："+e);
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }

    @Override
    public Result updInfoCheckedById(Integer id, Integer state) {
        try {
            SysInformationMapper.updInfoCheckedById(id,state);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            logger.info("后台审核文章接口异常："+e);
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }
}
