package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.TbSysModuleMapper;
import com.tangmo.zhygzhglxt.dao.TbSysPermissionsMapper;
import com.tangmo.zhygzhglxt.entity.TbSysModule;
import com.tangmo.zhygzhglxt.entity.TbSysPermissions;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbSysPermissionsService;
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
public class TbSysPermissionsServiceImpl implements TbSysPermissionsService {

    @Autowired
    TbSysPermissionsMapper tbSysPermissionsMapper;//权限

    @Autowired
    TbSysModuleMapper tbSysModuleMapper;//模块


    @Override
    public Result addSysPermissions(TbSysPermissions tbSysPermissions) {

        tbSysPermissions.setCode(EncryptUtil.get32Uuid());
        tbSysPermissions.setId(EncryptUtil.get32Uuid());
        tbSysPermissionsMapper.insertSelective(tbSysPermissions);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result updateSysPermissions(TbSysPermissions tbSysPermissions) {

        tbSysPermissionsMapper.updateByPrimaryKeySelective(tbSysPermissions);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result delSysPermissionsByCode(String code) {

        tbSysPermissionsMapper.deleteByCode(code);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result selSysPermissions(String name, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }

        //查询所有权限
        List<TbSysPermissions> tbSysPermissionsList = tbSysPermissionsMapper.selSysPermissions(name);

        for (TbSysPermissions tbSysPermissions : tbSysPermissionsList) {

            String modules = tbSysPermissions.getModule();
            String[] moduleCodes = modules.split(",");
            List<TbSysModule> moduleList = tbSysModuleMapper.selByModuleCodes(moduleCodes);
            tbSysPermissions.setModules(moduleList);
        }

        return new Result(ResultCode.SUCCESS, new PageInfo(tbSysPermissionsList));
    }

    @Override
    public Result selSysPermissionsByCode(String code) {

        if ("".equals(code) || code == null) {
            return new Result(ResultCode.FAIL, "唯一标识不能为空！");
        }

        TbSysPermissions tbSysPermissions = tbSysPermissionsMapper.selectByCode(code);

        return new Result(ResultCode.SUCCESS, tbSysPermissions);
    }
}
