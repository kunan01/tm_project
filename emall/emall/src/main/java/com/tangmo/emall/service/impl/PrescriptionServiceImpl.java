package com.tangmo.emall.service.impl;

import com.tangmo.emall.dao.PrescriptionDao;
import com.tangmo.emall.entity.Prescription;
import com.tangmo.emall.entity.PrescriptionKey;
import com.tangmo.emall.entity.PrescriptionValue;
import com.tangmo.emall.service.PrescriptionService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("prescriptionService")
public class PrescriptionServiceImpl implements PrescriptionService {

    @Resource
    private PrescriptionDao prescriptionDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public Result getPrescriptionKVList() {
        try {
            String key = "PrescriptionKVList";
            if(!jedisKeys.exists(key)){
                List<PrescriptionKey> keyList = prescriptionDao.getPrescriptionKeyList();
                if(keyList != null){
                    for (int i = 0;i<keyList.size();i++){

                        List<PrescriptionValue> valueList = prescriptionDao.getPrescriptionValueList(keyList.get(i).getPkId());
                        if(valueList != null){
                            if(keyList.get(i).getState().toString().equals("1")){
                                for (int j = 0;j<valueList.size();j++){

                                    Double startDegree = Double.parseDouble(valueList.get(j).getPvDetailed().split(",")[0].split("\\_")[0]);

                                    Double endDegree = Double.parseDouble(valueList.get(j).getPvDetailed().split(",")[0].split("\\_")[1]);

                                    Double poor = Double.parseDouble(valueList.get(j).getPvDetailed().split(",")[1]);

                                    List<String> strings = new ArrayList<>();

                                    if(valueList.get(j).getPvIntroduce() != null && !valueList.get(j).getPvIntroduce().equals("")){
                                        String[] strs = valueList.get(j).getPvIntroduce().split(",");
                                        for (String s : strs) {
                                            strings.add(s);
                                        }
                                    }

                                    strings.add(startDegree.toString());

                                    Double a = startDegree;

                                    while (true){
                                        a = a + poor;
                                        System.out.println(a);
                                        if(a == endDegree || a.toString().equals(endDegree.toString())){
                                            strings.add(endDegree.toString());
                                            break;
                                        }
                                        strings.add(a.toString());
                                    }

                                    valueList.get(j).setStringList(strings);
                                }
                            }
                            keyList.get(i).setValueList(valueList);
                        }
                    }
                }
                jedisStrings.set(key.getBytes(), SerializeUtil.serialize(keyList));
                //设置过期时间两个小时
                jedisStrings.expire(key.getBytes(),7200);
                return ResultUtil.success(keyList);
            }else{
                List<PrescriptionKey> keyList = (List<PrescriptionKey>)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));
                return ResultUtil.success(keyList);
            }
        }catch (Exception e){
            log.error("处方模块：'获取处方定义信息'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result addPrescriptionByUser(Prescription prescription) {
        try {
            if(prescription == null || prescription.getUserId() == null || prescription.getPrescriptionName() == null){
                return ResultUtil.paramError();
            }

            prescriptionDao.addPrescription(prescription);

            String key = "PrescriptionUserList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success(prescription.getPrescriptionId());
        }catch (Exception e){
            log.error("处方模块：'添加用户处方信息'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result deletePrescription(Integer prescriptionId) {
        try {
            if(prescriptionId == null){
                return ResultUtil.paramError();
            }

            prescriptionDao.deletePrescription(prescriptionId);

            String key = "PrescriptionUserList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("删除完成");
        }catch (Exception e){
            log.error("处方模块：'删除用户处方信息'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getPrescriptionUserList(Integer userId) {
        try {
            if(userId == null){
                return ResultUtil.paramError();
            }

            String key = "PrescriptionUserList";
            if(!jedisKeys.exists(key)){
                List<Prescription> prescriptionList = prescriptionDao.getPrescriptionUserList(userId);
                return ResultUtil.success(prescriptionList);
            }else{
                List<Prescription> prescriptionList = (List<Prescription>)SerializeUtil.deserialize(jedisStrings.get(key.getBytes()));
                return ResultUtil.success(prescriptionList);
            }

        }catch (Exception e){
            log.error("处方模块：'获取用户处方信息'接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
