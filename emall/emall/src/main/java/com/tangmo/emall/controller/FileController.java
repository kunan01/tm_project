package com.tangmo.emall.controller;

import com.tangmo.emall.controller.base.BizBaseController;
import com.tangmo.emall.utils.OrderRelated;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author hanjialin
 * @date 2019/1/10.
 * @Description
 */
@Api("文件层")
@RestController
@RequestMapping("/web/file")
public class FileController extends BizBaseController {

    private final static String AGENT_MSIE = "MSIE";
    private final static String AGENT_TRIDENT = "Trident";
    private final static String AGENT_EDGE = "Edge";

    /**
     * @api {POST} /file/upload 上传图片
     * @apiGroup File
     * @apiVersion 0.0.1
     * @apiDescription 上传用户图片
     * @apiParamExample {json} 请求样例:
     *                      {
     *                          file:"图片文件",
     *                      }
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : "图片id"
     *                      }
     */
    @ApiOperation(value="图片上传接口",notes="只支持jpg,png格式")
    @PostMapping(value = "/upload")
    public Result loadFeedbackImg(MultipartFile file) {

        if(file == null){
            return ResultUtil.error("图片不能为空");
        }

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        if(suffix.equals("jpg") || suffix.equals("png")){
            byte[] data = null;

            try {
                InputStream in = file.getInputStream();
                data = new byte[in.available()];
                in.read(data);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(data!=null && data.length != -1) {
                // 对字节数组Base64编码
                String base64 = "data:image/png;base64,"+ new String(Base64.encodeBase64(data));
                return fileService.addFile(base64);
            }
            return ResultUtil.error("上传图片失败");
        }else{
            return ResultUtil.error("错误：只支持jpg,png格式的图片");
        }

    }

    /**
     * @api {GET} /file/baseToImage 获取base64图片
     * @apiGroup File
     * @apiVersion 0.0.1
     * @apiDescription 获取base64图片
     * @apiParamExample {json} 请求样例:
     *      /file/baseToImage?imageId=asdasd
     * @apiSuccess (success) {GET} 0:请求成功;
     * @apiSuccess (success) {GET} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" :
     *                      }
     */
    @ApiOperation(value="获取base64图片",notes="获取base64图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name="imageId",value="图片id",dataType="String",required=true,paramType="query")
    })
    @GetMapping(value = "/baseToImage")
    public String baseToImage(String imageId){

        String base64String = fileService.getBaseByImageId(imageId);
        return base64String;
    }

    /**
     * @api {POST} /file/showPicture 直接展示图片
     * @apiGroup File
     * @apiVersion 0.0.1
     * @apiDescription 直接展示图片
     * @apiParamExample {json} 请求样例:
     *      /file/showPicture?imageId=asdasd
     * @apiSuccess (success) {POST} 0:请求成功;
     * @apiSuccess (success) {POST} data 返回数据
     * @apiSuccessExample {json} 返回样例:
     *                      {
     *                          "code" : "0",
     *                          "msg"  : "请求成功",
     *                          "data" : ""
     *                      }
     */
    @ApiOperation(value="直接展示图片",notes="直接展示图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name="imageId",value="图片id",dataType="String",required=true,paramType="query")
    })
    @GetMapping(value = "/showPicture")
    public void baseToImage(String imageId,HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置响应的类型格式为图片格式
        response.setContentType("image/png");

        String base64String = fileService.getBaseByImageId(imageId);

        byte[] data = Base64.decodeBase64(base64String.substring(22));

        OutputStream toClient = response.getOutputStream();

        toClient.write(data);

        toClient.flush();

        toClient.close();
    }
}
