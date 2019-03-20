package com.tangmo.emall.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;

public class MailUtil {

    /**
     * 发送邮件(找回密码)
     * to : 需要发送的邮箱
     */
    public static String send_mail(String email) throws MessagingException {

        //创建连接对象 连接到邮件服务器
        Properties properties = new Properties();
        //发送邮件服务器
        properties.put("mail.smtp.host", "smtp.qq.com");
        properties.put("mail.smtp.auth", "true");

        //QQ邮箱需要，
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        //设置发送邮件的账号和密码
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //两个参数分别是发送邮件的账户和密码  qq邮箱为授权码
                return new PasswordAuthentication("1473747181@qq.com","iseqevvhcpcjgbej");
            }
        });

        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("1473747181@qq.com"));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));

        //设置主题
        message.setSubject("JM Optical");

        String str = sixRandomNumber();

        String content = "<html><head></head><body>测试验证码"+str+"</body></html>";

        //设置邮件正文  第二个参数是邮件发送的类型
        message.setContent(content,"text/html;charset=UTF-8");

        //发送一封邮件
        Transport.send(message);

        return str;
    }

    /**
     * 生成六位随机数
     *
     * @return
     */
    public static String sixRandomNumber() {
        Random random = new Random();
        String result="";
        for (int i=0;i<4;i++)
        {
            result+=random.nextInt(10);
        }
        return result;
    }


}
