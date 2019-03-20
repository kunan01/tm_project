package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.SysUserBean;
import com.tangmo.zhjy.system.modules.dto.SysCommunitySysuserDto;

public interface SysUserService {

    /**
     * @param @return 设定文件
     * @return Result    返回类型
     * @throws
     * @Title: saveSysUser
     * @Description: TODO(添加管理员)
     */
    @SuppressWarnings("rawtypes")
    Result saveSysUser(SysUserBean sysUserBean);

    /**
     * @param @param  sysUserBean
     * @param @return 设定文件
     * @return Result    返回类型
     * @throws
     * @Title: updateSysUser
     * @Description: TODO(修改管理员信息)
     */
    @SuppressWarnings("rawtypes")
    Result updateSysUser(SysUserBean sysUserBean);

    /**
     * @param @param  id
     * @param @return 设定文件
     * @return Result    返回类型
     * @throws
     * @Title: findById
     * @Description: TODO(根据ID查找)
     */
    @SuppressWarnings("rawtypes")
    Result findById(Integer id);

    /**
     * @param @param  id
     * @param @return 设定文件
     * @return Result    返回类型
     * @throws
     * @Title: deleteSysUser
     * @Description: TODO(删除管理员)
     */
    @SuppressWarnings("rawtypes")
    Result deleteSysUser(SysUserBean sysUserBean);

    /**
     * @param @param  name
     * @param @return 设定文件
     * @return SysUserBean    返回类型
     * @throws
     * @Title: selectByUserName
     * @Description: TODO(根据账号查询)
     */

    SysUserBean selectByUserName(String name);

    /**
     * @param @param  jobNumber
     * @param @return 设定文件
     * @return selectByJobNumber    返回类型
     * @throws
     * @Title: selectByUserName
     * @Description: TODO(根据工号查询)
     */

    SysUserBean selectByJobNumber(String jobNumber);

    /**
     * @param @param  userId
     * @param @return 设定文件
     * @return Result    返回类型
     * @throws
     * @Title: findSelectUserId
     * @Description: TODO(获取管理员详情信息)
     */
    @SuppressWarnings("rawtypes")
    Result findSelectUserName(String username);

    /**
     * @param @param  username
     * @param @return 设定文件
     * @return Result    返回类型
     * @throws
     * @Title: findByPage
     * @Description: TODO(分页查询)
     */
    @SuppressWarnings("rawtypes")
    Result findByPage(String name, Integer pageNo, Integer pageSize);

    @SuppressWarnings("rawtypes")
    Result allotCommunity(SysCommunitySysuserDto sysCommunitySysuserDto);

    @SuppressWarnings("rawtypes")
    Result queryCommunity(Integer sysUserId);

    /**
     * 更新用户实名认证状态
     *
     * @param userId
     * @param status
     * @return
     */
    Result changeVerifyState(Integer userId, String status);

    /**
     * 根据是否实名查询用户实名验证信息
     *
     * @param status
     * @return
     */
    Result queryVerifyInfo(String status,Integer pageNo,Integer pageSize);

    /**
     * 分页查询所有app用户信息
     * @return
     */
    Result selectByPage(String name, Integer pageNo, Integer pageSize);

    /**
     * 根据App用户id查询用户详情
     * @return
     */
    Result selectByAppUserId(Integer userId);

    /**
     * 根据实名用户id查询已实名信息
     *
     * @param userId
     * @return
     */
    Result getVerifyByUserId(Integer userId);

    Result getNotAuditAll();

    //统计用户
    Result statisticalUser();



}