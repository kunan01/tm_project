package com.tangmo.yiliao.service;


import com.tangmo.yiliao.entity.AppVersion;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date
 * @description 系统版本
 */
public interface VersionService {

    Result getVersion();

    Result getVersionAll(SelectUser selectUser);

    Result getVersionAllCount(SelectUser selectUser);

    Result delVersion(String vid);

    Result addVersion(AppVersion appVersion);

    Result updVersion(AppVersion appVersion);

}
