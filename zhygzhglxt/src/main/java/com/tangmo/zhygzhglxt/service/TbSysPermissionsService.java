package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbSysPermissions;
import com.tangmo.zhygzhglxt.utility.Result;

/**
 * Created by chengge on 2018/10/23.
 */
public interface TbSysPermissionsService {

    /*
     *增加权限信息
     * */
    Result addSysPermissions(TbSysPermissions tbSysPermissions);

    /*
     *修改权限信息
     * */
    Result updateSysPermissions(TbSysPermissions tbSysPermissions);

    /*
     *根据权限唯一标识code删除权限
     * */
    Result delSysPermissionsByCode(String code);

    /*
     *查询所有权限信息(可模糊,可分页)
     * */
    Result selSysPermissions(String name, Integer pageSize, Integer pageNo);


    /*
     *根据权限唯一标识code查询权限信息
     * */
    Result selSysPermissionsByCode(String code);

}
