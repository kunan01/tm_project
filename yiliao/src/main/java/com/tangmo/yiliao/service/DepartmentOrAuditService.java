package com.tangmo.yiliao.service;

import com.tangmo.yiliao.entity.Department;
import com.tangmo.yiliao.entity.DoctorApplied;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2018-9-6
 * @description
 */
public interface DepartmentOrAuditService {

    //后台分页查询所有科室信息
    Result getDepartmentALL(SelectUser selectUser);

    //后台获取科室总量
    Result getDepartmentALLCount(SelectUser selectUser);

    //后台删除科室信息
    Result delDepartmentById(String dtId,String userId);

    //后台修改科室信息
    Result updDepartmentById(Department department);

    //后台添加科室信息
    Result addDepartment(Department department);

    //后台获取医生审核总数量
    Result getDoctorCount(Integer type);

    //后台获取医生审核信息(分页)
    Result getDoctorAudit(Integer type,Integer start,Integer end);

    //后台审核医生
    Result AuditDoctorById(String daId,Integer type,String val,String userId);

    //
    Result updDoctorById(DoctorApplied doctorApplied);

}
