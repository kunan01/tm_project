package com.tangmo.zhjy.system.modules.service.impl;


import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.RsFile;
import com.tangmo.zhjy.system.modules.dao.FileDao;
import com.tangmo.zhjy.system.modules.service.ImgFileService;
import com.tangmo.zhjy.system.utils.EncryptUtil;
import com.tangmo.zhjy.system.utils.ImgUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author boge
 * @date 17/12/22
 * @description
 */
@Service("fileService")
public class ImgFileServiceImpl implements ImgFileService {

    @Resource
    private FileDao fileDao;
    @Autowired
    private Environment env;

    private static final String IMG_TYPE = "jpeg";
    private final static String AGENT_MSIE = "MSIE";
    private final static String AGENT_TRIDENT = "Trident";
    private final static String AGENT_EDGE = "Edge";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RsFile addImgFile(String code, Integer userId) {
        RsFile file = getFileInfo(userId,IMG_TYPE);
        int row = fileDao.insertSelective(file);
        String basePath = env.getProperty("RF.BASE_DIR");
        if(row == 1){
            Boolean isSave = ImgUtil.code2Disk(code,basePath+file.getPath());
            if(!isSave){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return null;
            }
            return file;
        }
        return null;
    }

    @Override
    public String getImgFilePath(String fileId) {
        return null;
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(String rfId, String userAgent) {
        ResponseEntity<byte[]> responseEntity = null;
        try {
            RsFile rf = fileDao.selectById(rfId);
            if (rf == null || rf.getPath() == null) {
                return null;
            }
            //获取文件目录
            String basePath = env.getProperty("RF.BASE_DIR");
            String resourceName = rf.getPath();
            String filePath = new StringBuilder(basePath).append("/").append(resourceName).toString();
            String fileType = resourceName.substring(resourceName.indexOf(".") + 1);
            responseEntity = downloadEntity(userAgent, filePath, "a", fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public String uploadFile(MultipartFile file, Integer userId) {
        String[] strings = uploadFileReturnType(file, userId);
        return strings[0];
    }

    @Override
    public Result uploadImg(Integer userId, MultipartFile file) {
        if(userId == null){
            return new Result(ResultCode.FAIL);
        }
        if(file!=null){
            String uuid = uploadFile(file,userId);
            if(uuid == null){
                return new Result(ResultCode.FAIL);
            }
            return new Result(ResultCode.SUCCESS,uuid);
        }else{
            return new Result(ResultCode.FAIL);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    public String[] uploadFileReturnType(MultipartFile file, Integer userId) {
        String[] rfInfo = new String[2];
        //验证文件格式
        String fileOriginalName = file.getOriginalFilename();
        int index = fileOriginalName.lastIndexOf(".") + 1;
        String fileType = fileOriginalName.substring(index);
        rfInfo[1] = fileType;
        String dir = env.getProperty("RF.BASE_DIR");
        String uuid = EncryptUtil.get32Uuid();
        StringBuilder sb = new StringBuilder("").append("/").append(userId).append("/")
                .append(uuid).append(".").append(fileType);
        String fileName = sb.toString();
        boolean bool = uploadFileEntity(file, dir, fileName);
        if (!bool) {
            return null;
        }
        RsFile rf = new RsFile();
        rf.setRfId(uuid);
        rf.setPath(fileName);
        rf.setUserId(userId);
        int row = fileDao.insertSelective(rf);
        if (row > 0) {
            rfInfo[0] = uuid;
            return rfInfo;
        }
        return null;
    }

    /**
     * 上传文件的方法
     * <p>
     * 1.使用MultipartFile 获取文件;
     * 2.将上传上来的文件存储与临时目录
     *
     * @param file MultipartFile 要上传的文件
     * @return fileName  文件名
     * @throws boolean 成功:true; 失败:false
     */
    private boolean uploadFileEntity(MultipartFile file, String dir, String fileName) {
        try {
            File newFile = new File(dir, fileName);
            //该方法内部会自动把用到的IO流关掉
            FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 下载文件实体
     *
     * @param agent    浏览器标识
     * @param srcFile  源文件路径
     * @param fileName 下载后的文件名
     * @param fileType 下载后的类型
     * @return
     * @throws Exception
     */
    private static ResponseEntity<byte[]> downloadEntity(String agent, String srcFile, String fileName, String fileType)
            throws Exception {
        byte[] body;
        InputStream in = null;
        ResponseEntity<byte[]> response = null;
        try {
            in = new FileInputStream(new File(srcFile));
            //预估文件的有效大小,放入一个body输出
            body = new byte[in.available()];
            in.read(body);
            HttpHeaders headers = new HttpHeaders();
            //浏览器辨别
            if (agent == null) {
                throw new Exception("浏览器标识未获取");
            }
            if (agent.indexOf(AGENT_MSIE) != -1 || agent.indexOf(AGENT_TRIDENT) != -1 || agent.indexOf(AGENT_EDGE) != -1) {
                fileName = fileName.replace(" ", "_");
                fileName = java.net.URLEncoder.encode(fileName, "UTF8");
            } else {// 火狐,chrome等
                fileName = new String(fileName.trim().getBytes("UTF-8"), "iso-8859-1");
            }
            //文件下载后的名称和类型
            headers.add("Content-Disposition", "attachment;filename=" + fileName + "." + fileType.trim());
            //响应状态码ok=200
            HttpStatus statusCode = HttpStatus.OK;
            //返回的数据
            response = new ResponseEntity<byte[]>(body, headers, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("下载文件异常：" + e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                throw new Exception("文件输入流关闭时发生异常");
            }
        }
        return response;
    }


    /**
     * 根据用户id,获取文件具体信息 32位主键编码和存放路径
     *
     * @param userId 用户id
     * @param fileType 文件类型
     * @return
     */
    public RsFile getFileInfo(Integer userId, String fileType) {
        String uuid = EncryptUtil.get32Uuid();
        StringBuilder sb = new StringBuilder("").append("/").append(userId).append("/")
                .append(uuid).append(".").append(fileType);
        RsFile file = new RsFile();
        file.setRfId(uuid);
        file.setPath(sb.toString());
        file.setUserId(userId);
        return file;
    }
}
