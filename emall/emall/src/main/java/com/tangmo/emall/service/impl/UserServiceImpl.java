package com.tangmo.emall.service.impl;

import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.dao.UserDao;
import com.tangmo.emall.entity.*;
import com.tangmo.emall.entity.dto.UserDto;
import com.tangmo.emall.service.UserService;
import com.tangmo.emall.utils.*;
import com.tangmo.emall.utils.jedis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.List;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Resource
    private FileDao fileDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    @Transactional
    public Result addUserBySystem(UserDto userDto) {
        try {
            //非空判断
            if(userDto == null || userDto.getUserEmail() == null || userDto.getPassword() == null || userDto.getFirstName() == null
                    || userDto.getLastName() == null || userDto.getCode() == null){
                return ResultUtil.paramError();
            }

            //校验用户
            User user1 = userDao.getUserByEmail(userDto.getUserEmail());

            if(user1 == null){

                String key = "EmailCode"+userDto.getUserEmail();

                //判断验证码是否存在
                if (!jedisKeys.exists(key)) {
                    return ResultUtil.linkError();
                }

                user1 = new User();

                user1.setUserEmail(userDto.getUserEmail());
                user1.setFirstName(userDto.getFirstName());
                user1.setLastName(userDto.getLastName());
                user1.setPassword(EncryptUtil.md5(userDto.getPassword())); //二次加密 密码
                user1.setNickName("Hello "+userDto.getFirstName());   //默认姓名
                user1.setUserPhoto("default");                        //默认头像

                //添加用户
                userDao.addUser(user1);

                jedisKeys.del(key);

                user1.setUserEmail(null);
                user1.setFirstName(null);
                user1.setLastName(null);

                user1.setToken(JWTUtil.createTokenWithClaim(user1));

                return ResultUtil.success(user1);
            }else{

                return ResultUtil.registered();
            }
        }catch (Exception e){
            log.error("用户模块：'系统用户注册'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result userSystemLogin(UserDto userDto) {
        try {
            //非空判断
            if(userDto == null || userDto.getUserEmail() == null || userDto.getPassword() == null){
                return ResultUtil.paramError();
            }

            //校验用户
            User user1 = userDao.getUserByEmail(userDto.getUserEmail());

            if(user1 != null){

                //校验密码
                if(!EncryptUtil.md5(userDto.getPassword()).equals(user1.getPassword())){
                    return ResultUtil.pwdError();
                }

                if(user1.getUserStatus().toString().equals("0")){
                    return ResultUtil.userError();
                }

                User user2 = new User();
                user2.setUserId(user1.getUserId());
                user2.setToken(JWTUtil.createTokenWithClaim(user1));

                return ResultUtil.success(user2);
            }else{
                return ResultUtil.inviter();
            }
        }catch (Exception e){
            log.error("用户模块：'邮箱密码登陆'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getUserById(Integer userId) {
        try {
            //非空判断
            if(userId == null){
                return ResultUtil.paramError();
            }

            String key = "userInfo"+userId;

            //判断验证码是否存在
            if (!jedisKeys.exists(key)) {
                //校验用户
                User user = userDao.findUserById(userId);
                if(user == null){
                    return ResultUtil.inviter();
                }

                if(user.getUserStatus().toString().equals("0")){
                    return ResultUtil.userError();
                }

                jedisStrings.set(key.getBytes(),SerializeUtil.serialize(user));

                //设置过期时间两个小时
                jedisStrings.expire(key.getBytes(),7200);

                return ResultUtil.success(user);
            }else{
                User user = (User)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));
                return ResultUtil.success(user);
            }

        }catch (Exception e){
            log.error("用户模块：'获取用户详细信息'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updUserPassword(UserDto userDto) {
        try {
            //非空判断
            if(userDto == null || userDto.getUserId() == null || userDto.getPassword() == null || userDto.getNewPassword() == null){
                return ResultUtil.paramError();
            }

            //两次密码验证
            if(userDto.getPassword().equals(userDto.getNewPassword())){
                return ResultUtil.updPwdError();
            }

            String key = "userInfo"+userDto.getUserId();

            //判断用户是否存在
            if (!jedisKeys.exists(key)) {
                //校验用户
                User user = userDao.findUserById(userDto.getUserId());

                if(user == null){
                    return ResultUtil.serviceError();
                }

                //用户状态验证
                if(user.getUserStatus().toString().equals("0")){
                    return ResultUtil.userError();
                }

                //新旧密码验证
                if(!user.getPassword().equals(EncryptUtil.md5(userDto.getPassword()))){
                    return ResultUtil.updPwdTwoError();
                }

                //修改密码
                User user1 = new User();

                user1.setUserId(userDto.getUserId());
                user1.setPassword(EncryptUtil.md5(userDto.getNewPassword()));

                userDao.updUser(user1);

            }else{
                User user = (User)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));

                //新旧密码验证
                if(!user.getPassword().equals(EncryptUtil.md5(userDto.getPassword()))){
                    return ResultUtil.updPwdTwoError();
                }

                //修改密码
                User user1 = new User();

                user1.setUserId(userDto.getUserId());
                user1.setPassword(EncryptUtil.md5(userDto.getNewPassword()));

                userDao.updUser(user1);

                jedisKeys.del(key.getBytes());
            }

            return ResultUtil.success("修改密码成功");
        }catch (Exception e){
            log.error("用户模块：'修改用户密码'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updUserNikeName(UserDto userDto) {
        try {

            //非空判断
            if(userDto == null || userDto.getUserId() == null || userDto.getNickName() == null){
                return ResultUtil.paramError();
            }

            String key = "userInfo"+userDto.getUserId();

            //判断用户是否存在
            if (!jedisKeys.exists(key)) {

                //校验用户
                User user = userDao.findUserById(userDto.getUserId());

                if(user == null){
                    return ResultUtil.serviceError();
                }

                //用户状态验证
                if(user.getUserStatus().toString().equals("0")){
                    return ResultUtil.userError();
                }

                //用户昵称验证
                if(!user.getNickName().equals(userDto.getNickName())){

                    //修改昵称
                    User user1 = new User();

                    user1.setUserId(userDto.getUserId());
                    user1.setNickName(userDto.getNickName());

                    userDao.updUser(user1);

                }
            }else{
                User user = (User)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));

                //用户昵称验证
                if(!user.getNickName().equals(userDto.getNickName())){
                    //修改昵称
                    User user1 = new User();

                    user1.setUserId(userDto.getUserId());
                    user1.setNickName(userDto.getNickName());

                    userDao.updUser(user1);

                    jedisKeys.del(key.getBytes());
                }
            }

            return ResultUtil.success("修改用户昵称成功");
        }catch (Exception e){
            log.error("用户模块：'修改用户昵称'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updUserPhone(UserDto userDto) {
        try {
            //非空判断
            if(userDto == null || userDto.getUserId() == null || userDto.getPhone() == null){
                return ResultUtil.paramError();
            }

            String key = "userInfo"+userDto.getUserId();

            //判断用户是否存在
            if (!jedisKeys.exists(key)) {
                //校验用户
                User user = userDao.findUserById(userDto.getUserId());

                if(user == null){
                    return ResultUtil.serviceError();
                }

                //用户状态验证
                if(user.getUserStatus().toString().equals("0")){
                    return ResultUtil.userError();
                }

                //用户电话验证
                if(!user.getUserPhone().equals(userDto.getPhone())){

                    //修改昵称
                    User user1 = new User();

                    user1.setUserId(userDto.getUserId());
                    user1.setUserPhone(userDto.getPhone());

                    userDao.updUser(user1);
                }

            }else{
                User user = (User)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));
                //用户电话验证
                if(!user.getUserPhone().equals(userDto.getPhone())){

                    //修改昵称
                    User user1 = new User();

                    user1.setUserId(userDto.getUserId());
                    user1.setUserPhone(userDto.getPhone());

                    userDao.updUser(user1);

                    jedisKeys.del(key.getBytes());
                }
            }


            return ResultUtil.success("修改用户电话成功");
        }catch (Exception e){
            log.error("用户模块：'修改用户电话'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updUserEmail(UserDto userDto) {
        try {
            //非空判断
            if(userDto.getUserId() == null || userDto.getUserEmail() == null || userDto.getNewEmail() == null || userDto.getPassword() == null){
                return ResultUtil.paramError();
            }

            //两次输入的邮箱验证
            if(userDto.getUserEmail().equals(userDto.getNewEmail())){
                return ResultUtil.emailError();
            }

            String key = "userInfo"+userDto.getUserId();

            //判断用户是否存在
            if (!jedisKeys.exists(key)) {
                //校验用户
                User user = userDao.findUserById(userDto.getUserId());
                if(user == null){
                    return ResultUtil.serviceError();
                }

                //用户状态验证
                if(user.getUserStatus().toString().equals("0")){
                    return ResultUtil.userError();
                }

                //用户密码验证
                if(!user.getPassword().equals(EncryptUtil.md5(userDto.getPassword()))){
                    return ResultUtil.updPwdTwoError();
                }

                //修改邮箱
                User user1 = new User();

                user1.setUserId(userDto.getUserId());
                user1.setUserEmail(userDto.getNewEmail());

                userDao.updUser(user1);
            }else{
                User user = (User)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));

                //用户密码验证
                if(!user.getPassword().equals(EncryptUtil.md5(userDto.getPassword()))){
                    return ResultUtil.updPwdTwoError();
                }

                //修改邮箱
                User user1 = new User();

                user1.setUserId(userDto.getUserId());
                user1.setUserEmail(userDto.getNewEmail());

                userDao.updUser(user1);

                jedisKeys.del(key.getBytes());
            }

            return ResultUtil.success("修改用户邮箱成功");
        }catch (Exception e){
            log.error("用户模块：'修改用户邮箱'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updUserPhoto(UserDto userDto) {
        try {
            //非空判断
            if(userDto == null || userDto.getUserId() == null || userDto.getUserPhoto() == null){
                return ResultUtil.paramError();
            }

            String key = "userInfo"+userDto.getUserId();

            //判断用户是否存在
            if (!jedisKeys.exists(key)) {
                //校验用户
                User user = userDao.findUserById(userDto.getUserId());
                if(user == null){
                    return ResultUtil.serviceError();
                }

                //用户状态验证
                if(user.getUserStatus().toString().equals("0")){
                    return ResultUtil.userError();
                }

                //修改头像
                User user1 = new User();
                user1.setUserId(userDto.getUserId());
                user1.setUserPhoto(userDto.getUserPhoto());

                userDao.updUser(user1);

                //校验头像图片是否存在
                RsFile rsFile = fileDao.getFileById(userDto.getUserPhoto());
                if(rsFile == null){
                    return ResultUtil.imgError();
                }

                //把头像图片改为已用状态
                fileDao.updFile(userDto.getUserPhoto());

                if(!user.getUserPhoto().equals("default")){
                    //删除用户原有头像
                    fileDao.delFile(user.getUserPhoto());
                }
            }else{
                User user = (User)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));

                //修改头像
                User user1 = new User();
                user1.setUserId(userDto.getUserId());
                user1.setUserPhoto(userDto.getUserPhoto());

                userDao.updUser(user1);

                //校验头像图片是否存在
                RsFile rsFile = fileDao.getFileById(userDto.getUserPhoto());
                if(rsFile == null){
                    return ResultUtil.imgError();
                }

                //把头像图片改为已用状态
                fileDao.updFile(userDto.getUserPhoto());

                if(!user.getUserPhoto().equals("default")){
                    //删除用户原有头像
                    fileDao.delFile(user.getUserPhoto());
                }

                jedisKeys.del(key.getBytes());
            }

            return ResultUtil.success("修改用户头像成功");
        }catch (Exception e){
            log.error("用户模块：'修改用户头像'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result retrievePassword(UserDto userDto) {
        try {

            if(userDto == null || userDto.getUserEmail() == null || userDto.getPassword() == null || userDto.getCode() == null){
                return ResultUtil.paramError();
            }

            //校验用户
            User user = userDao.getUserByEmail(userDto.getUserEmail());
            if(user == null){
                return ResultUtil.serviceError();
            }

            //用户状态验证
            if(user.getUserStatus().toString().equals("0")){
                return ResultUtil.userError();
            }

            String key = "EmailCode"+userDto.getUserEmail();

            //判断验证码是否存在
            if (!jedisKeys.exists(key)) {
                return ResultUtil.linkError();
            }

            //修改密码
            User user1 = new User();
            user1.setUserId(user.getUserId());
            user1.setPassword(EncryptUtil.md5(userDto.getPassword()));

            userDao.updUser(user1);

            jedisKeys.del(key);

            return ResultUtil.success("找回密码成功");
        }catch (Exception e){
            log.error("用户模块：'找回密码'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getEmailCode(String email) {
        if(email == null){
            return  ResultUtil.paramError();
        }

        String str = null;

        try {
            str = MailUtil.send_mail(email);
        } catch (MessagingException e) {
            log.error("用户模块：'发送邮箱链接'接口异常"+e.getMessage());
            return ResultUtil.emailCodeError();
        }


//        //加密用户id
//        String str = CryptoUtil.encode(EncryptUtil.randomPwd36()+user.getUserId()+EncryptUtil.randomPwd36());

        //存入redis设置过期时间
        jedisStrings.set("EmailCode"+email, str);

        //设置过期时间10分钟
        jedisStrings.expire("EmailCode"+email,600);

        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result customerFeedback(MessageUs us) {
        try {

            if(us == null || us.getUserId() == null || us.getMEmail() == null || us.getMTopic() == null || us.getMContent() == null){
                return ResultUtil.paramError();
            }

            //获取用户当天反馈次数，不能超过3次！否则明天在提交
            if(userDao.getCustomerFeedbackCount(us.getUserId()) >= 3){
                return ResultUtil.FeedbackError();
            }

            //执行反馈
            userDao.addCustomerFeedback(us);

            jedisKeys.exists("UsersMessageList");

            jedisKeys.batchdel("UsersMessageList");

            return ResultUtil.success("反馈成功");
        }catch (Exception e){
            log.error("用户模块：'添加客户反馈'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getCustomerService() {
        try {

            String key = "CustomerService";
            if(!jedisKeys.exists(key)){
                List<CustomerService> customerService = userDao.getCustomerService();

                jedisStrings.set(key.getBytes(),SerializeUtil.serialize(customerService));
                //设置过期时间两个小时
                jedisStrings.expire(key.getBytes(),7200);
                return ResultUtil.success(customerService);
            }else{
                List<CustomerService> customerService = (List<CustomerService>)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));
                return ResultUtil.success(customerService);
            }
        }catch (Exception e){
            log.error("用户模块：'获取客服服务信息'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result faceBookLogin(UserDto userDto) {
        try {

            if(userDto == null || userDto.getFirstName() == null || userDto.getLastName() == null ||
                userDto.getFacebookUserId() == null || userDto.getAccessToken() == null){
                return ResultUtil.paramError();
            }

            if(userDto.getAccessToken().equals("") || userDto.getFacebookUserId().equals("")){
                return ResultUtil.paramError();
            }

//            //校验用户id准确性
//            String requestxml = new HttpsRequest().HttpsRequest("https://graph.facebook.com/debug_token","POST","access_token=366208474212027|16728656feef7c9dd002a3c6a5823b62&input_token="+userDto.getAccessToken());
//            System.out.println(requestxml);
//
//            JSONObject jsonObject = JSONObject.parseObject(requestxml);
//            String user_id = jsonObject.getJSONObject("data").get("user_id").toString();
//            if(!user_id.equals(userDto.getFacebookUserId())){
//                return ResultUtil.error("校验faceBook用户失败");
//            }

            //校验用户是否存在
            User user1 = userDao.findUserByFacebookId(userDto.getFacebookUserId());
            if(user1 == null){
                //用户不存在。注册
                user1 = new User();

                user1.setFirstName(userDto.getFirstName());
                user1.setLastName(userDto.getLastName());
                user1.setNickName("Hello "+userDto.getFirstName());   //默认姓名
                user1.setUserPhoto("default");                        //默认头像
                user1.setFacebookId(userDto.getFacebookUserId());
                user1.setUserFrom(Byte.parseByte("1"));
                //添加用户
                userDao.addUser(user1);

                user1.setUserEmail(null);
                user1.setPassword(null);
                user1.setFirstName(null);
                user1.setLastName(null);
                user1.setFacebookId(null);

                user1.setToken(JWTUtil.createTokenWithClaim(user1));

                return ResultUtil.success(user1);

            }else{
                //用户存在，直接返回信息
                user1.setUserEmail(null);
                user1.setPassword(null);
                user1.setFirstName(null);
                user1.setLastName(null);
                user1.setFacebookId(null);

                user1.setToken(JWTUtil.createTokenWithClaim(user1));

                return ResultUtil.success(user1);
            }
        }catch (Exception e){
            log.error("用户模块：'faceBook登录'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getHelpList() {
        try{

            String key = "HelpList";
            if(!jedisKeys.exists(key)){
                List<Help> helps = userDao.getHelpListByLevel(1);
                if(helps != null){
                    for (int i = 0;i < helps.size(); i++){
                        List<Help> helps1 = userDao.getHelpListByParentId(helps.get(i).getHelpId());
                        for (Help help:helps1) {
                            help.setHelpInstructionsList(help.getHelpInstructions().split(","));
                        }
                        helps.get(i).setHelpList(helps1);
                    }
                    jedisStrings.set(key.getBytes(),SerializeUtil.serialize(helps));
                    //设置过期时间两个小时
                    jedisStrings.expire(key.getBytes(),7200);

                    return ResultUtil.success(helps);
                }
                return ResultUtil.success("暂无帮助数据");
            }else{
                List<Help> helps = (List<Help>)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));
                return ResultUtil.success(helps);
            }

        }catch (Exception e){
            log.error("用户模块：'获取帮助信息'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
