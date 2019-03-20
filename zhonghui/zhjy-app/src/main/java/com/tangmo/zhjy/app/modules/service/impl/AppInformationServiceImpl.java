/**     
 * @Title: AppInformationServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.app.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月18日 上午4:20:17   
 * @version V1.0     
 */   
package com.tangmo.zhjy.app.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tangmo.zhjy.app.modules.dao.*;
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
import com.tangmo.zhjy.app.modules.bean.AppInformation;
import com.tangmo.zhjy.app.modules.bean.AppUserBean;
import com.tangmo.zhjy.app.modules.dto.AppInformationDto;
import com.tangmo.zhjy.app.modules.service.AppInformationService;

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
 * @Date : 2018年1月18日 上午4:20:17
 */
@Service
@Transactional
public class AppInformationServiceImpl implements AppInformationService {

	@Autowired
	private AppInformationMapper appInformationMapper;

	@Autowired
	private AppInformationImagesMapper appInformationImagesMapper;

	@Autowired
    private AppInformationPageViewMapper appInformationPageViewMapper;

	@Autowired
	private AppUserBeanMapper appUserBeanMapper;

	@Autowired
    private AppBrowsingHistoryBeanMapper appBrowsingHistoryBeanMapper;
	
	@SuppressWarnings("unused")
	@Autowired
	private AppCollectMapper appCollectMapper;

	@Autowired
	private AppTwoClassifyMapper appTwoClassifyMapper;

	@Autowired
	private SysUserBeanMapper sysUserBeanMapper;



	Logger logger = Logger.getLogger(AppInformationServiceImpl.class);

	/* (非 Javadoc)   
	 * <p>Title: save</p>   
	 * <p>Description: </p>   
	 * @param appInformationDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppInformationService#save(com.tangmo.zhjy.modules.app.dto.AppInformationDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result save(AppInformationDto appInformationDto){
		try {
			AppInformation appInformation = new AppInformation(appInformationDto.getTwoClassifyId(),
					appInformationDto.getTitle(),0,
					appInformationDto.getAppUserid(),
					appInformationDto.getContent(),
					1);
			appInformationMapper.insert(appInformation);
			System.out.print(appInformation.getAppUserid()+"============================");
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
	public Result modify(AppInformationDto appInformationDto){
		if(appInformationDto.getId()!=null){
			if(appInformationMapper.selectByPrimaryKey(appInformationDto.getId())!=null){
				try {
					AppInformation appInformation = new AppInformation(appInformationDto.getId(),appInformationDto.getTitle(),
							appInformationDto.getFreeze(),
							appInformationDto.getSysUserid(),
							appInformationDto.getContent(),
							appInformationDto.getIsShow(),
							1);
					appInformationMapper.updateByPrimaryKeySelective(appInformation);
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


    @Override
    public Result getInformationById(Integer userId,Integer start,Integer end) {
        PageHelper.startPage(start, end);
        List<AppInformation> list = appInformationMapper.getInformationById(userId);
        for (int i = 0;i<list.size();i++){
            list.get(i).setTwoClassifyName(appTwoClassifyMapper.selectByPrimaryKey(list.get(i).getTwoClassifyId()).getName());
        }
        return new Result(ResultCode.SUCCESS,new PageInfo(list));
    }

    @Override
    public Result delInformationById(Integer id) {
	    if(id == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        appInformationPageViewMapper.deleteByInfoId(id);
        appBrowsingHistoryBeanMapper.deleteByInfoId(id);
        appInformationMapper.deleteByPrimaryKey(id);
        return new Result(ResultCode.SUCCESS);
    }


    @Override
    public Result getHotInformation(Integer pageNo, Integer pageSize) {

        if(pageSize!=null && pageNo!=null){
            PageHelper.startPage(pageNo, pageSize);
        }
        List<AppInformation>  appInformations = appInformationMapper.getHotInformation();

        if(appInformations.size()>0){
            for (AppInformation appInformation : appInformations) {
                String str = appInformation.getContent();
                List<String> imgs = new ArrayList<>();
                while (true){
                    if(str.indexOf("<img") != -1){
                        str = str.substring(str.indexOf("<img"));
                        imgs.add(str.substring(str.indexOf("<img"),str.indexOf("/>")+2));
                        str = str.substring(str.indexOf("/>")+2);
                    }else{
                        appInformation.setImgList(imgs);
                        break;
                    }
                }
                appInformation.setClassTwoName(appTwoClassifyMapper.selectByPrimaryKey(appInformation.getTwoClassifyId()).getName());
                appInformation.setAppInformationImages(appInformationImagesMapper.selectByPrimaryKey(appInformation.getId()));
            }
        }
        return new Result(ResultCode.SUCCESS,new PageInfo(appInformations));
    }

    /*已修改—添加 appinformation.setU_userName(appUserBeanMapper.selectByPrimaryKey(appinformation.getSysUserid()).getNikeName()); 获得发布者昵称*/
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

            //增加文章游览次数
            appInformationMapper.addInfoVisitCount(id);

			AppInformation appinformation=appInformationMapper.selectByPrimaryKey(id);

			appinformation.setClassifyId(appTwoClassifyMapper.selectByPrimaryKey(appinformation.getTwoClassifyId()).getAppClassifyid());
			if(appinformation.getSysUserid()!=null){
				appinformation.setU_userName(sysUserBeanMapper.selectByPrimaryKey(appinformation.getSysUserid()).getUsername());
			}else{
				appinformation.setU_userName(appUserBeanMapper.selectByPrimaryKey(appinformation.getAppUserid()).getNikeName());
			}

            appinformation.setTwoClassifyName(appTwoClassifyMapper.selectByPrimaryKey(appinformation.getTwoClassifyId()).getName());
			appinformation.setAppInformationImages(appInformationImagesMapper.selectByPrimaryKey(appinformation.getId()));
			return new Result(ResultCode.SUCCESS,appinformation);
		} catch (Exception e) {
		    e.printStackTrace();
			logger.info("根据编号查找文章出现异常："+e);
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
	public Result sysFindPage(String name,Integer pageNo,Integer pageSize,Integer classifyId){
		try {
			if(name!=null && !StringUtil.isEmpty(name)){
				name="%"+name+"%";
			}else{
				name="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appInformationMapper.sysFindPage(name,classifyId)));
		} catch (Exception e) {
			logger.info("后台查找发布文章异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	 * <p>Title: appFindPage</p>   
	 * <p>Description: </p>   
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppInformationService#appFindPage(java.lang.String, java.lang.Integer, java.lang.Integer)   
	 */  

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result appFindPage(Integer id,String name,Integer pageNo,Integer pageSize){
		try {
            if(pageSize!=null && pageNo!=null){
                PageHelper.startPage(pageNo, pageSize);
            }
            List<AppInformation> appInformations = null;
			if(name!=null && !"".equals(name)){
				name="%"+name+"%";
			}else{
                return new Result(ResultCode.SUCCESS,new PageInfo(appInformations));
			}
			appInformations=appInformationMapper.appFindPage(id,name);
			for (AppInformation appInformation : appInformations) {
				appInformation.setAppInformationImages(appInformationImagesMapper.selectByPrimaryKey(appInformation.getId()));
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appInformations));
		} catch (Exception e) {
			logger.info("前台查找发布文章异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/**
	 * 
	 * @Title: findByCommunityInformId   
	 * @Description: TODO(根据社区通知编号查找未读用户信息)   
	 * @param @param communityInformId
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Result findByCommunityInformId(Integer communityInformId){
		try {

			List<AppUserBean> users=appUserBeanMapper.findByCommunityInformId(communityInformId);
			return new Result(ResultCode.SUCCESS,users);
		} catch (Exception e) {
			logger.info("社区通知编号查找未读用户信息接口异常："+e);
			return new Result(ResultCode.PAPAMETE_ERROR);
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
			List<AppInformation>  appInformations = appInformationMapper.findClassifyId(classifId,title);

			if(appInformations.size()>0){
				for (AppInformation appInformation : appInformations) {
                    String str = appInformation.getContent();
                    List<String> imgs = new ArrayList<>();
                    while (true){
                        if(str.indexOf("<img") != -1){
                            str = str.substring(str.indexOf("<img"));
                            imgs.add(str.substring(str.indexOf("<img"),str.indexOf("/>")+2));
                            str = str.substring(str.indexOf("/>")+2);
                        }else{
                            appInformation.setImgList(imgs);
                            break;
                        }
                    }
					appInformation.setClassTwoName(appTwoClassifyMapper.selectByPrimaryKey(appInformation.getTwoClassifyId()).getName());
					appInformation.setAppInformationImages(appInformationImagesMapper.selectByPrimaryKey(appInformation.getId()));
				}
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appInformations));
		} catch (Exception e) {
			logger.info("根据一级菜单及搜索接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

    @Override
    public Result findClassImgById(Integer classifId) {
        List<AppInformation>  appInformations = appInformationMapper.findClassifyId(classifId,"%%");
        int a = 0;
        List<AppInformation> informationList = new ArrayList<>();
        if(appInformations.size()>0){
            for (AppInformation appInformation : appInformations) {
                String str = appInformation.getContent();
                if(str.indexOf("<img") != -1){
                    while (true){
                        List<String> imgs = new ArrayList<>();
                        str = str.substring(str.indexOf("<img"));
                        imgs.add(str.substring(str.indexOf("<img"),str.indexOf("/>")+2));
                        AppInformation appInformation1 = new AppInformation();
                        appInformation1.setId(appInformation.getId());
                        appInformation1.setTitle(appInformation.getTitle());
                        appInformation1.setImgList(imgs);
                        informationList.add(appInformation1);
                        a++;
                        break;
                    }
                }
                if(a == 9){
                    break;
                }
            }
        }
        return new Result(ResultCode.SUCCESS,informationList);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Result findTwoClassifyId(Integer twoclassifId, Integer pageNo, Integer pageSize, String title) {
        try {
            if(title!=null && !"".equals(title)){
                title="%"+title+"%";
            }else{
                title="%%";
            }
            if(pageSize!=null && pageNo!=null){
                PageHelper.startPage(pageNo, pageSize);
            }
            List<AppInformation>  appInformations = appInformationMapper.findTwoClassifyId(twoclassifId,title);

            if(appInformations.size()>0){
                for (AppInformation appInformation : appInformations) {
                    String str = appInformation.getContent();
                    List<String> imgs = new ArrayList<>();
                    while (true){
                        if(str.indexOf("<img") != -1){
                            str = str.substring(str.indexOf("<img"));
                            imgs.add(str.substring(str.indexOf("<img"),str.indexOf("/>")+2));
                            str = str.substring(str.indexOf("/>")+2);
                        }else{
                            appInformation.setImgList(imgs);
                            break;
                        }
                    }
                    appInformation.setClassTwoName(appTwoClassifyMapper.selectByPrimaryKey(appInformation.getTwoClassifyId()).getName());
                    appInformation.setAppInformationImages(appInformationImagesMapper.selectByPrimaryKey(appInformation.getId()));
                }
            }
            return new Result(ResultCode.SUCCESS,new PageInfo(appInformations));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("根据二级菜单及搜索接口异常："+e);
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }

    @Override
    public Result findTwoClassifImgById(Integer twoclassifId) {
        List<AppInformation>  appInformations = appInformationMapper.findTwoClassifyId(twoclassifId,"%%");
        int a = 0;
        List<AppInformation> informationList = new ArrayList<>();
        if(appInformations.size()>0){
            for (AppInformation appInformation : appInformations) {
                String str = appInformation.getContent();
                if(str.indexOf("<img") != -1){
                    while (true){
                        List<String> imgs = new ArrayList<>();
                        str = str.substring(str.indexOf("<img"));
                        imgs.add(str.substring(str.indexOf("<img"),str.indexOf("/>")+2));
                        AppInformation appInformation1 = new AppInformation();
                        appInformation1.setId(appInformation.getId());
                        appInformation1.setTitle(appInformation.getTitle());
                        appInformation1.setImgList(imgs);
                        informationList.add(appInformation1);
                        a++;
                        break;
                    }
                }
                if(a == 9){
                    break;
                }
            }
        }
        return new Result(ResultCode.SUCCESS,informationList);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result queryBrowsingHistory(Integer userId,Integer pageNo,Integer pageSize) {
		try {
		    PageHelper.startPage(pageNo,pageSize);
			//1.查询所有浏览记录
			List<AppInformation> appInformations = appInformationMapper.queryBrowsingHistory(userId);
			//2.查询文章的封面图片
			if(appInformations.size()>0){
				for (AppInformation appInformation : appInformations) {
					appInformation.setAppInformationImages(appInformationImagesMapper.selectByPrimaryKey(appInformation.getId()));
				}
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appInformations));
		} catch (Exception e) {
			logger.info("获取浏览记录接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

    @Override
    public Result ClearBrowsingHistory(Integer userId) {
        try {
            appInformationMapper.ClearBrowsingHistory(userId);

            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            logger.info("清空浏览记录接口异常："+e);
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Result findByUserId(Integer userId,Integer pageNo,Integer pageSize){
		try {
		    PageHelper.startPage(pageNo,pageSize);
			//1.查询所有浏览记录
			List<AppInformation> appInformations=appInformationMapper.findByUserId(userId);
			//2.查询文章的封面图片
			if(appInformations.size()>0){
				for (AppInformation appInformation : appInformations) {
					appInformation.setAppInformationImages(appInformationImagesMapper.selectByPrimaryKey(appInformation.getId()));
				}
			}
			return  new Result(ResultCode.SUCCESS,new PageInfo(appInformations));
		} catch (Exception e) {
			logger.info("查询用户收藏文章异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

}
