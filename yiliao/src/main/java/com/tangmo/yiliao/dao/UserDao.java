package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    //查询验证码记录
    VerificationCode getcode(String mobile);

    //添加验证码记录
    int insertcode(String mobile);

    //查询验证码
    Integer getcodeConsulting(String mobile);

    //添加
    int insertcodeConsulting(String mobile);

    //修改
    int updatecodeConsultinge(String mobile);

    //修改验证码记录
    int updatecode(@Param("type") Integer type,@Param("mobile") String mobile);

    //通过手机号查询用户
    User selectByMobile(String mobile);

    //通过登录名和密码查询用户
    User selectByCard(String Card);

    //通过用户id获取用户电话
    String getUserPhoneById(@Param("userId") String userId);

    //通过用户id查询用户页面基本资料
    User getBasicInformationById(String userId);

    //通过用户id查询用户密码
    String getPwdById(String userId);

    //添加用户
    int insertUser(User user);

    //增加用户消息
    int insertUserMessage(@Param("userId") String userId);

    //增加用户默认关系
    int insertUserBet(@Param("userId") String userId);

    //增加用户上级关系
    int insertOnUserBet(@Param("userId") String userId,@Param("onUserId") String onUserId);

    //增加用户下级关系
    int insertBelowUserBet(@Param("userId") String userId,@Param("belowUserId") String belowUserId);

    //获取用户下级关系
    RelationShip getUserBet(@Param("userId") String userId);

    //查询积分规则 type:0用户注册 1邀请用户 2购买积分 3积分提现 4单次提现最低金额 5用户累计多少积分方可提现 6单次提现最大金额 7单日最多提现次数
    Integer selectIntegralRules(@Param("type") Byte type);

    //修改密码
    int updateUserPwd(@Param("userId") String userId,@Param("pwd") String pwd);

    //获取用户个人信息
    User getPersonalInformation(String userId);

    //修改用户个人信息
    int updUser(User user);

    //获取用户积分余额
    Integer getUserIntegral(String userId);

    //获取用户累计积分总额
    Integer getUserIntegralAll(String userId);

    //清空用户足迹
    int clearUserFootprint(String userId);

    //添加用户足迹
    int addUserFootprint(Footprint footprint);

    //通过条件判断当前用户是否有次足迹
    int getUserFootprintBytype(@Param("userId") String userId,@Param("fType") Byte fType,@Param("typeId") String typeId);

    //获取后台用户
    User selectByUserName(String userName);

    //获取角色名称
    String selectByRoleId(String roleId);

    //通过角色id获取权限id
    String selectPermissionsByRoleId(String roleId);

    //通过权限id获取模块id
    String selectModuleByPresId(String PresId);

    //通过模块id获取模块详情
    Module selectModuleByModuleId(String mouId);

    //获取所有模块
    List<Module> selectModuleAll();

    //通过父级id获取模板信息
    List<Module> selectModuleList(String module_id);

    //删除模板信息
    int delModule(@Param("moduleId") String moduleId,@Param("userId") String userId);

    //通过模板id查询所使用权限数量
    Integer getPermCountByMoId(String moduleId);

    //修改模块信息
    int updModuleById(@Param("userId") String userId,@Param("mId") String mId,@Param("mName") String mName,@Param("mPath") String mPath);

    //添加模块信息
    int addModule(Module module);

    //获取所有二级模块
    List<Module> getModuleTwoAll();

    //获取后台所有用户
    List<User> getUserAll(SelectUser selectUser);

    //获取后台用户数量
    Integer getUserAllCount(SelectUser selectUser);

    //获取所有角色
    List<Role> getRoleAll();

    //修改后台用户信息
    int updBackGroundUserById(User user);

    //后台删除用户信息
    int delUserById(@Param("uId") String uId,@Param("userId") String userId);

    //修改用户积分
    int updUserIntegralById(@Param("userId") String userId,@Param("count") Integer count);

    int delUserIntegralById(@Param("userId") String userId,@Param("count") Integer count);

    //删除医生视频信息
    int delVideoByUserId(String userId);

    //删除医生信息
    int delDovtorByUserId(String userId);

    //获取用户绑定小程序的状态
    Integer getUserTypeOpenId(String userId);

    int addUserOpenId(@Param("userId") String userId,@Param("openId") String openId);

    //获取用户单日提现次数
    Integer getWithdrawalCount(String userId);

    //增加用户提现记录
    int addWithdrawalRecord(WithdrawalRecord withdrawalRecord);
}
