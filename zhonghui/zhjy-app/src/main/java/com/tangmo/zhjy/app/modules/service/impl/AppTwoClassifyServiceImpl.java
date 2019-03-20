/**     
 * @Title: AppTwoClassifyServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.app.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月17日 下午11:51:28   
 * @version V1.0     
 */   
package com.tangmo.zhjy.app.modules.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.AppTwoClassify;
import com.tangmo.zhjy.app.modules.dao.AppTwoClassifyMapper;
import com.tangmo.zhjy.app.modules.dto.AppTwoClassifyDto;
import com.tangmo.zhjy.app.modules.service.AppTwoClassifyService;

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
public class AppTwoClassifyServiceImpl implements AppTwoClassifyService {

	@Autowired
	private AppTwoClassifyMapper appTwoClassifyMapper;

	private Logger logger = Logger.getLogger(AppTwoClassifyServiceImpl.class);

	/* (非 Javadoc)   
	 * <p>Title: saveTwoClassify</p>   
	 * <p>Description: </p>   
	 * @param appTwoClassifyDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppTwoClassifyService#saveTwoClassify(com.tangmo.zhjy.modules.app.dto.AppTwoClassifyDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result saveTwoClassify(AppTwoClassifyDto appTwoClassifyDto){
		try {
			AppTwoClassify appTwoClassify = new  AppTwoClassify(appTwoClassifyDto.getName(),
					appTwoClassifyDto.getIcon(),
					appTwoClassifyDto.getUrl(),
					appTwoClassifyDto.getAppClassifyid(),
					appTwoClassifyDto.getFreeze());
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
	 * @param appTwoClassifyDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppTwoClassifyService#modify(com.tangmo.zhjy.modules.app.dto.AppTwoClassifyDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modify(AppTwoClassifyDto appTwoClassifyDto){
		if(appTwoClassifyDto.getId()!=null){
			if(appTwoClassifyMapper.selectByPrimaryKey(appTwoClassifyDto.getId())!=null){
				try {
					AppTwoClassify appTwoClassify = new  AppTwoClassify(appTwoClassifyDto.getId(),
							appTwoClassifyDto.getName(),
							appTwoClassifyDto.getIcon(),
							appTwoClassifyDto.getUrl(),
							appTwoClassifyDto.getAppClassifyid(),
							appTwoClassifyDto.getFreeze(),
							appTwoClassifyDto.getIsShow());
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByAppClassifyId(Integer classifyId){
		try {
			return new Result(ResultCode.SUCCESS,appTwoClassifyMapper.findByAppClassifyId(classifyId));
		} catch (Exception e) {
			logger.info("根据一级分类查找所有二级分类异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

    @Override
    public Result findByAppClassById() {
        try {
            return new Result(ResultCode.SUCCESS,appTwoClassifyMapper.findByAppClassById());
        } catch (Exception e) {
            logger.info("查询所有二级分类接口异常："+e);
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }
}
