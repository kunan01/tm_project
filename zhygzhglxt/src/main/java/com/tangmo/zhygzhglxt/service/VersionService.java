package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.Version;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.http.ResponseEntity;

/**
 * Created by chengge on 2018/10/9.
 */
public interface VersionService {

    Result<Version> getVersion(String versionNumber, String type);

    Result addVersion(Version version);

    ResponseEntity<byte[]> uploadVersion1(String userAgent);

    ResponseEntity<byte[]> uploadVersion2(String userAgent);

}
