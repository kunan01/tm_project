package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.AppVersion;
import com.tangmo.yiliao.entity.SelectUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VersionDao {

    AppVersion getVersion();

    List<AppVersion> getVersionAll(SelectUser selectUser);

    Integer getVersionAllCount(SelectUser selectUser);

    int delVersion(@Param("vid") String vid);

    int addVersion(AppVersion appVersion);

    int updVersion(AppVersion appVersion);
}
