package com.tangmo.yiliao.controller.appController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.DoctorApplied;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description 医生
 */
@Api("app医生")
@RestController
@RequestMapping("/doctor")
public class DoctorController extends BaseController {

    /**
     * @api {GET} /doctor/finePhysician 获取首页精品医师
     * @apiGroup Doctor
     * @apiVersion 0.0.1
     * @apiDescription 获取首页轮播视频
     * @apiParamExample {json} 请求样例：
     *  /doctor/finePhysician
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               userId: 医师主键id,
     *                               name:"医师姓名",
     *                               ddPosition:"医院职务",
     *                               ddField:"科室id",
     *                               userImgUrl:"头像路径"
     *                           }
     *                       }
     */
    @GetMapping("/finePhysician")
    public Result getFinePhysician(){
        return doctorService.getFinePhysician();
    }

    /**
     * @api {GET} /doctor/getDepartmentAllDoctorTop3 获取所有科室排名前三医师
     * @apiGroup Doctor
     * @apiVersion 0.0.1
     * @apiDescription 获取所有科室排名前三医师
     * @apiParamExample {json} 请求样例：
     *  /doctor/getDepartmentAllDoctorTop3
     * @apiSuccess (success) {GET} code  0:请求成功;
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                              [dtId:"科室id",dtName:"科室名称"]:{
     *                                  userId: 医师主键id,
     *                                  name:"医师姓名",
     *                                  ddPosition:"医院职务",
     *                                  ddService:"擅长领域",
     *                                  integral:"积分",
     *                                  userImgUrl:"头像路径"
     *                              }
     *                           }
     *                       }
     */
    @GetMapping("/getDepartmentAllDoctorTop3")
    public Result getDepartmentAllDoctorTop3(){
        return doctorService.getDepartmentAllDoctorTop3();
    }

    /**
     * @api {GET} /doctor/getDepartmentDoctorAllById/{dtId}/{start}/{end}  获取某个科室下所有医师(分页)
     * @apiGroup Doctor
     * @apiVersion 0.0.1
     * @apiDescription 获取某个科室下所有医师(分页)
     * @apiParam {String} dtId  科室id
     * @apiParam {int} start  页数
     * @apiParam {int} end    条数
     * @apiParamExample {json} 请求样例：
     *  /doctor/getDepartmentDoctorAllById/{dtId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:参数有误
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                                  userId: 医师主键id,
     *                                  name:"医师姓名",
     *                                  ddPosition:"医院职务",
     *                                  integral:"积分",
     *                                  userImgUrl:"头像路径"
     *
     *                           }
     *                       }
     */
    @GetMapping("/getDepartmentDoctorAllById/{dtId}/{start}/{end}")
    public Result getDepartmentDoctorAllById(@PathVariable String dtId,@PathVariable Integer start,@PathVariable Integer end){
        if(dtId == null || start == 0){
            return ResultUtil.paramError();
        }
        return doctorService.getDepartmentDoctorAllById(dtId,start,end);
    }

    /**
     * @api {POST} /doctor/applyForDoctor  申请医师
     * @apiGroup Doctor
     * @apiVersion 0.0.1
     * @apiDescription 申请医师
     * @apiParam {DoctorApplied} doctorApplied  医师对象
     * @apiParamExample {json} 请求样例：
     *                          {
     *                              userId:"用户id",
     *                              daHospital:"坐诊医院",
     *                              dtId:"就诊科室id",
     *                              daPosition:"职位",
     *                              daDisease:"擅长疾病",
     *                              daCertificateName:"证书名称",
     *                              daCertificate:"证书图片id",
     *                              phone:"联系电话",
     *                              daAchievement:"主要成就"
     *                          }
     * @apiSuccess (success) {POST} code  0:请求成功;  10005:参数有误
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @PostMapping("/applyForDoctor")
    public Result applyForDoctor(@RequestBody DoctorApplied doctorApplied){
        if(doctorApplied == null || doctorApplied.getUserId().equals("")){
            return ResultUtil.paramError();
        }
        return doctorService.applyForDoctor(doctorApplied);
    }

    /**
     * @api {GET} /doctor/getUserApplicationStatus/{userId}/{type}  获取医生申请状态
     * @apiGroup Doctor
     * @apiVersion 0.0.1
     * @apiDescription 申请医师
     * @apiParam {String} userId  用户id
     * @apiParam {Byte} type  0申请医生   1修改资料
     * @apiParamExample {json} 请求样例：
     *      /doctor/getUserApplicationStatus/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:参数有误
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{0:"未申请过";1:"正在审核中";2审核已通过;3审核未通过}
     *                       }
     */
    @GetMapping("/getUserApplicationStatus/{userId}/{type}")
    public Result getUserApplicationStatus(@PathVariable String userId,@PathVariable Byte type){
        if(userId == null || userId.equals("") || type == null || type.toString().equals("")){
            return ResultUtil.paramError();
        }
        return doctorService.getUserApplicationStatus(userId,type);
    }

    /**
     * @api {GET} /doctor/getDoctorInformation/{userId}  获取医生资料
     * @apiGroup Doctor
     * @apiVersion 0.0.1
     * @apiDescription 获取医生我的资料
     * @apiParam {String} userId  用户id
     * @apiParamExample {json} 请求样例：
     *      /doctor/getDoctorInformation/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:参数有误
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               ddHospital:"坐诊医院",
     *                               ddField:"就诊科室",
     *                               ddPosition:"职位",
     *                               ddService:"擅长疾病",
     *                               ddExperience:"主要成就",
     *                               ddCertificate:"证书图片id",
     *                               ddCertificateName:"证书名称",
     *                               ddPhone:"联系电话"
     *                           }
     *                       }
     */
    @GetMapping("/getDoctorInformation/{userId}")
    public Result getDoctorInformation(@PathVariable String userId){
        if(userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        return doctorService.getDoctorInformation(userId);
    }

    /**
     * @api {GET} /doctor/updDoctorInfo  修改医生资料(重新提交审核)
     * @apiGroup Doctor
     * @apiVersion 0.0.1
     * @apiDescription 获取医生我的资料
     * @apiParam {String} userId  用户id
     * @apiParamExample {json} 请求样例：
     *                      {
     *                          userId:"用户id",
     *                          daHospital:"坐诊医院",
     *                          dtId:"就诊科室id",
     *                          daPosition:"职位",
     *                          daDisease:"擅长疾病",
     *                          daCertificateName:"证书名称",
     *                          daCertificate:"证书图片id",
     *                          phone:"联系电话",
     *                          daAchievement:"主要成就"
     *                      }
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:参数有误
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{}
     *                       }
     */
    @PostMapping("/updDoctorInfo")
    public Result updDoctorInfo(@RequestBody DoctorApplied doctorApplied){
        if(doctorApplied == null || doctorApplied.getUserId() == null){
            return ResultUtil.paramError();
        }
        return doctorService.updDoctorInfo(doctorApplied);
    }

    /**
     * @api {GET} /doctor/getDoctorDetailsById/{userId}  医生详情
     * @apiGroup Doctor
     * @apiVersion 0.0.1
     * @apiDescription 医生详情
     * @apiParam {String} userId  用户id
     * @apiParamExample {json} 请求样例：
     *      /doctor/getDoctorDetailsById/{userId}
     * @apiSuccess (success) {GET} code  0:请求成功;  10005:参数有误
     * @apiSuccessExample {json} 返回样例:
     *                       {
     *                           "code":"0",
     *                           "msg":"请求成功",
     *                           "data":{
     *                               "doctor":{
     *                                   name:"医生姓名",
     *                                   userImgUrl:"医生头像",
     *                                   integral:"医生积分",
     *                                   ddHospital:"坐诊医院",
     *                                   ddField:"就诊科室",
     *                                   ddPosition:"职位",
     *                                   ddService:"擅长疾病",
     *                                   ddExperience:"主要成就",
     *                                   ddCertificate:"证书图片id",
     *                                   ddCertificateName:"证书名称",
     *                                   ddPhone:"联系电话"
     *                               },
     *                               "video":{
     *                                   dvId:"视频id",
     *                                   dvImgId:"视频展示图片id",
     *                                   dvTitle:"视频标题",
     *                                   dvIntegral:"视频积分",
     *                               }
     *                           }
     *                       }
     */
    @GetMapping("/getDoctorDetailsById/{userId}")
    public Result getDoctorDetailsById(@PathVariable String userId){
        if(userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        return doctorService.getDoctorDetailsById(userId);
    }

}
