package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbDriverVerify;
import com.tangmo.zhygzhglxt.entity.dto.TbDriverVerifyDto;
import com.tangmo.zhygzhglxt.utility.Result;

/**
 * Created by chengge on 2018/10/23.
 */
public interface TbDriverVerifyService {


    /*
     * 申请成为车主
     * */
    Result addDriverVerify(TbDriverVerify tbDriverVerify);

    /*
     * 查询车主审核信息（可模糊查，可分页，可根据状态查）
     * */
    Result selDriverVerify(String name, String state, Integer pageSize, Integer pageNo);

    /*
     * 根据用户的唯一标识查询车主审核信息
     * */
    Result selDriverVerifyByUserCode(String userCode);

    /*
     * 审核车主信息
     * */
    Result verifyDriver(String state, String code);

    /*
     * 概念性删除车主的审核信息
     * */
    Result delDriverVerifyByCode(String code);


    /**
     * 根据唯一标识code查找车主详细信息
     */
    Result jtQueryById(String code);

    /**
     * 实时更新车主的经度纬度
     */
    Result updateCarLaLo(String userCode, String carLa, String carLo, String driverOrderCode);

    /**
     * 根据司机用户的唯一标识实时查询当前司机的经度纬度
     */
    Result selCarLaLoByUserCode(String driverUserCode);


}
