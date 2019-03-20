package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.*;
import com.tangmo.zhygzhglxt.entity.*;
import com.tangmo.zhygzhglxt.entity.dto.TbDriverVerifyDto;
import com.tangmo.zhygzhglxt.entity.dto.UserDto;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbSysUserService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by chengge on 2018/10/22.
 */
@Service
public class TbSysUserServiceImpl implements TbSysUserService {

    @Autowired
    protected TbSysUserMapper tbSysUserMapper;//用户

    @Autowired
    protected TbDriverVerifyMapper tbDriverVerifyMapper;//司机审核


    @Autowired
    protected TbSysRoleMapper tbSysRoleMapper;//角色

    @Autowired
    protected TbSysModuleMapper tbSysModuleMapper;//模块

    @Autowired
    protected TbSysPermissionsMapper tbSysPermissionsMapper;//权限


    private Logger logger = Logger.getLogger(TbSysUserServiceImpl.class);

    /*
     * 乘客端用户注册
     * */
    @Override
    @Transactional
    public Result addAppUser(UserDto userDto) {
        if (userDto.getPassword() == null || userDto.getPhone() == null) {
            return new Result(ResultCode.PHONE_PASSWORD_ERROR);
        }
        if (userDto.getUserStatus() == null || "".equals(userDto.getUserStatus())) {
            return new Result(ResultCode.FAIL, "用户身份userStatus不能为空 0 管理员 1乘客 2车主");
        }
        TbSysUser result = tbSysUserMapper.selectByPhone(userDto.getPhone());

        if (result != null) {
            return new Result(ResultCode.PHONE_ERROR);
        }
        //设置code
        String code = EncryptUtil.get32Uuid();
        //设置userId
        String userId = EncryptUtil.get32Uuid();
        //设置userName(用户名)
        userDto.setUserName(userDto.getPhone());
        userDto.setId(userId);
        userDto.setCode(code);
        userDto.setToken(EncryptUtil.get32Uuid());
        userDto.setPassword(EncryptUtil.md5(userDto.getPassword()));
        int row = tbSysUserMapper.insertAppUser(userDto);

        if (row == 1) {
            userDto.setPassword("就你也想知道密码？");
            userDto.setPayPassword("支付密码就更别想了");
            return new Result(ResultCode.SUCCESS, userDto);
        }
        return new Result(ResultCode.FAIL);
    }

    /*
     * 后台添加用户
     * */
    @Override
    @Transactional
    public Result saveSysUser(TbSysUser tbSysUser) {
        if (tbSysUser.getPassword() == null || tbSysUser.getPhone() == null) {
            return new Result(ResultCode.PHONE_PASSWORD_ERROR);
        }
        if (tbSysUser.getUserStatus() == null || "".equals(tbSysUser.getUserStatus())) {
            return new Result(ResultCode.FAIL, "用户身份userStatus不能为空 0 管理员 1乘客 2车主");
        }

        TbSysUser result = tbSysUserMapper.selectByPhone(tbSysUser.getPhone());

        if (result != null) {
            return new Result(ResultCode.PHONE_ERROR);
        }

        //设置code
        String code = EncryptUtil.get32Uuid();
        //设置userId
        String userId = EncryptUtil.get32Uuid();
        //设置userName(用户名)
        tbSysUser.setToken(EncryptUtil.get32Uuid());
        tbSysUser.setCode(code);
        tbSysUser.setId(userId);
        tbSysUser.setPassword(EncryptUtil.md5(tbSysUser.getPassword()));
        tbSysUser.setUserName(tbSysUser.getPhone());

        if ("1".equals(tbSysUser.getUserStatus())) { //代表添加的乘客
            int row = tbSysUserMapper.insertSelective(tbSysUser);
            if (row == 1) {
                tbSysUser.setPassword("就你也想知道密码？");
                tbSysUser.setPayPassword("支付密码就更别想了");
                return new Result(ResultCode.SUCCESS, tbSysUser);
            }
        } else if ("2".equals(tbSysUser.getUserStatus())) {  //代表是的车主

            TbDriverVerify tbDriverVerify = tbSysUser.getTbDriverVerify();

            if (tbDriverVerify == null) {
                return new Result(ResultCode.FAIL, "车主信息不能为空！");
            }

            int row = tbSysUserMapper.insertSelective(tbSysUser);

            if (row > 0) {

                tbDriverVerify.setDriverUserid(userId);
                //代表是乘客端
                if (tbDriverVerify.getIdcardFace() == null || //身份证正面
                        tbDriverVerify.getUserCard() == null || //身份证号
                        tbDriverVerify.getDriverName() == null || //车主姓名
                        tbDriverVerify.getCarType() == null ||   //车辆类型
                        tbDriverVerify.getCarNumber() == null ||   //车牌号
                        tbDriverVerify.getCarType() == null ||   //车牌号
                        tbDriverVerify.getCompany() == null ||   //运营公司
                        tbDriverVerify.getDriverIcence() == null || //驾驶证
                        tbDriverVerify.getLicensePlate() == null ||//车牌照
                        tbDriverVerify.getIdcardBack() == null  //身份证反面
                ) {

                    return new Result(ResultCode.FAIL, "参数信息不全！");
                }
                if (tbDriverVerify.getDriverVerifyCode() == null || "".equals(tbDriverVerify.getDriverVerifyCode())) {
                    tbDriverVerify.setDriverVerifyId(EncryptUtil.get32Uuid());
                    tbDriverVerify.setDriverVerifyCode(EncryptUtil.get32Uuid());
                    tbDriverVerify.setState("1");
                    tbDriverVerifyMapper.insertSelective(tbDriverVerify);
                }
            }
        } else {                               //0代表的是管理员

            if (tbSysUser.getRoleCode() == null || "".equals(tbSysUser.getRoleCode())) {
                return new Result(ResultCode.FAIL, "用户的角色未指定！");
            }
            int row = tbSysUserMapper.insertSelective(tbSysUser);
            return new Result(ResultCode.SUCCESS);

        }
        return new Result(ResultCode.FAIL);
    }

//    /*
//     * 司机端用户注册
//     * */
//    @Override
//    @Transactional
//    public Result saveDriverUser(TbDriverVerifyDto tbDriverVerifyDto) {
//
//        if (tbDriverVerifyDto.getPassword() == null || tbDriverVerifyDto.getPhone() == null) {
//            return new Result(ResultCode.PHONE_PASSWORD_ERROR);
//        }
//        if (tbDriverVerifyDto.getIdcardFace() == null ||
//                tbDriverVerifyDto.getUserCard() == null ||
//                tbDriverVerifyDto.getRealName() == null ||
//                tbDriverVerifyDto.getUserStatus() == null|| //用户身份
//                tbDriverVerifyDto.getCarType() == null||
//                tbDriverVerifyDto.getCertificate() == null||
//                tbDriverVerifyDto.getDriverIcence() == null||
//                tbDriverVerifyDto.getLicensePlate() == null) {
//            return new Result(ResultCode.FAIL,"参数信息不全！");
//        }
//
//        TbSysUser result = tbSysUserMapper.selectByPhone(tbDriverVerifyDto.getPhone());
//
//        if (result != null) {
//            return new Result(ResultCode.PHONE_ERROR);
//        }
//        //设置code
//        String code = EncryptUtil.get32Uuid();
//        //设置token
//        String token = EncryptUtil.get32Uuid();
//        //设置userId
//        String userId = EncryptUtil.get32Uuid();
//        //设置userName(用户名)
//        UserDto userDto = new UserDto();
//        userDto.setImgUrl(tbDriverVerifyDto.getImgUrl());
//        userDto.setRealName(tbDriverVerifyDto.getRealName());
//        userDto.setIdcardFace(tbDriverVerifyDto.getIdcardFace());
//        userDto.setPhone(tbDriverVerifyDto.getPhone());
//        userDto.setUserCard(tbDriverVerifyDto.getUserCard());
//        userDto.setNickName(tbDriverVerifyDto.getRealName());
//        userDto.setUserStatus(tbDriverVerifyDto.getUserStatus());
//        userDto.setUserName(tbDriverVerifyDto.getPhone());
//        userDto.setId(userId);
//        userDto.setCode(code);
//        userDto.setToken(token);
//        userDto.setPassword(EncryptUtil.md5(tbDriverVerifyDto.getPassword()));
//        int row = tbSysUserMapper.insertAppUser(userDto);   //添加进入用户表
//        if (row == 1) {
//
//            TbDriverVerify tbDriverVerify = new TbDriverVerify();
//
//            String verifyId = EncryptUtil.get32Uuid();
//            String verifyCode = EncryptUtil.get32Uuid();
//            tbDriverVerify.setCarType(tbDriverVerifyDto.getCarType());  //车辆类型
//            tbDriverVerify.setCertificate(tbDriverVerifyDto.getCertificate());//资格证
//            tbDriverVerify.setDriverIcence(tbDriverVerifyDto.getDriverIcence());//驾驶证
//            tbDriverVerify.setLicensePlate(tbDriverVerifyDto.getLicensePlate());//车牌照
//            tbDriverVerify.setDriverUserid(code);//司机用户的唯一标识code
//            tbDriverVerify.setDriverVerifyCode(verifyCode);//唯一标识
//            tbDriverVerify.setDriverVerifyId(verifyId);//主键
//            tbDriverVerify.setTransport(tbDriverVerifyDto.getTransport());//运输证
//            int hang = tbDriverVerifyMapper.insertSelective(tbDriverVerify);   //添加进入司机审核表
////            userDto.setPassword("就你也想知道密码？");
////            userDto.setPayPassword("支付密码就更别想了");
//            if(hang>0){
//                return new Result(ResultCode.SUCCESS);
//            }
//        }
//        return new Result(ResultCode.FAIL,"注册失败！");
//    }

    /*
     * 用户短信登录
     * */
    @Override
    public Result smsLogin(String phone, String code, HttpSession session, String type) {

//            String smsCode=(String)session.getAttribute("code");
//            String smsPhone=(String)session.getAttribute("phone");
//            Date date=(Date)session.getAttribute("date");
//            System.out.println("===smsCode==="+smsCode);
//            System.out.println("===smsPhone==="+smsPhone);
//            System.out.println("===date==="+date);

//            if(smsCode == null || smsPhone ==null || date == null){
//                return new Result(ResultCode.SMS_ERROR1,"手机验证码未发送！");
//            }

//            if(Math.floor(new Date().getTime()/1000)- Math.floor(date.getTime()/1000)>60){
//                return new Result(ResultCode.SMSTIME_ERROR);
//            }
//            else{
//
//                if(smsCode.equals(code) && smsPhone.equals(phone)){
        System.out.println(type);

        TbSysUser tbSysUser = tbSysUserMapper.selectByPhone(phone);

        if (tbSysUser == null) {
            return new Result(ResultCode.PHONE_ERROR1);
        }
        if ("1".equals(tbSysUser.getState())) {
            return new Result(ResultCode.FAIL, "该账号已被冻结！");
        }
        String token = EncryptUtil.get32Uuid();
        int a = tbSysUserMapper.updateToken(token, tbSysUser.getCode());
        if (a > 0) {
            tbSysUser.setToken(token);
        }
        //--------------
        if (type.equals("0")) {        //代表乘客端登录0
            Map map = new HashMap();
            map.put("userCode", tbSysUser.getCode());
            map.put("token", tbSysUser.getToken());
            return new Result(ResultCode.SUCCESS, map);
        } else if (type.equals("1")) {  //代表司机端登录1
            if (tbSysUser.getUserStatus().equals("1")) {   //代表还是乘客
                return new Result(ResultCode.FAIL, "该司机还未通过审核！");
            } else if (tbSysUser.getUserStatus().equals("2")) {  //代表是司机登录
                Map map = new HashMap();
                map.put("userCode", tbSysUser.getCode());
                map.put("token", tbSysUser.getToken());
                map.put("imgUrl", tbSysUser.getImgUrl());
                //根据用户id查询司机审核的信息
                TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByUserCode(tbSysUser.getCode());
                if (tbDriverVerify != null) {
                    map.put("parmId", tbDriverVerify.getCarType());
                }
                return new Result(ResultCode.SUCCESS, map);
            } else {
                return new Result(ResultCode.FAIL, "该用户是管理员！");
            }
        } else {
            return new Result(ResultCode.FAIL, "登录类型未确认！");
        }
//                }
//                else{
//                    return new Result(ResultCode.SMS_ERROR);
//                }
//            }
    }

    /*
     * 根据用户的唯一标识查询用户的信息
     * */
    @Override
    public Result getUserInfoByCode(String code) {

        if ("".equals(code) || code == null) {

            return new Result(ResultCode.FAIL, "用户的唯一标识不能为空！");
        }

        TbSysUser tbSysUser = tbSysUserMapper.selectByCode(code);
        if (tbSysUser == null) {
            return new Result(ResultCode.USER_ERROR);
        }
        return new Result(ResultCode.SUCCESS, tbSysUser);
    }

    /*
     * 用户登录
     * */
    @Override
    public Result login(UserDto userDto) {

        if (userDto.getPassword() == null || userDto.getUserName() == null) {
            return new Result(ResultCode.PHONE_PASSWORD_ERROR);
        }

        if (userDto.getType() == null || "".equals(userDto.getType())) {
            return new Result(ResultCode.FAIL, "客户端类型不能为空！");
        }

        TbSysUser checkUser = tbSysUserMapper.selectByUserName(userDto.getUserName());

        if (checkUser == null) {
            return new Result(ResultCode.USER_ERROR);
        }


        System.out.println("" + userDto.getPassword());
        if (!(checkUser.getPassword().equals(EncryptUtil.md5(userDto.getPassword())))) {
            return new Result(ResultCode.PASSWORD_ERROR);
        }
        if ("1".equals(checkUser.getState())) {
            return new Result(ResultCode.FAIL, "该账号已被冻结！");
        }
        String token = EncryptUtil.get32Uuid();
        int a = tbSysUserMapper.updateToken(token, checkUser.getCode());
        checkUser.setToken(token);//设置token
        checkUser.setPassword(null);
        checkUser.setPayPassword(null);
        if ("0".equals(userDto.getType())) {        //代表乘客端登录0
            Map map = new HashMap();
            map.put("userCode", checkUser.getCode());
            map.put("token", checkUser.getToken());
            return new Result(ResultCode.SUCCESS, map);
        } else if ("1".equals(userDto.getType())) {  //代表司机端登录1
            if ("1".equals(checkUser.getUserStatus())) {   //代表还是乘客
                return new Result(ResultCode.FAIL, "该司机还未通过审核！");
            } else if ("2".equals(checkUser.getUserStatus())) {  //代表是司机登录
                Map map = new HashMap();
                map.put("userCode", checkUser.getCode());
                map.put("token", checkUser.getToken());
                map.put("imgUrl", checkUser.getImgUrl());
                //根据用户id查询司机审核的信息
                TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByUserCode(checkUser.getCode());
                if (tbDriverVerify != null) {
                    map.put("parmId", tbDriverVerify.getCarType());
                }
                return new Result(ResultCode.SUCCESS, map);
            } else {
                return new Result(ResultCode.FAIL, "该用户是管理员！");
            }
        } else {                                    //代表后台登录2
            if (checkUser.getRoleCode() != null) {
                String roleCode = checkUser.getRoleCode();
                String roleName = "";
                TbSysRole sysRole = tbSysRoleMapper.selectByCode(roleCode);
                roleName = sysRole.getRoleName();
                //根据角色得到权限
                String[] permissionsCodes = sysRole.getPermissions().split(",");
                List<TbSysPermissions> tbSysPermissionLists = tbSysPermissionsMapper.selectByCodes(permissionsCodes);
                String modules = "";
                for (TbSysPermissions tbSysPermissions : tbSysPermissionLists) {

                    modules += tbSysPermissions.getModule();
                }
                //根据权限得到模块
                String[] moduleCodes = modules.split(",");
                List<TbSysModule> moduleList = tbSysModuleMapper.selByModuleCodes(moduleCodes);
                checkUser.setRoleName(roleName);
                checkUser.setTbSysModules(moduleList);
                return new Result(ResultCode.SUCCESS, checkUser);
            }
            return new Result(ResultCode.FAIL, "该用户未指定角色！");
        }
    }

    /*
     * 根据电话查找用户
     * */
    @Override
    public Result selUserByPhone(String phone) {

        TbSysUser tbSysUser = tbSysUserMapper.selectByPhone(phone);
        if (tbSysUser == null) {
            return new Result(ResultCode.PHONE_ERROR1);
        }
        tbSysUser.setPassword(null);
        tbSysUser.setPayPassword(null);

        return new Result(ResultCode.SUCCESS, tbSysUser);
    }

    /*
     * 根据用户的唯一标识获取token
     * */
    @Override
    public String selectToken(String userCode) {

        return tbSysUserMapper.selectToken(userCode);
    }

    /*
     * 重置密码
     * */
    @Override
    public Result resetPwd(UserDto userDto) {

        if (userDto.getPassword() == null) {
            return new Result(ResultCode.FAIL, "密码不能为空");
        }
        userDto.setPassword(EncryptUtil.md5(userDto.getPassword()));
        tbSysUserMapper.updatePwdByPhone(userDto);
        TbSysUser tbSysUser = tbSysUserMapper.selectByPhone(userDto.getPhone());
        tbSysUser.setPassword(null);
        tbSysUser.setPayPassword(null);
        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 更改用户基本信息
     * */
    @Override
    @Transactional
    public Result changeUser(UserDto userDto) {

        if (userDto.getCode() == null) {
            return new Result(ResultCode.FAIL, "用户唯一标识code不能为空");
        }
        if (userDto.getPassword() != null) {
            userDto.setPassword(EncryptUtil.md5(userDto.getPassword()));
        }
        if (userDto.getPayPassword() != null) {
            userDto.setPayPassword(EncryptUtil.md5(userDto.getPayPassword()));
        }
        tbSysUserMapper.updateByAppCode(userDto);

        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 更改用户手机号
     * */
    @Override
    public Result updateUserPhone(String code, String userPhone) {

        tbSysUserMapper.updateUserPhone(userPhone, code);
        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 更改用户登录密码
     * */
    @Override
    public Result changePwd(UserDto userDto) {

        if (userDto.getCode() == null || userDto.getPassword() == null || userDto.getUserNewPassWord() == null) {
            return new Result(ResultCode.FAIL, "更改信息不完整");
        }
        TbSysUser result = tbSysUserMapper.selectByCode(userDto.getCode());
        if (result == null) {
            return new Result(ResultCode.USER_ERROR);
        }
        if (!result.getPassword().equals(EncryptUtil.md5(userDto.getPassword()))) {
            return new Result(ResultCode.PASSWORD_ERROR);
        }
        tbSysUserMapper.updatePwd(EncryptUtil.md5(userDto.getUserNewPassWord()), userDto.getCode());

        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 更改用户支付密码
     * */
    @Override
    public Result changePayPwd(UserDto userDto) {
        if (userDto.getCode() == null || userDto.getPayPassword() == null || userDto.getUserNewPayPassWord() == null) {
            return new Result(ResultCode.FAIL, "更改信息不完整");
        }
        TbSysUser result = tbSysUserMapper.selectByCode(userDto.getCode());
        if (!result.getPayPassword().equals(EncryptUtil.md5(userDto.getPayPassword()))) {
            return new Result(ResultCode.PASSWORD_ERROR2);
        }
        tbSysUserMapper.updatePayPwd(EncryptUtil.md5(userDto.getUserNewPayPassWord()), userDto.getCode());
        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 设置用户支付密码
     * */
    @Override
    public Result setPayPwd(UserDto userDto) {
        if (userDto.getCode() == null || userDto.getPayPassword() == null) {
            return new Result(ResultCode.FAIL, "更改信息不完整");
        }
        tbSysUserMapper.updatePayPwd(EncryptUtil.md5(userDto.getPayPassword()), userDto.getCode());
        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 查询所有用户信息
     *fbState：传0 查询未读 传1查询已读 不传：查询全部
     * */
    @Override
    public Result selAllUser(String name, String userStatus, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        if ("".equals(userStatus)) {
            userStatus = null;
        }
        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }
        if ("".equals(userStatus)) {
            userStatus = null;
        }
        List<TbSysUser> tbSysUsers = tbSysUserMapper.selAllUser(name, userStatus);
        if (tbSysUsers.size() > 0) {
            for (TbSysUser tbSysUser : tbSysUsers) {
                if ("2".equals(tbSysUser.getUserStatus())) { //车主
                    TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByUserCode(tbSysUser.getCode());
                    tbSysUser.setTbDriverVerify(tbDriverVerify);
                }
            }
        }

        return new Result(ResultCode.SUCCESS, new PageInfo(tbSysUsers));
    }

    /*
     * 根据用户的唯一标识code概念性删除
     * */
    @Override
    public Result delByUserCode(String code) {

        if ("".equals(code) || code == null) {

            return new Result(ResultCode.FAIL, "w唯一标识不能为空！");
        }

        tbSysUserMapper.deleteByCode(code);

        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 根根据用户的唯一标识code修改用户的状态（冻结1或者解除冻结0）
     * */
    @Override
    public Result updateStateByUserCode(String code, String state) {

        if ("".equals(code) || code == null) {

            return new Result(ResultCode.FAIL, "w唯一标识不能为空！");
        }
        if (state == null || "".equals(state)) {
            return new Result(ResultCode.FAIL, "用户的状态不能为空！");
        }

        tbSysUserMapper.updateStateByUserCode(code, state);

        return new Result(ResultCode.SUCCESS);
    }


    /*
     * 后台修改用户基本信息
     * */
    @Override
    @Transactional
    public Result updateSysUser(TbSysUser tbSysUser) {

        if (tbSysUser.getCode() == null) {
            return new Result(ResultCode.FAIL, "用户唯一标识code不能为空");
        }

        if (tbSysUser.getPassword() != null) {
            tbSysUser.setPassword(EncryptUtil.md5(tbSysUser.getPassword()));
        }

        if (tbSysUser.getPayPassword() != null) {
            tbSysUser.setPayPassword(EncryptUtil.md5(tbSysUser.getPayPassword()));
        }

        int a = tbSysUserMapper.updateByPrimaryKeySelective(tbSysUser);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据用户唯一标识查找用户详情信息
     *
     * @param code 用户唯一标识
     * @return
     */
    @Override
    public Result jtQueryById(String code) {

        if (code != null) {

            return new Result(ResultCode.SUCCESS, tbSysUserMapper.jtQueryById(code));
        }

        return new Result(ResultCode.USER_ERROR);
    }

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
    @Override
    public Result jtQueryList(String name, String userStatus, String parmId, String parmTypeId, Integer pageNo, Integer pageSize) {
        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }

        List<TbSysUser> jtQueryList = tbSysUserMapper.jtQueryList(name, userStatus, parmId, parmTypeId);

        return new Result(ResultCode.SUCCESS, new PageInfo(jtQueryList));
    }


    /**
     * 后台统计所有用户
     *
     * @return
     */
    @Override
    public Result selAllUserCount() {

        Map map = tbSysUserMapper.selAllUserCount();

        return new Result(ResultCode.SUCCESS, map);
    }

    /**
     * 后台按年统计所有用户
     *
     * @return
     */
    @Override
    public Result selAllUserCountByYear(String year) {

        if (year == null || "".equals(year)) {
            return new Result(ResultCode.FAIL, "年份不能为空");
        }
        String startDate = year + "-01";
        String endDate = (Integer.parseInt(year) + 1) + "-01";

        List<Map> list = new ArrayList<>();

        List<Map> mapList = tbSysUserMapper.selAllUserCountByYear(startDate, endDate);

        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

        for (int i = 0; i < months.length; i++) {
            Integer x = 1;
            String date = year + "-" + months[i];
            Map map = new HashMap();
            map.put("timeDate", date);
            map.put("driver", 0);
            map.put("passenger", 0);
            for (int j = 0; j < mapList.size(); j++) {
                if (date.equals(mapList.get(j).get("timeDate").toString())) {  //如果相等
                    map.put("driver", mapList.get(j).get("driver").toString());
                    map.put("passenger", mapList.get(j).get("passenger").toString());
                    mapList.remove(j);
                }
            }
            list.add(map);
        }
        return new Result(ResultCode.SUCCESS, list);
    }


    /**
     * 后台按月统计所有用户
     *
     * @return
     */
    @Override
    public Result selAllUserCountByMonth(String yearMonth) {

        if (yearMonth == null || "".equals(yearMonth)) {
            return new Result(ResultCode.FAIL, "年份月份不能为空");
        }
        int day = tbSysUserMapper.selectByDay(yearMonth); //查询该月有多少天

        String startDate = yearMonth + "-01";
        String endDate = yearMonth + "-" + day;

        List<Map> list = new ArrayList<>();

        List<Map> mapList = tbSysUserMapper.selAllUserCountByMonth(startDate, endDate);
        for (int i = 1; i < day + 1; i++) {
            String date = "";
            if (i < 10) {
                date = "0" + i;
            } else {
                date = i + "";
            }
            date = yearMonth + "-" + date;
            Map map = new HashMap();
            map.put("timeDate", date);
            map.put("driver", 0);
            map.put("passenger", 0);
            for (int j = 0; j < mapList.size(); j++) {
                if (date.equals(mapList.get(j).get("timeDate").toString())) {  //如果相等
                    map.put("driver", mapList.get(j).get("driver").toString());
                    map.put("passenger", mapList.get(j).get("passenger").toString());
                    mapList.remove(j);
                }
            }
            list.add(map);
        }
        return new Result(ResultCode.SUCCESS, list);
    }


    /**
     * 后台按开始时间和结束时间查询每一天的用户数
     *
     * @return
     */
    @Override
    public Result selAllUserCountByDate(String startDate, String endDate, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (startDate != null && !"".equals(startDate)) {
            startDate = null;
        }

        if (endDate != null && !"".equals(endDate)) {
            endDate = null;
        }

        List<Map> listMap = tbSysUserMapper.selAllUserCountByDate(startDate, endDate);

        return new Result(ResultCode.SUCCESS, new PageInfo(listMap));
    }
}
