package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.VersionDao;
import com.tangmo.yiliao.entity.AppVersion;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.service.VersionService;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
@Service("versionService")
public class VersionServiceImpl implements VersionService {

    @Resource
    private VersionDao versionDao;

    @Override
    public Result getVersion() {
        return ResultUtil.success(versionDao.getVersion());
    }

    @Override
    public Result getVersionAll(SelectUser selectUser) {
        selectUser.setStart((selectUser.getStart()-1)*selectUser.getEnd());
        return ResultUtil.success(versionDao.getVersionAll(selectUser));
    }

    @Override
    public Result getVersionAllCount(SelectUser selectUser) {
        return ResultUtil.success(versionDao.getVersionAllCount(selectUser));
    }

    @Override
    @Transactional
    public Result delVersion(String vid) {
        return ResultUtil.success(versionDao.delVersion(vid));
    }

    @Override
    @Transactional
    public Result addVersion(AppVersion appVersion) {
        appVersion.setVId(UUID.randomUUID().toString());
        return ResultUtil.success(versionDao.addVersion(appVersion));
    }

    @Override
    @Transactional
    public Result updVersion(AppVersion appVersion) {
        return ResultUtil.success(versionDao.updVersion(appVersion));
    }
}
