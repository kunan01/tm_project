package com.tangmo.zhjy.system.modules.dao;

import com.tangmo.zhjy.system.modules.bean.RsFile;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author boge
 * @date 17/12/22
 * @description 文件信息Dao接口
 */
@Mapper
public interface FileDao {

    /**
     * 增加文件信息
     *
     * @param file
     * @return
     */
    int insertSelective(RsFile file);

    /**
     * 查询文件路径信息
     *
     * @param rfId
     * @return
     */
    RsFile selectById(String rfId);
}
