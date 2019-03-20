package com.tangmo.yiliao.service;

import com.tangmo.yiliao.entity.DoctorApplied;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
public interface DoctorService {

    //获取首页精品医师
    Result getFinePhysician();

    //获取所有科室排名前三医师
    Result getDepartmentAllDoctorTop3();

    //获取某个科室下所有医师
    Result getDepartmentDoctorAllById(String dtId,Integer start,Integer end);

    //申请医师
    Result applyForDoctor(DoctorApplied doctorApplied);

    //查看用户申请医师状态
    Result getUserApplicationStatus(String userId,Byte type);

    //获取医生资料
    Result getDoctorInformation(String userId);

    //修改医生资料(重新提交审核)
    Result updDoctorInfo(DoctorApplied doctorApplied);

    //获取医生详情
    Result getDoctorDetailsById(String userId);


}
