package com.tangmo.emall.dao;

import com.tangmo.emall.entity.RsFile;
import org.springframework.stereotype.Repository;


/**
 * @author Chamber
 * @date 2019/1/9.
 * @Description
 */
@Repository
public interface FileDao {

    /*添加图片*/
    int addFile(RsFile file);

    //通过id查询资源文件对象
    RsFile getFileById(String fId);

    //通过id删除资源文件对象
    int delFile(String fId);

    //修改图片状态为已用
    int updFile(String fId);

    //更新未使用的图片
    int updateFile();
}
