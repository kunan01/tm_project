package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.TbSysModuleMapper;
import com.tangmo.zhygzhglxt.dao.TbSysPermissionsMapper;
import com.tangmo.zhygzhglxt.dao.TbSysRoleMapper;
import com.tangmo.zhygzhglxt.entity.TbSysModule;
import com.tangmo.zhygzhglxt.entity.TbSysPermissions;
import com.tangmo.zhygzhglxt.entity.TbSysRole;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbSysRoleService;
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
public class TbSysRoleServiceImpl implements TbSysRoleService {

    @Autowired
    TbSysRoleMapper tbSysRoleMapper;

    @Autowired
    TbSysModuleMapper tbSysModuleMapper;

    @Autowired
    TbSysPermissionsMapper tbSysPermissionsMapper;

    /*
     * 增加角色信息
     * */
    @Override
    public Result addSysRole(TbSysRole tbSysRole) {

        tbSysRole.setCode(EncryptUtil.get32Uuid());
        tbSysRole.setId(EncryptUtil.get32Uuid());
        tbSysRoleMapper.insertSelective(tbSysRole);
        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 修改角色信息
     * */
    @Override
    public Result updateSysRole(TbSysRole tbSysRole) {

        tbSysRoleMapper.updateByPrimaryKeySelective(tbSysRole);
        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 根据模块的唯一标识code概念性删除模块信息
     * */
    @Override
    public Result delSysRoleByCode(String code) {

        if ("".equals(code) || code == null) {
            return new Result(ResultCode.FAIL, "唯一标识code不能为空！");
        }

        int a = tbSysRoleMapper.deleteByCode(code);
        return new Result(ResultCode.SUCCESS);
    }

    /*
     * 查询所有角色信息（分页）
     * */
    @Override
    public Result selSysRole(String name, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }

        //查询所有角色
        List<TbSysRole> tbSysRoles = tbSysRoleMapper.selSysRole(name);

        for (TbSysRole tbSysRole : tbSysRoles) {
            String codeList = tbSysRole.getPermissions();
            String[] code = codeList.split(",");
            List<TbSysPermissions> selectByCodes = tbSysPermissionsMapper.selectByCodes(code);
            tbSysRole.setPermissionsList(selectByCodes);

        }

        return new Result(ResultCode.SUCCESS, new PageInfo(tbSysRoles));
    }


    /*
     * 根据角色的唯一标识查询角色所拥有的模块
     * */
    @Override
    public Result selSysRoleByCode(String code) {

        if ("".equals(code) || code == null) {
            return new Result(ResultCode.FAIL, "唯一标识code不能为空！");
        }

        TbSysRole tbSysRole = tbSysRoleMapper.selectByCode(code);

        if (tbSysRole == null) {
            return new Result(ResultCode.FAIL, "该角色不存在！");
        } else {
            //根据角色得到权限
            String[] permissionsCodes = tbSysRole.getPermissions().split(",");
            List<TbSysPermissions> tbSysPermissionLists = tbSysPermissionsMapper.selectByCodes(permissionsCodes);
            String modules = "";

            for (TbSysPermissions tbSysPermissions : tbSysPermissionLists) {
                modules += tbSysPermissions.getModule();
            }

            //根据权限得到模块
            String[] moduleCodes = modules.split(",");
            List<TbSysModule> moduleList = tbSysModuleMapper.selByModuleCodes(moduleCodes);
            //tbSysRole.setModuleList(moduleList);
            return new Result(ResultCode.SUCCESS, moduleList);
        }
    }
}
