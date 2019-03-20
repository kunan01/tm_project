package com.tangmo.yiliao.service;

import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
public interface DepartmentService {

    //获取首页部分科室
    Result getPartOfDepartment();

    //获取全部科室
    Result getDepartmentAll();

    //获取科室详情
    Result getDepartmentDetails(String dtId);

}
