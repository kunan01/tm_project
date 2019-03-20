package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.HelpDao;
import com.tangmo.emall.entity.Help;
import com.tangmo.emall.service.HelpService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("helpService")
public class HelpServiceImpl implements HelpService {

    @Resource
    private HelpDao helpDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public Result getSysHelpList(Integer level, Integer isPage, Integer parentId, Integer pageNo, Integer pageSize) {
        try{
            if(level == null || isPage == null){
                return ResultUtil.paramError();
            }
            if(isPage == 1){
                if(pageNo == null || pageSize == null){
                    return ResultUtil.paramError();
                }
                PageHelper.startPage(pageNo,pageSize);
            }

            if(level == 1){
                PageInfo<Help> page = new PageInfo<>(helpDao.getHelpListByLevel(1));
                return ResultUtil.success(page);
            }else{
                if(parentId == null){
                    return ResultUtil.paramError();
                }
                PageInfo<Help> page = new PageInfo<>(helpDao.getHelpListByParentId(parentId));
                return ResultUtil.success(page);
            }
        }catch (Exception e){
            System.out.println("获取帮助信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result addHelp(Help help) {
        try {
            if(help == null || help.getHelpTitle() == null || help.getHelpInstructions() == null || help.getLevel() == null){
                return ResultUtil.paramError();
            }
            if(help.getHelpTitle().equals("") || help.getHelpInstructions().equals("")){
                return ResultUtil.paramError();
            }
            if(help.getLevel() == 2){
                if(help.getParentId() == null){
                    return ResultUtil.paramError();
                }
            }else{
                help.setParentId(0);
            }

            helpDao.addHelp(help);

            String key = "HelpList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("添加成功");
        }catch (Exception e){
            System.out.println("添加帮助信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updateSysHelpList(Help help) {
        try {
            if(help == null || help.getHelpId() == null){
                return ResultUtil.paramError();
            }

            //校验当前帮助信息是否存在
            Help help1 = helpDao.getHelpById(help.getHelpId());
            if(help1 == null){
                return ResultUtil.dataNoError();
            }

            if(help.getHelpTitle() == null && help.getHelpInstructions() == null){
                return ResultUtil.success("修改完成");
            }

            helpDao.updateHelp(help);

            String key = "HelpList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("修改成功");
        }catch (Exception e){
            System.out.println("修改帮助信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result deleteSysHelpList(Integer helpId) {
        try {
            if(helpId == null){
                return ResultUtil.paramError();
            }

            //校验当前帮助信息是否存在
            Help help1 = helpDao.getHelpById(helpId);
            if(help1 == null){
                return ResultUtil.dataNoError();
            }

            helpDao.deleteHelp(helpId);

            String key = "HelpList";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除帮助信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDeleteSysHelpList(Help help) {
        try {
            if(help == null || help.getHelpIdList() == null){
                return ResultUtil.paramError();
            }
            if(help.getHelpIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (Integer helpId: help.getHelpIdList()) {
                //校验当前帮助信息是否存在
                Help help1 = helpDao.getHelpById(helpId);
                if(help1 != null){

                    helpDao.deleteHelp(helpId);

                    String key = "HelpList";
                    if(jedisKeys.exists(key)){
                        jedisKeys.del(key);
                    }
                }
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除帮助信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
