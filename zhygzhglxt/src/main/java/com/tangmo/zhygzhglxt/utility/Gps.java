package com.tangmo.zhygzhglxt.utility;

/**
 * Created by chengge on 2018/9/14.
 */
public class Gps {


    public String wgLat;//纬度
    public String wgLon;//经度

    public Gps() {

    }


    public Gps(String wgLat, String wgLon) {
        setWgLat(wgLat);
        setWgLon(wgLon);
    }

    public String getWgLat() {
        return wgLat;
    }

    public void setWgLat(String wgLat) {
        this.wgLat = wgLat;
    }

    public String getWgLon() {
        return wgLon;
    }

    public void setWgLon(String wgLon) {
        this.wgLon = wgLon;
    }

    @Override
    public String toString() {
        return wgLat + "," + wgLon;
    }
}
