package com.tangmo.zhjy.system.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.ShopVerify;
import com.tangmo.zhjy.system.modules.dao.SysShopMapper;
import com.tangmo.zhjy.system.modules.dao.SysUserBeanMapper;
import com.tangmo.zhjy.system.modules.service.SysShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author boge
 * @date 2018/4/29
 * @description
 */
@Service("sysShopService")
public class SysShopServiceImpl implements SysShopService {
    @Resource
    private SysShopMapper sysShopMapper;
    @Resource
    private SysUserBeanMapper sysUserBeanMapper;

    @Override
    public Result searchSvInfo(Byte state,Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<ShopVerify> list = sysShopMapper.selectPageByState(state);
        return new Result(ResultCode.SUCCESS,new PageInfo(list));
    }

    @Override
    public Result verifyByUserId(Integer userId) {
        if(userId==null){
            return new Result(ResultCode.FAIL,"用户id不能为空");
        }
        ShopVerify shopVerify = sysShopMapper.verifyByUserId(userId);
        if(shopVerify==null){
            return new Result(ResultCode.FAIL,"审核信息不存在");
        }
        return new Result(ResultCode.SUCCESS,shopVerify);
    }

    @Override
    public Result updateSvState(Integer svId, Byte state) {
        sysShopMapper.updateSvState(svId, state);
        /**
         * 更新成为商家/取消商家资格
         */
        Integer userId = sysShopMapper.selectUserId(svId);
        if(state==1){
            sysUserBeanMapper.updateUserStatus(userId,(byte)1);
        }else{
            sysUserBeanMapper.updateUserStatus(userId,(byte)0);
        }
        return new Result(ResultCode.SUCCESS);
    }
}
