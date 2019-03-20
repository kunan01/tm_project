package com.tangmo.emall.service.impl;

import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.entity.RsFile;
import com.tangmo.emall.service.FileService;
import com.tangmo.emall.utils.EncryptUtil;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author hanjialin
 * @date 2019/1/9.
 * @Description
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;

    @Override
    @Transactional
    public Result addFile(String base64) {

        RsFile file = new RsFile();

        file.setFId(String.valueOf(EncryptUtil.randomPwd() + System.currentTimeMillis()));
        file.setFBase(base64);

        fileDao.addFile(file);
        return ResultUtil.success(file.getFId());
    }

    @Override
    public String getBaseByImageId(String imageId) {
        try {
            if(imageId == null){
                return null;
            }
            RsFile rsFile = fileDao.getFileById(imageId);
            if(rsFile == null){
                return null;
            }
            return rsFile.getFBase();
        }catch (Exception e){
            System.out.println("获取图片接口异常"+e.getMessage());
            return null;
        }
    }

}
