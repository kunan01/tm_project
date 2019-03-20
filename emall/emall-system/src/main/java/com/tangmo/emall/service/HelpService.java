package com.tangmo.emall.service;

import com.tangmo.emall.entity.Help;
import com.tangmo.emall.utils.Result;

public interface HelpService {

    //获取帮助信息
    Result getSysHelpList(Integer level,Integer isPage,Integer parentId,Integer pageNo, Integer pageSize);

    //添加帮助信息
    Result addHelp(Help help);

    //修改帮助信息
    Result updateSysHelpList(Help help);

    //删除帮助信息
    Result deleteSysHelpList(Integer helpId);

    //批量删除帮助信息
    Result batchDeleteSysHelpList(Help help);
}
