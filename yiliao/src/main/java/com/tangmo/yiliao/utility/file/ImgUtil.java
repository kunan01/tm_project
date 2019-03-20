package com.tangmo.yiliao.utility.file;

import com.alibaba.fastjson.JSONObject;
import com.tangmo.yiliao.utility.code.OrderRelated;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author boge
 * @date 2018-8-27
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

    /**
     * 得到amr的时长
     *
     * @param path
     * @return amr文件时间长度
     * @throws
     */
    public static int getAmrDuration(String path) throws IOException {
        java.io.File file = new java.io.File(path);

        long duration = -1;
        int[] packedSize = { 12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0,
                0, 0 };
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            long length = file.length();// 文件的长度
            int pos = 6;// 设置初始位置
            int frameCount = 0;// 初始帧数
            int packedPos = -1;

            byte[] datas = new byte[1];// 初始数据值
            while (pos <= length) {
                randomAccessFile.seek(pos);
                if (randomAccessFile.read(datas, 0, 1) != 1) {
                    duration = length > 0 ? ((length - 6) / 650) : 0;
                    break;
                }
                packedPos = (datas[0] >> 3) & 0x0F;
                pos += packedSize[packedPos] + 1;
                frameCount++;
            }

            duration += frameCount * 20;// 帧数*20
        } finally {
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        }
        return (int)((duration/1000)+1);
    }

    /**
     * 生成带参小程序二维码
     * @param sceneStr	参数
     * @param accessToken	token
     * @param path	路径
     */
    public static String getminiqrQr(String sceneStr, String accessToken,String path) {
        try
        {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", sceneStr);
            paramJson.put("page", path);
            paramJson.put("width", 300);
            paramJson.put("auto_color", true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */
            String str = OrderRelated.getOrderNo();

            printWriter.write(paramJson.toString());
            System.out.println(paramJson);
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            //Users/hanjialin/Desktop/
            OutputStream os = new FileOutputStream(new File("E:/Tomcat 8.0/webapps/static/shareCode/"+str+".png"));
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1)
            {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
            return str+".png";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
