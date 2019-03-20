package com.tangmo.zhjy.app.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.dao.CommonDao;
import com.tangmo.zhjy.app.modules.service.CommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 18/4/9
 * @description 参数类,一般性服务层
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {


    @Resource
    private CommonDao commonDao;


    @Override
    public Result searchDistrict(String city) {
        return new Result(ResultCode.SUCCESS,commonDao.selectDistrict(city));
    }

    @Override
    public Result getMessage(Integer userId,Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return new Result(ResultCode.SUCCESS,new PageInfo(commonDao.getMessage()));
    }
}
