package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.TbSysModuleMapper;
import com.tangmo.zhygzhglxt.entity.TbSysModule;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbSysModuleService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chengge on 2018/10/23.
 */
@Service
public class TbSysModuleServiceImpl implements TbSysModuleService {

    @Autowired
    TbSysModuleMapper tbSysModuleMapper;

    /*
     * 增加模块信息
     * */
    @Override
    public Result addSysModule(TbSysModule tbSysModule) {

        tbSysModule.setId(EncryptUtil.get32Uuid());
        tbSysModule.setCode(EncryptUtil.get32Uuid());

        tbSysModuleMapper.insertSelective(tbSysModule);
        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 修改模块信息
     * */
    @Override
    public Result updateSysModule(TbSysModule tbSysModule) {

        tbSysModuleMapper.updateByPrimaryKeySelective(tbSysModule);
        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 概念性删除模块信息
     * */
    @Override
    public Result delSysModuleByCode(String code) {

        tbSysModuleMapper.deleteByCode(code);
        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 模糊查询模块信息（分页）
     * */
    @Override
    public Result selSysModule(String name, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }
        List<TbSysModule> tbSysModuleList = tbSysModuleMapper.selSysModule(name);

        return new Result(ResultCode.SUCCESS, new PageInfo(tbSysModuleList));
    }

    /*
     * 根据模块的唯一标识查询模块信息
     * */
    @Override
    public Result selSysModuleByCode(String code) {

        if ("".equals(code) || code == null) {
            return new Result(ResultCode.FAIL, "code的唯一标识不能为空！");
        }

        TbSysModule tbSysModule = tbSysModuleMapper.selectByCode(code);

        if (tbSysModule == null) {
            return new Result(ResultCode.FAIL, "该模块不存在！");
        }
        return new Result(ResultCode.SUCCESS, tbSysModule);
    }
}
