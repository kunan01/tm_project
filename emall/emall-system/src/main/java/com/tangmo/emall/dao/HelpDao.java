package com.tangmo.emall.dao;

import com.tangmo.emall.entity.Help;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpDao {

    //通过级别查询帮助信息
    List<Help> getHelpListByLevel(Integer level);

    //通过父级id查询下级帮助信息
    List<Help> getHelpListByParentId(Integer parentId);

    //添加帮助信息
    int addHelp(Help help);

    //通过帮助信息id查询帮助信息
    Help getHelpById(Integer helpId);

    //修改帮助信息
    int updateHelp(Help help);

    //删除帮助信息
    int deleteHelp(Integer helpId);
}
