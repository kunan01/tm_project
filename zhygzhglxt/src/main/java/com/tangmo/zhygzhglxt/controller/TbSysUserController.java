package com.tangmo.zhygzhglxt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangmo.zhygzhglxt.controller.base.BaseController;
import com.tangmo.zhygzhglxt.entity.TbSysUser;
import com.tangmo.zhygzhglxt.entity.dto.UserDto;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.utility.FileUploadUtil;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import com.tangmo.zhygzhglxt.utility.jedis.JedisUtil;
import com.tangmo.zhygzhglxt.utility.sendMsg.WxwsMsg;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengge on 2018/10/22.
 */
@Api("用户相关的接口")
@RestController
@RequestMapping("/user")
public class TbSysUserController extends BaseController {

    @Value("${app.upload-head-path}")
    private String uploadHead;

    @Value("${app.upload-identity-path}")
    private String identityPath;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @ApiOperation(value = "乘客用户注册（短信注册）", notes = "")
    @ApiImplicitParam(name = "userDto", value = "用户信息Dto", required = true, dataType = "UserDto")
    @PostMapping("/saveUser")
    public Result saveUser(@RequestBody UserDto userDto) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = userDto.getPhone();
        if (!jedisKeys.exists(key)) {
            return new Result(ResultCode.SMS_ERROR1);
        } else {
            //若存在，则直接从redis里面取出相应数
            String code = jedisStrings.get(key);
            String jsonString = jedisStrings.get(key + "date");
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000 * 60 * 60 * 24);
                long hour = (l / (1000 * 60 * 60) - day * 24);
                long min = ((l / (1000 * 60)) - day * 24 * 60 - hour * 60);
                long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                if (((day * 24 * 60 * 60) + (hour * 60 * 60) + (min * 60) + s) > 180) {
                    jedisKeys.del(key);
                    jedisKeys.del(key + "date");
                    return new Result(ResultCode.FAIL, "验证码过期，请重新获取");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (!code.equals(userDto.getAuthCode().toString())) {
                return new Result(ResultCode.SMS_ERROR2);
            }
            jedisKeys.del(key);
            jedisKeys.del(key + "date");
        }

//        String code = (String)getSession().getAttribute(userDto.getPhone());
//
//        System.out.println("======="+code);
//        if("".equals(userDto.getAuthCode())||userDto.getAuthCode() == null){
//            return new Result(ResultCode.FAIL,"验证码不能为空！");
//        }
//        if(code == null){
//            return new Result(ResultCode.SMS_ERROR1);
//        }
//        if(!code.equals(userDto.getAuthCode())){
//            return new Result(ResultCode.SMS_ERROR2);
//        }
        return tbSysUserService.addAppUser(userDto);
    }

//    @ApiOperation(value="司机用户注册（短信注册）",notes="")
//    @ApiImplicitParam(name="tbDriverVerifyDto",value="司机用户信息Dto",required=true,dataType="TbDriverVerifyDto")
//    @PostMapping("/saveDriverUser")
//    public Result saveDriverUser(@RequestBody TbDriverVerifyDto tbDriverVerifyDto){
//
//        String code = (String)this.getSession().getAttribute(tbDriverVerifyDto.getPhone());
//
//        System.out.println("======="+code);
//
//        if(code == null){
//            return new Result(ResultCode.SMS_ERROR1);
//        }
//        if(!code.equals(tbDriverVerifyDto.getAuthCode())){
//            return new Result(ResultCode.SMS_ERROR2);
//        }
//
//        return tbSysUserService.saveDriverUser(tbDriverVerifyDto);
//    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "后台添加用户接口", notes = "后台添加用户接口")
    @ApiImplicitParam(name = "tbSysUser", value = "用户信息实体", required = true, dataType = "TbSysUser")
    @PostMapping(value = "/saveSysUser")
    public Result saveSysUser(@RequestBody TbSysUser tbSysUser) {

        return tbSysUserService.saveSysUser(tbSysUser);
    }

    /**
     * 发送短信验证码
     *
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @ApiOperation(value = "发送短信验证码", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "电话", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping(value = "/sendSmsCode")
    public Result sendSmsCode(String phone) throws Exception {
        if (phone == null || phone.equals("")) {
            return new Result(ResultCode.PHONE_ERROR1, "手机号不能为空");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        if (jedisKeys.exists(phone) && jedisKeys.exists(phone + "date")) {
            String jsonString = jedisStrings.get(phone + "date");   //取出之前发送的短信请求的时间
            try {
                Date d1 = df.parse(df.format(new Date()).toString());  //当前时间
                Date d2 = df.parse(jsonString);                         //第一次发送短信的时间
                long l = d1.getTime() - d2.getTime();                  //多少毫秒
                long day = l / (1000 * 60 * 60 * 24);                         //天数
                long hour = (l / (1000 * 60 * 60) - day * 24);                  //小时
                long min = ((l / (1000 * 60)) - day * 24 * 60 - hour * 60);           //分钟
                long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);       //秒
                if (((day * 24 * 60 * 60) + (hour * 60 * 60) + (min * 60) + s) <= 60) {
                    return new Result(ResultCode.FAIL, "请求次数频繁，请休息一会再试！");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //String code="123";
        String code = WxwsMsg.sendMsg(phone);
//                String code=new SmsCodeUtil().sendSms(phone);
//                this.getSession().setAttribute(phone,code);
//                this.getSession().setAttribute("code",code);
//                this.getSession().setAttribute("phone",phone);
//                this.getSession().setAttribute("date",new Date());
        //getSession().setAttribute(phone,code);
        jedisStrings.set(phone, code);
        jedisStrings.set(phone + "date", df.format(new Date()).toString());
        return new Result(ResultCode.SUCCESS, code);

    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "短信验证码登录", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户电话", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型（0乘客端 1司机端）", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping(value = "/smsLogin")
    public Result smsLogin(String phone, String code, String type) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = phone;
        if (!jedisKeys.exists(key)) {
            return new Result(ResultCode.SMS_ERROR1);
        } else {
            //若存在，则直接从redis里面取出相应数
            String codes = jedisStrings.get(key);
            String jsonString = jedisStrings.get(key + "date");
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000 * 60 * 60 * 24);
                long hour = (l / (1000 * 60 * 60) - day * 24);
                long min = ((l / (1000 * 60)) - day * 24 * 60 - hour * 60);
                long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                if (((day * 24 * 60 * 60) + (hour * 60 * 60) + (min * 60) + s) > 180) {
                    jedisKeys.del(key);
                    jedisKeys.del(key + "date");
                    return new Result(ResultCode.FAIL, "验证码过期，请重新获取");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (!codes.equals(code.toString())) {
                return new Result(ResultCode.SMS_ERROR2);
            }
            jedisKeys.del(key);
            jedisKeys.del(key + "date");
        }
//        String codes = (String)getSession().getAttribute(phone);
//
//        System.out.println(codes+"======="+code);
//        if(codes.equals(code)){
        return tbSysUserService.smsLogin(phone, code, this.getSession(), type);
//        }else{
//            return new Result(ResultCode.SMS_ERROR2);
//        }
    }


    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "根据用户唯一标识code获取用户基本信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户的唯一标识", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping(value = "/getUserInfoByCode")
    public Result getUserInfoByCode(String userCode) {

        return tbSysUserService.getUserInfoByCode(userCode);
    }


    @ApiOperation(value = "用户登录乘客端/司机端/后台管理系统接口", notes = "用户登录乘客端/司机端/后台管理系统接口")
    @ApiImplicitParam(name = "userDto", value = "用户实体DTO", required = true, dataType = "UserDto")
    @PostMapping("/login")
    public Result userLogin(@RequestBody UserDto userDto) {

        return tbSysUserService.login(userDto);
    }

    @ApiOperation(value = "用户密码重置接口", notes = "用户密码重置接口")
    @ApiImplicitParam(name = "userDto", value = "用户实体DTO", required = true, dataType = "UserDto")
    @PostMapping("/pwd/reset")
    public Result resetPwd(@RequestBody UserDto userDto) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = userDto.getPhone();
        if (!jedisKeys.exists(key)) {
            return new Result(ResultCode.SMS_ERROR1);
        } else {
            //若存在，则直接从redis里面取出相应数
            String code = jedisStrings.get(key);
            String jsonString = jedisStrings.get(key + "date");
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000 * 60 * 60 * 24);
                long hour = (l / (1000 * 60 * 60) - day * 24);
                long min = ((l / (1000 * 60)) - day * 24 * 60 - hour * 60);
                long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                if (((day * 24 * 60 * 60) + (hour * 60 * 60) + (min * 60) + s) > 180) {
                    jedisKeys.del(key);
                    jedisKeys.del(key + "date");
                    return new Result(ResultCode.FAIL, "验证码过期，请重新获取");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!code.equals(userDto.getAuthCode().toString())) {
                return new Result(ResultCode.SMS_ERROR2);
            }
            jedisKeys.del(key);
            jedisKeys.del(key + "date");
        }

        TbSysUser result = (TbSysUser) tbSysUserService.selUserByPhone(userDto.getPhone()).getData();
        if (result == null) {
            return new Result(ResultCode.PHONE_ERROR1);
        }

        return tbSysUserService.resetPwd(userDto);
    }

    @ApiOperation(value = "修改用户信息(sex 1:男 0:女)", notes = "APP用户修改个人信息接口")
    @ApiImplicitParam(name = "userDto", value = "用户实体DTO", required = true, dataType = "UserDto")
    @PostMapping("/updateUser")
    public Result changeUserInfo(@RequestBody UserDto userDto) {

        return tbSysUserService.changeUser(userDto);
    }

    @ApiOperation(value = "修改用户手机号", notes = "用户修改手机号接口")
    @ApiImplicitParam(name = "userDto", value = "用户实体DTO", required = true, dataType = "UserDto")
    @PutMapping("/mobile")
    public Result changeMobile(@RequestBody UserDto userDto) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = userDto.getPhone();
        if (!jedisKeys.exists(key)) {
            return new Result(ResultCode.SMS_ERROR1);
        } else {
            //若存在，则直接从redis里面取出相应数
            String code = jedisStrings.get(key);
            String jsonString = jedisStrings.get(key + "date");
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000 * 60 * 60 * 24);
                long hour = (l / (1000 * 60 * 60) - day * 24);
                long min = ((l / (1000 * 60)) - day * 24 * 60 - hour * 60);
                long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                if (((day * 24 * 60 * 60) + (hour * 60 * 60) + (min * 60) + s) > 180) {
                    jedisKeys.del(key);
                    jedisKeys.del(key + "date");
                    return new Result(ResultCode.FAIL, "验证码过期，请重新获取");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!code.equals(userDto.getAuthCode().toString())) {
                return new Result(ResultCode.SMS_ERROR2);
            }
            jedisKeys.del(key);
            jedisKeys.del(key + "date");
        }

        TbSysUser result = (TbSysUser) tbSysUserService.selUserByPhone(userDto.getPhone()).getData();

        if (result == null) {
            return new Result(ResultCode.PHONE_ERROR1);
        }

        if (!result.getCode().equals(userDto.getCode())) {
            return new Result(ResultCode.FAIL, "手机号错误");
        }

        TbSysUser tbSysUser = (TbSysUser) tbSysUserService.selUserByPhone(userDto.getUserNewPhone()).getData();

        if (tbSysUser != null) {
            return new Result(ResultCode.PHONE_ERROR);
        }

        return tbSysUserService.updateUserPhone(userDto.getCode(), userDto.getUserNewPhone());
    }

    @ApiOperation(value = "修改用户密码", notes = "APP用户修改密码接口")
    @ApiImplicitParam(name = "userDto", value = "用户实体DTO", required = true, dataType = "UserDto")
    @PutMapping("/pwd")
    public Result changePwd(@RequestBody UserDto userDto) {

        return tbSysUserService.changePwd(userDto);
    }

    @ApiOperation(value = "修改用户支付密码", notes = "APP用户修改支付密码接口")
    @ApiImplicitParam(name = "userDto", value = "用户信息DTO实体类", required = true, dataType = "UserDto")
    @PutMapping("/payPwd")
    public Result changePayPwd(@RequestBody UserDto userDto) {

        return tbSysUserService.changePayPwd(userDto);
    }

    @ApiOperation(value = "设置用户支付密码", notes = "APP用户设置支付密码接口")
    @ApiImplicitParam(name = "userDto", value = "用户实体DTO", required = true, dataType = "UserDto")
    @PutMapping("/setPayPwd")
    public Result setPayPwd(@RequestBody UserDto userDto) {
        return tbSysUserService.setPayPwd(userDto);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "头像上传接口", notes = "并且修改用户信息")
    @PostMapping(value = "/filesUpload/{code}", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result uploadHead(@ApiParam(name = "file", value = "图 片上传", required = true) MultipartFile file, @PathVariable String code) {
        Result result = FileUploadUtil.upload(file, uploadHead);
        if (result.getData() != null) {
            UserDto appUserBean = new UserDto();
            appUserBean.setCode(code);
            appUserBean.setImgUrl((String) result.getData());
            tbSysUserService.changeUser(appUserBean);
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "用户头像上传接口", notes = "用户头像上传接口")
    @PostMapping(value = "/sysUploadHead", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result sysUploadHead(@ApiParam(name = "file", value = "图 片上传", required = true) MultipartFile file) {

        return FileUploadUtil.upload(file, uploadHead);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "证件照上传传接口", notes = "证件照上传传接口")
    @PostMapping(value = "/uploadIdentity", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result uploadIdentity(@ApiParam(name = "file", value = "图 片上传", required = true) MultipartFile file) {

        return FileUploadUtil.upload(file, identityPath);
    }


    /*
     * 模糊查找所有用户（可分页）
     * */
    @ApiOperation(value = "后台模糊查找所有用户（可分页）", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "关键字", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "userStatus", value = "用户的身份", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query")
    })
    @GetMapping("/selAllUser")
    public Map selAllUser(String name, String userStatus, Integer pageSize, Integer pageNo) {

        Result result = tbSysUserService.selAllUser(name, userStatus, pageSize, pageNo);
        PageInfo pageInfo = (PageInfo) result.getData();
        Map map = new HashMap();
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("data", pageInfo.getList());
        map.put("pageNo", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        map.put("total", pageInfo.getTotal());
        return map;
    }


    /*
     * 根据用户的唯一标识code概念性删除
     * */
    @ApiOperation(value = "根据用户的唯一标识code概念性删除", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "唯一标识", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/delByUserCode")
    public Result delByUserCode(String code) {

        return tbSysUserService.delByUserCode(code);
    }

    /*
     * 根据用户的唯一标识code修改用户的状态（冻结1或者解除冻结0）
     * */
    @ApiOperation(value = "根据用户的唯一标识code修改用户的状态（冻结1或者解除冻结0）", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "唯一标识", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "state", value = "用户的状态(0改为正常 1冻结)", dataType = "string", required = true, paramType = "query")
    })
    @GetMapping("/updateStateByUserCode")
    public Result updateStateByUserCode(String code, String state) {

        return tbSysUserService.updateStateByUserCode(code, state);
    }

    @ApiOperation(value = "后台修改用户信息", notes = "后台修改用户信息")
    @ApiImplicitParam(name = "tbSysUser", value = "用户实体", required = true, dataType = "TbSysUser")
    @PutMapping("/updateSysUser")
    public Result updateSysUser(@RequestBody TbSysUser TbSysUser) {

        return tbSysUserService.updateSysUser(TbSysUser);
    }

    @ApiOperation(value = "获取sessionId")
    @GetMapping("/getSessionId")
    public Result getSessionId() {

        return new Result(ResultCode.SUCCESS, this.getSession().getId());
    }


    @ApiOperation(value = "根据唯一标识查找用户详情信息")
    @ApiImplicitParam(name = "code", value = "用户唯一标识", required = true, dataType = "string", paramType = "query")
    @PostMapping("/jtQueryById")
    public Result jtQueryById(String code) {

        return tbSysUserService.jtQueryById(code);
    }


    @ApiOperation(value = "查询所有用户或车主")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模糊参数 真实姓名 用户名 昵称", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "userStatus", value = "用户身份 1乘客用户，2车主，0管理员", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "parmId", value = "车辆类型id", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "parmTypeId", value = "服务类型id", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几条", dataType = "int", required = false, paramType = "query")
    })
    @PostMapping("/jtQueryList")
    public Map jtQueryList(String name, String userStatus, String parmId, String parmTypeId, Integer pageNo, Integer pageSize) {

        Result result = tbSysUserService.jtQueryList(name, userStatus, parmId, parmTypeId, pageNo, pageSize);

        PageInfo pageInfo = (PageInfo) result.getData();
        Map map = new HashMap();
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("data", pageInfo.getList());
        map.put("pageNo", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    /*
     * 后台统计所有用户
     * */
    @ApiOperation(value = "后台统计所有用户", notes = "")
    @SuppressWarnings("rawtypes")
    @GetMapping("/selAllUserCount")
    public Result selAllUserCount() {

        return tbSysUserService.selAllUserCount();
    }


    /*
     * 后台按年统计所有用户
     * */
    @ApiOperation(value = "后台按年统计所有用户", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年", dataType = "string", required = true, paramType = "query"),
    })
    @GetMapping("/selAllUserCountByYear")
    public Result selAllUserCountByYear(String year) {

        return tbSysUserService.selAllUserCountByYear(year);
    }

    /*
     * 后台按月统计所有用户
     * */
    @ApiOperation(value = "后台按月统计所有用户", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "yearMonth", value = "年月", dataType = "string", required = true, paramType = "query"),
    })
    @GetMapping("/selAllUserCountByMonth")
    public Result selAllUserCountByMonth(String yearMonth) {

        return tbSysUserService.selAllUserCountByMonth(yearMonth);
    }

    /*
     *
     * */
    @ApiOperation(value = "后台按开始时间和结束时间查询每一天的用户数", notes = "")
    @SuppressWarnings("rawtypes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始时间", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", dataType = "string", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页几行", dataType = "int", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "int", required = false, paramType = "query"),
    })
    @GetMapping("/selAllUserCountByDate")
    public Map selAllUserCountByDate(String startDate, String endDate, Integer pageSize, Integer pageNo) {
        Result result = tbSysUserService.selAllUserCountByDate(startDate, endDate, pageSize, pageNo);
        PageInfo pageInfo = (PageInfo) result.getData();
        Map map = new HashMap();
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        map.put("data", pageInfo.getList());
        map.put("pageNo", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        map.put("total", pageInfo.getTotal());
        return map;
    }


//    public static void main(String[] args) throws ParseException {
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d1 = df.parse(df.format(new Date()).toString());
//        Date d2 = df.parse("2019-1-13 14:12:00");
//        long l = d1.getTime() - d2.getTime();
//        long day = l / (1000*60*60*24);
//        long hour = (l / (1000*60*60)-day*24);
//        long min = ((l/(1000*60))-day*24*60-hour*60);
//        long s = (l/1000-day*24*60*60-hour*60*60-min*60);
//        System.out.println("===相差毫秒======"+l);
//        System.out.println("===天======"+day);
//        System.out.println("===小时======"+hour);
//        System.out.println("===分钟======"+min);
//        System.out.println("===秒======"+s);
//        if(((day*24*60*60)+(hour*60*60)+(min*60)+s) > 180){
//            System.out.println("===="+((day*24*60*60)+(hour*60*60)+(min*60)+s));
//            System.out.println("验证码过期，请重新获取");
//            System.out.println("===测试======"+(2/1));
//        }
//
//    }

}
