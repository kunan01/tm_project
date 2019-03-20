package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.Department;
import com.tangmo.yiliao.entity.DoctorDetails;
import com.tangmo.yiliao.entity.DoctorVideo;
import com.tangmo.yiliao.entity.SystemTheArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
@Mapper
public interface DepartmentDao {

    //获取首页部分科室
    List<Department> getPartOfDepartment();

    //获取全部科室
    List<Department> getDepartmentAll();

}
