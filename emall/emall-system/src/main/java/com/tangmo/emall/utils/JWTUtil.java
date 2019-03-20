package com.tangmo.emall.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tangmo.emall.entity.SysUser;
import com.tangmo.emall.entity.User;

import java.util.*;

public class JWTUtil {

    //用户生成token
    public static String createTokenWithClaim(SysUser sysUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0);//2小过期
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            String token = JWT.create()
                    /*设置头部信息 Header*/
                    .withHeader(map)
                    .withClaim("sysUserId",sysUser.getSysUserId())
                    .withIssuer("SYSTEM_SERVICE")//签名是有谁生成 例如 服务器
                    .withSubject("this is test token")//签名的主题
                    .withAudience("SYS")//签名的观众 也可以理解谁接受签名的
                    .withIssuedAt(nowDate) //生成签名的时间
                    .withExpiresAt(expireDate)//签名过期的时间
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            exception.printStackTrace();
        }
        return null;
    }
    /**
     * 返回一定时间后的日期
     * @param date 开始计时的时间
     * @param year 增加的年
     * @param month 增加的月
     * @param day 增加的日
     * @param hour 增加的小时
     * @param minute 增加的分钟
     * @param second 增加的秒
     * @return
     */
    public static Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second){
        if(date == null){
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if(year != 0){
            cal.add(Calendar.YEAR, year);
        }
        if(month != 0){
            cal.add(Calendar.MONTH, month);
        }
        if(day != 0){
            cal.add(Calendar.DATE, day);
        }
        if(hour != 0){
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute != 0){
            cal.add(Calendar.MINUTE, minute);
        }
        if(second != 0){
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }

    public static Integer verifyToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("SYSTEM_SERVICE")
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            Map<String, Claim> claims = jwt.getClaims();
            Claim claim = claims.get("sysUserId");
            System.out.println(claim.asInt());
            return claim.asInt();
        } catch (JWTVerificationException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        JWTUtil demo = new JWTUtil();
        //String createToken = demo.createToken();
//        User user = new User();
//        user.setUserId(1);
//        String token = demo.createTokenWithClaim(user);
//        String token1 = demo.createTokenWithClaim(user);
//        System.out.println(token);
//        System.out.println(token1);
//        demo.verifyToken(token);
//        demo.verifyToken(token1);
//        System.out.println(token);
//        demo.verifyToken(token);
    }
}
