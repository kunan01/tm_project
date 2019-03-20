package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.TbBusMapper;
import com.tangmo.zhygzhglxt.dao.TbCarMapper;
import com.tangmo.zhygzhglxt.entity.TbCar;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbCarService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chengge on 2019/3/4.
 */
@Service
public class TbCarServiceImpl implements TbCarService {

    @Autowired
    TbCarMapper tbCarMapper;  //同乡车辆

    @Override
    public Result addCar(TbCar tbCar) {

        if (tbCar == null) {
            return new Result(ResultCode.FAIL, "信息不能为空！");
        }

        if (tbCar.getCarNumber() == null || "".equals(tbCar.getCarNumber())) {
            return new Result(ResultCode.FAIL, "车牌号不能为空！");
        }

        if (tbCar.getDeviceNumber() == null || "".equals(tbCar.getDeviceNumber())) {
            return new Result(ResultCode.FAIL, "设备号不能为空！");
        }
        tbCar.setId(EncryptUtil.get32Uuid());
        tbCarMapper.insertSelective(tbCar);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result delCarById(String id) {

        if (id == null || "".equals(id)) {
            return new Result(ResultCode.FAIL, "主键不能为空！");
        }

        tbCarMapper.deleteByPrimaryKey(id);

        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result selAllTbCar(String name, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }

        List<TbCar> carList = tbCarMapper.selAllTbCar(name);

        return new Result(ResultCode.SUCCESS, new PageInfo(carList));
    }

    @Override
    public Result selCarByDeviceNumber(String deviceNumber) {

        if (deviceNumber == null || "".equals(deviceNumber)) {
            return new Result(ResultCode.FAIL, "设备号不能为空！");
        }

        TbCar carList = tbCarMapper.selCarByDeviceNumber(deviceNumber);

        return new Result(ResultCode.SUCCESS, carList);
    }

    @Override
    public Result selCarByCarNumber(String carNumber) {

        if (carNumber == null || "".equals(carNumber)) {
            return new Result(ResultCode.FAIL, "车牌号不能为空！");
        }

        TbCar carList = tbCarMapper.selCarByCarNumber(carNumber);

        return new Result(ResultCode.SUCCESS, carList);
    }
}
