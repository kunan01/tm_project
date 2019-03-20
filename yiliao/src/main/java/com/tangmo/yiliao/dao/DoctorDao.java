package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
@Mapper
public interface DoctorDao {

    //获取首页精品医师
    List<DoctorDetails> getFinePhysician();

    //根据科室id获取当前科室下排名前二的医生
    List<DoctorDetails> getDepartmentDoctorTop2ById(String dtId);

    //根据科室id获取当前科室下排名前三的医生
    List<DoctorDetails> getDepartmentDoctorTop3ById(String dtId);

    //获取某个科室下所有医师
    List<DoctorDetails> getDepartmentDoctorAllById(@Param("dtId") String dtId,@Param("start") Integer start,@Param("end") Integer end);

    //根据医生姓名获取医生列表
    List<DoctorDetails> getDepartmentDoctorAllByName(@Param("name") String name,@Param("nameToOne") String nameToOne);

    //申请医师
    int applyForDoctor(DoctorApplied doctorApplied);

    //查看用户申请状态
    List<String> getUserApplicationStatus(@Param("userId") String userId,@Param("type") Byte type);

    //获取医生资料
    List<DoctorDetails> getDoctorInformation(String userId);

    //获取医生详细信息
    DoctorDetails getDoctorDetailsById(String userId);

    //获取医生发布视频数量
    Integer getDoctorVideoCount(String userId);

}
