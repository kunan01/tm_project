/**     
 * @Title: AppUserServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.app.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月18日 下午9:55:41   
 * @version V1.0     
 */   
package 
com.tangmo.zhjy.app.modules.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tangmo.zhjy.app.modules.bean.AppTwoClassify;
import com.tangmo.zhjy.app.utils.AuthorizeUtil;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.AppMyChannelBean;
import com.tangmo.zhjy.app.modules.bean.AppUserBean;
import com.tangmo.zhjy.app.modules.dao.AppMyChannelBeanMapper;
import com.tangmo.zhjy.app.modules.dao.AppTwoClassifyMapper;
import com.tangmo.zhjy.app.modules.dao.AppUserBeanMapper;
import com.tangmo.zhjy.app.modules.dto.AppUserDto;
import com.tangmo.zhjy.app.modules.service.AppUserService;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserBeanMapper appUserBeanMapper;

	@Autowired
	private AppMyChannelBeanMapper appMyChannelBeanMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
	private AppTwoClassifyMapper appTwoClassifyMapper;
	private final static String USER_PASSWORD = "123456";


	private Logger logger = Logger.getLogger(AppUserServiceImpl.class);

	/* (非 Javadoc)   
	 * <p>Title: addUser</p>   
	 * <p>Description: </p>   
	 * @param appUserDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppUserService#addUser(com.tangmo.zhjy.modules.app.dto.AppUserDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result addUser(AppUserDto appUserDto){
		try {
			if(appUserBeanMapper.findByPhone(appUserDto.getPhone())==null){
				AppUserBean appUserBean = new AppUserBean(appUserDto.getPhone(),
						"",
						"男",
						appUserDto.getPassword(),
						appUserDto.getProvince(),
						appUserDto.getCity());

				int row = appUserBeanMapper.insert(appUserBean);
				System.out.println("jasndjan===="+row);
				return new Result(ResultCode.SUCCESS);
			}
		} catch (Exception e) {
		    e.printStackTrace();
			logger.info("注册用户账号异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		return new Result(ResultCode.SYSTEM_ADMIN_USER_ERROR);
	}


	@Override
	public Result smsLogin(String phone, String code, HttpSession session) {
//		try{
//			String smsCode=(String)session.getAttribute("code");
//			String smsPhone=(String)session.getAttribute("phone");
//			Date date=(Date)session.getAttribute("date");
//			if( Math.floor(new Date().getTime()/1000)- Math.floor(date.getTime()/1000)>60){
//				return new Result(ResultCode.SMSTIME_ERROR);
//			}else{
//				if(!smsPhone.equals(phone)){
//					return new Result(ResultCode.PHONE_ERROR);
//				}
//				if(smsCode.equals(code) && smsPhone.equals(phone)){
					AppUserBean appUserBean = appUserBeanMapper.findByPhone(phone);
					if(appUserBean==null){
                        AppUserBean appUserBeans = new AppUserBean(phone,
                                "",
                                "男",
                                passwordEncoder.encode(USER_PASSWORD),
                                null,
                                null);
                        appUserBeanMapper.insert(appUserBeans);
                        appUserBean = appUserBeanMapper.findByPhone(phone);
					}
//					Map map = new HashMap();
//					map.put("phone",appUserBean.getPhone());
//					JSONObject josn=com.alibaba.fastjson.JSONObject.parseObject(AuthorizeUtil.getToken(appUserBean.getPhone(),USER_PASSWORD));
//					map.put("access_token",josn.get("access_token"));
					return new Result(ResultCode.SUCCESS,appUserBean.getId());
//				}else{
//					return new Result(ResultCode.SMS_ERROR);
//				}
//			}
//		}catch(Exception e){
//			logger.info("短信验证登录异常"+e);
//			return new Result(ResultCode.WEAK_NET_WORK,"网络异常");
//		}
	}

    @Override
    public Result smsLoginByPass(String phone, String password) {
        AppUserBean appUserBean = appUserBeanMapper.findByPhone(phone);
        if(appUserBean==null){
            return new Result(ResultCode.USER_ERROR);
        }
        if(!passwordEncoder.matches(password,appUserBean.getPassword())){
            return new Result(ResultCode.PASSWORD_ERROR);
        }
        return new Result(ResultCode.SUCCESS,appUserBean.getId());
    }

    @Override
    public Result getUserById(Integer id) {
	    if(id == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        AppUserBean appUserBean = appUserBeanMapper.selectByPrimaryKey(id);
	    if(appUserBean == null){
            return new Result(ResultCode.USER_ERROR);
        }
        return new Result(ResultCode.SUCCESS,appUserBean);
    }

    /* (非 Javadoc)
	 * <p>Title: saveChannel</p>   
	 * <p>Description: </p>   
	 * @param userId
	 * @param twoClassifyid
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppUserService#saveChannel(java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result saveChannel(Integer userId,Integer twoClassifyid){
		try {
			if(findExist(userId,twoClassifyid)){
                AppMyChannelBean appMyChannelBean1 = appMyChannelBeanMapper.selectByValue(userId,twoClassifyid);
                if(appMyChannelBean1 != null){
                    return new Result(ResultCode.CHANNEL_ERROR);
                }
				AppMyChannelBean appMyChannelBean = new AppMyChannelBean();
				appMyChannelBean.setUserId(userId);
				appMyChannelBean.setTwoClassifyid(twoClassifyid);
				appMyChannelBeanMapper.insert(appMyChannelBean);

                List<AppMyChannelBean> appMyChannelBeans = appMyChannelBeanMapper.getMyChannel(userId);
                if(appMyChannelBeans.size() > 3){
                    for (int i = 3;i < appMyChannelBeans.size();i++){
                        appMyChannelBeanMapper.delByPrimaryKey(appMyChannelBeans.get(i).getId());
                    }
                }
				return new Result(ResultCode.SUCCESS);
			}else{
				return new Result(ResultCode.PAPAMETE_ERROR);
			}
		} catch (Exception e) {
		    e.printStackTrace();
			logger.info("用户增加频道异常"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

    @Override
    public Result getMyChannel(Integer userId) {
        try {
            if(userId != null){
                List<AppMyChannelBean> list = appMyChannelBeanMapper.getMyChannel(userId);
                List<AppTwoClassify> appTwoClassifies = appTwoClassifyMapper.findByAppClassById();
                List<AppTwoClassify> appTwoClassifyList = new ArrayList<>();
                if(list == null){
                    for (int i = 0;i < 3;i++){
                        appTwoClassifyList.add(appTwoClassifies.get(i));
                    }
                }else{
                    for (int i = 0;i < list.size();i++){
                        for (int j = 0;j < appTwoClassifies.size();j++){
                            if(list.get(i).getTwoClassifyid() == appTwoClassifies.get(j).getId()){
                                appTwoClassifyList.add(appTwoClassifies.get(j));
                                break;
                            }
                        }
                    }
                    if(list.size() != 3){
                        int a = 0;
                        for (int i = 0;i < appTwoClassifies.size();i++){
                            for (int j = 0;j < list.size();j++){
                                a++;
                                if(appTwoClassifies.get(i).getId() == list.get(j).getTwoClassifyid()){
                                    a = 0;
                                    break;
                                }
                                if(a == list.size()){
                                    appTwoClassifyList.add(appTwoClassifies.get(i));
                                }
                            }
                            if(appTwoClassifyList.size() == 3){
                                break;
                            }
                        }
                    }
                }
                return new Result(ResultCode.SUCCESS,appTwoClassifyList);
            }else{
                return new Result(ResultCode.PAPAMETE_ERROR);
            }
        } catch (Exception e) {
            logger.info("获取关联频道"+e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }

    /* (非 Javadoc)
	 * <p>Title: removeChannel</p>   
	 * <p>Description: </p>   
	 * @param userId
	 * @param twoClassifyid
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppUserService#removeChannel(java.lang.Integer, java.lang.Integer)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result removeChannel(Integer userId,Integer twoClassifyid){
		try {
			if(findExist(userId,twoClassifyid)){
				appMyChannelBeanMapper.deleteByPrimaryKey(userId, twoClassifyid);
				return new Result(ResultCode.SUCCESS);
			}else{
				return new Result(ResultCode.PAPAMETE_ERROR);
			}
		} catch (Exception e) {
			logger.info("用户删除频道异常"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	 * <p>Title: modify</p>   
	 * <p>Description: </p>   
	 * @param appUserDto
	 * @return   
	 * @see com.tangmo.zhjy.modules.app.service.impl.AppUserService#modify(com.tangmo.zhjy.modules.app.dto.AppUserDto)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result modify(AppUserDto appUserDto){
		Integer userId = appUserDto.getId();
		if(userId!=null){
			try {
				if(appUserBeanMapper.selectByPrimaryKey(userId)!=null){
					AppUserBean appUserBean = new AppUserBean(appUserDto.getId(),
							appUserDto.getPhone(),
							appUserDto.getNikeName(),
							appUserDto.getSex(),
							appUserDto.getPassword(),
							appUserDto.getRealName(),
							appUserDto.getIdentityId(),
							appUserDto.getIsRealname(),
							appUserDto.getIsStore(),
							appUserDto.getFreeze(),
							appUserDto.getProvince(),
							appUserDto.getCity(),
							appUserDto.getFront(),
							appUserDto.getVerso());
					appUserBean.setHeadImage(appUserDto.getHeadImage());
					appUserBeanMapper.updateByPrimaryKeySelective(appUserBean);
					return new Result(ResultCode.SUCCESS);
				}
			} catch (Exception e) {
				logger.info("用户信息修改异常："+e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return new Result(ResultCode.WEAK_NET_WORK);
			}
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}
	/**
	 * 5.判断对象是否存在
	 */
	private boolean findExist(Integer userId,Integer twoClassifyid){

		if(userId != null){
			if(appUserBeanMapper.selectByPrimaryKey(userId)==null){
				return false;
			}
		}else{
            return false;
        }

		if(twoClassifyid!=null){
			if(appTwoClassifyMapper.selectByPrimaryKey(twoClassifyid)==null){
                return false;
			}
		}else{
            return false;
        }

		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByPhone(String phone) {
		try {
			return new Result(ResultCode.SUCCESS,appUserBeanMapper.findByPhone(phone));
		} catch (Exception e) {
			logger.info("根据手机号查找异常"+e);
			return new Result(ResultCode.WEAK_NET_WORK,"网络异常");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result updatePassword(String phone,String password){
		try {
            AppUserBean appUserBeans = appUserBeanMapper.findByPhone(phone);
			AppUserBean appUserBean = new AppUserBean();
			if(appUserBeans != null){
				appUserBean.setPassword(password);
                appUserBean.setId(appUserBeans.getId());
				appUserBeanMapper.updateByPrimaryKeySelective(appUserBean);
			}else{
				return new Result(100,"账号不存在",null);
			}
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("找回密码接口异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}


    @Override
    public Result updPassword(Integer id, String password, String newPassword) {
        try {
            AppUserBean appUserBean = appUserBeanMapper.selectByPrimaryKey(id);
            if(appUserBean == null){
                return new Result(ResultCode.FAIL);
            }

            if(!passwordEncoder.matches(password,appUserBean.getPassword())){
                return new Result(111,"原密码输入有误",null);
            }
            if(password.equals(newPassword)){
                return new Result(111,"新旧密码不能一致",null);
            }
            AppUserBean appUserBean1 = new AppUserBean();
            appUserBean1.setId(id);
            appUserBean1.setPassword(passwordEncoder.encode(newPassword));
            appUserBeanMapper.updateByPrimaryKeySelective(appUserBean);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            logger.info("修改密码接口异常："+e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByUserAndPassword(String phone, String password,PasswordEncoder passwordEncoder) {
		try {
			AppUserBean appUserBean=appUserBeanMapper.findByPhone(phone);
			if(appUserBean!=null){
				if(passwordEncoder.matches(password, appUserBean.getPassword())){
					return new Result(ResultCode.SUCCESS,appUserBean);
				}
			}
		} catch (Exception e) {
			return new Result(ResultCode.WEAK_NET_WORK);
		}
		return new Result(408,"用户名密码输入有误",null);
	}

}
