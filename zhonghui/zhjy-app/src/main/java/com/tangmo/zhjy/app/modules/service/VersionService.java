package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.Version;

/**
 * Created by chengge on 2018/10/9.
 */
public interface VersionService {

    Result getVersion(String versionNumber);

    Result addVersion(Version version);
}
