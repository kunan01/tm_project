package com.tangmo.zhjy.app.modules.controller;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.service.ImgFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 18/1/5
 * @description 文件控制层
 */
@Api("文件控制层")
@RestController
@RequestMapping("/file")
public class FileController{

    @Resource
    private ImgFileService imgFileService;
    /**
     * @api {GET} /file/read/{rfId} 附件查看
     * @apiGroup Resource
     * @apiVersion 0.0.1
     * @apiDescription 查看文件,需要传输文件id, 返回ResponseEntity<byte[]>
     * @apiParam {String} rfId 文件id
     * @apiParamExample {uri} 请求样例:
     *                   /file/read/d5540ff2db804a8daf1050a06679155f
     */
    @ApiOperation(value="附件查看")
    @GetMapping("/read/{rfId}")
    public ResponseEntity<byte[]> readFile(@PathVariable String rfId){
        String userAgent = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("USER-AGENT");
        return imgFileService.downloadFile(rfId, userAgent);
    }

    /**
     * @api {POST} /file/upload/{userId} 上传用户图片
     * @apiGroup File
     * @apiVersion 0.0.1
     * @apiDescription 上传用户图片
     * @apiParamExample {json} 请求样例:
     *                   {
     *                      file:"图片文件",
     *                   }
     * @apiSuccess (success) {POST} code success:请求成功； fail:请求失败；offline：掉线；param_error：请求参数错误;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                    {"code":"success"}
     */
    @ApiOperation(value="上传用户图片")
    @PostMapping("/upload/{userId}")
    public Result loadFeedbackImg(@PathVariable Integer userId, MultipartFile file){
        return imgFileService.uploadImg(userId,file);
    }
}
