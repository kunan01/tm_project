package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbCar;
import com.tangmo.zhygzhglxt.utility.Result;

/**
 * Created by chengge on 2019/3/4.
 */
public interface TbCarService {

    Result addCar(TbCar tbCar);

    Result delCarById(String id);

    Result selAllTbCar(String name, Integer pageSize, Integer pageNo);

    Result selCarByDeviceNumber(String deviceNumber);

    Result selCarByCarNumber(String carNumber);


}
