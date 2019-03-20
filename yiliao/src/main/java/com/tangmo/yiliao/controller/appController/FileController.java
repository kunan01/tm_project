package com.tangmo.yiliao.controller.appController;

import com.tangmo.yiliao.controller.BaseController;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.file.ChangeFormat;
import com.tangmo.yiliao.utility.util.EncryptUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * @author boge
 * @date 2018/7/6.
 * @description 文件控制层
 */
@Api("文件控制层")
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    /**
     * @api {POST} /file/read 附件查看
     * @apiGroup File
     * @apiVersion 0.0.1
     * @apiDescription 查看文件, 需要传输文件id, 返回ResponseEntity<byte[]>
     * @apiParam {String} rfId 文件id
     * @apiParamExample {uri} 请求样例:
     * /file/read/d5540ff2db804a8daf1050a06679155f
     */
    @PostMapping("/read")
    public Result readFile(@RequestBody SelectUser selectUser) {
        return imgFileService.downloadFile(selectUser.getCondName());
    }

    /**
     * @api {GET} /file/video/{rfId} 播放视频
     * @apiGroup File
     * @apiVersion 0.0.1
     * @apiDescription 查看文件, 需要传输文件id, 返回ResponseEntity<byte[]>
     * @apiParam {String} rfId 文件id
     * @apiParamExample {uri} 请求样例:
     *      /file/video/d5540ff2db804a8daf1050a06679155f
     */
    @GetMapping("/video/{rfId}")
    public void playVideo(HttpServletRequest request, HttpServletResponse response, @PathVariable String rfId) {
        imgFileService.playVideo(request, response, rfId);
    }


    public void player2(HttpServletRequest request, HttpServletResponse response) {
          String path = request.getServletPath();
    }

    /**
     * @api {POST} /file/upload/{userId} 上传图片
     * @apiGroup File
     * @apiVersion 0.0.1
     * @apiDescription 上传用户图片
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          file:"图片文件",
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功; -1请求失败
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {"code":"0"}
     */
    @PostMapping(value = "/upload/{userId}")
    public Result loadFeedbackImg(@PathVariable String userId,MultipartHttpServletRequest multipartRequest) {

        Iterator<String> itr = multipartRequest.getFileNames();
        while (itr.hasNext()){
            String str = itr.next();
            MultipartFile file = multipartRequest.getFile(str);
            if(file != null){
                return imgFileService.uploadImg(userId,file);
            }
        }
        return ResultUtil.error("图片不能为空");
    }


    /**
     * @api {POST} /file/video/upload/{userId} 上传视频
     * @apiGroup File
     * @apiVersion 0.0.1
     * @apiDescription 上传视频
     * @apiParamExample {json} 请求样例:
     * {
     *  file:"图片文件",
     * }
     * @apiSuccess (success) {POST} code 0:请求成功; -1:请求失败
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {"code":"0"}
     */
    @PostMapping(value = "/video/upload/{userId}")
    public Result loadVideo(@PathVariable String userId ,MultipartHttpServletRequest multipartRequest) {
        Iterator<String> itr = multipartRequest.getFileNames();

        while (itr.hasNext()){
            String str = itr.next();
            MultipartFile file = multipartRequest.getFile(str);
            if(file != null){
                return imgFileService.uploadImg(userId,file);
            }
        }
        return ResultUtil.error("图片不能为空");
    }

    /**
     * @api {POST} /file/video/amrToMp3/{userId} 上传mp3
     * @apiGroup File
     * @apiVersion 0.0.1
     * @apiDescription 上传视频
     * @apiParamExample {json} 请求样例:
     * {
     *  file:"图片文件",
     * }
     * @apiSuccess (success) {POST} code 0:请求成功; -1:请求失败
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {"code":"0"}
     */
    @PostMapping(value = "/video/amrToMp3/{userId}")
    public Result amrToMp3(@PathVariable String userId ,MultipartHttpServletRequest multipartRequest) {
        Iterator<String> itr = multipartRequest.getFileNames();

        while (itr.hasNext()){
            String str = itr.next();
            MultipartFile file = multipartRequest.getFile(str);
            if(file != null){

                Result result = imgFileService.uploadImg(userId,file);

                String abc = result.getData().toString().split("\\.")[0];

                ChangeFormat.changeAmrToMp3(abc);

                abc = abc+".mp3";
                return ResultUtil.success(abc);
            }
        }
        return ResultUtil.error("图片不能为空");
    }

    /**
     * @api {POST} /file/getShareCode 获取小程序分享二维码
     * @apiGroup File
     * @apiVersion 0.0.1
     * @apiDescription 上传视频
     * @apiParamExample {json} 请求样例:
     *            /file/getShareCode/dqwndueq.asdjnj/280
     * @apiSuccess (success) {POST} code 0:请求成功; -1:请求失败
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {"code":"0"}
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name="path",value="路径",dataType="String",required=true,paramType="query"),
            @ApiImplicitParam(name="str",value="参数",dataType="String",required=true,paramType="query")
    })
    @PostMapping(value = "/getShareCode")
    public Result getShareCode(String path,String str) {
        if(path == null || str == null || str.equals("")){
            return ResultUtil.paramError();
        }
        return imgFileService.getShareCode(path,str);
    }

}
