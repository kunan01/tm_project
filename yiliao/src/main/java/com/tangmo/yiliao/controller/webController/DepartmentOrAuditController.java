package com.tangmo.yiliao.controller.webController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.Department;
import com.tangmo.yiliao.entity.DoctorApplied;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanjialin
 * @date 2018-9-5
 * @description 后台科室和审核
 */
@Api("system科室和审核")
@RestController
@RequestMapping("/departmentOrAudit")
public class DepartmentOrAuditController extends BaseController {

    //后台分页获取科室信息
    @PostMapping("/getDepartmentALL")
    public Result getDepartmentALL(@RequestBody SelectUser selectUser){
        return departmentOrAuditService.getDepartmentALL(selectUser);
    }

    //后台获取科室数量
    @PostMapping("/getDepartmentALLCount")
    public Result getDepartmentALLCount(@RequestBody SelectUser selectUser){
        return departmentOrAuditService.getDepartmentALLCount(selectUser);
    }

    //后台删除科室信息
    @GetMapping("/delDepartmentById/{dtId}/{userId}")
    public Result delDepartmentById(@PathVariable String dtId, @PathVariable String userId){
        return departmentOrAuditService.delDepartmentById(dtId,userId);
    }

    //后台修改科室信息
    @PostMapping("/updDepartmentById")
    public Result updDepartmentById(@RequestBody Department department){
        return departmentOrAuditService.updDepartmentById(department);
    }

    //后台添加科室信息
    @PostMapping("/addDepartment")
    public Result addDepartment(@RequestBody Department department){
        return departmentOrAuditService.addDepartment(department);
    }

    ///后台获取医生审核总数量
    @GetMapping("/getDoctorCount/{type}")
    public Result getDoctorCount(@PathVariable Integer type){
        return departmentOrAuditService.getDoctorCount(type);
    }

    //后台获取医生审核信息(分页)
    @GetMapping("/getDoctorAudit/{type}/{start}/{end}")
    public Result getDoctorAudit(@PathVariable Integer type,@PathVariable Integer start,@PathVariable Integer end){
        return departmentOrAuditService.getDoctorAudit(type,start,end);
    }

    //后台审核医生信息
    @GetMapping("/AuditDoctorById/{daId}/{type}/{val}/{userId}")
    public Result AuditDoctorById(@PathVariable String daId,@PathVariable Integer type,@PathVariable String val,@PathVariable String userId){
        return departmentOrAuditService.AuditDoctorById(daId,type,val,userId);
    }

    //后台修改医生信息
    @PostMapping("/updDoctorById")
    public Result updDoctorById(@RequestBody DoctorApplied doctorApplied){
        return departmentOrAuditService.updDoctorById(doctorApplied);
    }

}
