package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbComplain;
import com.tangmo.zhygzhglxt.utility.Result;

/**
 * Created by chengge on 2018/10/29.
 */
public interface TbComplainService {

    Result addComplain(TbComplain tbComplain);

    Result delComplainByCode(String code);

    Result selComplain(String name, String state, Integer pageSize, Integer pageNo);

    Result selComplainByUserCode(String name, String state, String userCode, Integer pageSize, Integer pageNo);

    Result verifyComplain(TbComplain tbComplain);

    Result selComplainByCode(String code);

}
