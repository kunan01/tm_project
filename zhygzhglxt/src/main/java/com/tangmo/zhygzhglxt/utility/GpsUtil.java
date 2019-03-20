package com.tangmo.zhygzhglxt.utility;

import com.alibaba.fastjson.JSONObject;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.utility.sendMsg.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengge on 2018/12/12.
 */
public class GpsUtil {

    public static final String MAP_URL = "https://restapi.amap.com/v3/assistant/coordinate/convert";
//    /**
//     * 坐标转换，腾讯地图转换成百度地图坐标
//     * @param lat 腾讯纬度
//     * @param lon 腾讯经度
//     * @return 返回结果：经度,纬度
//     */
//    public static Gps map_tx2bd(double lat, double lon){
//        double bd_lat;
//        double bd_lon;
//        double x_pi=3.14159265358979324;
//        double x = lon, y = lat;
//        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
//        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
//        bd_lon = z * Math.cos(theta) + 0.0065;
//        bd_lat = z * Math.sin(theta) + 0.006;
//
//        System.out.println("bd_lat:"+bd_lat);
//        System.out.println("bd_lon:"+bd_lon);
//         ;
//
//        return new Gps(bd_lat,bd_lon);
//    }
//
//    /**
//     * 坐标转换，百度地图坐标转换成腾讯地图坐标
//     * @param lat  百度坐标纬度
//     * @param lon  百度坐标经度
//     * @return 返回结果：纬度,经度
//     */
//    public static Gps map_bd2tx(double lat, double lon){
//        double tx_lat;
//        double tx_lon;
//        double x_pi=3.14159265358979324;
//        double x = lon - 0.0065, y = lat - 0.006;
//        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
//        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
//        tx_lon = z * Math.cos(theta);
//        tx_lat = z * Math.sin(theta);
//        return new Gps(tx_lat,tx_lon);
//    }


    //gps到腾讯（高德）
    public static Result gps_to_tx(String la, String lo) throws Exception {

        if (la == null || "".equals(la) || Double.parseDouble(la) <= 0) {
            return new Result(ResultCode.FAIL, "纬度不能为空！");
        }
        if (lo == null || "".equals(lo) || Double.parseDouble(lo) <= 0) {
            return new Result(ResultCode.FAIL, "经度不能为空！");
        }
        System.out.println("la纬度============" + la);
        System.out.println("lo经度============" + lo);
        String result = null;
        JSONObject json = null;
        Gps gps = new Gps();
        String url = MAP_URL + "?key=adcd18741152a223b03252155a4a461b&locations=" + lo + "," + la + "&coordsys=gps";
        try {
            result = HttpUtil.sendGet(url);
            json = JSONObject.parseObject(result);
            System.out.println(json);
            if (json.getIntValue("status") != 1) {
                System.out.println(json.getString("info"));
            } else {
                String locations = json.getString("locations");
                System.out.println("=======" + locations + "======");
                String[] lalo = locations.split(",");
                gps.setWgLon(lalo[0]);
                gps.setWgLat(lalo[1]);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SUCCESS, gps);
    }

//    public static void main(String[] args) throws Exception {
//
//
//        Gps gps = (Gps)GpsUtil.gps_to_tx("33.076202","108.217070").getData();
//
//        System.out.println("===la== "+gps.getWgLat()+" =======");
//        System.out.println("===lo== "+gps.getWgLon()+" =======");
//
//    }

}
