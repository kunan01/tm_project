package com.tangmo.emall.dao;

import com.tangmo.emall.entity.Prescription;
import com.tangmo.emall.entity.PrescriptionKey;
import com.tangmo.emall.entity.PrescriptionValue;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionDao {

    //获取处方定义信息key集合
    List<PrescriptionKey> getPrescriptionKeyList();

    //通过key的id获取处方定义信息value集合
    List<PrescriptionValue> getPrescriptionValueList(Integer keyId);

    //添加用户处方信息
    int addPrescription(Prescription prescription);

    //删除用户处方信息
    int deletePrescription(Integer prescriptionId);

    //获取用户处方信息
    List<Prescription> getPrescriptionUserList(Integer userId);

    //通过处方id获取处方信息
    Prescription getPrescriptionById(Integer pId);
}
