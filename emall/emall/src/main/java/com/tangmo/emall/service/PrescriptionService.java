package com.tangmo.emall.service;

import com.tangmo.emall.entity.Prescription;
import com.tangmo.emall.utils.Result;

public interface PrescriptionService {

    //获取处方定义信息
    Result getPrescriptionKVList();

    //添加用户处方信息
    Result addPrescriptionByUser(Prescription prescription);

    //删除用户处方信息
    Result deletePrescription(Integer prescriptionId);

    //获取用户处方信息
    Result getPrescriptionUserList(Integer userId);
}
