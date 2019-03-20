package com.tangmo.zhjy.app.modules.service.impl;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.Version;
import com.tangmo.zhjy.app.modules.dao.VersionMapper;
import com.tangmo.zhjy.app.modules.service.VersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengge on 2018/10/9.
 */
@Service
public class VersionServiceImpl implements VersionService{

    @Resource
    private VersionMapper versionMapper;

    @Override
    public Result getVersion(String versionNumber) {

        if(versionNumber==null || "".equals(versionNumber)){
            return new Result(ResultCode.FAIL,"版本号不能为空");
        }

        Version version=versionMapper.getVersion();

        if(versionNumber.equals(version.getVersionNumber())){//如果相同，说明版本号已是最新
            return new Result(ResultCode.VERSION_NUMBER1,version);
        }
        else{//如果不相同，说明版本号不是最新的
            return new Result(ResultCode.VERSION_NUMBER2,version);
        }
    }

    @Override
    public Result addVersion(Version version) {

        if(version == null){
            return new Result(ResultCode.FAIL,"版本不能为空");
        }

        if(version.getVersionNumber() == null){
            return new Result(ResultCode.FAIL,"版本号不能为空");
        }
        versionMapper.insertSelective(version);
        return new Result(ResultCode.SUCCESS);
    }
}
