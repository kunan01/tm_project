package com.tangmo.zhygzhglxt.service.Impl;

import com.tangmo.zhygzhglxt.dao.TbParmMapper;
import com.tangmo.zhygzhglxt.entity.TbParm;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbParmService;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chengge on 2018/7/16.
 */
@Service
public class TbParmServiceImpl implements TbParmService {

    @Autowired
    protected TbParmMapper tbParmMapper;


    @Override
    public Result addParm(TbParm tbParm) {
        tbParmMapper.insert(tbParm);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result delParmById(String parmId) {
        tbParmMapper.deleteByPrimaryKey(parmId);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result selParmByType(String numberType) {
        return new Result(ResultCode.SUCCESS, tbParmMapper.selParmByType(numberType));
    }

    @Override
    public Result selBusType() {
        String busType = "busType";
        return new Result(ResultCode.SUCCESS, tbParmMapper.selBusType(busType));
    }

    @Override
    public Result selParmById(String parmId) {
        return new Result(ResultCode.SUCCESS, tbParmMapper.selectByPrimaryKey(parmId));
    }
}
