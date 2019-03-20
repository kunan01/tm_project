package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbParm;
import com.tangmo.zhygzhglxt.utility.Result;

/**
 * Created by chengge on 2018/7/16.
 */
public interface TbParmService {

    /**
     * 增加类别参数信息
     */
    Result addParm(TbParm tbParm);

    /**
     * 根据类别参数id删除
     */
    Result delParmById(String parmId);

    /*
     * 根据类型查询数字参数信息
     *numberType：传1 车辆类型
     * */
    Result selParmByType(String numberType);

    /**
     * 根据类别参数id查询信息
     */
    Result selParmById(String parmId);
}
