package com.tangmo.zhygzhglxt.service.Impl;


import com.tangmo.zhygzhglxt.dao.TbServiceTypeMapper;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbServiceTypeService;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbServiceTypeServiceImpl implements TbServiceTypeService {

    @Autowired
    private TbServiceTypeMapper tbServiceTypeMapper;

    /**
     * 根据主键查询服务类型
     *
     * @param serviceTypeId 主键
     * @return
     */
    @Override
    public Result jtQueryById(String serviceTypeId) {


        return new Result(ResultCode.SUCCESS, tbServiceTypeMapper.jtQueryById(serviceTypeId));
    }
}
