package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.DepartmentDao;
import com.tangmo.yiliao.dao.DoctorDao;
import com.tangmo.yiliao.dao.VideoDao;
import com.tangmo.yiliao.entity.Department;
import com.tangmo.yiliao.entity.DoctorApplied;
import com.tangmo.yiliao.service.DoctorService;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.util.EncryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorDao doctorDao;

    @Resource
    private DepartmentDao departmentDao;

    @Resource
    private VideoDao videoDao;

    @Override
    public Result getFinePhysician() {
        return ResultUtil.success(doctorDao.getFinePhysician());
    }

    @Override
    public Result getDepartmentAllDoctorTop3() {
        Map<Object, Object> tun = new HashMap<>();
        List<String> strs = new ArrayList<>();
        Map<Object, Object> res = new HashMap<>();
        List<Department> departmentList = departmentDao.getDepartmentAll();
        for (int i = 0; i < departmentList.size(); i++){
            strs.add(departmentList.get(i).getDtId()+","+departmentList.get(i).getDtName());
            res.put(departmentList.get(i).getDtId(),doctorDao.getDepartmentDoctorTop3ById(departmentList.get(i).getDtId()));
        }
        tun.put("key",strs);
        tun.put("values",res);
        return ResultUtil.success(tun);
    }

    @Override
    public Result getDepartmentDoctorAllById(String dtId,Integer start,Integer end) {
        return ResultUtil.success(doctorDao.getDepartmentDoctorAllById(dtId,(start-1) * end,end));
    }

    @Override
    @Transactional
    public Result applyForDoctor(DoctorApplied doctorApplied) {
        doctorApplied.setDaId(EncryptUtil.get32Uuid());
        doctorApplied.setDaType(Byte.parseByte("0"));

        doctorDao.applyForDoctor(doctorApplied);

        return ResultUtil.success();
    }

    @Override
    public Result getUserApplicationStatus(String userId,Byte type) {
        List<String> state = doctorDao.getUserApplicationStatus(userId, type);

        if(state == null || state.size() == 0){
            return ResultUtil.success(0);
        }

        for (int i = 0; i < state.size();i++){
            if(state.get(i).equals("1")){
                return ResultUtil.success(2);
            }
        }
        for (int i = 0; i < state.size();i++){
            if(state.get(i).equals("0")){
                return ResultUtil.success(1);
            }
        }

        return ResultUtil.success(3);
    }

    @Override
    public Result getDoctorInformation(String userId) {
        return ResultUtil.success(doctorDao.getDoctorInformation(userId));
    }

    @Override
    public Result updDoctorInfo(DoctorApplied doctorApplied) {
        doctorApplied.setDaId(EncryptUtil.get32Uuid());
        doctorApplied.setDaType(Byte.parseByte("1"));

        doctorDao.applyForDoctor(doctorApplied);

        return ResultUtil.success();
    }

    @Override
    public Result getDoctorDetailsById(String userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("doctor",doctorDao.getDoctorDetailsById(userId));
        map.put("video",videoDao.getUserDoctorVideoById(userId,0,4));
        return ResultUtil.success(map);
    }
}
