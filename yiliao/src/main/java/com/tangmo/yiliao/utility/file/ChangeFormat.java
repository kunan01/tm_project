package com.tangmo.yiliao.utility.file;

import com.tangmo.yiliao.utility.file.jave.AudioAttributes;
import com.tangmo.yiliao.utility.file.jave.Encoder;
import com.tangmo.yiliao.utility.file.jave.EncodingAttributes;

import java.io.*;


public class ChangeFormat {

    public static void changeAmrToMp3(String str){

        String fPath = "E:\\Tomcat 8.0\\webapps\\static"+str+".amr";

        String targetPath = "E:\\Tomcat 8.0\\webapps\\static"+str+".mp3";

        File f = new File(fPath);

        File target = new File(targetPath);
        AudioAttributes audio = new AudioAttributes();
        Encoder encoder = new Encoder();

        audio.setCodec("libmp3lame");
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        try {
            encoder.encode(f, target, attrs);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }

        f.delete();
    }

    //MultipartFile转file
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
