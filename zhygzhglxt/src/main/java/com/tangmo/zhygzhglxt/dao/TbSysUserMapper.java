package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.TbSysUser;
import com.tangmo.zhygzhglxt.entity.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface TbSysUserMapper {


    /**
     * This method was generated by MyBatis Generator.
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     */
    int insert(TbSysUser record);

    /**
     * This method was generated by MyBatis Generator.
     */
    int insertSelective(TbSysUser record);

    /**
     * 根据用户的身份查询
     */
    List<TbSysUser> selAllUserByStatus();

    /**
     * This method was generated by MyBatis Generator.
     */
    TbSysUser selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKeySelective(TbSysUser record);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKey(TbSysUser record);

    /**
     * 改变司机的余额
     */
    int updateBanlanceByUserCode(@Param("userCode") String userCode, @Param("price") BigDecimal price);

    /**
     * 根据用户的唯一标识修改token
     */
    int updateToken(@Param("token") String token, @Param("code") String code);

    /**
     * 根据用户的唯一标识修改用户的身份
     */
    int updateStatusByCode(@Param("status") String status, @Param("code") String code);

    /**
     * 根据用户的唯一标识查找用户
     */
    TbSysUser selectByCode(@Param("code") String code);

    /**
     * 根据用户名查找用户
     */
    TbSysUser selectByUserName(@Param("userName") String userName);

    /**
     * 根据用户的唯一标识获取token
     */
    String selectToken(@Param("code") String code);


    /**
     * 增加用户信息
     */
    int insertAppUser(UserDto userDto);

    /**
     * 根据电话验证码重置密码
     */
    int updatePwdByPhone(UserDto userDto);

    /**
     * 修改用户信息
     */
    int updateByAppCode(UserDto userDto);

    /**
     * 修改用户手机号
     */
    int updateUserPhone(@Param("phone") String phone, @Param("code") String code);

    /**
     * 修改用户密码
     */
    int updatePwd(@Param("password") String password, @Param("code") String code);

    /**
     * 修改用户支付密码
     */
    int updatePayPwd(@Param("payPassword") String payPassword, @Param("code") String code);


    /**
     * 根据手机号查询用户
     */
    TbSysUser selectByPhone(@Param("phone") String phone);

    /**
     * 根据用户的唯一标识code修改用户的状态
     */
    int updateStateByUserCode(@Param("code") String code, @Param("state") String state);

    /**
     * 根据用户的唯一标识code概念性删除
     */
    int deleteByCode(@Param("code") String code);

    /**
     * 根据手机号查询用户
     */
    List<TbSysUser> selAllUser(@Param("name") String name, @Param("userStatus") String userStatus);

    /**
     * 根据用户唯一标识查找用户详情信息
     */
    TbSysUser jtQueryById(@Param("code") String code);

    /**
     * 查询所有车主
     *
     * @param name       模糊参数 真实姓名，用户名，昵称
     * @param userStatus 用户身份 1乘客用户，2车主，0管理员
     * @param parmId     车辆类型id
     * @param parmTypeId 服务类型id
     * @return
     */
    List<TbSysUser> jtQueryList(@Param("name") String name, @Param("userStatus") String userStatus, @Param("parmId") String parmId, @Param("parmTypeId") String parmTypeId);


    /**
     * 后台统计用户数量
     */
    Map selAllUserCount();

    /**
     * 后台按年统计用户数量
     */
    List<Map> selAllUserCountByYear(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Map> selAllUserCountByMonth(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Map> selAllUserCountByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 后台查询每月的天
     */
    int selectByDay(@Param("yearMonth") String yearMonth);

}