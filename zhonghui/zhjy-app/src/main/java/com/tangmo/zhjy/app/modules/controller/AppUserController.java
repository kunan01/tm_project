package com.tangmo.zhjy.app.modules.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangmo.zhjy.app.utils.*;
import com.tangmo.zhjy.app.utils.jedis.JedisUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.aliyuncs.exceptions.ClientException;
import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.dto.AppCollectDto;
import com.tangmo.zhjy.app.modules.dto.AppUserDto;
import com.tangmo.zhjy.app.modules.service.AppCollectService;
import com.tangmo.zhjy.app.modules.service.AppInformationService;
import com.tangmo.zhjy.app.modules.service.AppUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/app/user")
@Api("APP用户接口")
public class AppUserController {

	@Autowired
	private AppUserService appUserServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AppCollectService appCollectServiceImpl;

	@Autowired
	private AppInformationService  appInformationServiceImpl;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

	@Value("${app.upload-head-path}")
	private String uploadHead;

	@Value("${app.upload-identity-path}")
	private String identityPath;

	@Value("${app.baidu.location.ak}")
	private String ak;


	//百度API获取地理位置接口
	private String api = "http://restapi.amap.com/v3/geocode/regeo?poitype=&radius=1000&extensions=all&batch=false&roadlevel=0&location=";

	/**
	 * 得到request对象
	 */
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 得到session对象
	 */
	protected HttpSession getSession() {
		return this.getRequest().getSession();
	}

	/**
	 * 1.注册
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="用户注册接口",notes="APP用户注册接口")
	@ApiImplicitParam(name="appUserDto",value="用户信息DTO实体类",required=true,dataType="AppUserDto")
	@PostMapping(value="/saveUser")
	public Result saveUser(@ApiParam(value="用户Dto实体类",required=true)@Valid @RequestBody AppUserDto appUserDto,BindingResult error){
		ValidationUtil.verifyDispose(error);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = appUserDto.getPhone();

        String code = null;
        try {
            code = getSession().getAttribute(key).toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        if (code == null) {
            return new Result(ResultCode.SMS_ERROR1);
        }else{
            //若存在，则直接从redis里面取出相应数
//            String code = jedisStrings.get(key);
//            String jsonString = jedisStrings.get(key+"date");
            String jsonString = getSession().getAttribute(key+"date").toString();
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000*60*60*24);
                long hour = (l / (1000*60*60)-day*24);
                long min = ((l/(1000*60))-day*24*60-hour*60);
                long s = (l/1000-day*24*60*60-hour*60*60-min*60);
                if(((day*24*60*60)+(hour*60*60)+(min*60)+s) > 180){
//                    jedisKeys.del(key);
//                    jedisKeys.del(key+"date");
                    getSession().removeAttribute(key);
                    getSession().removeAttribute(key+"date");
                    return new Result(ResultCode.FAIL,"验证码过期，请重新获取");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(!code.equals(appUserDto.getCode().toString())){
                return new Result(ResultCode.SMS_ERROR);
            }
//            jedisKeys.del(key);
//            jedisKeys.del(key+"date");
            getSession().removeAttribute(key);
            getSession().removeAttribute(key+"date");
        }

		appUserDto.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
		return appUserServiceImpl.addUser(appUserDto);
	}

	/**
	 * 2.修改
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="修改用户信息接口",notes="")
	@ApiImplicitParam(name="appUserDto",value="用户信息DTO实体类",dataType="AppUserDto",required=true)
	@PutMapping(value="/modifyUser")
	public Result modifyUser(@Valid @RequestBody AppUserDto appUserDto,BindingResult error){
		ValidationUtil.verifyDispose(error);
		if(appUserDto.getPassword()!=null){
			appUserDto.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
		}
		return appUserServiceImpl.modify(appUserDto);
	}

	/**
	 * 3.增加频道
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="用户添加关联频道",notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",value="用户编号",required=true,dataType="Long",paramType="query"),
		@ApiImplicitParam(name="twoClassifyId",value="频道编号",required=true,dataType="Long",paramType="query")
	})
	@PostMapping(value="/addMyChannel")
	public Result addMyChannel(Long userId,Long twoClassifyId){
		return appUserServiceImpl.saveChannel(userId.intValue(), twoClassifyId.intValue());
	}

    /**
     * 3.获取关联频道
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value="获取关联频道",notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户编号",required=true,dataType="Long",paramType="query")
    })
    @PostMapping(value="/getMyChannel")
    public Result getMyChannel(Long userId){
        return appUserServiceImpl.getMyChannel(userId.intValue());
    }

	/**
	 * 4.删除频道
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="用户删除关联频道",notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",value="用户编号",required=true,dataType="Long",paramType="query"),
		@ApiImplicitParam(name="twoClassifyId",value="频道编号",required=true,dataType="Long",paramType="query")
	})
	@DeleteMapping(value="/deleteMyChannel")
	public Result deleteMyChannel(Integer userId,Integer twoClassifyId){
		return appUserServiceImpl.removeChannel(userId, twoClassifyId);
	}

	/**
	 * 5.头像上传接口
	 * @Title: uploadHead
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    设定文件
	 * @return Result    返回类型
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="头像上传接口",notes="并且修改用户信息")
	@PostMapping(value="/filesUpload",consumes="multipart/*",headers="content-type=multipart/form-data")
	public Result uploadHead(@ApiParam(name="file",value="图片上传",required=true) MultipartFile file,@ApiParam(name="userId",value="用户编号",required=true)@RequestParam Integer userId){
		Result result=FileUploadUtil.upload(file, uploadHead);
		if(result.getData()!=null){
			AppUserDto appUserBean = new AppUserDto();
			appUserBean.setId(userId);
			appUserBean.setHeadImage((String) result.getData());
			appUserServiceImpl.modify(appUserBean);
		}
		return result;
	}

	/**
	 * 发送短信验证码
	 * @throws ClientException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="发送短信验证码",notes="")
	@GetMapping(value="/sendSmsCode/{phone}")
	public Result sendSmsCode(@PathVariable String phone) throws ClientException{
        if(phone == null || phone.equals("")){
            return new Result(ResultCode.PHONE_ERROR1,"手机号不能为空");
        }
        String code = null;
        try {
            code = getSession().getAttribute(phone).toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (code != null) {
            String jsonString = getSession().getAttribute(phone+"date").toString();
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000*60*60*24);
                long hour = (l / (1000*60*60)-day*24);
                long min = ((l/(1000*60))-day*24*60-hour*60);
                long s = (l/1000-day*24*60*60-hour*60*60-min*60);
                if(((day*24*60*60)+(hour*60*60)+(min*60)+s) <= 60){
                    return new Result(ResultCode.FAIL,"请求次数频繁，请休息一会再试！");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        code = new SmsCodeUtil().sendSms(phone);
        getSession().setAttribute(phone, code);
        getSession().setAttribute(phone+"date", df.format(new Date()).toString());
//        jedisStrings.set(phone, code);
//        jedisStrings.set(phone+"date", df.format(new Date()).toString());
        return new Result(ResultCode.SUCCESS,code);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="短信验证登录",notes="")
	@ApiImplicitParams({
			@ApiImplicitParam(name="phone",value="用户账号",required=true,dataType="string",paramType="query"),
			@ApiImplicitParam(name="code",value="验证码",required=true,dataType="string",paramType="query")
	})
	@PostMapping(value="/smsLogin")
	public Result smsLogin(String phone,String code){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = phone;
        String codes = null;
        try {
            codes = getSession().getAttribute(key).toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        if (codes == null) {
            return new Result(ResultCode.SMS_ERROR1);
        }else{
            //若存在，则直接从redis里面取出相应数
//            String codes = jedisStrings.get(key);
            String jsonString = getSession().getAttribute(key+"date").toString();
//            String jsonString = jedisStrings.get(key+"date");
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000*60*60*24);
                long hour = (l / (1000*60*60)-day*24);
                long min = ((l/(1000*60))-day*24*60-hour*60);
                long s = (l/1000-day*24*60*60-hour*60*60-min*60);
                if(((day*24*60*60)+(hour*60*60)+(min*60)+s) > 180){
                    jedisKeys.del(key);
                    jedisKeys.del(key+"date");
                    return new Result(ResultCode.FAIL,"验证码过期，请重新获取");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(!codes.equals(code.toString())){
                return new Result(ResultCode.SMS_ERROR);
            }
            getSession().removeAttribute(key);
            getSession().removeAttribute(key+"date");
//            jedisKeys.del(key);
//            jedisKeys.del(key+"date");
        }
//
//        Result result = appUserServiceImpl.findByPhone(phone);
//
//        if(result.getCode()!=0){
//            return new Result(ResultCode.FAIL,"该用户不存在！");
//        }
//        AppUserBean appUserBean=(AppUserBean)result.getData();
//
//        String token = AuthorizeUtil.getToken(appUserBean.getPhone(), appUserBean.getPassword());
//        if(token.equals("")){
//            return new Result(ResultCode.FAIL);
//        }else{
//            com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(token);
//            //json.put("phone",username);
//            return new Result(ResultCode.SUCCESS,json);
//        }
		return appUserServiceImpl.smsLogin(phone, code,getSession());
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="找回密码",notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(name="phone",value="用户账号",required=true,dataType="string",paramType="query"),
		@ApiImplicitParam(name="password",value="用户新密码",required=true,dataType="string",paramType="query"),
        @ApiImplicitParam(name="code",value="验证码",required=true,dataType="string",paramType="query")
	})
	@PutMapping(value="/forgetPassword")
	public Result forgetPassword(String phone,String password,String code){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = phone;

        String codes = null;
        try {
            codes = getSession().getAttribute(key).toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (codes == null) {
            return new Result(ResultCode.SMS_ERROR1);
        }else{
            //若存在，则直接从redis里面取出相应数
//            String codes = jedisStrings.get(key);
//            String jsonString = jedisStrings.get(key+"date");
            String jsonString = getSession().getAttribute(key+"date").toString();
            try {
                Date d1 = df.parse(df.format(new Date()).toString());
                Date d2 = df.parse(jsonString);
                long l = d1.getTime() - d2.getTime();
                long day = l / (1000*60*60*24);
                long hour = (l / (1000*60*60)-day*24);
                long min = ((l/(1000*60))-day*24*60-hour*60);
                long s = (l/1000-day*24*60*60-hour*60*60-min*60);
                if(((day*24*60*60)+(hour*60*60)+(min*60)+s) > 180){
//                    jedisKeys.del(key);
//                    jedisKeys.del(key+"date");
                    getSession().removeAttribute(key);
                    getSession().removeAttribute(key+"date");
                    return new Result(ResultCode.FAIL,"验证码过期，请重新获取");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(!codes.equals(code.toString())){
                return new Result(ResultCode.SMS_ERROR);
            }
//            jedisKeys.del(key);
//            jedisKeys.del(key+"date");
            getSession().removeAttribute(key);
            getSession().removeAttribute(key+"date");
        }
		return appUserServiceImpl.updatePassword(phone, passwordEncoder.encode(password));
	}

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="修改密码",notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="用户id",required=true,dataType="int",paramType="query"),
            @ApiImplicitParam(name="password",value="用户原密码",required=true,dataType="string",paramType="query"),
            @ApiImplicitParam(name="newPassword",value="用户新密码",required=true,dataType="string",paramType="query")
    })
    @PutMapping(value="/updPassword")
    public Result updPassword(Integer id,String password,String newPassword){
        return appUserServiceImpl.updPassword(id, password,newPassword);
    }

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="校验用户名密码是否正确",notes="")
	@GetMapping(value="/findByUserAndPassword")
	public Result findByUserAndPassword(@RequestParam String phone,@RequestParam String password){
		return appUserServiceImpl.findByUserAndPassword(phone,password, passwordEncoder);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="身份证上传接口",notes="")
	@PostMapping(value="/uploadIdentityImg",consumes="multipart/*",headers="content-type=multipart/form-data")
	public Result uploadIdentityImg(@ApiParam(name="file",value="图片上传",required=true) MultipartFile file){
		Result result=FileUploadUtil.upload(file, identityPath);
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="获取用户所在市",notes="")
	@GetMapping(value="/getLocation")
	public Result getLocation(@RequestParam String location) throws Exception{
		StringBuffer buffer=null;
		try {
			StringBuffer api2 = new StringBuffer(api);
			api2.append(location);
			api2.append("&key="+ak);
			URL url = new URL(api2.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.connect();

			//读取服务器端返回的内容
			InputStream is=conn.getInputStream();
			InputStreamReader isr=new InputStreamReader(is,"utf-8");
			BufferedReader br=new BufferedReader(isr);
			buffer=new StringBuffer();
			String line=null;
			while((line=br.readLine())!=null){
				buffer.append(line);
			}
			System.out.println(buffer.toString());
			JSONObject jsonObject = new JSONObject(buffer.toString());
			JSONObject result=jsonObject.getJSONObject("regeocode");
			JSONObject addressComponent=result.getJSONObject("addressComponent");
			Object city= addressComponent.get("city");
			if(city instanceof JSONArray){
				city = (String) addressComponent.get("province");
			}
			return new Result(ResultCode.SUCCESS,city);
		} catch (Exception e) {
			return new Result(ResultCode.WEAK_NET_WORK,"获取定位失败");
		}

	}

	/**
	 *
	* @Title: collectInfo
	* @Description: TODO(收藏文章接口)
	* @param @param appCollectDto
	* @param @return    设定文件
	* @return Result    返回类型
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="收藏文章")
	@GetMapping(value="/collectInfo/{informationid}/{userId}")
	public Result collectInfo(@PathVariable Integer informationid,@PathVariable Integer userId){
		AppCollectDto appCollectDto = new AppCollectDto();
		appCollectDto.setInformationid(informationid);
		appCollectDto.setUserId(userId);
		return appCollectServiceImpl.save(appCollectDto);
	}

	/**
     *
	* @Title: collectInfo
	* @Description: TODO(收藏文章接口)
	* @param @param appCollectDto
	* @param @return    设定文件
	* @return Result    返回类型
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="收藏文章")
	@PostMapping(value="/collectInfo")
	public Result collectInfo(AppCollectDto appCollectDto){
		return appCollectServiceImpl.save(appCollectDto);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="删除收藏文章")
	@ApiImplicitParams({
			@ApiImplicitParam(name="informationid",dataType="int",required=true,paramType="query"),
			@ApiImplicitParam(name="userId",dataType="int",required=true,paramType="query")
	})
	@PostMapping(value="/collectRemove")
	public Result collectRemove(Integer informationid,Integer userId){
		AppCollectDto appCollectDto = new AppCollectDto();
		appCollectDto.setUserId(userId);
		appCollectDto.setInformationid(informationid);
		return appCollectServiceImpl.delete(appCollectDto);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="根据用户ID查询收藏文章")
	@GetMapping(value="/selectCollect/{userId}/{pageNo}/{pageSize}")
	public Result selectCollect(@ApiParam(name="userId")@PathVariable Integer userId,@ApiParam(name="pageNo")@PathVariable Integer pageNo,@ApiParam(name="pageSize")@PathVariable Integer pageSize){
		return appInformationServiceImpl.findByUserId(userId,pageNo,pageSize);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value="查询用户是否收藏该文章")
	@GetMapping(value="/selectUserAndCollect/{informationid}/{userId}")
	public Result selectUserAndCollect(@PathVariable Integer informationid,@PathVariable Integer userId){
		AppCollectDto appCollectDto = new AppCollectDto();
		appCollectDto.setInformationid(informationid);
		appCollectDto.setUserId(userId);
		return appCollectServiceImpl.selectById(appCollectDto);
	}

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="用户名密码登陆")
	@ApiImplicitParams({
			@ApiImplicitParam(name="phone",dataType="string",required=true,paramType="query"),
			@ApiImplicitParam(name="password",dataType="string",required=true,paramType="query")
	})
    @PostMapping("/token")
	public Result getUserToken(String phone, String password){

//		Result result = appUserServiceImpl.findByPhone(username);
//
//		if(result.getCode()!=0){
//			return new Result(ResultCode.FAIL,"该用户不存在！");
//		}else{
//			AppUserBean appUserBean=(AppUserBean)result.getData();
//			if(!passwordEncoder.matches(password, appUserBean.getPassword())){
//
//				return new Result(ResultCode.PASSWORD_ERROR);
//			}
//		}
//		System.out.print("======="+username+"====="+password);
//		String token = AuthorizeUtil.getToken(username, password);
//		if(token.equals("")){
//			return new Result(ResultCode.FAIL);
//		}else{
//			com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(token);
//			//json.put("phone",username);
//			return new Result(ResultCode.SUCCESS,json );
//		}
        return appUserServiceImpl.smsLoginByPass(phone, password);
	}

    @SuppressWarnings("rawtypes")
    @ApiOperation(value="获取用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",dataType="int",required=true,paramType="query")
    })
    @GetMapping("/getUserById")
    public Result getUserById(Integer id){

//		Result result = appUserServiceImpl.findByPhone(username);
//
//		if(result.getCode()!=0){
//			return new Result(ResultCode.FAIL,"该用户不存在！");
//		}else{
//			AppUserBean appUserBean=(AppUserBean)result.getData();
//			if(!passwordEncoder.matches(password, appUserBean.getPassword())){
//
//				return new Result(ResultCode.PASSWORD_ERROR);
//			}
//		}
//		System.out.print("======="+username+"====="+password);
//		String token = AuthorizeUtil.getToken(username, password);
//		if(token.equals("")){
//			return new Result(ResultCode.FAIL);
//		}else{
//			com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(token);
//			//json.put("phone",username);
//			return new Result(ResultCode.SUCCESS,json );
//		}
        return appUserServiceImpl.getUserById(id);
    }
}
