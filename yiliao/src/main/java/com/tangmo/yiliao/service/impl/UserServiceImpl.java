package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.*;
import com.tangmo.yiliao.entity.*;
import com.tangmo.yiliao.service.UserService;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.util.EncryptUtil;
import com.tangmo.yiliao.utility.util.PinyinTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private ManyTimesDao manyTimesDao;

    @Resource
    private ArticleDao articleDao;

    @Resource
    private VideoDao videoDao;

    @Resource
    private DoctorDao doctorDao;

    @Resource
    private DepartmentOrAuditDao departmentOrAuditDao;

    @Resource
    private MessageDao messageDao;

    @Resource
    private IntegralDao integralDao;

    @Override
    public RsFile getRsF() {
        return integralDao.getcode();
    }

    @Override
    @Transactional
    public boolean getcode(String mobile) {
        if(userDao.getcodeConsulting(mobile) == 0){
            userDao.insertcodeConsulting(mobile);
        }else{
            userDao.updatecodeConsultinge(mobile);
        }
        VerificationCode verificationCode = userDao.getcode(mobile);
        if(null == verificationCode){
            userDao.insertcode(mobile);
        }else{
            if(verificationCode.getState().toString().equals("1")){
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public Result addUser(User user) {
        //手机号
        User result = userDao.selectByMobile(user.getUserPhone());
        //用户名
        User user1 = userDao.selectByCard(user.getUserCard());

        if(result != null){
            return ResultUtil.registered();
        }

        if(user1 != null){
            return ResultUtil.error("登录名已被占用，请更新");
        }

        user.setUserId(EncryptUtil.get32Uuid());
        user.setUserName(user.getUserPhone().substring(0,3)+"****"+user.getUserPhone().substring(7,11));
        user.setUserImgUrl("/admin/4010b450422848f5a82c443ad56944ae.png");//默认头像id
        user.setToken(EncryptUtil.get32Uuid());
        user.setCreateUserId(user.getUserId());
        user.setRoleId("MEMBER");
        Integer count = userDao.selectIntegralRules(Byte.parseByte("0"));
        user.setUserTotal(count);
        user.setUserIntegral(count);
        int rowOne = userDao.insertUser(user);
        int rowTwo = insertIntegralSubsidiary(user.getUserId(),"REGISTERED",count);//REGISTERED
        int rowS = userDao.insertUserMessage(user.getUserId());
        int rowR = userDao.insertUserBet(user.getUserId());
        if(rowOne == 1 && rowTwo == 1 && rowS == 1 && rowR == 1){
            if(user.getState().toString().equals("1")){
                //增加当前用户上级
                userDao.insertOnUserBet(user.getUserId(),"1,"+user.getUpdateUserId());

                RelationShip relationShip = userDao.getUserBet(user.getUpdateUserId());
                //增加邀请用户下级
                userDao.insertBelowUserBet(user.getUpdateUserId(),relationShip.getBelowUser()+","+user.getUserId());
                //增加邀请人积分
                count = userDao.selectIntegralRules(Byte.parseByte("1"));
                //增加余额明细记录
                insertIntegralSubsidiary(user.getUpdateUserId(),"FRIENDS_REGISTERED",count);
                //修改用户积分
                userDao.updUserIntegralById(user.getUpdateUserId(),count);
            }
            return ResultUtil.success();
        }
        return ResultUtil.serverError();
    }

    //增加余额明细记录
    @Override
    @Transactional
    public int insertIntegralSubsidiary(String userId,String key,Integer count) {
        IntegralSubsidiary integralSubsidiary = new IntegralSubsidiary();
        integralSubsidiary.setSyId(EncryptUtil.get32Uuid());
        integralSubsidiary.setUserId(userId);
        integralSubsidiary.setLtId(key);
        integralSubsidiary.setSyBean(count);
        integralSubsidiary.setCreateUserId(userId);
        return manyTimesDao.addIntegralSubsidiary(integralSubsidiary);
    }

    @Override
    public Result pwdLogin(User user) {
        if(user.getPassword() == null){
            return ResultUtil.paramError();
        }
        User checkUser = null;
        if(user.getUserPhone() == null){
            return ResultUtil.paramError();
        }
        checkUser = userDao.selectByMobile(user.getUserPhone());
        if(null == checkUser){
            checkUser = userDao.selectByCard(user.getUserPhone());
        }

        if (checkUser == null) {
            return ResultUtil.inviter();
        }
        if (checkUser.getState() == 1 || checkUser.getState().toString().equals("1")) {
            return ResultUtil.userError();
        }
        if (checkUser.getPassword().equals(user.getPassword())) {
            checkUser.setPassword(null);
            checkUser.setUserName(null);
            checkUser.setUserPhone(null);
            checkUser.setState(null);
            checkUser.setRoleName(null);
            return ResultUtil.success(checkUser);
        } else {
            return ResultUtil.pwdError();
        }
    }

    @Override
    @Transactional
    public Result backPwd(User user) {
        User checkUser = userDao.selectByMobile(user.getUserPhone());
        if (checkUser == null) {
            return ResultUtil.inviter();
        }
        if (checkUser.getState() == 1 || checkUser.getState().toString().equals("1")) {
            return ResultUtil.userError();
        }
        userDao.updateUserPwd(checkUser.getUserId(),user.getPassword());
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result updPwd(User user) {
        if(userDao.getPwdById(user.getUserId()).equals(user.getPassword())){
            userDao.updateUserPwd(user.getUserId(),user.getUserCard());
            return ResultUtil.success();
        }
        return ResultUtil.error("原密码不正确");
    }

    @Override
    public Result getBasicInformationById(String userId) {
        return ResultUtil.success(userDao.getBasicInformationById(userId));
    }

    @Override
    public Result getPersonalInformation(String userId) {
        return ResultUtil.success(userDao.getPersonalInformation(userId));
    }

    @Override
    @Transactional
    public Result updUser(User user) {
        userDao.updUser(user);
        return ResultUtil.success();
    }

    @Override
    public Result getUserIntegral(String userId) {
        return ResultUtil.success(userDao.getUserIntegral(userId));
    }

    @Override
    public Result getUserIntegralDetail(String userId) {
        return ResultUtil.success(manyTimesDao.getUserIntegralDetail(userId));
    }

    @Override
    public Result getUserArticleFootprint(String userId,Integer start,Integer end) {
        return ResultUtil.success(articleDao.getUserArticleFootprint(userId,(start - 1)*end,end));
    }

    @Override
    public Result getUserVideoFootprint(String userId, Integer start, Integer end) {
        return ResultUtil.success(videoDao.getUserVideoFootprint(userId,(start - 1)*end,end));
    }

    @Override
    public Result clearUserFootprint(String userId) {
        userDao.clearUserFootprint(userId);
        return ResultUtil.success();
    }

    @Override
    public Result getUserDoctorVideoById(String userId, Integer start, Integer end) {
        return ResultUtil.success(videoDao.getUserDoctorVideoById(userId,(start - 1)*end,end));
    }

    @Override
    public Result adminLogin(User user) {
        User user1 = userDao.selectByMobile(user.getUserName());
        if(user1 != null){
            //医生
            if(!user1.getRoleId().equals("DOCTOR")){
                return ResultUtil.error("当前用户没有权限登录此平台");
            }
            if(!user1.getPassword().equals(user.getPassword())){
                return ResultUtil.error("密码错误");
            }
            user1.setUserName(null);
            user1.setToken(null);
            user1.setUserPhone(null);
            user1.setPassword(null);
            return ResultUtil.success(user1);
        }else{
            //后台用户
            User user2 = userDao.selectByUserName(user.getUserName());
            if(user2 != null){
                if(!user2.getPassword().equals(user.getPassword())){
                    return ResultUtil.error("密码错误");
                }
                if(user2.getRoleId().equals("DOCTOR") || user2.getRoleId().equals("MEMBER")){
                    return ResultUtil.error("身份验证有误");
                }
                user2.setPassword(null);
                String[] roles = user2.getRoleId().split(",");
                String roleName = "";
                for (int i = 0; i <roles.length; i++){
                    if(roleName.equals("")){
                        roleName = roleName + userDao.selectByRoleId(roles[i]);
                    }else{
                        roleName = roleName +" 兼 "+userDao.selectByRoleId(roles[i]);
                    }
                }
                user2.setRoleName(roleName);
                return ResultUtil.success(user2);
            }
        }
        return ResultUtil.error("此账号不存在");
    }

    @Override
    public Result getModuleByRoleId(String roleId) {
        if(roleId.equals("")){
            return ResultUtil.paramError();
        }
        String[] roles = roleId.split(",");
        //权限id
        String idAll = "";
        for(int i =0; i<roles.length; i++){
            String str = userDao.selectPermissionsByRoleId(roles[i]);
            if(str != null && !str.equals("")){
                if(idAll.equals("")){
                    idAll = idAll + str;
                }else{
                    idAll = idAll +","+ str;
                }
            }
        }
        //过滤重复数据
        List<String> preList = filter(idAll);
        //清空后变模块id
        idAll = "";
        for(int i =0; i < preList.size(); i++){
            String str = userDao.selectModuleByPresId(preList.get(i));
            if(str != null && !str.equals("")){
                if(idAll.equals("")){
                    idAll = idAll + str;
                }else{
                    idAll = idAll +","+ str;
                }
            }
        }
        //过滤重复数据
        List<String> modList = filter(idAll);
        //一级集合
        List<Module> fathers = new ArrayList<>();
        //二级集合
        List<Module> modules = new ArrayList<>();
        //清空后变模块父级id
        idAll = "";
        for (int i = 0;i<modList.size();i++){
            Module module = userDao.selectModuleByModuleId(modList.get(i));
            if(module != null){
                if(idAll.equals("")){
                    idAll = idAll + module.getFatherId();
                }else{
                    idAll = idAll +","+ module.getFatherId();
                }
                modules.add(module);
            }
        }
        //过滤重复数据
        List<String> fatherList = filter(idAll);
        for (int i = 0;i<fatherList.size();i++){
            fathers.add(userDao.selectModuleByModuleId(fatherList.get(i)));
        }
        for (int i = 0;i<fathers.size();i++){
            List<Module> ms = new ArrayList<>();
            for (int j = 0; j<modules.size();j++){
                if(fathers.get(i).getModuleId().equals(modules.get(j).getFatherId())){
                    ms.add(modules.get(j));
                }
            }
            fathers.get(i).setModuleList(ms);
        }
        return ResultUtil.success(fathers);
    }

    //过滤重复数据
    private static List<String> filter(String str){
        List<String> modList = new ArrayList(Arrays.asList(str.split(",")));
        List<String> newmodList = new ArrayList<>();
        for(int i = 0; i < modList.size(); i++){
            if(newmodList.size() == 0){
                newmodList.add(modList.get(i));
            }else{
                int row = 0;
                for (int j = 0; j < newmodList.size(); j++){
                    if(modList.get(i).equals(newmodList.get(j))){
                        row = 1;
                    }
                }
                if(row == 0){
                    newmodList.add(modList.get(i));
                }
            }
        }
        return newmodList;
    }


    @Override
    public Result getModuleAll() {
        return ResultUtil.success(userDao.selectModuleAll());
    }

    @Override
    @Transactional
    public Result delModuleById(String userId,String moId,String type) {
        if(type.equals("0")){
            List<Module> moduleList = userDao.selectModuleList(moId);
            if(moduleList != null && moduleList.size() != 0){
                return ResultUtil.error("当前模板下存在子集模板,无法删除");
            }
        }
        else{
            if(userDao.getPermCountByMoId(moId) != 0){
                return ResultUtil.error("当前模板正在使用,请删除对应的权限");
            }
        }
        userDao.delModule(moId,userId);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result updModuleById(String userId, String mId,String mName, String mPath) {
        if(userId == null || userId.equals("") || mId==null || mId.equals("") || mName == null || mPath == null){
            return ResultUtil.paramError();
        }
        if(mPath.equals("0")){
            mPath = null;
        }
        userDao.updModuleById(userId,mId,mName,mPath==null?null:mPath.replaceAll("_","/"));
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result addModule(Module module) {
        PinyinTool tool = new PinyinTool();
        if(module == null || module.getModuleName() == null || module.getModuleLevel() == null || module.getFatherId() == null){
            return ResultUtil.paramError();
        }
        module.setModuleId(tool.toPinYin(module.getModuleName(), "", PinyinTool.Type.UPPERCASE));
        Module module1 = userDao.selectModuleByModuleId(module.getModuleId());
        if(module1 != null){
            return ResultUtil.error("当前模板名称生成的模板标识重复,请更换后在试！");
        }
        userDao.addModule(module);
        return ResultUtil.success();
    }

    @Override
    public Result getModuleTwoAll(String id) {
        List<Module> modules = userDao.getModuleTwoAll();
        String str = userDao.selectModuleByPresId(id);
        for (int i = 0;i< modules.size();i++){
            int row = 0;
            if(str != null && !str.equals("")){
                String[] strs = str.split(",");
                if(strs.length != 0){
                    for (int j = 0;j<strs.length;j++){
                        if(modules.get(i).getModuleId().equals(strs[j])){
                            modules.get(i).setState(Byte.parseByte("0"));
                            row = 1;
                            break;
                        }
                    }
                }
            }
            if(row == 0){
                modules.get(i).setState(Byte.parseByte("1"));
            }
        }
        return ResultUtil.success(modules);
    }

    @Override
    public Result getUserAll(SelectUser selectUser) {
        selectUser.setStart((selectUser.getStart()-1)*selectUser.getEnd());
        if(selectUser.getCondName().equals("") || selectUser.getCondName() == null){
            selectUser.setCondName("");
        }
        return ResultUtil.success(userDao.getUserAll(selectUser));
    }

    @Override
    public Result getUserAllCount(SelectUser selectUser) {
        selectUser.setStart((selectUser.getStart()-1)*selectUser.getEnd());
        if(selectUser.getCondName().equals("") || selectUser.getCondName() == null){
            selectUser.setCondName("");
        }
        return ResultUtil.success(userDao.getUserAllCount(selectUser));
    }

    @Override
    public Result getRoleAll() {
        return ResultUtil.success(userDao.getRoleAll());
    }

    @Override
    @Transactional
    public Result updBackGroundUserById(User user) {
        if(user.getUserId() == null || user.getUserPhone() == null || user.getRoleId() == null || user.getName() == null){
            return ResultUtil.paramError();
        }
        if(user.getPassword().equals("") || user.getPassword() == null){
            user.setPassword(null);
        }
        userDao.updBackGroundUserById(user);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result updAppUserById(User user) {
        System.out.println(user);

        if(user == null){
            return ResultUtil.paramError();
        }
        userDao.updBackGroundUserById(user);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result delUserById(String uId, String userId) {
        if(uId == null || userId == null){
            return ResultUtil.paramError();
        }
        //删除用户信息
        userDao.delUserById(uId,userId);
        //删除用户视频
        userDao.delVideoByUserId(uId);
        //删除医生信息
        userDao.delDovtorByUserId(uId);

        return ResultUtil.success();
    }

    @Override
    public Result getUserDoctorById(String userId) {
        if(userId == null){
            return ResultUtil.paramError();
        }
        DoctorDetails doctorDetails = doctorDao.getDoctorDetailsById(userId);
        doctorDetails.setUpdateUserId(doctorDao.getDoctorVideoCount(userId)+"");
        return ResultUtil.success(doctorDetails);
    }

    @Override
    public Result getUserById(String userId) {
        if(userId == null){
            return ResultUtil.paramError();
        }
        return ResultUtil.success(userDao.getBasicInformationById(userId));
    }

    @Override
    public Result getUserByPhone(String userPhone) {
        User user = userDao.selectByMobile(userPhone);
        if(user == null){
            return ResultUtil.error("当前用户不存在");
        }
        user.setPassword(null);
        user.setToken(null);
        user.setState(null);
        user.setRoleName(null);
        return ResultUtil.success(user);
    }

    @Override
    @Transactional
    public Result addUserH(User user) {
        //手机号
        User result = userDao.selectByMobile(user.getUserPhone());
        if(result != null){
            return ResultUtil.registered();
        }

        user.setUserId(EncryptUtil.get32Uuid());
        if(user.getRoleId().equals("MEMBER")){
            //用户名
            User user1 = userDao.selectByCard(user.getUserCard());
            if(user1 != null){
                return ResultUtil.error("登录名已被占用，请更新");

            }
            user.setUserImgUrl("/admin/4010b450422848f5a82c443ad56944ae.png");//默认头像id
            user.setToken(EncryptUtil.get32Uuid());
        }else{
            user.setUserAge(null);
            user.setUserGender(null);
        }
        user.setCreateUserId(user.getUserId());
        Integer count = userDao.selectIntegralRules(Byte.parseByte("0"));
        user.setUserTotal(count);
        user.setUserIntegral(count);
        int rowOne = userDao.insertUser(user);
        int rowTwo = insertIntegralSubsidiary(user.getUserId(),"REGISTERED",count);
        int rowS = userDao.insertUserMessage(user.getUserId());
        int rowR = userDao.insertUserBet(user.getUserId());
        if(rowOne == 1 && rowTwo == 1){
            return ResultUtil.success();
        }
        return ResultUtil.serverError();
    }

    @Override
    @Transactional
    public Result addDoctorH(DoctorApplied doctorApplied) {
        doctorApplied.setDaId(EncryptUtil.get32Uuid());
        doctorApplied.setCreateUserId(doctorApplied.getUserId());
        departmentOrAuditDao.addDoctor(doctorApplied);
        //修改真实姓名以及身份
        User user = new User();
        user.setRoleId("DOCTOR");
        user.setUserId(doctorApplied.getUserId());
        user.setName(doctorApplied.getName());
        userDao.updUser(user);
        //修改消息状态
        messageDao.updMessageState(Byte.parseByte("1"),1,doctorApplied.getUserId());
        return ResultUtil.success();
    }

    @Override
    public Result getUserSJGXById(String userId) {
        RelationShip relationShip1 = userDao.getUserBet(userId);
        List<User> userList = new ArrayList<>();
        if(null != relationShip1){
            String[] strs1 = relationShip1.getBelowUser().split(",");
            for (int i = 1; i < strs1.length; i++){
                User user2 = userDao.getPersonalInformation(strs1[i]);
                if(user2 != null){
                    RelationShip relationShip2 = userDao.getUserBet(user2.getUserId());
                    if(null != relationShip2){
                        String[] strs2 = relationShip2.getBelowUser().split(",");
                        for (int j = 1; j< strs2.length; j++){
                            User user3 = userDao.getPersonalInformation(strs2[j]);
                            if(user3 != null){
                                RelationShip relationShip3 = userDao.getUserBet(user3.getUserId());
                                if(null != relationShip3){
                                    String[] strs3 = relationShip3.getBelowUser().split(",");
                                    for (int k = 1; k < strs3.length; k++){
                                        user3.getUserList().add(userDao.getPersonalInformation(strs3[k]));
                                    }
                                }
                                user2.getUserList().add(user3);
                            }
                        }
                    }
                    userList.add(user2);
                }

            }
        }
        return ResultUtil.success(userList);
    }
}
