package com.tangmo.zhjy.system.modules.dao;

import java.util.List;
import java.util.Map;

import com.tangmo.zhjy.system.modules.bean.AppUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tangmo.zhjy.system.modules.bean.SysUserBean;

@Mapper
public interface SysUserBeanMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserBean record);

    int insertSelective(SysUserBean record);

    SysUserBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserBean record);

    int updateByPrimaryKey(SysUserBean record);
    
    SysUserBean selectByUserName(@Param("username")String username);

    SysUserBean selectByJobNumber(@Param("jobNumber")String jobNumber);
    
    List<SysUserBean> findUserId(@Param("users")List<Integer> users);
    
    List<SysUserBean> findByPage(@Param("username")String username);

    /**
     * 更新用户商家状态
     * @param userId
     * @return
     */
    int updateUserStatus(@Param("userId") Integer userId,@Param("status") Byte status);

    /**
     * 更新用户实名认证状态
     * @param userId
     * @param status
     * @return
     */
    int updateUserVerify(@Param("userId") Integer userId,@Param("status") String status);

    /**
     * 根据是否实名认证筛选用户信息
     * @param status
     * @return
     */
    List<Map<String,Object>> selectByVerifyStatus(String status);

    /**
     * 查询实名用户认证数量
     * @param isRealName
     * @return
     */
    Integer selectByVerifyStatusCount(@Param("isRealName") Integer isRealName);

    /**
     * 根据实名用户id查询已实名信息
     * @param userId
     * @return
     */
    Map<String,Object> getVerifyByUserId(@Param("userId") Integer userId);
}