package com.tangmo.emall.service;

import com.tangmo.emall.utils.Result;

/**
 * @author hanjialin
 * @date 2019/1/10.
 * @Description
 */
public interface FileService {

    /*添加图片*/
    Result addFile(String base64);

    //获取图片
    String getBaseByImageId(String imageId);

}
