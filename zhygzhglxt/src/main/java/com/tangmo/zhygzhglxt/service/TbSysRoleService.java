package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbSysModule;
import com.tangmo.zhygzhglxt.entity.TbSysRole;
import com.tangmo.zhygzhglxt.utility.Result;

/**
 * Created by chengge on 2018/10/23.
 */
public interface TbSysRoleService {

    /*
     *增加角色信息
     * */
    Result addSysRole(TbSysRole tbSysRole);

    /*
     *修改角色信息
     * */
    Result updateSysRole(TbSysRole tbSysRole);

    /*
     *根据角色唯一标识code删除角色
     * */
    Result delSysRoleByCode(String code);

    /*
     *查询所有角色信息(可模糊,可分页)
     * */
    Result selSysRole(String name, Integer pageSize, Integer pageNo);

    /*
     *根据角色唯一标识code查询角色信息
     * */
    Result selSysRoleByCode(String code);

}
