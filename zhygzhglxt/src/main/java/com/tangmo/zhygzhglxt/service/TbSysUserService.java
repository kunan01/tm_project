package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbSysUser;
import com.tangmo.zhygzhglxt.entity.dto.TbDriverVerifyDto;
import com.tangmo.zhygzhglxt.entity.dto.UserDto;
import com.tangmo.zhygzhglxt.utility.Result;

import javax.servlet.http.HttpSession;

/**
 * Created by chengge on 2018/10/22.
 */
public interface TbSysUserService {

    /**
     * 注册乘客用户信息
     *
     * @param userDto 用户对象dto
     * @return
     */
    Result addAppUser(UserDto userDto);

//    /**
//     * 注册司机用户信息
//     *
//     * @param tbDriverVerifyDto 司机用户信息对象dto
//     * @return
//     */
//    Result saveDriverUser(TbDriverVerifyDto tbDriverVerifyDto);

    /**
     * 5.短信验证登录
     */
    @SuppressWarnings("rawtypes")
    Result smsLogin(String phone, String code, HttpSession session, String type);

    /**
     * 根据用户的唯一标识code获取用户的基本信息
     */
    @SuppressWarnings("rawtypes")
    Result getUserInfoByCode(String userCode);

    /**
     * 登录
     *
     * @param userDto
     * @return
     */
    Result login(UserDto userDto);

    /**
     * 通过手机号查询
     *
     * @param userPhone
     * @return
     */
    Result selUserByPhone(String userPhone);


    /**
     * 通过用户的唯一标识获取token
     *
     * @param userCode
     * @return
     */
    String selectToken(String userCode);

    /**
     * 重置密码
     *
     * @param userDto
     * @return
     */
    Result resetPwd(UserDto userDto);

    /**
     * 修改密码
     *
     * @param userDto
     * @return
     */
    Result changePwd(UserDto userDto);

    /**
     * 修改支付密码
     *
     * @param userDto
     * @return
     */
    Result changePayPwd(UserDto userDto);

    /**
     * 设置支付密码
     *
     * @param userDto
     * @return
     */
    Result setPayPwd(UserDto userDto);


    /**
     * 修改用户信息
     *
     * @param userDto 用户对象Dto
     * @return
     */
    Result changeUser(UserDto userDto);

    /**
     * 修改手机号
     *
     * @param userId    用户id
     * @param userPhone 手机号
     * @return
     */
    Result updateUserPhone(String userId, String userPhone);

    /**
     * 查询所有用户可分页（可根据状态，用户身份查找）
     */
    Result selAllUser(String name, String userStatus, Integer pageSize, Integer pageNo);

    /**
     * 根据用户的唯一标识code概念性删除
     */
    Result delByUserCode(String code);

    /**
     * 根根据用户的唯一标识code修改用户的状态（冻结1或者解除冻结0）
     */
    Result updateStateByUserCode(String code, String state);


    /**
     * 后台修改用户信息
     */
    Result updateSysUser(TbSysUser tbSysUser);


    /**
     * 后台添加用户信息
     */
    Result saveSysUser(TbSysUser tbSysUser);

    /**
     * 根据用户唯一标识查找用户详情信息
     */
    Result jtQueryById(String code);

    /**
     * 查询所有车主
     *
     * @param name       模糊参数 真实姓名，用户名，昵称
     * @param userStatus 用户身份 1乘客用户，2车主，0管理员
     * @param parmId     车辆类型id
     * @param parmTypeId 服务类型id
     * @param pageNo     当前页
     * @param pageSize   每页几条
     * @return
     */
    Result jtQueryList(String name, String userStatus, String parmId, String parmTypeId, Integer pageNo, Integer pageSize);

    /**
     * 后台统计所有用户
     */
    Result selAllUserCount();

    /**
     * 后台按年统计所有用户
     */
    Result selAllUserCountByYear(String year);

    /**
     * 后台按月统计所有用户
     */
    Result selAllUserCountByMonth(String yearMonth);

    /**
     * 后台按开始时间和结束时间查询每一天的用户数
     */
    Result selAllUserCountByDate(String startDate, String endDate, Integer pageSize, Integer pageNo);


}
