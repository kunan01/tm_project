package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.RsFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author boge
 * @date 17/12/22
 * @description 文件服务
 */

public interface ImgFileService {

    /**
     * 增加图片文件,并存储
     *
     * @param code   图像base64编码
     * @param userId 用户id
     * @return
     */
    RsFile addImgFile(String code, Integer userId);

    /**
     * 查找文件路径
     *
     * @param fileId 文件名
     * @return
     */
    String getImgFilePath(String fileId);


    /**
     * 下载文件
     *
     * @param rfId
     * @param userAgent
     * @return
     */
    ResponseEntity<byte[]> downloadFile(String rfId, String userAgent);

    /**
     * 上传文件
     *
     * @param file
     * @param userId
     * @return
     */
    String uploadFile(MultipartFile file, Integer userId);

    /**
     * 上传图片
     *
     * @param userId
     * @param file
     * @return
     */
    Result uploadImg(Integer userId, MultipartFile file);
}
