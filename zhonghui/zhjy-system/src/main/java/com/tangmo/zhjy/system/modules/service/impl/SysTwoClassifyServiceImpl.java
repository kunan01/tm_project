/**     
 * @Title: AppTwoClassifyServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.app.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月17日 下午11:51:28   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.service.impl;

import com.tangmo.zhjy.system.modules.dao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.SysTwoClassify;
import com.tangmo.zhjy.system.modules.dto.SysTwoClassifyDto;
import com.tangmo.zhjy.system.modules.service.SysTwoClassifyService;
import com.tangmo.zhjy.system.utils.PageInfo;
import com.tangmo.zhjy.system.utils.StringUtil;

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
 * @Description : TODO(二级分类模块业务逻辑层)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月17日 下午11:51:28
 */
@Service
@Transactional
public class SysTwoClassifyServiceImpl implements SysTwoClassifyService {

	@Autowired
	private SysTwoClassifyMapper appTwoClassifyMapper;
	@Autowired
	private AppBrowsingHistoryBeanMapper appBrowsingHistoryBeanMapper;
	@Autowired
	private AppCollectMapper appCollectMapper;
	@Autowired
	private AppInformationPageViewMapper appInformationPageViewMapper;
	@Autowired
	private SysInformationImagesMapper appInformationImagesMapper;
	@Autowired
	private SysSlideBeanMapper sysSlideBeanMapper;
	@Autowired
	private SysInformationMapper sysInformationMapper;
	@Autowired
	private AppMyChannelBeanMapper appMyChannelBeanMapper;

	private Logger logger = Logger.getLogger(SysTwoClassifyServiceImpl.class);

	/* (非 Javadoc)   
	 * <p>Title: saveTwoClassify</p>   
	 * <p>Description: </p>   
	 * @param SysTwoClassifyDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppTwoClassifyService#saveTwoClassify(com.tangmo.zhjy.modules.app.dto.SysTwoClassifyDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result saveTwoClassify(SysTwoClassifyDto SysTwoClassifyDto){
		try {
			SysTwoClassify appTwoClassify = new  SysTwoClassify(SysTwoClassifyDto.getName(),
					SysTwoClassifyDto.getIcon(),
					SysTwoClassifyDto.getUrl(),
					SysTwoClassifyDto.getAppClassifyid(),
					SysTwoClassifyDto.getFreeze());
			appTwoClassifyMapper.insert(appTwoClassify);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("增加二级分类异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	 * <p>Title: modify</p>   
	 * <p>Description: </p>   
	 * @param SysTwoClassifyDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppTwoClassifyService#modify(com.tangmo.zhjy.modules.app.dto.SysTwoClassifyDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modify(SysTwoClassifyDto SysTwoClassifyDto){
		if(SysTwoClassifyDto.getId()!=null){
			if(appTwoClassifyMapper.selectByPrimaryKey(SysTwoClassifyDto.getId())!=null){
				try {
					SysTwoClassify appTwoClassify = new  SysTwoClassify(SysTwoClassifyDto.getId(),
							SysTwoClassifyDto.getName(),
							SysTwoClassifyDto.getIcon(),
							SysTwoClassifyDto.getUrl(),
							SysTwoClassifyDto.getAppClassifyid(),
							SysTwoClassifyDto.getFreeze(),
							SysTwoClassifyDto.getIsShow());
					appTwoClassifyMapper.updateByPrimaryKeySelective(appTwoClassify);
					return new Result(ResultCode.SUCCESS);
				} catch (Exception e) {
					logger.info("修改二级分类异常："+e);
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
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppTwoClassifyService#findById(java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findById(Integer id){
		try {
			return new Result(ResultCode.SUCCESS,appTwoClassifyMapper.selectByPrimaryKey(id));
		} catch (Exception e) {
			logger.info("根据Id查找二级分类异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public Result delByTwoClassifyId(Integer twoClassifyId){
		try {
			//要彻底删除二级分类下面的所有文章信息
				//根据文章id要删除所有与文章相关的信息
					//根据二级分类id查询该二级菜单下的所有文章id，删除有关文章的浏览记录
					appBrowsingHistoryBeanMapper.deleteByTwoClassifyId(twoClassifyId);
					//根据二级分类id查询该二级菜单下的所有文章id，删除有关文章的收藏
					appCollectMapper.deleteByTwoClassifyId(twoClassifyId);
					//根据二级分类id查询该二级菜单下的所有文章id，删除有关文章的访问量
					appInformationPageViewMapper.deleteByTwoClassifyId(twoClassifyId);
					//根据二级分类id查询该二级菜单下的所有文章id，删除有关文章的封面图
					appInformationImagesMapper.deleteBTwoClassifyId(twoClassifyId);
					//根据二级分类id查询该二级菜单下的所有文章id，删除有关文章的轮播图
					sysSlideBeanMapper.deleteByTwoClassifyId(twoClassifyId);
					//根据二级分类id查询该二级菜单下的所有文章id来彻底删除相关的文章表信息
					sysInformationMapper.deleteByTwoClassifyId(twoClassifyId);
			//根据二级分类菜单id删除关注频道表的所有信息
			appMyChannelBeanMapper.deleteByTwoClassifyId(twoClassifyId);
			//根据二级分类菜单id删除二级菜单分类表
			appTwoClassifyMapper.deleteByPrimaryKey(twoClassifyId);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("根据TwoClassifyId彻底删除二级分类异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}


	/* (非 Javadoc)   
	 * <p>Title: findByPage</p>   
	 * <p>Description: </p>   
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppTwoClassifyService#findByPage(java.lang.String, java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByPage(String name,Integer pageNo,Integer pageSize){
		try {
			if(name!=null && !StringUtil.isEmpty(name)){
				name="%"+name+"%";
			}else{
				name="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(appTwoClassifyMapper.findByPage(name)));
		} catch (Exception e) {
			logger.info("分页查找二级分类异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	 * <p>Title: findByAppClassifyId</p>   
	 * <p>Description: </p>   
	 * @param classifyId
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppTwoClassifyService#findByAppClassifyId(java.lang.Integer)   
	 */  
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Result findByAppClassifyId(Integer classifyId){
		try {
			return new Result(ResultCode.SUCCESS,appTwoClassifyMapper.findByAppClassifyId(classifyId));
		} catch (Exception e) {
			logger.info("根据一级分类查找所有二级分类异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}
}
