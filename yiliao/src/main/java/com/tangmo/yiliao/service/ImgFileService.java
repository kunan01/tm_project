package com.tangmo.yiliao.service;

import com.tangmo.yiliao.entity.RsFile;
import com.tangmo.yiliao.utility.code.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author boge
 * @date 2018-8-29
 * @description 文件服务
 */

public interface ImgFileService {

    /**
     * 下载文件
     * ResponseEntity<byte[]>
     */
    Result downloadFile(String rfId);

    /**
     * 播放视频
     *
     * @param response
     * @param rfId
     */
    void playVideo(HttpServletRequest request, HttpServletResponse response, String rfId);

    /**
     * 上传文件
     *
     * @param file
     * @param userId
     * @return
     */
    String uploadFile(MultipartFile file, String userId);

    /**
     * 上传图片
     *
     * @param userId
     * @param file
     * @return
     */
    Result uploadImg(String userId, MultipartFile file);

    //获取小程序分享二维码
    Result getShareCode(String path,String paramStr);
}
