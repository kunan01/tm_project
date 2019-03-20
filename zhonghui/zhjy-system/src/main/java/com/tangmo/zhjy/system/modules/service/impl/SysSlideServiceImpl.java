/**     
 * @Title: AppSlideServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.app.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月16日 下午5:45:07   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.SysSlideBean;
import com.tangmo.zhjy.system.modules.dao.SysSlideBeanMapper;
import com.tangmo.zhjy.system.modules.dto.SysSlideBeanDto;
import com.tangmo.zhjy.system.modules.service.SysSlideService;
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
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月16日 下午5:45:07
 */
@Service
@Transactional
public class SysSlideServiceImpl implements SysSlideService {

	@Autowired
	private SysSlideBeanMapper sysSlideBeanMapper;

	Logger logger = Logger.getLogger(SysSlideServiceImpl.class);

	/* (非 Javadoc)   
	 * <p>Title: addSlide</p>   
	 * <p>Description: </p>   
	 * @param SysSlideBeanDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppSlideService#addSlide(com.tangmo.zhjy.modules.app.dto.SysSlideBeanDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result addSlide(SysSlideBeanDto SysSlideBeanDto){
		try {
			SysSlideBean SysSlideBean = new SysSlideBean(SysSlideBeanDto.getTitle(),SysSlideBeanDto.getUrl(),SysSlideBeanDto.getRank(),SysSlideBeanDto.getFreeze(),SysSlideBeanDto.getInformationId());
			sysSlideBeanMapper.insert(SysSlideBean);
		} catch (Exception e) {
			logger.info("添加轮播图接口异常:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		return new Result(ResultCode.SUCCESS);

	}

	/* (非 Javadoc)   
	 * <p>Title: modify</p>   
	 * <p>Description: </p>   
	 * @param SysSlideBeanDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppSlideService#modify(com.tangmo.zhjy.modules.app.dto.SysSlideBeanDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modify(SysSlideBeanDto SysSlideBeanDto){
		try {
			SysSlideBean SysSlideBean = new SysSlideBean(SysSlideBeanDto.getId(),SysSlideBeanDto.getTitle(),SysSlideBeanDto.getUrl(),SysSlideBeanDto.getIsShow(),SysSlideBeanDto.getRank(),SysSlideBeanDto.getFreeze(),SysSlideBeanDto.getInformationId());
			sysSlideBeanMapper.updateByPrimaryKeySelective(SysSlideBean);
		} catch (Exception e) {
			logger.info("修改轮播图接口异常:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);

		}
		return new Result(ResultCode.SUCCESS);
	}

	/* (非 Javadoc)   
	 * <p>Title: findByPage</p>   
	 * <p>Description: </p>   
	 * @param roleName
	 * @param pageNo
	 * @param pageSize
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppSlideService#findByPage(java.lang.String, java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByPage(String roleName, Integer pageNo, Integer pageSize){

		try {
			if(roleName!=null && !StringUtil.isEmpty(roleName)){
				roleName="%"+roleName+"%";
			}else{
				roleName="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			return new Result(ResultCode.SUCCESS,new PageInfo(sysSlideBeanMapper.findByPage(roleName)));
		} catch (Exception e) {
			logger.info("轮播图分页查询异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/**
	 * 根据编号查询轮播图是否存在
	 */
	public SysSlideBean findById(int id){
		try {
			return sysSlideBeanMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据编号彻底删除轮播图
	 */
	@Override
	public Result delById(Integer id) {
		try {
			sysSlideBeanMapper.deleteByPrimaryKey(id);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("彻底删除轮播图出现异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}
}
