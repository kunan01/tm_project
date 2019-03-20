package com.tangmo.zhjy.app.utils;

import sun.misc.BASE64Decoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author boge
 * @date 17/12/22
 * @description 文件工具类
 */

public class ImgUtil {

    /**
     * base64转化为图片byte[]
     *
     * @param code
     * @return
     */
    public static byte[] decryptCode(String code) {

        if (code == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return decoder.decodeBuffer(code);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 图片存储到硬盘上
     *
     * @param path  存储路径
     * @param bytes 图片base64code
     * @return
     */
    public static boolean saveImg2Disk(byte[] bytes, String path) {

        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] < 0) {
                bytes[i] += 256;
            }
        }

        try {
            java.io.File fileDir = new java.io.File(path);
            if (!fileDir.getParentFile().exists()) {
                boolean mkdirs = fileDir.getParentFile().mkdirs();
                if (!mkdirs) {
                    return false;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 图片code直接存储
     *
     * @param code
     * @param path
     * @return
     */
    public static boolean code2Disk(String code, String path) {
        if (code.equals("")) {
            return false;
        }
        byte[] bytes = decryptCode(code);
        if (bytes == null) {
            return false;
        }
        return saveImg2Disk(bytes, path);
    }
}
