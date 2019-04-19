package com.tangmo.zhygzhglxt.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.TbComplainMapper;
import com.tangmo.zhygzhglxt.dao.TbParmMapper;
import com.tangmo.zhygzhglxt.entity.TbComplain;
import com.tangmo.zhygzhglxt.entity.TbFeedBack;
import com.tangmo.zhygzhglxt.entity.TbParm;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbComplainService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chengge on 2018/10/29.
 */

@Service
public class TbComplainServiceImpl implements TbComplainService {

    @Autowired
    TbComplainMapper tbComplainMapper;

    @Autowired
    TbParmMapper tbParmMapper;


    @Override
    public Result addComplain(TbComplain tbComplain) {

        if (tbComplain == null) {
            return new Result(ResultCode.FAIL, "投诉实体不能为空！");
        }
        if (tbComplain.getCarColor() == null || tbComplain.getCarNum() == null || tbComplain.getComplainType() == null ||
                tbComplain.getDriverName() == null) {
            return new Result(ResultCode.FAIL, "参数不全");
        }
        tbComplain.setComplainId(EncryptUtil.get32Uuid());
        tbComplain.setComplainCode(EncryptUtil.get32Uuid());
        tbComplainMapper.insertSelective(tbComplain);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result delComplainByCode(String code) {

        tbComplainMapper.deleteByCode(code);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result selComplain(String name, String state, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }
        List<TbComplain> tbComplains = tbComplainMapper.selComplainByUserCode(name, state, null);
        if (tbComplains.size() > 0) {
            for (TbComplain tbComplain : tbComplains) {
                tbComplain.setColor(tbParmMapper.selectByPrimaryKey(tbComplain.getCarColor()).getParmName());
            }
        }
//        return new Result(ResultCode.SUCCESS, new PageInfo(tbComplains));
        return new Result(ResultCode.SUCCESS, tbComplains);
    }

    @Override
    public Result verifyComplain(TbComplain tbComplain) {

        tbComplainMapper.updateByCode(tbComplain);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result selComplainByUserCode(String name, String state, String userCode, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }
        List<TbComplain> tbComplains = tbComplainMapper.selComplainByUserCode(name, state, userCode);


        if (tbComplains.size() > 0) {
            for (TbComplain tbComplain : tbComplains) {
                tbComplain.setColor(tbParmMapper.selectByPrimaryKey(tbComplain.getCarColor()).getParmName());
            }
        }
        return new Result(ResultCode.SUCCESS, new PageInfo(tbComplains));
    }

    @Override
    public Result selComplainByCode(String code) {


        TbComplain tbComplain = tbComplainMapper.selComplainByCode(code);

        if (tbComplain != null) {
            tbComplain.setColor(tbParmMapper.selectByPrimaryKey(tbComplain.getCarColor()).getParmName());
        }
        return new Result(ResultCode.SUCCESS, tbComplain);
    }
}
