package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.DepartmentOrAuditDao;
import com.tangmo.yiliao.dao.MessageDao;
import com.tangmo.yiliao.dao.UserDao;
import com.tangmo.yiliao.entity.*;
import com.tangmo.yiliao.service.DepartmentOrAuditService;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.util.EncryptUtil;
import com.tangmo.yiliao.utility.util.PinyinTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author hanjialin
 * @date 2018-9-6
 * @description
 */
@Service("departmentOrAuditService")
public class DepartmentOrAuditServiceImpl implements DepartmentOrAuditService {

    @Resource
    private DepartmentOrAuditDao departmentOrAuditDao;

    @Resource
    private UserDao userDao;

    @Resource
    private MessageDao messageDao;

    @Override
    public Result getDepartmentALL(SelectUser selectUser) {
        selectUser.setStart((selectUser.getStart()-1)*selectUser.getEnd());
        return ResultUtil.success(departmentOrAuditDao.getDepartmentALL(selectUser));
    }

    @Override
    public Result getDepartmentALLCount(SelectUser selectUser) {
        return ResultUtil.success(departmentOrAuditDao.getDepartmentALLCount(selectUser));
    }

    @Override
    @Transactional
    public Result delDepartmentById(String dtId, String userId) {
        if(dtId == null || userId == null){
            return ResultUtil.paramError();
        }
        if(departmentOrAuditDao.getDtIdCount(dtId) == 0){
            departmentOrAuditDao.delDepartOrderBy(dtId);
            departmentOrAuditDao.delDepartmentById(dtId,EncryptUtil.get32Uuid(),userId);
            return ResultUtil.success();
        }
        return ResultUtil.error("当前科室正在使用,请处理后再试");
    }

    @Override
    @Transactional
    public Result updDepartmentById(Department department) {
        if(department.getDtName() == null || department.getDtId() == null || department.getDtImgId() == null || department.getUpdateUserId() == null){
            return ResultUtil.paramError();
        }
        //目前排名
        Integer dtSoId = departmentOrAuditDao.getDeparCountById(department.getDtId());
        if(dtSoId > department.getSortingId()){
            departmentOrAuditDao.updDepartOrderByJia(dtSoId,department.getSortingId());
        }else if(dtSoId < department.getSortingId()){
            departmentOrAuditDao.updDepartOrderByJian(dtSoId,department.getSortingId());
        }
        departmentOrAuditDao.updDepartmentById(department);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result addDepartment(Department department) {
        if(department.getDtName() == null || department.getDtImgId() == null || department.getCreateUserId() == null){
            return ResultUtil.paramError();
        }
        PinyinTool tool = new PinyinTool();
        department.setDtId(tool.toPinYin(department.getDtName(), "", PinyinTool.Type.UPPERCASE));

        if(departmentOrAuditDao.getDeparCount() >= department.getSortingId()){
            departmentOrAuditDao.addDepartOrderBy(department.getSortingId());
        }

        try {
            departmentOrAuditDao.addDepartment(department);
        }catch (Exception e){
            return ResultUtil.error("当前权限名称生成的权限标识重复,请更换后在试！");
        }
        return ResultUtil.success();
    }

    @Override
    public Result getDoctorCount(Integer type) {
        return ResultUtil.success(departmentOrAuditDao.getDoctorCount(type));
    }

    @Override
    public Result getDoctorAudit(Integer type,Integer start, Integer end) {
        return ResultUtil.success(departmentOrAuditDao.getDoctorAudit(type,(start - 1) * end,end));
    }

    @Override
    @Transactional
    public Result AuditDoctorById(String daId, Integer type, String val,String userId) {
        if(type == 2){
            //不通过
            departmentOrAuditDao.updDoctorAppById(daId,type,val,userId);
            return ResultUtil.success();
        }else{
            //通过
            departmentOrAuditDao.updDoctorAppById(daId,type,null,userId);
            DoctorApplied doctorApplied = departmentOrAuditDao.getDoctorAppById(daId);
            if(doctorApplied.getDaType().toString().equals("0")){
                //申请医生
                doctorApplied.setDaId(EncryptUtil.get32Uuid());
                doctorApplied.setCreateUserId(userId);
                departmentOrAuditDao.addDoctor(doctorApplied);
                //修改消息状态
                messageDao.updMessageState(Byte.parseByte("1"),1,doctorApplied.getUserId());
            }else{
                //修改资料
                doctorApplied.setUpdateUserId(userId);
                departmentOrAuditDao.updDoctor(doctorApplied);
            }
            //修改真实姓名以及身份
            User user = new User();
            user.setRoleId("DOCTOR");
            user.setUserId(doctorApplied.getUserId());
            user.setName(doctorApplied.getName());
            userDao.updUser(user);
            return ResultUtil.success();
        }
    }

    @Override
    @Transactional
    public Result updDoctorById(DoctorApplied doctorApplied) {
        departmentOrAuditDao.updDoctor(doctorApplied);
        return ResultUtil.success();
    }
}
