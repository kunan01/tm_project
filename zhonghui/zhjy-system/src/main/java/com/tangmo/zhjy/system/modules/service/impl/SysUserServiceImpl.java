/**     
 * @Title: SysUserServiceImpl.java   
 * @Package com.tangmo.zhjy.modules.sys.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月10日 上午11:12:03   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tangmo.zhjy.system.modules.bean.*;
import com.tangmo.zhjy.system.modules.dao.*;
import com.tangmo.zhjy.system.modules.vo.CommodityVO;
import com.tangmo.zhjy.system.modules.vo.SimpleShopGoodsVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.dto.SysCommunitySysuserDto;
import com.tangmo.zhjy.system.modules.service.SysUserService;

import javax.annotation.Resource;

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
 * @Description : TODO(管理员模块业务逻辑层)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月10日 上午11:12:03
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserBeanMapper sysUserBeanMapper;

	@Autowired
	private AppUserBeanMapper appUserBeanMapper;

	@Autowired
	private SysRoleBeanMapper sysRoleBeanMapper;
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysOperationMapper sysOperationMapper;
	@Autowired
	private SysRelatierolOperationMapper sysRelatierolOperationMapper;
	@Autowired
	private SysRoleUserBeanMapper sysRoleUserBeanMapper;
	@Autowired
	private SysCommunitySysuserBeanMapper sysCommunitySysuserBeanMapper;
	@Autowired
	private SysCommunityBeanMapper sysCommunityBeanMapper;

    @Autowired
    private SysInformationMapper SysInformationMapper;

    @Autowired
    private SysShopMapper sysShopMapper;

    @Autowired
    private ShopGoodsDao shopGoodsDao;

    @Autowired
    private CommodityDao commodityDao;


	private Logger logger = Logger.getLogger(SysUserServiceImpl.class);



	/* (非 Javadoc)   
	 * <p>Title: saveSysUser</p>   
	 * <p>Description: </p>   
	 * @param sysUserBean
	 * @return   
	 * @see com.tangmo.zhjy.modules.sys.service.impl.SysUserService#saveSysUser(com.tangmo.zhjy.modules.sys.bean.SysUserBean)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result saveSysUser(SysUserBean sysUserBean){
		if(sysUserBean.getRoles()!=null){
			try {
				if(sysRoleBeanMapper.batchRole(sysUserBean.getRoles())==sysUserBean.getRoles().size()){
					sysUserBeanMapper.insert(sysUserBean);
					sysRoleUserBeanMapper.insert(sysUserBean.getId(),sysUserBean.getRoles());
					return new Result(ResultCode.SUCCESS);
				}
			} catch (Exception e) {
				System.out.println("添加管理员接口异常："+e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		return new Result(ResultCode.PAPAMETE_ERROR);
	}

	/* (非 Javadoc)   
	 * <p>Title: updateSysUser</p>   
	 * <p>Description: </p>   
	 * @param sysUserBean
	 * @return   
	 * @see com.tangmo.zhjy.modules.sys.service.impl.SysUserService#updateSysUser(com.tangmo.zhjy.modules.sys.bean.SysUserBean)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result updateSysUser(SysUserBean sysUserBean){
		try {
			if(sysUserBean.getRoles()!=null && sysUserBean.getRoles().size()>0){
				if(sysRoleBeanMapper.batchRole(sysUserBean.getRoles())==sysUserBean.getRoles().size()){
					sysRoleUserBeanMapper.insert(sysUserBean.getId(), sysUserBean.getRoles());
				}
			}
			sysUserBeanMapper.updateByPrimaryKeySelective(sysUserBean);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			System.out.println("修改管理员信息异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/* (非 Javadoc)   
	 * <p>Title: findById</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.tangmo.zhjy.modules.sys.service.impl.SysUserService#findById(java.lang.Integer)   
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findById(Integer id){
		SysUserBean  sysUserBean = sysUserBeanMapper.selectByPrimaryKey(id);
		return new Result(ResultCode.SUCCESS,sysUserBean);
	}

	/* (非 Javadoc)   
	 * <p>Title: deleteSysUser</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.tangmo.zhjy.modules.sys.service.impl.SysUserService#deleteSysUser(java.lang.Integer)   
	 */  
	@SuppressWarnings("rawtypes")
	@Override
	public Result deleteSysUser(SysUserBean sysUserBean){
		try {
			sysUserBean.setIsShow(1);
			sysUserBeanMapper.updateByPrimaryKeySelective(sysUserBean);
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			System.out.println("删除管理员接口异常："+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}
	/**
	 * 
	 * @Title: selectByUserName   
	 * @Description: TODO(根据账号查询)   
	 * @param @param name
	 * @param @return    设定文件   
	 * @return SysUserBean    返回类型   
	 * @throws
	 */
	public SysUserBean selectByUserName(String name){
		return sysUserBeanMapper.selectByUserName(name);
	}

	public SysUserBean selectByJobNumber(String jobNumber){
		return sysUserBeanMapper.selectByJobNumber(jobNumber);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findSelectUserName(String username){
		//1.查询关联角色信息   (1,超级管理员)
		List<SysRoleBean> sysRoleBeans = sysRoleBeanMapper.findByRelevanceRole(username);

		//2.查询关联菜单信息    ()
		List<SysPermission> sysPermissions = sysPermissionMapper.findByPermissionAndOperation(sysRoleBeans);
		//4.判断用户是否拥有角色
		if(sysRoleBeans!=null && sysRoleBeans.size()!=0){
			//5.循环遍历角色
			for (SysRoleBean sysRoleBean : sysRoleBeans) {
				
				List<SysRolePermission> isBePermission = sysRolePermissionMapper.selectByRoleId(sysRoleBean.getId());
				List<SysPermission> sysPermissions2 = new ArrayList<SysPermission>();
				//6.判断该角色是否拥有菜单
				if(isBePermission.size()!=0){
					//7.循环遍历拥有菜单集合
					for (SysPermission sysPermission : sysPermissions) {
						if(sysPermission.getRoleId()==sysRoleBean.getId()){
							//根据角色和菜单中间表记录编号查找拥有操作记录ID
							List<SysRelatierolOperation> sysRelatierolOperations=sysRelatierolOperationMapper.findByRelatierolId(sysPermission.getSysRolePermissionId());
							List<Integer> operationIds = new ArrayList<Integer>();
							for (SysRelatierolOperation SysRelatierolOperation : sysRelatierolOperations) {
								operationIds.add(SysRelatierolOperation.getId());
							}
							if(operationIds.size()!=0){
								sysPermission.setSysOperations(sysOperationMapper.batchSelect(operationIds));
							}
							sysPermissions2.add(sysPermission);
						}

					}
				}
				sysRoleBean.setSysPermissions(sysPermissions2);

			}
		}

		return new Result(ResultCode.SUCCESS,sysRoleBeans);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result findByPage(String name, Integer pageNo, Integer pageSize) {
		try {
			if(name!=null && !"".equals(name)){
				name="%"+name+"%";
			}else{
				name="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			List<SysUserBean> sysUsers=sysUserBeanMapper.findByPage(name);
			if(sysUsers!=null){
				for (SysUserBean sysUserBean : sysUsers) {
					String roleNam="";
					List<Integer> sysRoleIds = new ArrayList<Integer>();
					//1.查询关联角色信息
					List<SysRoleBean> sysRoleBeans = sysRoleBeanMapper.findByRelevanceRole(sysUserBean.getUsername());
					if(sysRoleBeans!=null){
						for (SysRoleBean sysRoleBean : sysRoleBeans) {
							sysRoleIds.add(sysRoleBean.getId());
							roleNam+=sysRoleBean.getName()+",";
						}
					}
					sysUserBean.setRoleName(roleNam);
					sysUserBean.setRoles(sysRoleIds);
				}
				return new Result(ResultCode.SUCCESS,new PageInfo(sysUsers));
			}
			return new Result(ResultCode.SUCCESS,new ArrayList<SysUserBean>());
		} catch (Exception e) {
			logger.info("管理用户分页接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result selectByPage(String name, Integer pageNo, Integer pageSize) {
		try {
			if(name!=null && !"".equals(name)){
				name="%"+name+"%";
			}else{
				name="%%";
			}
			if(pageSize!=null && pageNo!=null){
				PageHelper.startPage(pageNo, pageSize);
			}
			List<AppUserBean> sysUsers= appUserBeanMapper.selectByPage(name);
			System.out.println(sysUsers);
			return new Result(ResultCode.SUCCESS,new PageInfo(sysUsers));
//            return new Result(ResultCode.SUCCESS,sysUsers);
		} catch (Exception e) {
			logger.info("app用户分页接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result selectByAppUserId(Integer userId) {
		try {
			AppUserBean sysUser= appUserBeanMapper.selectByAppUserId(userId);
			return new Result(ResultCode.SUCCESS,sysUser);
		} catch (Exception e) {
			logger.info("查找app用户详情接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	/**
	 * 
	 * @Title: allotCommunity   
	 * @Description: TODO(c)   
	 * @param @param sysCommunitySysuserDto
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public Result allotCommunity(SysCommunitySysuserDto sysCommunitySysuserDto){

		try {
			sysCommunitySysuserBeanMapper.deleteBySysUserId(sysCommunitySysuserDto.getSysUserId());
			sysCommunitySysuserBeanMapper.insert(sysCommunitySysuserDto.getSysUserId(), sysCommunitySysuserDto.getCommunityId());
			return new Result(ResultCode.SUCCESS);
		} catch (Exception e) {
			logger.info("给管理员分配社区接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}

	}
	/**
	 * 
	* @Title: queryCommunity   
	* @Description: TODO(查询管理员信息以及关联社区)   
	* @param @param sysUserId
	* @param @return    设定文件   
	* @return Result    返回类型   
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Result queryCommunity(Integer sysUserId){
		try {
			SysUserBean sysUserBean=sysUserBeanMapper.selectByPrimaryKey(sysUserId);	
			sysUserBean.setCommunitysIds(sysCommunityBeanMapper.findBySysUserId(sysUserBean.getId()));
			return new Result(ResultCode.SUCCESS,sysUserBean);
		} catch (Exception e) {
			logger.info("查询管理员信息接口异常："+e);
			return new Result(ResultCode.WEAK_NET_WORK);
		}
	}

	@Override
	public Result changeVerifyState(Integer userId, String status) {
		sysUserBeanMapper.updateUserVerify(userId, status);
		return new Result(ResultCode.SUCCESS);
	}

	@Override
	public Result queryVerifyInfo(String status,Integer pageNo,Integer pageSize) {
	    PageHelper.startPage(pageNo,pageSize);
        List<Map<String,Object>> mapList = sysUserBeanMapper.selectByVerifyStatus(status);
		return new Result(ResultCode.SUCCESS,new PageInfo(mapList));
	}

	@Override
	public Result getVerifyByUserId(Integer userId) {
		return new Result(ResultCode.SUCCESS,sysUserBeanMapper.getVerifyByUserId(userId));
	}

    @Override
    public Result getNotAuditAll() {
	    Map<String,Object> map = new HashMap<>();

	    //商家未审核
        List<ShopVerify> list = sysShopMapper.selectPageByState(Byte.parseByte("0"));

        //实名认证未审核
        List<Map<String,Object>> mapList = sysUserBeanMapper.selectByVerifyStatus("1");

        //文章未审核
        List<SysInformation>  appInformations = SysInformationMapper.getInfoCheckedList("%%");

        //同城未审核
        List<SimpleShopGoodsVO> SimpleShopGoodss= shopGoodsDao.selectByState(1,null);

        //商品未审核
        List<CommodityVO> commodityvos =commodityDao.selectList(3,0,null);

        if(list != null){
            map.put("商家未审核",list.size());
        }

        if(mapList != null){
            map.put("实名认证未审核",mapList.size());
        }

        if(appInformations != null){
            map.put("文章未审核",appInformations.size());
        }

        if(SimpleShopGoodss != null){
            map.put("同城未审核",SimpleShopGoodss.size());
        }

        if(commodityvos != null){
            map.put("商品未审核",commodityvos.size());
        }

        return new Result(ResultCode.SUCCESS,map);
    }

    @Override
    public Result statisticalUser() {
	    Map<String,Integer> map = new HashMap<>();
        map.put("UserCount",sysUserBeanMapper.selectByVerifyStatusCount(null)); //用户总数
        map.put("NoRealNameUserCount",sysUserBeanMapper.selectByVerifyStatusCount(0));//未实名用户数量
        map.put("ToAuditUserCount",sysUserBeanMapper.selectByVerifyStatusCount(1));//待审核实名用户数量
        map.put("ThroughUserCount",sysUserBeanMapper.selectByVerifyStatusCount(2));//已实名用户数量
        map.put("RejectedUserCount",sysUserBeanMapper.selectByVerifyStatusCount(3));//驳回实名用户数量
        map.put("ArticleCount",SysInformationMapper.getInfoCount(null));//全部文章数量
        map.put("NotAuditArticleCount",SysInformationMapper.getInfoCount(1));//审核通过文章数量
        map.put("ThrougharticleCount",SysInformationMapper.getInfoCount(2));//审核未通过文章数量
        map.put("ArticleVisitCount",SysInformationMapper.getInfoByClassifyId(null));//文章总游览量
        map.put("HandleAffairsVisitCount",SysInformationMapper.getInfoByClassifyId(1));//办事指南游览量
        map.put("WikipediaVisitCount",SysInformationMapper.getInfoByClassifyId(2));//百科知识游览量
        map.put("ComprehensiveVisitCount",SysInformationMapper.getInfoByClassifyId(3));//综合列表浏览量
        map.put("CommunityVisitCount",SysInformationMapper.getInfoByClassifyId(4));//社区服务游览量
        return new Result(ResultCode.SUCCESS,map);
    }


}
