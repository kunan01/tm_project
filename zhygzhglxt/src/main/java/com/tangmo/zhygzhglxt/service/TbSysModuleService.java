package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbSysModule;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by chengge on 2018/10/23.
 */
public interface TbSysModuleService {

    /*
     *增加模块信息
     * */
    Result addSysModule(TbSysModule tbSysModule);

    /*
     *修改模块信息
     * */
    Result updateSysModule(TbSysModule tbSysModule);

    /*
     *根据模块唯一标识code删除模块
     * */
    Result delSysModuleByCode(String code);

    /*
     *查询所有模块信息(可模糊,可分页)
     * */
    Result selSysModule(String name, Integer pageSize, Integer pageNo);

    /*
     *根据模块唯一标识code查询模块信息
     * */
    Result selSysModuleByCode(String code);
}
