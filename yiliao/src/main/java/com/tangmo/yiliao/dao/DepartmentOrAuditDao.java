package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.Department;
import com.tangmo.yiliao.entity.DoctorApplied;
import com.tangmo.yiliao.entity.SelectUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hanjialin
 * @date 2018-9-6
 * @description
 */
@Mapper
public interface DepartmentOrAuditDao {

    //后台分页查询所有科室信息
    List<Department> getDepartmentALL(SelectUser selectUser);

    //后台查询所有科室数量
    Integer getDepartmentALLCount(SelectUser selectUser);

    //删除科室信息
    int delDepartmentById(@Param("dtId") String dtId,@Param("newDtId") String newDtId,@Param("userId") String userId);

    //修改科室信息
    int updDepartmentById(Department department);

    //查询科室使用量
    Integer getDtIdCount(String dtId);

    //删除时科室排序
    int delDepartOrderBy(@Param("dtId") String dtId);

    //修改时科室排序
    int updDepartOrderByJian(@Param("soId") Integer soId,@Param("newSoId") Integer newSoId);

    int updDepartOrderByJia(@Param("soId") Integer soId,@Param("newSoId") Integer newSoId);

    //添加时科室排序
    int addDepartOrderBy(@Param("soId") Integer soId);

    //获取科室数量
    Integer getDeparCount();

    //获取科室排序
    Integer getDeparCountById(@Param("dtId") String dtId);

    //修改科室信息
    int addDepartment(Department department);

    //后台获取医生审核总数量
    Integer getDoctorCount(Integer type);

    //后台获取医生审核信息(分页)
    List<DoctorApplied> getDoctorAudit(@Param("type") Integer type, @Param("start") Integer start, @Param("end") Integer end);

    //获取医生审核信息
    DoctorApplied getDoctorAppById(String daId);

    //修改审核状态
    int updDoctorAppById(@Param("daId") String daId,@Param("type") Integer type,@Param("val") String val,@Param("userId") String userId);

    //添加医生
    int addDoctor(DoctorApplied doctorApplied);

    //修改医生资料
    int updDoctor(DoctorApplied doctorApplied);
}
